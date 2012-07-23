package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DispositivoVO {

    /** Atributo id dispositivo. */
    private Integer idDispositivo;

    /** Atributo descricao dispositivo. */
    private String descricaoDispositivo;

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

}
