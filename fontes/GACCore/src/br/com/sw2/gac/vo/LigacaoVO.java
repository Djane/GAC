package br.com.sw2.gac.vo;

import java.util.Date;
import java.util.List;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class LigacaoVO {

    /** Atributo id uniqueid. */
    private String idUniqueid;

    /** Atributo codigo atendente. */
    private String codigoAtendente;

    /** Atributo numero ramal. */
    private String numeroRamal;

    /** Atributo numero telefone origem. */
    private String numeroTelefoneOrigem;

    /** Atributo data hora ligacao. */
    private Date dataHoraLigacao;

    /** Atributo data horar atendimento. */
    private Date dataHorarAtendimento;

    /** Atributo codigo enviado pulseira. */
    private String codigoEnviadoPulseira;

    /** Atributo lista contratos cliente. */
    private List<ContratoVO> listaContratosCliente;

    /** Atributo numero dispositivo. */
    private Integer numeroDispositivo;

    /** Atributo codigo sinal dispositivo. */
    private Integer codigoSinalDispositivo;

    /** Atributo codigo sentido. */
    private String codigoSentido;

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
     * Nome: getCodigoAtendente Recupera o valor do atributo 'codigoAtendente'.
     * @return valor do atributo 'codigoAtendente'
     * @see
     */
    public String getCodigoAtendente() {
        return codigoAtendente;
    }

    /**
     * Nome: setCodigoAtendente Registra o valor do atributo 'codigoAtendente'.
     * @param codigoAtendente valor do atributo codigo atendente
     * @see
     */
    public void setCodigoAtendente(String codigoAtendente) {
        this.codigoAtendente = codigoAtendente;
    }

    /**
     * Nome: getNumeroRamal Recupera o valor do atributo 'numeroRamal'.
     * @return valor do atributo 'numeroRamal'
     * @see
     */
    public String getNumeroRamal() {
        return numeroRamal;
    }

    /**
     * Nome: setNumeroRamal Registra o valor do atributo 'numeroRamal'.
     * @param numeroRamal valor do atributo numero ramal
     * @see
     */
    public void setNumeroRamal(String numeroRamal) {
        this.numeroRamal = numeroRamal;
    }

    /**
     * Nome: getNumeroTelefoneOrigem Recupera o valor do atributo 'numeroTelefoneOrigem'.
     * @return valor do atributo 'numeroTelefoneOrigem'
     * @see
     */
    public String getNumeroTelefoneOrigem() {
        return numeroTelefoneOrigem;
    }

    /**
     * Nome: setNumeroTelefoneOrigem Registra o valor do atributo 'numeroTelefoneOrigem'.
     * @param numeroTelefoneOrigem valor do atributo numero telefone origem
     * @see
     */
    public void setNumeroTelefoneOrigem(String numeroTelefoneOrigem) {
        this.numeroTelefoneOrigem = numeroTelefoneOrigem;
    }

    /**
     * Nome: getDataHoraLigacao Recupera o valor do atributo 'dataHoraLigacao'.
     * @return valor do atributo 'dataHoraLigacao'
     * @see
     */
    public Date getDataHoraLigacao() {
        return dataHoraLigacao;
    }

    /**
     * Nome: setDataHoraLigacao Registra o valor do atributo 'dataHoraLigacao'.
     * @param dataHoraLigacao valor do atributo data hora ligacao
     * @see
     */
    public void setDataHoraLigacao(Date dataHoraLigacao) {
        this.dataHoraLigacao = dataHoraLigacao;
    }

    /**
     * Nome: getDataHorarAtendimento Recupera o valor do atributo 'dataHorarAtendimento'.
     * @return valor do atributo 'dataHorarAtendimento'
     * @see
     */
    public Date getDataHorarAtendimento() {
        return dataHorarAtendimento;
    }

    /**
     * Nome: setDataHorarAtendimento Registra o valor do atributo 'dataHorarAtendimento'.
     * @param dataHorarAtendimento valor do atributo data horar atendimento
     * @see
     */
    public void setDataHorarAtendimento(Date dataHorarAtendimento) {
        this.dataHorarAtendimento = dataHorarAtendimento;
    }

    /**
     * Nome: getCodigoEnviadoPulseira
     * Recupera o valor do atributo 'codigoEnviadoPulseira'.
     *
     * @return valor do atributo 'codigoEnviadoPulseira'
     * @see
     */
    public String getCodigoEnviadoPulseira() {
        return codigoEnviadoPulseira;
    }

    /**
     * Nome: setCodigoEnviadoPulseira
     * Registra o valor do atributo 'codigoEnviadoPulseira'.
     *
     * @param codigoEnviadoPulseira valor do atributo codigo enviado pulseira
     * @see
     */
    public void setCodigoEnviadoPulseira(String codigoEnviadoPulseira) {
        this.codigoEnviadoPulseira = codigoEnviadoPulseira;

        if (null != this.codigoEnviadoPulseira && this.codigoEnviadoPulseira.length() > 3) {
            try {
                this.numeroDispositivo = Integer.parseInt(codigoEnviadoPulseira.substring(
                    codigoEnviadoPulseira.length() - 3, codigoEnviadoPulseira.length() - 2));
                this.codigoSinalDispositivo = Integer.parseInt(codigoEnviadoPulseira.substring(
                    codigoEnviadoPulseira.length() - 2, codigoEnviadoPulseira.length() - 1));
            } catch (Exception e) {
                this.numeroDispositivo = null;
                this.codigoSinalDispositivo = null;
            }
            this.codigoSentido = codigoEnviadoPulseira.substring(0, 1);
        }
    }

    /**
     * Nome: getListaContratosCliente
     * Recupera o valor do atributo 'listaContratosCliente'.
     *
     * @return valor do atributo 'listaContratosCliente'
     * @see
     */
    public List<ContratoVO> getListaContratosCliente() {
        return listaContratosCliente;
    }

    /**
     * Nome: setListaContratosCliente
     * Registra o valor do atributo 'listaContratosCliente'.
     *
     * @param listaContratosCliente valor do atributo lista contratos cliente
     * @see
     */
    public void setListaContratosCliente(List<ContratoVO> listaContratosCliente) {
        this.listaContratosCliente = listaContratosCliente;
    }

    /**
     * Nome: getNumeroDispositivo
     * Recupera o valor do atributo 'numeroDispositivo'.
     *
     * @return valor do atributo 'numeroDispositivo'
     * @see
     */
    public Integer getNumeroDispositivo() {
        return numeroDispositivo;
    }

    /**
     * Nome: setNumeroDispositivo
     * Registra o valor do atributo 'numeroDispositivo'.
     *
     * @param numeroDispositivo valor do atributo numero dispositivo
     * @see
     */
    public void setNumeroDispositivo(Integer numeroDispositivo) {
        this.numeroDispositivo = numeroDispositivo;
    }

    /**
     * Nome: getCodigoSinalDispositivo
     * Recupera o valor do atributo 'codigoSinalDispositivo'.
     *
     * @return valor do atributo 'codigoSinalDispositivo'
     * @see
     */
    public Integer getCodigoSinalDispositivo() {
        return codigoSinalDispositivo;
    }

    /**
     * Nome: setCodigoSinalDispositivo
     * Registra o valor do atributo 'codigoSinalDispositivo'.
     *
     * @param codigoSinalDispositivo valor do atributo codigo sinal dispositivo
     * @see
     */
    public void setCodigoSinalDispositivo(Integer codigoSinalDispositivo) {
        this.codigoSinalDispositivo = codigoSinalDispositivo;
    }

    /**
     * Nome: getCodigoSentido
     * Recupera o valor do atributo 'codigoSentido'.
     *
     * @return valor do atributo 'codigoSentido'
     * @see
     */
    public String getCodigoSentido() {
        return codigoSentido;
    }

    /**
     * Nome: setCodigoSentido
     * Registra o valor do atributo 'codigoSentido'.
     *
     * @param codigoSentido valor do atributo codigo sentido
     * @see
     */
    public void setCodigoSentido(String codigoSentido) {
        this.codigoSentido = codigoSentido;
    }
}
