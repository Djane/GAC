package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class FormaContatoVO {

    /** Atributo id forma contato. */
    private Integer idFormaContato;

    /** Atributo id contato. */
    private Integer idContato;

    /** Atributo tipo contato. */
    private String tipoContato;

    /** Atributo fone contato. */
    private String telefone;

    /** Atributo email contato. */
    private String email;

    /**
     * Nome: getIdFormaContato Recupera o valor do atributo 'idFormaContato'.
     * @return valor do atributo 'idFormaContato'
     * @see
     */
    public Integer getIdFormaContato() {
        return idFormaContato;
    }

    /**
     * Nome: setIdFormaContato Registra o valor do atributo 'idFormaContato'.
     * @param idFormaContato valor do atributo id forma contato
     * @see
     */
    public void setIdFormaContato(Integer idFormaContato) {
        this.idFormaContato = idFormaContato;
    }

    /**
     * Nome: getIdContato Recupera o valor do atributo 'idContato'.
     * @return valor do atributo 'idContato'
     * @see
     */
    public Integer getIdContato() {
        return idContato;
    }

    /**
     * Nome: setIdContato Registra o valor do atributo 'idContato'.
     * @param idContato valor do atributo id contato
     * @see
     */
    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    /**
     * Nome: getTipoContato Recupera o valor do atributo 'tipoContato'.
     * @return valor do atributo 'tipoContato'
     * @see
     */
    public String getTipoContato() {
        return tipoContato;
    }

    /**
     * Nome: setTipoContato Registra o valor do atributo 'tipoContato'.
     * @param tipoContato valor do atributo tipo contato
     * @see
     */
    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    /**
     * Nome: getFoneContato Recupera o valor do atributo 'foneContato'.
     * @return valor do atributo 'foneContato'
     * @see
     */
    public String getFoneContato() {
        return telefone;
    }

    /**
     * Nome: setFoneContato Registra o valor do atributo 'foneContato'.
     * @param foneContato valor do atributo fone contato
     * @see
     */
    public void setFoneContato(String foneContato) {
        this.telefone = foneContato;
    }

    /**
     * Nome: getEmailContato Recupera o valor do atributo 'emailContato'.
     * @return valor do atributo 'emailContato'
     * @see
     */
    public String getEmailContato() {
        return email;
    }

    /**
     * Nome: setEmailContato Registra o valor do atributo 'emailContato'.
     * @param emailContato valor do atributo email contato
     * @see
     */
    public void setEmailContato(String emailContato) {
        this.email = emailContato;
    }

}
