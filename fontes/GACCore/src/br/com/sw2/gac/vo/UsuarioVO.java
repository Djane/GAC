package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * <b>Descrição: Objeto que representa um usuário do GAC.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -6082278126857385935L;

    /** Atributo login. */
    private String login;

    /** Atributo nome usuario. */
    private String nomeUsuario;

    /** Atributo senha. */
    private String senha;

    /** Atributo perfil. */
    private PerfilVO perfil;

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public String getLogin() {
        return login;
    }

    /**
     * Nome: setLogin Registra o valor do atributo 'login'.
     * @param login valor do atributo login
     * @see
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Nome: getNomeUsuario Recupera o valor do atributo 'nomeUsuario'.
     * @return valor do atributo 'nomeUsuario'
     * @see
     */
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    /**
     * Nome: setNomeUsuario Registra o valor do atributo 'nomeUsuario'.
     * @param nomeUsuario valor do atributo nome usuario
     * @see
     */
    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    /**
     * Nome: getSenha Recupera o valor do atributo 'senha'.
     * @return valor do atributo 'senha'
     * @see
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Nome: setSenha Registra o valor do atributo 'senha'.
     * @param senha valor do atributo senha
     * @see
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Nome: getPerfil Recupera o valor do atributo 'perfil'.
     * @return valor do atributo 'perfil'
     * @see
     */
    public PerfilVO getPerfil() {
        return perfil;
    }

    /**
     * Nome: setPerfil Registra o valor do atributo 'perfil'.
     * @param perfil valor do atributo perfil
     * @see
     */
    public void setPerfil(PerfilVO perfil) {
        this.perfil = perfil;
    }

    /**
     * Nome: getSerialversionuid Recupera o valor do atributo 'serialversionuid'.
     * @return valor do atributo 'serialversionuid'
     * @see
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}