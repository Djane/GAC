package br.com.sw2.test.dispositivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.business.UploadDispositivoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.vo.ArquivoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;


/**
 * Testes de carga de dispositivos.
 *
 * @author eduardopax
 *
 */
public class UploadDispositivoTest {

	private static final String ID = "abcd0123fg456";

	/**
	 * Setup a ser executado antes dos testes.
	 */
	@Before
	public void setup() {
	    // Garantir que o dispositivo que ser� usado nos testes n�o existe na base
	    apagarDispositivo(ID);
	}

	/**
     * Teste de carga de arquivo correto.
     */
    @Test
    public void testCarregarArquivoCorreto() {
    	String nomeArquivoTemp = "c:/temp/testeOk.txt";
    	//Cria um arquivo com apenas 2 colunas
    	List<String> linhasArquivo = new ArrayList<>();
    	linhasArquivo.add(ID + ";1;01/10/2012;1");

    	try {
			gerarArquivoTemp(linhasArquivo, nomeArquivoTemp);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

    	ArquivoVO arquivoVO = new ArquivoVO();
    	arquivoVO.setCaminho(nomeArquivoTemp);
    	arquivoVO.setUsuario(getUsuario());

    	UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();

    	try {
    		uploadDispositivoBusiness.processarArquivo(arquivoVO);
		} catch (Exception exception) {
			Assert.fail();
		}
    }

    /**
     * Teste de carga de arquivo doms dispositivos duplicados no arquivo de carga.
     */
    @Test
    public void testCarregarArquivoDispositivosDuplicadosNaCarga() {
    	String nomeArquivoTemp = "c:/temp/testeOk.txt";
    	//Cria um arquivo com apenas 2 colunas
    	List<String> linhasArquivo = new ArrayList<>();
    	linhasArquivo.add(ID + ";1;01/10/2012;1");
    	linhasArquivo.add(ID + ";1;01/10/2012;1");

    	try {
			gerarArquivoTemp(linhasArquivo, nomeArquivoTemp);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

    	ArquivoVO arquivoVO = new ArquivoVO();
    	arquivoVO.setCaminho(nomeArquivoTemp);
    	arquivoVO.setUsuario(getUsuario());

    	UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();

    	try {
    		uploadDispositivoBusiness.processarArquivo(arquivoVO);
		} catch (Exception exception) {
			List<String> criticas = uploadDispositivoBusiness.recuperarCriticas();
			Assert.assertEquals("Linha: 2 - Erro: dispositivo 'abcd0123fg456' já existente na carga", criticas.get(0));
		}
    }

    /**
     * Teste de carga de arquivo doms dispositivos no banco.
     */
    @Test
    public void testCarregarArquivoDispositivosNoBanco() {
    	String nomeArquivoTemp = "c:/temp/testeOk.txt";
    	//Cria um arquivo com apenas 2 colunas
    	List<String> linhasArquivo = new ArrayList<>();
    	linhasArquivo.add(ID + ";1;01/10/2012;1");

    	try {
			gerarArquivoTemp(linhasArquivo, nomeArquivoTemp);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

    	ArquivoVO arquivoVO = new ArquivoVO();
    	arquivoVO.setCaminho(nomeArquivoTemp);
    	arquivoVO.setUsuario(getUsuario());

    	UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();

    	try {
    		uploadDispositivoBusiness.processarArquivo(arquivoVO);
		} catch (Exception exception) {
			List<String> criticas = uploadDispositivoBusiness.recuperarCriticas();
			Assert.assertEquals("Linha: 1 - Erro: dispositivo 'abcd0123fg456' já existente no banco", criticas.get(0));
		}
    }


	/**
     * Teste de carga de arquivo com tamanho de colunas inválidas.
     */
    @Test
    public void testCarregarArquivoComColunasFaltantes() {
    	String nomeArquivoTemp = "c:/temp/teste1.txt";
    	//Cria um arquivo com apenas 2 colunas
    	List<String> linhasArquivo = new ArrayList<>();
    	linhasArquivo.add("1111;2222");

    	try {
			gerarArquivoTemp(linhasArquivo, nomeArquivoTemp);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

    	ArquivoVO arquivoVO = new ArquivoVO();
    	arquivoVO.setCaminho(nomeArquivoTemp);

    	UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();

    	try {
    		uploadDispositivoBusiness.processarArquivo(arquivoVO);
		} catch (Exception exception) {
			List<String> criticas = uploadDispositivoBusiness.recuperarCriticas();
			Assert.assertEquals("Linha: 1 - Erro: Quantidade de colunas inválida", criticas.get(0));
		}
    }

    /**
     * Teste de carga de arquivo com a coluna Tipo de Dispositivo errado.
     */
    @Test
    public void testCarregarArquivoComColunaTipoDispositivoErrado() {
    	String nomeArquivoTemp = "c:/temp/teste2.txt";
    	//Cria um arquivo com apenas 2 colunas
    	List<String> linhasArquivo = new ArrayList<>();
    	linhasArquivo.add("1111;2222;222;222");

    	try {
			gerarArquivoTemp(linhasArquivo, nomeArquivoTemp);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

    	ArquivoVO arquivoVO = new ArquivoVO();
    	arquivoVO.setCaminho(nomeArquivoTemp);

    	UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();

    	try {
    		uploadDispositivoBusiness.processarArquivo(arquivoVO);
		} catch (Exception exception) {
			List<String> criticas = uploadDispositivoBusiness.recuperarCriticas();
			Assert.assertEquals("Linha: 1 - Erro: Valor do Tipo de Dispositivo inválido", criticas.get(0));
		}
    }

    /**
     * Teste de carga de arquivo com a coluna de Data de Fabricacao errada.
     */
    @Test
    public void testCarregarArquivoComColunaDataDeFabricacaoErrada() {
    	String nomeArquivoTemp = "c:/temp/teste2.txt";
    	//Cria um arquivo com apenas 2 colunas
    	List<String> linhasArquivo = new ArrayList<>();
    	linhasArquivo.add("1111;1;222;222");

    	try {
			gerarArquivoTemp(linhasArquivo, nomeArquivoTemp);
		} catch (IOException e1) {
			e1.printStackTrace();
			Assert.fail();
		}

    	ArquivoVO arquivoVO = new ArquivoVO();
    	arquivoVO.setCaminho(nomeArquivoTemp);

    	UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();

    	try {
    		uploadDispositivoBusiness.processarArquivo(arquivoVO);
		} catch (Exception exception) {
			List<String> criticas = uploadDispositivoBusiness.recuperarCriticas();
			Assert.assertEquals("Linha: 1 - Erro: Valor da Data de Fabricação inválida", criticas.get(0));
		}
    }

    /**
     * Gera um arquivo com as linhas solicitadas.
     * @param linhas
     * @throws IOException
     */
    private void gerarArquivoTemp(final List<String> linhas, final String nomeArquivo) throws IOException {
		File arq = new File(nomeArquivo);

		try {
			//Gera um arquivo novo e substiti caso exista
			FileWriter fileWriter = new FileWriter(arq, false);

			PrintWriter printWriter = new PrintWriter(fileWriter);

			for (String linha : linhas) {
				printWriter.println(linha);
			}

			// o método flush libera a escrita no arquivo
			printWriter.flush();

			// No final precisamos fechar o arquivo
			printWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
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

}
