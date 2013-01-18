package br.com.sw2.gac.vo;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class AcionamentoEmailVO {

    /** Atributo to. */
    private String to;

    /** Atributo cc. */
    private String cc;

    /** Atributo assunto. */
    private String assunto;

    /** Atributo corpo. */
    private String corpo;

    /**
     * Nome: getTo Recupera o valor do atributo 'to'.
     * @return valor do atributo 'to'
     * @see
     */
    public String getTo() {
        return to;
    }

    /**
     * Nome: setTo Registra o valor do atributo 'to'.
     * @param to valor do atributo to
     * @see
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Nome: getCc Recupera o valor do atributo 'cc'.
     * @return valor do atributo 'cc'
     * @see
     */
    public String getCc() {
        return cc;
    }

    /**
     * Nome: setCc Registra o valor do atributo 'cc'.
     * @param cc valor do atributo cc
     * @see
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * Nome: getAssunto Recupera o valor do atributo 'assunto'.
     * @return valor do atributo 'assunto'
     * @see
     */
    public String getAssunto() {
        return assunto;
    }

    /**
     * Nome: setAssunto Registra o valor do atributo 'assunto'.
     * @param assunto valor do atributo assunto
     * @see
     */
    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    /**
     * Nome: getCorpo Recupera o valor do atributo 'corpo'.
     * @return valor do atributo 'corpo'
     * @see
     */
    public String getCorpo() {
        return corpo;
    }

    /**
     * Nome: setCorpo Registra o valor do atributo 'corpo'.
     * @param corpo valor do atributo corpo
     * @see
     */
    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

}
