package br.com.sw2.gac.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.eclipse.persistence.exceptions.DatabaseException;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.PacoteServico;

/**
 * <b>Descrição: Classe de acesso aos dados de pacotes de serviços.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PacoteServicoDAO extends BaseDao<PacoteServico> {

    /**
     * Construtor Padrao Instancia um novo objeto PacoteServicoDAO.
     */
    public PacoteServicoDAO() {
        super(PacoteServico.class);
    }

    /**
     * Nome: getPacoteServicoByTituloDescricao Retorna um pacote de serviço que atenda ao critério
     * informado no título e/ou descrição.
     * @param entity the entity
     * @return valor do atributo 'pacoteServicoByTituloDescricao'
     * @throws DataBaseException the data base exception
     * @see
     */
    public PacoteServico getPacoteServicoByTituloDescricao(PacoteServico entity) throws DataBaseException {

        PacoteServico result = null;
        try {
            Query query = getEntityManager()
                    .createQuery(
                            "SELECT ps FROM PacoteServico ps WHERE ps.dsServico = :dsServico or ps.dsTitulo = :dsTitulo");
            query.setParameter("dsTitulo", entity.getDsTitulo());
            query.setParameter("dsServico", entity.getDsServico());
            result = (PacoteServico) query.getSingleResult();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return result;
    }

    /**
     * Nome: getListaTodosPacoteServicos. Recupera a lista de todos os pacotes de serviços.
     * @return valor do atributo 'listaPacoteServicosValidos'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<PacoteServico> getListaTodosPacoteServicos() throws DataBaseException {

        List<PacoteServico> result = null;
        try {
            Query query = getEntityManager().createQuery(
                    "SELECT ps FROM PacoteServico ps");
     
            result = (List<PacoteServico>) query.getResultList();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return result;
    }
    
    /**
     * Nome: getListaPacoteServicosValidos. Recupera a lista de pacotes de serviços com a data de
     * validade nula ou superior igual a data atual.
     * @return valor do atributo 'listaPacoteServicosValidos'
     * @throws DataBaseException the data base exception
     * @see
     */
    public List<PacoteServico> getListaPacoteServicosValidos() throws DataBaseException {

        List<PacoteServico> result = null;
        try {
            Query query = getEntityManager().createQuery(
                    "SELECT ps FROM PacoteServico ps WHERE ps.dtFinalValidade is null or ps.dtFinalValidade >= :dtFinalValidade");
            query.setParameter("dtFinalValidade", new Date());

            result = (List<PacoteServico>) query.getResultList();
        } catch (NoResultException exception) {
            result = null;
        } catch (DatabaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO,
                    exception.getMessage());
        }
        return result;
    }

}
