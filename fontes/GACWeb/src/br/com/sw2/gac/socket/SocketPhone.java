package br.com.sw2.gac.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class SocketPhone implements SocketCommandInterface {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /** Atributo socket. */
    private Socket socket = null;

    /** Atributo ramal ativo. */
    private boolean ramalAtivo = false;

    /** Atributo atendente autenticado. */
    private boolean atendenteAutenticado = false;

    /** Atributo em atendimento. */
    private boolean emAtendimento = false;

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

        try {
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            write.write(str);
            write.flush();
        } catch (Exception e) {
            this.logger.debug("***** Não foi possível enviar a mensagem  ao servidor socket.\n" + e.getMessage());
            throw new SocketException(e);
        }

    }

    /**
     * Nome: getMessage Recupera o valor do atributo 'message'.
     * @return valor do atributo 'message'
     * @see
     */
    public String getMensagem() {
        String retorno = null;
        try {
            BufferedReader recebidos = new BufferedReader(new InputStreamReader(
                socket.getInputStream()));
            retorno = ler(recebidos);
        } catch (IOException e) {
            throw new SocketException("Erro de IO ao obter socket.getInputStream()");
        } catch (SocketException e) {
            throw new SocketException(e);
        }
        return retorno;
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
     * Nome: ler Ler.
     * @param bf the bf
     * @return string
     * @throws SocketException the socket exception
     * @see
     */
    private String ler(BufferedReader bf) throws SocketException {
        String message = "";
        String mensagemCompleta = "";

        try {
            if (bf.ready()) {
                while ((message = bf.readLine()) != null) {
                    mensagemCompleta += message;
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
     * Nome: autenticarAtendente
     * Autenticar atendente.
     *
     * @param numero the numero
     * @param ramal the ramal
     * @param codigoAtendente the codigo atendente
     * @param senhaAtendente the senha atendente
     * @throws SocketException e
     * @see
     */
    public void autenticarAtendente(String numero, String ramal, String codigoAtendente,
        String senhaAtendente) throws SocketException {

        this.logger.debug("***** Iniciando autenticação do atendente (agent) *****");
        Event evento = this.iniciarLigacao(numero);
        this.atendenteAutenticado = false;
        if (evento.getStatus().equalsIgnoreCase("Dialing")) {
            this.logger.debug("***** Status dialing recebido *****");
            // Discagem feita para o numero de autenticação com sucesso.

            // Envia DTMF com o código do atendente (Agent)
            evento = this.enviarDTMF(codigoAtendente, ramal);

            if (evento.getStatus().equalsIgnoreCase("Answer")) {
                // Codigo do atendente (Agent) aceito. Envia a senha
                evento = this.enviarDTMF(senhaAtendente, ramal);
                if (evento.getStatus().equalsIgnoreCase("AgentLogin")) {
                    this.atendenteAutenticado = true;
                    this.logger.debug("***** A autenticação do atendente (agent) foi efetuada com sucesso *****");
                } else {
                    throw new SocketException("Não foi possível autenticar o atendente.\n O status AgentLogin não foi recebido. \n Status recebido: "
                        + evento.getStatus());
                }

            } else {
                throw new SocketException(
                    "Não foi obtida uma resposta 'Answer' após o envio do codigo do atendente (Agent). Status do evento recebido foi:\n"
                        + evento.getStatus());
            }
            this.logger.debug("***** O atendente (agent) foi autenticado *****");
        } else {
            throw new SocketException(
                "Problemas na discagem para autenticação do agente. O status Dialing não foi recebido. \n O Status recebido foi "
                    + evento.getEvent());
        }
    }

    /**
     * Nome: enviarDTMF
     * Enviar dtmf.
     *
     * @param digitos the digitos
     * @param ramal the ramal
     * @return event
     * @see
     */
    public Event enviarDTMF(String digitos, String ramal) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\n");
        comando.append("Command: SendDtmf");

        if (null != ramal && !"".equals(ramal.trim())) {
            comando.append("\n");
            comando.append("User: ");
            comando.append(ramal);
        }

        comando.append("\n");
        comando.append("Digits: ");
        comando.append(digitos);
        comando.append("#");
        comando.append("\n");
        comando.append("\n");

        String comandoStr = comando.toString();
        this.enviarMensagem(comandoStr);

        Event evento;
        try {
            String resposta = aguardarResposta();
            evento = parse2Event(resposta);
        } catch (Exception e) {
            throw new SocketException(e);
        }
        return evento;

    }

    /**
     * Nome: aguardarResposta Aguardar resposta.
     * @return string
     * @throws IOException the IO exception
     * @see
     */
    private String aguardarResposta() throws IOException {

        BufferedReader recebidos = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String retorno = "";
        String retornoCompleto = "";
        String mensagemDebug = "";
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
                PropertyUtils.setProperty(evento, name, value);
            } catch (Exception e) {
                evento.setEvent("Desconhecido");
                evento.setMessage(e.getMessage());
                this.logger.debug("Não foi possivel fazer parser da mensagem recebida pelo socket, para um objeto");
            }
            i++;
        }

        return evento;
    }

    /**
     * Nome: escutar
     * Escutar.
     *
     * @return string
     * @see
     */
    public Event escutar() {
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
            e.printStackTrace();
        }
        Event retorno = parse2Event(responseComplete);

        this.logger.debug("Finalizada escuta. Entregando mensagem " + responseComplete);

        return retorno;
    }

    /**
     * Nome: fecharConexaoSocket
     * Fechar conexao socket.
     *
     * @see
     */
    public void fecharConexaoSocket() {
        try {
            this.socket.getInputStream().close();
            this.socket.getOutputStream().close();
            this.socket.close();
        } catch (IOException e) {
            this.logger.error("Não foi possível fechar a conexão com o socket");
        }
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

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#iniciarLigacao(java.lang.String)
     */
    @Override
    public Event iniciarLigacao(String numeroTelefone) throws SocketCommandException {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Dial");
        comando.append("\r\n");
        comando.append("Number: ");
        comando.append(numeroTelefone);
        comando.append("\r\n");
        comando.append("\r\n");

        Event evento;
        try {
            this.enviarMensagem(comando.toString());

            String resposta = aguardarResposta();
            evento = parse2Event(resposta);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SocketException(e);
        }
        return evento;
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#atenderLigacao(java.lang.String, int)
     */
    @Override
    public void atenderLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\n");
        comando.append("Command: Answer");
        comando.append("\n");
        comando.append("Line: ");
        comando.append(numeroDalinha);
        comando.append("\n");
        comando.append("\n");
        try {
            this.enviarMensagem(comando.toString());

            String resposta = aguardarResposta();
            Event evento = parse2Event(resposta);

            if (evento.getStatus().equalsIgnoreCase("AgentConnect")) {
                this.logger.debug("***** Chamada atendida *****");
            } else {
                throw new SocketException("O Status esperado não foi obtido. Esperava-se: AgentConnect e foi recebido: " + evento.getStatus());
            }
            this.logger.debug("***** A ligação foi atendida");

        } catch (Exception e) {
            throw new SocketCommandException(e);
        }
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#iniciarPausaNaLigacao(java.lang.String, int)
     */
    @Override
    public void iniciarPausaNaLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#removerPausaDaLigacao(java.lang.String, int)
     */
    @Override
    public void removerPausaDaLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#encerrarLigacao(java.lang.String, int)
     */
    @Override
    public void encerrarLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#desativarRamal()
     */
    @Override
    public void desativarRamal() throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#logarAtendente(java.lang.String, java.lang.String)
     */
    @Override
    public void logarAtendente(String codigoAgente, String senhaAgente)
        throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#logoffAtendente()
     */
    @Override
    public void logoffAtendente() throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#mudarStatusDisponibilidade(int)
     */
    @Override
    public void mudarStatusDisponibilidade(int codigoStatus) throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#breakAtendente(int)
     */
    @Override
    public void breakAtendente(int estado) throws SocketCommandException {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see br.com.sw2.gac.socket.SocketCommandInterface#ativarRamal(java.lang.String)
     */
    @Override
    public void ativarRamal(String numeroRamal) throws SocketCommandException {
        this.logger.debug("Iniciando ativação do ramal: " + numeroRamal + " *****");

        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\n");
        comando.append("Command: Login");
        comando.append("\n");
        comando.append("User: ");
        comando.append(numeroRamal);
        comando.append("\n");
        comando.append("RemoteMode: 1");
        comando.append("\n");
        comando.append("\n");

        try {
            this.enviarMensagem(comando.toString());

            String resposta = aguardarResposta();
            Event evento = parse2Event(resposta);

            if (evento.getResponse().equalsIgnoreCase("Success")) {
                this.ramalAtivo = true;
            } else {
                this.ramalAtivo = false;
                throw new SocketException(
                    "Não foi possível autenticar o ramal. Não foi recebido um status Sucess em resposta ao comando login.\n" + evento.getMessage());
            }
        } catch (SocketCommandException e) {
            throw e;
        } catch (Exception e) {
            throw new SocketCommandException(e);
        }
        this.logger.debug("***** Finalizada ativação do ramal: " + numeroRamal + " *****");
    }
}
