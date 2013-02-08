package br.com.sw2.gac.socket;

import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class SocketTest {

    /**
     * Metodo principal.
     * @param args the args
     */
    public static void main(String args[]) {

        LoggerUtils logger = LoggerUtils.getInstance(SocketTest.class, "/home/smart/GACWeb/log4j-gac.properties");
        String usuarioRamal = "201";
        String numeroLoginAgente  = "*9800";
        String codigoAgente  = "2001";
        String senhaAgente  = "2001";
        Integer linha = 1;
        SocketPhone sf = new SocketPhone();

        try {

            sf.conectarAoSocketServer("189.72.105.176", 5038);
            sf.login("201");
            sf.loginAgente(usuarioRamal, codigoAgente, senhaAgente);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            sf.fecharConexaoSocket(usuarioRamal);
        }
    }

}
