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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: The persistent class for the TblAcionamento database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblAcionamento")
public class Acionamento implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id aciona. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdAciona", unique = true, nullable = false)
    private int idAciona;

    /** Atributo acao pedida. */
    @Lob
    @Column(name = "AcaoPedida")
    private String acaoPedida;

    /** Atributo dta hora aciona. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraAciona;

    /** Atributo dta hora final. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DtaHoraFinal")
    private Date dtaHoraFinal;

    /** Atributo dta hora inicio. */
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtaHoraInicio;

    /** Atributo sucesso. */
    @Column(name = "Sucesso", length = 1)
    private String sucesso;

    /** Atributo texto livre sms. */
    @Column(name = "TextoLivreSMS")
    private String textoLivreSMS;

    // bi-directional many-to-one association to TblSM
    /** Atributo tbl sm. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdSMS")
    private SMS tblSm;

    // bi-directional many-to-one association to TblContato
    /** Atributo tbl contato. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdContato", nullable = false)
    private Contato tblContato;

    // bi-directional many-to-one association to TblOcorrencia
    /** Atributo tbl ocorrencia. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdOcorrencia", nullable = false)
    private Ocorrencia tblOcorrencia;

    /**
     * Construtor Padrao Instancia um novo objeto Acionamento.
     */
    public Acionamento() {
    }

    /**
     * Nome: getIdAciona Recupera o valor do atributo 'idAciona'.
     * @return valor do atributo 'idAciona'
     * @see
     */
    public int getIdAciona() {
        return this.idAciona;
    }

    /**
     * Nome: setIdAciona Registra o valor do atributo 'idAciona'.
     * @param idAciona valor do atributo id aciona
     * @see
     */
    public void setIdAciona(int idAciona) {
        this.idAciona = idAciona;
    }

    /**
     * Nome: getAcaoPedida Recupera o valor do atributo 'acaoPedida'.
     * @return valor do atributo 'acaoPedida'
     * @see
     */
    public String getAcaoPedida() {
        return this.acaoPedida;
    }

    /**
     * Nome: setAcaoPedida Registra o valor do atributo 'acaoPedida'.
     * @param acaoPedida valor do atributo acao pedida
     * @see
     */
    public void setAcaoPedida(String acaoPedida) {
        this.acaoPedida = acaoPedida;
    }

    /**
     * Nome: getDtaHoraAciona Recupera o valor do atributo 'dtaHoraAciona'.
     * @return valor do atributo 'dtaHoraAciona'
     * @see
     */
    public Date getDtaHoraAciona() {
        return this.dtaHoraAciona;
    }

    /**
     * Nome: setDtaHoraAciona Registra o valor do atributo 'dtaHoraAciona'.
     * @param dtaHoraAciona valor do atributo dta hora aciona
     * @see
     */
    public void setDtaHoraAciona(Date dtaHoraAciona) {
        this.dtaHoraAciona = dtaHoraAciona;
    }

    /**
     * Nome: getDtaHoraFinal Recupera o valor do atributo 'dtaHoraFinal'.
     * @return valor do atributo 'dtaHoraFinal'
     * @see
     */
    public Date getDtaHoraFinal() {
        return this.dtaHoraFinal;
    }

    /**
     * Nome: setDtaHoraFinal Registra o valor do atributo 'dtaHoraFinal'.
     * @param dtaHoraFinal valor do atributo dta hora final
     * @see
     */
    public void setDtaHoraFinal(Date dtaHoraFinal) {
        this.dtaHoraFinal = dtaHoraFinal;
    }

    /**
     * Nome: getDtaHoraInicio Recupera o valor do atributo 'dtaHoraInicio'.
     * @return valor do atributo 'dtaHoraInicio'
     * @see
     */
    public Date getDtaHoraInicio() {
        return this.dtaHoraInicio;
    }

    /**
     * Nome: setDtaHoraInicio Registra o valor do atributo 'dtaHoraInicio'.
     * @param dtaHoraInicio valor do atributo dta hora inicio
     * @see
     */
    public void setDtaHoraInicio(Date dtaHoraInicio) {
        this.dtaHoraInicio = dtaHoraInicio;
    }

    /**
     * Nome: getSucesso Recupera o valor do atributo 'sucesso'.
     * @return valor do atributo 'sucesso'
     * @see
     */
    public String getSucesso() {
        return this.sucesso;
    }

    /**
     * Nome: setSucesso Registra o valor do atributo 'sucesso'.
     * @param sucesso valor do atributo sucesso
     * @see
     */
    public void setSucesso(String sucesso) {
        this.sucesso = sucesso;
    }

    /**
     * Nome: getTextoLivreSMS Recupera o valor do atributo 'textoLivreSMS'.
     * @return valor do atributo 'textoLivreSMS'
     * @see
     */
    public String getTextoLivreSMS() {
        return this.textoLivreSMS;
    }

    /**
     * Nome: setTextoLivreSMS Registra o valor do atributo 'textoLivreSMS'.
     * @param textoLivreSMS valor do atributo texto livre sms
     * @see
     */
    public void setTextoLivreSMS(String textoLivreSMS) {
        this.textoLivreSMS = textoLivreSMS;
    }

    /**
     * Nome: getTblSm Recupera o valor do atributo 'tblSm'.
     * @return valor do atributo 'tblSm'
     * @see
     */
    public SMS getTblSm() {
        return this.tblSm;
    }

    /**
     * Nome: setTblSm Registra o valor do atributo 'tblSm'.
     * @param tblSm valor do atributo tbl sm
     * @see
     */
    public void setTblSm(SMS tblSm) {
        this.tblSm = tblSm;
    }

    /**
     * Nome: getTblContato Recupera o valor do atributo 'tblContato'.
     * @return valor do atributo 'tblContato'
     * @see
     */
    public Contato getTblContato() {
        return this.tblContato;
    }

    /**
     * Nome: setTblContato Registra o valor do atributo 'tblContato'.
     * @param tblContato valor do atributo tbl contato
     * @see
     */
    public void setTblContato(Contato tblContato) {
        this.tblContato = tblContato;
    }

    /**
     * Nome: getTblOcorrencia Recupera o valor do atributo 'tblOcorrencia'.
     * @return valor do atributo 'tblOcorrencia'
     * @see
     */
    public Ocorrencia getTblOcorrencia() {
        return this.tblOcorrencia;
    }

    /**
     * Nome: setTblOcorrencia Registra o valor do atributo 'tblOcorrencia'.
     * @param tblOcorrencia valor do atributo tbl ocorrencia
     * @see
     */
    public void setTblOcorrencia(Ocorrencia tblOcorrencia) {
        this.tblOcorrencia = tblOcorrencia;
    }

}