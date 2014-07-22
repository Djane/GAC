package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>Descrição: Classe que representa um dispositivo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoVO extends BaseVO implements Serializable {

    private static final long serialVersionUID = -6630857372872795756L;

    /**
     * Id do dispositivo
     */
    private String idDispositivo;

    /**
     *  Tipo de dispositivo.
     *  @see br.com.sw2.gac.tools.TipoDispositivo 
     */
    private Integer tipoDispositivo;

    /** 
     * Data de fabricação do dispositivo
     */
    private Date dataFabricacao;

    /**
     * Estado do dispositivo.
     * @see br.com.sw2.gac.tools.EstadoDispositivo
     */
    private Integer estadoAtual;

    /**
     * Usuário qe fez a última alteração no dispositivo.
     */
    private UsuarioVO usuario;

    /** Atributo data entrada. */
    private Date dataEntrada;

    /** Atributo data proxima manutencao. */
    private Date dataProximaManutencao;

    /** Atributo data sucata. */
    private Date dataSucata;

    /**
     * Indica a localização de um dispositivo.
     * @see br.com.sw2.gac.tools.LocalizacaoDispositivo
     */
    private Integer local;

    /**
     * Número sequencial que indica qual o número do dispositivo na central.
     */
    private Integer numeroSequencialDisponisitoNaCentralInteger;

    public void setIdDispositivo(String id) {
        this.idDispositivo = id;
    }

    public String getIdDispositivo() {
        return idDispositivo;
    }

    public Integer getTipoDispositivo() {
        return tipoDispositivo;
    }

    public void setTipoDispositivo(Integer tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    public Integer getEstadoAtual() {
        return estadoAtual;
    }

    public void setEstadoAtual(Integer novo) {
        this.estadoAtual = novo;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataProximaManutencao() {
        return dataProximaManutencao;
    }

    public void setDataProximaManutencao(Date dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }

    public Date getDataSucata() {
        return dataSucata;
    }

    public void setDataSucata(Date dataSucata) {
        this.dataSucata = dataSucata;
    }

    public Integer getLocal() {
        return local;
    }

    public void setLocal(Integer local) {
        this.local = local;
    }

    public Integer getNumeroSequencialDisponisitoNaCentralInteger() {
        return numeroSequencialDisponisitoNaCentralInteger;
    }

    public void setNumeroSequencialDisponisitoNaCentralInteger(
        Integer numeroSequencialDisponisitoNaCentralInteger) {
        this.numeroSequencialDisponisitoNaCentralInteger = numeroSequencialDisponisitoNaCentralInteger;
    }
    
    public String toString() {
        StringBuffer strbuff = new StringBuffer();
        strbuff.append("idDispositivo: ");
        strbuff.append(idDispositivo);
        strbuff.append(" tipoDispositivo: ");
        strbuff.append(tipoDispositivo );
        strbuff.append(" dataFabricacao:");
        strbuff.append(dataFabricacao);
        strbuff.append(" estadoAtual: ");
        strbuff.append(estadoAtual);
        strbuff.append(" usuario: ");
        if (null == usuario) {
            strbuff.append("null");
        } else {
            strbuff.append(usuario.getLogin());   
        }
        strbuff.append(" dataEntrada: ");
        strbuff.append(dataEntrada);
        strbuff.append(" dataProximaManutencao: ");
        strbuff.append(dataProximaManutencao);
        strbuff.append(" dataSucata: ");
        strbuff.append(dataSucata);
        strbuff.append(" local: ");
        strbuff.append(local);
        strbuff.append(" numeroSequencialDisponisitoNaCentralInteger: ");
        strbuff.append(numeroSequencialDisponisitoNaCentralInteger);     
        
        return strbuff.toString();
    }
}
