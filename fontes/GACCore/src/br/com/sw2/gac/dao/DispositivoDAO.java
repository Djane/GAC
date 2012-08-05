package br.com.sw2.gac.dao;

import javax.persistence.NoResultException;
import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;

/**
 * @author ddiniz
 *
 */
public class DispositivoDAO extends BaseDao<Dispositivo> {

	/**
	 * Construtor Padrao Instancia um novo objeto DispositivoDAO.
	 */
	public DispositivoDAO() {
		super(Dispositivo.class);
	}

	/**
	 * Método que recupera o Dispositivo a partir de seu ID.
	 * @param id ID do dispositivo
	 * @return dispositivo Dispositivo
	 * @throws DataBaseException exceção
	 */
	public Dispositivo recuperaDispositivoPeloId(String id) throws DataBaseException {
		Dispositivo result;
		try {
			result = getEntityManager().find(Dispositivo.class, id);
		} catch (NoResultException exception) {
			result = null;
		} catch (DatabaseException exception) {
			throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
		}
		return result;
	}

}