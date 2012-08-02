package br.com.sw2.gac.view;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.tools.Sexo;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.tools.UFBrasil;
import br.com.sw2.gac.util.ObjectUtils;

// TODO: Auto-generated Javadoc
/**
 * <b>Descrição: Super classe com métodos comuns aos managed beans</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BaseBean {

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

    /** Local onde as imagens do thema ficam armazenadas. */
    private String urlImage = "primefaces-smartangel/images";

    /** Lista contendo a sigla e nome dos estados brasileiros. */
    private List<SelectItem> listaUf;

    /** Atributo lista sexo. */
    private List<SelectItem> listaSexo;

    /** Atributo lista forma contato. */
    private List<SelectItem> listaFormaContato;

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
     * Nome: getMessageFromBundle Recupera o valor do atributo 'messageFromBundle'.
     * @param key the key
     * @param args the args
     * @return valor do atributo 'messageFromBundle'
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
     * Nome: setFacesMessage Adiciona uma mensagem ao facesMessage. O valor do resumo e detalhe da
     * mensagem ficam iguais.
     * @param key chave da mensagem no mesasge bundle.
     * @see
     */
    public void setFacesMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(getMessageFromBundle(key),
                getMessageFromBundle(key)));
    }

    /**
     * Nome: setFacesMessage Adiciona uma mensagem ao facesMessage.
     * @param keyTitle the key title
     * @param keyDetail the key detail
     * @see
     */
    public void setFacesMessage(String keyTitle, String keyDetail) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(getMessageFromBundle(keyTitle),
                getMessageFromBundle(keyDetail)));
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
     * Nome: getSelectItens
     * Recupera o valor do atributo 'selectItens'.
     *
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

}
