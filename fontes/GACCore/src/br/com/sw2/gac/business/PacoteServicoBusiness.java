package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.sw2.gac.dao.PacoteServicoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.PacoteServico;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.vo.PacoteServicoVO;

/**
 * <b>Descrição: Classe de negócio do módulo de Pacotes de serviços.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PacoteServicoBusiness {

    /** Atributo pacote servico dao. */
    private PacoteServicoDAO pacoteServicoDAO = new PacoteServicoDAO();

    /**
     * Nome: adicionarNovoPacote Adicionar novo pacote.
     * @param vo the vo
     * @throws BusinessException the business exception
     * @see
     */
    public void adicionarNovoPacote(PacoteServicoVO vo) throws BusinessException {

        PacoteServico entity = ObjectUtils.parse(vo);

        try {
            PacoteServico jaExiste = this.pacoteServicoDAO
                    .getPacoteServicoByTituloDescricao(entity);

            if (null == jaExiste) {
                this.pacoteServicoDAO.gravar(entity);
            } else {
                throw new BusinessException(BusinessExceptionMessages.PACOTE_SERVICO_JA_CADASTRADO);
            }

        } catch (BusinessException e) {

            throw e;

        } catch (Exception e) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }

    /**
     * Nome: atualizarPacoteServico Atualizar um pacote servico.
     * @param pacoteServico the pacote servico
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarPacoteServico(PacoteServicoVO pacoteServico) throws BusinessException {

        Date dataAtual = DateUtil.getDataAtual();
        PacoteServico entityOriginal = this.pacoteServicoDAO.getEnityById(pacoteServico
                .getIdPacote());

        if (null != entityOriginal.getDtFinalValidade()
                && DateUtil.compareIgnoreTime(entityOriginal.getDtFinalValidade(), dataAtual) < 0) {

            throw new BusinessException(BusinessExceptionMessages.PACOTE_SERVICO_VENCIDO);

        } else {

            entityOriginal.setPrcMensal(pacoteServico.getPreco());
            entityOriginal.setDtFinalValidade(pacoteServico.getDataFinalValidade());
            if (DateUtil.compareIgnoreTime(entityOriginal.getDtInicioValidade(), dataAtual) < 0) {
                // Pacote não está vencido, porem ainda não entrou em vigencia
                entityOriginal.setDsTitulo(pacoteServico.getTitulo());
                entityOriginal.setDsServico(pacoteServico.getDescricao());
                entityOriginal.setDtInicioValidade(pacoteServico.getDataInicioValidade());
                entityOriginal.setDtFinalValidade(pacoteServico.getDataFinalValidade());
            }

            this.pacoteServicoDAO.gravar(entityOriginal);

        }

    }

    /**
     * Nome: getListaPacoteServicosValidos Retorna uma lita com todos
     * os pacotes cuja data de final da validade não expirou ou é nula.
     * @return valor do atributo 'listaPacoteServicosValidos'
     * @throws BusinessException the business exception
     * @see
     */
    public List<PacoteServicoVO> getListaPacoteServicosValidos() throws BusinessException {

        try {
            List<PacoteServicoVO> retorno = new ArrayList<PacoteServicoVO>();
            List<PacoteServico> listaEntity = this.pacoteServicoDAO.getListaPacoteServicosValidos();

            if (null != listaEntity) {
                for (PacoteServico item : listaEntity) {
                    retorno.add(ObjectUtils.parse(item));
                }
            }
            return retorno;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }

    /**
     * Nome: excluirPacoteServico Excluir um pacote servico.
     * @param filtro the filtro
     * @throws BusinessException the business exception
     * @see
     */
    public void excluirPacoteServico(PacoteServicoVO filtro) throws BusinessException {

        try {
            this.pacoteServicoDAO.apagar(filtro.getIdPacote());
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                throw new BusinessException(BusinessExceptionMessages.DELETE_PACOTE_SERVICO_EM_USO);
            }
        }
    }

}
