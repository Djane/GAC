package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PacoteVO {

    /** Atributo id pacote. */
    private Integer idPacote;

    /** Atributo titulo. */
    private String titulo;

    /** Atributo descricao. */
    private String descricao;

    /**
     * Nome: getIdPacote Recupera o valor do atributo 'idPacote'.
     * @return valor do atributo 'idPacote'
     * @see
     */
    public Integer getIdPacote() {
        return idPacote;
    }

    /**
     * Nome: setIdPacote Registra o valor do atributo 'idPacote'.
     * @param idPacote valor do atributo id pacote
     * @see
     */
    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    /**
     * Nome: getTitulo Recupera o valor do atributo 'titulo'.
     * @return valor do atributo 'titulo'
     * @see
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Nome: setTitulo Registra o valor do atributo 'titulo'.
     * @param titulo valor do atributo titulo
     * @see
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Nome: getDescricao Recupera o valor do atributo 'descricao'.
     * @return valor do atributo 'descricao'
     * @see
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Nome: setDescricao Registra o valor do atributo 'descricao'.
     * @param descricao valor do atributo descricao
     * @see
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
