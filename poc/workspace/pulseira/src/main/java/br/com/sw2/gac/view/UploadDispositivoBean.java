package br.com.sw2.gac.view;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.FileUploadEvent;

import br.com.sw2.gac.vo.ArquivoVO;

/**
 * <b>Descrição: Controller da tela de upload de dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class UploadDispositivoBean extends BaseBean {

    /** Atributo destination. */
    private String destination = "/temp/";

    /** Atributo lista arquivos. */
    private List<ArquivoVO> listaArquivos;

    /**
     * Construtor Padrao Instancia um novo objeto UploadDispositivoBean.
     */
    public UploadDispositivoBean() {
        super();

        this.listaArquivos = GacMock.getListaArquivosUpload();

    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.uploaddispositivo.view.title", true);

        return "uploaddispositivo";
    }

    /**
     * Nome: upload Upload.
     * @param event the event
     * @see
     */
    public void upload(FileUploadEvent event) {

        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArquivoVO arq = new ArquivoVO();
        arq.setDataEnvio(new Date());
        arq.setStatus("Enviado");
        arq.setCaminho(destination + event.getFile().getFileName());
        this.listaArquivos.add(arq);

        setFacesMessage("message.uploaddispositivo.upload.sucess");
    }

    /**
     * Nome: copyFile Copy file.
     * @param fileName the file name
     * @param in the in
     * @see
     */
    public void copyFile(String fileName, InputStream in) {
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
            e.printStackTrace();
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
