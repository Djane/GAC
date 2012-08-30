package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblPacoteServico database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblPacoteServico")
public class PacoteServico implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id servico. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdServico", unique = true, nullable = false)
    private int idServico;

    /** Atributo ds servico. */
    @Column()
    private String dsServico;

    /** Atributo dt final validade. */
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;

    /** Atributo dt inicio validade. */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtInicioValidade;

    // bi-directional many-to-one association to TblContrato
    /** Atributo tbl contratos. */
    @OneToMany(mappedBy = "tblPacoteServico")
    private List<Contrato> tblContratos;

    /**
     * Construtor Padrao Instancia um novo objeto PacoteServico.
     */
    public PacoteServico() {
    }

    /**
     * Nome: getIdServico Recupera o valor do atributo 'idServico'.
     * @return valor do atributo 'idServico'
     * @see
     */
    public int getIdServico() {
        return this.idServico;
    }

    /**
     * Nome: setIdServico Registra o valor do atributo 'idServico'.
     * @param idServico valor do atributo id servico
     * @see
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * Nome: getDsServico Recupera o valor do atributo 'dsServico'.
     * @return valor do atributo 'dsServico'
     * @see
     */
    public String getDsServico() {
        return this.dsServico;
    }

    /**
     * Nome: setDsServico Registra o valor do atributo 'dsServico'.
     * @param dsServico valor do atributo ds servico
     * @see
     */
    public void setDsServico(String dsServico) {
        this.dsServico = dsServico;
    }

    /**
     * Nome: getDtFinalValidade Recupera o valor do atributo 'dtFinalValidade'.
     * @return valor do atributo 'dtFinalValidade'
     * @see
     */
    public Date getDtFinalValidade() {
        return this.dtFinalValidade;
    }

    /**
     * Nome: setDtFinalValidade Registra o valor do atributo 'dtFinalValidade'.
     * @param dtFinalValidade valor do atributo dt final validade
     * @see
     */
    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    /**
     * Nome: getDtInicioValidade Recupera o valor do atributo 'dtInicioValidade'.
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return this.dtInicioValidade;
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
     * Nome: getTblContratos Recupera o valor do atributo 'tblContratos'.
     * @return valor do atributo 'tblContratos'
     * @see
     */
    public List<Contrato> getTblContratos() {
        return this.tblContratos;
    }

    /**
     * Nome: setTblContratos Registra o valor do atributo 'tblContratos'.
     * @param tblContratos valor do atributo tbl contratos
     * @see
     */
    public void setTblContratos(List<Contrato> tblContratos) {
        this.tblContratos = tblContratos;
    }

}