package br.com.sw2.test.dispositivo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.vo.DispositivoEstadoVO;

/**
 * <b>Descrição: Classe de teste para a consulta do relatório de dispositivos por estado.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class RelatorioDispositivoEstadoTest {

    /** Atributo dispositivo business. */
    private DispositivoBusiness dispositivoBusiness = new DispositivoBusiness();

    /**
     * Nome: obterListaDispositivosPorEstado Obter lista dispositivos por estado.
     * @see
     */
    @Test
    public void obterListaDispositivosPorEstado() {

        List<DispositivoEstadoVO> lista = this.dispositivoBusiness
                .recuperaQtdeDispositivosPorEstado();
        Assert.assertTrue(lista.size() > 0);

    }

}
