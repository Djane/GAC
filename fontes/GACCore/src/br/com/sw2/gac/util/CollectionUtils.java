package br.com.sw2.gac.util;

import java.util.Collection;
import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

/**
 * <b>Descrição: Classe para encapsular chamadas e customizações baseadas na biblioteca
 * CollectionsUtils da apache.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    /**
     * Nome: findByAttribute Localiza um objeto na lista atraves de um atributo do tipo
     * java.lang.Integer.
     * @param list the list
     * @param field the field
     * @param fieldValue the field value
     * @return object
     * @see
     */
    public static Object findByAttribute(List<?> list, String field, Integer fieldValue) {
        EqualPredicate equalPredicate = new EqualPredicate(fieldValue);
        BeanPredicate beanPredicate = new BeanPredicate(field, equalPredicate);
        return find(list, beanPredicate);
    }

    /**
     * Nome: findByAttribute Localiza um objeto na lista atraves de um atributo do tipo
     * java.lang.String.
     * @param list the list
     * @param field the field
     * @param fieldValue the field value
     * @return object
     * @see
     */
    public static Object findByAttribute(List<?> list, String field, String fieldValue) {
        EqualPredicate equalPredicate = new EqualPredicate(fieldValue);
        BeanPredicate beanPredicate = new BeanPredicate(field, equalPredicate);
        return find(list, beanPredicate);
    }

    /**
     * Nome: findByAttribute Localiza um objeto na lista atraves de um atributo que extenda Object.
     * @param list the list
     * @param field the field
     * @param fieldValue the field value
     * @return object
     * @see
     */
    public static Object findByAttribute(List<?> list, String field, Object fieldValue) {
        EqualPredicate equalPredicate = new EqualPredicate(fieldValue);
        BeanPredicate beanPredicate = new BeanPredicate(field, equalPredicate);
        return find(list, beanPredicate);
    }

    /**
     * Nome: isEmptyOrNull Retorna true se uma lista é vazia ou nula.
     * @param collection the collection
     * @return true, se for empty or null senão retorna false
     * @see
     */
    public static boolean isEmptyOrNull(Collection<?> collection) {
        boolean retorno = false;
        if (null == collection || collection.isEmpty()) {
            retorno = true;
        }
        return retorno;
    }

}
