package br.com.sw2.gac.business.state;

import java.util.List;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Interface que utiliza o Design Pattern State para controlar a maquina de estados do dispositivo.
 * @author ddiniz
 *
 */
public interface DispositivoState {

	/**
	 * Colocar um Dispositivo em Manutenção, possível a partir dos estados NOVO, DEFEITO e DEVOLVIDO.
	 * @param listaDispositivos Dispositivos a serem alterados
	 * @throws BusinessException se mudança é invalida
	 */
    void fazerManutencao(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Mudar para o estado Pronto, isso só pode ser feito a partir do estado em Manutenção.
     * @param listaDispositivos Dispositivos a serem alterados
 	 * @throws BusinessException se mudança é invalida
     */
    void validarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Mudar para o estado com Defeito, isso só pode ser feito a partir do estado em Manutenção.
     * @param listaDispositivos Dispositivos a serem alterados
     * @throws BusinessException se mudança é invalida
     */
    void abrirDefeito(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Mudar para o estado em Uso, isso só pode ser feito a partir do estado em Pronto.
     * @param listaDispositivos Dispositivos a serem alterados
     * @throws BusinessException se mudança é invalida
     */
    void utilizarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Mudar para o estado Devolvido, isso só pode ser feito a partir do estado em Uso.
     * @param listaDispositivos Dispositivos a serem alterados
     * @throws BusinessException se mudança é invalida
     */
    void devolverDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Mudar para o estado Descartado, isso só pode ser feito a partir do estado em Manutenção.
     * @param listaDispositivos Dispositivos a serem alterados
     * @throws BusinessException se mudança é invalida
     */
    void descartarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Mudar para o estado Fábrica, isso só pode ser feito a partir do estado em com Defeito.
     * @param listaDispositivos Dispositivos a serem alterados
     * @throws BusinessException se mudança é invalida
     */
    void devolverFabrica(List<DispositivoVO> listaDispositivos) throws BusinessException;

    /**
     * Não é possível mudar para o estado Novo a partir de nenhum outro estado, sempre retorna exception se chamado.
     * @throws BusinessException se mudança é invalida
     */
	void criarDispositivo() throws BusinessException;;
}