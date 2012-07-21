package br.com.smartangel.pulseira.view;

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

import br.com.smartangel.pulseira.vo.ArquivoVO;

@ManagedBean
@ViewScoped
public class UploadDispositivoBean extends BaseBean {

    private String destination = "/temp/";
    private List<ArquivoVO> listaArquivos;

    public UploadDispositivoBean() {
        super();

        this.listaArquivos = new ArrayList<ArquivoVO>();

        ArquivoVO arq = new ArquivoVO();
        arq.setDataEnvio(new Date(112, 06, 17));
        arq.setStatus("Processado");
        arq.setCaminho("/temp/planilha.txt");
        this.listaArquivos.add(arq);

    }

    public String iniciarPagina() {
        setTituloCabecalho("Upload de Arquivos Dispositivos");

        return "uploaddispositivo";
    }

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

    public void copyFile(String fileName, InputStream in) {
        try {

            OutputStream out = new FileOutputStream(new File(destination + fileName));

            int read = 0;
            byte[] bytes = new byte[1024];

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

    public List<ArquivoVO> getListaArquivos() {
        return listaArquivos;
    }

    public void setListaArquivos(List<ArquivoVO> listaArquivos) {
        this.listaArquivos = listaArquivos;
    }

}
