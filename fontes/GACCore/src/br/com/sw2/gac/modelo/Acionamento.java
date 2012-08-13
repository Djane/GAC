/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelo
 */
@Entity
@Table(name = "TblAcionamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Acionamento.findAll", query = "SELECT a FROM Acionamento a"),
    @NamedQuery(name = "Acionamento.findByIdAciona", query = "SELECT a FROM Acionamento a WHERE a.idAciona = :idAciona"),
    @NamedQuery(name = "Acionamento.findByDtaHoraAciona", query = "SELECT a FROM Acionamento a WHERE a.dtaHoraAciona = :dtaHoraAciona"),
    @NamedQuery(name = "Acionamento.findByDtaHoraInicio", query = "SELECT a FROM Acionamento a WHERE a.dtaHoraInicio = :dtaHoraInicio"),
    @NamedQuery(name = "Acionamento.findByDtaHoraFinal", query = "SELECT a FROM Acionamento a WHERE a.dtaHoraFinal = :dtaHoraFinal"),
    @NamedQuery(name = "Acionamento.findByTextoLivreSMS", query = "SELECT a FROM Acionamento a WHERE a.textoLivreSMS = :textoLivreSMS"),
    @NamedQuery(name = "Acionamento.findBySucesso", query = "SELECT a FROM Acionamento a WHERE a.sucesso = :sucesso")})
public class Acionamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdAciona")
    private Integer idAciona;
    @Column(name = "dtaHoraAciona")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAciona;
    @Lob
    @Column(name = "acaoPedida")
    private String acaoPedida;
    @Column(name = "dtaHoraInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraInicio;
    @Column(name = "dtaHoraFinal")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraFinal;
    @Column(name = "textoLivreSMS")
    private String textoLivreSMS;
    @Column(name = "sucesso")
    private Character sucesso;
    @JoinColumn(name = "idOcorrencia", referencedColumnName = "idOcorrencia")
    @ManyToOne(optional = false)
    private Ocorrencia idOcorrencia;
    @JoinColumn(name = "idContato", referencedColumnName = "idContato")
    @ManyToOne(optional = false)
    private Contato idContato;
    @JoinColumn(name = "idSMS", referencedColumnName = "idSMS")
    @ManyToOne
    private SMS idSMS;

    public Acionamento() {
    }

    public Acionamento(Integer idAciona) {
        this.idAciona = idAciona;
    }

    public Integer getIdAciona() {
        return idAciona;
    }

    public void setIdAciona(Integer idAciona) {
        this.idAciona = idAciona;
    }

    public Date getDtaHoraAciona() {
        return dtaHoraAciona;
    }

    public void setDtaHoraAciona(Date dtaHoraAciona) {
        this.dtaHoraAciona = dtaHoraAciona;
    }

    public String getAcaoPedida() {
        return acaoPedida;
    }

    public void setAcaoPedida(String acaoPedida) {
        this.acaoPedida = acaoPedida;
    }

    public Date getDtaHoraInicio() {
        return dtaHoraInicio;
    }

    public void setDtaHoraInicio(Date dtaHoraInicio) {
        this.dtaHoraInicio = dtaHoraInicio;
    }

    public Date getDtaHoraFinal() {
        return dtaHoraFinal;
    }

    public void setDtaHoraFinal(Date dtaHoraFinal) {
        this.dtaHoraFinal = dtaHoraFinal;
    }

    public String getTextoLivreSMS() {
        return textoLivreSMS;
    }

    public void setTextoLivreSMS(String textoLivreSMS) {
        this.textoLivreSMS = textoLivreSMS;
    }

    public Character getSucesso() {
        return sucesso;
    }

    public void setSucesso(Character sucesso) {
        this.sucesso = sucesso;
    }

    public Ocorrencia getIdOcorrencia() {
        return idOcorrencia;
    }

    public void setIdOcorrencia(Ocorrencia idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    public Contato getIdContato() {
        return idContato;
    }

    public void setIdContato(Contato idContato) {
        this.idContato = idContato;
    }

    public SMS getIdSMS() {
        return idSMS;
    }

    public void setIdSMS(SMS idSMS) {
        this.idSMS = idSMS;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAciona != null ? idAciona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Acionamento)) {
            return false;
        }
        Acionamento other = (Acionamento) object;
        if ((this.idAciona == null && other.idAciona != null) || (this.idAciona != null && !this.idAciona.equals(other.idAciona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Acionamento[ idAciona=" + idAciona + " ]";
    }
    
}
