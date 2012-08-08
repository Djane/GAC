package br.com.sw2.gac.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import br.com.sw2.gac.exception.DataBaseException;

/**
 * <b>Descrição:</b> <br>
 * .
 * @param <T> the generic type
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BaseDao<T extends Serializable> {

    /** Atributo manager. */
    private EntityManager entityManager;

    /** Atributo classe. */
    private Class<T> entity;

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     */
    public BaseDao() {
        this.entityManager = ConnectionFactory.getConnection();
    }

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     * @param clazz the clazz
     */
    public BaseDao(Class<T> clazz) {
        this.entityManager = ConnectionFactory.getConnection();
        this.entity = clazz;
    }

    /**
     * Nome: gravar Gravar.
     * @param objeto the objeto
     * @see
     */
    public void gravar(T objeto) {

        if (!this.entityManager.getTransaction().isActive()) {
            this.entityManager.getTransaction().begin();
        }
        this.entityManager.merge(objeto);
        this.entityManager.getTransaction().commit();
    }

    /**
     * Nome: apagar deleta um registro na base de dados baseado em seu ID.
     * @param id the id
     * @throws DataBaseException the data base exception
     * @see
     */
    @SuppressWarnings("unchecked")
    public void apagar(Object id) throws DataBaseException {

        T objeto = (T) this.entityManager.find(this.entity, id);
        this.apagar(objeto);

    }

    /**
     * Nome: apagar Apagar.
     * @param objeto the objeto
     * @throws DataBaseException the data base exception
     * @see
     */
    public void apagar(T objeto) throws DataBaseException {
        try {
            this.entityManager.getTransaction().begin();
            this.entityManager.remove(objeto);
            this.entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (e.getMessage().contains("Error Code: 1451")) {
                throw new DataBaseException(DataBaseException.DELETE_VIOLACAO_CONSTRAINT,
                        e.getMessage());
            }
        }
    }

    /**
     * Nome: getEntityManager Recupera o valor do atributo 'entityManager'.
     * @return valor do atributo 'entityManager'
     * @see
     */
    public EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * Nome: setEntityManager Registra o valor do atributo 'entityManager'.
     * @param entityManager valor do atributo entity manager
     * @see
     */
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
