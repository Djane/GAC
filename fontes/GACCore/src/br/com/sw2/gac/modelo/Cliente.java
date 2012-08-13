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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "TblCliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByNmCPFCliente", query = "SELECT c FROM Cliente c WHERE c.nmCPFCliente = :nmCPFCliente"),
    @NamedQuery(name = "Cliente.findByNmCliente", query = "SELECT c FROM Cliente c WHERE c.nmCliente = :nmCliente"),
    @NamedQuery(name = "Cliente.findByDsEndereco", query = "SELECT c FROM Cliente c WHERE c.dsEndereco = :dsEndereco"),
    @NamedQuery(name = "Cliente.findByDsBairro", query = "SELECT c FROM Cliente c WHERE c.dsBairro = :dsBairro"),
    @NamedQuery(name = "Cliente.findByDsCidade", query = "SELECT c FROM Cliente c WHERE c.dsCidade = :dsCidade"),
    @NamedQuery(name = "Cliente.findByDsEstado", query = "SELECT c FROM Cliente c WHERE c.dsEstado = :dsEstado"),
    @NamedQuery(name = "Cliente.findByDsCEP", query = "SELECT c FROM Cliente c WHERE c.dsCEP = :dsCEP"),
    @NamedQuery(name = "Cliente.findByNrRG", query = "SELECT c FROM Cliente c WHERE c.nrRG = :nrRG"),
    @NamedQuery(name = "Cliente.findByTpSexo", query = "SELECT c FROM Cliente c WHERE c.tpSexo = :tpSexo"),
    @NamedQuery(name = "Cliente.findByNrTelefone", query = "SELECT c FROM Cliente c WHERE c.nrTelefone = :nrTelefone"),
    @NamedQuery(name = "Cliente.findByNrCelular", query = "SELECT c FROM Cliente c WHERE c.nrCelular = :nrCelular"),
    @NamedQuery(name = "Cliente.findByDtNascimento", query = "SELECT c FROM Cliente c WHERE c.dtNascimento = :dtNascimento"),
    @NamedQuery(name = "Cliente.findByNmPlanoSaude", query = "SELECT c FROM Cliente c WHERE c.nmPlanoSaude = :nmPlanoSaude"),
    @NamedQuery(name = "Cliente.findByDsEmail", query = "SELECT c FROM Cliente c WHERE c.dsEmail = :dsEmail"),
    @NamedQuery(name = "Cliente.findByDtaProxBemEstar", query = "SELECT c FROM Cliente c WHERE c.dtaProxBemEstar = :dtaProxBemEstar")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nmCPFCliente")
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
    private Integer tpSexo;
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
    @Column(name = "dtaProxBemEstar")
    @Temporal(TemporalType.DATE)
    private Date dtaProxBemEstar;
    @ManyToMany(mappedBy = "clienteList")
    private List<Dispositivo> dispositivoList;
    @ManyToMany(mappedBy = "clienteList")
    private List<CID> cIDList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Contato> contatoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Tratamento> tratamentoList;
    @JoinColumn(name = "login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @JoinColumn(name = "nmContrato", referencedColumnName = "nmContrato")
    @ManyToOne
    private Contrato nmContrato;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Monitoramento> monitoramentoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nmCPFCliente")
    private List<Ocorrencia> ocorrenciaList;

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

    public Integer getTpSexo() {
        return tpSexo;
    }

    public void setTpSexo(Integer tpSexo) {
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

    public Date getDtaProxBemEstar() {
        return dtaProxBemEstar;
    }

    public void setDtaProxBemEstar(Date dtaProxBemEstar) {
        this.dtaProxBemEstar = dtaProxBemEstar;
    }

    @XmlTransient
    public List<Dispositivo> getDispositivoList() {
        return dispositivoList;
    }

    public void setDispositivoList(List<Dispositivo> dispositivoList) {
        this.dispositivoList = dispositivoList;
    }

    @XmlTransient
    public List<CID> getCIDList() {
        return cIDList;
    }

    public void setCIDList(List<CID> cIDList) {
        this.cIDList = cIDList;
    }

    @XmlTransient
    public List<Contato> getContatoList() {
        return contatoList;
    }

    public void setContatoList(List<Contato> contatoList) {
        this.contatoList = contatoList;
    }

    @XmlTransient
    public List<Tratamento> getTratamentoList() {
        return tratamentoList;
    }

    public void setTratamentoList(List<Tratamento> tratamentoList) {
        this.tratamentoList = tratamentoList;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public Contrato getNmContrato() {
        return nmContrato;
    }

    public void setNmContrato(Contrato nmContrato) {
        this.nmContrato = nmContrato;
    }

    @XmlTransient
    public List<Monitoramento> getMonitoramentoList() {
        return monitoramentoList;
    }

    public void setMonitoramentoList(List<Monitoramento> monitoramentoList) {
        this.monitoramentoList = monitoramentoList;
    }

    @XmlTransient
    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
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
    
}
