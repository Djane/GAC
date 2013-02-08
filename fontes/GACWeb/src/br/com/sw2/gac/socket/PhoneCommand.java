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
     * @param user the user
     * @return string
     * @see
     */
    public static String login(String user) {
        StringBuffer comando = new StringBuffer();
        comando.append("Action: DGPhoneCommand");
        comando.append("\r\n");
        comando.append("Command: Login");
        comando.append("\r\n");
        comando.append("User: ");
        comando.append(user);
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
    public static String logoff(String usuarioRamal) {
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
    public static String selecionarLinha(String usuario, Integer linha) {

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
    public static String discar(String numero, String usuario, Integer linha) {

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
    public static String enviarDtmf(String usuario, String digitos) {
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
        comando.append("#");
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
    public static String desligar(String usuario, Integer linha) {
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

}
