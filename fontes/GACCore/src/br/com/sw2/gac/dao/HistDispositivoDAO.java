package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.HistDispositivo;

/**
 * @author ddiniz
 */
public class HistDispositivoDAO extends BaseDao<HistDispositivo> {

	/**
	 * Construtor Padrao Instancia um novo objeto DispositivoDAO.
	 */
	public HistDispositivoDAO() {
		super(HistDispositivo.class);
	}

	/**
	 * MÃ©todo que apaga o HistÃ³rico de um  Dispositivo a partir de seu ID.
	 * @param id ID do dispositivo
	 * @return histDispositivo Objeto HistDispositivo
	 * @throws DataBaseException exceÃ§Ã£o
	 */
	@SuppressWarnings("unchecked")
	public List<HistDispositivo> recuperaHistDispositivoPeloId(String id) throws DataBaseException {
		List<HistDispositivo> result = new ArrayList<>();
		try {
			result = getEntityManager().createQuery("Select h from HistDispositivo h where h.tblhistdispositivoPK.idDispositivo = '"
					+ id + "'").getResultList();
		} catch (NoResultException exception) {
			result = null;
		} catch (DatabaseException exception) {
			throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
		}
		return result;
	}

	/**
     * Apagar HistDispositivo.
     * @param hist historico
     * @throws DataBaseException the data base exception
     */
    public void apagar(HistDispositivo hist) throws DataBaseException {
        try {
            getEntityManager().getTransaction().begin();
            hist = getEntityManager().merge(hist);
            getEntityManager().remove(hist);
            getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().contains("Error Code: 1451")) {
                throw new DataBaseException(DataBaseException.DELETE_VIOLACAO_CONSTRAINT, e.getMessage());
            } else {
            	throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, e.getMessage());
            }
        }
    }
}