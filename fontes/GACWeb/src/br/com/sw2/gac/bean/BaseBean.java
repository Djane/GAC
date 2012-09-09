package br.com.sw2.gac.bean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.Sexo;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.tools.UFBrasil;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.LoggerUtils;
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
    private LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /**
     * Construtor Padrao Instancia um novo objeto BaseBean.
     */
    public BaseBean() {

        // Monta lista de estados Brasileiros
        this.listaUf = getSelectIems(UFBrasil.class);

        // Lista de sexo para combo
        this.listaSexo = getSelectIems(Sexo.class);

        // Formas de contato
        this.listaFormaContato = getSelectIems(TipoContato.class);
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
     * Nome: setRequestAttribute Armazena um atributo no HttpServletRequest.
     * @param str the str
     * @param obj the obj
     * @see
     */
    public void setRequestAttribute(String str, Object obj) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        request.setAttribute(str, obj);
    }

    /**
     * Nome: getRequestParameter Recupera o valor de um atributo colocado no HttpServletRequest,
     * convertendo-o para uma String'.
     * @param str the str
     * @return valor do atributo 'requestParameter'
     * @see
     */
    public String getRequestParameter(String str) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        return (String) request.getParameter(str);
    }

    /**
     * Nome: getMessageFromBundle Recupera o valor de uma chave de mensagem do message bundle.
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
        EqualPredicate equalPredicate = new EqualPredicate(fieldValue);
        BeanPredicate beanPredicate = new BeanPredicate(field, equalPredicate);
        return CollectionUtils.find(list, beanPredicate);
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
        EqualPredicate equalPredicate = new EqualPredicate(fieldValue);
        BeanPredicate beanPredicate = new BeanPredicate(field, equalPredicate);
        return CollectionUtils.find(list, beanPredicate);
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
    public static <T extends Enum<T>> List<SelectItem> getSelectIems(Class<T> enumType) {
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
     * Nome: getSelectItens Recupera o valor do atributo 'selectItens'.
     * @param lista the lista
     * @param idPropertyName the id property name
     * @param valuePropertyName the value property name
     * @return valor do atributo 'selectItens'
     * @see
     */
    public static final List<SelectItem> getSelectItens(List<? extends Object> lista,
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
            e.printStackTrace();
        }
        return usuario;
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
     * Nome: getUrlBase Retorna a url da aplicação ate o contexto.
     * @return valor do atributo 'urlBase'
     * @see
     */
    public String getUrlBase() {

        HttpServletRequest request = getHttpServLetRequest();
        String url = request.getScheme() + "://" + request.getServerName() + ":"
                + request.getServerPort() + request.getContextPath();

        return url;
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

}
