package br.com.sw2.gac.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.ArquivoVO;


/**
 * Classe de negócio responsável pela carga de dispositivos.
 * @author eduardopax
 */
public class UploadDispositivoBusiness {

	private static final int QTDE_COLUNAS_CARGA = 4;
	private List<String> criticas;

	/**
	 * Processa o arquivo selecionado, realizando a carga dos dispositivos.
	 * @param arquivo ArquivoVO
	 * @throws BusinessException exception
	 */
    public void processarArquivo(final ArquivoVO arquivo) throws BusinessException {
    	//Limpa a lista de criticas
    	criticas = new ArrayList<String>();
        try {
        	//carrega o arquivo
        	List<String> arquivoDispositivos = carregarArquivo(arquivo.getCaminho());

        	if (!verificaArquivoValido(arquivoDispositivos)) {
        		throw new BusinessException(BusinessExceptionMessages.FALHA_CARGA_DISPOSITIVOS);
        	}

        	//monta as entidades para serem persistidas
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionMessages.FALHA_CARGA_DISPOSITIVOS);
		}
    }


    /**
     * Verifica se o arquivo está na estrutura correta. Caso não esteja, gera as criticas e devolve uma Exception
     * @param List com as linhas contidas no arquivo carregado
     * @return true ou false
     */
    private boolean verificaArquivoValido(final List<String> arquivoDispositivos) throws Exception {
    	String[] carga;
    	int contLinha = 1;
    	for (String linha : arquivoDispositivos) {
			carga = linha.split(";");
			if (carga.length < QTDE_COLUNAS_CARGA) {
				criticas.add("Linha: " + contLinha + " Erro: Quantidade de colunas inválida ");
			}
		}
    	return true;
    }

    /**
     * Carrega o arquivo fisico em uma lista com as linhas para ser convertida.
     * @param arquivo
     * @return Lista contendo as linhas do arquivo de carga
     * @throws IOException
     */
    private List<String> carregarArquivo(final String arquivo) throws IOException {
		List<String> dispositivos = new ArrayList<String>();
		String linha = null;
		try {
			// instancia do arquivo que vou ler
			FileReader reader = new FileReader(arquivo);
			BufferedReader leitor = new BufferedReader(reader);

			// loop que percorrerá todas as linhas do arquivo.txt que eu quero
			// ler
			while ((linha = leitor.readLine()) != null) {
				dispositivos.add(linha);
			}

			leitor.close();
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		return dispositivos;
    }

}
