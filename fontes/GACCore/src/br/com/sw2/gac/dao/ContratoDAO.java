package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.AplicaMedico;
import br.com.sw2.gac.modelo.CID;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.ClienteDispositivo;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.FormaComunica;
import br.com.sw2.gac.modelo.Tratamento;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição: Classe responsável pela manipuação dos dados de </b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoDAO extends BaseDao<Contrato> {

    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

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
     * Nome: gravarNovoContrato Gravar novo contrato.
     * @param entity the entity
     * @return contrato
     * @throws DataBaseException the data base exception
     * @see
     */
    @SuppressWarnings("unchecked")
    public Contrato gravarNovoContrato(Contrato entity) throws DataBaseException {

        List<Tratamento> copiaListaTratamentos = entity.getClienteList().get(0).getTratamentoList();
        // Zero para impedir que o eclipse tente inserir em cascata e retorno erro de nullpointes no
        // idTratamento
        entity.getClienteList().get(0).setTratamentoList(new ArrayList<Tratamento>());
        Contrato contrato = entity;

        try {
            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(contrato);
            this.getEntityManager().flush();

            salvarClienteDoContrato(contrato);

            // Esse procedimento é para burlar uma limitação que o Eclipselink tem de salvar em
            // cascata.
            // Ao salvar em cascata o eclipselink não consegue passar por referencia/herança o id do
            // pai.
            for (Tratamento tratamento : copiaListaTratamentos) {
                List<AplicaMedico> aplicTemp = tratamento.getAplicaMedicoList();
                tratamento.setAplicaMedicoList(new ArrayList<AplicaMedico>());
                this.getEntityManager().persist(tratamento);
                this.getEntityManager().flush();
                for (AplicaMedico aplic : aplicTemp) {
                    aplic.getAplicaMedicoPK().setIdTratamento(tratamento.getIdTratamento());
                    this.getEntityManager().persist(aplic);
                    this.getEntityManager().flush();
                }
            }
            this.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (this.getEntityManager().getTransaction().isActive()) {
                this.getEntityManager().getTransaction().rollback();
            }
            throw new DataBaseException(e);
        }
        return contrato;
    }

    /**
     * Nome: salvarClienteDoContrato Salvar cliente do contrato.
     * @param contrato the contrato
     * @see
     */
    private void salvarClienteDoContrato(Contrato contrato) {
        for (Cliente cliente : contrato.getClienteList()) {
            cliente.setNmContrato(contrato);
            this.getEntityManager().persist(cliente);
            this.getEntityManager().flush();
            // Contatos DO cliente
            for (Contato contatoEntity : cliente.getContatoList()) {
                for (FormaComunica formaComunicaEntity : contatoEntity.getFormaComunicaList()) {
                    formaComunicaEntity.setIdContato(contatoEntity);
                    this.getEntityManager().persist(cliente);
                    this.getEntityManager().flush();
                }
            }
            // Formas de comunicação com o cliente;
            for (FormaComunica formaComunica : cliente.getFormaComunicaList()) {
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
     * @throws DataBaseException the data base exception
     * @see
     */
	@SuppressWarnings("unchecked")
	public List<Contrato> recuperarContratosAtivosAVencerEm(Integer diasAVencer)
        throws DataBaseException {

        StringBuffer statementJPA = new StringBuffer();
        statementJPA.append(" SELECT c ");
        statementJPA.append(" FROM Contrato c ");
        statementJPA.append(" WHERE ");
        statementJPA
            .append(" c.dtInicioValidade <= :inicioPeriodo ");
        statementJPA.append(" AND (c.dtSuspensao is null OR c.dtSuspensao >= :fimPeriodo) ");
        statementJPA.append(" AND (c.dtFinalValidade <= :fimPeriodo) ");

        statementJPA.append(" ORDER BY c.nmContrato ");

        List<Contrato> retorno = null;
        try {
            Query query = getEntityManager().createQuery(statementJPA.toString());

            // data atual
            query.setParameter("inicioPeriodo", new Date());

            // soma dias a data futura
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new java.util.Date());
            calendar.add(Calendar.DAY_OF_MONTH, diasAVencer);

            query.setParameter("fimPeriodo", calendar.getTime());

            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: getListaDoencas Recupera o valor do atributo 'listaDoencas'.
     * @param filtro the filtro
     * @return valor do atributo 'listaDoencas'
     * @see
     */
    public List<CID> getListaDoencas(String filtro) {
        List<CID> retorno = null;
        try {
            StringBuffer statementJPA = new StringBuffer("SELECT c FROM CID c  ");
            statementJPA.append(" WHERE c.nmDoenca like :nmDoenca");
            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("nmDoenca", "%" + filtro + "%");
            retorno = query.getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: excluirContrato Excluir contrato.
     * @param contrato the contrato
     * @throws DataBaseException the data base exception
     * @see
     */
    public void excluirContrato(Contrato contrato) throws DataBaseException {
        try {

            this.getEntityManager().getTransaction().begin();
            Contrato ctt = this.getEntityManager().find(Contrato.class, contrato.getNmContrato());
            Cliente cliente = ctt.getClienteList().get(0);

            Query query = getEntityManager().createQuery(
                "DELETE FROM AplicaMedico d WHERE d.aplicaMedicoPK.nmCPFCliente = :nmCPFCliente");
            query.setParameter("nmCPFCliente", cliente.getNmCPFCliente());
            query.executeUpdate();

            Query queryDelTratamento = getEntityManager().createQuery(
                "DELETE FROM Tratamento d WHERE d.cliente.nmCPFCliente = :nmCPFCliente");
            queryDelTratamento.setParameter("nmCPFCliente", cliente.getNmCPFCliente());
            queryDelTratamento.executeUpdate();

            Query queryDelFormaComunica = getEntityManager().createQuery(
                "DELETE FROM FormaComunica d WHERE d.nmCPFCliente.nmCPFCliente = :nmCPFCliente");
            queryDelFormaComunica.setParameter("nmCPFCliente", cliente.getNmCPFCliente());
            queryDelFormaComunica.executeUpdate();

            Query queryDelContato = getEntityManager().createQuery(
                "DELETE FROM Contato d WHERE d.nmCPFCliente.nmCPFCliente = :nmCPFCliente");
            queryDelContato.setParameter("nmCPFCliente", cliente.getNmCPFCliente());
            queryDelContato.executeUpdate();

            logger.debug("CPF do cliente: " + cliente.getNmCliente());
            //O Modelo não pertite a exclusão em cascata de todos os item
            this.getEntityManager().remove(ctt);
            this.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (this.getEntityManager().getTransaction().isActive()) {
                this.getEntityManager().getTransaction().rollback();
            }
            throw new DataBaseException(e);

        }
    }

}
