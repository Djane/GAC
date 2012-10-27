package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>Descrição: Classe que representa um dispositivo</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoVO extends BaseVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -6630857372872795756L;

    /** Atributo id dispositivo. */
    private String idDispositivo;

    /** Atributo tipo dispositivo. */
    private Integer tipoDispositivo;

    /** Atributo data fabricacao. */
    private Date dataFabricacao;

    /** Atributo estado atual. */
    private Integer estadoAtual;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /** Atributo data entrada. */
    private Date dataEntrada;

    /** Atributo data proxima manutencao. */
    private Date dataProximaManutencao;

    /** Atributo data sucata. */
    private Date dataSucata;

    /** Atributo local. */
    private Integer local;

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param id valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String id) {
        this.idDispositivo = id;
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: getTipoDispositivo Recupera o valor do atributo 'tipoDispositivo'.
     * @return valor do atributo 'tipoDispositivo'
     * @see
     */
    public Integer getTipoDispositivo() {
        return tipoDispositivo;
    }

    /**
     * Nome: setTipoDispositivo Registra o valor do atributo 'tipoDispositivo'.
     * @param tipoDispositivo valor do atributo tipo dispositivo
     * @see
     */
    public void setTipoDispositivo(Integer tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    /**
     * Nome: getDataFabricacao Recupera o valor do atributo 'dataFabricacao'.
     * @return valor do atributo 'dataFabricacao'
     * @see
     */
    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    /**
     * Nome: setDataFabricacao Registra o valor do atributo 'dataFabricacao'.
     * @param dataFabricacao valor do atributo data fabricacao
     * @see
     */
    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    /**
     * Nome: getEstadoAtual Recupera o valor do atributo 'estadoAtual'.
     * @return valor do atributo 'estadoAtual'
     * @see
     */
    public Integer getEstadoAtual() {
        return estadoAtual;
    }

    /**
     * Nome: setEstadoAtual Registra o valor do atributo 'estadoAtual'.
     * @param novo valor do atributo estado atual
     * @see
     */
    public void setEstadoAtual(Integer novo) {
        this.estadoAtual = novo;
    }

    /**
     * Nome: getUsuario Recupera o valor do atributo 'usuario'.
     * @return valor do atributo 'usuario'
     * @see
     */
    public UsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * Nome: setUsuario Registra o valor do atributo 'usuario'.
     * @param usuario valor do atributo usuario
     * @see
     */
    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    /**
     * Nome: getDataEntrada Recupera o valor do atributo 'dataEntrada'.
     * @return valor do atributo 'dataEntrada'
     * @see
     */
    public Date getDataEntrada() {
        return dataEntrada;
    }

    /**
     * Nome: setDataEntrada Registra o valor do atributo 'dataEntrada'.
     * @param dataEntrada valor do atributo data entrada
     * @see
     */
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * Nome: getDataProximaManutencao Recupera o valor do atributo 'dataProximaManutencao'.
     * @return valor do atributo 'dataProximaManutencao'
     * @see
     */
    public Date getDataProximaManutencao() {
        return dataProximaManutencao;
    }

    /**
     * Nome: setDataProximaManutencao Registra o valor do atributo 'dataProximaManutencao'.
     * @param dataProximaManutencao valor do atributo data proxima manutencao
     * @see
     */
    public void setDataProximaManutencao(Date dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }

    /**
     * Nome: getDataSucata Recupera o valor do atributo 'dataSucata'.
     * @return valor do atributo 'dataSucata'
     * @see
     */
    public Date getDataSucata() {
        return dataSucata;
    }

    /**
     * Nome: setDataSucata Registra o valor do atributo 'dataSucata'.
     * @param dataSucata valor do atributo data sucata
     * @see
     */
    public void setDataSucata(Date dataSucata) {
        this.dataSucata = dataSucata;
    }

    /**
     * Nome: getLocal Recupera o valor do atributo 'local'.
     * @return valor do atributo 'local'
     * @see
     */
    public Integer getLocal() {
        return local;
    }

    /**
     * Nome: setLocal Registra o valor do atributo 'local'.
     * @param local valor do atributo local
     * @see
     */
    public void setLocal(Integer local) {
        this.local = local;
    }

}
