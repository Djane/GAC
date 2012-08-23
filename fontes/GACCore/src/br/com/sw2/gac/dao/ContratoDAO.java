package br.com.sw2.gac.dao;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Contato;
import br.com.sw2.gac.modelo.Contrato;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.PacoteServico;
import br.com.sw2.gac.modelo.TipoDoenca;
import br.com.sw2.gac.modelo.Tratamento;

/**
 *
 * @author castilhodaniel
 *
 */
public class ContratoDAO extends BaseDao<Contrato> {

    /**
     * Construtor Padrao Instancia um novo objeto ContratoDAO.
     */
    public ContratoDAO() {
        super(Contrato.class);
    }

    /**
     * Metodo que recupera a lista com todos os pacotes de servicos cadastrados.
     * @return Lista de pacotes de servicos
     * @throws DataBaseException Excecao de banco
     */
    @SuppressWarnings("unchecked")
    public List<PacoteServico> recuperaListaPacoteServico() throws DataBaseException {
        List<PacoteServico> listaPacotesServico = new ArrayList<PacoteServico>();
        try {
            listaPacotesServico = getEntityManager().createQuery("select ps from PacoteServico ps").getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return listaPacotesServico;
    }

    /**
     * Metodo que recupera a lista com todos os tratamentos cadastrados.
     * @return Lista de tratamentos
     * @throws DataBaseException Excecao de banco
     */
    @SuppressWarnings("unchecked")
    public List<Tratamento> recuperaListaTratamento() throws DataBaseException {
        List<Tratamento> listaTratamento = new ArrayList<Tratamento>();
        try {
            listaTratamento = getEntityManager().createQuery("select t from Tratamento t").getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return listaTratamento;
    }

    /**
     * Metodo que recupera a lista com todos os contatos cadastrados.
     * @return Lista de contatos
     * @throws DataBaseException Excecao de banco
     */
    @SuppressWarnings("unchecked")
    public List<Contato> recuperaListaContato() throws DataBaseException {
        List<Contato> listaContato = new ArrayList<Contato>();
        try {
            listaContato = getEntityManager().createQuery("select c from Contato c").getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return listaContato;
    }

//    @SuppressWarnings("unchecked")
//    public List<Central> recuperaListaCentral() throws DataBaseException {
//        List<Central> listaContato = new ArrayList<Central>();
//        try {
//            listaContato = getEntityManager().createQuery("select c from Contato c").getResultList();
//        } catch (DataBaseException exception) {
//            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
//        }
//        return listaContato;
//    }

    /**
     * Metodo que recupera a lista com todos os dispositivos cadastrados.
     * @return Lista de dispositivos
     * @throws DataBaseException Excecao de banco
     */
    @SuppressWarnings("unchecked")
    public List<Dispositivo> recuperaListaDispositivo() throws DataBaseException {
        List<Dispositivo> listaDispositivo = new ArrayList<Dispositivo>();
        try {
            listaDispositivo = getEntityManager().createQuery("select c from Dispositivo c").getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return listaDispositivo;
    }

    /**
     * Metodo que recupera a lista com todas as doencas cadastradas.
     * @return Lista de doencas
     * @throws DataBaseException Excecao de banco
     */
    @SuppressWarnings("unchecked")
    public List<TipoDoenca> recuperaListaDoenca() throws DataBaseException {
        List<TipoDoenca> listaDoenca = new ArrayList<TipoDoenca>();
        try {
            listaDoenca = getEntityManager().createQuery("select td from TipoDoenca td").getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return listaDoenca;
    }

    /**
     * Metodo que recupera a lista com todos os contratos cadastrados.
     * @return Lista de contratos
     * @throws DataBaseException Excecao de banco
     */
    @SuppressWarnings("unchecked")
    public List<Contrato> recuperaListaContrato() throws DataBaseException {
        List<Contrato> listaContrato = new ArrayList<Contrato>();
        try {
            listaContrato = getEntityManager().createQuery("select c from Contrato c").getResultList();
        } catch (DataBaseException exception) {
            throw new DataBaseException(DataBaseException.FALHA_COMUNICACAO_BANCO, exception.getMessage());
        }
        return listaContrato;
    }

}
