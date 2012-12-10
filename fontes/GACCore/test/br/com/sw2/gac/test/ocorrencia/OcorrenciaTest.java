package br.com.sw2.gac.test.ocorrencia;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.OcorrenciaBusiness;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.ScriptVO;
import br.com.sw2.gac.vo.TipoOcorrenciaVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaTest {

    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /** Atributo ocorrencia. */
    private OcorrenciaVO ocorrencia;

    /** Atributo ocorrencia business. */
    private OcorrenciaBusiness ocorrenciaBusiness = new OcorrenciaBusiness();

    /**
     * Nome: setup Setup.
     * @see
     */
    @Before
    public void setup() {
        this.ocorrencia = new OcorrenciaVO();
        this.ocorrencia.setNumerorTelefoneLigado("1144443333");
        this.ocorrencia.setSnDispositivo(1);
        this.ocorrencia.setDataAbertura(new Date());
        this.ocorrencia.setCodigoPrioridade(1);
        this.ocorrencia.setTipoOcorrencia(new TipoOcorrenciaVO(TipoOcorrencia.Emergencia));
        this.ocorrencia.setScript(new ScriptVO());
        this.ocorrencia.getScript().setIdScript(1);
        this.ocorrencia.setUsuario(new UsuarioVO());
        this.ocorrencia.getUsuario().setLogin("admin");
        final Integer idLigacao = 123456;
        this.ocorrencia.setIdLigacao(idLigacao);
    }

    /**
     * Nome: usuarioExiste Usuario existe.
     * @see
     */
    @Test
    public void inserirRegistroNaFila() {
        this.logger.debug(ocorrenciaBusiness.inserirNaFilaAtendimento(ocorrencia));
    }
}
