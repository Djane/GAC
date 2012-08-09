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
@Table(name = "tblsms")
@NamedQueries({
    @NamedQuery(name = "Sms.findAll", query = "SELECT s FROM Sms s")})
public class Sms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdSMS")
    private Integer idSMS;
    @Basic(optional = false)
    @Column(name = "tpMensagem")
    private String tpMensagem;
    @Basic(optional = false)
    @Column(name = "dsMensagem")
    private String dsMensagem;
    @Column(name = "idMomento")
    private Character idMomento;
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;
    @Column(name = "dtTerminoValidade")
    @Temporal(TemporalType.DATE)
    private Date dtTerminoValidade;
    @OneToMany(mappedBy = "idSMS")
    private List<Acionamento> acionamentoList;

    public Sms() {
    }

    public Sms(Integer idSMS) {
        this.idSMS = idSMS;
    }

    public Sms(Integer idSMS, String tpMensagem, String dsMensagem, Date dtInicioValidade) {
        this.idSMS = idSMS;
        this.tpMensagem = tpMensagem;
        this.dsMensagem = dsMensagem;
        this.dtInicioValidade = dtInicioValidade;
    }

    public Integer getIdSMS() {
        return idSMS;
    }

    public void setIdSMS(Integer idSMS) {
        this.idSMS = idSMS;
    }

    public String getTpMensagem() {
        return tpMensagem;
    }

    public void setTpMensagem(String tpMensagem) {
        this.tpMensagem = tpMensagem;
    }

    public String getDsMensagem() {
        return dsMensagem;
    }

    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
    }

    public Character getIdMomento() {
        return idMomento;
    }

    public void setIdMomento(Character idMomento) {
        this.idMomento = idMomento;
    }

    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    public Date getDtTerminoValidade() {
        return dtTerminoValidade;
    }

    public void setDtTerminoValidade(Date dtTerminoValidade) {
        this.dtTerminoValidade = dtTerminoValidade;
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
        hash += (idSMS != null ? idSMS.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sms)) {
            return false;
        }
        Sms other = (Sms) object;
        if ((this.idSMS == null && other.idSMS != null) || (this.idSMS != null && !this.idSMS.equals(other.idSMS))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Sms[ idSMS=" + idSMS + " ]";
    }
    
}
