package br.com.sw2.gac.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.sw2.gac.dao.OcorrenciaDAO;
import br.com.sw2.gac.dao.TelefoniaDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.modelo.Ligacao;
import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.PausaLigacao;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.socket.constants.TipoLigacao;
import br.com.sw2.gac.socket.exception.SocketConnectionException;
import br.com.sw2.gac.socket.exception.SocketException;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.vo.LigacaoVO;

/**
 * <b>Descrição: Componente de integração com o SoftPhone</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class SocketPhone  {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(SocketPhone.class);

    /** Atributo socket. */
    private Socket socket = null;

    /** Atributo ramal ativo. */
    private boolean ramalAtivo = false;

    /** Indica se o agente/atendente está autenticado. */
    private boolean atendenteAutenticado = false;

    /** Indica se o atendente está disponível para receber ligações. */
    private boolean atendenteDisponivel = false;

    /** Indica se houve ou não um abandona na na fila de atendimento. */
    private boolean abandonoNaFila = false;;

    /** Indica que uma ligação passada aoa tendente/agente não foi atendida. */
    private boolean agenteNaoAtende = false;

    /** Indica se o atendente atendeu ou não uma chamada passada para ele.*/
    private boolean agenteAtendeuLigacao = false;

    /** Indica se o atendente atendeu ou não uma chamada passada para ele.*/
    private boolean agenteSendoChamado = false;
    
    /** Indica que o atendente/agente está em atendimento de uma chamada. */
    private boolean emAtendimento = false;

    /** Atributo alerta chamada. */
    private String alertaChamada = "";

    /** Linhas utilizadas pelo softPhone. */
    private List<Line> linhas;

    /**Indica quantas ligações há na fila de emergências. */
    private Integer qtdeLigacoesFilaAtendimentoEmergencia = 0;

    /** Define quantos TimeStamp serão considerado timeoutpara algumas operações. */
    public static final int DEFAULT_TIMEOUT = 2;

    /** Atributo aviso ligacao emergencia. */
    private boolean avisoLigacaoEmergencia;

    /** Atributo numero discagem login agente. */
    private String numeroDiscagemLoginAgente = "*9800";

    /** Atributo user ramal. */
    private Integer userRamal;

    /** Atributo codigo agente tambem chamado de atendente. */
    private Integer codigoAgente;

    /** Atributo senha agente. */
    private String senhaAgente;

    /** Atributo telefonia dao. */
    private TelefoniaDAO  telefoniaDAO;
    
    /** Atributo ligacao. */
    private LigacaoVO chamadaParaOAgente = null;
        
    /** Atributo ocorrencia dao. */
    private OcorrenciaDAO ocorrenciaDAO = null;
    
    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketPhone.
     */
    public SocketPhone() {
        
        this.telefoniaDAO = new TelefoniaDAO();
        
        try {
            this.ocorrenciaDAO = new OcorrenciaDAO();
        } catch (Exception e) {
            this.logger.error("N�o � poss�vel iniciar o banco de dados GAC. ");
        }

        this.linhas = new ArrayList<Line>();
        for (int i = 1;  i < 7; i++) {
            Line linha = new Line();
            linha.setStatusLinha(StatusLigacao.LIVRE.getValue());
            linha.setTipoLigacao(0);
            linha.setNumeroLinha(i);
            this.linhas.add(linha);
        }
    }

    /**
     * Nome: connect Connect.
     * @param host the host
     * @param port the port
     * @throws SocketException the socket exception
     * @see
     */
    public void conectarAoSocketServer(String host, int port) throws SocketException {
        try {
            this.socket = new Socket(host, port);
            this.socket.setKeepAlive(true);

            BufferedReader recebidos = null;
            try {
                recebidos = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                String retorno = "";
                while ((retorno = recebidos.readLine()) != null) {
                    if (!recebidos.ready()) {
                        break;
                    }
                }
                this.logger.debug(this.getClass(), " Conectado ao host " + host + ":" + port);
                this.logger.debug(retorno);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ConnectException e) {
            throw new SocketException("Não é possível conectar ao servidor socket: " + host + ":"
                + port);
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }

    /**
     * Nome: sendMessage Send message.
     * @param str the str
     * @see
     */
    public void enviarMensagem(String str) {
        this.logger.debug(this.getClass(), "***** Enviando mensagem para o servidor socket: \n" + str);
        if (null == this.socket) {
            this.logger.error(this.getClass().getName() + " - Não há uma conexão estabelecida com o servidor socket");
        } else {
            try {
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                write.write(str);
                write.flush();
            } catch (Exception e) {
                this.logger.debug("***** Não foi possível enviar a mensagem  ao servidor socket.\n" + e.getMessage());
                throw new SocketException(e);
            }
        }
    }

    /**
     * Nome: getEvento
     * Recupera o valor do atributo 'evento'.
     *
     * @return valor do atributo 'evento'
     * @see
     */
    public Event getEvento() {
        Event retorno = null;
        try {
            BufferedReader recebidos = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            String mensagem = lerStream(recebidos);
            retorno = parse2Event(mensagem);
        } catch (IOException e) {
            throw new SocketException("Erro de IO ao obter socket.getInputStream()");
        } catch (SocketException e) {
            throw new SocketException(e);
        }
        return retorno;
    }

    /**
     * Nome: lerStream
     * Ler stream.
     *
     * @param bf the bf
     * @return string
     * @throws SocketException the socket exception
     * @see
     */
    private String lerStream(BufferedReader bf) throws SocketException {
        String message = "";
        String mensagemCompleta = "";

        try {
            if (bf.ready()) {
                while ((message = bf.readLine()) != null) {
                    mensagemCompleta += message + ":";
                    if (!bf.ready()) {
                        break;
                    }
                }
            }
        } catch (SocketTimeoutException e) {
            throw new SocketException("O Servidor não está respondendo");
        } catch (Exception e) {
            throw new SocketException(e);
        }

        return mensagemCompleta;
    }

    /**
     * Nome: aguardarResposta Aguardar resposta.
     * @return string
     * @throws IOException the IO exception
     * @see
     */
    public String aguardarResposta() throws IOException {

        BufferedReader recebidos = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String retorno = "";
        String retornoCompleto = "";
        String mensagemDebug = "";
        this.logger.debug(this.getClass(), "Aguardando por eventos de resposta do servidor socket:");
        while ((retorno = recebidos.readLine()) != null) {
            retornoCompleto += retorno + ":";
            mensagemDebug += retorno + "\n";
            if (!recebidos.ready()) {
                break;
            }
        }
        this.logger.debug(this.getClass(), "Mensagem recebida do servidor socket: \n" + mensagemDebug);

        return retornoCompleto;
    }

    /**
     * Nome: aguardarEvento
     * Aguardar evento.
     *
     * @return list
     * @throws IOException the IO exception
     * @see
     */
    public List<Event> aguardarEvento() throws IOException {

        List<Event> listaEventos = new ArrayList<Event>();
        BufferedReader recebidos = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String retorno = "";
        String retornoCompleto = "";
        this.logger.debug(this.getClass(), "Aguardando por eventos de resposta do servidor socket:");
        while ((retorno = recebidos.readLine()) != null) {
            retornoCompleto += retorno + ":";
            if (!recebidos.ready()) {
                retornoCompleto = retornoCompleto.replaceAll("::", "|");
                break;
            }
        }
        //Converte em objeto

        StringTokenizer tokenLinha = new StringTokenizer(retornoCompleto, "|");
        while (tokenLinha.hasMoreElements()) {
            Event evento = this.parse2Event(tokenLinha.nextElement().toString().trim());
            listaEventos.add(evento);
        }

        this.logger.debug(this.getClass(), "Mensagem recebida do servidor socket: \n" + retornoCompleto);
        return listaEventos;
    }

    /**
     * Nome: parse2Event Parse2 event.
     * @param str the str
     * @return event
     * @see
     */
    public Event parse2Event(String str) {
        StringTokenizer st = new StringTokenizer(str, ":");
        int i = 1;
        Event evento = new Event();
        while (st.hasMoreElements()) {
            String name = null;
            String value = null;
            name = st.nextElement().toString().trim().toLowerCase();
            value = st.nextElement().toString().trim();
            try {
                Class<?> type = PropertyUtils.getPropertyType(evento, name);
                if (type.getName().equalsIgnoreCase("java.lang.Integer")) {
                    PropertyUtils.setProperty(evento, name, Integer.parseInt(value));
                } else {
                    PropertyUtils.setProperty(evento, name, value);
                }

            } catch (Exception e) {
                evento.setEvent("Desconhecido");
                evento.setMessage(e.getMessage());
                this.logger.debug(this.getClass(), "Não foi possivel fazer parser da mensagem recebida pelo socket, para um objeto. \n " + str);
            }
            i++;
        }

        return evento;
    }


    /**
     * Nome: escutar
     * Escutar.
     *
     * @return event
     * @throws SocketConnectionException the socket connection exception
     * @see
     */
    public Event escutar() throws SocketConnectionException {
        this.logger.debug(this.getClass(), "Iniciando escuta. Aguardando resposta do server socket");
        String responseComplete = "";
        try {

            final InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            final BufferedReader br = new BufferedReader(isr);

            while (true) {
                String response = br.readLine();
                responseComplete += response + ":";
                if (!br.ready()) {
                    break;
                }
            }
        } catch (Exception e) {
            throw new SocketConnectionException(e);
        }
        Event retorno = parse2Event(responseComplete);

        this.logger.debug(this.getClass(), "Finalizada escuta. Entregando mensagem " + responseComplete);

        return retorno;
    }

    /**
     * Nome: fecharConexaoSocket
     * Fechar conexao socket.
     *
     * @param usuarioRamal the usuario ramal
     * @see
     */
    public void fecharConexaoSocket(Integer usuarioRamal) {
        this.atendenteAutenticado = false;
        this.ramalAtivo = false;
        this.emAtendimento = false;
        this.qtdeLigacoesFilaAtendimentoEmergencia = 0;
        this.alertaChamada = null;
        this.atendenteDisponivel = true;

        if (null != usuarioRamal) {
            try {
                this.logger.debug(this.getClass(), "Iniciando encerramento da conexão com o socket.");
                try {
                    hangupAll(usuarioRamal);
                    this.enviarMensagem(PhoneCommand.logoff(usuarioRamal));
                } catch (Exception e) {
                    this.logger.error(this.getClass(), e);
                }
                this.socket.close();
                this.logger.debug(this.getClass(), "Conexão com o socket encerrada");
            } catch (Exception e) {
                this.logger.error(this.getClass(), "Não foi possível fechar a conexão com o socket");
            }
        }
    }

    /**
     * Nome: login
     * Efetua a ativação do usuario. O Usuário é o ramal onde serão feitas e atendidas uma chamada
     *
     * @param usuario a ser ativado.
     * @throws SocketException the socket exception
     * @see
     */
    public void login(Integer usuario) throws SocketException {

        this.userRamal = usuario;
        //Zera o contador de ligações em espera na fila de emergência.
        this.qtdeLigacoesFilaAtendimentoEmergencia = 0;
        try {
            this.logger.debug("Iniciando login do usuario/ramal " + usuario);
            this.enviarMensagem(PhoneCommand.login(usuario));

            boolean inLoop = true;
            int timeOut = 0;
            while (inLoop) {
                List<Event> eventos = this.aguardarEvento();
                this.abandonoNaFila = false;
                for (Event evento : eventos) {

                    if (evento.getEvent().equals("DGTimeStamp")) {
                        timeOut++;
                        if (timeOut == DEFAULT_TIMEOUT) {
                            this.ramalAtivo = false;
                            this.atendenteAutenticado = false;
                            throw new SocketException("Esgotado tempo limite (timeOut)");
                        }
                    }

                    tratarEventoSocket(evento);
                }

                if (this.ramalAtivo) {
                    inLoop = false;
                }
            }

            //Bloco para totalizar as ligações em espera na fila de atendimento de emerência
            inLoop = true;
            this.logger.debug(this.getClass(), "Calculando se há ligações em espera na fila de emergência");
            this.enviarMensagem(PhoneCommand.queueStatus(usuario, TipoOcorrencia.Emergencia.getValue()));
            while (inLoop) {
                List<Event> eventos = this.aguardarEvento();
                for (Event evento : eventos) {
                    if (evento.getEvent().equals("DGTimeStamp")) {
                        timeOut++;
                        if (timeOut == DEFAULT_TIMEOUT) {
                            inLoop = false;
                        }
                    } else if (evento.getStatus().equals("QueueStatusComplete")) {
                        inLoop = false;
                    } else {
                        tratarEventoSocket(evento);
                    }
                }
            }

        } catch (SocketException e) {
            this.logger.debug(this.getClass(), "Falha no login de " + usuario);
            throw new SocketException("Não foi possível efetuar o login de :" + usuario + " - " + e.getMessage());
        } catch (Exception e) {
            this.logger.debug(this.getClass(), "Falha no login de " + usuario);
            throw new SocketException(e);
        }
    }

    /**
     * Nome: loginAgente
     * Login agente.
     *
     * @param codigoAgente the codigo agente
     * @param senhaAgente the senha agente
     * @throws SocketException the socket exception
     * @see
     */
    public void loginAgente(Integer codigoAgente, String senhaAgente) throws SocketException {

        this.codigoAgente = codigoAgente;
        this.senhaAgente = senhaAgente;
        Integer linhaDeLogin = 1;

        if (this.atendenteAutenticado) {
            //Indica que há uma sessão não encerrada. Forçar encerramento
            this.enviarMensagem(PhoneCommand.desligar(this.userRamal, linhaDeLogin));
            this.atendenteAutenticado = false;
        }
        this.discar(this.numeroDiscagemLoginAgente, linhaDeLogin);

        try {
            boolean inLoop = true;
            int timeOut = 0;
            while (inLoop) {

                List<Event> eventos = this.aguardarEvento();
                for (Event evento : eventos) {

                    if (evento.getEvent().equals("DGTimeStamp")) {
                        timeOut++;
                        if (timeOut == DEFAULT_TIMEOUT) {
                            inLoop = false;
                            this.ramalAtivo = false;
                            this.atendenteAutenticado = false;
                            throw new SocketException("Esgotado tempo limite (timeOut) para autenticação do agente.");
                        }
                    }

                    tratarEventoSocket(evento);
                    if (this.atendenteAutenticado) {
                        this.logger.debug(this.getClass(), "O Agente está logado. ***********");
                        inLoop = false;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }

    /**
     * Nome: colocarRemoverChamadaEmEspera
     * Coloca ou remove uma chamada em espera. A ação é sempre o oposto da situação atual da linha,
     * ou seja se a linah estiver em espera a linha é retirada de espera.
     *
     * @param linha a ser ser colocada ou retirada de espera.
     * @see
     */
    public void colocarRemoverChamadaEmEspera(Integer linha) {
        this.enviarMensagem(PhoneCommand.hold(this.userRamal, linha));
    }

    /**
     * Nome: encerrarChamadaParaAgente
     * Encerrar uma chamada recebida pelo agente.
     *
     * @param linha linha onde está a ligação a ser encerrada
     * @see
     */
    public void encerrarChamadaParaAgente(Integer linha) {
        this.enviarMensagem(PhoneCommand.selecionarLinha(this.userRamal, linha));
        this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "*"));

        Line line = (Line) CollectionUtils.findByAttribute(this.linhas, "numeroLinha", linha);
        line.setStatusLinha(StatusLigacao.PAUSA.getValue());
        line.setAcionamento(null);
        line.setSubNumeroDiscado(null);
    }

    /**
     * Nome: encerrarChamada
     * Encerrar uma chamada.
     *
     * @param linha que contem a ligação a ser encerrada.
     * @see
     */
    public void encerrarChamada(Integer linha) {
        this.enviarMensagem(PhoneCommand.selecionarLinha(this.userRamal, linha));
        this.enviarMensagem(PhoneCommand.desligar(this.userRamal, linha));

        Line line = (Line) CollectionUtils.findByAttribute(this.linhas, "numeroLinha", linha);
        line.setStatusLinha(StatusLigacao.LIVRE.getValue());
        line.setNumeroDiscado(null);
        line.setTipoLigacao(TipoLigacao.INDEFINIDO.getValue());
        line.setAcionamento(null);
    }

    /**
     * Nome: selecionarLinha
     * Selecionar uma linha.
     *
     * @param linha the linha
     * @see
     */
    public void selecionarLinha(Integer linha) {
        this.enviarMensagem(PhoneCommand.selecionarLinha(this.userRamal, 1));
    }

    /**
     * Nome: discar
     * Efetua a iscagem para o número informado atraves da linha especificada.
     *
     * @param numero the numero
     * @param linha the linha
     * @see
     */
    public void discar(String numero, Integer linha) {
        this.enviarMensagem(PhoneCommand.selecionarLinha(this.userRamal, linha));
        this.enviarMensagem(PhoneCommand.discar(numero, this.userRamal, linha));
    }

    /**
     * Nome: tratarEventoSocket
     * Tratar evento socket.
     *
     * @param evento the evento
     * @throws SocketException the socket command exception
     * @see
     */
    public void tratarEventoSocket(Event evento) throws SocketException {

        Line line = null;
        if (null != evento.getLine()) {
            line = (Line) CollectionUtils.findByAttribute(this.linhas, "numeroLinha", evento.getLine());
        }

        if (evento.getResponse() != null && evento.getResponse().equals("Success")
            && evento.getUser().equals(this.userRamal) && evento.getCommand().equals("Login")) {

            this.setRamalAtivo(true);
            this.logger.debug(this.getClass(), "O Ramal " + this.userRamal + " foi logado com sucesso");

        } else if (null != evento.getStatus()) {

            tratarEventosFila(evento);

            tratarEventosAgente(evento);

            if (evento.getStatus().equals("Hold")) {
                if (evento.getHold().intValue() == PausaLigacao.HOLD_ON.getValue().intValue()) {
                    line.setStatusLinha(StatusLigacao.PAUSA.getValue());
                    this.logger.debug("Linha " + evento.getLine() + " entrou em pausa");
                } else if (evento.getHold().intValue() == PausaLigacao.HOLD_OFF.getValue().intValue()) {
                    line.setStatusLinha(StatusLigacao.FALANDO.getValue());
                    this.logger.debug("Linha " + evento.getLine() + " esta ativa");
                }

            } else if (evento.getStatus().equals("Dialing")) {
                if (evento.getNumber().equals(this.numeroDiscagemLoginAgente)) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, this.codigoAgente + "#"));
                }

                line.setNumeroDiscado(evento.getNumber());

            } else if (evento.getStatus().equals("Answer")) {
                if (evento.getNumber().equals(this.numeroDiscagemLoginAgente)) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, this.senhaAgente + "#"));
                }
                line.setStatusLinha(StatusLigacao.FALANDO.getValue());

            } else if (evento.getStatus().equals("Ready")) {
                line.setStatusLinha(StatusLigacao.LIVRE.getValue());
                line.setNumeroDiscado(null);

            } else if ((evento.getStatus() != null && evento.getStatus().equals("Error"))
                || (evento.getResponse() != null &&  evento.getResponse().equals("Error"))) {
                throw new SocketException(evento.getMessage());

            }
        }
    }

    /**
     * Nome: tratarEventosFila
     * Tratar eventos fila.
     *
     * @param evento the evento
     * @see
     */
    private void tratarEventosFila(Event evento) {

        if (null != evento.getQueue() && evento.getQueue().intValue() == 1) {

            if (evento.getStatus().equalsIgnoreCase("QueueEntry")) {
                this.qtdeLigacoesFilaAtendimentoEmergencia++;

            } else if (evento.getStatus().equalsIgnoreCase("QueueJoin")) {
                this.avisoLigacaoEmergencia = true;
                this.qtdeLigacoesFilaAtendimentoEmergencia++;

            } else if (evento.getStatus().equalsIgnoreCase("QueueLeave")) {
                this.qtdeLigacoesFilaAtendimentoEmergencia--;

            }
        }

        if (evento.getStatus().equals("QueueAbandon") && evento.getUser().intValue() == this.userRamal) {
            Line linhaCliente = (Line) CollectionUtils.findByAttribute(this.getLinhas(), "numeroLinha", 1);
            linhaCliente.setSubNumeroDiscado("");
            this.abandonoNaFila = true;

        }
    }

    /**
     * Nome: tratarEventosAgente
     * Tratar eventos agente.
     *
     * @param evento the evento
     * @see
     */
    private void tratarEventosAgente(Event evento) {
        if (evento.getStatus().equalsIgnoreCase("AgentConnect")
            && evento.getUser().intValue() == this.userRamal) {
            
            this.telefoniaDAO.atualizarDataHoraAtendimento(evento.getUniqueid(), new Date());
            this.atendenteDisponivel = false;
            this.agenteAtendeuLigacao = true;
            this.emAtendimento = true;
            this.agenteSendoChamado = false;
            
        } else if (evento.getStatus().equals("AgentNoAnswer") && evento.getUser().intValue() == this.userRamal) {
            this.emAtendimento = false;
            this.agenteNaoAtende = true;
            this.agenteSendoChamado = false;

        } else if (evento.getStatus().equals("AgentLogin")) {
            this.atendenteAutenticado = true;
            this.atendenteDisponivel = true;

        } else if (evento.getStatus().equals("AgentPaused") && evento.getAgent().intValue() == this.codigoAgente) {
            if (evento.getPaused().intValue() == 0) {
                this.atendenteDisponivel = true;
            } else {
                this.atendenteDisponivel = false;
            }
        }  else if (evento.getStatus().equalsIgnoreCase("AgentCalled")) { 
            //Obtem dados da liga��o no intelix
            this.chamadaParaOAgente = this.obterDadosNovaLigacaoAtendente(evento.getUniqueid());
            this.agenteSendoChamado = true;
        
        } else if (evento.getStatus().equals("AgentComplete") && evento.getAgent().intValue() == this.codigoAgente) { 
            this.logger.debug(" *************** ATUALIZAR ***************************************");
            try {
                this.ocorrenciaDAO.atualizarDataHoraFimChamada(evento.getUniqueid(), new Date());
            } catch (Exception e) {
                this.logger.error("N�o � poss�vel atualizar a data hora de fim da chamada para o agente");
            }
        }
    }

    /**
     * Nome: obterDadosNovaLigacaoAtendente
     * Obter dados nova ligacao atendente.
     *
     * @param idUniqueid the id uniqueid
     * @return list
     * @throws SocketException the business exception
     * @see
     */
    public LigacaoVO obterDadosNovaLigacaoAtendente(String idUniqueid) throws SocketException {

        try {
            Ligacao entity = this.telefoniaDAO.obterDadosNovaLigacaoAtendente(idUniqueid);
            
            LigacaoVO ligacaoVO = null;
            if (null != entity) {
                ligacaoVO = new LigacaoVO();
                ligacaoVO.setIdUniqueid(entity.getIdUniqueid());
                ligacaoVO.setNumeroTelefoneOrigem(entity.getNumTelefone());
                ligacaoVO.setCodigoEnviadoPulseira(entity.getCodPulseira());
                ligacaoVO.setDataHoraLigacao(entity.getDtHrLigacao());
                ligacaoVO.setDataHorarAtendimento(entity.getDtHrLigacao());
                FiltroPesquisarPreAtendimento filtro = new FiltroPesquisarPreAtendimento();
                filtro.setTelefone(ligacaoVO.getNumeroTelefoneOrigem());
            }
            return ligacaoVO;

        } catch (Exception e) {
            throw new BusinessException("N�o � poss�vel oter os dados da liga��o na base de dados do intelix");
        }
    }

    /**
     * Nome: atenderLigacaoParaAgente
     * Atender ligacao para agente.
     *
     * @param ramal the ramal
     * @throws SocketException the socket command exception
     * @see
     */
    public void atenderLigacaoParaAgente(Integer ramal) throws SocketException {
        try {
            this.enviarMensagem(PhoneCommand.selecionarLinha(ramal, 1));
            this.enviarMensagem(PhoneCommand.enviarDtmf(ramal, "#"));
        } catch (Exception e) {
            throw new SocketException(e);
        }
    }

    /**
     * Nome: hangupAll
     * Hangup all.
     *
     * @param usuarioRamal the usuario ramal
     * @see
     */
    public void hangupAll(Integer usuarioRamal) {
        this.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 1));
        this.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 2));
        this.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 3));
        this.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 4));
        this.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 5));
        this.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 6));
    }

    /**
     * Nome: getSocket Recupera o valor do atributo 'socket'.
     * @return valor do atributo 'socket'
     * @see
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Nome: setSocket Registra o valor do atributo 'socket'.
     * @param socket valor do atributo socket
     * @see
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Nome: isRamalAtivo
     * Verifica se e ramal ativo.
     *
     * @return true, se for ramal ativo senão retorna false
     * @see
     */
    public boolean isRamalAtivo() {
        return ramalAtivo;
    }

    /**
     * Nome: setRamalAtivo
     * Registra o valor do atributo 'ramalAtivo'.
     *
     * @param ramalAtivo valor do atributo ramal ativo
     * @see
     */
    public void setRamalAtivo(boolean ramalAtivo) {
        this.ramalAtivo = ramalAtivo;
    }

    /**
     * Nome: isAtendenteAutenticado
     * Verifica se e atendente autenticado.
     *
     * @return true, se for atendente autenticado senão retorna false
     * @see
     */
    public boolean isAtendenteAutenticado() {
        return atendenteAutenticado;
    }

    /**
     * Nome: setAtendenteAutenticado
     * Registra o valor do atributo 'atendenteAutenticado'.
     *
     * @param atendenteAutenticado valor do atributo atendente autenticado
     * @see
     */
    public void setAtendenteAutenticado(boolean atendenteAutenticado) {
        this.atendenteAutenticado = atendenteAutenticado;
    }

    /**
     * Nome: isEmAtendimento
     * Verifica se e em atendimento.
     *
     * @return true, se for em atendimento senão retorna false
     * @see
     */
    public boolean isEmAtendimento() {
        return emAtendimento;
    }

    /**
     * Nome: setEmAtendimento
     * Registra o valor do atributo 'emAtendimento'.
     *
     * @param emAtendimento valor do atributo em atendimento
     * @see
     */
    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    /**
     * Nome: getLinhas
     * Recupera o valor do atributo 'linhas'.
     *
     * @return valor do atributo 'linhas'
     * @see
     */
    public List<Line> getLinhas() {
        return linhas;
    }

    /**
     * Nome: setLinhas
     * Registra o valor do atributo 'linhas'.
     *
     * @param linhas valor do atributo linhas
     * @see
     */
    public void setLinhas(List<Line> linhas) {
        this.linhas = linhas;
    }

    /**
     * Nome: getAlertaChamada
     * Recupera o valor do atributo 'alertaChamada'.
     *
     * @return valor do atributo 'alertaChamada'
     * @see
     */
    public String getAlertaChamada() {
        return alertaChamada;
    }

    /**
     * Nome: setAlertaChamada
     * Registra o valor do atributo 'alertaChamada'.
     *
     * @param alertaChamada valor do atributo alerta chamada
     * @see
     */
    public void setAlertaChamada(String alertaChamada) {
        this.alertaChamada = alertaChamada;
    }

    /**
     * Nome: getQtdeLigacoesFilaAtendimento
     * Recupera o valor do atributo 'qtdeLigacoesFilaAtendimento'.
     *
     * @return valor do atributo 'qtdeLigacoesFilaAtendimento'
     * @see
     */
    public Integer getQtdeLigacoesFilaAtendimentoEmergencia() {
        return qtdeLigacoesFilaAtendimentoEmergencia;
    }

    /**
     * Nome: setQtdeLigacoesFilaAtendimento
     * Registra o valor do atributo 'qtdeLigacoesFilaAtendimento'.
     *
     * @param qtdeLigacoesFilaAtendimento valor do atributo qtde ligacoes fila atendimento
     * @see
     */
    public void setQtdeLigacoesFilaAtendimentoEmergencia(Integer qtdeLigacoesFilaAtendimento) {
        this.qtdeLigacoesFilaAtendimentoEmergencia = qtdeLigacoesFilaAtendimento;
    }

    /**
     * Nome: isAvisoLigacaoEmergencia
     * Verifica se e aviso ligacao emergencia.
     *
     * @return true, se for aviso ligacao emergencia senão retorna false
     * @see
     */
    public boolean isAvisoLigacaoEmergencia() {
        return avisoLigacaoEmergencia;
    }

    /**
     * Nome: setAvisoLigacaoEmergencia
     * Registra o valor do atributo 'avisoLigacaoEmergencia'.
     *
     * @param avisoLigacaoEmergencia valor do atributo aviso ligacao emergencia
     * @see
     */
    public void setAvisoLigacaoEmergencia(boolean avisoLigacaoEmergencia) {
        this.avisoLigacaoEmergencia = avisoLigacaoEmergencia;
    }

    /**
     * Nome: isAtendenteDisponivel
     * Verifica se e atendente disponivel.
     *
     * @return true, se for atendente disponivel senão retorna false
     * @see
     */
    public boolean isAtendenteDisponivel() {
        return atendenteDisponivel;
    }

    /**
     * Nome: setAtendenteDisponivel
     * Registra o valor do atributo 'atendenteDisponivel'.
     *
     * @param atendenteDisponivel valor do atributo atendente disponivel
     * @see
     */
    public void setAtendenteDisponivel(boolean atendenteDisponivel) {
        this.atendenteDisponivel = atendenteDisponivel;
    }

    /**
     * Nome: isLigacaoNaoAtendida
     * Verifica se e ligacao nao atendida.
     *
     * @return true, se for ligacao nao atendida senão retorna false
     * @see
     */
    public boolean isAbandonoNaFila() {
        return abandonoNaFila;
    }

    /**
     * Nome: setLigacaoNaoAtendida
     * Registra o valor do atributo 'ligacaoNaoAtendida'.
     *
     * @param abandonoNaFila valor do atributo ligacao nao atendida
     * @see
     */
    public void setAbandonoNaFila(boolean abandonoNaFila) {
        this.abandonoNaFila = abandonoNaFila;
    }

    /**
     * Nome: isAgenteNaoAtende
     * Verifica se e agente nao atende.
     *
     * @return true, se for agente nao atende senão retorna false
     * @see
     */
    public boolean isAgenteNaoAtende() {
        return agenteNaoAtende;
    }

    /**
     * Nome: setAgenteNaoAtende
     * Registra o valor do atributo 'agenteNaoAtende'.
     *
     * @param agenteNaoAtende valor do atributo agente nao atende
     * @see
     */
    public void setAgenteNaoAtende(boolean agenteNaoAtende) {
        this.agenteNaoAtende = agenteNaoAtende;
    }

    /**
     * Nome: isAgenteAtendeuLigacao
     * Verifica se e agente atendeu ligacao.
     *
     * @return true, se for agente atendeu ligacao senão retorna false
     * @see
     */
    public boolean isAgenteAtendeuLigacao() {
        return agenteAtendeuLigacao;
    }

    /**
     * Nome: setAgenteAtendeuLigacao
     * Registra o valor do atributo 'agenteAtendeuLigacao'.
     *
     * @param agenteAtendeuLigacao valor do atributo agente atendeu ligacao
     * @see
     */
    public void setAgenteAtendeuLigacao(boolean agenteAtendeuLigacao) {
        this.agenteAtendeuLigacao = agenteAtendeuLigacao;
    }

    /**
     * Nome: getChamadaParaOAgente
     * Recupera o valor do atributo 'chamadaParaOAgente'.
     *
     * @return valor do atributo 'chamadaParaOAgente'
     * @see
     */
    public LigacaoVO getChamadaParaOAgente() {
        return chamadaParaOAgente;
    }

    /**
     * Nome: setChamadaParaOAgente
     * Registra o valor do atributo 'chamadaParaOAgente'.
     *
     * @param chamadaParaOAgente valor do atributo chamada para o agente
     * @see
     */
    public void setChamadaParaOAgente(LigacaoVO chamadaParaOAgente) {
        this.chamadaParaOAgente = chamadaParaOAgente;
    }
   
    /**
     * Nome: isAgenteSendoChamado
     * Verifica se e agente sendo chamado.
     *
     * @return true, se for agente sendo chamado sen�o retorna false
     * @see
     */
    public boolean isAgenteSendoChamado() {
        return agenteSendoChamado;
    }

    /**
     * Nome: setAgenteSendoChamado
     * Registra o valor do atributo 'agenteSendoChamado'.
     *
     * @param agenteSendoChamado valor do atributo agente sendo chamado
     * @see
     */
    public void setAgenteSendoChamado(boolean agenteSendoChamado) {
        this.agenteSendoChamado = agenteSendoChamado;
    }
    
}
