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
            sf.login(usuarioRamal);
            sf.loginAgente(usuarioRamal, codigoAgente, senhaAgente);

            //Teste de ligação

   /*         sf.enviarMensagem(PhoneCommand.selecionarLinha(usuarioRamal, 2));
            sf.enviarMensagem(PhoneCommand.discar("4001", usuarioRamal, 2));

            String retornoDial = "";
            while (true) {
                retornoDial = sf.aguardarResposta();
                if (retornoDial.contains("Status: Hangup")
                    || retornoDial.contains("Status: Busy")
                    || retornoDial.contains("Status: Error")
                    && (retornoDial.contains("User: " + usuarioRamal) && retornoDial
                        .contains("Line: " + 2))) {
                    System.out.println("Não foi possível completar a ligação");
                    break;
                }
            }

            sf.enviarMensagem(PhoneCommand.desligar(usuarioRamal, linha));
*/
        } catch (Exception e) {
            sf.fecharConexaoSocket(usuarioRamal);
            e.printStackTrace(); 
            
        } finally {
            sf.fecharConexaoSocket(usuarioRamal);
        }
    }

}
