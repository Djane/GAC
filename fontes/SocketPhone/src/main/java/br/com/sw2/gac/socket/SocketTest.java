package br.com.sw2.gac.socket;

import br.com.sw2.gac.socket.bean.Event;
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

        LoggerUtils.init("/home/smart/GACWeb/log4j-gac.properties");
        LoggerUtils logger = LoggerUtils.getInstance(SocketTest.class);
        Integer usuarioRamal = 201;
        String codigoAgente = "9505";
        String senhaAgente = "9505";

        SocketPhone sf = new SocketPhone();

        try {

            sf.conectarAoSocketServer("189.72.105.176", 5038);
            sf.login(usuarioRamal);
           // sf.loginAgente(codigoAgente, senhaAgente);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            sf.fecharConexaoSocket(usuarioRamal);
        }
    }

}
