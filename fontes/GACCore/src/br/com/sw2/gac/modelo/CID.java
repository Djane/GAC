package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <b>Descrição: The persistent class for the TbCID database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TbCID")
public class CID implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo cd cid. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "CdCID", unique = true, nullable = false)
    private String cdCID;

    /** Atributo nm doenca. */
    @Column()
    private String nmDoenca;

    // bi-directional many-to-one association to TipoDoenca
    /** Atributo tb tipo doenca. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cdTipoDoenca", nullable = false)
    private TipoDoenca tbTipoDoenca;

    // bi-directional many-to-many association to TblPaciente
    /** Atributo tbl pacientes. */
    @ManyToMany(mappedBy = "tbCids")
    private List<Paciente> tblPacientes;

    /**
     * Construtor Padrao Instancia um novo objeto CID.
     */
    public CID() {
    }

    /**
     * Nome: getCdCID Recupera o valor do atributo 'cdCID'.
     * @return valor do atributo 'cdCID'
     * @see
     */
    public String getCdCID() {
        return this.cdCID;
    }

    /**
     * Nome: setCdCID Registra o valor do atributo 'cdCID'.
     * @param cdCID valor do atributo cd cid
     * @see
     */
    public void setCdCID(String cdCID) {
        this.cdCID = cdCID;
    }

    /**
     * Nome: getNmDoenca Recupera o valor do atributo 'nmDoenca'.
     * @return valor do atributo 'nmDoenca'
     * @see
     */
    public String getNmDoenca() {
        return this.nmDoenca;
    }

    /**
     * Nome: setNmDoenca Registra o valor do atributo 'nmDoenca'.
     * @param nmDoenca valor do atributo nm doenca
     * @see
     */
    public void setNmDoenca(String nmDoenca) {
        this.nmDoenca = nmDoenca;
    }

    /**
     * Nome: getTbTipoDoenca Recupera o valor do atributo 'tbTipoDoenca'.
     * @return valor do atributo 'tbTipoDoenca'
     * @see
     */
    public TipoDoenca getTbTipoDoenca() {
        return this.tbTipoDoenca;
    }

    /**
     * Nome: setTbTipoDoenca Registra o valor do atributo 'tbTipoDoenca'.
     * @param tbTipoDoenca valor do atributo tb tipo doenca
     * @see
     */
    public void setTbTipoDoenca(TipoDoenca tbTipoDoenca) {
        this.tbTipoDoenca = tbTipoDoenca;
    }

    /**
     * Nome: getTblPacientes Recupera o valor do atributo 'tblPacientes'.
     * @return valor do atributo 'tblPacientes'
     * @see
     */
    public List<Paciente> getTblPacientes() {
        return this.tblPacientes;
    }

    /**
     * Nome: setTblPacientes Registra o valor do atributo 'tblPacientes'.
     * @param tblPacientes valor do atributo tbl pacientes
     * @see
     */
    public void setTblPacientes(List<Paciente> tblPacientes) {
        this.tblPacientes = tblPacientes;
    }

}