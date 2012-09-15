package br.com.sw2.test.dispositivo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.business.InventarioDispositivoBusiness;
import br.com.sw2.gac.dao.HistDispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.HistDispositivo;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.RelHistDispositivoVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * Classe que testa o relatório Histórico de Dispositivos.
 * @author ddiniz
 */
public class RelatorioHistoricoDispositivoTest {

	private static final String ID = "1111111111111";
	private DispositivoBusiness business = new DispositivoBusiness();
	private DispositivoVO dispositivo;
	private Date data = new Date();

	/**
	 * setup.
	 */
	@Before
	public void setUp() {
		dispositivo = incluirDispositivo();
		mudarDeNovoParaManutencao(dispositivo);
	}

	/**
	 * 	teardown.
	 */
	@After
	public void tearDown() {
		apagarHistoricoDispositivo(ID);
		apagarDispositivo(ID);
	}

	/**
	 * Testa a recuperação dos dados do relatório informando o ID do dispositivo.
	 */
	@Test
	public void testRecuperarDadosRelatorioHistDispositivoPorId() {
		List<RelHistDispositivoVO> lista = business.recuperaHistDispositivos(ID, null, null, null);
		Assert.assertNotNull(lista);
        Assert.assertTrue(lista.size() > 0);
    }

	/**
	 * Testa a recuperação dos dados do relatório informando a data de movimentação.
	 */
	@Test
	public void testRecuperarDadosRelatorioHistDispositivoPorData() {
		List<RelHistDispositivoVO> lista = business.recuperaHistDispositivos(null, null, data, null);
		Assert.assertNotNull(lista);
        Assert.assertTrue(lista.size() > 0);
    }

	/**
	 * Testa a recuperação dos dados do relatório informando o estado Atual.
	 */
	@Test
	public void testRecuperarDadosRelatorioHistDispositivoPorEstadoAtual() {
		List<RelHistDispositivoVO> lista = business.recuperaHistDispositivos(null, EstadoDispositivo.Manutencao.getValue(), null, null);
		Assert.assertNotNull(lista);
        Assert.assertTrue(lista.size() > 0);
    }

	/**
	 * Testa a recuperação dos dados do relatório informando o login.
	 */
	@Test
	public void testRecuperarDadosRelatorioHistDispositivoPorLogin() {
		List<RelHistDispositivoVO> lista = business.recuperaHistDispositivos(null, null, null, "admin");
		Assert.assertNotNull(lista);
        Assert.assertTrue(lista.size() > 0);
    }

	/**
	 * Testa a recuperação dos dados falha quando nenhum parâmetro é informado.
	 */
	@Test
	public void testRecuperarDadosRelatorioHistDispositivoSemParametro() {
		try {
			business.recuperaHistDispositivos(null, null, null, null);
			Assert.fail();
		} catch (BusinessException e) {
			Assert.assertEquals(BusinessExceptionMessages.PARAMETRO_OBRIGATORIO_RELATORIO_HISTDISPOSITIVO.toString(), e.getMessage());
		}
    }

	private DispositivoVO incluirDispositivo() {
		DispositivoVO dispositivo = new DispositivoVO();
		dispositivo.setIdDispositivo(ID);
		dispositivo.setUsuario(getUsuario());
		dispositivo.setEstadoAtual(1);
		dispositivo.setTipoDispositivo(TipoDispositivo.Pulseira.getValue());
		dispositivo.setDataEntrada(data);
		dispositivo.setDataFabricacao(data);
		dispositivo.setDataProximaManutencao(data);
		dispositivo.setDataSucata(data);
		dispositivo.setLocal(LocalizacaoDispositivo.EmUso.getValue());

		salvarDispositivo(dispositivo);

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

	private void salvarDispositivo(DispositivoVO dispositivo) {
		// Cria o dispositivo na base
		try {
			business.adicionarNovoDispositivo(dispositivo, dispositivo.getIdDispositivo());
		} catch (BusinessException exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}

	/**
	 * Mmudança de Novo para Manutenção.
	 * @param dispositivo
	 */
	private void mudarDeNovoParaManutencao(DispositivoVO dispositivo) {
		List<DispositivoVO> listaDispositivos = new ArrayList<DispositivoVO>();
		listaDispositivos.add(dispositivo);

		InventarioDispositivoBusiness inventario = new InventarioDispositivoBusiness();

		try {
			inventario.mudarEstado(listaDispositivos, EstadoDispositivo.Novo, EstadoDispositivo.Manutencao, "admin");
		} catch (BusinessException e) {
			e.printStackTrace();
			Assert.fail();
		}
	}

	private void apagarDispositivo(String id) {
		DispositivoBusiness business = new DispositivoBusiness();
	    try {
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
