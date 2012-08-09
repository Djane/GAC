/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * <b>Descrição:</b> <br>
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
    @Column(name = "IdParametro")
    private Integer idParametro;

    /** Atributo dias dados. */
    @Basic(optional = false)
    @Column(name = "DiasDados")
    private int diasDados;

    /** Atributo dias bem estar. */
    @Basic(optional = false)
    @Column(name = "DiasBemEstar")
    private int diasBemEstar;

    /** Atributo tolera rotina cliente. */
    @Column(name = "ToleraRotinaCliente")
    private Integer toleraRotinaCliente;

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

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (idParametro != null) {
            hash +=  idParametro.hashCode();
        } else {
            hash +=  0;
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
