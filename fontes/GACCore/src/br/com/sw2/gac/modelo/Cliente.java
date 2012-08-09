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
 *
 * @author rogerio
 */
@Entity
@Table(name = "tblcliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c")})
public class Cliente implements Serializable {
    @JoinTable(name = "tblclientexdispositivo", joinColumns = {
        @JoinColumn(name = "NmCPFCliente", referencedColumnName = "NmCPFCliente")}, inverseJoinColumns = {
        @JoinColumn(name = "IdDispositivo", referencedColumnName = "IdDispositivo")})
    @ManyToMany
    private List<Dispositivo> dispositivoList;
    @JoinColumn(name = "nmContrato", referencedColumnName = "nmContrato")
    @ManyToOne
    private Contrato nmContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Ocorrencia> ocorrenciaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Contato> contatoList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NmCPFCliente")
    private String nmCPFCliente;
    @Basic(optional = false)
    @Column(name = "nmCliente")
    private String nmCliente;
    @Basic(optional = false)
    @Column(name = "dsEndereco")
    private String dsEndereco;
    @Basic(optional = false)
    @Column(name = "dsBairro")
    private String dsBairro;
    @Basic(optional = false)
    @Column(name = "dsCidade")
    private String dsCidade;
    @Basic(optional = false)
    @Column(name = "dsEstado")
    private String dsEstado;
    @Basic(optional = false)
    @Column(name = "dsCEP")
    private String dsCEP;
    @Basic(optional = false)
    @Column(name = "nrRG")
    private String nrRG;
    @Column(name = "tpSexo")
    private Character tpSexo;
    @Column(name = "nrTelefone")
    private String nrTelefone;
    @Column(name = "nrCelular")
    private String nrCelular;
    @Basic(optional = false)
    @Column(name = "dtNascimento")
    @Temporal(TemporalType.DATE)
    private Date dtNascimento;
    @Lob
    @Column(name = "nmNecessidadeEspecial")
    private String nmNecessidadeEspecial;
    @Column(name = "nmPlanoSaude")
    private String nmPlanoSaude;
    @Lob
    @Column(name = "dsCobertura")
    private String dsCobertura;
    @Column(name = "dsEmail")
    private String dsEmail;
    @ManyToMany(mappedBy = "clienteList")
    private List<Cid> cidList;
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Monitoramento> monitoramentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Tratamento> tratamentoList;

    public Cliente() {
    }

    public Cliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    public Cliente(String nmCPFCliente, String nmCliente, String dsEndereco, String dsBairro, String dsCidade, String dsEstado, String dsCEP, String nrRG, Date dtNascimento) {
        this.nmCPFCliente = nmCPFCliente;
        this.nmCliente = nmCliente;
        this.dsEndereco = dsEndereco;
        this.dsBairro = dsBairro;
        this.dsCidade = dsCidade;
        this.dsEstado = dsEstado;
        this.dsCEP = dsCEP;
        this.nrRG = nrRG;
        this.dtNascimento = dtNascimento;
    }

    public String getNmCPFCliente() {
        return nmCPFCliente;
    }

    public void setNmCPFCliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String dsEndereco) {
        this.dsEndereco = dsEndereco;
    }

    public String getDsBairro() {
        return dsBairro;
    }

    public void setDsBairro(String dsBairro) {
        this.dsBairro = dsBairro;
    }

    public String getDsCidade() {
        return dsCidade;
    }

    public void setDsCidade(String dsCidade) {
        this.dsCidade = dsCidade;
    }

    public String getDsEstado() {
        return dsEstado;
    }

    public void setDsEstado(String dsEstado) {
        this.dsEstado = dsEstado;
    }

    public String getDsCEP() {
        return dsCEP;
    }

    public void setDsCEP(String dsCEP) {
        this.dsCEP = dsCEP;
    }

    public String getNrRG() {
        return nrRG;
    }

    public void setNrRG(String nrRG) {
        this.nrRG = nrRG;
    }

    public Character getTpSexo() {
        return tpSexo;
    }

    public void setTpSexo(Character tpSexo) {
        this.tpSexo = tpSexo;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getNrCelular() {
        return nrCelular;
    }

    public void setNrCelular(String nrCelular) {
        this.nrCelular = nrCelular;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getNmNecessidadeEspecial() {
        return nmNecessidadeEspecial;
    }

    public void setNmNecessidadeEspecial(String nmNecessidadeEspecial) {
        this.nmNecessidadeEspecial = nmNecessidadeEspecial;
    }

    public String getNmPlanoSaude() {
        return nmPlanoSaude;
    }

    public void setNmPlanoSaude(String nmPlanoSaude) {
        this.nmPlanoSaude = nmPlanoSaude;
    }

    public String getDsCobertura() {
        return dsCobertura;
    }

    public void setDsCobertura(String dsCobertura) {
        this.dsCobertura = dsCobertura;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public List<Cid> getCidList() {
        return cidList;
    }

    public void setCidList(List<Cid> cidList) {
        this.cidList = cidList;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public List<Monitoramento> getMonitoramentoList() {
        return monitoramentoList;
    }

    public void setMonitoramentoList(List<Monitoramento> monitoramentoList) {
        this.monitoramentoList = monitoramentoList;
    }

    public List<Tratamento> getTratamentoList() {
        return tratamentoList;
    }

    public void setTratamentoList(List<Tratamento> tratamentoList) {
        this.tratamentoList = tratamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmCPFCliente != null ? nmCPFCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.nmCPFCliente == null && other.nmCPFCliente != null) || (this.nmCPFCliente != null && !this.nmCPFCliente.equals(other.nmCPFCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Cliente[ nmCPFCliente=" + nmCPFCliente + " ]";
    }

    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
    }

    public Contrato getNmContrato() {
        return nmContrato;
    }

    public void setNmContrato(Contrato nmContrato) {
        this.nmContrato = nmContrato;
    }

    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }
    
}
