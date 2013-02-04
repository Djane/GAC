package br.com.sw2.gac.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import org.w3c.tools.codec.Base64Decoder;
import org.w3c.tools.codec.Base64Encoder;
import org.w3c.tools.codec.Base64FormatException;

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
     * Nome: isEmpty Verifica se uam string é nula ou vazia vazio.
     * @param str the str
     * @param trim indica que deve ser aplicado um trim na string a verificar.
     * @return true, se for vazio senão retorna false
     * @see
     */
    public static boolean isEmpty(String str, boolean trim) {
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

    /**
     * Nome: isNotEmpty Verifica se uma string não é nula ou em branco.
     * @param str the str
     * @param trim indica que deve ser aplicado um trim na string a verificar.
     * @return true, se não for vazio e false se for vazio.
     * @see
     */
    public static boolean isNotEmpty(String str, boolean trim) {
        boolean retorno = true;
        if (null == str) {
            retorno = false;
        } else if (trim && "".equals(str.trim())) {
            retorno = false;
        } else if (!trim && "".equals(str)) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Nome: formatString Formata uma string.
     * @param str the string
     * @param mask the mask
     * @return string
     * @see
     */
    public static String formatString(String str, String mask) {
        String retorno = "";
        try {
            javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter(mask);
            mf.setValueContainsLiteralCharacters(false);
            retorno = mf.valueToString(str);
        } catch (ParseException e) {
            retorno = str;
        }
        return retorno;
    }

    /**
     * Nome: cryptoN1
     * Crypto n1.
     *
     * @param str the str
     * @return string
     * @see
     */
    public static String cryptoN1(String str) {
        Base64Encoder enc = new Base64Encoder(str);
        return enc.processString();
    }

    /**
     * Nome: decrypterN1
     * Decrypter n1.
     *
     * @param str the str
     * @return string
     * @see
     */
    public static String decrypterN1(String str) {
        Base64Decoder dec = new Base64Decoder(str);
        String texto;
        try {
            texto = dec.processString();
        } catch (Base64FormatException e) {
            texto = null;
        }
        return texto;
    }
}
