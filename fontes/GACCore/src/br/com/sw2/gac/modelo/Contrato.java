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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "tblcontrato")
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c")})
public class Contrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nmContrato")
    private String nmContrato;
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
    @OneToMany(mappedBy = "nmContrato")
    private List<Cliente> clienteList;
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @JoinColumn(name = "IdServico", referencedColumnName = "IdServico")
    @ManyToOne(optional = false)
    private Pacoteservico idServico;

    public Contrato() {
    }

    public Contrato(String nmContrato) {
        this.nmContrato = nmContrato;
    }

    public Contrato(String nmContrato, Date dtInicioValidade, String nmCPFContratante, String nmNomeContratante, String dsEnderecoContratante, String dsBairroContratante, String dsCidadeContratante, String dsUFContratante, String nmCEPContratante, Date dtProxAtual) {
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

    public String getNmContrato() {
        return nmContrato;
    }

    public void setNmContrato(String nmContrato) {
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

    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public Pacoteservico getIdServico() {
        return idServico;
    }

    public void setIdServico(Pacoteservico idServico) {
        this.idServico = idServico;
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
