package br.com.sw2.gac.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.config.CacheUsage;
import org.eclipse.persistence.config.QueryHints;
import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Ligacao;
import br.com.sw2.gac.modelo.MotivoPausa;


/**
 * <b>Descrição: Dao para acesso as tabelas do Intellix.</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class TelefoniaDAO {


    /** Atributo manager. */
    private EntityManager entityManager;


    /**
     * Construtor Padrao
     * Instancia um novo objeto UraDAO.
     *
     */
    public TelefoniaDAO() {
        try {
            this.entityManager = ConnectionFactory.getConnectionURA();
        } catch (Exception e) {
            throw new DataBaseException("Não foi possível obter uma conexão com o banco de dados de telefonia", e);
        }
    }

    /**
     * Nome: getEntityManager
     * Recupera o valor do atributo 'entityManager'.
     *
     * @return valor do atributo 'entityManager'
     * @see
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Nome: setEntityManager
     * Registra o valor do atributo 'entityManager'.
     *
     * @param entityManager valor do atributo entity manager
     * @see
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Nome: obterDadosNovaLigacaoAtendente
     * Obter dados nova ligacao atendente.
     *
     * @param idUniqueid the id uniqueid
     * @return ligacao
     * @see
     */
    public Ligacao obterDadosNovaLigacaoAtendente(String idUniqueid) {
        this.entityManager.getEntityManagerFactory().getCache().evictAll();
        Ligacao retorno = null;

        StringBuffer jpql = new StringBuffer("SELECT entity FROM Ligacao entity ");
        jpql.append(" WHERE entity.idUniqueid = :idUniqueid");

        Query query = this.entityManager.createQuery(jpql.toString());
        query.setHint(QueryHints.CACHE_USAGE, CacheUsage.DoNotCheckCache);
        query.setHint(QueryHints.CACHE_STORE_MODE, CacheUsage.NoCache); 
        query.setHint("javax.persistence.cache.storeMode", "REFRESH");
        query.setParameter("idUniqueid", idUniqueid);


        try {
            retorno = (Ligacao) query.getSingleResult();
        } catch (NoResultException exception) {
            retorno = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }


    /**
     * Nome: obterMotivosPausa
     * Obter motivos pausa.
     *
     * @return list
     * @see
     */
    @SuppressWarnings("unchecked")
    public List<MotivoPausa> obterMotivosPausa() {
        this.entityManager.getEntityManagerFactory().getCache().evictAll();
        List<MotivoPausa> retorno = null;

        StringBuffer jpql = new StringBuffer("SELECT entity FROM MotivoPausa entity ");

        Query query = this.entityManager.createQuery(jpql.toString());

        try {
            retorno = (List<MotivoPausa>) query.getResultList();
        } catch (NoResultException exception) {
            retorno = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }
        return retorno;
    }

    /**
     * Nome: atualizarDataHoraAtendimento
     * Atualizar data hora atendimento.
     *
     * @param idUniqueid the id uniqueid
     * @param data the data
     * @throws DataBaseException the data base exception
     * @see
     */
    public void atualizarDataHoraAtendimento(String idUniqueid, Date data) throws DataBaseException {
        this.entityManager.getEntityManagerFactory().getCache().evictAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sql = new StringBuffer("UPDATE LigacaoGAC ");
        sql.append("SET dtHrAtendimento = '");
        sql.append(sdf.format(data));
        sql.append("' WHERE idUniqueid = '");
        sql.append(idUniqueid);
        sql.append("'");

        try {
            if (!this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().begin();
            }
            this.entityManager.createNativeQuery(sql.toString()).executeUpdate();
            this.entityManager.getTransaction().commit();
        } catch (DatabaseException exception) {
            if (!this.entityManager.getTransaction().isActive()) {
                this.entityManager.getTransaction().rollback();
            }
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                exception.getMessage());
        }

    }

}
