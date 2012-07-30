/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 * <b>Descrição:</b> <br>
 * .
 * @param <T> the generic type
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BaseDao<T extends Serializable> {

    /** Atributo manager. */
    private  EntityManager entityManager;

    /**
     * Construtor Padrao Instancia um novo objeto BaseDao.
     */
    public BaseDao() {
        this.entityManager = ConnectionFactory.getConnection();
    }

    /**
     * Nome: gravar Gravar.
     * @param objeto the objeto
     * @see
     */
    public void gravar(T objeto) {
        this.entityManager.getTransaction().begin();
        this.entityManager.merge(objeto);
        this.entityManager.getTransaction().commit();
    }

    /**
     * Nome: apagar Apagar.
     * @param objeto the objeto
     * @see
     */
    public void apagar(T objeto) {
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(objeto);
        this.entityManager.getTransaction().commit();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
