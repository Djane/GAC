package br.com.sw2.gac.business.state;

import java.util.List;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Classe que implementa o estado Pronto.
 * @author ddiniz
 */
public class DispositivoProntoState implements DispositivoState {

	@Override
	public void fazerManutencao(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void validarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		throw new BusinessException(BusinessExceptionMessages.MUDANCA_MESMO_ESTADO_INVALIDA);
	}

	@Override
	public void abrirDefeito(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	@Override
	public void utilizarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		for (DispositivoVO dispositivo : listaDispositivos) {
			dispositivo.setEstadoAtual(EstadoDispositivo.Uso.getValue());
		}
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
		lancaException();
	}

	private void lancaException() throws BusinessException {
		throw new BusinessException(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA);
	}

}
