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

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "tblscript")
@NamedQueries({ @NamedQuery(name = "Script.findAll", query = "SELECT s FROM Script s") })
public class Script implements Serializable {

    /** Atributo ocorrencia list. */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idScript")
    private List<Ocorrencia> ocorrenciaList;

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id script. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "IdScript")
    private Integer idScript;

    /** Atributo nm titulo. */
    @Basic(optional = false)
    @Column(name = "nmTitulo")
    private String nmTitulo;

    /** Atributo ds processo. */
    @Lob
    @Column(name = "dsProcesso")
    private String dsProcesso;

    /** Atributo ds descricao. */
    @Column(name = "dsDescricao")
    private String dsDescricao;

    /** Atributo dt inicio validade. */
    @Basic(optional = false)
    @Column(name = "dtInicioValidade")
    @Temporal(TemporalType.DATE)
    private Date dtInicioValidade;

    /** Atributo dt final validade. */
    @Column(name = "dtFinalValidade")
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;

    /**
     * Construtor Padrao Instancia um novo objeto Script.
     */
    public Script() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto Script.
     * @param idScript the id script
     */
    public Script(Integer idScript) {
        this.idScript = idScript;
    }

    /**
     * Construtor Padrao Instancia um novo objeto Script.
     * @param idScript the id script
     * @param nmTitulo the nm titulo
     * @param dtInicioValidade the dt inicio validade
     */
    public Script(Integer idScript, String nmTitulo, Date dtInicioValidade) {
        this.idScript = idScript;
        this.nmTitulo = nmTitulo;
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getIdScript Recupera o valor do atributo 'idScript'.
     * @return valor do atributo 'idScript'
     * @see
     */
    public Integer getIdScript() {
        return idScript;
    }

    /**
     * Nome: setIdScript Registra o valor do atributo 'idScript'.
     * @param idScript valor do atributo id script
     * @see
     */
    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }

    /**
     * Nome: getNmTitulo Recupera o valor do atributo 'nmTitulo'.
     * @return valor do atributo 'nmTitulo'
     * @see
     */
    public String getNmTitulo() {
        return nmTitulo;
    }

    /**
     * Nome: setNmTitulo Registra o valor do atributo 'nmTitulo'.
     * @param nmTitulo valor do atributo nm titulo
     * @see
     */
    public void setNmTitulo(String nmTitulo) {
        this.nmTitulo = nmTitulo;
    }

    /**
     * Nome: getDsProcesso Recupera o valor do atributo 'dsProcesso'.
     * @return valor do atributo 'dsProcesso'
     * @see
     */
    public String getDsProcesso() {
        return dsProcesso;
    }

    /**
     * Nome: setDsProcesso Registra o valor do atributo 'dsProcesso'.
     * @param dsProcesso valor do atributo ds processo
     * @see
     */
    public void setDsProcesso(String dsProcesso) {
        this.dsProcesso = dsProcesso;
    }

    /**
     * Nome: getDsDescricao Recupera o valor do atributo 'dsDescricao'.
     * @return valor do atributo 'dsDescricao'
     * @see
     */
    public String getDsDescricao() {
        return dsDescricao;
    }

    /**
     * Nome: setDsDescricao Registra o valor do atributo 'dsDescricao'.
     * @param dsDescricao valor do atributo ds descricao
     * @see
     */
    public void setDsDescricao(String dsDescricao) {
        this.dsDescricao = dsDescricao;
    }

    /**
     * Nome: getDtInicioValidade Recupera o valor do atributo 'dtInicioValidade'.
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    /**
     * Nome: setDtInicioValidade Registra o valor do atributo 'dtInicioValidade'.
     * @param dtInicioValidade valor do atributo dt inicio validade
     * @see
     */
    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getDtFinalValidade Recupera o valor do atributo 'dtFinalValidade'.
     * @return valor do atributo 'dtFinalValidade'
     * @see
     */
    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    /**
     * Nome: setDtFinalValidade Registra o valor do atributo 'dtFinalValidade'.
     * @param dtFinalValidade valor do atributo dt final validade
     * @see
     */
    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;

        if (idScript != null) {
            hash += idScript.hashCode();
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

        if (!(object instanceof Script)) {
            return false;
        }
        Script other = (Script) object;
        if ((this.idScript == null && other.idScript != null)
                || (this.idScript != null && !this.idScript.equals(other.idScript))) {
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
        return "br.com.sw2.gac.modelo.Script[ idScript=" + idScript + " ]";
    }

    /**
     * Nome: getOcorrenciaList Recupera o valor do atributo 'ocorrenciaList'.
     * @return valor do atributo 'ocorrenciaList'
     * @see
     */
    public List<Ocorrencia> getOcorrenciaList() {
        return ocorrenciaList;
    }

    /**
     * Nome: setOcorrenciaList Registra o valor do atributo 'ocorrenciaList'.
     * @param ocorrenciaList valor do atributo ocorrencia list
     * @see
     */
    public void setOcorrenciaList(List<Ocorrencia> ocorrenciaList) {
        this.ocorrenciaList = ocorrenciaList;
    }

}
