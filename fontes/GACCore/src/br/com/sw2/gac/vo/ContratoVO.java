package br.com.sw2.gac.vo;

import java.util.Date;
import java.util.List;

/**
 * <b>Descriçã: Classe que representa um contrato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ContratoVO {

    /** Atributo numero contrato. */
    private Integer numeroContrato;

    /** Atributo dt inicio validade. */
    private Date dtInicioValidade;

    /** Atributo dt suspensao. */
    private Date dtSuspensao;

    /** Atributo dt final validade. */
    private Date dtFinalValidade;

    /** Atributo nome contratante. */
    private String nomeContratante;

    /** Atributo cpf contratante. */
    private String cpfContratante;

    /** Atributo rg contratante. */
    private String rgContratante;

    /** Atributo endereco contratante. */
    private String enderecoContratante;

    /** Atributo bairro contratante. */
    private String bairroContratante;

    /** Atributo cidade contratante. */
    private String cidadeContratante;

    /** Atributo uf contratante. */
    private String ufContratante;

    /** Atributo cep contratante. */
    private String cepContratante;

    /** Atributo dt nascimento contratante. */
    private Date dtNascimentoContratante;

    /** Atributo id servico. */
    private Integer idServico;

    /** Atributo email contratante. */
    private String emailContratante;

    /** Atributo lista tratamentos. */
    private List<TratamentoVO> listaTratamentos;

    /** Atributo lista contatos. */
    private List<ContatoVO> listaContatos;

    /** Atributo lista doencas. */
    private List<DoencaVO> listaDoencas;

    /** Atributo cliente. */
    private ClienteVO cliente = new ClienteVO();

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /** Atributo dtProxAtual. */
    private Date dtProxAtual;

    /**
     * Nome: getNumeroContrato Recupera o valor do atributo 'numeroContrato'.
     * @return valor do atributo 'numeroContrato'
     * @see
     */
    public Integer getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * Nome: setNumeroContrato Registra o valor do atributo 'numeroContrato'.
     * @param numeroContrato valor do atributo numero contrato
     * @see
     */
    public void setNumeroContrato(Integer numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    /**
     * Nome: getDtInicioValidade Recupera o valor do atributo 'dtInicioValidade'.
     * @return valor do atributo 'dtInicioValidade'
     * @see
     */
    public Date getDtInicioValidade() {
        return dtInicioValidade;
    }

    /**
     * Nome: setDtInicioValidade Registra o valor do atributo 'dtInicioValidade'.
     * @param dtInicioValidade valor do atributo dt inicio validade
     * @see
     */
    public void setDtInicioValidade(Date dtInicioValidade) {
        this.dtInicioValidade = dtInicioValidade;
    }

    /**
     * Nome: getDtSuspensao Recupera o valor do atributo 'dtSuspensao'.
     * @return valor do atributo 'dtSuspensao'
     * @see
     */
    public Date getDtSuspensao() {
        return dtSuspensao;
    }

    /**
     * Nome: setDtSuspensao Registra o valor do atributo 'dtSuspensao'.
     * @param dtSuspensao valor do atributo dt suspensao
     * @see
     */
    public void setDtSuspensao(Date dtSuspensao) {
        this.dtSuspensao = dtSuspensao;
    }

    /**
     * Nome: getDtFinalValidade Recupera o valor do atributo 'dtFinalValidade'.
     * @return valor do atributo 'dtFinalValidade'
     * @see
     */
    public Date getDtFinalValidade() {
        return dtFinalValidade;
    }

    /**
     * Nome: setDtFinalValidade Registra o valor do atributo 'dtFinalValidade'.
     * @param dtFinalValidade valor do atributo dt final validade
     * @see
     */
    public void setDtFinalValidade(Date dtFinalValidade) {
        this.dtFinalValidade = dtFinalValidade;
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
     * Nome: getEnderecoContratante Recupera o valor do atributo 'enderecoContratante'.
     * @return valor do atributo 'enderecoContratante'
     * @see
     */
    public String getEnderecoContratante() {
        return enderecoContratante;
    }

    /**
     * Nome: setEnderecoContratante Registra o valor do atributo 'enderecoContratante'.
     * @param enderecoContratante valor do atributo endereco contratante
     * @see
     */
    public void setEnderecoContratante(String enderecoContratante) {
        this.enderecoContratante = enderecoContratante;
    }

    /**
     * Nome: getBairroContratante Recupera o valor do atributo 'bairroContratante'.
     * @return valor do atributo 'bairroContratante'
     * @see
     */
    public String getBairroContratante() {
        return bairroContratante;
    }

    /**
     * Nome: setBairroContratante Registra o valor do atributo 'bairroContratante'.
     * @param bairroContratante valor do atributo bairro contratante
     * @see
     */
    public void setBairroContratante(String bairroContratante) {
        this.bairroContratante = bairroContratante;
    }

    /**
     * Nome: getCidadeContratante Recupera o valor do atributo 'cidadeContratante'.
     * @return valor do atributo 'cidadeContratante'
     * @see
     */
    public String getCidadeContratante() {
        return cidadeContratante;
    }

    /**
     * Nome: setCidadeContratante Registra o valor do atributo 'cidadeContratante'.
     * @param cidadeContratante valor do atributo cidade contratante
     * @see
     */
    public void setCidadeContratante(String cidadeContratante) {
        this.cidadeContratante = cidadeContratante;
    }

    /**
     * Nome: getUfContratante Recupera o valor do atributo 'ufContratante'.
     * @return valor do atributo 'ufContratante'
     * @see
     */
    public String getUfContratante() {
        return ufContratante;
    }

    /**
     * Nome: setUfContratante Registra o valor do atributo 'ufContratante'.
     * @param ufContratante valor do atributo uf contratante
     * @see
     */
    public void setUfContratante(String ufContratante) {
        this.ufContratante = ufContratante;
    }

    /**
     * Nome: getCepContratante Recupera o valor do atributo 'cepContratante'.
     * @return valor do atributo 'cepContratante'
     * @see
     */
    public String getCepContratante() {
        return cepContratante;
    }

    /**
     * Nome: setCepContratante Registra o valor do atributo 'cepContratante'.
     * @param cepContratante valor do atributo cep contratante
     * @see
     */
    public void setCepContratante(String cepContratante) {
        this.cepContratante = cepContratante;
    }

    /**
     * Nome: getDtNascimentoContratante Recupera o valor do atributo 'dtNascimentoContratante'.
     * @return valor do atributo 'dtNascimentoContratante'
     * @see
     */
    public Date getDtNascimentoContratante() {
        return dtNascimentoContratante;
    }

    /**
     * Nome: setDtNascimentoContratante Registra o valor do atributo 'dtNascimentoContratante'.
     * @param dtNascimentoContratante valor do atributo dt nascimento contratante
     * @see
     */
    public void setDtNascimentoContratante(Date dtNascimentoContratante) {
        this.dtNascimentoContratante = dtNascimentoContratante;
    }

    /**
     * Nome: getIdServico Recupera o valor do atributo 'idServico'.
     * @return valor do atributo 'idServico'
     * @see
     */
    public Integer getIdServico() {
        return idServico;
    }

    /**
     * Nome: setIdServico Registra o valor do atributo 'idServico'.
     * @param idServico valor do atributo id servico
     * @see
     */
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    /**
     * Nome: getEmailContratante Recupera o valor do atributo 'emailContratante'.
     * @return valor do atributo 'emailContratante'
     * @see
     */
    public String getEmailContratante() {
        return emailContratante;
    }

    /**
     * Nome: setEmailContratante Registra o valor do atributo 'emailContratante'.
     * @param emailContratante valor do atributo email contratante
     * @see
     */
    public void setEmailContratante(String emailContratante) {
        this.emailContratante = emailContratante;
    }

    /**
     * Nome: getListaTratamentos Recupera o valor do atributo 'listaTratamentos'.
     * @return valor do atributo 'listaTratamentos'
     * @see
     */
    public List<TratamentoVO> getListaTratamentos() {
        return listaTratamentos;
    }

    /**
     * Nome: setListaTratamentos Registra o valor do atributo 'listaTratamentos'.
     * @param listaTratamentos valor do atributo lista tratamentos
     * @see
     */
    public void setListaTratamentos(List<TratamentoVO> listaTratamentos) {
        this.listaTratamentos = listaTratamentos;
    }

    /**
     * Nome: getListaContatos Recupera o valor do atributo 'listaContatos'.
     * @return valor do atributo 'listaContatos'
     * @see
     */
    public List<ContatoVO> getListaContatos() {
        return listaContatos;
    }

    /**
     * Nome: setListaContatos Registra o valor do atributo 'listaContatos'.
     * @param listaContatos valor do atributo lista contatos
     * @see
     */
    public void setListaContatos(List<ContatoVO> listaContatos) {
        this.listaContatos = listaContatos;
    }

    /**
     * Nome: getListaDoencas Recupera o valor do atributo 'listaDoencas'.
     * @return valor do atributo 'listaDoencas'
     * @see
     */
    public List<DoencaVO> getListaDoencas() {
        return listaDoencas;
    }

    /**
     * Nome: setListaDoencas Registra o valor do atributo 'listaDoencas'.
     * @param listaDoencas valor do atributo lista doencas
     * @see
     */
    public void setListaDoencas(List<DoencaVO> listaDoencas) {
        this.listaDoencas = listaDoencas;
    }

    public ClienteVO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the usuario
     */
    public UsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the dtProxAtual
     */
    public Date getDtProxAtual() {
        return dtProxAtual;
    }

    /**
     * @param dtProxAtual the dtProxAtual to set
     */
    public void setDtProxAtual(Date dtProxAtual) {
        this.dtProxAtual = dtProxAtual;
    }

}
