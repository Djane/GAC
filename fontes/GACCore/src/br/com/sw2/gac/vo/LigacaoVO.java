package br.com.sw2.gac.vo;

import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Classe que representa uma ligação entregue pela central da intellix</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class LigacaoVO {
    
    private String idUniqueid;    
    private String codigoAtendente;    
    private String numeroRamal;    
    private String numeroTelefoneOrigem;    
    private Date dataHoraLigacao;    
    private Date dataHorarAtendimento;    
    private String codigoEnviadoPulseira;    
    private List<ContratoVO> listaContratosCliente;    
    private Integer numeroDispositivo;    
    private Integer codigoSinalDispositivo;    
    private String codigoSentido;
    private String codigoEvento;

    public String getIdUniqueid() {
        return idUniqueid;
    }

    public void setIdUniqueid(String idUniqueid) {
        this.idUniqueid = idUniqueid;
    }

    public String getCodigoAtendente() {
        return codigoAtendente;
    }

    public void setCodigoAtendente(String codigoAtendente) {
        this.codigoAtendente = codigoAtendente;
    }

    public String getNumeroRamal() {
        return numeroRamal;
    }

    public void setNumeroRamal(String numeroRamal) {
        this.numeroRamal = numeroRamal;
    }

    public String getNumeroTelefoneOrigem() {
        return numeroTelefoneOrigem;
    }

    public void setNumeroTelefoneOrigem(String numeroTelefoneOrigem) {
        this.numeroTelefoneOrigem = numeroTelefoneOrigem;
    }

    public Date getDataHoraLigacao() {
        return dataHoraLigacao;
    }

    public void setDataHoraLigacao(Date dataHoraLigacao) {
        this.dataHoraLigacao = dataHoraLigacao;
    }

    public Date getDataHorarAtendimento() {
        return dataHorarAtendimento;
    }

    public void setDataHorarAtendimento(Date dataHorarAtendimento) {
        this.dataHorarAtendimento = dataHorarAtendimento;
    }

    public String getCodigoEnviadoPulseira() {
        return codigoEnviadoPulseira;
    }

    public void setCodigoEnviadoPulseira(String codigoEnviadoPulseira) {
        this.codigoEnviadoPulseira = codigoEnviadoPulseira;

        if (null != this.codigoEnviadoPulseira && this.codigoEnviadoPulseira.length() > 3) {
            try {
                this.numeroDispositivo = Integer.parseInt(codigoEnviadoPulseira.substring(codigoEnviadoPulseira.length() - 3, codigoEnviadoPulseira.length() - 2));
                this.codigoSinalDispositivo = Integer.parseInt(codigoEnviadoPulseira.substring(codigoEnviadoPulseira.length() - 2, codigoEnviadoPulseira.length() - 1));
            } catch (Exception e) {
                this.numeroDispositivo = null;
                this.codigoSinalDispositivo = null;
            }
            this.codigoSentido = codigoEnviadoPulseira.substring(0, 1);
        }
    }

    public List<ContratoVO> getListaContratosCliente() {
        return listaContratosCliente;
    }

    public void setListaContratosCliente(List<ContratoVO> listaContratosCliente) {
        this.listaContratosCliente = listaContratosCliente;
    }

    public Integer getNumeroDispositivo() {
        return numeroDispositivo;
    }

    public void setNumeroDispositivo(Integer numeroDispositivo) {
        this.numeroDispositivo = numeroDispositivo;
    }
    
    public Integer getCodigoSinalDispositivo() {
        return codigoSinalDispositivo;
    }

    public void setCodigoSinalDispositivo(Integer codigoSinalDispositivo) {
        this.codigoSinalDispositivo = codigoSinalDispositivo;
    }

    public String getCodigoSentido() {
        return codigoSentido;
    }

    public void setCodigoSentido(String codigoSentido) {
        this.codigoSentido = codigoSentido;
    }
    
    /**
     * Código do evento gerado pela Central
     * @return
     */
    public String getCodigoEvento() {
                
        if (null != this.numeroDispositivo) {
            codigoEvento = this.numeroDispositivo.toString();
        }
        
        if (null != this.codigoSinalDispositivo) {
            codigoEvento = codigoEvento + this.codigoSinalDispositivo.toString();
        }
        
        return codigoEvento;        
    }
    
}
