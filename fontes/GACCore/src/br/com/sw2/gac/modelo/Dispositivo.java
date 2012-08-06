package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblDispositivo database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblDispositivo")
public class Dispositivo implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id dispositivo. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdDispositivo", unique = true, nullable = false)
    private String idDispositivo;

    /** Atributo dta entrada. */
    @Temporal(TemporalType.DATE)
    private Date dtaEntrada;

    /** Atributo dta fabrica. */
    @Temporal(TemporalType.DATE)
    private Date dtaFabrica;

    /** Atributo dta proxima manut. */
    @Temporal(TemporalType.DATE)
    private Date dtaProximaManut;

    /** Atributo dta sucata. */
    @Temporal(TemporalType.DATE)
    private Date dtaSucata;

    /** Atributo local. */
    @Column(name = "Local")
    private String local;

    /** Atributo tp dispositivo. */
    @Column(name = "TpDispositivo")
    private Integer tpDispositivo;

    /** Atributo tp estado. */
    @Column()
    private Integer tpEstado;

    // bi-directional many-to-one association to TbUsuario
    /** Atributo tb usuario. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Login", nullable = false)
    private Usuario tbUsuario;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl pacientes1. */
    @OneToMany(mappedBy = "tblDispositivo1")
    private List<Paciente> tblPacientes1;

    // bi-directional many-to-one association to TblPaciente
    /** Atributo tbl pacientes2. */
    @OneToMany(mappedBy = "tblDispositivo2")
    private List<Paciente> tblPacientes2;

    /**
     * Construtor Padrao Instancia um novo objeto Dispositivo.
     */
    public Dispositivo() {
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return this.idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getDtaEntrada Recupera o valor do atributo 'dtaEntrada'.
     * @return valor do atributo 'dtaEntrada'
     * @see
     */
    public Date getDtaEntrada() {
        return this.dtaEntrada;
    }

    /**
     * Nome: setDtaEntrada Registra o valor do atributo 'dtaEntrada'.
     * @param dtaEntrada valor do atributo dta entrada
     * @see
     */
    public void setDtaEntrada(Date dtaEntrada) {
        this.dtaEntrada = dtaEntrada;
    }

    /**
     * Nome: getDtaFabrica Recupera o valor do atributo 'dtaFabrica'.
     * @return valor do atributo 'dtaFabrica'
     * @see
     */
    public Date getDtaFabrica() {
        return this.dtaFabrica;
    }

    /**
     * Nome: setDtaFabrica Registra o valor do atributo 'dtaFabrica'.
     * @param dtaFabrica valor do atributo dta fabrica
     * @see
     */
    public void setDtaFabrica(Date dtaFabrica) {
        this.dtaFabrica = dtaFabrica;
    }

    /**
     * Nome: getDtaProximaManut Recupera o valor do atributo 'dtaProximaManut'.
     * @return valor do atributo 'dtaProximaManut'
     * @see
     */
    public Date getDtaProximaManut() {
        return this.dtaProximaManut;
    }

    /**
     * Nome: setDtaProximaManut Registra o valor do atributo 'dtaProximaManut'.
     * @param dtaProximaManut valor do atributo dta proxima manut
     * @see
     */
    public void setDtaProximaManut(Date dtaProximaManut) {
        this.dtaProximaManut = dtaProximaManut;
    }

    /**
     * Nome: getDtaSucata Recupera o valor do atributo 'dtaSucata'.
     * @return valor do atributo 'dtaSucata'
     * @see
     */
    public Date getDtaSucata() {
        return this.dtaSucata;
    }

    /**
     * Nome: setDtaSucata Registra o valor do atributo 'dtaSucata'.
     * @param dtaSucata valor do atributo dta sucata
     * @see
     */
    public void setDtaSucata(Date dtaSucata) {
        this.dtaSucata = dtaSucata;
    }

    /**
     * Nome: getLocal Recupera o valor do atributo 'local'.
     * @return valor do atributo 'local'
     * @see
     */
    public String getLocal() {
        return this.local;
    }

    /**
     * Nome: setLocal Registra o valor do atributo 'local'.
     * @param local valor do atributo local
     * @see
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Nome: getTpDispositivo Recupera o valor do atributo 'tpDispositivo'.
     * @return valor do atributo 'tpDispositivo'
     * @see
     */
    public Integer getTpDispositivo() {
        return this.tpDispositivo;
    }

    /**
     * Nome: setTpDispositivo Registra o valor do atributo 'tpDispositivo'.
     * @param tpDispositivo valor do atributo tp dispositivo
     * @see
     */
    public void setTpDispositivo(Integer tpDispositivo) {
        this.tpDispositivo = tpDispositivo;
    }

    /**
     * Nome: getTpEstado Recupera o valor do atributo 'tpEstado'.
     * @return valor do atributo 'tpEstado'
     * @see
     */
    public Integer getTpEstado() {
        return this.tpEstado;
    }

    /**
     * Nome: setTpEstado Registra o valor do atributo 'tpEstado'.
     * @param tpEstado valor do atributo tp estado
     * @see
     */
    public void setTpEstado(Integer tpEstado) {
        this.tpEstado = tpEstado;
    }

    /**
     * Nome: getTbUsuario Recupera o valor do atributo 'tbUsuario'.
     * @return valor do atributo 'tbUsuario'
     * @see
     */
    public Usuario getTbUsuario() {
        return this.tbUsuario;
    }

    /**
     * Nome: setTbUsuario Registra o valor do atributo 'tbUsuario'.
     * @param tbUsuario valor do atributo tb usuario
     * @see
     */
    public void setTbUsuario(Usuario tbUsuario) {
        this.tbUsuario = tbUsuario;
    }

    /**
     * Nome: getTblPacientes1 Recupera o valor do atributo 'tblPacientes1'.
     * @return valor do atributo 'tblPacientes1'
     * @see
     */
    public List<Paciente> getTblPacientes1() {
        return this.tblPacientes1;
    }

    /**
     * Nome: setTblPacientes1 Registra o valor do atributo 'tblPacientes1'.
     * @param tblPacientes1 valor do atributo tbl pacientes1
     * @see
     */
    public void setTblPacientes1(List<Paciente> tblPacientes1) {
        this.tblPacientes1 = tblPacientes1;
    }

    /**
     * Nome: getTblPacientes2 Recupera o valor do atributo 'tblPacientes2'.
     * @return valor do atributo 'tblPacientes2'
     * @see
     */
    public List<Paciente> getTblPacientes2() {
        return this.tblPacientes2;
    }

    /**
     * Nome: setTblPacientes2 Registra o valor do atributo 'tblPacientes2'.
     * @param tblPacientes2 valor do atributo tbl pacientes2
     * @see
     */
    public void setTblPacientes2(List<Paciente> tblPacientes2) {
        this.tblPacientes2 = tblPacientes2;
    }

}