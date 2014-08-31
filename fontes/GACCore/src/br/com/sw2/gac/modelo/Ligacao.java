package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
@Entity
@Table(name = "LigacaoGAC")
public class Ligacao implements Serializable {


    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -7762543985354672452L;

    /** Atributo id uniqueid. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private String idUniqueid;

    /** Atributo num atendente. */
    @Column
    private String numAtendente;

    /** Atributo rum ramal. */
    @Column
    private String numRamal;

    /** Atributo num telefone. */
    @Column
    private String numTelefone;

    /** Atributo cod pulseira. */
    @Column
    private String codPulseira;

    /** Atributo dt hr ligacao. */
    @Column(name = "dtHrLigacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHrLigacao;

    /** Atributo dt hr atendimento. */
    @Column(name = "dtHrAtendimento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtHrAtendimento;

    /**
     * Nome: getIdUniqueid Recupera o valor do atributo 'idUniqueid'.
     * @return valor do atributo 'idUniqueid'
     * @see
     */
    public String getIdUniqueid() {
        return idUniqueid;
    }

    /**
     * Nome: setIdUniqueid Registra o valor do atributo 'idUniqueid'.
     * @param idUniqueid valor do atributo id uniqueid
     * @see
     */
    public void setIdUniqueid(String idUniqueid) {
        this.idUniqueid = idUniqueid;
    }

    /**
     * Nome: getNumAtendente Recupera o valor do atributo 'numAtendente'.
     * @return valor do atributo 'numAtendente'
     * @see
     */
    public String getNumAtendente() {
        return numAtendente;
    }

    /**
     * Nome: setNumAtendente Registra o valor do atributo 'numAtendente'.
     * @param numAtendente valor do atributo num atendente
     * @see
     */
    public void setNumAtendente(String numAtendente) {
        this.numAtendente = numAtendente;
    }

    /**
     * Nome: getRumRamal Recupera o valor do atributo 'rumRamal'.
     * @return valor do atributo 'rumRamal'
     * @see
     */
    public String getNumRamal() {
        return numRamal;
    }

    /**
     * Nome: setRumRamal Registra o valor do atributo 'rumRamal'.
     * @param rumRamal valor do atributo rum ramal
     * @see
     */
    public void setNumRamal(String rumRamal) {
        this.numRamal = rumRamal;
    }

    /**
     * Nome: getNumTelefone Recupera o valor do atributo 'numTelefone'.
     * @return valor do atributo 'numTelefone'
     * @see
     */
    public String getNumTelefone() {
        return numTelefone;
    }

    /**
     * Nome: setNumTelefone Registra o valor do atributo 'numTelefone'.
     * @param numTelefone valor do atributo num telefone
     * @see
     */
    public void setNumTelefone(String numTelefone) {
        this.numTelefone = numTelefone;
    }

    /**
     * Nome: getCodPulseira Recupera o valor do atributo 'codPulseira'.
     * @return valor do atributo 'codPulseira'
     * @see
     */
    public String getCodPulseira() {
        return codPulseira;
    }

    /**
     * Nome: setCodPulseira Registra o valor do atributo 'codPulseira'.
     * @param codPulseira valor do atributo cod pulseira
     * @see
     */
    public void setCodPulseira(String codPulseira) {
        this.codPulseira = codPulseira;
    }

    /**
     * Nome: getDtHrLigacao Recupera o valor do atributo 'dtHrLigacao'.
     * @return valor do atributo 'dtHrLigacao'
     * @see
     */
    public Date getDtHrLigacao() {
        return dtHrLigacao;
    }

    /**
     * Nome: setDtHrLigacao Registra o valor do atributo 'dtHrLigacao'.
     * @param dtHrLigacao valor do atributo dt hr ligacao
     * @see
     */
    public void setDtHrLigacao(Date dtHrLigacao) {
        this.dtHrLigacao = dtHrLigacao;
    }

    /**
     * Nome: getDtHrAtendimento Recupera o valor do atributo 'dtHrAtendimento'.
     * @return valor do atributo 'dtHrAtendimento'
     * @see
     */
    public Date getDtHrAtendimento() {
        return dtHrAtendimento;
    }

    /**
     * Nome: setDtHrAtendimento Registra o valor do atributo 'dtHrAtendimento'.
     * @param dtHrAtendimento valor do atributo dt hr atendimento
     * @see
     */
    public void setDtHrAtendimento(Date dtHrAtendimento) {
        this.dtHrAtendimento = dtHrAtendimento;
    }

}
