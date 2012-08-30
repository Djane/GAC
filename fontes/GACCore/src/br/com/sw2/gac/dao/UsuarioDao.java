package br.com.sw2.gac.dao;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.sw2.gac.modelo.Usuario;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioDao extends BaseDao<Usuario> {

    /**
     * Nome: getUsuario Recupera um usuario na base de dados.'.
     * @param usuario the usuario
     * @return valor do atributo 'usuario'
     * @see
     */
    public Usuario getUsuario(Usuario usuario) {

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
        }
        return result;
    }

}
