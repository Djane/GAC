/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblusuario")
@NamedQueries({ @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u") })
public class Usuario implements Serializable {

    /** Atributo dispositivo list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Dispositivo> dispositivoList;

    /** Atributo ocorrencia list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Ocorrencia> ocorrenciaList;

    /** Atributo contrato list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Contrato> contratoList;

    /** Atributo contato list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Contato> contatoList;

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo login. */
    @Id
    @Basic(optional = false)
    @Column(name = "login")
    private String login;

    /** Atributo nm usuario. */
    @Basic(optional = false)
    @Column(name = "nmUsuario")
    private String nmUsuario;

    /** Atributo senha. */
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;

    /** Atributo nm tel fixo. */
    @Column(name = "nmTelFixo")
    private String nmTelFixo;

    /** Atributo nm tel celular. */
    @Column(name = "nmTelCelular")
    private String nmTelCelular;

    /** Atributo nm funcao. */
    @Column(name = "nmFuncao")
    private Integer nmFuncao;

    /** Atributo cd perfil. */
    @Column(name = "cdPerfil")
    private Integer cdPerfil;

    /** Atributo cliente list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Cliente> clienteList;

    /**
     * Construtor Padrao Instancia um novo objeto Usuario.
     */
    public Usuario() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Usuario.
     * @param login the login
     */
    public Usuario(String login) {
        this.login = login;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Usuario.
     * @param login the login
     * @param nmUsuario the nm usuario
     * @param senha the senha
     */
    public Usuario(String login, String nmUsuario, String senha) {
        this.login = login;
        this.nmUsuario = nmUsuario;
        this.senha = senha;
    }

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
     * Nome: getNmUsuario Recupera o valor do atributo 'nmUsuario'.
     * @return valor do atributo 'nmUsuario'
     * @see
     */
    public String getNmUsuario() {
        return nmUsuario;
    }

    /**
     * Nome: setNmUsuario Registra o valor do atributo 'nmUsuario'.
     * @param nmUsuario valor do atributo nm usuario
     * @see
     */
    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
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
     * Nome: getNmTelFixo Recupera o valor do atributo 'nmTelFixo'.
     * @return valor do atributo 'nmTelFixo'
     * @see
     */
    public String getNmTelFixo() {
        return nmTelFixo;
    }

    /**
     * Nome: setNmTelFixo Registra o valor do atributo 'nmTelFixo'.
     * @param nmTelFixo valor do atributo nm tel fixo
     * @see
     */
    public void setNmTelFixo(String nmTelFixo) {
        this.nmTelFixo = nmTelFixo;
    }

    /**
     * Nome: getNmTelCelular Recupera o valor do atributo 'nmTelCelular'.
     * @return valor do atributo 'nmTelCelular'
     * @see
     */
    public String getNmTelCelular() {
        return nmTelCelular;
    }

    /**
     * Nome: setNmTelCelular Registra o valor do atributo 'nmTelCelular'.
     * @param nmTelCelular valor do atributo nm tel celular
     * @see
     */
    public void setNmTelCelular(String nmTelCelular) {
        this.nmTelCelular = nmTelCelular;
    }

    /**
     * Nome: getNmFuncao Recupera o valor do atributo 'nmFuncao'.
     * @return valor do atributo 'nmFuncao'
     * @see
     */
    public Integer getNmFuncao() {
        return nmFuncao;
    }

    /**
     * Nome: setNmFuncao Registra o valor do atributo 'nmFuncao'.
     * @param nmFuncao valor do atributo nm funcao
     * @see
     */
    public void setNmFuncao(Integer nmFuncao) {
        this.nmFuncao = nmFuncao;
    }

    /**
     * Nome: getCdPerfil Recupera o valor do atributo 'cdPerfil'.
     * @return valor do atributo 'cdPerfil'
     * @see
     */
    public Integer getCdPerfil() {
        return cdPerfil;
    }

    /**
     * Nome: setCdPerfil Registra o valor do atributo 'cdPerfil'.
     * @param cdPerfil valor do atributo cd perfil
     * @see
     */
    public void setCdPerfil(Integer cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    /**
     * Nome: getClienteList Recupera o valor do atributo 'clienteList'.
     * @return valor do atributo 'clienteList'
     * @see
     */
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    /**
     * Nome: setClienteList Registra o valor do atributo 'clienteList'.
     * @param clienteList valor do atributo cliente list
     * @see
     */
    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (login != null) {
            hash += login.hashCode();
        } else {
            hash += 0;
        }

        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.login == null && other.login != null)
                || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Usuario[ login=" + login + " ]";
    }

    /**
     * Nome: getDispositivoList Recupera o valor do atributo 'dispositivoList'.
     * @return valor do atributo 'dispositivoList'
     * @see
     */
    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    /**
     * Nome: setDispositivoList Registra o valor do atributo 'dispositivoList'.
     * @param dispositivoList valor do atributo dispositivo list
     * @see
     */
    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
    }

    /**
     * Nome: getOcorrenciaList Recupera o valor do atributo 'ocorrenciaList'.
     * @return valor do atributo 'ocorrenciaList'
     * @see
     */
    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    /**
     * Nome: setOcorrenciaList Registra o valor do atributo 'ocorrenciaList'.
     * @param ocorrenciaList valor do atributo ocorrencia list
     * @see
     */
    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

    /**
     * Nome: getContratoList Recupera o valor do atributo 'contratoList'.
     * @return valor do atributo 'contratoList'
     * @see
     */
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    /**
     * Nome: setContratoList Registra o valor do atributo 'contratoList'.
     * @param contratoList valor do atributo contrato list
     * @see
     */
    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    /**
     * Nome: getContatoList Recupera o valor do atributo 'contatoList'.
     * @return valor do atributo 'contatoList'
     * @see
     */
    public List<Contato> getContatoList() {
        return contatoList;
    }

    /**
     * Nome: setContatoList Registra o valor do atributo 'contatoList'.
     * @param contatoList valor do atributo contato list
     * @see
     */
    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

}
