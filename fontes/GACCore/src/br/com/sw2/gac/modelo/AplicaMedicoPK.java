/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Embeddable
public class AplicaMedicoPK implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 2281047328515782730L;

    /** Atributo hr aplicacao. */
    @Basic(optional = false)
    @Column(name = "hrAplicacao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hrAplicacao;

    /** Atributo id tratamento. */
    @Basic(optional = false)
    @Column(name = "idTratamento")
    private Integer idTratamento;

    /** Atributo nm cpf cliente. */
    @Basic(optional = false)
    @Column(name = "nmCPFCliente")
    private String nmCPFCliente;

    /**
     * Construtor Padrao Instancia um novo objeto AplicaMedicoPK.
     */
    public AplicaMedicoPK() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto AplicaMedicoPK.
     * @param hrAplicacao the hr aplicacao
     * @param idTratamento the id tratamento
     * @param nmCPFCliente the nm cpf cliente
     */
    public AplicaMedicoPK(Date hrAplicacao, int idTratamento, String nmCPFCliente) {
        this.hrAplicacao = hrAplicacao;
        this.idTratamento = idTratamento;
        this.nmCPFCliente = nmCPFCliente;
    }

    /**
     * Nome: getHrAplicacao Recupera o valor do atributo 'hrAplicacao'.
     * @return valor do atributo 'hrAplicacao'
     * @see
     */
    public Date getHrAplicacao() {
        return hrAplicacao;
    }

    /**
     * Nome: setHrAplicacao Registra o valor do atributo 'hrAplicacao'.
     * @param hrAplicacao valor do atributo hr aplicacao
     * @see
     */
    public void setHrAplicacao(Date hrAplicacao) {
        this.hrAplicacao = hrAplicacao;
    }

    /**
     * Nome: getIdTratamento Recupera o valor do atributo 'idTratamento'.
     * @return valor do atributo 'idTratamento'
     * @see
     */
    public Integer getIdTratamento() {
        return idTratamento;
    }

    /**
     * Nome: setIdTratamento Registra o valor do atributo 'idTratamento'.
     * @param idTratamento valor do atributo id tratamento
     * @see
     */
    public void setIdTratamento(Integer idTratamento) {
        this.idTratamento = idTratamento;
    }

    /**
     * Nome: getNmCPFCliente Recupera o valor do atributo 'nmCPFCliente'.
     * @return valor do atributo 'nmCPFCliente'
     * @see
     */
    public String getNmCPFCliente() {
        return nmCPFCliente;
    }

    /**
     * Nome: setNmCPFCliente Registra o valor do atributo 'nmCPFCliente'.
     * @param nmCPFCliente valor do atributo nm cpf cliente
     * @see
     */
    public void setNmCPFCliente(String nmCPFCliente) {
        this.nmCPFCliente = nmCPFCliente;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        if (hrAplicacao != null) {
            hash += hrAplicacao.hashCode();
        } else {
            hash += 0;
        }

        if (idTratamento != null) {
            hash += (int) idTratamento;
        }

        if (nmCPFCliente != null) {
            hash += nmCPFCliente.hashCode();
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

        boolean retorno = true;

        if ((object instanceof AplicaMedicoPK)) {

            retorno = equalC(object);
        } else {

            retorno = false;
        }
        return retorno;
    }

    /**
     * Nome: equalC Equal c.
     * @param object the object
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean equalC(Object object) {
        boolean retorno = true;
        AplicaMedicoPK other = (AplicaMedicoPK) object;
        if ((this.hrAplicacao == null && other.hrAplicacao != null)
            || (this.hrAplicacao != null && !this.hrAplicacao.equals(other.hrAplicacao))) {
            retorno = false;
        }
        if (this.idTratamento != other.idTratamento) {
            retorno = false;
        }
        if ((this.nmCPFCliente == null && other.nmCPFCliente != null)
            || (this.nmCPFCliente != null && !this.nmCPFCliente.equals(other.nmCPFCliente))) {
            retorno = false;
        }
        return retorno;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.AplicaMedicoPK[ hrAplicacao=" + hrAplicacao
            + ", idTratamento=" + idTratamento + ", nmCPFCliente=" + nmCPFCliente + " ]";
    }

}
