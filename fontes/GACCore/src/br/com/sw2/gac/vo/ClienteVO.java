package br.com.sw2.gac.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Objeto que representa um cliente no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ClienteVO {

    /** Atributo cpf. */
    private String cpf;

    /** Atributo rg. */
    private String rg;

    /** Atributo nome. */
    private String nome;

    /** Atributo endereco. */
    private String endereco;

    /** Atributo bairro. */
    private String bairro;

    /** Atributo cidade. */
    private String cidade;

    /** Atributo uf. */
    private final String uf = "SP";

    /** Atributo cep. */
    private String cep;

    /** Atributo sexo. */
    private String sexo;

    /** Atributo data nascimento. */
    private Date dataNascimento;

    /** Atributo necessidade especial. */
    private String necessidadeEspecial;

    /** Atributo plano saude. */
    private String planoSaude;

    /** Atributo cobertura. */
    private String cobertura;

    /** Atributo lista forma contato. */
    private List<FormaContatoVO> listaFormaContato = new ArrayList<FormaContatoVO>();

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

    /**
     * @param rg the rg to set
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * @return the dataNascimento
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @param dataNascimento the dataNascimento to set
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * @return the necessidadeEspecial
     */
    public String getNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    /**
     * @param necessidadeEspecial the necessidadeEspecial to set
     */
    public void setNecessidadeEspecial(String necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    /**
     * @return the planoSaude
     */
    public String getPlanoSaude() {
        return planoSaude;
    }

    /**
     * @param planoSaude the planoSaude to set
     */
    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    /**
     * @return the cobertura
     */
    public String getCobertura() {
        return cobertura;
    }

    /**
     * @param cobertura the cobertura to set
     */
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * Nome: getListaFormaContato Recupera o valor do atributo 'listaFormaContato'.
     * @return valor do atributo 'listaFormaContato'
     * @see
     */
    public List<FormaContatoVO> getListaFormaContato() {
        return listaFormaContato;
    }

    /**
     * Nome: setListaFormaContato Registra o valor do atributo 'listaFormaContato'.
     * @param listaFormaContato valor do atributo lista forma contato
     * @see
     */
    public void setListaFormaContato(List<FormaContatoVO> listaFormaContato) {
        this.listaFormaContato = listaFormaContato;
    }

}
