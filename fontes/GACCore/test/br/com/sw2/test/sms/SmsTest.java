package br.com.sw2.test.sms;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.SmsBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.SmsVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class SmsTest {

    /** Atributo sms business. */
    private SmsBusiness smsBusiness = new SmsBusiness();

    /** Atributo sms. */
    private SmsVO sms = new SmsVO();

    /**
     * Nome: setup Setup.
     * @see
     */
    @Before
    public void setup() {

        sms.setTitulo("Titulo SMS 1");
        sms.setTexto("Corpo da mensagem 1");
        sms.setDtInicioValidade(new Date());
    }

    /**
     * Nome: adicionarNovoSMS Adicionar novo sms.
     * @see
     */

    @Test
    public void testCrud() {
        this.adicionarNovoSMS();
        this.pesquisarMensagemPortituloMensagem();
        this.apagarSms();

    }

    /**
     * Nome: adicionarNovoSMS Adicionar novo sms.
     * @see
     */
    private void adicionarNovoSMS() {

        try {
            // Adiciona a 1º vez
            this.smsBusiness.adicionarNovaMensagem(this.sms);

        } catch (BusinessException e) {
            e.printStackTrace();
            Assert.fail();
        }

        try {
            // Adiciona a 2º vez
            this.smsBusiness.adicionarNovaMensagem(this.sms);
            Assert.fail();
        } catch (BusinessException e) {
            if (e.getExceptionCode() == BusinessExceptionMessages.SMS_JA_CADASTRADO.getValue()
                    .intValue()) {
                Assert.assertTrue(true);
            } else {
                e.printStackTrace();
                Assert.fail();
            }
        }
    }

    /**
     * Nome: pesquisarMensagemPortituloMensagem Pesquisar mensagem portitulo mensagem.
     * @see
     */
    private void pesquisarMensagemPortituloMensagem() {

        this.sms = this.smsBusiness.getSms(this.sms);

        if (null == this.sms) {
            Assert.fail();
        } else {
            Assert.assertTrue(true);
        }

    }

    /**
     * Nome: apagarSms Apagar sms.
     * @see
     */
    private void apagarSms() {
        try {
            this.smsBusiness.apagarSms(this.sms);
            Assert.assertTrue(true);
        } catch (BusinessException exception) {
            exception.printStackTrace();
        }
    }
}
