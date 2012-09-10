package br.com.sw2.gac.business;

import java.util.Date;
import java.util.List;

import br.com.sw2.gac.business.state.DispositivoDefeitoState;
import br.com.sw2.gac.business.state.DispositivoDescarteState;
import br.com.sw2.gac.business.state.DispositivoDevolvidoState;
import br.com.sw2.gac.business.state.DispositivoFabricaState;
import br.com.sw2.gac.business.state.DispositivoManutencaoState;
import br.com.sw2.gac.business.state.DispositivoNovoState;
import br.com.sw2.gac.business.state.DispositivoProntoState;
import br.com.sw2.gac.business.state.DispositivoState;
import br.com.sw2.gac.business.state.DispositivoUsoState;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.dao.HistDispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.HistDispositivo;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.HistDispositivoVO;

/**
 * Classe responsável pela máquina de estados do Dispositivo.
 * @author ddiniz
 */
public class InventarioDispositivoBusiness {

	private DispositivoState novoState;
	private DispositivoState manutencaoState;
	private DispositivoState prontoState;
	private DispositivoState usoState;
	private DispositivoState devolvidoState;
	private DispositivoState defeitoState;
	private DispositivoState fabricaState;
	private DispositivoState descarteState;

	private DispositivoState state = novoState;

	private EstadoDispositivo estadoAnterior;

	/**
	 * Construtor.
	 */
	public InventarioDispositivoBusiness() {
		novoState = new DispositivoNovoState();
		manutencaoState = new DispositivoManutencaoState();
		prontoState = new DispositivoProntoState();
		usoState = new DispositivoUsoState();
		devolvidoState = new DispositivoDevolvidoState();
		defeitoState = new DispositivoDefeitoState();
		fabricaState = new DispositivoFabricaState();
		descarteState = new DispositivoDescarteState();
	}

	/**
	 * Metodo que identifica qual classe DispositivoState deve implementar a
	 * mudança de estado.
	 *
	 * @param listaDispositivos
	 *            dispositivos que terão o estado alterado
	 * @param estadoAtual
	 *            estado atual do dispositivo
	 * @param novoEstado estado para qual o dispositivo será alterado
	 * @throws BusinessException exception
	 */
	public void mudarEstado(List<DispositivoVO> listaDispositivos, EstadoDispositivo estadoAtual, EstadoDispositivo novoEstado)
		throws BusinessException {
		this.estadoAnterior = estadoAtual;
		this.state = estadoAtual.recuperarDispositivoState(this);

		if (novoEstado == null) {
			throw new BusinessException(BusinessExceptionMessages.MUDANCA_ESTADO_INVALIDA);
		}

		novoEstado.movimentarDispositivo(listaDispositivos, this);

		DispositivoDAO dao = new DispositivoDAO();

		for (DispositivoVO dispositivoVO : listaDispositivos) {

			Dispositivo dispositivo = dao.recuperaDispositivoPeloId(dispositivoVO.getIdDispositivo());
			dispositivo.setTpEstado(dispositivoVO.getEstadoAtual());

	        try {
	            dao.gravar(dispositivo);
	        } catch (DataBaseException exception) {
	            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
	        }
		}
	}

	/**
	 * Método que muda para o estado Manutencao.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void manutencao(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.fazerManutencao(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Método que muda para o estado Pronto.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void pronto(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.validarDispositivo(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Método que muda para o estado Defeito.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void defeito(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.abrirDefeito(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Método que muda para o estado Uso.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void uso(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.utilizarDispositivo(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Método que muda para o estado Fabrica.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void fabrica(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.devolverFabrica(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Método que muda para o estado Devolvido.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void devolvido(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.devolverDispositivo(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Método que muda para o estado Descarte.
	 *
	 * @param listaDispositivos
	 *            Dispositivos a serem alterados
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void descarte(List<DispositivoVO> listaDispositivos) throws BusinessException {
		state.descartarDispositivo(listaDispositivos);
		gravarHistorico(listaDispositivos, estadoAnterior);
	}

	/**
	 * Não é possível mudar para o estado Novo a partir de nenhum outro estado,
	 * sempre lança esception.
	 *
	 * @exception BusinessException
	 *                Mudança não permitida
	 */
	public void novo() throws BusinessException {
		this.state.criarDispositivo();
	}

	/**
	 * Método que define qual é a instancia State a ser chamada.
	 *
	 * @param estado
	 *            Implementacao de State
	 */
	public void setEstado(DispositivoState estado) {
		this.state = estado;
	}

	public DispositivoState getNovoState() {
		return novoState;
	}

	public void setNovoState(DispositivoState novoState) {
		this.novoState = novoState;
	}

	public DispositivoState getManutencaoState() {
		return manutencaoState;
	}

	public void setManutencaoState(DispositivoState manutencaoState) {
		this.manutencaoState = manutencaoState;
	}

	public DispositivoState getProntoState() {
		return prontoState;
	}

	public void setProntoState(DispositivoState prontoState) {
		this.prontoState = prontoState;
	}

	public DispositivoState getUsoState() {
		return usoState;
	}

	public void setUsoState(DispositivoState usoState) {
		this.usoState = usoState;
	}

	public DispositivoState getDevolvidoState() {
		return devolvidoState;
	}

	public void setDevolvidoState(DispositivoState devolvidoState) {
		this.devolvidoState = devolvidoState;
	}

	public DispositivoState getDefeitoState() {
		return defeitoState;
	}

	public void setDefeitoState(DispositivoState defeitoState) {
		this.defeitoState = defeitoState;
	}

	public DispositivoState getFabricaState() {
		return fabricaState;
	}

	public void setFabricaState(DispositivoState fabricaState) {
		this.fabricaState = fabricaState;
	}

	public DispositivoState getDescarteState() {
		return descarteState;
	}

	public void setDescarteState(DispositivoState descarteState) {
		this.descarteState = descarteState;
	}

	public DispositivoState getState() {
		return state;
	}

	public void setState(DispositivoState state) {
		this.state = state;
	}

	public EstadoDispositivo getEstadoAnterior() {
		return estadoAnterior;
	}

	public void setEstadoAnterior(EstadoDispositivo estadoAnterior) {
		this.estadoAnterior = estadoAnterior;
	}

	private void gravarHistorico(List<DispositivoVO> listaDispositivos, EstadoDispositivo estadoAnterior) {
		for (DispositivoVO dispositivoVO : listaDispositivos) {

			HistDispositivoVO histDispositivoVO = new HistDispositivoVO();
			histDispositivoVO.setDispositivo(dispositivoVO);
			histDispositivoVO.setEstadoAnterior(estadoAnterior.getValue());
			histDispositivoVO.setDthrMudaEstado(new Date());
			histDispositivoVO.setIdDispositivo(dispositivoVO.getIdDispositivo());

			HistDispositivo entity = ObjectUtils.parse(histDispositivoVO);

			HistDispositivoDAO dao = new HistDispositivoDAO();

	        try {
	            dao.gravar(entity);
	        } catch (DataBaseException exception) {
	            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
	        }
		}
	}
}
