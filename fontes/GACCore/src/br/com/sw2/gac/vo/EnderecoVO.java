package br.com.sw2.gac.vo;

/**
 * <b>Descrição: Classe que representa um endereço no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class EnderecoVO {
    /** Atributo endereco. */
    private String endereco;

    /** Atributo bairro. */
    private String bairro;

    /** Atributo cidade. */
    private String cidade;

    /** Atributo uf. */
    private String uf = "SP";

    /** Atributo cep. */
    private String cep;

    /**
     * Construtor Padrao
     * Instancia um novo objeto EnderecoVO (Pseudo clonagem).
     *
     * @param vo the vo
     */
    public EnderecoVO(EnderecoVO vo) {
        this.endereco = vo.getEndereco();
        this.bairro = vo.getBairro();
        this.cidade = vo.getCidade();
        this.uf = vo.getUf();
        this.cep = vo.getCep();
    }

    /**
     * Construtor Padrao
     * Instancia um novo objeto EnderecoVO.
     */
    public EnderecoVO() {
        super();
    }

    /**
     * Nome: getEndereco Recupera o valor do atributo 'endereco'.
     * @return valor do atributo 'endereco'
     * @see
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * Nome: setEndereco Registra o valor do atributo 'endereco'.
     * @param endereco valor do atributo endereco
     * @see
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * Nome: getBairro Recupera o valor do atributo 'bairro'.
     * @return valor do atributo 'bairro'
     * @see
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * Nome: setBairro Registra o valor do atributo 'bairro'.
     * @param bairro valor do atributo bairro
     * @see
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * Nome: getCidade Recupera o valor do atributo 'cidade'.
     * @return valor do atributo 'cidade'
     * @see
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * Nome: setCidade Registra o valor do atributo 'cidade'.
     * @param cidade valor do atributo cidade
     * @see
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * Nome: getCep Recupera o valor do atributo 'cep'.
     * @return valor do atributo 'cep'
     * @see
     */
    public String getCep() {
        return cep;
    }

    /**
     * Nome: setCep Registra o valor do atributo 'cep'.
     * @param cep valor do atributo cep
     * @see
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * Nome: getUf Recupera o valor do atributo 'uf'.
     * @return valor do atributo 'uf'
     * @see
     */
    public String getUf() {
        return uf;
    }

    /**
     * Nome: setUf Registra o valor do atributo 'uf'.
     * @param uf valor do atributo uf
     * @see
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

}
