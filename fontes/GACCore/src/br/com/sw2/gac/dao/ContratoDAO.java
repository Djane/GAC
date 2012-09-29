package br.com.sw2.gac.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.FormaComunica;

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
            for (FormaComunica formaComunica : cliente.getFormaComunicaList()) {
                this.getEntityManager().persist(formaComunica);
                this.getEntityManager().flush();
            }
        }
        this.getEntityManager().getTransaction().commit();
    }
}
