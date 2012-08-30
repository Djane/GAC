package br.com.sw2.gac.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * <b>Descrição: The primary key class for the TblTratamento database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Embeddable
public class TratamentoPK implements Serializable {
    // default serial version id, required for serializable classes.
    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id tratamento. */
    @Column(name = "IdTratamento", unique = true, nullable = false)
    private int idTratamento;

    /** Atributo nm cpf paciente. */
    @Column(name = "NmCPFPaciente", unique = true, nullable = false)
    private String nmCPFPaciente;

    /**
     * Construtor Padrao Instancia um novo objeto TratamentoPK.
     */
    public TratamentoPK() {
    }

    /**
     * Nome: getIdTratamento Recupera o valor do atributo 'idTratamento'.
     * @return valor do atributo 'idTratamento'
     * @see
     */
    public int getIdTratamento() {
        return this.idTratamento;
    }

    /**
     * Nome: setIdTratamento Registra o valor do atributo 'idTratamento'.
     * @param idTratamento valor do atributo id tratamento
     * @see
     */
    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Nome: getNmCPFPaciente Recupera o valor do atributo 'nmCPFPaciente'.
     * @return valor do atributo 'nmCPFPaciente'
     * @see
     */
    public String getNmCPFPaciente() {
        return this.nmCPFPaciente;
    }

    /**
     * Nome: setNmCPFPaciente Registra o valor do atributo 'nmCPFPaciente'.
     * @param nmCPFPaciente valor do atributo nm cpf paciente
     * @see
     */
    public void setNmCPFPaciente(String nmCPFPaciente) {
        this.nmCPFPaciente = nmCPFPaciente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TratamentoPK)) {
            return false;
        }
        TratamentoPK castOther = (TratamentoPK) other;
        return (this.idTratamento == castOther.idTratamento)
                && this.nmCPFPaciente.equals(castOther.nmCPFPaciente);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int hash = 17;
        hash = hash * prime + this.idTratamento;
        hash = hash * prime + this.nmCPFPaciente.hashCode();

        return hash;
    }
}