package br.com.sw2.gac.socket;

import java.util.List;

import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.util.CollectionUtils;
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

        LoggerUtils logger = LoggerUtils.getInstance(SocketTest.class,
            "/home/smart/GACWeb/log4j-gac.properties");
        Integer usuarioRamal = 201;
        String numeroLoginAgente = "*9800";
        String codigoAgente = "2001";
        String senhaAgente = "2001";
        Integer linha = 1;
        SocketPhone sf = new SocketPhone();

        try {

            sf.conectarAoSocketServer("189.72.105.176", 5038);
            sf.login(usuarioRamal);
            sf.loginAgente(usuarioRamal, codigoAgente, senhaAgente);

            sf.enviarMensagem(PhoneCommand.selecionarLinha(usuarioRamal, 2));
            sf.enviarMensagem(PhoneCommand.discar("4010", usuarioRamal, 2));

            //Simulando ligação chegando


            //sf.enviarMensagem(PhoneCommand.selecionarLinha(usuarioRamal, 3));
            //sf.enviarMensagem(PhoneCommand.discar("4010", usuarioRamal, 3));

         //   sf.enviarMensagem(PhoneCommand.hold(usuarioRamal, 1)); // Tira o Hold

            int i = 0;

            //RAscunho de componente monitor
            boolean looping = true;
            while (looping) {

                List<Event> eventos = sf.aguardarEvento();

                for (Event evento : eventos) {

                    if (null != evento.getUser() && evento.getUser().equals(usuarioRamal)) {
                        //Detectado evento para o usuário
                        Line line = null;
                        if (null != evento.getLine()) {
                            line = (Line) CollectionUtils.findByAttribute(sf.getLinhas(), "numeroLinha", evento.getLine());
                        }
                        if (null != evento.getStatus() && evento.getStatus().equals("Dialing")) {
                            line.setNumeroDiscado(evento.getNumber());
                        } else if (null != evento.getStatus() && evento.getStatus().equals("Answer")) {
                            line.setStatusLinha(StatusLigacao.FALANDO.getValue());
                        } else if (null != evento.getStatus() && evento.getStatus().equals("Hangup")) {
                            //Verificar porem n~~ao há uam ação a ser tomada no momento
                        } else if (null != evento.getStatus() && evento.getStatus().equals("Ready")) {
                            line.setStatusLinha(StatusLigacao.LIVRE.getValue());
                            line.setNumeroDiscado(null);
                        } else if (null != evento.getStatus() && evento.getStatus().equals("Hold")) {
                            if (evento.getHold().equals("1")) {
                                line.setStatusLinha(StatusLigacao.PAUSA.getValue());
                            } else if (evento.getHold().equals("0")) {
                                line.setStatusLinha(StatusLigacao.FALANDO.getValue());
                            }
                        } else if (null != evento.getStatus() && evento.getStatus().equals("AgentCalled")) {

                            //Atende ligação chegando.
                            sf.enviarMensagem(PhoneCommand.selecionarLinha(usuarioRamal, 1)); //Seleciona a linha de *9800
                            sf.enviarMensagem(PhoneCommand.hold(usuarioRamal, 1)); // Tira o Hold
                            sf.enviarMensagem(PhoneCommand.enviarDtmf(usuarioRamal, "#")); // Enva DTMF avisando que é para atender

                        }
                    }
                }


                /*
                if (retornoDial.contains("Status: Hangup")
                    || retornoDial.contains("Status: Busy")
                    || retornoDial.contains("Status: Error")
                    && (retornoDial.contains("User: " + usuarioRamal) && retornoDial
                        .contains("Line: " + 2))) {
                    System.out.println("Não foi possível completar a ligação");
                    break;
                } else if (retornoDial.contains("Status: Answer")) {
                    sf.enviarMensagem(PhoneCommand.answer(usuarioRamal, 2));
                }*/
                i++;
                sf.enviarMensagem(PhoneCommand.hold(usuarioRamal, 1)); // Tira o Hold

                if (i > 3) {
                    break;
                }

            }

            sf.enviarMensagem(PhoneCommand.desligar(usuarioRamal, linha));
            sf.enviarMensagem(PhoneCommand.desligar(usuarioRamal, 2));

        } catch (Exception e) {            
            e.printStackTrace();

        } finally {
            sf.fecharConexaoSocket(usuarioRamal);
        }
    }

}
