package br.com.sw2.gac.business;

import br.com.sw2.gac.dao.ParametroDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Parametro;
import br.com.sw2.gac.vo.ParametroVO;

/**
 * @author Daniel Castilho
 * Classe de negocio responsavel por acoes com parametros.
 * @author Daniel Castilho
 */
public class ParametroBusiness {
    
    private ParametroDAO dao = new ParametroDAO();

    /**
     * Adicionar parametro.
     * @param parametro VO do Parametro
     * @throws BusinessException Exception do business
     */
    public void adicionarNovoParametro(ParametroVO parametro) throws BusinessException {

        Parametro entity = vo2Entity(parametro);

        try {
            dao.gravar(entity);
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }
    
    /**
     * Converte os dados do VO parametro em uma entity a ser enviada ao DAO.
     * @param parametro o VO do parametro
     * @return Parametro entity
     */
    private Parametro vo2Entity(ParametroVO parametro) {
        
        Parametro entity = new Parametro();
        entity.setIdParametro(parametro.getIdParametro());
        entity.setDiasDados(parametro.getDiasDados());
        entity.setDiasBemEstar(parametro.getDiasBemEstar());
        entity.setToleraRotinaCliente(parametro.getToleraRotinaCliente());
        
        return entity;
    }
}
