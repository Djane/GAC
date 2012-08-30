package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblMonitoramento database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblMonitoramento")
public class Monitoramento implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo dta inicio monitora. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Temporal(TemporalType.TIMESTAMP)
    @Column(unique = true, nullable = false)
    private Date dtaInicioMonitora;

    /** Atributo acontecimento. */
    @Column(name = "Acontecimento")
    private String acontecimento;

    /** Atributo tp monitora. */
    @Column()
    private String tpMonitora;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl paciente. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "NmCPFPaciente", nullable = false)
    private Paciente tblPaciente;

    /**
     * Construtor Padrao Instancia um novo objeto Monitoramento.
     */
    public Monitoramento() {
    }

    /**
     * Nome: getDtaInicioMonitora Recupera o valor do atributo 'dtaInicioMonitora'.
     * @return valor do atributo 'dtaInicioMonitora'
     * @see
     */
    public Date getDtaInicioMonitora() {
        return this.dtaInicioMonitora;
    }

    /**
     * Nome: setDtaInicioMonitora Registra o valor do atributo 'dtaInicioMonitora'.
     * @param dtaInicioMonitora valor do atributo dta inicio monitora
     * @see
     */
    public void setDtaInicioMonitora(Date dtaInicioMonitora) {
        this.dtaInicioMonitora = dtaInicioMonitora;
    }

    /**
     * Nome: getAcontecimento Recupera o valor do atributo 'acontecimento'.
     * @return valor do atributo 'acontecimento'
     * @see
     */
    public String getAcontecimento() {
        return this.acontecimento;
    }

    /**
     * Nome: setAcontecimento Registra o valor do atributo 'acontecimento'.
     * @param acontecimento valor do atributo acontecimento
     * @see
     */
    public void setAcontecimento(String acontecimento) {
        this.acontecimento = acontecimento;
    }

    /**
     * Nome: getTpMonitora Recupera o valor do atributo 'tpMonitora'.
     * @return valor do atributo 'tpMonitora'
     * @see
     */
    public String getTpMonitora() {
        return this.tpMonitora;
    }

    /**
     * Nome: setTpMonitora Registra o valor do atributo 'tpMonitora'.
     * @param tpMonitora valor do atributo tp monitora
     * @see
     */
    public void setTpMonitora(String tpMonitora) {
        this.tpMonitora = tpMonitora;
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