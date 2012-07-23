package br.com.sw2.gac.vo;

/**
 * <b>Descri��o:</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ScriptVO {

    /** Atributo id script. */
    private Integer idScript;

    /** Atributo titulo script. */
    private String tituloScript;

    /** Atributo descricao script. */
    private String descricaoScript;

    /** Atributo processo seguir. */
    private String processoSeguir;

    /**
     * Nome: getTituloScript Recupera o valor do atributo 'tituloScript'.
     * @return valor do atributo 'tituloScript'
     * @see
     */
    public String getTituloScript() {
        return tituloScript;
    }

    /**
     * Nome: setTituloScript Registra o valor do atributo 'tituloScript'.
     * @param tituloScript valor do atributo titulo script
     * @see
     */
    public void setTituloScript(String tituloScript) {
        this.tituloScript = tituloScript;
    }

    /**
     * Nome: getIdScript Recupera o valor do atributo 'idScript'.
     * @return valor do atributo 'idScript'
     * @see
     */
    public Integer getIdScript() {
        return idScript;
    }

    /**
     * Nome: setIdScript Registra o valor do atributo 'idScript'.
     * @param idScript valor do atributo id script
     * @see
     */
    public void setIdScript(Integer idScript) {
        this.idScript = idScript;
    }

    /**
     * Nome: getDescricaoScript Recupera o valor do atributo 'descricaoScript'.
     * @return valor do atributo 'descricaoScript'
     * @see
     */
    public String getDescricaoScript() {
        return descricaoScript;
    }

    /**
     * Nome: setDescricaoScript Registra o valor do atributo 'descricaoScript'.
     * @param descricaoScript valor do atributo descricao script
     * @see
     */
    public void setDescricaoScript(String descricaoScript) {
        this.descricaoScript = descricaoScript;
    }

    /**
     * Nome: getProcessoSeguir Recupera o valor do atributo 'processoSeguir'.
     * @return valor do atributo 'processoSeguir'
     * @see
     */
    public String getProcessoSeguir() {
        return processoSeguir;
    }

    /**
     * Nome: setProcessoSeguir Registra o valor do atributo 'processoSeguir'.
     * @param processoSeguir valor do atributo processo seguir
     * @see
     */
    public void setProcessoSeguir(String processoSeguir) {
        this.processoSeguir = processoSeguir;
    }

}
