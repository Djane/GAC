package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * <b>Descrição: Objeto que representa um perfil de usuário.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PerfilVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -9198363297559378080L;

    /** Atributo id perfil. */
    private Integer idPerfil;

    /** Atributo descricao. */
    private String descricao;

    /**
     * Construtor Padrao Instancia um novo objeto PerfilVO.
     */
    public PerfilVO() {
        super();
    }

    /**
     * Construtor Padrao Instancia um novo objeto PerfilVO.
     * @param idPerfil the id perfil
     * @param descricao the descricao
     */
    public PerfilVO(Integer idPerfil, String descricao) {
        super();
        this.idPerfil = idPerfil;
        this.descricao = descricao;
    }

    /**
     * Nome: getIdPerfil Recupera o valor do atributo 'idPerfil'.
     * @return valor do atributo 'idPerfil'
     * @see
     */
    public Integer getIdPerfil() {
        return idPerfil;
    }

    /**
     * Nome: setIdPerfil Registra o valor do atributo 'idPerfil'.
     * @param idPerfil valor do atributo id perfil
     * @see
     */
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * Nome: getDescricao Recupera o valor do atributo 'descricao'.
     * @return valor do atributo 'descricao'
     * @see
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Nome: setDescricao Registra o valor do atributo 'descricao'.
     * @param descricao valor do atributo descricao
     * @see
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
