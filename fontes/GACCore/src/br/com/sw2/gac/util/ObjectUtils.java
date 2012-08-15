package br.com.sw2.gac.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import br.com.sw2.gac.modelo.Dispositivo;
import br.com.sw2.gac.modelo.SMS;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.SmsVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Classe para manipulação de objetos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class ObjectUtils {

    /** Constante GET. */
    private static final String GET = "get";

    /**
     * Construtor Padrao Instancia um novo objeto ObjectUtils.
     */
    private ObjectUtils() {
        super();
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
     * Realiza verificação sobre o argumento <i>object</i>, se este argumento n�o for nulo retorna
     * Boolean.TRUE, caso contr�rio Boolean.FALSE.
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
     * Realiza a invoca��o ao método <i>methodName</i> e objeto <i>object</i> informado.
     * @param methodName Nome do método.
     * @param object Objeto a qual o método ser� executado.
     * @param parameterTypes Tipo dos par�metros para os argumentos do método.
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
     * Realiza a invoca��o ao método <i>methodName</i> e objeto <i>object</i> informado. Este método
     * deve seguir a especifica��o Java para métodos getters.
     * @param field the field
     * @param object Objeto a qual o método ser� executado.
     * @return java.lang.Object Valor retornado quando executado o método.
     * @throws Exception the exception
     * @see
     */
    public static Object invokeGetterMethod(Field field, Object object) throws Exception {
        return (ObjectUtils.invokeMethod(ObjectUtils.getAccessorGetterName(field), object,
                new Class[0], new Object[0]));
    }

    /**
     * Recupera a representa��o do método setter do atributo informado pelo argumento <i>field</i>.
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
     * Verifica se o valor informado <i>value</i> é uma instancia do tipo informado pelo argumento
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

    /**
     * Nome: Converte VO´s em Entitys e Entitys em VO´s.
     * @param <T> the generic type
     * @param object the object
     * @return t
     * @see
     */
    @SuppressWarnings("unchecked")
    public static <T> T parse(Object object) {

        T retorno = null;

        if (object instanceof UsuarioVO) {

            UsuarioVO usuario = (UsuarioVO) object;
            String senhaCriptografada = StringUtil.encriptarTexto(usuario.getSenha());
            Usuario entity = new Usuario();
            entity.setSenha(senhaCriptografada);
            entity.setLogin(usuario.getLogin());
            entity.setNmUsuario(usuario.getLogin());
            entity.setCdPerfil(usuario.getPerfil().getIdPerfil());

            retorno = (T) entity;

        } else if (object instanceof Usuario) {

            Usuario entity = (Usuario) object;
            UsuarioVO vo = new UsuarioVO();
            vo.setSenha(entity.getSenha());
            vo.setLogin(entity.getLogin());
            vo.setNomeUsuario(entity.getLogin());
            PerfilVO perfil = new PerfilVO();
            perfil.setIdPerfil(entity.getCdPerfil());
            vo.setPerfil(perfil);

            retorno = (T) vo;

        } else if (object instanceof Dispositivo) {

            Dispositivo entity = (Dispositivo) object;
            DispositivoVO dispositivo = new DispositivoVO();
            dispositivo.setIdDispositivo(entity.getIdDispositivo());
            dispositivo.setUsuario((UsuarioVO) ObjectUtils.parse(entity.getLogin()));
            dispositivo.setEstadoAtual(entity.getTpEstado());
            dispositivo.setTipoDispositivo(entity.getTpDispositivo());
            dispositivo.setDataEntrada(entity.getDtaEntrada());
            dispositivo.setDataFabricacao(entity.getDtaFabrica());
            dispositivo.setDataProximaManutencao(entity.getDtaProximaManut());
            dispositivo.setDataSucata(entity.getDtaSucata());
            dispositivo.setLocal(entity.getLocal());

            retorno = (T) dispositivo;

        } else if (object instanceof DispositivoVO) {

            DispositivoVO dispositivo = (DispositivoVO) object;
            Dispositivo entity = new Dispositivo();
            entity.setIdDispositivo(dispositivo.getIdDispositivo());
            entity.setLogin((Usuario) ObjectUtils.parse(dispositivo.getUsuario()));
            entity.setTpEstado(dispositivo.getEstadoAtual());
            entity.setTpDispositivo(dispositivo.getTipoDispositivo());
            entity.setDtaEntrada(dispositivo.getDataEntrada());
            entity.setDtaFabrica(dispositivo.getDataFabricacao());
            entity.setDtaProximaManut(dispositivo.getDataProximaManutencao());
            entity.setDtaSucata(dispositivo.getDataSucata());
            entity.setLocal(dispositivo.getLocal());

            retorno = (T) entity;

        } else if (object instanceof SmsVO) {

            SmsVO sms = (SmsVO) object;
            SMS entity = new SMS();
            entity.setTpMensagem(sms.getTitulo());
            entity.setDsMensagem(sms.getTexto());
            entity.setDtInicioValidade(sms.getDtInicioValidade());

            retorno = (T) entity;

        } else if (object instanceof SMS) {

            SMS entity = (SMS) object;
            SmsVO sms = new SmsVO();
            sms.setIdSms(entity.getIdSMS());
            sms.setTitulo(entity.getTpMensagem());
            sms.setTexto(entity.getDsMensagem());
            sms.setDtInicioValidade(entity.getDtInicioValidade());
            sms.setDtTerminoValidade(entity.getDtTerminoValidade());

            retorno = (T) sms;

        }

        return retorno;

    }
}
