/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descri��o:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblcliente")
@NamedQueries({ @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c") })
public class Cliente implements Serializable {

    /** Atributo dispositivo list. */
    @JoinTable(name = "tblclientexdispositivo",
            joinColumns = { @JoinColumn(name = "NmCPFCliente",
            referencedColumnName = "NmCPFCliente") },
            inverseJoinColumns = { @JoinColumn(name = "IdDispositivo", referencedColumnName = "IdDispositivo") })
    @ManyToMany
    private List<Dispositivo> dispositivoList;

    /** Atributo nm contrato. */
    @JoinColumn(name = "nmContrato", referencedColumnName = "nmContrato")
    @ManyToOne
    private Contrato nmContrato;

    /** Atributo ocorrencia list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Ocorrencia> ocorrenciaList;

    /** Atributo contato list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Contato> contatoList;

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo nm cpf cliente. */
    @Id
    @Basic(optional = false)
    @Column(name = "NmCPFCliente")
    private String nmCPFCliente;

    /** Atributo nm cliente. */
    @Basic(optional = false)
    @Column(name = "nmCliente")
    private String nmCliente;

    /** Atributo ds endereco. */
    @Basic(optional = false)
    @Column(name = "dsEndereco")
    private String dsEndereco;

    /** Atributo ds bairro. */
    @Basic(optional = false)
    @Column(name = "dsBairro")
    private String dsBairro;

    /** Atributo ds cidade. */
    @Basic(optional = false)
    @Column(name = "dsCidade")
    private String dsCidade;

    /** Atributo ds estado. */
    @Basic(optional = false)
    @Column(name = "dsEstado")
    private String dsEstado;

    /** Atributo ds cep. */
    @Basic(optional = false)
    @Column(name = "dsCEP")
    private String dsCEP;

    /** Atributo nr rg. */
    @Basic(optional = false)
    @Column(name = "nrRG")
    private String nrRG;

    /** Atributo tp sexo. */
    @Column(name = "tpSexo")
    private Character tpSexo;

    /** Atributo nr telefone. */
    @Column(name = "nrTelefone")
    private String nrTelefone;

    /** Atributo nr celular. */
    @Column(name = "nrCelular")
    private String nrCelular;

    /** Atributo dt nascimento. */
    @Basic(optional = false)
    @Column(name = "dtNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;

    /** Atributo nm necessidade especial. */
    @Lob
    @Column(name = "nmNecessidadeEspecial")
    private String nmNecessidadeEspecial;

    /** Atributo nm plano saude. */
    @Column(name = "nmPlanoSaude")
    private String nmPlanoSaude;

    /** Atributo ds cobertura. */
    @Lob
    @Column(name = "dsCobertura")
    private String dsCobertura;

    /** Atributo ds email. */
    @Column(name = "dsEmail")
    private String dsEmail;

    /** Atributo cid list. */
    @ManyToMany(mappedBy = "clienteList")
    private List<Cid> cidList;

    /** Atributo login. */
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;

    /** Atributo monitoramento list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Monitoramento> monitoramentoList;

    /** Atributo tratamento list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Tratamento> tratamentoList;

    /**
     * Construtor Padrao Instancia um novo objeto Cliente.
     */
    public Cliente() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Cliente.
     * @param nmCPFCliente the nm cpf cliente
     */
    public Cliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public String getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /**
     * Nome: getNmCliente Recupera o valor do atributo 'nmCliente'.
     * @return valor do atributo 'nmCliente'
     * @see
     */
    public String getNmCliente() {
        return nmCliente;
    }

    /**
     * Nome: setNmCliente Registra o valor do atributo 'nmCliente'.
     * @param nmCliente valor do atributo nm cliente
     * @see
     */
    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    /**
     * Nome: getDsEndereco Recupera o valor do atributo 'dsEndereco'.
     * @return valor do atributo 'dsEndereco'
     * @see
     */
    public String getDsEndereco() {
        return dsEndereco;
    }

    /**
     * Nome: setDsEndereco Registra o valor do atributo 'dsEndereco'.
     * @param dsEndereco valor do atributo ds endereco
     * @see
     */
    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    /**
     * Nome: getDsBairro Recupera o valor do atributo 'dsBairro'.
     * @return valor do atributo 'dsBairro'
     * @see
     */
    public String getDsBairro() {
        return dsBairro;
    }

    /**
     * Nome: setDsBairro Registra o valor do atributo 'dsBairro'.
     * @param dsBairro valor do atributo ds bairro
     * @see
     */
    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    /**
     * Nome: getDsCidade Recupera o valor do atributo 'dsCidade'.
     * @return valor do atributo 'dsCidade'
     * @see
     */
    public String getDsCidade() {
        return dsCidade;
    }

    /**
     * Nome: setDsCidade Registra o valor do atributo 'dsCidade'.
     * @param dsCidade valor do atributo ds cidade
     * @see
     */
    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    /**
     * Nome: getDsEstado Recupera o valor do atributo 'dsEstado'.
     * @return valor do atributo 'dsEstado'
     * @see
     */
    public String getDsEstado() {
        return dsEstado;
    }

    /**
     * Nome: setDsEstado Registra o valor do atributo 'dsEstado'.
     * @param dsEstado valor do atributo ds estado
     * @see
     */
    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    /**
     * Nome: getDsCEP Recupera o valor do atributo 'dsCEP'.
     * @return valor do atributo 'dsCEP'
     * @see
     */
    public String getDsCEP() {
        return dsCEP;
    }

    /**
     * Nome: setDsCEP Registra o valor do atributo 'dsCEP'.
     * @param dsCEP valor do atributo ds cep
     * @see
     */
    public void setDsCEP(String dsCEP) {
        this.dsCEP = dsCEP;
    }

    /**
     * Nome: getNrRG Recupera o valor do atributo 'nrRG'.
     * @return valor do atributo 'nrRG'
     * @see
     */
    public String getNrRG() {
        return nrRG;
    }

    /**
     * Nome: setNrRG Registra o valor do atributo 'nrRG'.
     * @param nrRG valor do atributo nr rg
     * @see
     */
    public void setNrRG(String nrRG) {
        this.nrRG = nrRG;
    }

    /**
     * Nome: getTpSexo Recupera o valor do atributo 'tpSexo'.
     * @return valor do atributo 'tpSexo'
     * @see
     */
    public Character getTpSexo() {
        return tpSexo;
    }

    /**
     * Nome: setTpSexo Registra o valor do atributo 'tpSexo'.
     * @param tpSexo valor do atributo tp sexo
     * @see
     */
    public void setTpSexo(Character tpSexo) {
        this.tpSexo = tpSexo;
    }

    /**
     * Nome: getNrTelefone Recupera o valor do atributo 'nrTelefone'.
     * @return valor do atributo 'nrTelefone'
     * @see
     */
    public String getNrTelefone() {
        return nrTelefone;
    }

    /**
     * Nome: setNrTelefone Registra o valor do atributo 'nrTelefone'.
     * @param nrTelefone valor do atributo nr telefone
     * @see
     */
    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    /**
     * Nome: getNrCelular Recupera o valor do atributo 'nrCelular'.
     * @return valor do atributo 'nrCelular'
     * @see
     */
    public String getNrCelular() {
        return nrCelular;
    }

    /**
     * Nome: setNrCelular Registra o valor do atributo 'nrCelular'.
     * @param nrCelular valor do atributo nr celular
     * @see
     */
    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    /**
     * Nome: getDtNascimento Recupera o valor do atributo 'dtNascimento'.
     * @return valor do atributo 'dtNascimento'
     * @see
     */
    public Date getDtNascimento() {
        return dtNascimento;
    }

    /**
     * Nome: setDtNascimento Registra o valor do atributo 'dtNascimento'.
     * @param dtNascimento valor do atributo dt nascimento
     * @see
     */
    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    /**
     * Nome: getNmNecessidadeEspecial Recupera o valor do atributo 'nmNecessidadeEspecial'.
     * @return valor do atributo 'nmNecessidadeEspecial'
     * @see
     */
    public String getNmNecessidadeEspecial() {
        return nmNecessidadeEspecial;
    }

    /**
     * Nome: setNmNecessidadeEspecial Registra o valor do atributo 'nmNecessidadeEspecial'.
     * @param nmNecessidadeEspecial valor do atributo nm necessidade especial
     * @see
     */
    public void setNmNecessidadeEspecial(String nmNecessidadeEspecial) {
        this.nmNecessidadeEspecial = nmNecessidadeEspecial;
    }

    /**
     * Nome: getNmPlanoSaude Recupera o valor do atributo 'nmPlanoSaude'.
     * @return valor do atributo 'nmPlanoSaude'
     * @see
     */
    public String getNmPlanoSaude() {
        return nmPlanoSaude;
    }

    /**
     * Nome: setNmPlanoSaude Registra o valor do atributo 'nmPlanoSaude'.
     * @param nmPlanoSaude valor do atributo nm plano saude
     * @see
     */
    public void setNmPlanoSaude(String nmPlanoSaude) {
        this.nmPlanoSaude = nmPlanoSaude;
    }

    /**
     * Nome: getDsCobertura Recupera o valor do atributo 'dsCobertura'.
     * @return valor do atributo 'dsCobertura'
     * @see
     */
    public String getDsCobertura() {
        return dsCobertura;
    }

    /**
     * Nome: setDsCobertura Registra o valor do atributo 'dsCobertura'.
     * @param dsCobertura valor do atributo ds cobertura
     * @see
     */
    public void setDsCobertura(String dsCobertura) {
        this.dsCobertura = dsCobertura;
    }

    /**
     * Nome: getDsEmail Recupera o valor do atributo 'dsEmail'.
     * @return valor do atributo 'dsEmail'
     * @see
     */
    public String getDsEmail() {
        return dsEmail;
    }

    /**
     * Nome: setDsEmail Registra o valor do atributo 'dsEmail'.
     * @param dsEmail valor do atributo ds email
     * @see
     */
    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    /**
     * Nome: getCidList Recupera o valor do atributo 'cidList'.
     * @return valor do atributo 'cidList'
     * @see
     */
    public List<Cid> getCidList() {
        return cidList;
    }

    /**
     * Nome: setCidList Registra o valor do atributo 'cidList'.
     * @param cidList valor do atributo cid list
     * @see
     */
    public void setCidList(List<Cid> cidList) {
        this.cidList = cidList;
    }

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public Usuario getLogin() {
        return login;
    }

    /**
     * Nome: setLogin Registra o valor do atributo 'login'.
     * @param login valor do atributo login
     * @see
     */
    public void setLogin(Usuario login) {
        this.login = login;
    }

    /**
     * Nome: getMonitoramentoList Recupera o valor do atributo 'monitoramentoList'.
     * @return valor do atributo 'monitoramentoList'
     * @see
     */
    public List<Monitoramento> getMonitoramentoList() {
        return monitoramentoList;
    }

    /**
     * Nome: setMonitoramentoList Registra o valor do atributo 'monitoramentoList'.
     * @param monitoramentoList valor do atributo monitoramento list
     * @see
     */
    public void setMonitoramentoList(List<Monitoramento> monitoramentoList) {
        this.monitoramentoList = monitoramentoList;
    }

    /**
     * Nome: getTratamentoList Recupera o valor do atributo 'tratamentoList'.
     * @return valor do atributo 'tratamentoList'
     * @see
     */
    public List<Tratamento> getTratamentoList() {
        return tratamentoList;
    }

    /**
     * Nome: setTratamentoList Registra o valor do atributo 'tratamentoList'.
     * @param tratamentoList valor do atributo tratamento list
     * @see
     */
    public void setTratamentoList(List<Tratamento> tratamentoList) {
        this.tratamentoList = tratamentoList;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (nmCPFCliente != null) {
            hash += nmCPFCliente.hashCode();
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
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.nmCPFCliente == null && other.nmCPFCliente != null)
                || (this.nmCPFCliente != null && !this.nmCPFCliente.equals(other.nmCPFCliente))) {
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
        return "br.com.sw2.gac.modelo.Cliente[ nmCPFCliente=" + nmCPFCliente + " ]";
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
     * Nome: getNmContrato Recupera o valor do atributo 'nmContrato'.
     * @return valor do atributo 'nmContrato'
     * @see
     */
    public Contrato getNmContrato() {
        return nmContrato;
    }

    /**
     * Nome: setNmContrato Registra o valor do atributo 'nmContrato'.
     * @param nmContrato valor do atributo nm contrato
     * @see
     */
    public void setNmContrato(Contrato nmContrato) {
        this.nmContrato = nmContrato;
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