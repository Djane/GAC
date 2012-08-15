package br.com.sw2.test.dispositivo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import br.com.sw2.gac.business.UploadDispositivoBusiness;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.ArquivoVO;


/**
 * Testes de carga de dispositivos.
 *
 * @author eduardopax
 *
 */
public class UploadDispositivoTest {

	/**
     * Teste de carga de arquivo com tamanho de colunas inválidas.
     */
    @Test
    public void testCarregarArquivoComColunasFaltantes() {
    	String nomeArquivoTemp = "teste1.txt";
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
			Assert.assertEquals(BusinessExceptionMessages.SALVAR_DISPOSITIVO_DADOS_INVALIDOS.toString(), exception.getMessage());
		}
    	Assert.assertTrue(true);
    }

    /**
     * Gera um arquivo com as linhas solicitadas.
     * @param linhas
     * @throws IOException
     */
    private void gerarArquivoTemp(final List<String> linhas, final String nome) throws IOException {
		File arq = new File("/temp/" + nome);

		try {
			//Gera um arquivo novo e substiti caso exista
			FileWriter fileWriter = new FileWriter(arq, true);

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

}
