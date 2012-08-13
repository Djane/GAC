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
import javax.persistence.Lob;
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
@Table(name = "TblScript")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Script.findAll", query = "SELECT s FROM Script s"),
    @NamedQuery(name = "Script.findByIdScript", query = "SELECT s FROM Script s WHERE s.idScript = :idScript"),
    @NamedQuery(name = "Script.findByNmTitulo", query = "SELECT s FROM Script s WHERE s.nmTitulo = :nmTitulo"),
    @NamedQuery(name = "Script.findByDsDescricao", query = "SELECT s FROM Script s WHERE s.dsDescricao = :dsDescricao"),
    @NamedQuery(name = "Script.findByDtInicioValidade", query = "SELECT s FROM Script s WHERE s.dtInicioValidade = :dtInicioValidade"),
    @NamedQuery(name = "Script.findByDtFinalValidade", query = "SELECT s FROM Script s WHERE s.dtFinalValidade = :dtFinalValidade")})
public class Script implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idScript")
    private Integer idScript;
    @Basic(optional = false)
    @Column(name = "nmTitulo")
    private String nmTitulo;
    @Lob
    @Column(name = "dsProcesso")
    private String dsProcesso;
    @Column(name = "dsDescricao")
    private String dsDescricao;
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;
    @Column(name = "dtFinalValidade")
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idScript")
    private List<Ocorrencia> ocorrenciaList;

    public Script() {
    }

    public Script(Integer idScript) {
        this.idScript = idScript;
    }

    public Script(Integer idScript, String nmTitulo, Date dtInicioValidade) {
        this.idScript = idScript;
        this.nmTitulo = nmTitulo;
        this.dtInicioValidade = dtInicioValidade;
    }

    public Integer getIdScript() {
        return idScript;
    }

    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }

    public String getNmTitulo() {
        return nmTitulo;
    }

    public void setNmTitulo(String nmTitulo) {
        this.nmTitulo = nmTitulo;
    }

    public String getDsProcesso() {
        return dsProcesso;
    }

    public void setDsProcesso(String dsProcesso) {
        this.dsProcesso = dsProcesso;
    }

    public String getDsDescricao() {
        return dsDescricao;
    }

    public void setDsDescricao(String dsDescricao) {
        this.dsDescricao = dsDescricao;
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
        hash += (idScript != null ? idScript.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Script)) {
            return false;
        }
        Script other = (Script) object;
        if ((this.idScript == null && other.idScript != null) || (this.idScript != null && !this.idScript.equals(other.idScript))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.Script[ idScript=" + idScript + " ]";
    }
    
}
