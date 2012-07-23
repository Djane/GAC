package br.com.sw2.gac.vo;

import java.util.Date;

public class ContatoVO {

    private Integer IdContato;
    private String nome;
    private String grauParentesco;
    private String endereco;
    private String bairro;
    private String cidade;
    private String cep;
    private String estado;
    private Date dataNascimento;
    private Integer sqaChamada;
    private boolean contratante = false;
    private String login;
    private String cpfPaciente;
    public Integer getIdContato() {
        return IdContato;
    }
    public void setIdContato(Integer idContato) {
        IdContato = idContato;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getGrauParentesco() {
        return grauParentesco;
    }
    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    public String getBairro() {
        return bairro;
    }
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }
    public String getCidade() {
        return cidade;
    }
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }
    public String getCep() {
        return cep;
    }
    public void setCep(String cep) {
        this.cep = cep;
    }
    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Date getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public Integer getSqaChamada() {
        return sqaChamada;
    }
    public void setSqaChamada(Integer sqaChamada) {
        this.sqaChamada = sqaChamada;
    }
    public boolean isContratante() {
        return contratante;
    }
    public void setContratante(boolean contratante) {
        this.contratante = contratante;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getCpfPaciente() {
        return cpfPaciente;
    }
    public void setCpfPaciente(String cpfPaciente) {
        this.cpfPaciente = cpfPaciente;
    }
}
