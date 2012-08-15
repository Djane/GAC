package br.com.sw2.gac.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.com.sw2.gac.business.UploadDispositivoBusiness;
import br.com.sw2.gac.vo.ArquivoVO;

/**
 * <b>Descri��o: Controller da tela de upload de dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class UploadDispositivoBean extends BaseBean {

    private static final String PROCESSADO = "Processado";

	private static final String ERRO = "Erro";

	private static final long serialVersionUID = -8846651963791122112L;

	/** Atributo destination. */
    private String destination = "/temp/";

    /** Atributo lista arquivos. */
    private List<ArquivoVO> listaArquivos = new ArrayList<ArquivoVO>();

    /**
     * Construtor Padrao Instancia um novo objeto UploadDispositivoBean.
     */
    public UploadDispositivoBean() {
        super();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.uploaddispositivo.view.title", true);

        return "uploadDispositivo";
    }

    /**
     * Nome: upload Upload.
     * @param event the event
     * @see
     */
    public void upload(FileUploadEvent event) {
    	ArquivoVO arquivoVO = new ArquivoVO();
        arquivoVO.setDataEnvio(new Date());
        arquivoVO.setCaminho(destination + event.getFile().getFileName());
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());

            //processa o arquivo
            UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();
            uploadDispositivoBusiness.processarArquivo(arquivoVO);

            setFacesMessage("message.uploaddispositivo.upload.sucess");
            arquivoVO.setStatus(PROCESSADO);
        } catch (IOException e) {
            e.printStackTrace();
            setFacesErrorMessage("message.cadastrodispositivo.save.error");
            arquivoVO.setStatus(ERRO);
        }
        this.listaArquivos.add(arquivoVO);
    }

    /**
     * /**
     * Nome: copyFile Copy file.
     * @param fileName the file name
     * @param in the in
     * @throws IOException exceção
     */
    public void copyFile(String fileName, InputStream in) throws IOException {
        try {

            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            final int kbyte = 1024;
            byte[] bytes = new byte[kbyte];

            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }

            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Nome: getListaArquivos Recupera o valor do atributo 'listaArquivos'.
     * @return valor do atributo 'listaArquivos'
     * @see
     */
    public List<ArquivoVO> getListaArquivos() {
        return listaArquivos;
    }

    /**
     * Nome: setListaArquivos Registra o valor do atributo 'listaArquivos'.
     * @param listaArquivos valor do atributo lista arquivos
     * @see
     */
    public void setListaArquivos(List<ArquivoVO> listaArquivos) {
        this.listaArquivos = listaArquivos;
    }

}
