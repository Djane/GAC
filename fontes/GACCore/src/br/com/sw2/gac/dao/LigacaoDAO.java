package br.com.sw2.gac.dao;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Ligacao;

/**
 * <b>Descrição: Dao para acesso e manipulação dos dados de ligação telefonica.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class LigacaoDAO extends BaseDao<Ligacao> {

    /**
     * Construtor Padrao Instancia um novo objeto UsuarioDao.
     */
    public LigacaoDAO() {
        super(Ligacao.class);
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

        Query query = getEntityManager().createQuery(jpql.toString());
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

}
