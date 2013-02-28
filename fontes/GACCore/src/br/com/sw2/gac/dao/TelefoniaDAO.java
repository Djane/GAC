package br.com.sw2.gac.dao;

import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Ligacao;


/**
 * <b>Descrição: Dao para acesso as tabelas do banco de dados da URA.</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class TelefoniaDAO {


    /** Atributo manager. */
    private EntityManager entityManager;

    /** Atributo classe. */
    private Ligacao entity;

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
     * Nome: getEntity
     * Recupera o valor do atributo 'entity'.
     *
     * @return valor do atributo 'entity'
     * @see
     */
    public Ligacao getEntity() {
        return entity;
    }

    /**
     * Nome: setEntity
     * Registra o valor do atributo 'entity'.
     *
     * @param entity valor do atributo entity
     * @see
     */
    public void setEntity(Ligacao entity) {
        this.entity = entity;
    }

    /**
     * Nome: obterDadosNovaLigacaoAtendente
     * Obter dados nova ligacao atendente.
     *
     * @param idUniqueid the id uniqueid
     * @return ligacao
     * @see
     */
    @SuppressWarnings("unchecked")
    public Ligacao obterDadosNovaLigacaoAtendente(String idUniqueid) {

        Ligacao retorno = null;

        StringBuffer jpql = new StringBuffer("SELECT entity FROM Ligacao entity ");
        jpql.append(" WHERE entity.idUniqueid = :idUniqueid");

        Query query = this.entityManager.createQuery(jpql.toString());
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
     * Nome: atualizarDataHoraAtendimento
     * Atualizar data hora atendimento.
     *
     * @param entity the entity
     * @throws DataBaseException the data base exception
     * @see
     */
    public void atualizarDataHoraAtendimento(Ligacao entity) throws DataBaseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sql = new StringBuffer("UPDATE LigacaoGAC ");
        sql.append("SET dtHrAtendimento = '");
        sql.append(sdf.format(entity.getDtHrAtendimento()));
        sql.append("' WHERE idUniqueid = '");
        sql.append(entity.getIdUniqueid());
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
