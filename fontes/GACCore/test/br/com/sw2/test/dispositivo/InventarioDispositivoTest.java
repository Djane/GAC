package br.com.sw2.test.dispositivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.business.InventarioDispositivoBusiness;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.dao.HistDispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.HistDispositivo;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * Testes de inventário de dispositivo. OBS: Estes testes devem ser rodados em
 * uma base sem dispositivos cadastrados
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

	private DispositivoBusiness dispositivoBusiness = new DispositivoBusiness();
	private DispositivoDAO dispositivoDao = new DispositivoDAO();

	private DispositivoVO dispositivoA;
	private DispositivoVO dispositivoB;
	private DispositivoVO dispositivoC;
	private DispositivoVO dispositivoD;
	private DispositivoVO dispositivoE;
	private DispositivoVO dispositivoF;
	private DispositivoVO dispositivoG;
	private DispositivoVO dispositivoH;
	private DispositivoVO dispositivoI;
	private DispositivoVO dispositivoL;

	/**
	 * Criar os dispositivos para os testes.
	 */
	@Before
	public void setup() {
		// Incluir alguns dispositivos em diversos estados
		dispositivoA = incluirDispositivo(ID_A, EstadoDispositivo.Novo);
		dispositivoB = incluirDispositivo(ID_B, EstadoDispositivo.Novo);
		dispositivoC = incluirDispositivo(ID_C, EstadoDispositivo.Defeito);
		dispositivoD = incluirDispositivo(ID_D, EstadoDispositivo.Descarte);
		dispositivoE = incluirDispositivo(ID_E, EstadoDispositivo.Devolvido);
		dispositivoF = incluirDispositivo(ID_F, EstadoDispositivo.Fabrica);
		dispositivoG = incluirDispositivo(ID_G, EstadoDispositivo.Manutencao);
		dispositivoH = incluirDispositivo(ID_H, EstadoDispositivo.Manutencao);
		dispositivoI = incluirDispositivo(ID_I, EstadoDispositivo.Pronto);
		incluirDispositivo(ID_J, EstadoDispositivo.Pronto);
		dispositivoL = incluirDispositivo(ID_L, EstadoDispositivo.Uso);
	}

	/**
	 * Apagar os dispositivos após os testes.
	 */
	@After
	public void tearDown() {
		// Remover registros da tabela de histórico
		apagarHistoricoDispositivo(ID_B);
		apagarHistoricoDispositivo(ID_C);
		apagarHistoricoDispositivo(ID_E);
		apagarHistoricoDispositivo(ID_G);
		apagarHistoricoDispositivo(ID_I);
		apagarHistoricoDispositivo(ID_L);

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
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Novo.getValue());
		// O banco não deve possuir outros dispositivos cadastrados
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_A, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_B, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado em
	 * Manutenção.
	 */
	@Test
	public void testListarDispositivosEstadoManutencao() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Manutencao.getValue());
		// O banco não deve possuir outros dispositivos cadastrados
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_G, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_H, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado com
	 * Defeito.
	 */
	@Test
	public void testListarDispositivosEstadoDefeito() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Defeito.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_C, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado
	 * Descartado.
	 */
	@Test
	public void testListarDispositivosEstadoDescarte() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Descarte.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_D, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado
	 * Devolvido.
	 */
	@Test
	public void testListarDispositivosEstadoDevolvido() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Devolvido.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_E, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado na
	 * Fabrica.
	 */
	@Test
	public void testListarDispositivosEstadoFabrica() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Fabrica.getValue());
		// O banco não deve possuir outros dispositivos cadastrados
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_F, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado
	 * Pronto.
	 */
	@Test
	public void testListarDispositivosEstadoPronto() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Pronto.getValue());
		// O banco não deve possuir outros dispositivos cadastrados
		Assert.assertEquals(2, listaDispositivos.size());
		Assert.assertEquals(ID_I, listaDispositivos.get(0).getIdDispositivo());
		Assert.assertEquals(ID_J, listaDispositivos.get(1).getIdDispositivo());
	}

	/**
	 * Teste que verifica se recupera os dispositivos que estão no estado em
	 * Uso.
	 */
	@Test
	public void testListarDispositivosEstadoUso() {
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Uso.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_L, listaDispositivos.get(0).getIdDispositivo());
	}

	/**
	 * Testa que somente Pulseiras e Centrais são retornadas.
	 */
	@Test
	public void testListarApenasDispositivosPulseiraECentral() {
		// Muda o tipo de dispositivo A, cujo estado é Novo, para Pingente, que
		// não deve ser retornado pelo método
		dispositivoA.setTipoDispositivo(TipoDispositivo.Pingente.getValue());
		salvarDispositivo(dispositivoA);
		List<DispositivoVO> listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Novo.getValue());
		// A tabela de dispositivo deve estar vazia antes de rodar os testes
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_B, listaDispositivos.get(0).getIdDispositivo());

		// Muda o tipo de dispositivo G, cujo estado é Manutenção, para Central,
		// que deve ser retornado pelo método
		dispositivoG.setTipoDispositivo(TipoDispositivo.CentralEletronica.getValue());
		salvarDispositivo(dispositivoG);
		// Muda o tipo de dispositivo H, cujo estado é Manutenção, para Relógio,
		// que não deve ser retornado pelo método
		dispositivoH.setTipoDispositivo(TipoDispositivo.Relogio.getValue());
		salvarDispositivo(dispositivoH);
		listaDispositivos = dispositivoBusiness
				.recuperaListaPulseiraECentralPorEstado(EstadoDispositivo.Manutencao.getValue());
		Assert.assertEquals(1, listaDispositivos.size());
		Assert.assertEquals(ID_G, listaDispositivos.get(0).getIdDispositivo());

	}

	/**
	 * Testa mudança de Novo para Manutenção.
	 */
	@Test
	public void testMudarDeNovoParaManutencao() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoB);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Novo, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_B);
		Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), dispositivo.getTpEstado());

		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_B);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Novo.getValue(), histDispositivo.getCdEstadoAnterior());
		}
	}

	/**
	 * Testa mudança de Novo para Uso, Devolvido, Descarte, Fabrica, Pronto e
	 * Defeito falha.
	 */
	@Test
	public void testMudarDeNovoParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoA);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Novo, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Novo, EstadoDispositivo.Devolvido);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Novo, EstadoDispositivo.Descarte);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.fabrica(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.pronto(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.defeito(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_NOVO_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Manutenção para Devolvido.
	 */
	@Test
	public void testMudarDeManutencaoParaDefeito() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoG);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Defeito);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_G);
		Assert.assertEquals(EstadoDispositivo.Defeito.getValue(), dispositivo.getTpEstado());

		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_G);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), histDispositivo.getCdEstadoAnterior());
		}

	}

	/**
	 * Testa mudança de Manutenção para Descarte.
	 */
	@Test
	public void testMudarDeManutencaoParaDescarte() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoG);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Descarte);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_G);

		Assert.assertEquals(EstadoDispositivo.Descarte.getValue(), dispositivo.getTpEstado());


		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_G);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), histDispositivo.getCdEstadoAnterior());
		}
	}

	/**
	 * Testa mudança de Manutenção para Pronto.
	 */
	@Test
	public void testMudarDeManutencaoParaPronto() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoG);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Pronto);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_G);

		Assert.assertEquals(EstadoDispositivo.Pronto.getValue(), dispositivo.getTpEstado());

		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_G);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), histDispositivo.getCdEstadoAnterior());
		}
	}

	/**
	 * Testa mudança de Manutencao para Novo, Uso, Fabrica e Devolvido falha.
	 */
	@Test
	public void testMudarDeManutencaoParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoG);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_MANUTENCAO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_MANUTENCAO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Devolvido);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_MANUTENCAO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Manutencao, EstadoDispositivo.Fabrica);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_MANUTENCAO_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Novo para Pronto.
	 */
	@Test
	public void testMudarDeProntoParaUso() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoI);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Pronto, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_I);

		Assert.assertEquals(EstadoDispositivo.Uso.getValue(), dispositivo.getTpEstado());

		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_I);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Pronto.getValue(), histDispositivo.getCdEstadoAnterior());
		}
	}

	/**
	 * Testa mudança de Manutencao para Novo, Manutencao, Fabrica, Defeito,
	 * Descarte e Devolvido falha.
	 */
	@Test
	public void testMudarDeProntoParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoI);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Pronto, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Pronto, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Pronto, EstadoDispositivo.Devolvido);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.fabrica(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.descarte(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.defeito(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_PRONTO_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Uso para Devolvido.
	 */
	@Test
	public void testMudarDeUsoParaDevolvido() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoL);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Uso, EstadoDispositivo.Devolvido);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_L);

		Assert.assertEquals(EstadoDispositivo.Devolvido.getValue(), dispositivo.getTpEstado());

		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_L);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Uso.getValue(), histDispositivo.getCdEstadoAnterior());
		}

	}

	/**
	 * Testa mudança de Uso para Novo, Manutencao, Pronto, Descarte, Fabrica e
	 * Defeito falha.
	 */
	@Test
	public void testMudarDeUsoParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoL);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Uso, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Uso, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Uso, EstadoDispositivo.Pronto);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.fabrica(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.descarte(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.defeito(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_USO_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Devolvido para Manutencao.
	 */
	@Test
	public void testMudarDeDevolvidoParaManutencao() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoE);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Devolvido, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_E);

		Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), dispositivo.getTpEstado());

		// Verificar se gravou mudança de estado no historico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_E);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Devolvido.getValue(), histDispositivo.getCdEstadoAnterior());
		}

	}

	/**
	 * Testa mudança de Devolvido para Novo, Uso, Pronto, Descarte, Fabrica e
	 * Defeito falha.
	 */
	@Test
	public void testMudarDeDevolvidoParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoE);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Devolvido, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEVOLVIDO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Devolvido, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEVOLVIDO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Devolvido, EstadoDispositivo.Pronto);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEVOLVIDO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.fabrica(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEVOLVIDO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.descarte(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEVOLVIDO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.defeito(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEVOLVIDO_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Descarte para Novo, Uso, Manutenção, Pronto, Defeito,
	 * Fabrica e Devolvido falha.
	 */
	@Test
	public void testMudarDeDescarteParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoD);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Descarte, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Descarte, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Descarte, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.pronto(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.fabrica(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.devolvido(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.defeito(listaDispositivos);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DESCARTE_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Defeito para manutenção.
	 */
	@Test
	public void testMudarDeDefeitoParaManutencao() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoC);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}

		Dispositivo dispositivo = dispositivoDao.recuperaDispositivoPeloId(ID_C);

		Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), dispositivo.getTpEstado());

		// Verifica que gravou no histórico
		List<HistDispositivo> hist = recuperarHistDipositivo(ID_C);
		Assert.assertEquals(hist.size(), 1);
		for (HistDispositivo histDispositivo : hist) {
			Assert.assertEquals(EstadoDispositivo.Defeito.getValue(), histDispositivo.getCdEstadoAnterior());
		}
	}

	/**
	 * Testa mudança de Defeito para Novo, Uso, Pronto, Defeito, Fabrica e
	 * Devolvido falha.
	 */
	@Test
	public void testMudarDeDefeitoParaEstadosInvalidos() {

		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoC);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEFEITO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEFEITO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Pronto);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEFEITO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Descarte);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEFEITO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Devolvido);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEFEITO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Fabrica);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_DEFEITO_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Defeito, EstadoDispositivo.Defeito);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_MESMO_ESTADO_INVALIDA.toString(), e.getLocalizedMessage());
		}
	}

	/**
	 * Testa mudança de Descarte para Novo, Uso, Manutenção, Pronto, Defeito,
	 * Descarte e Devolvido falha.
	 */
	@Test
	public void testMudarDeFabricaParaEstadosInvalidos() {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivoF);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Novo);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_FABRICA_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Manutencao);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_FABRICA_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Uso);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_FABRICA_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Pronto);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_FABRICA_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Descarte);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_FABRICA_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Devolvido);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_ESTADO_FABRICA_INVALIDA.toString(), e.getLocalizedMessage());
		}

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Fabrica, EstadoDispositivo.Fabrica);
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.MUDANCA_MESMO_ESTADO_INVALIDA.toString(), e.getLocalizedMessage());
		}
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
			dispositivoBusiness.adicionarNovoDispositivo(dispositivo, dispositivo.getIdDispositivo());
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

	private void apagarHistoricoDispositivo(String id) {
		HistDispositivoDAO dao = new HistDispositivoDAO();

		try {
			// Remove dispositivo da Base
			List<HistDispositivo> hist = recuperarHistDipositivo(id);
			for (HistDispositivo histDispositivo : hist) {
				dao.apagar(histDispositivo);
			}
		} catch (BusinessException exception) {
			exception.printStackTrace();
		}
	}

	private List<HistDispositivo> recuperarHistDipositivo(String id) {
		HistDispositivoDAO dao = new HistDispositivoDAO();

		List<HistDispositivo> result = null;
		try {
			result = dao.recuperaHistDispositivoPeloId(id);
		} catch (DataBaseException e) {
			e.printStackTrace();
			Assert.fail();
		}
		return result;
	}
}
