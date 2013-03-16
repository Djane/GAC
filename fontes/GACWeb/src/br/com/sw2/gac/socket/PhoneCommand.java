package br.com.sw2.gac.socket;

/**
 * <b>Descrição: Classe que representa os comandos a serem enviados ao DGPhone </b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public final class PhoneCommand {

    /**
     * Construtor Padrao
     * Instancia um novo objeto PhoneCommand.
     */
    private PhoneCommand() {
        super();
    }


    /**
     * Nome: dial
     * Dial.
     *
     * @param usuario the usuario.
     * @return string
     * @see
     */
    public static String login(Integer usuario) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Login");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("RemoteMode: 1");
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }


    /**
     * Nome: logoff
     * Logoff.
     *
     * @param usuarioRamal the usuario ramal
     * @return string
     * @see
     */
    public static String logoff(Integer usuarioRamal) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Logoff");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuarioRamal);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }

    /**
     * Nome: selecionarLinha
     * Selecionar linha.
     *
     * @param usuario the usuario
     * @param linha the linha
     * @return string
     * @see
     */
    public static String selecionarLinha(Integer usuario, Integer linha) {

        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: LineSelect");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("Line: ");
        comando.append(linha.toString());
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();

    }


    /**
     * Nome: discar
     * Discar.
     *
     * @param numero the numero
     * @param usuario the usuario
     * @param linha the linha
     * @return string
     * @see
     */
    public static String discar(String numero, Integer usuario, Integer linha) {

        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Dial");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        if (null != linha) {
            comando.append("Line: ");
            comando.append(linha);
        }
        comando.append("\r\n");
        comando.append("Number: ");
        comando.append(numero);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }

    /**
     * Nome: enviarDtmf
     * Enviar dtmf.
     *
     * @param usuario the usuario
     * @param digitos the digitos
     * @return string
     * @see
     */
    public static String enviarDtmf(Integer usuario, String digitos) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: SendDtmf");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("Digits: ");
        comando.append(digitos);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }

    /**
     * Nome: desligar
     * Desligar.
     *
     * @param usuario the usuario
     * @param linha the linha
     * @return string
     * @see
     */
    public static String desligar(Integer usuario, Integer linha) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Hangup");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("Line: ");
        comando.append(linha);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }


    /**
     * Nome: answer
     * Answer.
     *
     * @param usuario the usuario
     * @param linha the linha
     * @return string
     * @see
     */
    public static String answer(Integer usuario, Integer linha) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Answer");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("Line: ");
        comando.append(linha);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }

    /**
     * Nome: hold
     * Muda o status de um ligação para o status oposto ao status atual.
     *
     * @param usuario the usuario
     * @param linha the linha
     * @return string
     * @see
     */
    public static String hold(Integer usuario, Integer linha) {
        return hold(usuario, linha, null);
    }

    /**
     * Nome: hold
     * Muda o status de espera de uma ligação.
     *
     * @param usuario the usuario
     * @param linha the linha
     * @param onoff 0 indica chamada em espera, 1 indica chamada ativa.
     * @return string
     * @see
     */
    public static String hold(Integer usuario, Integer linha, Integer onoff) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Hold");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");

        if (null != onoff) {
            comando.append("Hold: ");
            comando.append(onoff);
            comando.append("\r\n");
        }

        comando.append("Line: ");
        comando.append(linha);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }

    /**
     * Nome: queueStatus
     * Queue status.
     *
     * @param usuario the usuario
     * @param fila the fila
     * @return string
     * @see
     */
    public static String queueStatus(Integer usuario, Integer fila) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: QueueStatus");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("Queue: ");
        comando.append(fila);
        comando.append("\r\n");
        comando.append("\r\n");

        return comando.toString();
    }


    /**
     * Nome: dgTimeStamp
     * Dg time stamp.
     *
     * @param usuario the usuario
     * @return string
     * @see
     */
    public static String dgTimeStamp(Integer usuario) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: DGTimeStamp");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("\r\n");
        return comando.toString();
    }

    /**
     * Nome: agentPaused
     * Coloca ou não o agente/atendente em pausa.
     *
     * @param agente Código do agente a entar em pausa.
     * @param pausa Indica true para pausa e false para tirar de pausa
     * @param motivo Codigo do motivo da pausa
     * @return string
     * @see
     */
    public static String agentPause(Integer usuario, Integer agente, boolean pausa, Integer motivo) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: AgentPause");
        comando.append("\r\n");
        comando.append("Agent: ");
        comando.append(agente);
        comando.append("\r\n");
        comando.append("Paused: ");
        if (pausa) {
            comando.append("1");
        } else {
            comando.append("0");
        }
        comando.append("\r\n");
        comando.append("Reason: ");
        comando.append(motivo);
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(usuario);
        comando.append("\r\n");
        comando.append("\r\n");
        return comando.toString();
    }
}
