package br.com.sw2.test.dispositivo;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * Testes de inventário de dispositivo.
 * OBS: Estes testes devem ser rodados em uma base sem dispositivos cadastrados
 * @author ddiniz
 */
public class InventarioDispositivoTest {

	private static final String ID_L = "lllllllllllll";
	private static final String ID_J = "jjjjjjjjjjjjj";
	private static final String ID_I = "iiiiiiiiiiiii";
	private static final String ID_F = "fffffffffffff";
	private static final String ID_E = "eeeeeeeeeeeee";
	private static final String ID_D = "ddddddddddddd";
	private static final String ID_C = "ccccccccccccc";
	private static final String ID_H = "hhhhhhhhhhhhh";
	private static final String ID_G = "ggggggggggggg";
	private static final String ID_B = "bbbbbbbbbbbbb";
	private static final String ID_A = "aaaaaaaaaaaaa";

	private DispositivoBusiness business = new DispositivoBusiness();

	private DispositivoVO dispositivoA;
	private DispositivoVO dispositivoG;
	private DispositivoVO dispositivoH;

	/**
	 * Criar os dispositivos para os testes.
	 */
	@Before
	public void setup() {
		// Incluir alguns dispositivos em diversos estados
		dispositivoA = incluirDispositivo(ID_A, EstadoDispositivo.Novo);
		incluirDispositivo(ID_B, EstadoDispositivo.Novo);
		incluirDispositivo(ID_C, EstadoDispositivo.Defeito);
		incluirDispositivo(ID_D, EstadoDispositivo.Descarte);
		incluirDispositivo(ID_E, EstadoDispositivo.Devolvido);
		incluirDispositivo(ID_F, EstadoDispositivo.Fabrica);
		dispositivoG = incluirDispositivo(ID_G, EstadoDispositivo.Manutencao);
		dispositivoH = incluirDispositivo(ID_H, EstadoDispositivo.Manutencao);
		incluirDispositivo(ID_I, EstadoDispositivo.Pronto);
		incluirDispositivo(ID_J, EstadoDispositivo.Pronto);
		incluirDispositivo(ID_L, EstadoDispositivo.Uso);
	}

	/**
	 * Apagar os dispositivos após os testes.
	 */
	@After
	public void tearDown() {
		apagarDispositivo(ID_A);
		apagarDispositivo(ID_B);
		apagarDispositivo(ID_C);
		apagarDispositivo(ID_D);
		apagarDispositivo(ID_E);
		apagarDispositivo(ID_F);
		apagarDispositivo(ID_G);
		apagarDispositivo(ID_H);
		apagarDispositivo(ID_I);
		apagarDispositivo(ID_J);
		apagarDispositivo(ID_L);
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Novo.
	 */
	@Test
	public void testListarDispositivosEstadoNovo() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Novo.getValue());
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_A, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_B, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado em Manutenção.
	 */
	@Test
	public void testListarDispositivosEstadoManutencao() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Manutencao.getValue());
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_G, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_H, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado com Defeito.
	 */
	@Test
	public void testListarDispositivosEstadoDefeito() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Defeito.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_C, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Descartado.
	 */
	@Test
	public void testListarDispositivosEstadoDescarte() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Descarte.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_D, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Devolvido.
	 */
	@Test
	public void testListarDispositivosEstadoDevolvido() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Devolvido.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_E, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado na Fabrica.
	 */
	@Test
	public void testListarDispositivosEstadoFabrica() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Fabrica.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_F, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Pronto.
	 */
	@Test
	public void testListarDispositivosEstadoPronto() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Pronto.getValue());
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_I, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_J, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado em Uso.
	 */
	@Test
	public void testListarDispositivosEstadoUso() {
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Uso.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_L, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Testa que somente Pulseiras e Centrais são retornadas.
	 */
	@Test
	public void testListarApenasDispositivosPulseiraECentral() {
		// Muda o tipo de dispositivo A, cujo estado é Novo, para Pingente, que não deve ser retornado pelo método
		dispositivoA.setTipoDispositivo(TipoDispositivo.Pingente.getValue());
		salvarDispositivo(dispositivoA);
		List<DispositivoVO> listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Novo.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_B, listaDispositivos.get(0).getIdDispositivo());

		// Muda o tipo de dispositivo G, cujo estado é Manutenção, para Central, que deve ser retornado pelo método
		dispositivoG.setTipoDispositivo(TipoDispositivo.CentralEletronica.getValue());
		salvarDispositivo(dispositivoG);
		// Muda o tipo de dispositivo H, cujo estado é Manutenção, para Relógio, que não deve ser retornado pelo método
		dispositivoH.setTipoDispositivo(TipoDispositivo.Relogio.getValue());
		salvarDispositivo(dispositivoH);
		listaDispositivos = business.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Manutencao.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_G, listaDispositivos.get(0).getIdDispositivo());

	}

	private DispositivoVO incluirDispositivo(String id, EstadoDispositivo estado) {
		DispositivoVO dispositivo = new DispositivoVO();
		dispositivo.setIdDispositivo(id);
		dispositivo.setUsuario(getUsuario());
		dispositivo.setEstadoAtual(estado.getValue());
		dispositivo.setTipoDispositivo(TipoDispositivo.Pulseira.getValue());
		Date data = new Date();
		dispositivo.setDataEntrada(data);
		dispositivo.setDataFabricacao(data);
		dispositivo.setDataProximaManutencao(data);
		dispositivo.setDataSucata(data);
		dispositivo.setLocal(LocalizacaoDispositivo.EmUso.getValue());

		salvarDispositivo(dispositivo);

		return dispositivo;
	}

	private void salvarDispositivo(DispositivoVO dispositivo) {
		// Cria o dispositivo na base
		try {
			business.adicionarNovoDispositivo(dispositivo, dispositivo.getIdDispositivo());
		} catch (BusinessException exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}

	private UsuarioVO getUsuario() {
		UsuarioVO usuario = new UsuarioVO();

		String admin = "admin";
		usuario.setLogin(admin);
		usuario.setSenha(admin);
		usuario.setNomeUsuario(admin);

		PerfilVO perfil = new PerfilVO();
		perfil.setIdPerfil(1);
		usuario.setPerfil(perfil);

		return usuario;
	}

	private void apagarDispositivo(String id) {
        DispositivoBusiness business = new DispositivoBusiness();
        try {
            // Remove dispositivo da Base
            business.apagarDispositivo(id);
        } catch (BusinessException exception) {
            exception.printStackTrace();
        }
	}
}
