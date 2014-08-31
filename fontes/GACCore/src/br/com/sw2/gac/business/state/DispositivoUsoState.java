package br.com.sw2.gac.business.state;

import java.util.List;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Classe que implementa o estado Uso.
 * @author ddiniz
 */
public class DispositivoUsoState implements DispositivoState {

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#fazerManutencao(java.util.List)
	 */
	@Override
	public void fazerManutencao(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#validarDispositivo(java.util.List)
	 */
	@Override
	public void validarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#abrirDefeito(java.util.List)
	 */
	@Override
	public void abrirDefeito(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#utilizarDispositivo(java.util.List)
	 */
	@Override
	public void utilizarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		// Nada acontece ao tentar mudar o estado para o mesmo atual
		throw new BusinessException(BusinessExceptionMessages.MUDANCA_MESMO_ESTADO_INVALIDA);
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#devolverDispositivo(java.util.List)
	 */
	@Override
	public void devolverDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		for (DispositivoVO dispositivo : listaDispositivos) {
			dispositivo.setEstadoAtual(EstadoDispositivo.Devolvido.getValue());
		}
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#descartarDispositivo(java.util.List)
	 */
	@Override
	public void descartarDispositivo(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#devolverFabrica(java.util.List)
	 */
	@Override
	public void devolverFabrica(List<DispositivoVO> listaDispositivos) throws BusinessException {
		lancaException();
	}

	/* (non-Javadoc)
	 * @see br.com.sw2.gac.business.state.DispositivoState#criarDispositivo()
	 */
	@Override
	public void criarDispositivo() throws BusinessException {
		lancaException();
	}

	private void lancaException() throws BusinessException {
		throw new BusinessException(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA);
	}

}
