package br.com.sw2.test.dispositivo;

import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * Testes de cadastro de dispositivo.
 *
 * @author ddiniz
 *
 */
public class CadastroDispositivoTest {

	private static final String ID = "9999999999999";
	private static final String ID_LETRAS = "abcd0123fg456";
	private DispositivoVO dispositivo = new DispositivoVO();

	/**
	 * Setup a ser executado antes dos testes.
	 */
	@Before
	public void setup() {
	    // Garantir que o dispositivo que será usado nos testes não existe na base
	    apagarDispositivo(ID);
	    apagarDispositivo(ID_LETRAS);
	}

    /**
     * Teste de inclusão de dispositivo.
     */
    @Test
    public void testIncluirDispositivo() {

    	dispositivo.setIdDispositivo(ID);
    	dispositivo.setUsuario(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
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
            Assert.fail();
        }

        // Recuper o dispositivo do banco pelo ID
        DispositivoDAO dao = new DispositivoDAO();
        try {
        	Dispositivo dispositivoObj = dao.recuperaDispositivoPeloId(ID);
        	Assert.assertNotNull(dispositivoObj);
        	Assert.assertEquals(data, dispositivoObj.getDtaEntrada());
        	Assert.assertEquals(data, dispositivoObj.getDtaFabrica());
        	Assert.assertEquals(data, dispositivoObj.getDtaProximaManut());
        	Assert.assertEquals(data, dispositivoObj.getDtaSucata());
        	Assert.assertEquals(getUsuario().getLogin(), dispositivoObj.getLogin().getLogin());
        	Assert.assertEquals(EstadoDispositivo.Novo.getValue(), dispositivoObj.getTpEstado());
        	Assert.assertEquals(TipoDispositivo.Pingente.getValue(), dispositivoObj.getTpDispositivo());
        	Assert.assertEquals(LocalizacaoDispositivo.EmUso.getValue(), dispositivoObj.getLocal());
        } catch (DataBaseException exception) {
        	exception.printStackTrace();
            Assert.fail();
        }

        // Remove o dispositivo para zerar a base
        apagarDispositivo(ID);
    }

    /**
     * Teste de inclusão de dispositivo com ID com tamanho diferente de 13.
     */
    @Test
    public void testIncluirDispositivoIdTamanhoIncorreto() {

    	DispositivoVO novoDispositivo = new DispositivoVO();
    	novoDispositivo.setIdDispositivo("1234");

    	DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(novoDispositivo, null);
            Assert.fail();
        } catch (BusinessException exception) {
            Assert.assertEquals(BusinessExceptionMessages.ID_DISPOSITIVO_TAMANHO_INVALIDO.toString(), exception.getMessage());
        }
    }

    /**
     * Teste de inclusão de dispositivo com ID com valor alfanumérico.
     */
    @Test
    public void testIncluirDispositivoIdAlfanumerico() {

    	DispositivoVO novoDispositivo = new DispositivoVO();
    	novoDispositivo.setIdDispositivo(ID_LETRAS);
    	novoDispositivo.setUsuario(getUsuario());
    	novoDispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
    	novoDispositivo.setTipoDispositivo(TipoDispositivo.Pulseira.getValue());
        Date data = new Date();
        novoDispositivo.setDataFabricacao(data);

    	DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(novoDispositivo, null);
            Assert.fail();
        } catch (BusinessException exception) {
            Assert.assertEquals(BusinessExceptionMessages.ID_DISPOSITIVO_VALOR_INVALIDO.toString(), exception.getMessage());
        }
    }

    /**
     * Teste de inclusão de dispositivo nulo.
     */
    @Test
    public void testIncluirDispositivoNulo() {

    	DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(null, null);
            Assert.fail();
        } catch (BusinessException exception) {
            Assert.assertEquals(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS.toString(), exception.getMessage());
        }
    }

    /**
     * Teste de alteração de um dispositivo.
     */
    @Test
    public void testAlterarEstadoDispositivo() {

        dispositivo.setIdDispositivo(ID);
        dispositivo.setUsuario(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());

    	DispositivoBusiness business = new DispositivoBusiness();
    	try {
    	    // Cria dispositivo em estado Novo
    	    business.adicionarNovoDispositivo(dispositivo, null);
    	    // Altera o estado do dispositivo
    	    dispositivo.setEstadoAtual(EstadoDispositivo.Manutencao.getValue());
            business.adicionarNovoDispositivo(dispositivo, ID);
    	} catch (BusinessException exception) {
            exception.printStackTrace();
            Assert.fail();
        }

    	DispositivoDAO dao = new DispositivoDAO();
        try {
            // Recupera o dispositivo pelo ID
        	Dispositivo dispositivoObj = dao.recuperaDispositivoPeloId(ID);
        	// Valida que o estado foi alterado
        	Assert.assertEquals(EstadoDispositivo.Manutencao.getValue(), dispositivoObj.getTpEstado());
        } catch (DataBaseException exception) {
        	exception.printStackTrace();
            Assert.fail();
        }

        // Remove dispositivo para zerar base
        apagarDispositivo(ID);
    }

    /**
     * Teste de inclusão de dispositivo duplicado.
     * Não serão permitidos dispositivos com identificadores (número de série ou código de barra) iguais, mesmo sendo de tipos diferentes.
     */
    @Test
    public void testIncluirDispositivoDuplicado() {

        dispositivo.setIdDispositivo(ID);
        dispositivo.setUsuario(getUsuario());
        dispositivo.setTipoDispositivo(TipoDispositivo.CentralEletronica.getValue());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
    	DispositivoVO dispositivo2 = dispositivo;
    	dispositivo2.setTipoDispositivo(TipoDispositivo.Pingente.getValue());

    	// Insere dois dispositivos com mesmo id
        DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(dispositivo, null);
            business.adicionarNovoDispositivo(dispositivo2, null);
            Assert.fail();
        } catch (BusinessException exception) {
        	Assert.assertEquals(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO.toString(), exception.getMessage());
        }

        // Remove o dispositivo para zerar a base
        apagarDispositivo(ID);
    }

    /**
     * Teste de exclus�o de dispositivo.
     */
    @Test
    public void testApagarDispositivo() {

        dispositivo.setIdDispositivo(ID);
        dispositivo.setUsuario(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());

        DispositivoBusiness business = new DispositivoBusiness();
        try {
            // Cria o dispositivo na base
            business.adicionarNovoDispositivo(dispositivo, null);
            // Remove dispositivo da Base
            business.apagarDispositivo(ID);
        } catch (BusinessException exception) {
            exception.printStackTrace();
            Assert.fail();
        }

        DispositivoDAO dao = new DispositivoDAO();

        // Verifica que o dispositivo n�o existe mais na base
        try {
        	Dispositivo dispositivoObj = dao.recuperaDispositivoPeloId(ID);
        	Assert.assertNull(dispositivoObj);
        } catch (DataBaseException exception) {
        	exception.printStackTrace();
            Assert.fail();
        }
    }

    /**
     * Teste de inclusão de dispositivo duplicado.
     */
    @Test
    public void testIncluirDispositivoIdDuplicado() {
    	dispositivo.setIdDispositivo(ID);
        dispositivo.setUsuario(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
        DispositivoVO dispositivo2 = dispositivo;

        DispositivoBusiness business = new DispositivoBusiness();
        try {
            // Cria o dispositivo na base
            business.adicionarNovoDispositivo(dispositivo, null);
            // Incluir dispositivo duplicado
            business.adicionarNovoDispositivo(dispositivo2, null);
            Assert.fail();
        } catch (BusinessException exception) {
        	Assert.assertEquals(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO.toString(), exception.getMessage());
        }

        // Remove o dispositivo para zerar a base
        apagarDispositivo(ID);
    }

    /**
     * Teste de alteração de ID do dispositivo para outro já existente.
     */
    @Test
    public void testAlterarDispositivoIdDuplicado() {
    	dispositivo.setIdDispositivo(ID);
        dispositivo.setUsuario(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
        DispositivoVO dispositivo2 = new DispositivoVO();
        String id2 = "1234567890999";
		dispositivo2.setIdDispositivo(id2);
        dispositivo2.setUsuario(getUsuario());
        dispositivo2.setEstadoAtual(EstadoDispositivo.Novo.getValue());

        DispositivoBusiness business = new DispositivoBusiness();
        try {
            // Cria um dispositivo na base com ID
            business.adicionarNovoDispositivo(dispositivo, null);
            // Incluir outro dispositivo com outro id
            business.adicionarNovoDispositivo(dispositivo2, null);
            // alterar o id do segundo dispositivo para valor igual ao primeiro
            dispositivo2.setIdDispositivo(ID);
            business.adicionarNovoDispositivo(dispositivo2, ID);
            Assert.fail();
        } catch (BusinessException exception) {
        	Assert.assertEquals(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO.toString(), exception.getMessage());
        }

        // Remove o dispositivo para zerar a base
        apagarDispositivo(ID);
        apagarDispositivo(id2);
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
            business.apagarDispositivo(id);
        } catch (BusinessException exception) {
            exception.printStackTrace();
        }
    }

}
