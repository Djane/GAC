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

	/**
	 * Criar os dispositivos para os testes.
	 */
	@Before
	public void setup() {
		// Incluir alguns dispositivos em diversos estados
		incluirDispositivo(ID_A, EstadoDispositivo.Novo);
		incluirDispositivo(ID_B, EstadoDispositivo.Novo);
		incluirDispositivo(ID_C, EstadoDispositivo.Defeito);
		incluirDispositivo(ID_D, EstadoDispositivo.Descarte);
		incluirDispositivo(ID_E, EstadoDispositivo.Devolvido);
		incluirDispositivo(ID_F, EstadoDispositivo.Fabrica);
		incluirDispositivo(ID_G, EstadoDispositivo.Manutencao);
		incluirDispositivo(ID_H, EstadoDispositivo.Manutencao);
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
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Novo);
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_A, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_B, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado em Manutenção.
	 */
	@Test
	public void testListarDispositivosEstadoManutencao() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Manutencao);
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_G, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_H, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado com Defeito.
	 */
	@Test
	public void testListarDispositivosEstadoDefeito() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Defeito);
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_C, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Descartado.
	 */
	@Test
	public void testListarDispositivosEstadoDescarte() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Descarte);
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_D, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Devolvido.
	 */
	@Test
	public void testListarDispositivosEstadoDevolvido() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Devolvido);
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_E, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado na Fabrica.
	 */
	@Test
	public void testListarDispositivosEstadoFabrica() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Fabrica);
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_F, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado Pronto.
	 */
	@Test
	public void testListarDispositivosEstadoPronto() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Pronto);
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_I, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_J, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado em Uso.
	 */
	@Test
	public void testListarDispositivosEstadoUso() {
		DispositivoBusiness business = new DispositivoBusiness();
		List<DispositivoVO> listaDispositivos = business.recuperaListaDispositivosPorEstado(EstadoDispositivo.Uso);
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_L, listaDispositivos.get(0).getIdDispositivo());
	}

	private DispositivoVO incluirDispositivo(String id, EstadoDispositivo estado) {
		DispositivoVO dispositivo = new DispositivoVO();
		dispositivo.setIdDispositivo(id);
		dispositivo.setUsuario(getUsuario());
		dispositivo.setEstadoAtual(estado.getValue());
		dispositivo.setTipoDispositivo(TipoDispositivo.Pingente.getValue());
		Date data = new Date();
		dispositivo.setDataEntrada(data);
		dispositivo.setDataFabricacao(data);
		dispositivo.setDataProximaManutencao(data);
		dispositivo.setDataSucata(data);
		dispositivo.setLocal(LocalizacaoDispositivo.EmUso.getValue());

		// Cria o dispositivo na base
		DispositivoBusiness business = new DispositivoBusiness();
		try {
			business.adicionarNovoDispositivo(dispositivo, null);
		} catch (BusinessException exception) {
			exception.printStackTrace();
		}

		return dispositivo;
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
