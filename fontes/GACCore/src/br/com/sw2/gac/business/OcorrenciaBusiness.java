package br.com.sw2.gac.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.dao.OcorrenciaDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.StatusOcorrenciaException;
import br.com.sw2.gac.modelo.Ocorrencia;
import br.com.sw2.gac.tools.StatusOcorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.RelChamadasPorOrigemVO;
import br.com.sw2.gac.vo.RelOcorrenciasAbertoVO;
import br.com.sw2.gac.vo.ResumoOcorrenciaVO;
import br.com.sw2.mock.DataSourceChamadasOrigem;

/**
 * <b>Descrição: Classe de negócio para operações relacionadas com os atendimentos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaBusiness extends BaseBusiness implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -9063997160471980539L;

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
                resumo.setPorcentagem(super.calcularPorcentagem(list.size(), listVO.size()));
                listResumo.add(resumo);
                listOcorrenciasAgrupadas.addAll(list);
            }

        }
        ocorrenciasEmAberto.setResumo(listResumo);
        ocorrenciasEmAberto.setOcorrencias(listOcorrenciasAgrupadas);

        return ocorrenciasEmAberto;
    }

    /**
     * Retorna uma lista com o histórico de dispositivos agrupado por dispositivo.
     *
     * @param relatorio VO do relatorio
     * @return list RelHistDispositivoVO
     * @throws BusinessException the business exception
     * @see
     */
    public List<RelChamadasPorOrigemVO> recuperaChamadasPorOrigem(RelChamadasPorOrigemVO relatorio) throws BusinessException {
        Date periodoInicial = relatorio.getPerInicio();
        Date periodoFinal = relatorio.getPerFim();

        List<RelChamadasPorOrigemVO> listaRetorno = new ArrayList<RelChamadasPorOrigemVO>();
        // Pelo menos um dos campos da tela deve estar preenchido
        if ((periodoInicial == null) || (periodoFinal == null)) {
            throw new BusinessException(
                BusinessExceptionMessages.PARAMETRO_OBRIGATORIO_RELATORIO_CHAMADAS_ORIGEM);
        } else {
            listaRetorno.addAll((List<RelChamadasPorOrigemVO>) DataSourceChamadasOrigem.createBeanCollection());
        }

        /*
        List<Object[]> lista = this.ocorrenciaDAO.recuperaRelChamadasOrigem(periodoInicial, periodoFinal);

        List<RelChamadasPorOrigemVO> listaRelatorios = new ArrayList<RelChamadasPorOrigemVO>();
        for (Object[] item : lista) {
            RelChamadasPorOrigemVO relChamadasPorOrigem = new RelChamadasPorOrigemVO();
            int coluna = 0;
            relHistChamadasPorOrigem.set.setIdDispositivo((String) item[coluna++]);
            relHistChamadasPorOrigem.setDataMovimentacao((Date) item[coluna++]);
            relHistChamadasPorOrigem estado = EstadoDispositivo.getEstadoPeloValor((Integer) item[coluna++]);
            relHistChamadasPorOrigem.setEstadoAtual(estado.getLabel());
            relHistChamadasPorOrigem = EstadoDispositivo.getEstadoPeloValor((Integer) item[coluna++]);
            relHistChamadasPorOrigem.setEstadoOrigem(estado.getLabel());
            relHistChamadasPorOrigem.setLogin((String) item[coluna]);

            listaRelatorios.add(relHistDispositivo);
        }*/

        return listaRetorno;
    }

    /**
     * Nome: inserirNaFilaAtendimento
     * Insere um novo item an fila de atendimento.
     *
     * @param ocorrencia the ocorrencia
     * @return integer
     * @throws BusinessException the business exception
     * @see
     */
    public Integer gravarNovaOcorrencia(OcorrenciaVO ocorrencia) throws BusinessException {
        Ocorrencia entity = ParseUtils.parse(ocorrencia);

        try {
            this.ocorrenciaDAO.inserir(entity);
        } catch (BusinessException e) {
            throw new BusinessException(e);
        }

        return entity.getIdOcorrencia();
    }

    /**
     * Nome: salvarDadosOcorrenciaEmAtendimento
     * Salvar dados ocorrencia em atendimento.
     *
     * @param ocorrencia the ocorrencia
     * @throws BusinessException the business exception
     * @see
     */
    public void salvarDadosOcorrenciaEmAtendimento(OcorrenciaVO ocorrencia)
        throws BusinessException {
        Ocorrencia entity = ParseUtils.parse(ocorrencia);

        if (ocorrencia.getStatusOcorrencia().intValue() != StatusOcorrencia.Fechado.getValue()
            .intValue()
            || ocorrencia.getStatusOcorrencia().intValue() != StatusOcorrencia.EmEspera.getValue()
                .intValue()) {
            throw new StatusOcorrenciaException("O status informado não é valido para gravação da ocorrencia !");
        } else {

            try {
                this.ocorrenciaDAO.gravar(entity);
            } catch (Exception e) {
                throw new BusinessException(e.getMessage());
            }
        }

    }

}
