package br.com.sw2.gac.business.state;

import java.util.List;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Classe State que implementa o estado Novo.
 * @author ddiniz
 */
public class DispositivoNovoState implements DispositivoState {

	@Override
	public void fazerManutencao(List<DispositivoVO> listaDispositivos) {
		for (DispositivoVO dispositivo : listaDispositivos) {
			dispositivo.setEstadoAtual(EstadoDispositivo.Manutencao.getValue());
		}
	}

	@Override
	public void validarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void abrirDefeito(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void utilizarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void devolverDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void descartarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void devolverFabrica(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void criarDispositivo() throws BusinessException {
		// Nada acontece ao tentar mudar o estado para o mesmo atual
		throw new BusinessException(BusinessExceptionMessages.MUDANCA_MESMO_ESTADO_INVALIDA);
	}

	private void lancaException() throws BusinessException {
		throw new BusinessException(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA);
	}

}
