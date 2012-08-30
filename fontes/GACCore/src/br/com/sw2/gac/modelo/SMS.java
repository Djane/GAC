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
 * <b>Descrição: The persistent class for the TblSMS database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblSMS")
public class SMS implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id sms. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdSMS", unique = true, nullable = false)
    private int idSMS;

    /** Atributo ds mensagem. */
    @Column(nullable = false)
    private String dsMensagem;

    /** Atributo dt inicio validade. */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtInicioValidade;

    /** Atributo dt termino validade. */
    @Temporal(TemporalType.DATE)
    private Date dtTerminoValidade;

    /** Atributo id momento. */
    @Column()
    private String idMomento;

    /** Atributo tp mensagem. */
    @Column(nullable = false)
    private String tpMensagem;

    // bi-directional many-to-one association to TblAcionamento
    /** Atributo tbl acionamentos. */
    @OneToMany(mappedBy = "tblSm")
    private List<Acionamento> tblAcionamentos;

    /**
     * Construtor Padrao Instancia um novo objeto SMS.
     */
    public SMS() {
    }

    /**
     * Nome: getIdSMS Recupera o valor do atributo 'idSMS'.
     * @return valor do atributo 'idSMS'
     * @see
     */
    public int getIdSMS() {
        return this.idSMS;
    }

    /**
     * Nome: setIdSMS Registra o valor do atributo 'idSMS'.
     * @param idSMS valor do atributo id sms
     * @see
     */
    public void setIdSMS(int idSMS) {
        this.idSMS = idSMS;
    }

    /**
     * Nome: getDsMensagem Recupera o valor do atributo 'dsMensagem'.
     * @return valor do atributo 'dsMensagem'
     * @see
     */
    public String getDsMensagem() {
        return this.dsMensagem;
    }

    /**
     * Nome: setDsMensagem Registra o valor do atributo 'dsMensagem'.
     * @param dsMensagem valor do atributo ds mensagem
     * @see
     */
    public void setDsMensagem(String dsMensagem) {
        this.dsMensagem = dsMensagem;
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
     * Nome: getDtTerminoValidade Recupera o valor do atributo 'dtTerminoValidade'.
     * @return valor do atributo 'dtTerminoValidade'
     * @see
     */
    public Date getDtTerminoValidade() {
        return this.dtTerminoValidade;
    }

    /**
     * Nome: setDtTerminoValidade Registra o valor do atributo 'dtTerminoValidade'.
     * @param dtTerminoValidade valor do atributo dt termino validade
     * @see
     */
    public void setDtTerminoValidade(Date dtTerminoValidade) {
        this.dtTerminoValidade = dtTerminoValidade;
    }

    /**
     * Nome: getIdMomento Recupera o valor do atributo 'idMomento'.
     * @return valor do atributo 'idMomento'
     * @see
     */
    public String getIdMomento() {
        return this.idMomento;
    }

    /**
     * Nome: setIdMomento Registra o valor do atributo 'idMomento'.
     * @param idMomento valor do atributo id momento
     * @see
     */
    public void setIdMomento(String idMomento) {
        this.idMomento = idMomento;
    }

    /**
     * Nome: getTpMensagem Recupera o valor do atributo 'tpMensagem'.
     * @return valor do atributo 'tpMensagem'
     * @see
     */
    public String getTpMensagem() {
        return this.tpMensagem;
    }

    /**
     * Nome: setTpMensagem Registra o valor do atributo 'tpMensagem'.
     * @param tpMensagem valor do atributo tp mensagem
     * @see
     */
    public void setTpMensagem(String tpMensagem) {
        this.tpMensagem = tpMensagem;
    }

    /**
     * Nome: getTblAcionamentos Recupera o valor do atributo 'tblAcionamentos'.
     * @return valor do atributo 'tblAcionamentos'
     * @see
     */
    public List<Acionamento> getTblAcionamentos() {
        return this.tblAcionamentos;
    }

    /**
     * Nome: setTblAcionamentos Registra o valor do atributo 'tblAcionamentos'.
     * @param tblAcionamentos valor do atributo tbl acionamentos
     * @see
     */
    public void setTblAcionamentos(List<Acionamento> tblAcionamentos) {
        this.tblAcionamentos = tblAcionamentos;
    }

}