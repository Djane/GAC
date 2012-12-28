package br.com.sw2.gac.business;

import java.util.List;

import br.com.sw2.gac.dao.ContratoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.ContratoVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class AtendimentoBusiness extends BaseBusiness {

    /** Atributo contrato dao. */
    private ContratoDAO contratoDAO = new ContratoDAO();

    /**
     * Nome: pesquisarContratosPreAtendimento Pesquisar contratos pre atendimento.
     * @param filtro the filtro
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<ContratoVO> pesquisarContratosPreAtendimento(FiltroPesquisarPreAtendimento filtro)
        throws BusinessException {

        if (null == filtro) {
            throw new BusinessException(BusinessExceptionMessages.FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO);
        } else {

            if (null == filtro.getNumeroContrato()
                && StringUtil.isEmpty(filtro.getNomeCliente(), true)
                && StringUtil.isEmpty(filtro.getTelefone(), true)
                && StringUtil.isEmpty(filtro.getNumeroCPFCliente(), true)) {
                throw new BusinessException(BusinessExceptionMessages.FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO);
            }

            List<Contrato> listEntity = this.contratoDAO.getListaContratos(filtro);

            return ParseUtils.parseContratoEntityList(listEntity);
        }

    }

}
