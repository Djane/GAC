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
    private EnderecoVO endereco = new EnderecoVO();

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
     * Nome: getCpf Recupera o valor do atributo 'cpf'.
     * @return valor do atributo 'cpf'
     * @see
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Nome: setCpf Registra o valor do atributo 'cpf'.
     * @param cpf valor do atributo cpf
     * @see
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Nome: getRg Recupera o valor do atributo 'rg'.
     * @return valor do atributo 'rg'
     * @see
     */
    public String getRg() {
        return rg;
    }

    /**
     * Nome: setRg Registra o valor do atributo 'rg'.
     * @param rg valor do atributo rg
     * @see
     */
    public void setRg(String rg) {
        this.rg = rg;
    }

    /**
     * Nome: getNome Recupera o valor do atributo 'nome'.
     * @return valor do atributo 'nome'
     * @see
     */
    public String getNome() {
        return nome;
    }

    /**
     * Nome: setNome Registra o valor do atributo 'nome'.
     * @param nome valor do atributo nome
     * @see
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Nome: getEndereco Recupera o valor do atributo 'endereco'.
     * @return valor do atributo 'endereco'
     * @see
     */
    public EnderecoVO getEndereco() {
        return endereco;
    }

    /**
     * Nome: setEndereco Registra o valor do atributo 'endereco'.
     * @param endereco valor do atributo endereco
     * @see
     */
    public void setEndereco(EnderecoVO endereco) {
        this.endereco = endereco;
    }

    /**
     * Nome: getSexo Recupera o valor do atributo 'sexo'.
     * @return valor do atributo 'sexo'
     * @see
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Nome: setSexo Registra o valor do atributo 'sexo'.
     * @param sexo valor do atributo sexo
     * @see
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Nome: getDataNascimento Recupera o valor do atributo 'dataNascimento'.
     * @return valor do atributo 'dataNascimento'
     * @see
     */
    public Date getDataNascimento() {
        return dataNascimento;
    }

    /**
     * Nome: setDataNascimento Registra o valor do atributo 'dataNascimento'.
     * @param dataNascimento valor do atributo data nascimento
     * @see
     */
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    /**
     * Nome: getNecessidadeEspecial Recupera o valor do atributo 'necessidadeEspecial'.
     * @return valor do atributo 'necessidadeEspecial'
     * @see
     */
    public String getNecessidadeEspecial() {
        return necessidadeEspecial;
    }

    /**
     * Nome: setNecessidadeEspecial Registra o valor do atributo 'necessidadeEspecial'.
     * @param necessidadeEspecial valor do atributo necessidade especial
     * @see
     */
    public void setNecessidadeEspecial(String necessidadeEspecial) {
        this.necessidadeEspecial = necessidadeEspecial;
    }

    /**
     * Nome: getPlanoSaude Recupera o valor do atributo 'planoSaude'.
     * @return valor do atributo 'planoSaude'
     * @see
     */
    public String getPlanoSaude() {
        return planoSaude;
    }

    /**
     * Nome: setPlanoSaude Registra o valor do atributo 'planoSaude'.
     * @param planoSaude valor do atributo plano saude
     * @see
     */
    public void setPlanoSaude(String planoSaude) {
        this.planoSaude = planoSaude;
    }

    /**
     * Nome: getCobertura Recupera o valor do atributo 'cobertura'.
     * @return valor do atributo 'cobertura'
     * @see
     */
    public String getCobertura() {
        return cobertura;
    }

    /**
     * Nome: setCobertura Registra o valor do atributo 'cobertura'.
     * @param cobertura valor do atributo cobertura
     * @see
     */
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
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
