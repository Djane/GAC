/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;

/**
 *
 * @author marcelo
 */
public class BaseDao<T extends Serializable> {
    private EntityManager manager;

    public BaseDao() {
        this.manager = ConnectionFactory.getConnection();
    }
    
    public void gravar(T objeto) {
        manager.getTransaction().begin();
        manager.merge(objeto);
        manager.getTransaction().commit();
    }
    
    public void apagar(T objeto) {
        manager.getTransaction().begin();
        manager.remove(objeto);
        manager.getTransaction().commit();
    }
}
