package br.com.sw2.gac.bean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.socket.SocketPhone;
import br.com.sw2.gac.tools.Sexo;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.tools.UFBrasil;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.JasperHelper;
import br.com.sw2.gac.util.LoggerUtils;
import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.util.ObjectUtils;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Classe básica para os managed beans.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BaseBean implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 4847092966042002700L;

    /** Atributo logger. */
    protected LoggerUtils logger = null;

    /** Indicador para controle de operações de CRUD Create, Read, Update, Delete. */
    private String crud;

    /** Atributo url base. */
    private String urlBase;

    /** Atributo handler. */
    private NavigationHandler handler;

    /** Atributo gac properties. */
    private Properties gacProperties = null;

    /**
     * Construtor Padrao Instancia um novo objeto BaseBean.
     */
    public BaseBean() {
        this.logger = LoggerUtils.getInstance(getClass());

        // Monta lista de estados Brasileiros
        this.listaUf = getSelectItems(UFBrasil.class);

        // Lista de sexo para combo
        this.listaSexo = getSelectItems(Sexo.class);

        // Formas de contato
        this.listaFormaContato = getSelectItems(TipoContato.class);

        HttpServletRequest request = getHttpServLetRequest();
        this.urlBase = request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + request.getContextPath();

    }

    /** Atributo locale. */
    private String locale = "pt_BR";

    /** Local onde as imagens do thema ficam armazenadas. */
    private String urlImage = "primefaces-smartangel/images";

    /** Lista contendo a sigla e nome dos estados brasileiros. */
    private List<SelectItem> listaUf;

    /** Atributo lista sexo. */
    private List<SelectItem> listaSexo;

    /** Atributo lista forma contato. */
    private List<SelectItem> listaFormaContato;

    /** Atributo data atual. */
    private Date dataAtual = DateUtil.getDataAtual();

    /**
     * Atributo faces context.
     * @return valor do atributo 'locale'
     * @see
     */

    /**
     * Nome: getLocale Recupera o valor do atributo 'locale'.
     * @return valor do atributo 'locale'
     * @see
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Nome: setLocale Registra o valor do atributo 'locale'.
     * @param locale valor do atributo locale
     * @see
     */
    public void setLocale(String locale) {
        this.locale = locale;
    }

    /**
     * Nome: getUrlImage Recupera o valor do atributo 'urlImage'.
     * @return valor do atributo 'urlImage'
     * @see
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * Nome: setUrlImage Registra o valor do atributo 'urlImage'.
     * @param urlImage valor do atributo url image
     * @see
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     * Nome: getListaUf Recupera o valor do atributo 'listaUf'.
     * @return valor do atributo 'listaUf'
     * @see
     */
    public List<SelectItem> getListaUf() {
        return listaUf;
    }

    /**
     * Nome: setListaUf Registra o valor do atributo 'listaUf'.
     * @param listaUf valor do atributo lista uf
     * @see
     */
    public void setListaUf(List<SelectItem> listaUf) {
        this.listaUf = listaUf;
    }

    /**
     * Nome: getListaSexo Recupera o valor do atributo 'listaSexo'.
     * @return valor do atributo 'listaSexo'
     * @see
     */
    public List<SelectItem> getListaSexo() {
        return listaSexo;
    }

    /**
     * Nome: setListaSexo Registra o valor do atributo 'listaSexo'.
     * @param listaSexo valor do atributo lista sexo
     * @see
     */
    public void setListaSexo(List<SelectItem> listaSexo) {
        this.listaSexo = listaSexo;
    }

    /**
     * Nome: getListaFormaContato Recupera o valor do atributo 'listaFormaContato'.
     * @return valor do atributo 'listaFormaContato'
     * @see
     */
    public List<SelectItem> getListaFormaContato() {
        return listaFormaContato;
    }

    /**
     * Nome: setListaFormaContato Registra o valor do atributo 'listaFormaContato'.
     * @param listaFormaContato valor do atributo lista forma contato
     * @see
     */
    public void setListaFormaContato(List<SelectItem> listaFormaContato) {
        this.listaFormaContato = listaFormaContato;
    }

    /**
     * Nome: setTituloCabecalho Registra o valor do atributo 'tituloCabecalho'.
     * @param str valor do atributo titulo cabecalho
     * @see
     */
    public void setTituloCabecalho(String str) {
        setTituloCabecalho(str, false);
    }

    /**
     * Nome: setTituloCabecalho Sets the titulo cabecalho atraves de uma string ou chave do messagem
     * bundle.
     * @param str the str
     * @param iskey the iskey
     * @see
     */
    public void setTituloCabecalho(String str, boolean iskey) {
        if (iskey) {
            setRequestAttribute("screenTitle", getMessageFromBundle(str));
        } else {
            setRequestAttribute("screenTitle", str);
        }
    }

    /**
     * Nome: setRequestAttribute
     * Armazena um atributo no presente pedido. Atributos são redefinidos entre os pedidos.
     *
     * @param name - uma String especificando o nome do attributo a ser armazenado
     * @param obj - O objeto a ser armazenado
     * @see javax.servlet.http.HttpServletRequest
     */
    public void setRequestAttribute(String name, Object obj) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.setAttribute(name, obj);
    }

    /**
     * Nome: getRequestAttribute
     * Retorna o valor do atributo nomeado como um objeto, ou nulo se nenhum atributo do nome dado existir.
     *
     * @param name - a String especificando o nome do atributo a ser recuperado.
     * @return um Object contendo o valor do attributo, ou null se o atributo não existir.
     * @see javax.servlet.http.HttpServletRequest
     */
    public Object getRequestAttribute(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return request.getAttribute("ativarMonitorSocket");
    }

    /**
     * Nome: getRequestBooleanAttribute
     * Retorna o valor de um atributo boolean colocado na request. Caso o atributo não exista,
     * seja nulo ou contenha um valor não conversível para "true/false" retorna false.
     *
     * @param name - a String especificando o nome do atributo a ser recuperado.
     * @return um Boolean contendo o valor do attributo, ou null se o atributo não existir.
     * @see javax.servlet.http.HttpServletRequest
     */
    public Boolean getRequestBooleanAttribute(String name) {
        Boolean retorno;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        Object valor = request.getAttribute("ativarMonitorSocket");
        if (null == valor) {
            retorno = false;
        } else {
            try {
                retorno =  (Boolean) valor;
            } catch (Exception e) {
                retorno = false;
            }
        }
        return retorno;
    }


    /**
     * Nome: getRequestParameter
     * Retorna o valor de um parametro na request no formato String. O valor será nulo se o parametro não existir.
     *
     * @param name - uma String especificando o nome do parametro
     * @return uma String representando o vaor do parametro
     * @see javax.servlet.http.HttpServletRequest
     */
    public String getRequestParameter(String name) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return (String) request.getParameter(name);
    }

    /**
     * Nome: getMessageFromBundle Recupera o valor de uma chave de mensagem do message bundle.
     *
     * @param key the key
     * @return valor do atributo 'messageFromBundle'
     * @see
     */
    public String getMessageFromBundle(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication()
            .getResourceBundle(context, "messageBundle");
        String message = bundle.getString(key);
        return message;
    }

    /**
     * Nome: getMessageFromBundle Recupera o valor de uma mensagem no message bundle através de sua
     * chave.
     * @param key Chave contendo a mensagem no message bundle.
     * @param args Valores dos parâmetros das mensagens.
     * @return message Mensagem enconrada no message bundle.
     * @see
     */
    public String getMessageFromBundle(String key, Object... args) {
        String message = getMessageFromBundle(key);
        if (args != null && args.length > 0) {
            message = MessageFormat.format(message, args);
        }
        return message;
    }

    /**
     * Nome: setFacesErrorMessage Adiciona uma mensagem ao Faces Message com severidade ERROR, a
     * partir de uma chave no message bundle.
     * @param key Chave no message bundle contendo a mensagem a ser exibida.
     * @see
     */
    public void setFacesErrorMessage(String key) {
        this.setFacesErrorMessage(key, true);
    }

    /**
     * Nome: setFacesErrorMessage Sets the faces error message.
     * @param str Mensagem a ser exibida. Pode ser uma string com mensagem ou uma chave no message
     *            bundle.
     * @param isKey se true indica uma keu no message bundle para mensagem. False indica que esta já
     *            é a mensagem.
     * @see
     */
    public void setFacesErrorMessage(String str, boolean isKey) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage facesMessage = null;
        if (isKey) {
            facesMessage = new FacesMessage(getMessageFromBundle(str), getMessageFromBundle(str));
        } else {
            facesMessage = new FacesMessage(str, str);
        }

        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, facesMessage);
    }

    /**
     * Nome: setFacesErrorBusinessMessage Sets the faces error message.
     * @param businessMensagem BusinessExceptionMessages contendo a mensagem a ser exibida.
     * @param id Indica em qual componente serão exibidas as mensagens.
     * @see
     */
    public void setFacesErrorBusinessMessage(BusinessExceptionMessages businessMensagem, String id) {
        FacesMessage facesMessage = new FacesMessage(businessMensagem.getLabel());
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(id, facesMessage);
    }

    /**
     * Adiciona uma mensagem ao Faces Message, com severidade ERROR, a partir de uma
     * BusinessMessageEsception.
     * @param businessMensagem BusinessExceptionMessages contendo a mensagem a ser exibida.
     * @see
     */
    public void setFacesErrorBusinessMessage(BusinessExceptionMessages businessMensagem) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(businessMensagem.getLabel());
        facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, facesMessage);
    }

    /**
     * Nome: setFacesMessage
     * Sets the faces message.
     *
     * @param str the str
     * @param isKey the is key
     * @see
     */
    public void setFacesMessage(String str, boolean isKey) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = null;
        if (isKey) {
            facesMessage = new FacesMessage(getMessageFromBundle(str), getMessageFromBundle(str));
        } else {
            facesMessage = new FacesMessage(str, str);
        }
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, facesMessage);
    }

    /**
     * Nome: setFacesMessage. Adiciona uma mensagem ao facesMessage. O valor do resumo e detalhe da
     * mensagem ficam iguais.
     * @param key chave da mensagem no mesasge bundle.
     * @see
     */
    public void setFacesMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(getMessageFromBundle(key),
            getMessageFromBundle(key));
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, facesMessage);
    }

    /**
     * Nome: setFacesMessage Adiciona uma mensagem ao facesMessage.
     * @param keyTitle the key title
     * @param keyDetail the key detail
     * @see
     */
    public void setFacesMessage(String keyTitle, String keyDetail) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage facesMessage = new FacesMessage(getMessageFromBundle(keyTitle),
            getMessageFromBundle(keyDetail));
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, facesMessage);
    }

    /**
     * Nome: findInListById Find in list by id.
     * @param list the list
     * @param field the field
     * @param fieldValue the field value
     * @return object
     * @see
     */
    public Object findInListById(List<?> list, String field, Integer fieldValue) {
        return CollectionUtils.findByAttribute(list, field, fieldValue);
    }

    /**
     * Nome: findInListById Find in list by id.
     * @param list the list
     * @param field the field
     * @param fieldValue the field value
     * @return object
     * @see
     */
    public Object findInListById(List<?> list, String field, String fieldValue) {
        return CollectionUtils.findByAttribute(list, field, fieldValue);
    }

    /**
     * Nome: retornarMenuPrincipal Retornar ao menu principal.
     * @return string
     * @see
     */
    public String retornarMenuPrincipal() {
        return "menuPrincipal";
    }

    /**
     * Nome: getSelectIems Converte um Enum em uma lista de SelectItem'.
     * @param <T> the generic type
     * @param enumType the enum type
     * @return valor do atributo 'selectIems'
     * @see
     */
    public static <T extends Enum<T>> List<SelectItem> getSelectItems(Class<T> enumType) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        for (T item : enumType.getEnumConstants()) {
            try {
                selectItems.add(new SelectItem(ObjectUtils.getPropertyValue("value", item),
                    ObjectUtils.getPropertyValue("label", item).toString()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return selectItems;
    }

    /**
     * Nome: getSelectItems Recupera o valor do atributo 'selectItens'.
     * @param lista the lista
     * @param idPropertyName the id property name
     * @param valuePropertyName the value property name
     * @return valor do atributo 'selectItens'
     * @see
     */
    public static final List<SelectItem> getSelectItems(List<? extends Object> lista,
        String idPropertyName, String valuePropertyName) {
        List<SelectItem> selectItems = new ArrayList<SelectItem>();
        SelectItem selectItem = null;

        try {
            for (Object item : lista) {
                selectItem = new SelectItem();
                selectItem.setValue(ObjectUtils.getPropertyValue(idPropertyName, item));
                selectItem.setLabel(ObjectUtils.getPropertyValue(valuePropertyName, item)
                    .toString());
                selectItems.add(selectItem);
            }
        } catch (Exception exception) {
            selectItems = new ArrayList<SelectItem>();
            exception.printStackTrace();
        }

        return selectItems;
    }

    /**
     * Nome: getUsuarioLogado Retorna os dados do usu�rio logado no sistema'.
     * @return valor do atributo 'usuarioLogado'
     * @see
     */
    public UsuarioVO getUsuarioLogado() {
        UsuarioVO usuario;
        try {
            HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
                .getSession(false);
            usuario = (UsuarioVO) session.getAttribute("usuariovo");
        } catch (Exception e) {
            usuario = new UsuarioVO();
            usuario.setLogin("anônimo");
            this.getLogger().error("Não foi possível localizar o usuario da sessão !");
        }
        return usuario;
    }

    /**
     * Nome: logoff Efetua logoff da aplicação.
     * @return string
     * @see
     */
    public String logoff() {

        //Limpara dados de atendimento
        SocketPhone  socketPhone =  (SocketPhone) getSessionAttribute("socketPhone");
        if (null != socketPhone && null != socketPhone.getSocket()) {
            try {
                socketPhone.fecharConexaoSocket(this.getUsuarioLogado().getRamal());
            } catch (Exception e) {
                this.logger.error("Erro ao remover socketPhone da sessão.");
            }
        }

        HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
            .getSession(false);
        session.removeAttribute("usuariovo");

        return "login";
    }

    /**
     * Nome: invokarPagina Método que invoca uma página baseada em seu codigo.
     * @return string
     * @see
     */
    public String invokarPagina() {
        String toViewId = "";
        Integer codigo = Integer.parseInt(getRequestParameter("codigoModulo"));

        for (MenuItem item : MenuItem.values()) {
            if (codigo.intValue() == item.getCodigoModulo().intValue()) {
                if (!item.getScreeTitle().equals("")) {
                    setTituloCabecalho(item.getScreeTitle(), true);
                }
                toViewId = item.getViewID();
            }
        }
        return toViewId;
    }

    /**
     * Nome: exportJasperToXls Export jasper to xls.
     * @param jasperFile the jasper file
     * @param beanCollection the bean collection
     * @param beanParameters the bean parameters
     * @return streamed content
     * @see
     */
    public StreamedContent exportJasperToXls(String jasperFile, Collection<?> beanCollection,
        Map<String, Object> beanParameters) {
        StreamedContent retorno = null;
        this.getLogger().debug("***** Iniciando exportJasperToXls *****");
        FacesContext context = FacesContext.getCurrentInstance();

        if (CollectionUtils.isNotEmptyOrNull(beanCollection)) {
            // Seta o dataSource
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                beanCollection);
            // Abre o arquivo .jasper contendo o relatorio
            InputStream inputStream = JasperHelper.getJasperFileAsStream(jasperFile);
            InputStream relatorio = null;
            try {
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.putAll(JasperHelper.getParameterLogoSmartAngel(getHttpServLetRequest()));
                if (beanParameters != null) {
                    parameters.putAll(beanParameters);
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,
                    beanCollectionDataSource);
                JExcelApiExporter exporterXLS = new JExcelApiExporter();
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                    Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                    Boolean.TRUE);
                exporterXLS.setParameter(
                    JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                ByteArrayOutputStream xls = new ByteArrayOutputStream();
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, xls);
                exporterXLS.exportReport();
                context.getApplication().getStateManager().saveView(context);
                relatorio = new ByteArrayInputStream(xls.toByteArray());

            } catch (Exception e) {
                this.getLogger().error(
                    "Problemas na geração da visualização do relatório " + jasperFile);
                this.getLogger().error(e);
            }
            retorno = new DefaultStreamedContent(relatorio, "application/vnd.ms-excel",
                "relatorio.xls");
        }
        this.getLogger().debug("***** Finalizado exportJasperToXls *****");
        return retorno;
    }

    /**
     * Nome: printJasperToXls Imprimir um relatorio jasper no response.
     * @param jasperFile the jasper file
     * @param beanCollection the bean collection
     * @param beanParameters the bean parameters
     * @see
     */
    public void printJasperToXls(String jasperFile, Collection<?> beanCollection,
        Map<String, Object> beanParameters) {
        this.getLogger().debug("***** Iniciando imprimirRelatorioPadrao *****");
        FacesContext context = FacesContext.getCurrentInstance();
        this.handler = context.getApplication().getNavigationHandler();
        if (CollectionUtils.isNotEmptyOrNull(beanCollection)) {
            // Seta o dataSource
            JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(
                beanCollection);
            // Abre o arquivo .jasper contendo o relatorio
            InputStream inputStream = JasperHelper.getJasperFileAsStream(jasperFile);
            try {
                Map<String, Object> parameters = new HashMap<String, Object>();
                parameters.putAll(JasperHelper.getParameterLogoSmartAngel(getHttpServLetRequest()));
                if (beanParameters != null) {
                    parameters.putAll(beanParameters);
                }

                JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parameters,
                    beanCollectionDataSource);
                HttpServletResponse response = getHttpServletResponse();
                response.reset();

                response.setContentType("application/vnd.ms-excel");
                response.addHeader("content-disposition", "attachment;filename=relatorio.xls");
                JExcelApiExporter exporterXLS = new JExcelApiExporter();
                exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,
                    Boolean.FALSE);
                exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,
                    Boolean.TRUE);
                exporterXLS.setParameter(
                    JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
                ByteArrayOutputStream xls = new ByteArrayOutputStream();
                exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, xls);
                exporterXLS.exportReport();
                context.getApplication().getStateManager().saveView(context);
                response.getOutputStream().flush();
                response.getOutputStream().close();
                context.responseComplete();
                this.getLogger().debug("***** Finalizado imprimirRelatorioPadrao *****");
            } catch (Exception e) {
                this.getLogger().error(
                    "Problemas na geração da visualização do relatório " + jasperFile);
                this.getLogger().error(e);
                this.handler.handleNavigation(context, null, "jasperError");
            }
        }
    }

    /**
     * Nome: error
     * Error.
     *
     * @param obj the obj
     * @see
     */
    public void logarErro(Object obj) {
        this.logger.error(getClass(), obj);
    }

    /**
     * Nome: debug
     * Debug.
     *
     * @param obj the obj
     * @see
     */
    public void debug(Object obj) {
        this.logger.debug(getClass(), obj);
    }

    /**
     * Nome: addCallbackValidationError
     * Adds the callback validation error.
     *
     * @param param the param
     * @param value the value
     * @see
     */
    public void addCallbackParam(String param, Object value) {
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        if (null != reqCtx) {
            reqCtx.addCallbackParam(param, value);
        }
    }

    /**
     * Nome: addCallbackValidationError Adiciona o parametro validationError no RequestContext com o
     * valor informado.
     * @param value the value
     * @see
     */
    public void addCallbackValidationError(boolean value) {
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        if (null != reqCtx) {
            reqCtx.addCallbackParam("validationError", value);
        }
    }

    /**
     * Nome: setSessionAttribute Armazena um valor na session.
     * @param name the name
     * @param value the value
     * @see
     */
    public void setSessionAttribute(String name, Object value) {
        HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
            .getSession(false);
        session.setAttribute(name, value);
    }

    /**
     * Nome: getSessionAttribute Recupera o valor de um atributo da session.
     * @param name the name
     * @return valor do atributo 'sessionAttribute'
     * @see
     */
    public Object getSessionAttribute(String name) {
        HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
            .getSession(false);
        return session.getAttribute(name);
    }

    /**
     * Nome: removeSessionAttribute
     * Removes um atributo da sessão.
     *
     * @param name the name
     * @see
     */
    public void removeSessionAttribute(String name) {
        HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
            .getSession(false);
        if (null != session) {
            session.removeAttribute(name);
        }
    }

    /**
     * Nome: getContextParam Recupera o valor do atributo 'contextParam'.
     * @return valor do atributo 'contextParam'
     * @see
     */
    public String getExternalWorkFolder() {
        FacesContext context = FacesContext.getCurrentInstance();
        ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
        return (String) servletContext.getInitParameter("EXTERNAL_WORK_FOLDER");
    }

    /**
     * Nome: dispatchError500
     * Dispatch error500.
     *
     * @param msg the msg
     * @see
     */
    protected void dispatchError500(String msg) {
        this.getLogger().error(msg);
        setRequestAttribute("mensagemErro500", msg);
        NavigationHandler nh = getFacesContext().getApplication().getNavigationHandler();
        nh.handleNavigation(getFacesContext(), null, "c500b");
    }

    /**
     * Nome: dispatchErrorAtendimento
     * Dispatch error atendimento.
     *
     * @param msg the msg
     * @see
     */
    protected void dispatchErrorAtendimento(String msg) {
        this.getLogger().error(msg);
        setRequestAttribute("mensagemErro500", msg);
        NavigationHandler nh = getFacesContext().getApplication().getNavigationHandler();
        nh.handleNavigation(getFacesContext(), null, "mensagematendimento");
    }

    /**
     * Nome: getGACProperty Recupera o valor do atributo 'GACProperty'.
     * @param key the key
     * @return valor do atributo 'GACProperty'
     * @see
     */
    public String getGACProperty(String key) {
        String valor = "";
        if (null == this.gacProperties) {
            this.gacProperties = this.getGACProperties();
        }

        try {
            valor = this.gacProperties.getProperty(key);
        } catch (Exception e) {
            valor = "?";
            this.logger.error("Não é possível localizar a chave: " + key);
            this.logger.error(e);
        }


        return valor;
    }

    /**
     * Nome: getGACProperties
     * Recupera o valor do do arquivo de propriedades do GAC.
     *
     * @return valor do atributo 'GACProperties'
     * @see
     */
    public Properties getGACProperties() {
        Properties properties = new Properties();
        try {

            FileInputStream istream = new FileInputStream(getExternalWorkFolder()
                + "gac.properties");
            properties.load(istream);

        } catch (Exception e) {
            properties = null;
            this.logger
                .equals("Não e possível carregar o arquivo de propriedades: gac.properties em "
                    + this.getExternalWorkFolder());
        }

        return properties;
    }

    /**
     * Nome: getFacesContext Recupera o valor do atributo 'facesContext'.
     * @return valor do atributo 'facesContext'
     * @see
     */
    public FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Nome: getUrlBase Recupera o valor do atributo 'urlBase'.
     * @return valor do atributo 'urlBase'
     * @see
     */
    public String getUrlBase() {
        return urlBase;
    }

    /**
     * Nome: setUrlBase Registra o valor do atributo 'urlBase'.
     * @param urlBase valor do atributo url base
     * @see
     */
    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    /**
     * Nome: getHttpServLetRequest Recupera o valor do atributo HttpServletRequest.
     * @return valor do atributo 'httpServLetRequest'
     * @see
     */
    public HttpServletRequest getHttpServLetRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
            .getRequest();
    }

    /**
     * Nome: getHttpServletResponse Recupera o valor do atributo 'HttpServletResponse'.
     * @return valor do atributo 'httpServletResponse'
     * @see
     */
    public HttpServletResponse getHttpServletResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
            .getResponse();
    }

    /**
     * Nome: getDataAtual Recupera o valor do atributo 'dataAtual'.
     * @return valor do atributo 'dataAtual'
     * @see
     */
    public Date getDataAtual() {
        return dataAtual;
    }

    /**
     * Nome: setDataAtual Registra o valor do atributo 'dataAtual'.
     * @param dataAtual valor do atributo data atual
     * @see
     */
    public void setDataAtual(Date dataAtual) {
        this.dataAtual = dataAtual;
    }

    /**
     * Nome: getLogger Recupera o valor do atributo 'logger'.
     * @return valor do atributo 'logger'
     * @see
     */
    public LoggerUtils getLogger() {
        return logger;
    }

    /**
     * Nome: setLogger Registra o valor do atributo 'logger'.
     * @param logger valor do atributo logger
     * @see
     */
    public void setLogger(LoggerUtils logger) {
        this.logger = logger;
    }

    /**
     * Nome: getCrud Recupera o valor do atributo 'crud'.
     * @return valor do atributo 'crud'
     * @see
     */
    public String getCrud() {
        return crud;
    }

    /**
     * Nome: setCrud Registra o valor do atributo 'crud'.
     * @param crud valor do atributo crud
     * @see
     */
    public void setCrud(String crud) {
        this.crud = crud;
    }

    /**
     * Nome: getHandler Recupera o valor do atributo 'handler'.
     * @return valor do atributo 'handler'
     * @see
     */
    public NavigationHandler getHandler() {
        return handler;
    }

    /**
     * Nome: setHandler Registra o valor do atributo 'handler'.
     * @param handler valor do atributo handler
     * @see
     */
    public void setHandler(NavigationHandler handler) {
        this.handler = handler;
    }

}
