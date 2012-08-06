package br.com.sw2.gac.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.StringTokenizer;

/**
 * <b>Descrição: Classe para manipulação de objetos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class ObjectUtils {

    /** Constante GET. */
    private static final String GET = "get";

    /** Constante SET. */
    private static final String SET = "set";

    /**
     * Construtor Padrao Instancia um novo objeto ObjectUtils.
     */
    private ObjectUtils() {
    }

    /**
     * Realiza verificação sobre o argumento <i>object</i>, se este argumento for nulo ou vazio
     * retorna Boolean.TRUE, caso contrário Boolean.FALSE.
     * @param object the object
     * @return Boolean
     * @see
     */
    public static Boolean isNullOrEmpty(Object object) {
        return ((ObjectUtils.isNull(object)) || ("".equals(object)));
    }

    /**
     * Realiza verificação sobre o argumento <i>object</i>, se este argumento for nulo retorna
     * Boolean.TRUE, caso contrário Boolean.FALSE.
     * @param object the object
     * @return Boolean
     * @see
     */
    public static Boolean isNull(Object object) {
        return (object == null);
    }

    /**
     * Realiza verificação sobre o argumento <i>object</i>, se este argumento não for nulo retorna
     * Boolean.TRUE, caso contrário Boolean.FALSE.
     * @param object the object
     * @return Boolean
     * @see
     */
    public static Boolean isNotNull(Object object) {
        return (object != null);
    }

    /**
     * Recupera o nome do método accessor Get.
     * @param field the field
     * @return java.lang.String Getter
     * @see
     */
    public static String getAccessorGetterName(Field field) {
        return (getAccessorName(field, GET));
    }

    /**
     * Recupera o nome do método accessor Get.
     * @param name the name
     * @return java.lang.String Getter
     * @see
     */
    public static String getAccessorGetterName(String name) {
        return (GET + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1));
    }

    /**
     * Recupera o nome do método accessor Set.
     * @param field the field
     * @return java.lang.String Setter
     * @see
     */
    public static String getAccessorSetterName(Field field) {
        return (getAccessorName(field, SET));
    }

    /**
     * Recupera o nome do método accessor Set.
     * @param name the name
     * @return java.lang.String Setter
     * @see
     */
    public static String getAccessorSetterName(String name) {
        return (SET + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1));
    }

    /**
     * Recupera o nome do método conforme tipo do método acessor Get ou Set.
     * @param field the field
     * @param accessorType the accessor type
     * @return java.lang.String Getter
     * @see
     */
    private static String getAccessorName(Field field, String accessorType) {
        return (accessorType + String.valueOf(field.getName().charAt(0)).toUpperCase() + field
                .getName().substring(1));
    }

    /**
     * Realiza a invocação ao método <i>methodName</i> e objeto <i>object</i> informado.
     * @param methodName Nome do método.
     * @param object Objeto a qual o método será executado.
     * @param parameterTypes Tipo dos parâmetros para os argumentos do método.
     * @param args Argumentos do método.
     * @return java.lang.Object Valor retornado quando executado o método.
     * @throws Exception the exception
     * @see
     */
    public static Object invokeMethod(String methodName, Object object, Class<?>[] parameterTypes,
            Object[] args) throws Exception {
        try {
            Method method = object.getClass().getMethod(methodName, parameterTypes);
            return (method.invoke(object, args));
        } catch (Exception exception) {
            throw (exception);
        }
    }

    /**
     * Realiza a invocação ao método <i>methodName</i> e objeto <i>object</i> informado. Este método
     * deve seguir a especificação Java para métodos getters.
     * @param field the field
     * @param object Objeto a qual o método será executado.
     * @return java.lang.Object Valor retornado quando executado o método.
     * @throws Exception the exception
     * @see
     */
    public static Object invokeGetterMethod(Field field, Object object) throws Exception {
        return (ObjectUtils.invokeMethod(ObjectUtils.getAccessorGetterName(field), object,
                new Class[0], new Object[0]));
    }

    /**
     * Realiza a invocação ao método <i>methodName</i> e objeto <i>object</i> informado. Este método
     * deve seguir a especificação Java para métodos setters.
     * @param field the field
     * @param object Objeto a qual o método será executado.
     * @param value the value
     * @return java.lang.Object Valor retornado quando executado o método.
     * @throws Exception the exception
     * @see
     */
    public static Object invokeSetterMethod(Field field, Object object, Object value)
        throws Exception {
        return (ObjectUtils.invokeMethod(ObjectUtils.getAccessorSetterName(field), object,
                new Class<?>[] { field.getType() }, new Object[] { value }));
    }

    /**
     * Recupera a representação do método setter do atributo informado pelo argumento <i>field</i>.
     * @param field the field
     * @param object the object
     * @return valor do atributo 'getterMethod'
     * @throws Exception the exception
     * @see
     */
    public static Method getGetterMethod(Field field, Object object) throws Exception {
        return (object.getClass().getDeclaredMethod(ObjectUtils.getAccessorGetterName(field),
                new Class<?>[0]));
    }

    /**
     * Recupera a representação do método getter do atributo informado pelo argumento <i>field</i>.
     * @param field the field
     * @param object the object
     * @return valor do atributo 'setterMethod'
     * @throws Exception the exception
     * @see
     */
    public static Method getSetterMethod(Field field, Object object) throws Exception {
        return (object.getClass().getDeclaredMethod(ObjectUtils.getAccessorSetterName(field),
                Object.class));
    }

    /**
     * Verifica se o valor informado <i>value</i> é uma instância do tipo informado pelo argumento
     * <i>classType</i>.
     * @param value the value
     * @param classType the class type
     * @return boolean
     * @see
     */
    public static Boolean instanceOf(Object value, Class<?> classType) {
        return ((!ObjectUtils.isNull(value)) && (classType.isInstance(value)));
    }

    /**
     * Recupera o valor da propriedade do objeto.
     * @param propertyName Nome da propriedade do objeto a ser recuperado o valor.
     * @param object Objeto a ser extraido o valor da propriedade.
     * @return valor do atributo 'propertyValue'
     * @throws Exception the exception
     * @see
     */
    public static Object getPropertyValue(String propertyName, Object object) throws Exception {
        Object parentObject = object;
        String token = null;
        Object propertyValue = null;
        for (StringTokenizer tokens = new StringTokenizer(propertyName, "."); tokens
                .hasMoreTokens();) {
            token = tokens.nextToken();
            try {
                propertyValue = ObjectUtils.getPropertyValue(parentObject.getClass()
                        .getDeclaredField(token), parentObject);
            } catch (NoSuchFieldException exception) {
                propertyValue = ObjectUtils.invokeMethod(ObjectUtils.getAccessorGetterName(token),
                        parentObject, new Class<?>[0], new Object[0]);
            }
            if (tokens.hasMoreTokens()) {
                parentObject = propertyValue;
            }
        }
        return (propertyValue);
    }

    /**
     * Recupera o valor da propriedade do objeto.
     * @param property the property
     * @param object the object
     * @return valor do atributo 'propertyValue'
     * @throws Exception the exception
     * @see
     */
    public static Object getPropertyValue(Field property, Object object) throws Exception {
        Object value = null;
        try {
            value = property.get(object);
        } catch (Exception exception) {
            return (ObjectUtils.invokeGetterMethod(property, object));
        }
        return (value);
    }

    /**
     * Recupera o valor da anotação marcada nas propriedades do objeto informado. Note-se que irá
     * retornar a primeira ocorrência marcada com a anotação.
     * @param object the object
     * @param annotationClass the annotation class
     * @return valor do atributo 'valueByAnnotation'
     * @throws Exception the exception
     * @see
     */
    public static Object getValueByAnnotation(Object object,
            Class<? extends Annotation> annotationClass) throws Exception {
        if (ObjectUtils.isNull(object)) {
            return (null);
        }
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                return (ObjectUtils.getPropertyValue(field, object));
            }
        }
        return (null);
    }

    /**
     * Nome: getAnnotationInField Recupera o valor do atributo 'annotationInField'.
     * @param object the object
     * @param annotationClass the annotation class
     * @return valor do atributo 'annotationInField'
     * @see
     */
    public static Annotation getAnnotationInField(Object object,
            Class<? extends Annotation> annotationClass) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(annotationClass)) {
                return (field.getAnnotation(annotationClass));
            }
        }
        return (null);
    }

}
