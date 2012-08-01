package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoVO {

    /** Atributo id dispositivo. */
    private Integer idDispositivo;

    /** Atributo descricao dispositivo. */
    private String descricaoDispositivo;

    /** Atributo tipo dispositivo. */
    private String tipoDispositivo;

    /** Atributo data fabricacao. */
    private Date dataFabricacao;

    /** Atributo data entrada. */
    private Date dataEntrada;

    /** Atributo estado. */
    private String estado;

    /** Atributo data proxima manutencao. */
    private Date dataProximaManutencao;

    /** Atributo data sucata. */
    private Date dataSucata;

    /** Atributo local. */
    private String local;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /**
     * Construtor Padrao Instancia um novo objeto DispositivoVO.
     */
    public DispositivoVO() {
        super();
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getDescricaoDispositivo Recupera o valor do atributo 'descricaoDispositivo'.
     * @return valor do atributo 'descricaoDispositivo'
     * @see
     */
    public String getDescricaoDispositivo() {
        return descricaoDispositivo;
    }

    /**
     * Nome: setDescricaoDispositivo Registra o valor do atributo 'descricaoDispositivo'.
     * @param descricaoDispositivo valor do atributo descricao dispositivo
     * @see
     */
    public void setDescricaoDispositivo(String descricaoDispositivo) {
        this.descricaoDispositivo = descricaoDispositivo;
    }

    /**
     * Nome: getTipoDispositivo
     * Recupera o valor do atributo 'tipoDispositivo'.
     *
     * @return valor do atributo 'tipoDispositivo'
     * @see
     */
    public String getTipoDispositivo() {
        return tipoDispositivo;
    }

    /**
     * Nome: setTipoDispositivo
     * Registra o valor do atributo 'tipoDispositivo'.
     *
     * @param tipoDispositivo valor do atributo tipo dispositivo
     * @see
     */
    public void setTipoDispositivo(String tipoDispositivo) {
        this.tipoDispositivo = tipoDispositivo;
    }

    /**
     * Nome: getDataFabricacao
     * Recupera o valor do atributo 'dataFabricacao'.
     *
     * @return valor do atributo 'dataFabricacao'
     * @see
     */
    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    /**
     * Nome: setDataFabricacao
     * Registra o valor do atributo 'dataFabricacao'.
     *
     * @param dataFabricacao valor do atributo data fabricacao
     * @see
     */
    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    /**
     * Nome: getDataEntrada
     * Recupera o valor do atributo 'dataEntrada'.
     *
     * @return valor do atributo 'dataEntrada'
     * @see
     */
    public Date getDataEntrada() {
        return dataEntrada;
    }

    /**
     * Nome: setDataEntrada
     * Registra o valor do atributo 'dataEntrada'.
     *
     * @param dataEntrada valor do atributo data entrada
     * @see
     */
    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * Nome: getEstado
     * Recupera o valor do atributo 'estado'.
     *
     * @return valor do atributo 'estado'
     * @see
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Nome: setEstado
     * Registra o valor do atributo 'estado'.
     *
     * @param estado valor do atributo estado
     * @see
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Nome: getDataProximaManutencao
     * Recupera o valor do atributo 'dataProximaManutencao'.
     *
     * @return valor do atributo 'dataProximaManutencao'
     * @see
     */
    public Date getDataProximaManutencao() {
        return dataProximaManutencao;
    }

    /**
     * Nome: setDataProximaManutencao
     * Registra o valor do atributo 'dataProximaManutencao'.
     *
     * @param dataProximaManutencao valor do atributo data proxima manutencao
     * @see
     */
    public void setDataProximaManutencao(Date dataProximaManutencao) {
        this.dataProximaManutencao = dataProximaManutencao;
    }

    /**
     * Nome: getDataSucata
     * Recupera o valor do atributo 'dataSucata'.
     *
     * @return valor do atributo 'dataSucata'
     * @see
     */
    public Date getDataSucata() {
        return dataSucata;
    }

    /**
     * Nome: setDataSucata
     * Registra o valor do atributo 'dataSucata'.
     *
     * @param dataSucata valor do atributo data sucata
     * @see
     */
    public void setDataSucata(Date dataSucata) {
        this.dataSucata = dataSucata;
    }

    /**
     * Nome: getLocal
     * Recupera o valor do atributo 'local'.
     *
     * @return valor do atributo 'local'
     * @see
     */
    public String getLocal() {
        return local;
    }

    /**
     * Nome: setLocal
     * Registra o valor do atributo 'local'.
     *
     * @param local valor do atributo local
     * @see
     */
    public void setLocal(String local) {
        this.local = local;
    }

    /**
     * Nome: getUsuario
     * Recupera o valor do atributo 'usuario'.
     *
     * @return valor do atributo 'usuario'
     * @see
     */
    public UsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * Nome: setUsuario
     * Registra o valor do atributo 'usuario'.
     *
     * @param usuario valor do atributo usuario
     * @see
     */
    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }
}
