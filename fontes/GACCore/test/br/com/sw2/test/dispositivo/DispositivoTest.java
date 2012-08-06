package br.com.sw2.test.dispositivo;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.dao.DispositivoDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Testes de dispositivo.
 *
 * @author ddiniz
 *
 */
public class DispositivoTest {

	private static final String ID = "abcd0123fg456";
	private DispositivoVO dispositivo = new DispositivoVO();

	/**
	 * Setup a ser executado antes dos testes.
	 */
	@Before
	public void setup() {
	    // Garantir que o dispositivo que será usado nos testes não existe na base
	    apagarDispositivo(ID);
	}

    /**
     * Teste de inclusão de dispositivo.
     */
    @Test
    public void testIncluirDispositivo() {

    	dispositivo.setId(ID);
    	dispositivo.setLogin(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo);
        dispositivo.setTipoDispositivo(TipoDispositivo.Pingente);

        // Cria o dispositivo na base
        DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(dispositivo);
        } catch (BusinessException exception) {
            exception.printStackTrace();
            Assert.fail();
        }

        // Recuper o dispositivo do banco pelo ID
        DispositivoDAO dao = new DispositivoDAO();
        try {
        	Dispositivo dispositivoObj = dao.recuperaDispositivoPeloId(ID);
        	Assert.assertNotNull(dispositivoObj);
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
    	novoDispositivo.setId("1234");

    	DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(novoDispositivo);
        } catch (BusinessException exception) {
            Assert.assertEquals(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS.toString(), exception.getMessage());
        }
    }

    /**
     * Teste de inclusão de dispositivo nulo.
     */
    @Test
    public void testIncluirDispositivoNulo() {

    	DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(null);
        } catch (BusinessException exception) {
            Assert.assertEquals(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS.toString(), exception.getMessage());
        }
    }

    /**
     * Teste de alteração de um dispositivo.
     */
    @Test
    public void testAlterarEstadoDispositivo() {

        dispositivo.setId(ID);
        dispositivo.setLogin(getUsuario());
        dispositivo.setEstadoAtual(EstadoDispositivo.Novo);

    	DispositivoBusiness business = new DispositivoBusiness();
    	try {
    	    // Cria dispositivo em estado Novo
    	    business.adicionarNovoDispositivo(dispositivo);
    	    // Altera o estado do dispositivo
    	    dispositivo.setEstadoAtual(EstadoDispositivo.Manutencao);
            business.atualizarDispositivo(dispositivo);
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
        }

        // Remove dispositivo para zerar base
        apagarDispositivo(ID);
    }

    /**
     * Teste de alteração de um dispositivo inválido.
     */
    @Test
    public void testAlterarDispositivoNulo() {
    	DispositivoBusiness business = new DispositivoBusiness();
    	try {
            business.atualizarDispositivo(null);
    	} catch (BusinessException exception) {
            Assert.assertEquals(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS.toString(), exception.getMessage());
        }
    }

    /**
     * Teste de inclusão de dispositivo duplicado.
     * Não serão permitidos dispositivos com identificadores (número de série ou código de barra) iguais, mesmo sendo de tipos diferentes.
     */
    @Test
    public void testIncluirDispositivoDuplicado() {

        dispositivo.setId(ID);
        dispositivo.setLogin(getUsuario());
        dispositivo.setTipoDispositivo(TipoDispositivo.CentralEletronica);
    	DispositivoVO dispositivo2 = dispositivo;
    	dispositivo2.setTipoDispositivo(TipoDispositivo.Pingente);

    	// Insere dois dispositivos com mesmo id
        DispositivoBusiness business = new DispositivoBusiness();
        try {
            business.adicionarNovoDispositivo(dispositivo);
            business.adicionarNovoDispositivo(dispositivo2);
        } catch (BusinessException exception) {
        	Assert.assertEquals(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO.toString(), exception.getMessage());
        }

        // Remove o dispositivo para zerar a base
        apagarDispositivo(ID);
    }

    /**
     * Teste de exclusão de dispositivo.
     */
    @Test
    public void testApagarDispositivo() {

        dispositivo.setId(ID);
        dispositivo.setLogin(getUsuario());

        DispositivoBusiness business = new DispositivoBusiness();
        try {
            // Cria o dispositivo na base
            business.adicionarNovoDispositivo(dispositivo);
            // Remove dispositivo da Base
            business.apagarDispositivo(ID);
        } catch (BusinessException exception) {
            exception.printStackTrace();
            Assert.fail();
        }

        DispositivoDAO dao = new DispositivoDAO();

        // Verifica que o dispositivo não existe mais na base
        try {
        	Dispositivo dispositivoObj = dao.recuperaDispositivoPeloId(ID);
        	Assert.assertNull(dispositivoObj);
        } catch (DataBaseException exception) {
        	exception.printStackTrace();
            Assert.fail();
        }
    }

    private Usuario getUsuario() {
		Usuario usuario = new Usuario();
        String admin = "admin";
		usuario.setLogin(admin);
        usuario.setSenha(admin);
        usuario.setNmUsuario(admin);
        usuario.setCdPerfil(1);
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
