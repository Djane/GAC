package br.com.sw2.gac.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Ocorrencia;

/**
 * <b>Descrição: Classe responsavel pelo acesso a dados da tabela de ocorrências.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaDAO extends BaseDao<Ocorrencia> {

    /**
     * Construtor Padrao Instancia um novo objeto OcorrenciaDAO.
     */
    public OcorrenciaDAO() {
        super(Ocorrencia.class);
    }

    /**
     * Nome: filtarOcorrenciasPorDataFechamento Filtar ocorrencias por data fechamento.
     * @param date the date
     * @return list
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<Ocorrencia> filtarOcorrenciasPorDataFechamento(Date date) throws DataBaseException {

        StringBuffer jpql = new StringBuffer("SELECT entity FROM Ocorrencia");
        jpql.append(" entity");
        Query query = null;
        if (null == date) {
            jpql.append(" WHERE entity.dtaHoraFechamento is null");
            query = getEntityManager().createQuery(jpql.toString());
        } else {
            jpql.append(" WHERE entity.dtaHoraFechamento = :value");
            query = getEntityManager().createQuery(jpql.toString());
            query.setParameter("value", date);
        }

        List<Ocorrencia> result;
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
