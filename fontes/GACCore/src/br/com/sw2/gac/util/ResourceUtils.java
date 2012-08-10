package br.com.sw2.gac.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class ResourceUtils {

    /**
     * Construtor Padrao Instancia um novo objeto ResourceUtils.
     */
    private ResourceUtils() {
    }

    /**
     * Nome: instanceOf Instance of.
     * @param value the value
     * @param classType the class type
     * @return boolean
     * @see
     */
    public static Boolean instanceOf(Object value, Class<?> classType) {
        return ((null != value)) && (classType.isInstance(value));
    }

    /**
     * Nome: getResourceAsProperties Recupera o valor do atributo 'resourceAsProperties'.
     * @param resourceName the resource name
     * @return valor do atributo 'resourceAsProperties'
     * @throws Exception the exception
     * @see
     */
    @SuppressWarnings("unchecked")
    public static Properties getResourceAsProperties(String resourceName) throws Exception {
        if (null == resourceName) {
            throw (new IllegalArgumentException("O valor informado pelo argumento "
                    + "[resourceName] n�o pode ser nulo."));
        }

        InputStream inputStream = ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream(
                resourceName);

        Properties properties = new Properties();
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

        String key;
        StringTokenizer map = null;
        String propertyKey = null;
        Object value = null;
        List<String> list = null;

        while ((key = in.readLine()) != null) {
            key = key.trim();
            if (("".equals(key)) || ("#".equals(String.valueOf(key.charAt(0))))) {
                continue;
            }
            map = new StringTokenizer(key, "=");
            if (map.countTokens() == 2) {
                propertyKey = map.nextToken().trim();
                if (properties.containsKey(propertyKey)) {
                    Object propertyValue = properties.get(propertyKey);
                    if (!(propertyValue instanceof List)) {
                        list = new ArrayList<String>();
                        list.add(propertyValue.toString());
                        properties.remove(propertyKey);
                    } else {
                        list = (List<String>) propertyValue;
                    }
                    list.add(map.nextToken().trim());
                    value = list;
                } else {
                    value = map.nextToken().trim();
                }
                properties.put(propertyKey, value);
            }
        }
        return (properties);
    }

}
