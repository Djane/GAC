package br.com.sw2.test.contratos;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.vo.RelContratosAVencerVO;

/**
 * <b>Descrição: Classe de teste para a consulta do relatório de dispositivos por estado.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class RelatorioContratosAVencerTest {

    private static final int TRINTA_DIAS = 30;

	/** Atributo dispositivo business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

    /**
     * Nome: obterListaDispositivosPorEstado Obter lista dispositivos por estado.
     * @see
     */
    @Test
    public void obterListaDispositivosPorEstado() {

		List<RelContratosAVencerVO> lista = this.contratoBusiness
				.recuperarContratosAtivosAVencerEm(TRINTA_DIAS);
		Assert.assertTrue(lista.size() == 0);

    }

}
