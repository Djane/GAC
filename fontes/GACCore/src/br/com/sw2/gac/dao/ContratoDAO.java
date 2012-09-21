package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.PacoteServico;
import br.com.sw2.gac.modelo.TipoDoenca;
import br.com.sw2.gac.modelo.Tratamento;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author castilhodaniel
 */
public class ContratoDAO extends BaseDao<Contrato> {

    /**
     * Construtor Padrao Instancia um novo objeto ContratoDAO.
     */
    public ContratoDAO() {
        super(Contrato.class);
    }

    /**
     * Metodo que recupera a lista com todos os pacotes de servicos cadastrados.
     * @return Lista de pacotes de servicos
     * @throws DataBaseException Excecao de banco
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<PacoteServico> recuperaListaPacoteServico() throws DataBaseException {
        List<PacoteServico> listaPacotesServico = new ArrayList<PacoteServico>();
        try {
            listaPacotesServico = getEntityManager().createQuery("select ps from PacoteServico ps")
                    .getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return listaPacotesServico;
    }

    /**
     * Metodo que recupera a lista com todos os tratamentos cadastrados.
     * @return Lista de tratamentos
     * @throws DataBaseException Excecao de banco
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<Tratamento> recuperaListaTratamento() throws DataBaseException {
        List<Tratamento> listaTratamento = new ArrayList<Tratamento>();
        try {
            listaTratamento = getEntityManager().createQuery("select t from Tratamento t")
                    .getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return listaTratamento;
    }

    /**
     * Metodo que recupera a lista com todos os contatos cadastrados.
     * @return Lista de contatos
     * @throws DataBaseException Excecao de banco
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<Contato> recuperaListaContato() throws DataBaseException {
        List<Contato> listaContato = new ArrayList<Contato>();
        try {
            listaContato = getEntityManager().createQuery("select c from Contato c")
                    .getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return listaContato;
    }

    // @SuppressWarnings("unchecked")
    // public List<Central> recuperaListaCentral() throws DataBaseException {
    // List<Central> listaContato = new ArrayList<Central>();
    // try {
    // listaContato = getEntityManager().createQuery("select c from Contato c").getResultList();
    // } catch (DataBaseException exception) {
    // throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
    // exception.getMessage());
    // }
    // return listaContato;
    // }

    /**
     * Metodo que recupera a lista com todos os dispositivos cadastrados.
     * @return Lista de dispositivos
     * @throws DataBaseException Excecao de banco
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<Dispositivo> recuperaListaDispositivo() throws DataBaseException {
        List<Dispositivo> listaDispositivo = new ArrayList<Dispositivo>();
        try {
            listaDispositivo = getEntityManager().createQuery("select c from Dispositivo c")
                    .getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return listaDispositivo;
    }

    /**
     * Metodo que recupera a lista com todas as doencas cadastradas.
     * @return Lista de doencas
     * @throws DataBaseException Excecao de banco
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<TipoDoenca> recuperaListaDoenca() throws DataBaseException {
        List<TipoDoenca> listaDoenca = new ArrayList<TipoDoenca>();
        try {
            listaDoenca = getEntityManager().createQuery("select td from TipoDoenca td")
                    .getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return listaDoenca;
    }

    /**
     * Metodo que recupera a lista com todos os contratos cadastrados.
     * @return Lista de contratos
     * @throws DataBaseException Excecao de banco
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<Contrato> recuperaListaContrato() throws DataBaseException {
        List<Contrato> listaContrato = new ArrayList<Contrato>();
        try {
            listaContrato = getEntityManager().createQuery("select c from Contrato c")
                    .getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return listaContrato;
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
     * Nome: getListaContratosAtivosPorPacote
     * Recupera o valor do atributo 'listaContratosAtivosPorPacote'.
     *
     * @param inicioPeriodo the inicio periodo
     * @param fimPeriodo the fim periodo
     * @return valor do atributo 'listaContratosAtivosPorPacote'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Object[]> getListaContratosAtivosPorPacote(Date inicioPeriodo, Date fimPeriodo) throws DataBaseException {
        StringBuffer statementJPA = new StringBuffer();
        statementJPA.append(" SELECT count(c.nmContrato) as total, c.idServico.dsTitulo ");
        statementJPA.append(" FROM Contrato c ");
        statementJPA.append(" WHERE  ");
        statementJPA.append(" c.dtInicioValidade >= :inicioPeriodo AND c.dtInicioValidade <= :fimPeriodo ");
        statementJPA.append(" AND (c.dtFinalValidade is null OR c.dtFinalValidade >= :fimPeriodo) ");
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

}
