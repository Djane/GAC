package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;

import br.com.sw2.gac.tools.EstadosBrasileiros;

/**
 * <b>Descrição:</b> <br>.
 *
 * @author: lucianor
 * @version 1.0
 *
 * Copyright 2012 SmartAngel.
 */
public class BaseBean {

    /**
     * Construtor Padrao
     * Instancia um novo objeto BaseBean.
     */
    public BaseBean() {

        //Monta lista de estados Brasileiros
        this.listaUf = new ArrayList<SelectItem>();
        for (EstadosBrasileiros uf : EstadosBrasileiros.values()) {
            this.listaUf.add(new SelectItem(uf, uf.getValue()));
        }
    }

    /** Atributo url image. */
    private String urlImage = "primefaces-smartangel/images";

    /** Atributo lista uf. */
    private List<SelectItem> listaUf;

    /**
     * Nome: getUrlImage
     * Recupera o valor do atributo 'urlImage'.
     *
     * @return valor do atributo 'urlImage'
     * @see
     */
    public String getUrlImage() {
        return urlImage;
    }

    /**
     * Nome: setUrlImage
     * Registra o valor do atributo 'urlImage'.
     *
     * @param urlImage valor do atributo url image
     * @see
     */
    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    /**
     * Nome: getListaUf
     * Recupera o valor do atributo 'listaUf'.
     *
     * @return valor do atributo 'listaUf'
     * @see
     */
    public List<SelectItem> getListaUf() {
        return listaUf;
    }

    /**
     * Nome: setListaUf
     * Registra o valor do atributo 'listaUf'.
     *
     * @param listaUf valor do atributo lista uf
     * @see
     */
    public void setListaUf(List<SelectItem> listaUf) {
        this.listaUf = listaUf;
    }

    /**
     * Nome: setTituloCabecalho
     * Registra o valor do atributo 'tituloCabecalho'.
     *
     * @param str valor do atributo titulo cabecalho
     * @see
     */
    public void setTituloCabecalho(String str) {
        setRequestAttribute("screenTitle", str);
    }

    /**
     * Nome: setRequestAttribute
     * Sets the request attribute.
     *
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
     * Nome: getRequestParameter
     * Recupera o valor do atributo 'requestParameter'.
     *
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
     * Nome: getMessageFromBundle
     * Recupera o valor do atributo 'messageFromBundle'.
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
     * Nome: setFacesMessage
     * Registra o valor do atributo 'facesMessage'.
     *
     * @param key valor do atributo faces message
     * @see
     */
    public void setFacesMessage(String key) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(getMessageFromBundle(key),
                getMessageFromBundle(key)));
    }

    /**
     * Nome: setFacesMessage
     * Sets the faces message.
     *
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
     * Nome: findInListById
     * Find in list by id.
     *
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
     * Nome: findInListById
     * Find in list by id.
     *
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
     * Nome: retornarMenuPrincipal
     * Retornar menu principal.
     *
     * @return string
     * @see
     */
    public String retornarMenuPrincipal() {
        return "menuPrincipal";
    }

}
