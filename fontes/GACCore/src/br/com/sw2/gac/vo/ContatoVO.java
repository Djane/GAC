package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Classe que representa um contato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContatoVO extends BaseVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 6606442660576209535L;

    /** Atributo Id contato. */
    private Integer idContato;

    /** Atributo nome. */
    private String nome;

    /** Atributo grau parentesco. */
    private String grauParentesco;

    /** Atributo endereco. */
    private EnderecoVO endereco = new EnderecoVO();

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

    /** Atributo lista forma contato. */
    private List<FormaContatoVO> listaFormaContato = new ArrayList<FormaContatoVO>();

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

}
