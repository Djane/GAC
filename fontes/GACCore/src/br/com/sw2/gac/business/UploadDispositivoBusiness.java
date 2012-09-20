package br.com.sw2.gac.business;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.vo.ArquivoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.UsuarioVO;


/**
 * Classe de negócio responsável pela carga de dispositivos.
 * @author eduardopax
 */
public class UploadDispositivoBusiness {

	private static final int TOTAL_TIPOS_DISPOSITIVO = 4;
	private static final int QTDE_COLUNAS_CARGA = 4;
	private List<String> criticas;

	private SimpleDateFormat  dtParser = new SimpleDateFormat("dd/MM/yyyyy");

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
        	if (!verificaDispositivoDuplicadoCarga(arquivoDispositivos)) {
        		throw new BusinessException(BusinessExceptionMessages.FALHA_CARGA_DISPOSITIVOS);
        	}
        	List<DispositivoVO> dispositivosVO = carregarDispositivosVO(arquivoDispositivos, arquivo.getUsuario());
        	if (!verificarDispositivoValido(dispositivosVO)) {
        		throw new BusinessException(BusinessExceptionMessages.FALHA_CARGA_DISPOSITIVOS);
        	}
        	if (!verificaDispositivoNoBanco(dispositivosVO)) {
        		throw new BusinessException(BusinessExceptionMessages.FALHA_CARGA_DISPOSITIVOS);
        	}
        	inserirDispositivos(dispositivosVO);

		} catch (BusinessException e) {
			throw e;
		} catch (Exception e) {
			throw new BusinessException(BusinessExceptionMessages.FALHA_CARGA_DISPOSITIVOS);
		}
    }

    /**
     * Insere os dispositivos no banco.
     * @param dispositivos
     */
    private void inserirDispositivos(List<DispositivoVO> dispositivos) {
    	// Cria o dispositivo na base
    	DispositivoBusiness dispositivoBusiness = new DispositivoBusiness();
    	for (DispositivoVO dispositivoVO : dispositivos) {
    		try {
            	dispositivoBusiness.adicionarNovoDispositivo(dispositivoVO, null);
            } catch (BusinessException exception) {
                exception.printStackTrace();
                throw exception;
            }
		}
    }

    /**
     * Verifica se existem dispositivos na carga que já existem no banco.
     * @param carregarDispositivosVO
     * @return true ou false
     */
    private boolean verificaDispositivoNoBanco(
			List<DispositivoVO> carregarDispositivosVO) {
    	boolean retorno = true;
    	DispositivoBusiness dispositivoBusiness = new DispositivoBusiness();
    	int contLinha = 1;

    	for (DispositivoVO dispositivoVO : carregarDispositivosVO) {
    		try {
    			dispositivoBusiness.verificarDispositivoDuplicado(dispositivoVO);
			} catch (Exception e) {
				criticas.add("Linha: " + contLinha + " - Erro: dispositivo '" + dispositivoVO.getIdDispositivo() + "' já existente no banco");
				retorno = false;
			}
    		contLinha++;
		}

    	return retorno;
	}

    /**
     * Verifica se o ID do dispositivo é valido.
     * @param dispositivosVO
     * @return true ou false
     */
    private boolean verificarDispositivoValido(
			List<DispositivoVO> dispositivosVO) {
    	boolean retorno = true;
    	DispositivoBusiness dispositivoBusiness = new DispositivoBusiness();
    	int contLinha = 1;

    	for (DispositivoVO dispositivoVO : dispositivosVO) {
    		try {
    			dispositivoBusiness.verificarDispositivoValido(dispositivoVO);
			} catch (BusinessException e) {
				criticas.add("Linha: " + contLinha + " - Erro: " + e.getBusinessMessage(e.getMessage()).getLabel());
				retorno = false;
			}
    		contLinha++;
		}

    	return retorno;
	}

	/**
     * Verifica se existe dispositivo duplicado no arquivo de carga.
     * @param arquivoDispositivos
     * @return true ou false
     */
    private boolean verificaDispositivoDuplicadoCarga(
			List<String> arquivoDispositivos) {
    	List<String> ids = new ArrayList<>();
    	boolean retorno = true;
    	String[] carga;
    	int contLinha = 1;
    	for (String linha : arquivoDispositivos) {
			carga = linha.split(";");
			//se conter o ID entao é duplicado
			if (ids.contains(carga[0])) {
				criticas.add("Linha: " + contLinha + " - Erro: dispositivo '" + carga[0] + "' já existente na carga");
				retorno = false;
			} else {
				ids.add(carga[0]);
			}
			contLinha++;
    	}

    	return retorno;
	}

	/**
	 * Converte as linhas em objetos VO.
	 * @param linhas
	 * @param usuario
	 * @return lista com dispositivos
	 * @throws ParseException
	 */
    private List<DispositivoVO> carregarDispositivosVO(final List<String> linhas , final UsuarioVO usuario) throws ParseException {
    	List<DispositivoVO> dispositivos = new ArrayList<>();

    	//monta as entidades para serem persistidas
    	String[] carga;
    	for (String linha : linhas) {
			carga = linha.split(";");
			DispositivoVO dispositivo = new DispositivoVO();
			dispositivo.setIdDispositivo(carga[0]);

			dispositivo.setTipoDispositivo(Integer.parseInt(carga[1]));
			dispositivo.setDataFabricacao(dtParser.parse(carga[2]));
			dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
			dispositivo.setUsuario(usuario);

			dispositivos.add(dispositivo);
		}

    	return dispositivos;
    }

    /**
     * Devolve a lista de criticas do ultimo processamento.
     * @return Lista com as criticas
     */
    public List<String> recuperarCriticas() {
    	return criticas;
    }

    /**
     * Verifica se o arquivo está na estrutura correta. Caso não esteja, gera as criticas e devolve uma Exception
     * @param List com as linhas contidas no arquivo carregado
     * @return true ou false
     */
    private boolean verificaArquivoValido(final List<String> arquivoDispositivos) throws Exception {
    	boolean retorno = true;
    	String[] carga;
    	int contLinha = 1;
    	for (String linha : arquivoDispositivos) {
			carga = linha.split(";");
			//Verifica a quantidade de colunas
			if (carga.length != QTDE_COLUNAS_CARGA) {
				criticas.add("Linha: " + contLinha + " - Erro: Quantidade de colunas inválida");
				retorno = false;
			} else {
				//verifica o tipo de dados das colunas já que o total de colunas é um total valido
				//Codigo do dispositivo é string entao nao copara.
				//Tipo do dispositivo
				try {
					if (Integer.parseInt(carga[1]) < 0  || Integer.parseInt(carga[1]) > TOTAL_TIPOS_DISPOSITIVO) {
						throw new Exception();
					}
				} catch (Exception e) {
					criticas.add("Linha: " + contLinha + " - Erro: Valor do Tipo de Dispositivo inválido");
					retorno = false;
				}

				//valida a data de fabricacao
				try {
					dtParser.parse(carga[2]);
				} catch (Exception e) {
					criticas.add("Linha: " + contLinha + " - Erro: Valor da Data de Fabricação inválida");
					retorno = false;
				}
			}
			contLinha++;
		}
    	return retorno;
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
