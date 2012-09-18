package br.com.sw2.test.contratos;

import org.junit.Assert;
import org.junit.Test;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.MesesDoAno;
import br.com.sw2.gac.vo.DesempenhoComercialVO;

/**
 * <b>Descrição: Classe de teste do método de geração de dados do relatorio de desempenho comercial;</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DesempenhoComercialTest {

    /** Atributo contrato business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

    /**
     * Nome: obterDadosDesempenhoComercial Obter dados desempenho comercial.
     * @see
     */
    @Test
    public void obterDadosDesempenhoComercial() {

        DesempenhoComercialVO desempenhocomercial = this.contratoBusiness
                .obterDadosDesempenhoComercial(DateUtil.getDate(DateUtil.getAnoAtual(), MesesDoAno.Agosto.getValue(), 01));

        Assert.assertNotNull(desempenhocomercial);
    }

}
