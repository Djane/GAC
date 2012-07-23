package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContatoVO {

    /** Atributo Id contato. */
    private Integer idContato;

    /** Atributo nome. */
    private String nome;

    /** Atributo grau parentesco. */
    private String grauParentesco;

    /** Atributo endereco. */
    private String endereco;

    /** Atributo bairro. */
    private String bairro;

    /** Atributo cidade. */
    private String cidade;

    /** Atributo cep. */
    private String cep;

    /** Atributo estado. */
    private String estado;

    /** Atributo data nascimento. */
    private Date dataNascimento;

    /** Atributo sqa chamada. */
    private Integer sqaChamada;

    /** Atributo contratante. */
    private boolean contratante = false;

    /** Atributo login. */
    private String login;

    /** Atributo cpf paciente. */
    private String cpfPaciente;

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
        idContato = idContato;
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
     * Nome: getGrauParentesco Recupera o valor do atributo 'grauParentesco'.
     * @return valor do atributo 'grauParentesco'
     * @see
     */
    public String getGrauParentesco() {
        return grauParentesco;
    }

    /**
     * Nome: setGrauParentesco Registra o valor do atributo 'grauParentesco'.
     * @param grauParentesco valor do atributo grau parentesco
     * @see
     */
    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
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
     * Nome: getEstado Recupera o valor do atributo 'estado'.
     * @return valor do atributo 'estado'
     * @see
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Nome: setEstado Registra o valor do atributo 'estado'.
     * @param estado valor do atributo estado
     * @see
     */
    public void setEstado(String estado) {
        this.estado = estado;
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
     * Nome: getSqaChamada Recupera o valor do atributo 'sqaChamada'.
     * @return valor do atributo 'sqaChamada'
     * @see
     */
    public Integer getSqaChamada() {
        return sqaChamada;
    }

    /**
     * Nome: setSqaChamada Registra o valor do atributo 'sqaChamada'.
     * @param sqaChamada valor do atributo sqa chamada
     * @see
     */
    public void setSqaChamada(Integer sqaChamada) {
        this.sqaChamada = sqaChamada;
    }

    /**
     * Nome: isContratante Verifica se e contratante.
     * @return true, se for contratante senão retorna false
     * @see
     */
    public boolean isContratante() {
        return contratante;
    }

    /**
     * Nome: setContratante Registra o valor do atributo 'contratante'.
     * @param contratante valor do atributo contratante
     * @see
     */
    public void setContratante(boolean contratante) {
        this.contratante = contratante;
    }

    /**
     * Nome: getLogin Recupera o valor do atributo 'login'.
     * @return valor do atributo 'login'
     * @see
     */
    public String getLogin() {
        return login;
    }

    /**
     * Nome: setLogin Registra o valor do atributo 'login'.
     * @param login valor do atributo login
     * @see
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Nome: getCpfPaciente Recupera o valor do atributo 'cpfPaciente'.
     * @return valor do atributo 'cpfPaciente'
     * @see
     */
    public String getCpfPaciente() {
        return cpfPaciente;
    }

    /**
     * Nome: setCpfPaciente Registra o valor do atributo 'cpfPaciente'.
     * @param cpfPaciente valor do atributo cpf paciente
     * @see
     */
    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
}
