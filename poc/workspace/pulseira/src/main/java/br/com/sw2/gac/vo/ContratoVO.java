package br.com.sw2.gac.vo;

import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Super classe para suporte aos managedBeans</b> <br>
 * .
 * @author: lucianor
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoVO {

    /** Atributo numero contrato. */
    private String numeroContrato;

    /** Atributo dt inicio validade. */
    private Date dtInicioValidade;

    private Date dtSuspensao;

    /** Atributo dt final validade. */
    private Date dtFinalValidade;

    /** Atributo nome contratante. */
    private String nomeContratante;

    /** Atributo cpf contratante. */
    private String cpfContratante;

    private String rgContratante;

    private String enderecoContratante;

    private String bairroContratante;

    private String cidadeContratante;

    private String ufContratante;

    private String cepContratante;

    private Date dtNascimentoContratante;

    private String idServico;
    
    private String emailContratante;

    /** Atributo lista tratamentos. */
    private List<TratamentoVO> listaTratamentos;

    /** Atributo lista contatos. */
    private List<ContatoVO> listaContatos;

    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    public Date getDtSuspensao() {
        return dtSuspensao;
    }

    public void setDtSuspensao(Date dtSuspensao) {
        this.dtSuspensao = dtSuspensao;
    }

    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
    }

    public String getNomeContratante() {
        return nomeContratante;
    }

    public void setNomeContratante(String nomeContratante) {
        this.nomeContratante = nomeContratante;
    }

    public String getCpfContratante() {
        return cpfContratante;
    }

    public void setCpfContratante(String cpfContratante) {
        this.cpfContratante = cpfContratante;
    }

    public String getRgContratante() {
        return rgContratante;
    }

    public void setRgContratante(String rgContratante) {
        this.rgContratante = rgContratante;
    }

    public String getEnderecoContratante() {
        return enderecoContratante;
    }

    public void setEnderecoContratante(String enderecoContratante) {
        this.enderecoContratante = enderecoContratante;
    }

    public String getBairroContratante() {
        return bairroContratante;
    }

    public void setBairroContratante(String bairroContratante) {
        this.bairroContratante = bairroContratante;
    }

    public String getCidadeContratante() {
        return cidadeContratante;
    }

    public void setCidadeContratante(String cidadeContratante) {
        this.cidadeContratante = cidadeContratante;
    }

    public String getUfContratante() {
        return ufContratante;
    }

    public void setUfContratante(String ufContratante) {
        this.ufContratante = ufContratante;
    }

    public String getCepContratante() {
        return cepContratante;
    }

    public void setCepContratante(String cepContratante) {
        this.cepContratante = cepContratante;
    }

    public Date getDtNascimentoContratante() {
        return dtNascimentoContratante;
    }

    public void setDtNascimentoContratante(Date dtNascimentoContratante) {
        this.dtNascimentoContratante = dtNascimentoContratante;
    }

    public String getIdServico() {
        return idServico;
    }

    public void setIdServico(String idServico) {
        this.idServico = idServico;
    }

    public String getEmailContratante() {
        return emailContratante;
    }

    public void setEmailContratante(String emailContratante) {
        this.emailContratante = emailContratante;
    }

    public List<TratamentoVO> getListaTratamentos() {
        return listaTratamentos;
    }

    public void setListaTratamentos(List<TratamentoVO> listaTratamentos) {
        this.listaTratamentos = listaTratamentos;
    }

    public List<ContatoVO> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<ContatoVO> listaContatos) {
        this.listaContatos = listaContatos;
    }

}
