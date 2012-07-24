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

    public Integer getIdFormaContato() {
        return idFormaContato;
    }

    public void setIdFormaContato(Integer idFormaContato) {
        this.idFormaContato = idFormaContato;
    }

    public Integer getIdContato() {
        return idContato;
    }

    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

    public String getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(String tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
