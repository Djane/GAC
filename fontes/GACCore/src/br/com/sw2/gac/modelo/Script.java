package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblScript database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblScript")
public class Script implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id script. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdScript", unique = true, nullable = false)
    private int idScript;

    /** Atributo ds descricao. */
    @Column()
    private String dsDescricao;

    /** Atributo ds processo. */
    @Lob
    private String dsProcesso;

    /** Atributo dt final validade. */
    @Temporal(TemporalType.DATE)
    private Date dtFinalValidade;

    /** Atributo dt inicio validade. */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtInicioValidade;

    /** Atributo nm titulo. */
    @Column(nullable = false)
    private String nmTitulo;

    // bi-directional many-to-one association to TblOcorrencia
    /** Atributo tbl ocorrencias. */
    @OneToMany(mappedBy = "tblScript")
    private List<Ocorrencia> tblOcorrencias;

    /**
     * Construtor Padrao Instancia um novo objeto Script.
     */
    public Script() {
    }

    /**
     * Nome: getIdScript Recupera o valor do atributo 'idScript'.
     * @return valor do atributo 'idScript'
     * @see
     */
    public int getIdScript() {
        return this.idScript;
    }

    /**
     * Nome: setIdScript Registra o valor do atributo 'idScript'.
     * @param idScript valor do atributo id script
     * @see
     */
    public void setIdScript(int idScript) {
        this.idScript = idScript;
    }

    /**
     * Nome: getDsDescricao Recupera o valor do atributo 'dsDescricao'.
     * @return valor do atributo 'dsDescricao'
     * @see
     */
    public String getDsDescricao() {
        return this.dsDescricao;
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
     * Nome: getDsProcesso Recupera o valor do atributo 'dsProcesso'.
     * @return valor do atributo 'dsProcesso'
     * @see
     */
    public String getDsProcesso() {
        return this.dsProcesso;
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
     * Nome: getNmTitulo Recupera o valor do atributo 'nmTitulo'.
     * @return valor do atributo 'nmTitulo'
     * @see
     */
    public String getNmTitulo() {
        return this.nmTitulo;
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
     * Nome: getTblOcorrencias Recupera o valor do atributo 'tblOcorrencias'.
     * @return valor do atributo 'tblOcorrencias'
     * @see
     */
    public List<Ocorrencia> getTblOcorrencias() {
        return this.tblOcorrencias;
    }

    /**
     * Nome: setTblOcorrencias Registra o valor do atributo 'tblOcorrencias'.
     * @param tblOcorrencias valor do atributo tbl ocorrencias
     * @see
     */
    public void setTblOcorrencias(List<Ocorrencia> tblOcorrencias) {
        this.tblOcorrencias = tblOcorrencias;
    }

}