package br.com.sw2.test.pacoteservico;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.PacoteServicoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.MesesDoAno;
import br.com.sw2.gac.vo.PacoteServicoVO;

/**
 * <b>Descrição: Casos e teste para o CRUD de Pacotes de serviço.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PacoteServicoTest {

    /** Atributo pacote servico business. */
    private PacoteServicoBusiness pacoteServicoBusiness = new PacoteServicoBusiness();

    /** Atributo ps. */
    private PacoteServicoVO ps = new PacoteServicoVO();

    /**
     * Nome: setup Setup.
     * @see
     */
    @Before
    public void setup() {
        ps = new PacoteServicoVO();
        ps.setTitulo("Titulo PS Teste JUNIT @123_");
        ps.setDescricao("Pacote 1 Teste JUNIT ");
        ps.setDataInicioValidade(new Date());
        ps.setDataFinalValidade(DateUtil.getDate(new GregorianCalendar().get(Calendar.YEAR),
                MesesDoAno.Dezembro.getValue(), 1));
        ps.setPreco(new BigDecimal(0));
    }

    /**
     * Nome: testCrud Test crud.
     * @see
     */
    @Test
    public void testCrud() {
        this.adicionarNovoPacote();
        this.pesquisarListaPacotesValidos();
        this.atualizarPacoteServico();
        this.apagarPacoteServico();
    }

    /**
     * Nome: adicionarNovoPacote Adicionar novo pacote.
     * @see
     */
    private void adicionarNovoPacote() {
        try {
            // Adiciona a 1º vez
            this.pacoteServicoBusiness.adicionarNovoPacote(ps);

        } catch (BusinessException e) {
            e.printStackTrace();
            Assert.fail();
        }

        try {
            // Adiciona a 2º vez
            this.pacoteServicoBusiness.adicionarNovoPacote(this.ps);
            Assert.fail();
        } catch (BusinessException e) {
            if (e.getExceptionCode() == BusinessExceptionMessages.PACOTE_SERVICO_JA_CADASTRADO
                    .getValue().intValue()) {
                Assert.assertTrue(true);
            } else {
                e.printStackTrace();
                Assert.fail();
            }
        }

    }

    /**
     * Nome: pesquisarListaPacotesValisos Pesquisar lista pacotes validos.
     * @see
     */
    private void pesquisarListaPacotesValidos() {
        try {

            List<PacoteServicoVO> list = this.pacoteServicoBusiness
                    .getListaPacoteServicosValidos();

            for (PacoteServicoVO item : list) {
                if (item.getTitulo().equals(this.ps.getTitulo())) {
                    this.ps.setIdPacote(item.getIdPacote());
                }
            }

            Assert.assertTrue(list.size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * Nome: atualizarMensagem Atualizar mensagem.
     * @see
     */
    private void atualizarPacoteServico() {

        try {

            this.ps.setDescricao("Alterada");
            this.pacoteServicoBusiness.atualizarPacoteServico(this.ps);
            Assert.assertTrue(true);
        } catch (BusinessException e) {
            e.printStackTrace();
            Assert.fail();
        }

    }

    /**
     * Nome: apagarPacoteServico
     * Apagar pacote servico.
     *
     * @see
     */
    public void apagarPacoteServico() {
        try {
            this.pacoteServicoBusiness.excluirPacoteServico(this.ps);
            Assert.assertTrue(true);
        } catch (BusinessException exception) {
            exception.printStackTrace();
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                Assert.assertTrue(true);
            } else {
                Assert.fail();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail();
        }
    }

}
