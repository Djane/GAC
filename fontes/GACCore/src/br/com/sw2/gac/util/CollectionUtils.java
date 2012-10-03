package br.com.sw2.gac.util;

import java.util.List;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.functors.EqualPredicate;

/**
 * <b>Descrição: Classe para encapsular manipulações de listas, baseada na bilioteca CollectionUtils da apache.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class CollectionUtils extends org.apache.commons.collections.CollectionUtils {

    /**
     * Nome: findByAttribute Find in list by id.
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
}
