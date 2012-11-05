package br.com.sw2.gac.business;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.dao.OcorrenciaDAO;
import br.com.sw2.gac.modelo.Ocorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.RelOcorrenciasAbertoVO;
import br.com.sw2.gac.vo.ResumoOcorrenciaVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaBusiness implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -9063997160471980539L;
    /** Atributo logger. */
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /** Atributo ocorrencia dao. */
    private OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();

    /**
     * Nome: obterOcorrenciasEmAberto Obter resumo das ocorrencias em aberto .
     * @return list
     * @see
     */
    @SuppressWarnings("unchecked")
    public RelOcorrenciasAbertoVO obterOcorrenciasEmAberto() {

        List<Ocorrencia> listEntity = this.ocorrenciaDAO.filtarOcorrenciasPorDataFechamento(null);

        List<OcorrenciaVO> listVO = new ArrayList<OcorrenciaVO>();
        for (Ocorrencia item : listEntity) {
            listVO.add(ParseUtils.parse(item));
        }

        List<OcorrenciaVO> listOcorrenciasAgrupadas = new ArrayList<OcorrenciaVO>();
        List<ResumoOcorrenciaVO> listResumo = new ArrayList<ResumoOcorrenciaVO>();
        RelOcorrenciasAbertoVO ocorrenciasEmAberto = new RelOcorrenciasAbertoVO();
        for (TipoOcorrencia item : TipoOcorrencia.values()) {

            EqualPredicate equalPredicate = new EqualPredicate(item.getValue());
            BeanPredicate predicate = new BeanPredicate("tipoOcorrencia.codigoTipoOcorrencia",
                equalPredicate);

            List<OcorrenciaVO> list = ((List<OcorrenciaVO>) CollectionUtils.select(listVO,
                predicate));
            if (CollectionUtils.isNotEmptyOrNull(list)) {
                ResumoOcorrenciaVO resumo = new ResumoOcorrenciaVO();
                resumo.setQuantidadeOcorrencias(list.size());
                resumo.setTipoOcorrencia(list.get(0).getTipoOcorrencia());
                resumo.setPorcentagem(new BigDecimal("12.25"));
                listResumo.add(resumo);
                listOcorrenciasAgrupadas.addAll(list);
            }

        }
        ocorrenciasEmAberto.setResumo(listResumo);
        ocorrenciasEmAberto.setOcorrencias(listOcorrenciasAgrupadas);

        return ocorrenciasEmAberto;
    }

}
