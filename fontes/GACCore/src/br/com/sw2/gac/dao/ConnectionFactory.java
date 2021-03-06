package br.com.sw2.gac.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * A factory for creating Connection objects.
 */
public final class ConnectionFactory {

    /**
     * Construtor Padrao
     * Instancia um novo objeto ConnectionFactory.
     */
    private ConnectionFactory() {
        super();
    }

    /**
     * Nome: getConnection Recupera o valor do atributo 'connection'.
     * @return valor do atributo 'connection'
     * @see
     */
    public static EntityManager getConnection() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("GACCore");
        EntityManager manager = emf.createEntityManager();
        return manager;
    }

    /**
     * Nome: getConnection Recupera o valor do atributo 'connection'.
     * @return valor do atributo 'connection'
     * @see
     */
    public static EntityManager getConnectionURA() {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("dsTelefonia");
        EntityManager manager = emf.createEntityManager();
        return manager;
    }
}
