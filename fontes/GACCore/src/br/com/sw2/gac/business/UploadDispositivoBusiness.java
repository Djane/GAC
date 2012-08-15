package br.com.sw2.gac.business;

import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.ArquivoVO;


/**
 * Classe de negócio responsável pela carga de dispositivos.
 * @author eduardopax
 */
public class UploadDispositivoBusiness {

	/**
	 * Processa o arquivo selecionado, realizando a carga dos dispositivos.
	 * @param arquivo ArquivoVO
	 * @throws BusinessException exception
	 */
    public void processarArquivo(ArquivoVO arquivo) throws BusinessException {

        if (null != arquivo) {
            throw new BusinessException(BusinessExceptionMessages.DISPOSITIVO_DUPLICADO);
        }
    }

}
