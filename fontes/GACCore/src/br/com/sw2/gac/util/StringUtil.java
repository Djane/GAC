package br.com.sw2.gac.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <b>Descrição: Classe utilit�ria para manipulação de Strings.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public final class StringUtil {

    /**
     * Construtor Padrao Instancia um novo objeto StringUtil.
     */
    private StringUtil() {
        super();
    }

    /**
     * Nome: encriptarTexto Encriptar texto sem volta.
     * @param str the str
     * @return string
     * @see
     */
    public static String encriptarTexto(String str) {

        String textoCriptgrafado = null;
        String textoParaCriptografar = str.trim();

        final char hexaValue = 0xFF;

        MessageDigest algorithm;
        try {
            algorithm = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = algorithm.digest(textoParaCriptografar.getBytes("UTF-8"));

            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02X", hexaValue & b));
            }
            textoCriptgrafado = hexString.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return textoCriptgrafado;
    }

    /**
     * Nome: isVazio Verifica se uam string � nula ou vazia vazio.
     * @param str the str
     * @param trim indica que deve ser aplicado um trim na string a verificar.
     * @return true, se for vazio sen�o retorna false
     * @see
     */
    public static boolean isVazio(String str, boolean trim) {
        boolean retorno = false;
        if (null == str) {
            retorno = true;
        } else if (trim && "".equals(str.trim())) {
            retorno = true;
        } else if (!trim && "".equals(str)) {
            retorno = true;
        }
        return retorno;
    }

}
