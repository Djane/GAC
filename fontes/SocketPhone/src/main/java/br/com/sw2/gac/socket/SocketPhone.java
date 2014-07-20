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

import br.com.sw2.gac.business.ParametroBusiness;
import br.com.sw2.gac.dao.OcorrenciaDAO;
import br.com.sw2.gac.dao.TelefoniaDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.modelo.Ligacao;
import br.com.sw2.gac.modelo.MotivoPausa;
import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.PausaLigacao;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.socket.constants.TipoLigacao;
import br.com.sw2.gac.socket.exception.SocketConnectionException;
import br.com.sw2.gac.socket.exception.SocketException;
import br.com.sw2.gac.socket.exception.SocketLoginException;
import br.com.sw2.gac.socket.exception.SocketLoginException.ExceptionCode;
import br.com.sw2.gac.tools.SinalDispositivo;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.vo.LigacaoVO;
import br.com.sw2.gac.vo.MotivoPausaVO;
import br.com.sw2.gac.vo.ParametroVO;

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

    private boolean keepAliveDaCentral = false;
    
    private boolean eventoFaltaEnergia = false;
    
    private boolean eventoRetornoEnergia = false;
    
    private boolean eventoCaboTelefoneConectado = false;
    
    private boolean eventoCaboTelefoneDesconectado = false;
        
    /** Atributo ramal ativo. */
    private boolean ramalAtivo = false;

    /** Indica se o agente/atendente está¡ autenticado. */
    private boolean atendenteAutenticado = false;

    /** Indica se o atendente está¡ disponível para receber ligações. */
    private boolean atendenteDisponivel = false;

    /** Indica se houve ou nÃƒÂ£o um abandona na na fila de atendimento. */
    private boolean abandonoNaFila = false;;

    /** Indica que uma ligação passada aoa tendente/agente nÃƒÂ£o foi atendida. */
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

    /**Indica quantas ligação há na fila de emergência. */
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
    
    /** Atributo lista motivos pausa. */
    private List<MotivoPausaVO> listaMotivosPausa = null;
    
    private ParametroBusiness parametroBusiness = new ParametroBusiness();
    
    private Integer configurarDispositivoRecuperado = null;
    
    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketPhone.
     */
    public SocketPhone() {
        
        try {
            this.logger.debug("##### Conectando ao banco de dados do intelix");
            this.telefoniaDAO = new TelefoniaDAO();
        } catch (Exception e) {     
            this.telefoniaDAO  = null;
            this.logger.error(e);
            this.logger.error("##### Não foi possível conectar ao banco de dados do Intelix");
        }    
        try {
            this.ocorrenciaDAO = new OcorrenciaDAO();
        } catch (Exception e) {
            this.logger.error(e);
            this.logger.error("Não foi possível iniciar o banco de dados GAC. ");
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
                this.logger.debug(this.getClass(), "##### Conectado ao host " + host + ":" + port);
                this.logger.debug(retorno);
            } catch (IOException e) {
                e.printStackTrace();
            }            
        } catch (ConnectException e) {
            this.logger.error(e);
            throw new SocketException("Não foi possível conectar ao servidor socket: " + host + ":" + port);
        } catch (Exception e) {
            this.logger.error(e);
            throw new SocketException(e);
        }
    }

    /**
     * Nome: sendMessage Send message.
     * @param str the str
     * @see
     */
    public void enviarMensagem(String str) {
        this.logger.debug(this.getClass(), "##### Enviando mensagem para o servidor socket: \n" + str);
        if (null == this.socket) {
            this.logger.registrarAcao("Não há uma conexão estabelecida com o servidor socket. Soket is null");            
        } else {
            try {
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                write.write(str);
                write.flush();            
                this.logger.registrarAcao("Mensagem enviada ao socket: " + str.replace("\r\n", " "));
                
            } catch (Exception e) {
                this.logger.error(e);
                this.logger.error("***** Não foi possível enviar a mensagem  ao servidor socket.\n" + e.getMessage());
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
            this.logger.error(e);
            throw new SocketException("Erro de IO ao obter socket.getInputStream()");
        } catch (SocketException e) {
            this.logger.error(e);
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
            throw new SocketException("O Servidor não está respondendo.");
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
        this.logger.debug(this.getClass(), "##### Aguardando por eventos de resposta do servidor socket:");
        while ((retorno = recebidos.readLine()) != null) {
            retornoCompleto += retorno + ":";
            mensagemDebug += retorno + "\n";
            if (!recebidos.ready()) {
                break;
            }
        }
        this.logger.debug(this.getClass(), "##### Mensagem recebida do servidor socket: \n" + mensagemDebug);

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
        this.logger.debug(this.getClass(), "##### Aguardando por eventos de resposta do servidor socket:");
        while ((retorno = recebidos.readLine()) != null) {
            retornoCompleto += retorno + ":";
            if (!recebidos.ready()) {
                retornoCompleto = retornoCompleto.replaceAll("::", "|");
                break;
            }
        }
        //Converte em objeto
        
        this.logger.registrarAcao("Mensagem recebida do socket: " + retornoCompleto);

        StringTokenizer tokenLinha = new StringTokenizer(retornoCompleto, "|");
        while (tokenLinha.hasMoreElements()) {
            Event evento = this.parse2Event(tokenLinha.nextElement().toString().trim());
            listaEventos.add(evento);
        }

        this.logger.debug(this.getClass(), "##### Mensagem recebida do servidor socket: \n" + retornoCompleto);
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
                this.logger.error(this.getClass(), "Não foi possível fazer parser da mensagem recebida pelo socket, para um objeto. \n " + str);
            }        
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
        this.logger.debug(this.getClass(), "##### Iniciando escuta. Aguardando resposta do server socket");
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
            this.logger.error(e);
            throw new SocketConnectionException(e);
        }
        Event retorno = parse2Event(responseComplete);

        this.logger.debug(this.getClass(), "##### Finalizada escuta. Entregando mensagem " + responseComplete);

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
                this.logger.debug(this.getClass(), "##### Iniciando encerramento da Conexão com o socket.");
                try {
                    hangupAll(usuarioRamal);
                    this.enviarMensagem(PhoneCommand.logoff(usuarioRamal));
                } catch (Exception e) {
                    this.logger.error(this.getClass(), e);
                }
                this.socket.close();
                this.logger.debug(this.getClass(), "##### Conexão com o socket encerrada");
            } catch (Exception e) {
                this.logger.error(this.getClass(), "##### Não foi possível fechar a Conexão com o socket");
            }
        }
    }

    /**
     * Nome: login
     * Efetua a ativação do usuario. O usuário é o ramal onde serão feitas e atendidas uma chamada
     *
     * @param usuario a ser ativado.
     * @throws SocketLoginException the socket login exception
     * @see
     */
    public void login(Integer usuario) throws SocketLoginException {

        if (null == usuario) {
            throw new SocketLoginException(SocketLoginException.ExceptionCode.USER_IS_NULL);            
        }        
        
        this.userRamal = usuario;
        //Zera o contador de ligaÃƒÂ§ÃƒÂµes em espera na fila de emergÃƒÂªncia.
        this.qtdeLigacoesFilaAtendimentoEmergencia = 0;
        try {
            this.logger.debug("##### Iniciando login do usuario/ramal " + usuario);
            this.enviarMensagem(PhoneCommand.login(usuario));

            boolean inLoop = true;
            int timeOut = 0;
            int count = 0;
            while (inLoop) {      
                
                List<Event> eventos = this.aguardarEvento();
                this.abandonoNaFila = false;
                for (Event evento : eventos) {

                    if (null != evento.getEvent() && evento.getEvent().equals("DGTimeStamp")) {
                        timeOut++;
                        if (timeOut == DEFAULT_TIMEOUT) {
                            this.ramalAtivo = false;
                            this.atendenteAutenticado = false;
                            inLoop = false;
                            throw new SocketLoginException(SocketLoginException.ExceptionCode.USER_TIMEOUT);
                        }
                    }

                    tratarEventoSocket(evento);
                }

                if (this.ramalAtivo || count == 10) {
                    inLoop = false;
                }
                count++;
            }
            
            //Bloco para totalizar as ligaÃƒÂ§ÃƒÂµes em espera na fila de atendimento de emerÃƒÂªncia
            inLoop = true;
            count = 0;
            this.logger.debug(this.getClass(), "##### Calculando se há ligações na fila de emergência");
            this.enviarMensagem(PhoneCommand.queueStatus(usuario, TipoOcorrencia.Emergencia.getValue()));
            
            while (inLoop) {            
                List<Event> eventos = this.aguardarEvento();
                for (Event evento : eventos) {
                    if (null != evento.getEvent() && evento.getEvent().equals("DGTimeStamp")) {
                        timeOut++;
                        if (timeOut == DEFAULT_TIMEOUT) {
                            inLoop = false;
                        }
                    } else if (null != evento.getStatus() && evento.getStatus().equals("QueueStatusComplete")) {
                        inLoop = false;
                    } else {
                        tratarEventoSocket(evento);
                    }
                }
                if (count == 30) {
                    inLoop = false;
                }                
                count++;
            }
            
        } catch (SocketLoginException e) {
            throw e;
        } catch (Exception e) {
            throw new SocketLoginException(ExceptionCode.UNDEFINED, e);
        }
    }

    /**
     * Nome: loginAgente
     * Login agente.
     *
     * @param codigoAgente the codigo agente
     * @param senhaAgente the senha agente
     * @throws SocketLoginException the socket login exception
     * @see
     */
    public void loginAgente(Integer codigoAgente, String senhaAgente) throws SocketLoginException {
        this.logger.debug("##### Iniciando login atendente " + codigoAgente);

        this.codigoAgente = codigoAgente;
        this.senhaAgente = senhaAgente;
        Integer linhaDeLogin = 1;

        if (null == codigoAgente) {
            throw new SocketLoginException(SocketLoginException.ExceptionCode.AGENT_IS_NULL);
        }
                
        if (null != this.telefoniaDAO) {
            try {
                this.logger.debug("##### Obtendo a lista de pausas no banco de dados do intelix.");
                this.listaMotivosPausa = this.obterListaMotivosPausa();
            } catch (Exception e) {
                throw new SocketLoginException(SocketLoginException.ExceptionCode.SEM_LISTA_MOTIVOS_PAUSA, e);
            }
        } else {
            this.logger.debug("##### O DAO do Intelix não foi iniciado. Não foi possível obter lsita de motivos de pausa.");
        }
        
        if (this.atendenteAutenticado) {
            //Indica que há uma sessão não encerrada. Forçar encerramento
            this.logger.debug("##### Atendente  " + codigoAgente + " já está autenticado. Forçando desconexão.");
            
            this.enviarMensagem(PhoneCommand.desligar(this.userRamal, linhaDeLogin));
            this.atendenteAutenticado = false;
        }
        
        this.logger.debug("##### Discando para o número de login :" + this.numeroDiscagemLoginAgente + " na linha " + linhaDeLogin);
        this.discar(this.numeroDiscagemLoginAgente, linhaDeLogin);

        try {
            boolean inLoop = true;
            int timeOut = 0;
            int count = 0;
            while (inLoop) {

                List<Event> eventos = this.aguardarEvento();
                for (Event evento : eventos) {

                    tratarEventoSocket(evento);
                    
                    if (evento.getEvent().equals("DGTimeStamp")) {
                        timeOut++;
                        if (timeOut == DEFAULT_TIMEOUT) {
                            inLoop = false;
                            this.ramalAtivo = false;
                            this.atendenteAutenticado = false;
                            throw new SocketLoginException(SocketLoginException.ExceptionCode.USER_TIMEOUT);
                        }
                    
                    } else if (evento.getStatus().equals("Hangup")) {
                        inLoop = false;
                        this.ramalAtivo = false;
                        this.atendenteAutenticado = false;
                        throw new SocketLoginException(SocketLoginException.ExceptionCode.HANGUP);                        
                        
                    }
                    
                    if (this.atendenteAutenticado) {                        
                        inLoop = false;
                        break;
                    }
                }
                if (count == 15) {
                    inLoop = false;
                }                
                count++;                
            }
            
        } catch (SocketLoginException e) {
            throw e;
        } catch (Exception e) {
            throw new SocketLoginException(SocketLoginException.ExceptionCode.UNDEFINED, e);
        }
    }

    /**
     * Nome: colocarRemoverChamadaEmEspera
     * Coloca ou remove uma chamada em espera. A operação será sempre o oposto da situação atual da linha,
     * ou seja se a linah estiver em espera a linha ÃƒÂ© retirada de espera.
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
        
        this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A0612D"));
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
     * Efetua a iscagem para o nÃƒÂºmero informado atraves da linha especificada.
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
     * @throws SocketLoginException the socket login exception
     * @throws SocketException the socket command exception
     * @see
     */
    public void tratarEventoSocket(Event evento) throws SocketLoginException, SocketException {

        Line line = null;
        if (null != evento.getLine()) {
            line = (Line) CollectionUtils.findByAttribute(this.linhas, "numeroLinha", evento.getLine());
        }

        if (evento.getResponse() != null && evento.getResponse().equals("Success")
            && null != evento.getUser() && evento.getUser().equals(this.userRamal) && null != evento.getCommand() && evento.getCommand().equals("Login")) {

            this.setRamalAtivo(true);
            this.logger.debug(this.getClass(), "O Ramal " + this.userRamal + " foi logado com sucesso");

        } else if (null != evento.getStatus()) {

            tratarEventosFila(evento);

            tratarEventosAgente(evento);

            if (evento.getStatus().equals("Hold")) {
                if (evento.getHold().intValue() == PausaLigacao.HOLD_ON.getValue().intValue()) {
                    line.setStatusLinha(StatusLigacao.PAUSA.getValue());
                    this.logger.debug("##### A Linha " + evento.getLine() + " entrou em pausa");
                } else if (evento.getHold().intValue() == PausaLigacao.HOLD_OFF.getValue().intValue()) {
                    line.setStatusLinha(StatusLigacao.FALANDO.getValue());
                    this.logger.debug("##### A Linha " + evento.getLine() + " esta ativa");
                }

            } else if (evento.getStatus().equals("Dialing")) {
                this.logger.debug("##### Discando para " + evento.getNumber());
                if (evento.getNumber().equals(this.numeroDiscagemLoginAgente)) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, this.codigoAgente + "#"));
                }

                line.setNumeroDiscado(evento.getNumber());

            } else if (evento.getStatus().equals("Answer")) {
                this.logger.debug("##### Recebida resposta da discagem para  " + evento.getNumber());
                if (evento.getNumber().equals(this.numeroDiscagemLoginAgente)) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, this.senhaAgente + "#"));
                }
                line.setStatusLinha(StatusLigacao.FALANDO.getValue());
                line.setNumeroDiscado(evento.getNumber());
                
            } else if (evento.getStatus().equals("Ready")) {
                this.logger.debug("##### A Linha " + line.getNumeroLinha() + " Está livre");
                
                line.setStatusLinha(StatusLigacao.LIVRE.getValue());
                line.setNumeroDiscado(null);
                line.setSubNumeroDiscado(null);
                line.setTipoLigacao(TipoLigacao.INDEFINIDO.getValue());                

            } else if (evento.getStatus().equals("Hangup")) {
                this.logger.debug("##### A Ligação da linha " + line.getNumeroLinha() + " se encerrou (Hangup)");
                
                line.setStatusLinha(StatusLigacao.LIVRE.getValue());
                line.setNumeroDiscado(null);
                line.setSubNumeroDiscado(null);
                line.setTipoLigacao(TipoLigacao.INDEFINIDO.getValue());                
                
            
            } else if (evento.getStatus().equals("DTMFReceived")) {                
                //Tratar recebimento de DTMF
                if (evento.getDigit() != null && evento.getDigit().startsWith("A00")) {
                    String numeroDispositivo = evento.getDigit().substring(evento.getDigit().length()-3,5);
                    
                    try {
                        this.configurarDispositivoRecuperado = Integer.parseInt(numeroDispositivo);
                    } catch (Exception e) {
                        this.configurarDispositivoRecuperado = -2;
                    }
                    
                    this.enviarMensagem(PhoneCommand.programarTempoComunicacaoDaCentral(this.userRamal, 24));
                    this.enviarMensagem(PhoneCommand.configurarDateHora(this.userRamal, null));
                    this.enviarMensagem(PhoneCommand.configuraTempoDeImobilidade(this.userRamal, "08"));
                    
                  //Parametros globais
                    ParametroVO parametro = this.parametroBusiness.recuperarParametros();
                    if (null != parametro.getTelefoneCentral1()) {
                        this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(this.userRamal, "1", parametro.getTelefoneCentral1()));    
                    }
                    
                    if (null != parametro.getTelefoneCentral2()) {
                        this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(this.userRamal, "2", parametro.getTelefoneCentral2()));    
                    }

                    if (null != parametro.getTelefoneCentral2()) {
                        this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(this.userRamal, "3", parametro.getTelefoneCentral3()));    
                    }
                    
                    if (null != parametro.getTelefoneCentral4()) {
                        this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(this.userRamal, "4", parametro.getTelefoneCentral4()));    
                    }

                    if (null != parametro.getTelefoneCentral5()) {
                        this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(this.userRamal, "5", parametro.getTelefoneCentral5()));    
                    }

                    if (null != parametro.getTelefoneCentral6()) {
                        this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(this.userRamal, "6", parametro.getTelefoneCentral6()));    
                    }                    
                }
                
            } else if ((evento.getStatus() != null && evento.getStatus().equals("Error"))) {
                this.logger.debug("##### Evento de erro recebido :" + evento.getMessage());
                
                throw new SocketException(evento.getMessage());
            }
            
        } else  if (evento.getResponse() != null &&  evento.getResponse().equals("Error")) {            
            if (evento.getMessage().contains("User is not active")) {
                throw new SocketLoginException(SocketLoginException.ExceptionCode.USER_IS_NOT_ACTIVE);    
            } else if (evento.getMessage().contains("Invalid user")) {
                    throw new SocketLoginException(SocketLoginException.ExceptionCode.INVALID_USER);    
            } else if (evento.getMessage().contains("User is not a DGPhone")) {
                throw new SocketLoginException(SocketLoginException.ExceptionCode.USER_IS_NOT_DGPHONE);
            } else if (evento.getMessage().contains("Invalid version of DGPhone")) {
                throw new SocketLoginException(SocketLoginException.ExceptionCode.INVALID_VERSION_OF_DGPHONE);
            } else {
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
            //TRATAMENTO ESPECÍFICO PARA A FILA DE EMERGÊNCIA
            if (evento.getStatus().equalsIgnoreCase("QueueEntry")) {
                this.logger.debug("##### (QueueEntry) Identificado entrada de ligação na fila " + evento.getQueue());
                
                this.qtdeLigacoesFilaAtendimentoEmergencia++;

            } else if (evento.getStatus().equalsIgnoreCase("QueueJoin")) {
                this.logger.debug("##### (QueueJoin) Identificado entrada de ligação na fila " + evento.getQueue());
                
                this.avisoLigacaoEmergencia = true;
                this.qtdeLigacoesFilaAtendimentoEmergencia++;

            } else if (evento.getStatus().equalsIgnoreCase("QueueLeave")) {
                this.logger.debug("##### (QueueLeave) Identificado saída de ligação na fila " + evento.getQueue());
                
                this.qtdeLigacoesFilaAtendimentoEmergencia--;
                if (this.qtdeLigacoesFilaAtendimentoEmergencia < 1) {
                    this.qtdeLigacoesFilaAtendimentoEmergencia = 0; //Caso seja negativo volta para zero.
                    this.avisoLigacaoEmergencia = false;
                } 

            }
        }
        // TRATAMENTO PARA TODAS AS FILAS
        if (evento.getStatus().equals("QueueAbandon") && evento.getUser().intValue() == this.userRamal) {
            this.logger.debug("##### (QueueAbandon) Identificado abandono de ligação na fila ");
            
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
            this.logger.debug("##### O Atendente atendeu uma ligação.");
            
            
            String codigoEvento =  this.chamadaParaOAgente.getCodigoEvento();
            if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.DispositivoNaoProgramado.getValue().toString())) {
                this.enviarMensagem(PhoneCommand.programarDispositivoDeAcionamento(this.userRamal));
            } if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.FaltaDeAlimentacaoEnergia.getValue().toString())) {
                this.setEventoFaltaEnergia(true); // Falta de energia evento 93 - Somente logar
            
            } else if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.CaboTelefoneConectado.getValue().toString())) {
                this.setEventoCaboTelefoneConectado(true); 
            
            } else if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.CaboTelefoneDesconectado.getValue().toString())) {
                this.setEventoCaboTelefoneDesconectado(true);
            }            
                        
            TelefoniaDAO dao = new TelefoniaDAO();
            dao.atualizarDataHoraAtendimento(evento.getUniqueid(), new Date());
            this.atendenteDisponivel = false;
            this.agenteAtendeuLigacao = true;
            this.emAtendimento = true;
            this.agenteSendoChamado = false;
            
        } else if (evento.getStatus().equals("AgentNoAnswer") && evento.getUser().intValue() == this.userRamal) {
            this.logger.debug("##### O Atendente não atendeu a ligação.");
            
            this.emAtendimento = false;
            this.agenteNaoAtende = true;
            this.agenteSendoChamado = false;

        } else if (evento.getStatus().equals("AgentLogin")) {
            this.logger.debug("##### O Atendente se logou com sucesso no sistema.");
            
            this.atendenteAutenticado = true;
            this.atendenteDisponivel = true;

        } else if (evento.getStatus().equals("AgentPaused") && evento.getAgent().intValue() == this.codigoAgente) {
            if (evento.getPaused().intValue() == 0) {
                this.logger.debug("##### O Atendente saiu de pausa.");
                
                this.atendenteDisponivel = true;
            } else {
                this.logger.debug("##### O Atendente entrou pausa.");
                
                this.atendenteDisponivel = false;
            }
            
        }  else if (evento.getStatus().equalsIgnoreCase("AgentCalled")) { 
            this.logger.debug("##### O Atendente está recebendo uma ligação. Telefone tocando...");
            
            //Obtem dados da ligação no intelix
            try {
                this.chamadaParaOAgente = this.obterDadosNovaLigacaoAtendente(evento.getUniqueid());
            } catch (SocketException e) {
                this.logger.debug(e.getMessage());
                this.chamadaParaOAgente = null;
            }    
            this.agenteSendoChamado = true;
        
        } else if (evento.getStatus().equals("AgentComplete") && evento.getAgent().intValue() == this.codigoAgente) {
            this.logger.debug("##### O Atendente encerrou uma ligação.");
            
            try {
                this.ocorrenciaDAO.atualizarDataHoraFimChamada(evento.getUniqueid(), new Date());
            } catch (Exception e) {
                this.logger.error("Não foi possível atualizar a data hora de fim da chamada para o agente");
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
            this.logger.error(e);
            throw new SocketException("Não foi possível obter os dados da ligação na base de dados do intelix.");
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
     * Nome: obterListaMotivosPausa
     * Obter lista motivos pausa.
     *
     * @return list
     * @see
     */
    public List<MotivoPausaVO> obterListaMotivosPausa() {
        try {
            List<MotivoPausa> listEntity = (List<MotivoPausa>) this.telefoniaDAO.obterMotivosPausa();
            List<MotivoPausaVO> listVO = new ArrayList<MotivoPausaVO>();
            for (MotivoPausa item : listEntity) {
                MotivoPausaVO vo = new MotivoPausaVO();
                vo.setMotivoPausaId(item.getMotivoPausaId());
                vo.setDescricaoMotivoPausa(item.getNome());
                vo.setPerfilConfiguracaoId(item.getPerfilConfiguracaoId());
                vo.setProdutivo(item.getProdutivo());
                listVO.add(vo);
            }

            return listVO;
        } catch (Exception e) {
            this.logger.error(e);
            throw new BusinessException("Não foi possível obter a lista de motivos de pausa!");
        }
    }

    
    
    /**
     * Nome: ativarMicrofone
     * Ativar microfone.
     *
     * @see
     */
    public void ativarMicrofone() {        
        if (this.socket != null) {
            for (Line line : this.getLinhas()) {            
                if (line.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A0208D"));        
                }
            }            
        }
    }    

    /**
     * Nome: desativarMicrofone
     * Desativar microfone.
     *
     * @see
     */
    public void desativarMicrofone() {        
        if (this.socket != null) {
            for (Line line : this.getLinhas()) {            
                if (line.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A0309D"));        
                }
            }            
        }
    }
    
    /**
     * Nome: ativarAltoFalante
     * Ativar alto falante.
     *
     * @see
     */
    public void ativarAltoFalante() {        
        if (this.socket != null) {
            for (Line line : this.getLinhas()) {            
                if (line.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A0414D"));        
                }
            }            
        }
    }
        
    /**
     * Nome: desativarAltoFalante
     * Desativar alto falante.
     *
     * @see
     */
    public void desativarAltoFalante() {        
        if (this.socket != null) {
            for (Line line : this.getLinhas()) {            
                if (line.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A0515D"));        
                }
            }            
        }
    }
   
    public void programarDispositivoAcionamento() {        
        if (this.socket != null) {
            for (Line line : this.getLinhas()) {            
                if (line.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A1415D"));        
                }
            }            
        }
    }
    
    public void apagarDispositivoAcionamento() {        
        if (this.socket != null) {
            for (Line line : this.getLinhas()) {            
                if (line.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "A15115D"));        
                }
            }            
        }
    }    
    
    
    public void enviarSinalComutacao() {        
        this.enviarMensagem(PhoneCommand.enviarDtmf(this.userRamal, "C"));
    }
    
    public void responderKeepAlive(Integer usuario, ParametroVO parametro, String tempoDeImobilidade) {        
        //Comando 13
        this.enviarMensagem(PhoneCommand.programarTempoComunicacaoDaCentral(usuario, parametro.getHorasrParaKeepAlive()));
        
        //Parametros globais
        if (null != parametro.getTelefoneCentral1()) {
            this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(usuario, "1", parametro.getTelefoneCentral1()));    
        }
        
        if (null != parametro.getTelefoneCentral2()) {
            this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(usuario, "2", parametro.getTelefoneCentral2()));    
        }

        if (null != parametro.getTelefoneCentral2()) {
            this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(usuario, "3", parametro.getTelefoneCentral3()));    
        }
        
        if (null != parametro.getTelefoneCentral4()) {
            this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(usuario, "4", parametro.getTelefoneCentral4()));    
        }

        if (null != parametro.getTelefoneCentral5()) {
            this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(usuario, "5", parametro.getTelefoneCentral5()));    
        }

        if (null != parametro.getTelefoneCentral6()) {
            this.enviarMensagem(PhoneCommand.programarNumeroDeTelefoneDoServidor(usuario, "6", parametro.getTelefoneCentral6()));    
        }        
        
        //Informações de contrato
        this.enviarMensagem(PhoneCommand.configuraTempoDeImobilidade(usuario, tempoDeImobilidade));
        
        //Comando apra desativar emergência
        this.enviarMensagem(PhoneCommand.desativarFuncaoDeEmergencia(usuario));
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
     * @return true, se for ramal ativo senão retorna false.
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
    public void setEmAtendimento(boolean emAtendimento) {
        this.emAtendimento = emAtendimento;
    }

    public List<Line> getLinhas() {
        return linhas;
    }

    public void setLinhas(List<Line> linhas) {
        this.linhas = linhas;
    }

    public String getAlertaChamada() {
        return alertaChamada;
    }

    public void setAlertaChamada(String alertaChamada) {
        this.alertaChamada = alertaChamada;
    }

    public Integer getQtdeLigacoesFilaAtendimentoEmergencia() {
        return qtdeLigacoesFilaAtendimentoEmergencia;
    }

    public void setQtdeLigacoesFilaAtendimentoEmergencia(Integer qtdeLigacoesFilaAtendimento) {
        this.qtdeLigacoesFilaAtendimentoEmergencia = qtdeLigacoesFilaAtendimento;
    }

    public boolean isAvisoLigacaoEmergencia() {
        return avisoLigacaoEmergencia;
    }

    public void setAvisoLigacaoEmergencia(boolean avisoLigacaoEmergencia) {
        this.avisoLigacaoEmergencia = avisoLigacaoEmergencia;
    }

    public boolean isAtendenteDisponivel() {
        return atendenteDisponivel;
    }

    public void setAtendenteDisponivel(boolean atendenteDisponivel) {
        this.atendenteDisponivel = atendenteDisponivel;
    }

    public boolean isAbandonoNaFila() {
        return abandonoNaFila;
    }
    public void setAbandonoNaFila(boolean abandonoNaFila) {
        this.abandonoNaFila = abandonoNaFila;
    }

    public boolean isAgenteNaoAtende() {
        return agenteNaoAtende;
    }

    public void setAgenteNaoAtende(boolean agenteNaoAtende) {
        this.agenteNaoAtende = agenteNaoAtende;
    }

    public boolean isAgenteAtendeuLigacao() {
        return agenteAtendeuLigacao;
    }

    public void setAgenteAtendeuLigacao(boolean agenteAtendeuLigacao) {
        this.agenteAtendeuLigacao = agenteAtendeuLigacao;
    }

    public LigacaoVO getChamadaParaOAgente() {
        return chamadaParaOAgente;
    }

    public void setChamadaParaOAgente(LigacaoVO chamadaParaOAgente) {
        this.chamadaParaOAgente = chamadaParaOAgente;
    }

    public boolean isAgenteSendoChamado() {
        return agenteSendoChamado;
    }

    public void setAgenteSendoChamado(boolean agenteSendoChamado) {
        this.agenteSendoChamado = agenteSendoChamado;
    }

    public List<MotivoPausaVO> getListaMotivosPausa() {
        return listaMotivosPausa;
    }

    public void setListaMotivosPausa(List<MotivoPausaVO> listaMotivosPausa) {
        this.listaMotivosPausa = listaMotivosPausa;
    }
    public String getNumeroDiscagemLoginAgente() {
        return numeroDiscagemLoginAgente;
    }

    public boolean isKeepAliveDaCentral() {
        return keepAliveDaCentral;
    }

    public void setKeepAliveDaCentral(boolean keepAliveDaCentral) {
        this.keepAliveDaCentral = keepAliveDaCentral;
    }

    public boolean isEventoFaltaEnergia() {
        return eventoFaltaEnergia;
    }

    public void setEventoFaltaEnergia(boolean eventoFaltaEnergia) {
        this.eventoFaltaEnergia = eventoFaltaEnergia;
    }

    public boolean isEventoRetornoEnergia() {
        return eventoRetornoEnergia;
    }

    public void setEventoRetornoEnergia(boolean eventoRetornoEnergia) {
        this.eventoRetornoEnergia = eventoRetornoEnergia;
    }

    public boolean isEventoCaboTelefoneConectado() {
        return eventoCaboTelefoneConectado;
    }

    public void setEventoCaboTelefoneConectado(boolean eventoCaboTelefoneConectado) {
        this.eventoCaboTelefoneConectado = eventoCaboTelefoneConectado;
    }

    public boolean isEventoCaboTelefoneDesconectado() {
        return eventoCaboTelefoneDesconectado;
    }

    public void setEventoCaboTelefoneDesconectado(boolean eventoCaboTelefoneDesconectado) {
        this.eventoCaboTelefoneDesconectado = eventoCaboTelefoneDesconectado;
    }

    public Integer getConfigurarDispositivoRecuperado() {
        return configurarDispositivoRecuperado;
    }

    public void setConfigurarDispositivoRecuperado(Integer configurarDispositivoRecuperado) {
        this.configurarDispositivoRecuperado = configurarDispositivoRecuperado;
    }
    
}
