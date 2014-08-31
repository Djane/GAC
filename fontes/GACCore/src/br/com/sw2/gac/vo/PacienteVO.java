package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PacienteVO {

    /** Atributo cpf. */
    private String cpf;

    /** Atributo numero contrato. */
    private String numeroContrato;

    /** Atributo nome paciente. */
    private String nomePaciente;

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

    /** Atributo rg. */
    private String rg;

    /** Atributo sexo. */
    private String sexo;

    /** Atributo telefone residencial. */
    private String telefoneResidencial;

    /** Atributo telefone celular. */
    private String telefoneCelular;

    /** Atributo data nascimento. */
    private Date dataNascimento;

    /** Atributo necessidade especial. */
    private String necessidadeEspecial;

    /** Atributo plano saude. */
    private String planoSaude;

    /** Atributo cobertura. */
    private String cobertura;

    /** Atributo email. */
    private String email;

    /** Atributo login. */
    private String login;

    /** Atributo id central. */
    private String idCentral;

    /** Atributo id dispositivo. */
    private String idDispositivo;

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
     * Nome: getNumeroContrato Recupera o valor do atributo 'numeroContrato'.
     * @return valor do atributo 'numeroContrato'
     * @see
     */
    public String getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Nome: setNumeroContrato Registra o valor do atributo 'numeroContrato'.
     * @param numeroContrato valor do atributo numero contrato
     * @see
     */
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    /**
     * Nome: getNomePaciente Recupera o valor do atributo 'nomePaciente'.
     * @return valor do atributo 'nomePaciente'
     * @see
     */
    public String getNomePaciente() {
        return nomePaciente;
    }

    /**
     * Nome: setNomePaciente Registra o valor do atributo 'nomePaciente'.
     * @param nomePaciente valor do atributo nome paciente
     * @see
     */
    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
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
     * Nome: getTelefoneResidencial Recupera o valor do atributo 'telefoneResidencial'.
     * @return valor do atributo 'telefoneResidencial'
     * @see
     */
    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    /**
     * Nome: setTelefoneResidencial Registra o valor do atributo 'telefoneResidencial'.
     * @param telefoneResidencial valor do atributo telefone residencial
     * @see
     */
    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    /**
     * Nome: getTelefoneCelular Recupera o valor do atributo 'telefoneCelular'.
     * @return valor do atributo 'telefoneCelular'
     * @see
     */
    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    /**
     * Nome: setTelefoneCelular Registra o valor do atributo 'telefoneCelular'.
     * @param telefoneCelular valor do atributo telefone celular
     * @see
     */
    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
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
     * Nome: getIdCentral Recupera o valor do atributo 'idCentral'.
     * @return valor do atributo 'idCentral'
     * @see
     */
    public String getIdCentral() {
        return idCentral;
    }

    /**
     * Nome: setIdCentral Registra o valor do atributo 'idCentral'.
     * @param idCentral valor do atributo id central
     * @see
     */
    public void setIdCentral(String idCentral) {
        this.idCentral = idCentral;
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

}
