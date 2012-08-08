package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Descrição: The persistent class for the TbUsuario database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblUsuario")
public class Usuario implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo login. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "Login", unique = true, nullable = false)
    private String login;

    /** Atributo cd perfil. */
    @Column(name = "cdPerfil")
    private int cdPerfil;

    /** Atributo nm funcao. */
    private int nmFuncao;

    /** Atributo nm tel celular. */
    @Column()
    private String nmTelCelular;

    /** Atributo nm tel fixo. */
    @Column()
    private String nmTelFixo;

    /** Atributo nm usuario. */
    @Column(nullable = false)
    private String nmUsuario;

    /** Atributo senha. */
    @Column(nullable = false)
    private String senha;

    // bi-directional many-to-one association to TblContato
    /** Atributo tbl contatos. */
    @OneToMany(mappedBy = "tbUsuario")
    private List<Contato> tblContatos;

    // bi-directional many-to-one association to TblContrato
    /** Atributo tbl contratos. */
    @OneToMany(mappedBy = "tbUsuario")
    private List<Contrato> tblContratos;

    // bi-directional many-to-one association to TblDispositivo
    /** Atributo tbl dispositivos. */
    @OneToMany(mappedBy = "tbUsuario")
    private List<Dispositivo> tblDispositivos;

    // bi-directional many-to-one association to TblOcorrencia
    /** Atributo tbl ocorrencias. */
    @OneToMany(mappedBy = "tbUsuario")
    private List<Ocorrencia> tblOcorrencias;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl pacientes. */
    @OneToMany(mappedBy = "tbUsuario")
    private List<Paciente> tblPacientes;

    /**
     * Construtor Padrao Instancia um novo objeto Usuario.
     */
    public Usuario() {
    }

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public String getLogin() {
        return this.login;
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
     * Nome: getCdPerfil Recupera o valor do atributo 'cdPerfil'.
     * @return valor do atributo 'cdPerfil'
     * @see
     */
    public int getCdPerfil() {
        return this.cdPerfil;
    }

    /**
     * Nome: setCdPerfil Registra o valor do atributo 'cdPerfil'.
     * @param cdPerfil valor do atributo cd perfil
     * @see
     */
    public void setCdPerfil(int cdPerfil) {
        this.cdPerfil = cdPerfil;
    }

    /**
     * Nome: getNmFuncao Recupera o valor do atributo 'nmFuncao'.
     * @return valor do atributo 'nmFuncao'
     * @see
     */
    public int getNmFuncao() {
        return this.nmFuncao;
    }

    /**
     * Nome: setNmFuncao Registra o valor do atributo 'nmFuncao'.
     * @param nmFuncao valor do atributo nm funcao
     * @see
     */
    public void setNmFuncao(int nmFuncao) {
        this.nmFuncao = nmFuncao;
    }

    /**
     * Nome: getNmTelCelular Recupera o valor do atributo 'nmTelCelular'.
     * @return valor do atributo 'nmTelCelular'
     * @see
     */
    public String getNmTelCelular() {
        return this.nmTelCelular;
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
     * Nome: getNmTelFixo Recupera o valor do atributo 'nmTelFixo'.
     * @return valor do atributo 'nmTelFixo'
     * @see
     */
    public String getNmTelFixo() {
        return this.nmTelFixo;
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
     * Nome: getNmUsuario Recupera o valor do atributo 'nmUsuario'.
     * @return valor do atributo 'nmUsuario'
     * @see
     */
    public String getNmUsuario() {
        return this.nmUsuario;
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
        return this.senha;
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
     * Nome: getTblContatos Recupera o valor do atributo 'tblContatos'.
     * @return valor do atributo 'tblContatos'
     * @see
     */
    public List<Contato> getTblContatos() {
        return this.tblContatos;
    }

    /**
     * Nome: setTblContatos Registra o valor do atributo 'tblContatos'.
     * @param tblContatos valor do atributo tbl contatos
     * @see
     */
    public void setTblContatos(List<Contato> tblContatos) {
        this.tblContatos = tblContatos;
    }

    /**
     * Nome: getTblContratos Recupera o valor do atributo 'tblContratos'.
     * @return valor do atributo 'tblContratos'
     * @see
     */
    public List<Contrato> getTblContratos() {
        return this.tblContratos;
    }

    /**
     * Nome: setTblContratos Registra o valor do atributo 'tblContratos'.
     * @param tblContratos valor do atributo tbl contratos
     * @see
     */
    public void setTblContratos(List<Contrato> tblContratos) {
        this.tblContratos = tblContratos;
    }

    /**
     * Nome: getTblDispositivos Recupera o valor do atributo 'tblDispositivos'.
     * @return valor do atributo 'tblDispositivos'
     * @see
     */
    public List<Dispositivo> getTblDispositivos() {
        return this.tblDispositivos;
    }

    /**
     * Nome: setTblDispositivos Registra o valor do atributo 'tblDispositivos'.
     * @param tblDispositivos valor do atributo tbl dispositivos
     * @see
     */
    public void setTblDispositivos(List<Dispositivo> tblDispositivos) {
        this.tblDispositivos = tblDispositivos;
    }

    /**
     * Nome: getTblOcorrencias Recupera o valor do atributo 'tblOcorrencias'.
     * @return valor do atributo 'tblOcorrencias'
     * @see
     */
    public List<Ocorrencia> getTblOcorrencias() {
        return this.tblOcorrencias;
    }

    /**
     * Nome: setTblOcorrencias Registra o valor do atributo 'tblOcorrencias'.
     * @param tblOcorrencias valor do atributo tbl ocorrencias
     * @see
     */
    public void setTblOcorrencias(List<Ocorrencia> tblOcorrencias) {
        this.tblOcorrencias = tblOcorrencias;
    }

    /**
     * Nome: getTblPacientes Recupera o valor do atributo 'tblPacientes'.
     * @return valor do atributo 'tblPacientes'
     * @see
     */
    public List<Paciente> getTblPacientes() {
        return this.tblPacientes;
    }

    /**
     * Nome: setTblPacientes Registra o valor do atributo 'tblPacientes'.
     * @param tblPacientes valor do atributo tbl pacientes
     * @see
     */
    public void setTblPacientes(List<Paciente> tblPacientes) {
        this.tblPacientes = tblPacientes;
    }

}