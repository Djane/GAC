package br.com.sw2.gac.business;

import com.mysql.jdbc.StringUtils;

import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Classe de negócio responsável por ações com dispositivos.
 * @author ddiniz
 */
public class DispositivoBusiness {

    private static final int TAMANHO_ID_DISPOSITIVO = 13;
    private DispositivoDAO dao = new DispositivoDAO();

    /**
     * Adicionar um dispositivo.
     * @param dispositivo VO do Dispositivo
     * @throws BusinessException Exceção do business
     */
    public void adicionarNovoDispositivo(DispositivoVO dispositivo) throws BusinessException {

        verificarDispositivoValido(dispositivo);
        verificarDispositivoDuplicado(dispositivo);

        Dispositivo entity = vo2Entity(dispositivo);

        try {
            dao.gravar(entity);
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }

    }

    /**
     * Verifica se o dispositivo e seu ID não são nulos e se seu ID tem o tamanho esperado.
     * @param dispositivo VO do Dispositivo
     * @throws BusinessException
     */
    private void verificarDispositivoValido(DispositivoVO dispositivo) throws BusinessException {
        if (null == dispositivo || StringUtils.isNullOrEmpty(dispositivo.getId())
                || dispositivo.getId().length() != TAMANHO_ID_DISPOSITIVO) {
            throw new BusinessException(
                    BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS);
        }
    }

    /**
     * Verifica se já não existe um Dispositivo com o Id informado.
     * @param dispositivo Vo do Dispositivo
     * @throws BusinessException
     */
    private void verificarDispositivoDuplicado(DispositivoVO dispositivo) throws BusinessException {
        Dispositivo existeId = this.dao.recuperaDispositivoPeloId(dispositivo.getId());

        if (null != existeId) {
            throw new BusinessException(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO);
        }
    }

    /**
     * Alterar um dispositivo.
     * @param dispositivo VO do Dispositivo
     */
    public void atualizarDispositivo(DispositivoVO dispositivo) {

        verificarDispositivoValido(dispositivo);

        Dispositivo entity = vo2Entity(dispositivo);
        dao.gravar(entity);
    }

    /**
     * Converte os dados do VO dispositivo em uma entity a ser enviada ao DAO.
     * @param dispositivo o VO do dispositvo
     * @return Dispositivo entity
     */
    private Dispositivo vo2Entity(DispositivoVO dispositivo) {
        Dispositivo entity = new Dispositivo();
        entity.setIdDispositivo(dispositivo.getId());
        entity.setTbUsuario(dispositivo.getLogin());

        Integer estado = null;
        if (null != dispositivo.getEstadoAtual()) {
            estado = dispositivo.getEstadoAtual().getValue();
        }
        entity.setTpEstado(estado);

        Integer tipo = null;
        if (null != dispositivo.getTipoDispositivo()) {
            tipo = dispositivo.getTipoDispositivo().getValue();
        }
        entity.setTpDispositivo(tipo);

        return entity;
    }

    /**
     * Exclusão do dispositivo.
     * @param id ID do dispositivo
     * @throws BusinessException exceção
     */
    public void apagarDispositivo(String id) throws BusinessException {

        try {
            dao.apagar(id);
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                throw new BusinessException(BusinessExceptionMessages.DELETE_DISPOSITIVO_EM_USO);
            }
        }
    }

}
