package br.com.sw2.gac.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>Descrição: The persistent class for the TblTratamento database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblTratamento")
public class Tratamento implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id. */
    @EmbeddedId
    private TratamentoPK id;

    /** Atributo descr trata. */
    @Column(name = "DescrTrata")
    private String descrTrata;

    /** Atributo freq minutos. */
    @Column(name = "FreqMinutos")
    private int freqMinutos;

    /** Atributo nome trata. */
    @Column(name = "NomeTrata")
    private String nomeTrata;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl paciente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NmCPFPaciente", nullable = false, insertable = false, updatable = false)
    private Paciente tblPaciente;

    /**
     * Construtor Padrao Instancia um novo objeto Tratamento.
     */
    public Tratamento() {
    }

    /**
     * Nome: getId Recupera o valor do atributo 'id'.
     * @return valor do atributo 'id'
     * @see
     */
    public TratamentoPK getId() {
        return this.id;
    }

    /**
     * Nome: setId Registra o valor do atributo 'id'.
     * @param id valor do atributo id
     * @see
     */
    public void setId(TratamentoPK id) {
        this.id = id;
    }

    /**
     * Nome: getDescrTrata Recupera o valor do atributo 'descrTrata'.
     * @return valor do atributo 'descrTrata'
     * @see
     */
    public String getDescrTrata() {
        return this.descrTrata;
    }

    /**
     * Nome: setDescrTrata Registra o valor do atributo 'descrTrata'.
     * @param descrTrata valor do atributo descr trata
     * @see
     */
    public void setDescrTrata(String descrTrata) {
        this.descrTrata = descrTrata;
    }

    /**
     * Nome: getFreqMinutos Recupera o valor do atributo 'freqMinutos'.
     * @return valor do atributo 'freqMinutos'
     * @see
     */
    public int getFreqMinutos() {
        return this.freqMinutos;
    }

    /**
     * Nome: setFreqMinutos Registra o valor do atributo 'freqMinutos'.
     * @param freqMinutos valor do atributo freq minutos
     * @see
     */
    public void setFreqMinutos(int freqMinutos) {
        this.freqMinutos = freqMinutos;
    }

    /**
     * Nome: getNomeTrata Recupera o valor do atributo 'nomeTrata'.
     * @return valor do atributo 'nomeTrata'
     * @see
     */
    public String getNomeTrata() {
        return this.nomeTrata;
    }

    /**
     * Nome: setNomeTrata Registra o valor do atributo 'nomeTrata'.
     * @param nomeTrata valor do atributo nome trata
     * @see
     */
    public void setNomeTrata(String nomeTrata) {
        this.nomeTrata = nomeTrata;
    }

    /**
     * Nome: getTblPaciente Recupera o valor do atributo 'tblPaciente'.
     * @return valor do atributo 'tblPaciente'
     * @see
     */
    public Paciente getTblPaciente() {
        return this.tblPaciente;
    }

    /**
     * Nome: setTblPaciente Registra o valor do atributo 'tblPaciente'.
     * @param tblPaciente valor do atributo tbl paciente
     * @see
     */
    public void setTblPaciente(Paciente tblPaciente) {
        this.tblPaciente = tblPaciente;
    }

}