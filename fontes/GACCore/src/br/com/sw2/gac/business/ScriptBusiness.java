package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.dao.ScriptDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Script;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.vo.ScriptVO;

/**
 * <b>Descri��o: Classe de neg�cio respons�vel por a��es com os dados de script de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ScriptBusiness {

    /** Atributo dao. */
    private ScriptDAO dao = new ScriptDAO();

    /**
     * Nome: obterListaDeScripts Obter lista de scripts de atendimento.
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<ScriptVO> obterListaScripts() throws BusinessException {
        List<ScriptVO> listaVO = new ArrayList<ScriptVO>();
        try {
            List<Script> listaEntity = this.dao.findAll();

            if (!listaEntity.isEmpty()) {

                for (Script entity : listaEntity) {
                    ScriptVO script = ParseUtils.parse(entity);
                    listaVO.add(script);
                }

            }

        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }

        return listaVO;
    }

    /**
     * Nome: salvarScript Adicionar novor script de atendimento.
     * @param script the script
     * @throws BusinessException the business exception
     * @see
     */

    public void salvarScript(ScriptVO script) throws BusinessException {

        Script entity = ParseUtils.parse(script);
        try {
            dao.gravar(entity);
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }

    /**
     * Nome: atualizarScript Adiciona ou altera um script de atendimento na base de dados.
     * @param script the script
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarScript(ScriptVO script) throws BusinessException {
        Script entity = ParseUtils.parse(script);
        dao.gravar(entity);
    }

    /**
     * Nome: apagarScript Apagar script.
     * @param idScript the idScript
     * @see
     */
    public void apagarScript(Integer idScript) {
        try {
            dao.apagar(idScript);
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                throw new BusinessException(BusinessExceptionMessages.DELETE_SCRIPT_EM_USO);
            }
        }
    }

}
