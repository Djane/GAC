package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.modelo.AplicaMedico;
import br.com.sw2.gac.modelo.AplicaMedicoPK;
import br.com.sw2.gac.modelo.CID;
import br.com.sw2.gac.modelo.Cliente;
import br.com.sw2.gac.modelo.ClienteDispositivo;
import br.com.sw2.gac.modelo.ClienteDispositivoPK;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.FormaComunica;
import br.com.sw2.gac.modelo.Tratamento;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.util.StringUtil;

/**
 * <b>Descrição: Classe responsável pela manipuação dos dados de </b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoDAO extends BaseDao<Contrato> {

    /** Atributo logger. */
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
     * Nome: filtarContratosPorCPFCliente Filtar contratos por cpf cliente.
     * @param cpf the cpf
     * @return list
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Contrato> filtarContratosPorCPFCliente(String cpf) throws DataBaseException {
        List<Contrato> retorno = null;
        try {
            retorno = super.filterByField("cliente.nmCPFCliente", cpf);
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
     * Nome: filtarContratosPorNomeCliente Filtar contratos por nome cliente.
     * @param nomeCliente the nome cliente
     * @return list
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Contrato> filtarContratosPorNomeCliente(String nomeCliente)
        throws DataBaseException {
        List<Contrato> retorno = null;
        try {

            StringBuffer statementJPA = new StringBuffer("SELECT c from Contrato c ");
            statementJPA.append(" WHERE c.cliente.nmCliente like :nmCliente");
            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("nmCliente", "%" + nomeCliente + "%");

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

        List<Tratamento> copiaListaTratamentos = entity.getCliente().getTratamentoList();
        // Zero para impedir que o eclipse tente inserir em cascata e retorno erro de nullpointes no
        // idTratamento
        entity.getCliente().setTratamentoList(new ArrayList<Tratamento>());
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
                this.incluirTratamento(tratamento);
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
    @SuppressWarnings("unchecked")
    private void salvarClienteDoContrato(Contrato contrato) {
        Cliente cliente = contrato.getCliente();
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
        BeanPredicate beanPredicate = new BeanPredicate("dispositivo.tpDispositivo", equalPredicate);
        List<ClienteDispositivo> listaCentrais = (List<ClienteDispositivo>) CollectionUtils.select(
            cliente.getClienteDispositivoList(), beanPredicate);
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
        statementJPA.append(" c.dtInicioValidade <= :inicioPeriodo ");
        statementJPA.append(" AND (c.dtSuspensao is null OR c.dtSuspensao >= :fimPeriodo) ");
        statementJPA.append(" AND (c.dtFinalValidade <= :fimPeriodo) ");

        statementJPA.append(" ORDER BY c.nmContrato ");

        List<Contrato> retorno = null;
        try {
            Query query = getEntityManager().createQuery(statementJPA.toString());

            // data atual
            query.setParameter("inicioPeriodo", new Date());
            query.setParameter("fimPeriodo", DateUtil.adicionarDias(diasAVencer));

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
            Cliente cliente = ctt.getCliente();

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

            Query queryDelDispositivos = getEntityManager()
                .createQuery(
                    "DELETE FROM ClienteDispositivo d WHERE d.clienteDispositivoPK.nmCPFCliente = :nmCPFCliente");
            queryDelDispositivos.setParameter("nmCPFCliente", cliente.getNmCPFCliente());
            queryDelDispositivos.executeUpdate();

            logger.debug("CPF do cliente: " + cliente.getNmCliente());
            // O Modelo não pertite a exclusão em cascata de todos os item
            this.getEntityManager().remove(ctt);
            this.getEntityManager().getTransaction().commit();
        } catch (Exception e) {
            if (this.getEntityManager().getTransaction().isActive()) {
                this.getEntityManager().getTransaction().rollback();
            }
            throw new DataBaseException(e);

        }
    }

    /**
     * Nome: atualizarContrato Atualizar contrato.
     * @param contrato the contrato
     * @throws DataBaseException the data base exception
     * @see
     */
    public void atualizarContrato(Contrato contrato) throws DataBaseException {
        try {
            this.getEntityManager().merge(contrato);
            this.getEntityManager().flush();
        } catch (Exception e) {
            throw new DataBaseException(e);
        }
    }

    /**
     * Nome: atualizarContato Atualizar contato.
     * @param entity the entity
     * @see
     */
    public void atualizarContato(Contato entity) {
        StringBuffer strJpaQL = new StringBuffer();
        strJpaQL.append("UPDATE Contato  ");
        strJpaQL.append("SET nomeContato = :nomeContato, ");
        strJpaQL.append(" grauParentesco = :grauParentesco, ");
        strJpaQL.append(" endContato = :endContato,");
        strJpaQL.append(" baiContato = :baiContato, ");
        strJpaQL.append(" cidContato = :cidContato, ");
        strJpaQL.append(" cepContato = :cepContato, ");
        strJpaQL.append(" estadoContato = :estadoContato,");
        strJpaQL.append(" contratante = :contratante, ");
        strJpaQL.append(" dtaNascimento = :dtaNascimento ");
        strJpaQL.append("WHERE idContato = :idContato");

        Query queryDelContato = getEntityManager().createQuery(strJpaQL.toString());
        queryDelContato.setParameter("idContato", entity.getIdContato());
        queryDelContato.setParameter("nomeContato", entity.getNomeContato());
        queryDelContato.setParameter("grauParentesco", entity.getGrauParentesco());
        queryDelContato.setParameter("endContato", entity.getEndContato());
        queryDelContato.setParameter("baiContato", entity.getBaiContato());
        queryDelContato.setParameter("cidContato", entity.getCidContato());
        queryDelContato.setParameter("cepContato", entity.getCepContato());
        queryDelContato.setParameter("estadoContato", entity.getEstadoContato());
        queryDelContato.setParameter("contratante", entity.getContratante());
        queryDelContato.setParameter("idContato", entity.getIdContato());
        queryDelContato.setParameter("dtaNascimento", entity.getDtaNascimento());
        queryDelContato.executeUpdate();
    }

    /**
     * Nome: excluirContato Excluir contato.
     * @param entity the entity
     * @see
     */
    public void excluirContato(Contato entity) {

        Query queryDelFormaContato = getEntityManager().createQuery(
            "DELETE FROM FormaComunica d WHERE d.idContato = :idContato");
        queryDelFormaContato.setParameter("idContato", entity.getIdContato());
        queryDelFormaContato.executeUpdate();

        Query queryDelContato = getEntityManager().createQuery(
            "DELETE FROM Contato d WHERE d.idContato = :idContato");
        queryDelContato.setParameter("idContato", entity.getIdContato());
        queryDelContato.executeUpdate();
    }

    /**
     * Nome: atualizarFormaComunicacao Atualizar forma comunicacao.
     * @param entity the entity
     * @see
     */
    public void atualizarFormaComunicacao(FormaComunica entity) {
        StringBuffer strJpaQL = new StringBuffer();
        strJpaQL.append(" UPDATE FormaComunica ");
        strJpaQL.append(" SET tpContato = :tpContato, ");
        strJpaQL.append(" foneContato = :foneContato,");
        strJpaQL.append(" mailContato = :mailContato ");
        strJpaQL.append(" WHERE idFormaComunica = :idFormaComunica");

        Query query = getEntityManager().createQuery(strJpaQL.toString());
        query.setParameter("tpContato", entity.getTpContato());
        query.setParameter("foneContato", entity.getFoneContato());
        query.setParameter("mailContato", entity.getMailContato());
        query.setParameter("idFormaComunica", entity.getIdFormaComunica());
        query.executeUpdate();
    }

    /**
     * Nome: excluirFormaComunicacao Excluir forma comunicacao.
     * @param entity the entity
     * @see
     */
    public void excluirFormaComunicacao(FormaComunica entity) {
        Query query = getEntityManager().createQuery(
            "DELETE FROM FormaComunica d WHERE d.idFormaComunica = :idFormaComunica");
        query.setParameter("idFormaComunica", entity.getIdFormaComunica());
        query.executeUpdate();
    }

    /**
     * Nome: atualizarTratamento Atualizar tratamento.
     * @param entity the entity
     * @see
     */
    public void atualizarTratamento(Tratamento entity) {
        StringBuffer strJpaQL = new StringBuffer();
        strJpaQL.append("UPDATE Tratamento");
        strJpaQL.append(" SET nomeTrata = :nomeTrata");
        strJpaQL.append(",descrTrata = :descrTrata ");
        strJpaQL.append(",horaInicial = :horaInicial ");
        strJpaQL.append(",tpFrequencia = :tpFrequencia ");
        strJpaQL.append(" WHERE idTratamento = :idTratamento ");

        Query query = getEntityManager().createQuery(strJpaQL.toString());
        query.setParameter("nomeTrata", entity.getNomeTrata());
        query.setParameter("descrTrata", entity.getDescrTrata());
        query.setParameter("horaInicial", entity.getHoraInicial());
        query.setParameter("tpFrequencia", entity.getTpFrequencia());
        query.setParameter("idTratamento", entity.getIdTratamento());
        query.executeUpdate();
    }

    /**
     * Nome: excluirTratamento Excluir tratamento.
     * @param entity the entity
     * @see
     */
    public void excluirTratamento(Tratamento entity) {

        Query queryHorarios = getEntityManager().createQuery(
            "DELETE FROM AplicaMedico d WHERE d.aplicaMedicoPK.idTratamento = :idTratamento");
        queryHorarios.setParameter("idTratamento", entity.getIdTratamento());
        queryHorarios.executeUpdate();

        Query query = getEntityManager().createQuery(
            "DELETE FROM Tratamento d WHERE d.idTratamento = :idTratamento");
        query.setParameter("idTratamento", entity.getIdTratamento());
        query.executeUpdate();
    }

    /**
     * Nome: excluirHorarioTratamento Excluir horario tratamento.
     * @param idTratamento the id tratamento
     * @param hrAplicacao the hr aplicacao
     * @see
     */
    public void excluirHorarioTratamento(Integer idTratamento, Date hrAplicacao) {
        Query query = getEntityManager()
            .createQuery(
                "DELETE FROM AplicaMedico d WHERE d.aplicaMedicoPK.idTratamento = :idTratamento AND d.aplicaMedicoPK.hrAplicacao = :hrAplicacao");
        query.setParameter("idTratamento", idTratamento);
        query.setParameter("hrAplicacao", hrAplicacao);
        query.executeUpdate();
    }

    /**
     * Nome: excluirDispositivosContrato Excluir dispositivos contrato.
     * @param idDispositivo the id dispositivo
     * @param nmCPFCliente the nm cpf cliente
     * @see
     */
    public void excluirDispositivosContrato(String idDispositivo, String nmCPFCliente) {
        StringBuffer strJpqQl = new StringBuffer("DELETE FROM ClienteDispositivo d ");
        strJpqQl
            .append("WHERE d.clienteDispositivoPK.idDispositivo = :idDispositivo AND d.clienteDispositivoPK.nmCPFCliente = :nmCPFCliente");
        Query query = getEntityManager().createQuery(strJpqQl.toString());
        query.setParameter("idDispositivo", idDispositivo);
        query.setParameter("nmCPFCliente", nmCPFCliente);
        query.executeUpdate();
    }

    /**
     * Nome: incluirDispositivosContrato Incluir dispositivos contrato.
     * @param idDispositivo the id dispositivo
     * @param nmCPFCliente the nm cpf cliente
     * @param numeroDispositivo the numero dispositivo
     * @see
     */
    public void incluirDispositivosContrato(String idDispositivo, String nmCPFCliente,
        Integer numeroDispositivo) {

        ClienteDispositivo clienteDispositivo = new ClienteDispositivo();
        clienteDispositivo.setNumDispositivo(numeroDispositivo);
        ClienteDispositivoPK pk = new ClienteDispositivoPK();
        pk.setIdDispositivo(idDispositivo);
        pk.setNmCPFCliente(nmCPFCliente);
        clienteDispositivo.setClienteDispositivoPK(pk);

        this.getEntityManager().persist(clienteDispositivo);
        this.getEntityManager().flush();

    }

    /**
     * Nome: existeDispositivoCliente Existe dispositivo cliente.
     * @param idDispositivo the id dispositivo
     * @param nmCPFCliente the nm cpf cliente
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean existeDispositivoCliente(String idDispositivo, String nmCPFCliente) {
        boolean retorno = true;
        try {

            StringBuffer statementJPA = new StringBuffer("SELECT c FROM ClienteDispositivo c ");
            statementJPA.append(" WHERE c.clienteDispositivoPK.idDispositivo = :idDispositivo");
            statementJPA.append(" AND c.clienteDispositivoPK.nmCPFCliente = :nmCPFCliente");

            Query query = getEntityManager().createQuery(statementJPA.toString());
            query.setParameter("idDispositivo", idDispositivo);
            query.setParameter("nmCPFCliente", nmCPFCliente);

            List<ClienteDispositivo> list = query.getResultList();

            if (CollectionUtils.isEmptyOrNull(list)) {
                retorno = false;
            }

        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }

        return retorno;
    }

    /**
     * Nome: incluirContato Incluir contato.
     * @param contatoEntity the contato entity
     * @see
     */
    public void incluirContato(Contato contatoEntity) {
        this.getEntityManager().persist(contatoEntity);
        this.getEntityManager().flush();
        for (FormaComunica formaComunica : contatoEntity.getFormaComunicaList()) {
            formaComunica.setIdContato(contatoEntity);
            formaComunica.setNmCPFCliente(contatoEntity.getNmCPFCliente());
            this.getEntityManager().merge(formaComunica);
            this.getEntityManager().flush();
        }
    }

    /**
     * Nome: incluirTratamento Incluir tratamento.
     * @param entity the entity
     * @see
     */
    public void incluirTratamento(Tratamento entity) {
        List<AplicaMedico> aplicTemp = entity.getAplicaMedicoList();
        entity.setAplicaMedicoList(new ArrayList<AplicaMedico>());
        this.getEntityManager().persist(entity);
        this.getEntityManager().flush();
        for (AplicaMedico aplic : aplicTemp) {
            aplic.getAplicaMedicoPK().setIdTratamento(entity.getIdTratamento());
            this.getEntityManager().persist(aplic);
            this.getEntityManager().flush();
        }
    }

    /**
     * Nome: incluirHorarioTratamento Incluir horario tratamento.
     * @param idTratamento the id tratamento
     * @param nmCPFCliente the nm cpf cliente
     * @param hrAplicacao the Hr aplicacao
     * @see
     */
    public void incluirHorarioTratamento(Integer idTratamento, String nmCPFCliente, Date hrAplicacao) {
        AplicaMedico aplicaMedico = new AplicaMedico();
        AplicaMedicoPK aplicaMedicopk = new AplicaMedicoPK();
        aplicaMedicopk.setHrAplicacao(hrAplicacao);
        aplicaMedicopk.setIdTratamento(idTratamento);
        aplicaMedicopk.setNmCPFCliente(nmCPFCliente);
        aplicaMedico.setAplicaMedicoPK(aplicaMedicopk);
        this.getEntityManager().persist(aplicaMedico);
        this.getEntityManager().flush();
    }

    /**
     * Nome: getListaContratos
     * Recupera o valor do atributo 'listaContratos'.
     *
     * @param filtro the filtro
     * @return valor do atributo 'listaContratos'
     * @see
     */
    public List<Contrato> getListaContratos(FiltroPesquisarPreAtendimento filtro) {

        StringBuffer statementJPQL = new StringBuffer();
        statementJPQL.append(" SELECT c ");
        statementJPQL.append(" FROM Contrato c ");

        if (StringUtil.isNotEmpty(filtro.getTelefone(), true)) {
            statementJPQL.append(" FormaComunica d ");
        }

        StringBuffer statementWHERE = new StringBuffer();
        if (null != filtro.getNumeroContrato()) {
            statementWHERE.append(" c.nmContrato = ");
            statementWHERE.append(filtro.getNumeroContrato());
        }

        if (StringUtil.isNotEmpty(filtro.getNumeroCPFCliente(), true)) {
            hasOperadorAnd(statementWHERE);
            statementWHERE.append("(c.cliente.nmCPFCliente = ");
            statementWHERE.append("'");
            statementWHERE.append(filtro.getNumeroCPFCliente());
            statementWHERE.append("')");
        }

        if (StringUtil.isNotEmpty(filtro.getNomeCliente(), true)) {
            hasOperadorAnd(statementWHERE);
            statementWHERE.append("(c.cliente.nmCliente like ");
            statementWHERE.append("'%");
            statementWHERE.append(filtro.getNomeCliente());
            statementWHERE.append("%')");
        }

        if (StringUtil.isNotEmpty(filtro.getTelefone(), true)) {
            hasOperadorAnd(statementWHERE);
            statementWHERE.append(" (d.nmCPFCliente.nmCPFCliente = c.cliente.nmCPFCliente) ");
            hasOperadorAnd(statementWHERE);
            statementWHERE.append(" (d.foneContato = ");
            statementWHERE.append("'");
            statementWHERE.append(filtro.getTelefone());
            statementWHERE.append("')");
        }

        statementJPQL.append(" WHERE ");
        statementJPQL.append(statementWHERE.toString());
        Query query = getEntityManager().createQuery(statementJPQL.toString());
        List<Contrato> list = query.getResultList();
        return list;
    }

    /**
     * Nome: hasOperadorAnd
     * Checks for operador and.
     *
     * @param statementWHERE the statement where
     * @see
     */
    private void hasOperadorAnd(StringBuffer statementWHERE) {
        if (statementWHERE.length() > 0) {
            statementWHERE.append(" AND ");
        }
    }

}
