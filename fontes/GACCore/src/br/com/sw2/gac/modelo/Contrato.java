/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblContrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByNmContrato", query = "SELECT c FROM Contrato c WHERE c.nmContrato = :nmContrato"),
    @NamedQuery(name = "Contrato.findByDtInicioValidade", query = "SELECT c FROM Contrato c WHERE c.dtInicioValidade = :dtInicioValidade"),
    @NamedQuery(name = "Contrato.findByDtFinalValidade", query = "SELECT c FROM Contrato c WHERE c.dtFinalValidade = :dtFinalValidade"),
    @NamedQuery(name = "Contrato.findByDtSuspensao", query = "SELECT c FROM Contrato c WHERE c.dtSuspensao = :dtSuspensao"),
    @NamedQuery(name = "Contrato.findByNmCPFContratante", query = "SELECT c FROM Contrato c WHERE c.nmCPFContratante = :nmCPFContratante"),
    @NamedQuery(name = "Contrato.findByNmNomeContratante", query = "SELECT c FROM Contrato c WHERE c.nmNomeContratante = :nmNomeContratante"),
    @NamedQuery(name = "Contrato.findByDsEnderecoContratante", query = "SELECT c FROM Contrato c WHERE c.dsEnderecoContratante = :dsEnderecoContratante"),
    @NamedQuery(name = "Contrato.findByDsBairroContratante", query = "SELECT c FROM Contrato c WHERE c.dsBairroContratante = :dsBairroContratante"),
    @NamedQuery(name = "Contrato.findByDsCidadeContratante", query = "SELECT c FROM Contrato c WHERE c.dsCidadeContratante = :dsCidadeContratante"),
    @NamedQuery(name = "Contrato.findByDsUFContratante", query = "SELECT c FROM Contrato c WHERE c.dsUFContratante = :dsUFContratante"),
    @NamedQuery(name = "Contrato.findByNmCEPContratante", query = "SELECT c FROM Contrato c WHERE c.nmCEPContratante = :nmCEPContratante"),
    @NamedQuery(name = "Contrato.findByDtNascContratante", query = "SELECT c FROM Contrato c WHERE c.dtNascContratante = :dtNascContratante"),
    @NamedQuery(name = "Contrato.findByDsEMailContratante", query = "SELECT c FROM Contrato c WHERE c.dsEMailContratante = :dsEMailContratante"),
    @NamedQuery(name = "Contrato.findByNmRGContratante", query = "SELECT c FROM Contrato c WHERE c.nmRGContratante = :nmRGContratante"),
    @NamedQuery(name = "Contrato.findByDtProxAtual", query = "SELECT c FROM Contrato c WHERE c.dtProxAtual = :dtProxAtual")})
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "nmContrato")
    private Integer nmContrato;
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;
    @Column(name = "dtFinalValidade")
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;
    @Column(name = "dtSuspensao")
    @Temporal(TemporalType.DATE)
    private Date dtSuspensao;
    @Basic(optional = false)
    @Column(name = "nmCPFContratante")
    private String nmCPFContratante;
    @Basic(optional = false)
    @Column(name = "nmNomeContratante")
    private String nmNomeContratante;
    @Basic(optional = false)
    @Column(name = "dsEnderecoContratante")
    private String dsEnderecoContratante;
    @Basic(optional = false)
    @Column(name = "dsBairroContratante")
    private String dsBairroContratante;
    @Basic(optional = false)
    @Column(name = "dsCidadeContratante")
    private String dsCidadeContratante;
    @Basic(optional = false)
    @Column(name = "dsUFContratante")
    private String dsUFContratante;
    @Basic(optional = false)
    @Column(name = "nmCEPContratante")
    private String nmCEPContratante;
    @Column(name = "dtNascContratante")
    @Temporal(TemporalType.DATE)
    private Date dtNascContratante;
    @Column(name = "dsEMailContratante")
    private String dsEMailContratante;
    @Column(name = "nmRGContratante")
    private String nmRGContratante;
    @Basic(optional = false)
    @Column(name = "dtProxAtual")
    @Temporal(TemporalType.DATE)
    private Date dtProxAtual;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @JoinColumn(name = "idServico", referencedColumnName = "idServico")
    @ManyToOne(optional = false)
    private PacoteServico idServico;
    @OneToMany(mappedBy = "nmContrato")
    private List<Cliente> clienteList;

    public Contrato() {
    }

    public Contrato(Integer nmContrato) {
        this.nmContrato = nmContrato;
    }

    public Contrato(Integer nmContrato, Date dtInicioValidade, String nmCPFContratante, String nmNomeContratante, String dsEnderecoContratante, String dsBairroContratante, String dsCidadeContratante, String dsUFContratante, String nmCEPContratante, Date dtProxAtual) {
        this.nmContrato = nmContrato;
        this.dtInicioValidade = dtInicioValidade;
        this.nmCPFContratante = nmCPFContratante;
        this.nmNomeContratante = nmNomeContratante;
        this.dsEnderecoContratante = dsEnderecoContratante;
        this.dsBairroContratante = dsBairroContratante;
        this.dsCidadeContratante = dsCidadeContratante;
        this.dsUFContratante = dsUFContratante;
        this.nmCEPContratante = nmCEPContratante;
        this.dtProxAtual = dtProxAtual;
    }

    public Integer getNmContrato() {
        return nmContrato;
    }

    public void setNmContrato(Integer nmContrato) {
        this.nmContrato = nmContrato;
    }

    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    public Date getDtSuspensao() {
        return dtSuspensao;
    }

    public void setDtSuspensao(Date dtSuspensao) {
        this.dtSuspensao = dtSuspensao;
    }

    public String getNmCPFContratante() {
        return nmCPFContratante;
    }

    public void setNmCPFContratante(String nmCPFContratante) {
        this.nmCPFContratante = nmCPFContratante;
    }

    public String getNmNomeContratante() {
        return nmNomeContratante;
    }

    public void setNmNomeContratante(String nmNomeContratante) {
        this.nmNomeContratante = nmNomeContratante;
    }

    public String getDsEnderecoContratante() {
        return dsEnderecoContratante;
    }

    public void setDsEnderecoContratante(String dsEnderecoContratante) {
        this.dsEnderecoContratante = dsEnderecoContratante;
    }

    public String getDsBairroContratante() {
        return dsBairroContratante;
    }

    public void setDsBairroContratante(String dsBairroContratante) {
        this.dsBairroContratante = dsBairroContratante;
    }

    public String getDsCidadeContratante() {
        return dsCidadeContratante;
    }

    public void setDsCidadeContratante(String dsCidadeContratante) {
        this.dsCidadeContratante = dsCidadeContratante;
    }

    public String getDsUFContratante() {
        return dsUFContratante;
    }

    public void setDsUFContratante(String dsUFContratante) {
        this.dsUFContratante = dsUFContratante;
    }

    public String getNmCEPContratante() {
        return nmCEPContratante;
    }

    public void setNmCEPContratante(String nmCEPContratante) {
        this.nmCEPContratante = nmCEPContratante;
    }

    public Date getDtNascContratante() {
        return dtNascContratante;
    }

    public void setDtNascContratante(Date dtNascContratante) {
        this.dtNascContratante = dtNascContratante;
    }

    public String getDsEMailContratante() {
        return dsEMailContratante;
    }

    public void setDsEMailContratante(String dsEMailContratante) {
        this.dsEMailContratante = dsEMailContratante;
    }

    public String getNmRGContratante() {
        return nmRGContratante;
    }

    public void setNmRGContratante(String nmRGContratante) {
        this.nmRGContratante = nmRGContratante;
    }

    public Date getDtProxAtual() {
        return dtProxAtual;
    }

    public void setDtProxAtual(Date dtProxAtual) {
        this.dtProxAtual = dtProxAtual;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public PacoteServico getIdServico() {
        return idServico;
    }

    public void setIdServico(PacoteServico idServico) {
        this.idServico = idServico;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nmContrato != null ? nmContrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.nmContrato == null && other.nmContrato != null) || (this.nmContrato != null && !this.nmContrato.equals(other.nmContrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Contrato[ nmContrato=" + nmContrato + " ]";
    }
    
}
