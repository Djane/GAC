package br.com.sw2.gac.business;

import java.util.Date;

import br.com.sw2.gac.dao.ParametroDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Parametro;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.vo.ParametroVO;

/**
 * <b>Descrição: Classe responsável pela manipulação de informações sobre parametros.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ParametroBusiness {

    /** Atributo dao. */
    private ParametroDAO parametroDao = new ParametroDAO();

    /**
     * Adicionar parametro.
     * @param parametro VO do Parametro
     * @throws BusinessException Exception do business
     * @see
     */
    public void adicionarNovoParametro(ParametroVO parametro) throws BusinessException {
        Parametro entity = ParseUtils.parse(parametro);
        try {
            entity.setDataUltimaAlteracao(new Date());
            parametroDao.gravar(entity);
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }

    /**
     * Nome: recuperarParametros Recuperar parametros.
     * @return parametro vo
     * @see
     */
    public ParametroVO recuperarParametros() {
        ParametroVO retorno = null;
        try {
            Parametro entity = this.parametroDao.getParametros();
            if (null != entity) {
                retorno = ParseUtils.parse(entity);
            }
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
        return retorno;
    }

    /**
     * Recupera a quantidade de dias do período de atualização.
     * @return Integer dias
     * @see
     */
    public Integer recuperaPeriodoAtualizacao() {
        // Recupera os dias do período de atualização do banco.
        Integer dias = parametroDao.recuperaPeriodoAtualizacao();
        return dias;
    }
}
