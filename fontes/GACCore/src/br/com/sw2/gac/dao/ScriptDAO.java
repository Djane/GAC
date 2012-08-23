package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Script;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2o
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ScriptDAO extends BaseDao<Script> {


    /**
     * Construtor Padrao Instancia um novo objeto ScriptDao.
     */
    public ScriptDAO() {
        super(Script.class);
    }

    /**
     * Nome: findAll Recupera o valor do atributo 'result'.
     * @return valor do atributo 'result'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Script> findAll() throws DataBaseException {
        List<Script> result = new ArrayList<Script>();
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Script> q = cb.createQuery(Script.class);
        TypedQuery<Script> query = getEntityManager().createQuery(q);
        try {
            result = query.getResultList();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return result;
    }
}
