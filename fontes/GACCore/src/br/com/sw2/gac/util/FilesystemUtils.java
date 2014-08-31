package br.com.sw2.gac.util;

import java.io.File;

/**
 * <b>Descrição: Classe utilitária para operações de arquivos e pastas.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class FilesystemUtils {

    /**
     * Cria uma pasta
     * @param nome e uri da pasta a ser criada
     */
    public static void criarPasta(String pasta) {
        File dir = new File(pasta);
        dir.mkdir();
    }
    
}
