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
 *
 * @author rogerio
 */
@Entity
@Table(name = "tblusuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")})
public class Usuario implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Dispositivo> dispositivoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Ocorrencia> ocorrenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Contrato> contratoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Contato> contatoList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @Column(name = "nmUsuario")
    private String nmUsuario;
    @Basic(optional = false)
    @Column(name = "senha")
    private String senha;
    @Column(name = "nmTelFixo")
    private String nmTelFixo;
    @Column(name = "nmTelCelular")
    private String nmTelCelular;
    @Column(name = "nmFuncao")
    private Integer nmFuncao;
    @Column(name = "cdPerfil")
    private Integer cdPerfil;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "login")
    private List<Cliente> clienteList;

    public Usuario() {
    }

    public Usuario(String login) {
        this.login = login;
    }

    public Usuario(String login, String nmUsuario, String senha) {
        this.login = login;
        this.nmUsuario = nmUsuario;
        this.senha = senha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNmUsuario() {
        return nmUsuario;
    }

    public void setNmUsuario(String nmUsuario) {
        this.nmUsuario = nmUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNmTelFixo() {
        return nmTelFixo;
    }

    public void setNmTelFixo(String nmTelFixo) {
        this.nmTelFixo = nmTelFixo;
    }

    public String getNmTelCelular() {
        return nmTelCelular;
    }

    public void setNmTelCelular(String nmTelCelular) {
        this.nmTelCelular = nmTelCelular;
    }

    public Integer getNmFuncao() {
        return nmFuncao;
    }

    public void setNmFuncao(Integer nmFuncao) {
        this.nmFuncao = nmFuncao;
    }

    public Integer getCdPerfil() {
        return cdPerfil;
    }

    public void setCdPerfil(Integer cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Usuario[ login=" + login + " ]";
    }

    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
    }

    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }
    
}
