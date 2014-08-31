package br.com.sw2.gac.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.SMS;

/**
 * <b>Descrição: Classe de acesso aos dados da base de SMS.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class SmsDao extends BaseDao<SMS> {

    /**
     * Construtor Padrao Instancia um novo objeto SmsDAO.
     */
    public SmsDao() {
        super(SMS.class);
    }

    /**
     * Nome: getSmsByTituloDescricao
     * Recupera o valor do atributo 'smsByTituloDescricao'.
     *
     * @param sms the sms
     * @return valor do atributo 'smsByTituloDescricao'
     * @see
     */
    public SMS getSmsByTituloDescricao(SMS sms) {

        CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SMS> query = qb.createQuery(SMS.class);

        Root<SMS> smsQuery = query.from(SMS.class);

        Predicate titulo = qb.equal(smsQuery.get("tpMensagem"), sms.getTpMensagem());
        Predicate corpo = qb.equal(smsQuery.get("dsMensagem"), sms.getDsMensagem());

        query.where(titulo, corpo);

        SMS result;
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
     * Nome: getSmsVigentes
     * Recupera o valor do atributo 'smsVigentes'.
     *
     * @param sms the sms
     * @return valor do atributo 'smsVigentes'
     * @see
     */
    public List<SMS> getListaSmsByDataTermino(SMS sms) {

        CriteriaBuilder qb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<SMS> query = qb.createQuery(SMS.class);

        Root<SMS> smsQuery = query.from(SMS.class);

        Predicate dataFim = qb.greaterThanOrEqualTo(smsQuery.<Date> get("dtTerminoValidade"),
                sms.getDtTerminoValidade());
        Predicate dataFimNula = qb.or(dataFim, qb.isNull(smsQuery.<Date> get("dtTerminoValidade")));

        query.where(dataFimNula);

        List<SMS> result;
        try {
            result = getEntityManager().createQuery(query).getResultList();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }

        return result;
    }

}
