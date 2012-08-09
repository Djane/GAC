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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
@Table(name = "tblocorrencia")
@NamedQueries({
    @NamedQuery(name = "Ocorrencia.findAll", query = "SELECT o FROM Ocorrencia o")})
public class Ocorrencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdOcorrencia")
    private Integer idOcorrencia;
    @Column(name = "tpOcorrencia")
    private Integer tpOcorrencia;
    @Column(name = "StatusOcorre")
    private Character statusOcorre;
    @Column(name = "dtaAtend")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaAtend;
    @Lob
    @Column(name = "AcaoOcorrencia")
    private String acaoOcorrencia;
    @Lob
    @Column(name = "ReclOcorrencia")
    private String reclOcorrencia;
    @Column(name = "dtaHoraAbertura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAbertura;
    @Column(name = "dtaHoraFechamento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraFechamento;
    @Column(name = "dtaHoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraInicio;
    @Column(name = "dtaHoraTermino")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraTermino;
    @Lob
    @Column(name = "Conclusao")
    private String conclusao;
    @JoinColumn(name = "IdScript", referencedColumnName = "IdScript")
    @ManyToOne(optional = false)
    private Script idScript;
    @JoinColumn(name = "NmCPFCliente", referencedColumnName = "NmCPFCliente")
    @ManyToOne(optional = false)
    private Cliente nmCPFCliente;
    @JoinColumn(name = "Login", referencedColumnName = "login")
    @ManyToOne(optional = false)
    private Usuario login;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOcorrencia")
    private List<Acionamento> acionamentoList;

    public Ocorrencia() {
    }

    public Ocorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Integer getTpOcorrencia() {
        return tpOcorrencia;
    }

    public void setTpOcorrencia(Integer tpOcorrencia) {
        this.tpOcorrencia = tpOcorrencia;
    }

    public Character getStatusOcorre() {
        return statusOcorre;
    }

    public void setStatusOcorre(Character statusOcorre) {
        this.statusOcorre = statusOcorre;
    }

    public Date getDtaAtend() {
        return dtaAtend;
    }

    public void setDtaAtend(Date dtaAtend) {
        this.dtaAtend = dtaAtend;
    }

    public String getAcaoOcorrencia() {
        return acaoOcorrencia;
    }

    public void setAcaoOcorrencia(String acaoOcorrencia) {
        this.acaoOcorrencia = acaoOcorrencia;
    }

    public String getReclOcorrencia() {
        return reclOcorrencia;
    }

    public void setReclOcorrencia(String reclOcorrencia) {
        this.reclOcorrencia = reclOcorrencia;
    }

    public Date getDtaHoraAbertura() {
        return dtaHoraAbertura;
    }

    public void setDtaHoraAbertura(Date dtaHoraAbertura) {
        this.dtaHoraAbertura = dtaHoraAbertura;
    }

    public Date getDtaHoraFechamento() {
        return dtaHoraFechamento;
    }

    public void setDtaHoraFechamento(Date dtaHoraFechamento) {
        this.dtaHoraFechamento = dtaHoraFechamento;
    }

    public Date getDtaHoraInicio() {
        return dtaHoraInicio;
    }

    public void setDtaHoraInicio(Date dtaHoraInicio) {
        this.dtaHoraInicio = dtaHoraInicio;
    }

    public Date getDtaHoraTermino() {
        return dtaHoraTermino;
    }

    public void setDtaHoraTermino(Date dtaHoraTermino) {
        this.dtaHoraTermino = dtaHoraTermino;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }

    public Script getIdScript() {
        return idScript;
    }

    public void setIdScript(Script idScript) {
        this.idScript = idScript;
    }

    public Cliente getNmCPFCliente() {
        return nmCPFCliente;
    }

    public void setNmCPFCliente(Cliente nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    public Usuario getLogin() {
        return login;
    }

    public void setLogin(Usuario login) {
        this.login = login;
    }

    public List<Acionamento> getAcionamentoList() {
        return acionamentoList;
    }

    public void setAcionamentoList(List<Acionamento> acionamentoList) {
        this.acionamentoList = acionamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOcorrencia != null ? idOcorrencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ocorrencia)) {
            return false;
        }
        Ocorrencia other = (Ocorrencia) object;
        if ((this.idOcorrencia == null && other.idOcorrencia != null) || (this.idOcorrencia != null && !this.idOcorrencia.equals(other.idOcorrencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Ocorrencia[ idOcorrencia=" + idOcorrencia + " ]";
    }
    
}
