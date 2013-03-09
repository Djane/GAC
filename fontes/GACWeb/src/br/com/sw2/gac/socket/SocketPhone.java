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
import java.util.List;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.socket.exception.SocketConnectionException;
import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class SocketPhone  {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass(), "/home/smart/GACWeb/log4j-gac.properties");

    /** Atributo socket. */
    private Socket socket = null;

    /** Atributo ramal ativo. */
    private boolean ramalAtivo = false;

    /** Atributo atendente autenticado. */
    private boolean atendenteAutenticado = false;

    /** Atributo em atendimento. */
    private boolean emAtendimento = false;

    /** Atributo alerta chamada. */
    private String alertaChamada = "";

    /** Atributo linhas. */
    private List<Line> linhas;

    /** Atributo qtde ligacoes fila atendimento. */
    private Integer qtdeLigacoesFilaAtendimentoEmergencia = 0;
    /**
     * Construtor Padrao
     * Instancia um novo objeto SocketPhone.
     */
    public SocketPhone() {

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
                this.logger.debug("Conectado ao host " + host + ":" + port);
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
        this.logger.debug("***** Enviando mensagem para o servidor socket: \n" + str);
        if (null == this.socket) {
            this.logger.error("Não há uma conexão estabelecida com o servidor socket");
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
        this.logger.debug("Aguardando por eventos de resposta do servidor socket:");
        while ((retorno = recebidos.readLine()) != null) {
            retornoCompleto += retorno + ":";
            mensagemDebug += retorno + "\n";
            if (!recebidos.ready()) {
                break;
            }
        }
        this.logger.debug("Mensagem recebida do servidor socket: \n" + mensagemDebug);

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
        this.logger.debug("Aguardando por eventos de resposta do servidor socket:");
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

        this.logger.debug("Mensagem recebida do servidor socket: \n" + retornoCompleto);
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
                this.logger.debug("Não foi possivel fazer parser da mensagem recebida pelo socket, para um objeto. \n " + str);
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
        this.logger.debug("Iniciando escuta. Aguardando resposta do server socket");
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

        this.logger.debug("Finalizada escuta. Entregando mensagem " + responseComplete);

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

        if (null != usuarioRamal) {
            try {
                this.logger.debug("Iniciando encerramento da conexão com o socket.");
                try {
                    hangupAll(usuarioRamal);
                    this.enviarMensagem(PhoneCommand.logoff(usuarioRamal));
                } catch (Exception e) {
                    this.logger.error(e);
                }
                this.socket.close();
                this.logger.debug("Conexão com o socket encerrada");
            } catch (Exception e) {
                this.logger.error("Não foi possível fechar a conexão com o socket");
            }
        }
    }

    /**
     * Nome: login
     * Login.
     *
     * @param usuario the user
     * @throws SocketException the socket exception
     * @see
     */
    public void login(Integer usuario) throws SocketException {

        try {
            this.logger.debug("Iniciando login do usuario/ramal " + usuario);
            String retorno = "";
            this.enviarMensagem(PhoneCommand.login(usuario));
            while (true) {
                retorno = this.aguardarResposta();
                if (retorno.contains("Response: Success") && retorno.contains("User: " + usuario)
                    && retorno.contains("Command: Login")) {

                    this.setRamalAtivo(true);
                    this.logger.debug("O Ramal " + usuario + " foi logado com sucesso");
                    break;
                } else if (retorno.contains("User: " + usuario) && (retorno.contains("Status: Error") || retorno.contains("Response: Error"))) {
                    throw new SocketCommandException("Não foi possível efetuar o login de :" + usuario + " - " + retorno);
                }
            }

        } catch (SocketCommandException e) {
            this.logger.debug("Falha no login de " + usuario);
            throw e;
        } catch (Exception e) {
            this.logger.debug("Falha no login de " + usuario);
            throw new SocketException(e);
        }
    }


    /**
     * Nome: loginAgente
     * Login agente.
     *
     * @param usuarioRamal the usuario ramal
     * @param codigoAgente the codigo agente
     * @param senhaAgente the senha agente
     * @throws SocketException the socket exception
     * @see
     */
    public void loginAgente(Integer usuarioRamal, String codigoAgente, String senhaAgente) throws SocketException {

        this.enviarMensagem(PhoneCommand.selecionarLinha(usuarioRamal, 1));
        String numeroLoginAgente = "*9800";
        Integer linha = 1;

        int timeOut = 0;

        logger.debug("Discando para " + numeroLoginAgente);
        this.enviarMensagem(PhoneCommand.discar(numeroLoginAgente, usuarioRamal, linha));

        String retornoDial;

        try {

            while (true) {
                retornoDial = this.aguardarResposta();
                if (retornoDial.contains("Status: AgentLogin")) {

                    logger.debug("Agente ja está logado");
                    this.setAtendenteAutenticado(true);
                    this.enviarMensagem(PhoneCommand.queueStatus(usuarioRamal));
                    break;

                } else if (retornoDial.contains("Event: DGPhoneEvent")
                    && retornoDial.contains("Status: Dialing")
                    && retornoDial.contains("User: " + usuarioRamal)
                    && retornoDial.contains("Line: " + linha)) {

                    logger.debug("Discagem para " + numeroLoginAgente + " bem sucedida. Enviando DTMF");
                    logger.debug("Enviando DTMF com código do agente " + codigoAgente + " para o usuário " + usuarioRamal);
                    this.enviarMensagem(PhoneCommand.enviarDtmf(usuarioRamal, codigoAgente + "#"));

                    String retornoDtmf1;
                    while (true) {
                        retornoDtmf1 = this.aguardarResposta();
                        if (retornoDtmf1.contains("Event: DGPhoneEvent")
                            && retornoDtmf1.contains("Status: Answer")
                            && retornoDtmf1.contains("User: " + usuarioRamal)
                            && retornoDtmf1.contains("Line: " + linha)
                            && retornoDtmf1.contains("Number: " + numeroLoginAgente)) {

                            logger.debug("Envio do código do agente " + codigoAgente + " aceito para usuario " + usuarioRamal);
                            logger.debug("Enviando DTMF com senha do agente para o usuário " + usuarioRamal);
                            this.enviarMensagem(PhoneCommand.enviarDtmf(usuarioRamal, senhaAgente + "#"));
                            while (true) {
                                String retornoDtmfSenha = this.aguardarResposta();
                                if (retornoDtmfSenha.contains("Event: DGPhoneEvent")  && retornoDtmfSenha.contains("Status: AgentLogin")) {
                                    this.setAtendenteAutenticado(true);
                                    this.logger.debug("O agente " + codigoAgente + " se logou no ramal " + usuarioRamal);

                                    //Atualizar qtde de linhas em emergencia
                                    this.enviarMensagem(PhoneCommand.queueStatus(usuarioRamal));

                                } else if (retornoDtmf1.contains("Status: Error")) {
                                    throw new SocketCommandException("Problemas no envio da senha do agente " + codigoAgente);
                                } else if (retornoDial.contains("DGTimeStamp")) {
                                    timeOut = timeOut(timeOut);
                                }
                                break;
                            }
                            break;

                        } else if (retornoDtmf1.contains("Status: Error")
                            || retornoDtmf1.contains("Status: Busy")
                            || retornoDtmf1.contains("Status: Hangup")) {
                            throw new SocketCommandException("Problemas no envio do código do agente " + codigoAgente);
                        } else if (retornoDial.contains("DGTimeStamp")) {
                            timeOut = timeOut(timeOut);
                        }
                    }

                    break;
                } else if (retornoDial.contains("DGTimeStamp")) {
                    timeOut = timeOut(timeOut);
                } else if (retornoDial.contains("Status: Error") || retornoDial.contains("Status: Hangup")) {
                    throw new SocketCommandException("Não foi possível discar para "
                        + numeroLoginAgente);
                }

            }
        } catch (Exception e) {
            throw new SocketCommandException(e);
        }
    }

    /**
     * Nome: timeOut
     * Time out.
     *
     * @param timeOut the time out
     * @return int
     * @see
     */
    private int timeOut(int timeOut) {
        timeOut++;
        if (timeOut > 1) {
            throw new SocketCommandException("O tempo limite para o login excedeu !");
        }
        return timeOut;
    }

    /**
     * Nome: atenderLigacaoParaAgente
     * Atender ligacao para agente.
     *
     * @param ramal the ramal
     * @throws SocketCommandException the socket command exception
     * @see
     */
    public void atenderLigacaoParaAgente(Integer ramal) throws SocketCommandException {
        try {
            this.enviarMensagem(PhoneCommand.selecionarLinha(ramal, 1));
            this.enviarMensagem(PhoneCommand.enviarDtmf(ramal, "#"));
        } catch (Exception e) {
            throw new SocketCommandException(e);
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
     * Nome: saveState
     * Save state.
     *
     * @see
     */
    public void saveState() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
            .getSession(false);
        session.setAttribute("socketPhone", this);
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

}
