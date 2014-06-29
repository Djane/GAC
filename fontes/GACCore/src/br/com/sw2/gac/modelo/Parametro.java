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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descricao:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblparametro")
@NamedQueries({ @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p") })
public class Parametro implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id parametro. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idParametro")
    private Integer idParametro;

    /** Atributo dias dados. */
    @Basic(optional = false)
    @Column(name = "diasDados")
    private int diasDados;

    /** Atributo dias bem estar. */
    @Basic(optional = false)
    @Column(name = "diasBemEstar")
    private int diasBemEstar;

    /** Atributo tolera rotina cliente. */
    @Column(name = "toleraRotinaCliente")
    private Integer toleraRotinaCliente;

    @Column(name = "hrKeepAlive")
    private Integer horasrParaKeepAlive;    
    
    @Column(name = "nrFoneCentral1")
    private String telefoneCentral1;    
    
    @Column(name = "nrFoneCentral2")
    private String telefoneCentral2;    
    
    @Column(name = "nrFoneCentral3")
    private String telefoneCentral3;    

    @Column(name = "nrFoneCentral4")
    private String telefoneCentral4;    
    
    @Column(name = "nrFoneCentral5")
    private String telefoneCentral5;    
    
    @Column(name = "nrFoneCentral6")
    private String telefoneCentral6;    
    
    @Column(name = "hrGSM")
    private String horarGSM;    
    
    @Column(name = "dtUltimaAltera")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataUltimaAlteracao;
    
    
    /**
     * Construtor Padrao Instancia um novo objeto Parametro.
     */
    public Parametro() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Parametro.
     * @param idParametro the id parametro
     */
    public Parametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Parametro.
     * @param idParametro the id parametro
     * @param diasDados the dias dados
     * @param diasBemEstar the dias bem estar
     */
    public Parametro(Integer idParametro, int diasDados, int diasBemEstar) {
        this.idParametro = idParametro;
        this.diasDados = diasDados;
        this.diasBemEstar = diasBemEstar;
    }

    /**
     * Nome: getIdParametro Recupera o valor do atributo 'idParametro'.
     * @return valor do atributo 'idParametro'
     * @see
     */
    public Integer getIdParametro() {
        return idParametro;
    }

    /**
     * Nome: setIdParametro Registra o valor do atributo 'idParametro'.
     * @param idParametro valor do atributo id parametro
     * @see
     */
    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * Nome: getDiasDados Recupera o valor do atributo 'diasDados'.
     * @return valor do atributo 'diasDados'
     * @see
     */
    public int getDiasDados() {
        return diasDados;
    }

    /**
     * Nome: setDiasDados Registra o valor do atributo 'diasDados'.
     * @param diasDados valor do atributo dias dados
     * @see
     */
    public void setDiasDados(int diasDados) {
        this.diasDados = diasDados;
    }

    /**
     * Nome: getDiasBemEstar Recupera o valor do atributo 'diasBemEstar'.
     * @return valor do atributo 'diasBemEstar'
     * @see
     */
    public int getDiasBemEstar() {
        return diasBemEstar;
    }

    /**
     * Nome: setDiasBemEstar Registra o valor do atributo 'diasBemEstar'.
     * @param diasBemEstar valor do atributo dias bem estar
     * @see
     */
    public void setDiasBemEstar(int diasBemEstar) {
        this.diasBemEstar = diasBemEstar;
    }

    /**
     * Nome: getToleraRotinaCliente Recupera o valor do atributo 'toleraRotinaCliente'.
     * @return valor do atributo 'toleraRotinaCliente'
     * @see
     */
    public Integer getToleraRotinaCliente() {
        return toleraRotinaCliente;
    }

    /**
     * Nome: setToleraRotinaCliente Registra o valor do atributo 'toleraRotinaCliente'.
     * @param toleraRotinaCliente valor do atributo tolera rotina cliente
     * @see
     */
    public void setToleraRotinaCliente(Integer toleraRotinaCliente) {
        this.toleraRotinaCliente = toleraRotinaCliente;
    }
    
    
    public Integer getHorasrParaKeepAlive() {
        return horasrParaKeepAlive;
    }

    public void setHorasrParaKeepAlive(Integer horasrParaKeepAlive) {
        this.horasrParaKeepAlive = horasrParaKeepAlive;
    }

    public String getTelefoneCentral1() {
        return telefoneCentral1;
    }

    public void setTelefoneCentral1(String telefoneCentral1) {
        this.telefoneCentral1 = telefoneCentral1;
    }

    public String getTelefoneCentral2() {
        return telefoneCentral2;
    }

    public void setTelefoneCentral2(String telefoneCentral2) {
        this.telefoneCentral2 = telefoneCentral2;
    }

    public String getTelefoneCentral3() {
        return telefoneCentral3;
    }

    public void setTelefoneCentral3(String telefoneCentral3) {
        this.telefoneCentral3 = telefoneCentral3;
    }

    public String getTelefoneCentral4() {
        return telefoneCentral4;
    }

    public void setTelefoneCentral4(String telefoneCentral4) {
        this.telefoneCentral4 = telefoneCentral4;
    }

    public String getTelefoneCentral5() {
        return telefoneCentral5;
    }

    public void setTelefoneCentral5(String telefoneCentral5) {
        this.telefoneCentral5 = telefoneCentral5;
    }

    public String getTelefoneCentral6() {
        return telefoneCentral6;
    }

    public void setTelefoneCentral6(String telefoneCentral6) {
        this.telefoneCentral6 = telefoneCentral6;
    }

    public String getHorarGSM() {
        return horarGSM;
    }

    public void setHorarGSM(String horarGSM) {
        this.horarGSM = horarGSM;
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (idParametro != null) {
            hash += idParametro.hashCode();
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

        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.idParametro == null && other.idParametro != null)
                || (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
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
        return "br.com.sw2.gac.modelo.Parametro[ idParametro=" + idParametro + " ]";
    }

}
