package br.com.sw2.gac.vo;

/**
 * <b>Descrição: Classe que representa o contratante de um contrato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratanteVO {

    /** Atributo cpf contratante. */
    private String cpfContratante;

    /** Atributo rg contratante. */
    private String rgContratante;

    /** Atributo nome contratante. */
    private String nomeContratante;

    /** Atributo contato. */
    private ContatoVO contato;

    /**
     * Nome: getCpfContratante Recupera o valor do atributo 'cpfContratante'.
     * @return valor do atributo 'cpfContratante'
     * @see
     */
    public String getCpfContratante() {
        return cpfContratante;
    }

    /**
     * Nome: setCpfContratante Registra o valor do atributo 'cpfContratante'.
     * @param cpfContratante valor do atributo cpf contratante
     * @see
     */
    public void setCpfContratante(String cpfContratante) {
        this.cpfContratante = cpfContratante;
    }

    /**
     * Nome: getRgContratante Recupera o valor do atributo 'rgContratante'.
     * @return valor do atributo 'rgContratante'
     * @see
     */
    public String getRgContratante() {
        return rgContratante;
    }

    /**
     * Nome: setRgContratante Registra o valor do atributo 'rgContratante'.
     * @param rgContratante valor do atributo rg contratante
     * @see
     */
    public void setRgContratante(String rgContratante) {
        this.rgContratante = rgContratante;
    }

    /**
     * Nome: getNomeContratante Recupera o valor do atributo 'nomeContratante'.
     * @return valor do atributo 'nomeContratante'
     * @see
     */
    public String getNomeContratante() {
        return nomeContratante;
    }

    /**
     * Nome: setNomeContratante Registra o valor do atributo 'nomeContratante'.
     * @param nomeContratante valor do atributo nome contratante
     * @see
     */
    public void setNomeContratante(String nomeContratante) {
        this.nomeContratante = nomeContratante;
    }

    /**
     * Nome: getContato Recupera o valor do atributo 'contato'.
     * @return valor do atributo 'contato'
     * @see
     */
    public ContatoVO getContato() {
        return contato;
    }

    /**
     * Nome: setContato Registra o valor do atributo 'contato'.
     * @param contato valor do atributo contato
     * @see
     */
    public void setContato(ContatoVO contato) {
        this.contato = contato;
    }

}
