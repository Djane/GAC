package br.com.sw2.gac.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.primefaces.event.FileUploadEvent;

import br.com.sw2.gac.business.UploadDispositivoBusiness;
import br.com.sw2.gac.util.ClassLoaderUtils;
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

    private List<String> listaCriticas = new ArrayList<String>();

    private String nomeCaminhoArquivoCritica;

    private static final String IMAGEM = "/primefaces-smartangel/images/smartangel-150-90.jpg";
	private static final String CRITICAS_CARGA_DISPOSITIVO_JASPER = "criticasCargaDispositivo.jasper";

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
    	//limpa as mensagens de criticas
    	listaCriticas = new ArrayList<String>();

    	ArquivoVO arquivoVO = new ArquivoVO();
        arquivoVO.setDataEnvio(new Date());
        arquivoVO.setCaminho(destination + event.getFile().getFileName());

     // Recuperar o usuÃ¡rio logado na sessÃ£o e colocar no VO do dispositivo
        BaseBean base = new BaseBean();
        arquivoVO.setUsuario(base.getUsuarioLogado());

        UploadDispositivoBusiness uploadDispositivoBusiness = new UploadDispositivoBusiness();
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());

            uploadDispositivoBusiness.processarArquivo(arquivoVO);

            setFacesMessage("message.uploaddispositivo.upload.sucess");
            arquivoVO.setStatus(PROCESSADO);
            this.listaArquivos.add(arquivoVO);
        } catch (Exception e) {
        	this.listaArquivos.add(arquivoVO);
        	this.nomeCaminhoArquivoCritica = arquivoVO.getCaminho();
            e.printStackTrace();
            setFacesErrorMessage("message.cadastrodispositivo.save.error");
            arquivoVO.setStatus(ERRO);
            listaCriticas = uploadDispositivoBusiness.recuperarCriticas();
        }

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
     * Imprimir relatório histórico de dispositivos.
     * @param event the event
     */
    public void imprimirHistoricoDispositivos(ActionEvent event) {

        FacesContext context = FacesContext.getCurrentInstance();

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(listaCriticas);

        //Abre o arquivo .jasper contendo o relatorio
        InputStream inputStream = ClassLoaderUtils.getJasperFileAsStream(CRITICAS_CARGA_DISPOSITIVO_JASPER);

        exportarRelatorio(context, beanCollectionDataSource, inputStream);
    }


	private void exportarRelatorio(FacesContext context,
			JRBeanCollectionDataSource beanCollectionDataSource,
			InputStream inputStream) {
		try {
            //Exporta o relatorio.
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("LOGO_SMARTANGEL", getUrlBase() + IMAGEM);
            parameters.put("NOME_CAMINHO_ARQUIVO_CRITICA", nomeCaminhoArquivoCritica);
            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters, beanCollectionDataSource);
            HttpServletResponse response = getHttpServletResponse();
            response.reset();
            response.setContentType("application/pdf");
            response.addHeader("Content-disposition", "inline; filename=relatorio.pdf");
            ServletOutputStream servletOutputStream = (ServletOutputStream) getHttpServletResponse().getOutputStream();

            JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);
            context.getApplication().getStateManager().saveView(context);

            // Fecha o stream do response
            response.getOutputStream().flush();
            response.getOutputStream().close();
            context.responseComplete();

        } catch (Exception e) {
            getLogger().error(e);
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

    public List<String> getListaCriticas() {
		return listaCriticas;
	}

	public void setListaCriticas(List<String> listaCriticas) {
		this.listaCriticas = listaCriticas;
	}

}
