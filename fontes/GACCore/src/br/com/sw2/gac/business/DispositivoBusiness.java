package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.util.StringUtil;
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
     * @param idOriginal Envia o id original do dispositivo em caso de edição, ou null se for novo dispositivo
     * @throws BusinessException Exceção do business
     */
    public void adicionarNovoDispositivo(DispositivoVO dispositivo, String idOriginal) throws BusinessException {

        verificarDispositivoValido(dispositivo);

        // Se dispositivo é novo ou se editou o id de um dispositivo existente, deve validar duplicidade
        if (!dispositivo.getIdDispositivo().equalsIgnoreCase(idOriginal)) {
            verificarDispositivoDuplicado(dispositivo);
        }

        Dispositivo entity = ObjectUtils.parse(dispositivo);

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
        if (null == dispositivo || StringUtil.isVazio(dispositivo.getIdDispositivo(), true)) {
            throw new BusinessException(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS);
        }

        if (dispositivo.getIdDispositivo().length() != TAMANHO_ID_DISPOSITIVO) {
        	throw new BusinessException(BusinessExceptionMessages.ID_DISPOSITIVO_TAMANHO_INVALIDO);
        }
    }

    /**
     * Verifica se já não existe um Dispositivo com o Id informado.
     * @param dispositivo Vo do Dispositivo
     * @throws BusinessException
     */
    private void verificarDispositivoDuplicado(DispositivoVO dispositivo) throws BusinessException {

        Dispositivo existeId = this.dao.recuperaDispositivoPeloId(dispositivo.getIdDispositivo());

        if (null != existeId) {
            throw new BusinessException(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO);
        }
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

    /**
     * Recupera a lista de Dispositivos.
     * @return List<DispositivoVO>
     */
    public List<DispositivoVO> recuperaListaDispositivos() {
        // Recupera a lista de dispositivos do banco
        List<Dispositivo> listaDispositivos = dao.recuperaListaDispositivos();

        List<DispositivoVO> listaVO = new ArrayList<DispositivoVO>();

        // Transforma a lista de entities em VOS
        for (Dispositivo dispositivo : listaDispositivos) {
            DispositivoVO vo = new DispositivoVO();
            vo = ObjectUtils.parse(dispositivo);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Recupera a lista de Dispositivos que estão em determinado estado.
     * @param estado a ser pesquisado
     * @return List<DispositivoVO>
     */
    public List<DispositivoVO> recuperaListaPulseiraECentralPorEstado(Integer estado) {
        // Recupera a lista de dispositivos do banco
        List<Dispositivo> listaDispositivos = dao.recuperaPulseiraECentralPeloEstado(estado);

        List<DispositivoVO> listaVO = new ArrayList<DispositivoVO>();

        // Transforma a lista de entities em VOS
        for (Dispositivo dispositivo : listaDispositivos) {
            DispositivoVO vo = new DispositivoVO();
            vo = ObjectUtils.parse(dispositivo);
            listaVO.add(vo);
        }
        return listaVO;
    }
}
