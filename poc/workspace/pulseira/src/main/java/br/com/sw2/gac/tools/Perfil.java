package br.com.sw2.gac.tools;

/**
 * <b>Descrição: Descrição dos perfils utilizados pelo usuário.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum Perfil {

    /** Atributo Gerencial. */
    Gerencial(1),
    /** Atributo Backoffice. */
    Backoffice(2),
    /** Atributo Usuario. */
    Usuario(3),
    /** Atributo Atendente. */
    Atendente(4);

    /** Atributo cod perfil. */
    private int codPerfil;

    /**
     * Construtor Padrao Instancia um novo objeto Perfil.
     * @param cod the cod
     */
    private Perfil(int cod) {
        codPerfil = cod;
    }

    /**
     * Nome: getCodPerfil Recupera o valor do atributo 'codPerfil'.
     * @return valor do atributo 'codPerfil'
     * @see
     */
    public int getCodPerfil() {
        return codPerfil;
    }
}