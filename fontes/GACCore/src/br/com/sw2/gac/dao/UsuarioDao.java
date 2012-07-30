package br.com.sw2.gac.dao;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
     * Nome: getUsuario Recupera o valor do atributo 'usuario'.
     * @param usuario the usuario
     * @return valor do atributo 'usuario'
     * @see
     */
    public Usuario getUsuario(Usuario usuario) {

        CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Usuario> query = qb.createQuery(Usuario.class);

        Root<Usuario> usuarioQuery = query.from(Usuario.class);
        query.where(qb.equal(usuarioQuery.get("login"), usuario.getLogin()));
        Usuario result = getEntityManager().createQuery(query).getSingleResult();

        return result;
    }

}
