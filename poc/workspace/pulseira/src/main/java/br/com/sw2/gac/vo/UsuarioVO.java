package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioVO {

    /** Atributo login. */
    private String login;

    /** Atributo senha. */
    private String senha;

    /** Atributo perfil. */
    private Integer perfil = 0;

    /** Atributo nome perfil. */
    private String nomePerfil;

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
    public Integer getPerfil() {
        return perfil;
    }

    /**
     * Nome: setPerfil Registra o valor do atributo 'perfil'.
     * @param perfil valor do atributo perfil
     * @see
     */
    public void setPerfil(Integer perfil) {
        this.perfil = perfil;
    }

    /**
     * Nome: getNomePerfil Recupera o valor do atributo 'nomePerfil'.
     * @return valor do atributo 'nomePerfil'
     * @see
     */
    public String getNomePerfil() {
        return nomePerfil;
    }

    /**
     * Nome: setNomePerfil Registra o valor do atributo 'nomePerfil'.
     * @param nomePerfil valor do atributo nome perfil
     * @see
     */
    public void setNomePerfil(String nomePerfil) {
        this.nomePerfil = nomePerfil;
    }
}
