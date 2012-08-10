package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.UsuarioVO;

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
        if (null == dispositivo || StringUtil.isVazio(dispositivo.getIdDispositivo(), true)
                || dispositivo.getIdDispositivo().length() != TAMANHO_ID_DISPOSITIVO) {
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
        Dispositivo existeId = this.dao.recuperaDispositivoPeloId(dispositivo.getIdDispositivo());

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
            vo = entity2vo(dispositivo);
            listaVO.add(vo);
        }
        return listaVO;
    }

    /**
     * Converte os dados do VO dispositivo em uma entity a ser enviada ao DAO.
     * @param dispositivo o VO do dispositvo
     * @return Dispositivo entity
     */
    private Dispositivo vo2Entity(DispositivoVO dispositivo) {
        Dispositivo entity = new Dispositivo();
        entity.setIdDispositivo(dispositivo.getIdDispositivo());

        UsuarioBusiness usuarioBusiness = new UsuarioBusiness();
        Usuario usuario = usuarioBusiness.recuperarUsuario(dispositivo.getUsuario());
        entity.setLogin(usuario);

        entity.setTpEstado(dispositivo.getEstadoAtual());
        entity.setTpDispositivo(dispositivo.getTipoDispositivo());
        entity.setDtaEntrada(dispositivo.getDataEntrada());
        entity.setDtaFabrica(dispositivo.getDataFabricacao());
        entity.setDtaProximaManut(dispositivo.getDataProximaManutencao());
        entity.setDtaSucata(dispositivo.getDataSucata());
        entity.setLocal(dispositivo.getLocal());


        return entity;
    }

    /**
     * Converte os dados do da entity recuperada do banco em um VO.
     * @param entity Entity do dispositvo
     * @return Dispositivo VO
     */
    private DispositivoVO entity2vo(Dispositivo entity) {
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(entity.getIdDispositivo());
        dispositivo.setUsuario(new UsuarioVO());

        Integer estado = null;
        if (null != entity.getTpEstado()) {
            estado = entity.getTpEstado();
        }
        dispositivo.setEstadoAtual(estado);

        Integer tipo = null;
        if (null != entity.getTpDispositivo()) {
            tipo = entity.getTpDispositivo();
        }
        dispositivo.setTipoDispositivo(tipo);

        return dispositivo;
    }
}
