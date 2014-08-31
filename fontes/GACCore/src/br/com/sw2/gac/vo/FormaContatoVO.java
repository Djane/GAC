package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class FormaContatoVO extends BaseVO {

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
     * Construtor Padrao Instancia um novo objeto FormaContatoVO (Pseudo clonagem).
     * @param vo the vo
     */
    public FormaContatoVO(FormaContatoVO vo) {
        this.idFormaContato = vo.getIdFormaContato();
        this.idContato = vo.getIdContato();
        this.tipoContato = vo.getTipoContato();
        this.telefone = vo.getTelefone();
        this.email = vo.getEmail();
    }

    /**
     * Construtor Padrao Instancia um novo objeto FormaContatoVO.
     */
    public FormaContatoVO() {
        super();
    }

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
     * Nome: getTelefone Recupera o valor do atributo 'telefone'.
     * @return valor do atributo 'telefone'
     * @see
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * Nome: setTelefone Registra o valor do atributo 'telefone'.
     * @param telefone valor do atributo telefone
     * @see
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * Nome: getEmail Recupera o valor do atributo 'email'.
     * @return valor do atributo 'email'
     * @see
     */
    public String getEmail() {
        return email;
    }

    /**
     * Nome: setEmail Registra o valor do atributo 'email'.
     * @param email valor do atributo email
     * @see
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
