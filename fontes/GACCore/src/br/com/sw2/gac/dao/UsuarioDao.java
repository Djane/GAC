package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Usuario;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioDao extends BaseDao<Usuario> {

    /**
     * Construtor Padrao Instancia um novo objeto UsuarioDao.
     */
    public UsuarioDao() {
        super(Usuario.class);
    }

    /**
     * Nome: getUsuario Recupera o valor do atributo 'usuario'.
     * @param usuario the usuario
     * @return valor do atributo 'usuario'
     * @throws DataBaseException the data base exception
     * @see
     */
    public Usuario getUsuario(Usuario usuario) throws DataBaseException {

        CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);

        Root<Usuario> usuarioQuery = query.from(Usuario.class);
        Predicate login = qb.equal(usuarioQuery.get("login"), usuario.getLogin());
        if (null == usuario.getSenha()) {
            query.where(login);
        } else {
            Predicate senha = qb.equal(usuarioQuery.get("senha"), usuario.getSenha());
            query.where(login, senha);
        }

        Usuario result;
        try {
            result = getEntityManager().createQuery(query).getSingleResult();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return result;
    }

    /**
     * Nome: getListaUsuarios Retorna uma lista com os usu�rios existentes'.
     * @return valor do atributo 'listaUsuarios'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Usuario> getListaUsuarios() throws DataBaseException {

        List<Usuario> result;

        CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);
        try {
            result = getEntityManager().createQuery(query).getResultList();
        } catch (NoResultException exception) {
            result = new ArrayList<Usuario>();
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }

        return result;
    }

}
