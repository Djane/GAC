package br.com.sw2.gac.dao;

import javax.persistence.NoResultException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Parametro;

/**
 * <b>Descrição: Classe responsável pelo acesso a base de dados de paraâmetros</b> <br>
 * .
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
     * Nome: getParametros Recupera o valor do atributo 'parametro'.
     * @return valor do atributo 'parametro'
     * @see
     */
    public Parametro getParametros() {

        Parametro result;
        try {
            result = (Parametro) getEntityManager().createQuery("SELECT p FROM Parametro p")
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return result;
    }

    /**
     * Metodo que recupera a quantidade de dias do perído de atualização.
     * @return dias do perído de atualização
     * @throws DataBaseException Excecao de banco
     * @see
     */
    public Integer recuperaPeriodoAtualizacao() throws DataBaseException {
        Integer dias;
        try {
            dias = (Integer) getEntityManager().createQuery("SELECT p.diasDados FROM Parametro p")
                    .getSingleResult();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return dias;
    }
}
