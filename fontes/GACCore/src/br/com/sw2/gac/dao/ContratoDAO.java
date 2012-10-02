package br.com.sw2.gac.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.ClienteDispositivo;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.FormaComunica;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.util.StringUtil;

/**
 * <b>Descrição: Classe responsável pela manipuação dos dados de </b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoDAO extends BaseDao<Contrato> {

    /**
     * Construtor Padrao Instancia um novo objeto ContratoDAO.
     */
    public ContratoDAO() {
        super(Contrato.class);
    }

    /**
     * Nome: filtarContratosPorCPFContratante Filtar contratos por cpf contratante.
     * @param cpf the cpf
     * @return list
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Contrato> filtarContratosPorCPFContratante(String cpf) throws DataBaseException {
        List<Contrato> retorno = null;
        try {
            retorno = super.filterByField("nmCPFContratante", cpf);
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: filtarContratosPorNomeContratante Filtar contratos por nome contratante.
     * @param nomeContratante the nome contratante
     * @return list
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Contrato> filtarContratosPorNomeContratante(String nomeContratante)
        throws DataBaseException {
        List<Contrato> retorno = null;
        try {

            StringBuffer statementJPA = new StringBuffer("SELECT c from Contrato c ");
            statementJPA.append(" WHERE c.nmNomeContratante like :nmNomeContratante");
            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("nmNomeContratante", "%" + nomeContratante + "%");

            retorno = query.getResultList();

        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: getNovosContratosPeriodo Recupera a quantidade de contratos novos, agrupados por dia em
     * um determinado periodo.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @return valor do atributo 'novosContratosPeriodo'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Object[]> getNovosContratosPeriodo(Date inicioPeriodo, Date fimPeriodo)
        throws DataBaseException {

        List<Object[]> retorno = null;
        try {

            StringBuffer statementJPA = new StringBuffer(
                "SELECT count(c.nmContrato) as qtdeContratos, c.dtInicioValidade from Contrato c ");
            statementJPA.append(" WHERE c.dtInicioValidade >= :dtInicio");
            statementJPA.append(" AND c.dtInicioValidade <= :dtFinal");
            statementJPA.append(" GROUP BY c.dtInicioValidade");

            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("dtInicio", inicioPeriodo);
            query.setParameter("dtFinal", fimPeriodo);

            retorno = query.getResultList();

        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: getContratosCanceladosPeriodo Recupera a quantidade de contratos cancelados, agrupados
     * por dia em um determinado periodo.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @return valor do atributo 'novosContratosPeriodo'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Object[]> getContratosCanceladosPeriodo(Date inicioPeriodo, Date fimPeriodo)
        throws DataBaseException {

        List<Object[]> retorno = null;
        try {

            StringBuffer statementJPA = new StringBuffer(
                "SELECT count(c.nmContrato) as qtdeContratos, c.dtFinalValidade from Contrato c ");
            statementJPA.append(" WHERE c.dtFinalValidade >= :dtInicio");
            statementJPA.append(" AND c.dtFinalValidade <= :dtFinal");
            statementJPA.append(" GROUP BY c.dtFinalValidade");

            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("dtInicio", inicioPeriodo);
            query.setParameter("dtFinal", fimPeriodo);

            retorno = query.getResultList();

        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: getNovosContratosPeriodo Recupera a quantidade de contratos novos, agrupados por dia em
     * um determinado periodo.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @return valor do atributo 'novosContratosPeriodo'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Object[]> getContratosSuspensosPeriodo(Date inicioPeriodo, Date fimPeriodo)
        throws DataBaseException {

        List<Object[]> retorno = null;
        try {

            StringBuffer statementJPA = new StringBuffer(
                "SELECT count(c.nmContrato) as qtdeContratos, c.dtSuspensao from Contrato c ");
            statementJPA.append(" WHERE c.dtSuspensao >= :dtInicio");
            statementJPA.append(" AND c.dtSuspensao <= :dtFinal");
            statementJPA.append(" GROUP BY c.dtSuspensao");

            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("dtInicio", inicioPeriodo);
            query.setParameter("dtFinal", fimPeriodo);

            retorno = query.getResultList();

        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: getListaContratosAtivosInicioMes Recupera a quantidade de contratos ativos no inicio do
     * mês informado.
     * @param dataInicioMes the data inicio mes
     * @return valor do atributo 'listaContratosAtivosInicioMes'
     * @see
     */
    public Integer getListaContratosAtivosInicioMes(Date dataInicioMes) {

        Integer retorno = null;
        try {

            StringBuffer statementJPA = new StringBuffer(
                "SELECT count(c.nmContrato) as qtdeContratos FROM Contrato c ");
            statementJPA.append(" WHERE c.dtInicioValidade < :dtReferencia ");
            statementJPA
                .append(" AND (c.dtFinalValidade is null or c.dtFinalValidade >= :dtReferencia )");
            statementJPA.append(" AND (c.dtSuspensao is null or c.dtSuspensao >= :dtReferencia )");

            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("dtReferencia", dataInicioMes);

            Long count = (Long) query.getSingleResult();

            retorno = count.intValue();

        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;

    }

    /**
     * Nome: getListaContratosAtivosPorPacote Recupera o valor do atributo
     * 'listaContratosAtivosPorPacote'.
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @return valor do atributo 'listaContratosAtivosPorPacote'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Object[]> getListaContratosAtivosPorPacote(Date inicioPeriodo, Date fimPeriodo)
        throws DataBaseException {

        StringBuffer statementJPA = new StringBuffer();
        statementJPA.append(" SELECT count(c.nmContrato) as total, c.idServico.dsTitulo ");
        statementJPA.append(" FROM Contrato c ");
        statementJPA.append(" WHERE  ");
        statementJPA
            .append(" c.dtInicioValidade >= :inicioPeriodo AND c.dtInicioValidade <= :fimPeriodo ");
        statementJPA
            .append(" AND (c.dtFinalValidade is null OR c.dtFinalValidade >= :fimPeriodo) ");
        statementJPA.append(" AND (c.dtSuspensao is null OR c.dtSuspensao >= :fimPeriodo) ");
        statementJPA.append(" GROUP BY c.idServico ");

        List<Object[]> retorno = null;
        try {
            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("inicioPeriodo", inicioPeriodo);
            query.setParameter("fimPeriodo", fimPeriodo);
            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: insertContrato Insere um contrato.
     * @param entity the Entity
     * @throws DataBaseException the data base exception
     * @see
     */
    public void gravarNovoContrato(Contrato entity) throws DataBaseException {

        Contrato contrato = entity;
        this.getEntityManager().getTransaction().begin();
        this.getEntityManager().persist(contrato);
        this.getEntityManager().flush();
        for (Cliente cliente : contrato.getClienteList()) {
            cliente.setNmContrato(contrato);
            this.getEntityManager().persist(cliente);
            this.getEntityManager().flush();
            // Formas de comunicação do cliente;
            for (FormaComunica formaComunica : cliente.getFormaComunicaList()) {
                if (!StringUtil.isVazio(formaComunica.getFoneContato(), true)) {
                    formaComunica.setFoneContato(formaComunica.getFoneContato().replace("-", "")
                        .replace("(", "").replace(")", ""));
                }
                this.getEntityManager().persist(formaComunica);
                this.getEntityManager().flush();
            }

            EqualPredicate equalPredicate = new EqualPredicate(
                TipoDispositivo.CentralEletronica.getValue());
            BeanPredicate beanPredicate = new BeanPredicate("dispositivo.tpDispositivo",
                equalPredicate);
            List<ClienteDispositivo> listaCentrais = (List<ClienteDispositivo>) CollectionUtils
                .select(cliente.getClienteDispositivoList(), beanPredicate);
            List<ClienteDispositivo> listaDispositivos = (List<ClienteDispositivo>) CollectionUtils
                .selectRejected(cliente.getClienteDispositivoList(), beanPredicate);

            for (ClienteDispositivo itemCentral : listaCentrais) {
                // Grava as centrais do cliente e ja verifica a quantidade de itens.
                this.getEntityManager().persist(itemCentral);
                this.getEntityManager().flush();

                List<Cliente> clientesNaCentral = this.getListaClientesPorCentral(itemCentral
                    .getDispositivo().getIdDispositivo());

                int numDispositivo = clientesNaCentral.size();
                if (numDispositivo < ContratoBusiness.LIMITE_DISPOSITIVOS_POR_CENTRAL) {
                    // Dispositivos do cliente;
                    for (ClienteDispositivo dispositivo : listaDispositivos) {
                        numDispositivo++;
                        dispositivo.setNumDispositivo(numDispositivo);
                        this.getEntityManager().persist(dispositivo);
                        this.getEntityManager().flush();
                    }
                }

            }
        }
        this.getEntityManager().getTransaction().commit();
    }

    /**
     * Nome: getListaCentraisPorEndereco Obtem uma lista com todas as centrais existentes em um
     * endereço.
     * @param entity the entity
     * @return valor do atributo 'listaCentraisPorEndereco'
     * @see
     */
    public List<ClienteDispositivo> getListaCentraisPorEndereco(Cliente entity) {
        List<ClienteDispositivo> retorno = null;
        try {
            StringBuffer statementJPA = new StringBuffer(
                "SELECT a FROM ClienteDispositivo a, Cliente b WHERE ");
            statementJPA.append("a.clienteDispositivoPK.nmCPFCliente = b.nmCPFCliente");
            statementJPA.append(" AND a.numDispositivo is null");
            statementJPA.append(" AND b.dsEndereco = :dsEndereco");
            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("dsEndereco", entity.getDsEndereco());
            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: getListaCentraisPorEndereco Recupera o valor do atributo 'listaCentraisPorEndereco'.
     * @param idDispositivo the id dispositivo
     * @return valor do atributo 'listaCentraisPorEndereco'
     * @see
     */
    public List<Cliente> getListaClientesPorCentral(String idDispositivo) {
        List<Cliente> retorno = null;
        try {
            StringBuffer statementJPA = new StringBuffer(
                "SELECT b FROM ClienteDispositivo a, Cliente b WHERE ");
            statementJPA.append("a.clienteDispositivoPK.nmCPFCliente = b.nmCPFCliente");
            statementJPA.append(" AND a.numDispositivo is null");
            statementJPA.append(" AND a.clienteDispositivoPK.idDispositivo = :idDispositivo ");

            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("idDispositivo", idDispositivo);
            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }
    
    /**
     * Lista de Contratos Ativos pelo periodo.
     * @param diasAVencer = dias a vencer
     * @return List
     * @throws DataBaseException  the data base exception
     */
	public List<Object[]> recuperarContratosAtivosAVencerEm(Integer diasAVencer)
		throws DataBaseException {

		StringBuffer statementJPA = new StringBuffer();
		statementJPA.append(" SELECT c ");
		statementJPA.append(" FROM Contrato c ");
		statementJPA.append(" WHERE 1=1 ");
		statementJPA
				.append(" AND c.dtInicioValidade >= :inicioPeriodo AND c.dtInicioValidade <= :fimPeriodo ");
		statementJPA
				.append(" AND (c.dtFinalValidade >= :inicioPeriodo) ");
		statementJPA
				.append(" AND (c.dtSuspensao is null OR c.dtSuspensao >= :inicioPeriodo) ");
		//ate o momento pega os contratos validos na data atual, agora precisa filtrar se essas datas estarao invalidos para X dias ??? quando é preenchido dtSuspensao
		statementJPA
				.append(" AND (c.dtFinalValidade <= :fimPeriodo) ");
		statementJPA
				.append(" AND (c.dtSuspensao is null OR c.dtSuspensao <= :fimPeriodo) ");
		
		statementJPA.append(" ORDER BY c.nmContrato ");

		List<Object[]> retorno = null;
		try {
			Query query = getEntityManager().createQuery(
					statementJPA.toString());

			//data atual
			query.setParameter("inicioPeriodo", new Date());

			//soma dias a data futura
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());
			calendar.add(Calendar.DAY_OF_MONTH, diasAVencer);

			query.setParameter("fimPeriodo", calendar.getTime());

			retorno = query.getResultList();
		} catch (DataBaseException exception) {
			throw new DataBaseException(
					DataBaseException.FALHA_COMUNICACAO_BANCO,
					exception.getMessage());
		}
		return retorno;
	}

}
