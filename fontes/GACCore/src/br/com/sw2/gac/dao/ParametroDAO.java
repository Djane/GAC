package br.com.sw2.gac.dao;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Parametro;

/**
 * @author Daniel Castilho
 */
public class ParametroDAO extends BaseDao<Parametro> {

    /**
     * Construtor Padrao Instancia um novo objeto ParametroDAO.
     */
    public ParametroDAO() {
        super(Parametro.class);
    }

    /**
     * Metodo que recupera a quantidade de dias do perído de atualização.
     * @return dias do perído de atualização
     * @throws DataBaseException Excecao de banco
     */
    public Integer recuperaPeriodoAtualizacao() throws DataBaseException {
        Integer dias;
        try {
            dias = (Integer) getEntityManager().createQuery("SELECT p.diasDados FROM Parametro p").getSingleResult();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return dias;
    }
}
