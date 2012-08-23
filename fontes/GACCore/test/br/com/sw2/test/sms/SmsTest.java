package br.com.sw2.test.sms;

import java.util.Date;
import java.util.List;

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
        sms.setDtTerminoValidade(new Date());
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
        this.atualizarMensagem();
        this.listarMensagensAtivas();
        this.apagarSms();
    }

    /**
     * Nome: listarMensagensAtivas
     * Listar mensagens ativas.
     *
     * @see
     */
    private void listarMensagensAtivas() {
        try {
            // Adiciona a 1º vez
            List<SmsVO> lista = this.smsBusiness.obterListaMensagensAtivas(sms);
            Assert.assertTrue(!lista.isEmpty());
        } catch (BusinessException e) {
            e.printStackTrace();
            Assert.fail();
        }
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
     * Nome: atualizarMensagem
     * Atualizar mensagem.
     *
     * @see
     */
    private void atualizarMensagem() {
        try {
            // Adiciona a 1º vez
            this.smsBusiness.atualizarMensagem(this.sms);
            Assert.assertTrue(true);
        } catch (BusinessException e) {
            e.printStackTrace();
            Assert.fail();
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
