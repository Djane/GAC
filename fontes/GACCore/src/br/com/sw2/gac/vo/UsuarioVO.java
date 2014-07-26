package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * <b>Descrição: Objeto que representa um usuário do GAC.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioVO implements Serializable {

    
    private static final long serialVersionUID = -6082278126857385935L;

    /**
     * Login do usuário no GAC
     */
    private String login;

    /**
     * Nome completo do usuário do GAC 
     */
    private String nomeUsuario;

    /**
     * Senha do usuário do GAC. 
     * No Caso de atendente a senha também será a senha do DgPhone
     */
    private String senha;

    /**
     * Objeto que representa o perfil do usuário
     * @See br.com.sw2.gac.vo.PerfilVO
     */
    private PerfilVO perfil;

    /**
     * Ramal do usuário, caso ele seja atendente
     */
    private Integer ramal;

    /**
     * Login do usuário no DgPhone
     */
    private Integer registroAtendente;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilVO getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilVO perfil) {
        this.perfil = perfil;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getRamal() {
        return ramal;
    }

    public void setRamal(Integer ramal) {
        this.ramal = ramal;
    }

    public Integer getRegistroAtendente() {
        return registroAtendente;
    }

    public void setRegistroAtendente(Integer registroAtendente) {
        this.registroAtendente = registroAtendente;
    }
    
    public String toString() {
        
        StringBuffer str = new StringBuffer(); 
        str.append("login: ");
        str.append(login);
        
        str.append(" nomeUsuario: ");
        str.append(nomeUsuario);

        str.append(" perfil: ");
        if (null == perfil) {
            str.append("null");    
        } else {
            str.append(perfil.getIdPerfil());
        }
        
        str.append(" ramal: ");
        str.append(ramal);

        str.append(" registroAtendente: ");
        str.append(registroAtendente);        
        
        return str.toString();
    }   

}