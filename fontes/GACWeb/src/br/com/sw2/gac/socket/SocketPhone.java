package br.com.sw2.gac.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.StringTokenizer;

import org.apache.commons.beanutils.PropertyUtils;

import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class SocketPhone {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /** Atributo socket. */
    private Socket socket = null;

    /** Atributo ramal ativo. */
    private boolean ramalAtivo = false;

    /** Atributo mensagem ativacao do ramal. */
    private String mensagemAtivacaoDoRamal = null;

    /** Atributo atendente autenticado. */
    private boolean atendenteAutenticado = false;

    /** Atributo mensagem atendente autenticado. */
    private String  mensagemAtendenteAutenticado = "Não autenticado";

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
    public void enviarMessage(String str) {
        this.logger.debug("***** Inicio do envio de mensagem ao server socket: " + str);
        PrintStream saida;
        try {
            saida = new PrintStream(this.socket.getOutputStream());
            saida.println(str);
            saida.flush();
        } catch (IOException e) {
            throw new SocketException(e);
        }
        this.logger.debug("***** Fim do envio de mensagem ao server socket: ");

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
     * Nome: loginRamal Login ramal.
     * @param ramal the ramal
     * @throws SocketException the socket exception
     * @see
     */
    public void ativarRamal(String ramal) throws SocketException {
        this.logger.debug("Iniciando ativação do ramal: " + ramal + " *****");

        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\n");
        comando.append("Command: Login");
        comando.append("\n");
        comando.append("User: ");
        comando.append(ramal);
        comando.append("\n");
        comando.append("RemoteMode: 1");
        comando.append("\n");
        comando.append("\n");

        this.enviarMessage(comando.toString());

        try {
            String resposta = aguardarResposta();
            Event evento = parse2Event(resposta);
            this.ramalAtivo = false;
            this.mensagemAtivacaoDoRamal = "Não foi possível ativar o ramal.\n" + evento.getMessage();
            if (evento.getResponse().equalsIgnoreCase("Success")) {
                this.ramalAtivo = true;
            }
            this.logger.debug(resposta);

        } catch (Exception e) {
            throw new SocketException(e);
        }
        this.logger.debug("Finalizada ativação do ramal: " + ramal + " *****");
    }


    /**
     * Nome: autenticarAtendente
     * Autenticar atendente.
     *
     * @param numero the numero
     * @param ramal the ramal
     * @param codigoAtendente the codigo atendente
     * @param senhaAtendente the senha atendente
     * @see
     */
    public void autenticarAtendente(String numero, String ramal, String codigoAtendente,
        String senhaAtendente) {

        Event evento = this.discar(numero, ramal);
        this.atendenteAutenticado = false;
        if (evento.getStatus().equalsIgnoreCase("Dialing")) {
            // Discagem feita para o numero de autenticação com sucesso.

            // Envia DTMF com o código do atendente (Agent)
            evento = this.enviarDTMF(codigoAtendente, ramal);

            if (evento.getStatus().equalsIgnoreCase("Answer")) {
                // Codigo do atendente (Agent) aceito. Envia a senha
                evento = this.enviarDTMF(senhaAtendente, ramal);
                if (evento.getStatus().equalsIgnoreCase("AgentLogin")) {
                    this.atendenteAutenticado = true;
                }
            }
            this.mensagemAtendenteAutenticado = evento.getStatus();
        } else {
            this.mensagemAtendenteAutenticado = "Problemas na discagem para autenticação do atendente";
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
        comando.append("\r\n");
        comando.append("Command: SendDtmf");

        if (null != ramal && !"".equals(ramal.trim())) {
            comando.append("\r\n");
            comando.append("User: ");
            comando.append(ramal);
        }

        comando.append("\r\n");
        comando.append("Digits: ");
        comando.append(digitos);
        comando.append("#");
        comando.append("\r\n");
        comando.append("\r\n");

        String comandoStr = comando.toString();
        this.enviarMessage(comandoStr);

        Event evento;
        try {
            String resposta = aguardarResposta();
            evento = parse2Event(resposta);
        } catch (Exception e) {
            throw new SocketException(e);
        }

        this.logger.debug("**** Comando de discagem: " + comandoStr);

        return evento;

    }

    /**
     * Nome: discar
     * Discar.
     *
     * @param numero the numero
     * @param ramal the ramal
     * @return event
     * @see
     */
    public Event discar(String numero, String ramal) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Dial");

        if (null != ramal && !"".equals(ramal.trim())) {
            comando.append("\r\n");
            comando.append("User: ");
            comando.append(ramal);
        }

        comando.append("\r\n");
        comando.append("Number: ");
        comando.append(numero);
        comando.append("\r\n");
        comando.append("\r\n");

        String comandoStr = comando.toString();

        this.enviarMessage(comandoStr);

        Event evento;
        try {
            String resposta = aguardarResposta();
            evento = parse2Event(resposta);
        } catch (Exception e) {
            throw new SocketException(e);
        }

        this.logger.debug("**** Comando de discagem: " + comandoStr);

        return evento;
    }

    /**
     * Nome: aguardarResposta Aguardar resposta.
     * @return string
     * @throws IOException the IO exception
     * @see
     */
    private String aguardarResposta() throws IOException {

        BufferedReader recebidos;
        recebidos = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String retorno = "";
        String retornoCompleto = "";
        while ((retorno = recebidos.readLine()) != null) {
            retornoCompleto += retorno + ":";
            if (!recebidos.ready()) {
                break;
            }
        }
        this.logger.debug("Resposta do server socket :" + retornoCompleto);

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
                e.printStackTrace();
            }
            i++;
        }

        return evento;
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
     * Nome: getMensagemAtivacaoDoRamal
     * Recupera o valor do atributo 'mensagemAtivacaoDoRamal'.
     *
     * @return valor do atributo 'mensagemAtivacaoDoRamal'
     * @see
     */
    public String getMensagemAtivacaoDoRamal() {
        return mensagemAtivacaoDoRamal;
    }

    /**
     * Nome: setMensagemAtivacaoDoRamal
     * Registra o valor do atributo 'mensagemAtivacaoDoRamal'.
     *
     * @param mensagemAtivacaoDoRamal valor do atributo mensagem ativacao do ramal
     * @see
     */
    public void setMensagemAtivacaoDoRamal(String mensagemAtivacaoDoRamal) {
        this.mensagemAtivacaoDoRamal = mensagemAtivacaoDoRamal;
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
     * Nome: getMensagemAtendenteAutenticado
     * Recupera o valor do atributo 'mensagemAtendenteAutenticado'.
     *
     * @return valor do atributo 'mensagemAtendenteAutenticado'
     * @see
     */
    public String getMensagemAtendenteAutenticado() {
        return mensagemAtendenteAutenticado;
    }

    /**
     * Nome: setMensagemAtendenteAutenticado
     * Registra o valor do atributo 'mensagemAtendenteAutenticado'.
     *
     * @param mensagemAtendenteAutenticado valor do atributo mensagem atendente autenticado
     * @see
     */
    public void setMensagemAtendenteAutenticado(String mensagemAtendenteAutenticado) {
        this.mensagemAtendenteAutenticado = mensagemAtendenteAutenticado;
    }
/*
    public static void main(String args[]) {

        SocketPhone sc = new SocketPhone();
        try {
            sc.conectarAoSocketServer("localhost", 5038);
            sc.getSocket().setSoTimeout(60 * 1000);
            sc.autenticarAtendente("*9800","504", "9502", "1234");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

}
