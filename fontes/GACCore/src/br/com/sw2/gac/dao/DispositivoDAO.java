package br.com.sw2.gac.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.util.StringUtil;

/**
 * <b>Descrição: Classe responsável pela manipuação dos dados de dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
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
	 * @param id
	 *            ID do dispositivo
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
			throw new DataBaseException(
					DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
		}
		return result;
	}

	/**
	 * Método que recupera a lista com todos os dispositivos cadastrados.
	 * @return Lista de dispositivos
	 * @throws DataBaseException Exceção de banco
	 */
	@SuppressWarnings("unchecked")
	public List<Dispositivo> recuperaListaDispositivos() throws DataBaseException {
		List<Dispositivo> listaDispositivos = new ArrayList<Dispositivo>();
		try {
			listaDispositivos = getEntityManager().createQuery("select d from Dispositivo d").getResultList();
		} catch (DataBaseException exception) {
			throw new DataBaseException(
					DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
		}
		return listaDispositivos;
	}

	/**
	 * Método que recupera uma lista de Dispositivos(Pulseiras e Central) a
	 * partir de seu estado.
	 * @param estado Estado do dispositivo
	 * @return List<Dispositivo> lista de Dispositivos
	 * @throws DataBaseException
	 *             exceção
	 */
	@SuppressWarnings("unchecked")
	public List<Dispositivo> recuperaPulseiraECentralPeloEstado(Integer estado) throws DataBaseException {
		List<Dispositivo> result;
		try {
			Query createQuery = getEntityManager().createQuery(
					"SELECT d FROM Dispositivo d WHERE d.tpEstado = " + estado
							+ " AND d.tpDispositivo in ("
							+ TipoDispositivo.Pulseira.getValue() + ", "
							+ TipoDispositivo.CentralEletronica.getValue()
							+ ")");
			result = createQuery.getResultList();
		} catch (NoResultException exception) {
			result = null;
		} catch (DatabaseException exception) {
			throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
		}
		return result;
	}

	/**
	 * Nome: recuperaDispositivosPorEstado Retorna o quantidade de dispositivos
	 * agrupados por tipo e estado do dispositivo.
	 * @return list
	 * @throws DataBaseException
	 *             the data base exception
	 * @see
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> recuperaQtdeDispositivosPorEstado() throws DataBaseException {
		List<Object[]> result;
		try {

			// Deixar o sql nativo ate descobrir porque o ElipseLink recusa a
			// subquery no jpaql e também criteria.
			StringBuffer strSQL = new StringBuffer();
			strSQL.append("SELECT d.tpDispositivo, d.tpEstado, count(d.tpEstado), ");
			strSQL.append("(select count(sub.tpEstado) from tbldispositivo sub WHERE d.tpDispositivo = sub.tpDispositivo) ");
			strSQL.append("FROM tbldispositivo d ");
			strSQL.append("GROUP BY tpDispositivo, tpEstado ");
			Query createQuery = getEntityManager().createNativeQuery(strSQL.toString());
			result = createQuery.getResultList();
		} catch (NoResultException exception) {
			result = null;
		} catch (DatabaseException exception) {
			throw new DataBaseException(
					DataBaseException.FALHA_COMUNICACAO_BANCO,
					exception.getMessage());
		}
		return result;
	}

	/**
	 * Método que recupera a lista de hitórico de dispositivo, baseado no critério de busca
	 * definido pelos parâmetros passados no relatório Histórico de Dispositivos.
	 * @param id
	 *            ID do dispositivo
	 * @param estadoAtual
	 *            estado atual do dispositivo
	 * @param dataMovimentacao
	 *            data de movimentação
	 * @param login
	 *            cpf do cliente
	 * @return lista do histórico de dispositivos
	 * @throws DataBaseException exception
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> recuperaRelHistDispositivo(String id, Integer estadoAtual, Date dataMovimentacao, String login) throws DataBaseException {
		List<Object[]> result;
		StringBuffer query = new StringBuffer();
		query.append("SELECT h.dispositivo.idDispositivo, h.tblhistdispositivoPK.dthrMudaEstado, h.dispositivo.tpEstado,");
		query.append(" h.cdEstadoAnterior, h.login FROM HistDispositivo h WHERE 1=1");

		if (id != null && !id.isEmpty()) {
			query.append(" AND h.dispositivo.idDispositivo = '" + id + "'");
		}

		if (estadoAtual != null && estadoAtual != 0) {
			query.append(" AND h.dispositivo.tpEstado = " + estadoAtual);
		}

		if (dataMovimentacao != null) {
			// Somar 1 dia na data selecionada para poder montar a query
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(dataMovimentacao);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			Date dataFim = calendar.getTime();

			// Converter as datas para Timestamp, pois está assim no banco
			Timestamp timeFim = new Timestamp(dataFim.getTime());
			Timestamp timeInicio = new Timestamp(dataMovimentacao.getTime());

			query.append(" AND h.tblhistdispositivoPK.dthrMudaEstado BETWEEN '" + timeInicio + "' AND '" + timeFim + "'");
		}

		if (login != null && !login.isEmpty()) {
			query.append(" AND h.login = '" + login + "'");
		}

		try {
			Query createQuery = getEntityManager().createQuery(query.toString());
			result = createQuery.getResultList();
		} catch (NoResultException exception) {
			result = null;
		} catch (DatabaseException exception) {
			throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
		}
		return result;
	}

    /**
     * Nome: recuperaPulseirasSelecionaveis Recupera uma lista com as pulseiras/relogios disponiveis
     * para uso em novos contratos.
     * @param filtro the filtro
     * @return valor do atributo 'listaPulseirasSelecionaveis'
     * @throws DataBaseException the data base exception
     * @see
     */
    @SuppressWarnings("unchecked")
	public List<Dispositivo> recuperaDispositivosSelecionaveis(String filtro)
        throws DataBaseException {

        StringBuffer statementJPA = new StringBuffer();
        statementJPA.append(" SELECT d FROM Dispositivo d");
        statementJPA.append(" WHERE d.tpDispositivo = ");
        statementJPA.append(TipoDispositivo.Pulseira.getValue());
        statementJPA.append(" AND d.tpEstado = ");
        statementJPA.append(EstadoDispositivo.Pronto.getValue());
        statementJPA.append(" AND (d.local in  (");
        statementJPA.append(LocalizacaoDispositivo.EstoqueExterno.getValue());
        statementJPA.append(",");
        statementJPA.append(LocalizacaoDispositivo.EstoqueInterno.getValue());
        statementJPA.append(",");
        statementJPA.append(LocalizacaoDispositivo.Transito.getValue());
        statementJPA.append(") or d.local is null)");
        if (!StringUtil.isVazio(filtro, true)) {
            statementJPA.append(" AND d.idDispositivo LIKE '%");
            statementJPA.append(filtro.trim());
            statementJPA.append("%'");
        }

        List<Dispositivo> retorno = null;
        try {
            Query query = getEntityManager().createQuery(statementJPA.toString());
            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: recuperaCentraisSelecionaveis Recupera a lista de centrais disponiveis para novos
     * contratos.
     * @param filtro the filtro
     * @return valor do atributo 'listaCentraisSelecionaveis'
     * @throws DataBaseException the data base exception
     * @see
     */
    @SuppressWarnings("unchecked")
	public List<Dispositivo> recuperaCentraisSelecionaveis(String filtro) throws DataBaseException {

        StringBuffer statementJPA = new StringBuffer();
        statementJPA.append(" SELECT d FROM Dispositivo d");
        statementJPA.append(" WHERE d.tpDispositivo = ");
        statementJPA.append(TipoDispositivo.CentralEletronica.getValue());
        statementJPA.append(" AND d.tpEstado = ");
        statementJPA.append(EstadoDispositivo.Pronto.getValue());
        statementJPA.append(" AND (d.local in  (");
        statementJPA.append(LocalizacaoDispositivo.EstoqueExterno.getValue());
        statementJPA.append(",");
        statementJPA.append(LocalizacaoDispositivo.EstoqueInterno.getValue());
        statementJPA.append(",");
        statementJPA.append(LocalizacaoDispositivo.Transito.getValue());
        statementJPA.append(") or d.local is null)");
        if (!StringUtil.isVazio(filtro, true)) {
            statementJPA.append(" AND d.idDispositivo LIKE '%");
            statementJPA.append(filtro.trim());
            statementJPA.append("%'");
        }

        List<Dispositivo> retorno = null;
        try {
            Query query = getEntityManager().createQuery(statementJPA.toString());
            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }
}