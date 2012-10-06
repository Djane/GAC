package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.vo.ContratoVO;

/**
 * <b>Descrição Controller da tela de pesquisa de contratos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class PerquisarContratoBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -2015282411903689056L;

    /** Atributo contrato business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

    /** Atributo numero contrato. */
    private Integer numeroContrato;

    /** Atributo cpf contratante. */
    private String cpfContratante;

    /** Atributo nome contratante. */
    private String nomeContratante;

    /** Atributo resultado pesquisa contratos. */
    private List<ContratoVO> resultadoPesquisaContratos;

    /** Atributo numero contrato selecionado. */
    private Integer numeroContratoSelecionado;

    /**
     * Nome: pesquisarEfetua a pesquisa de contratos, baseado no filtro informado na tela.
     * @param e the e
     * @see
     */
    public void pesquisar(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método pesquisar(...)");
        this.getLogger().debug("Filtro recebido");
        this.getLogger().debug("CPF Contratante: " + this.cpfContratante);
        this.getLogger().debug("Número contrato: " + this.numeroContrato);
        this.getLogger().debug("Nome contratante: " + this.nomeContratante);

        this.resultadoPesquisaContratos = this.contratoBusiness.pesquisarContratos(
            this.numeroContrato, this.cpfContratante, this.nomeContratante);
        this.getLogger().debug("***** finalização do método pesquisar(...) ****");
    }

    /**
     * Nome: reset Reseta o form e resultado da pesquisa efetuada..
     * @param e the e
     * @see
     */
    public void reset(ActionEvent e) {
        this.cpfContratante = "";
        this.numeroContrato = null;
        this.nomeContratante = "";

        this.resultadoPesquisaContratos = new ArrayList<ContratoVO>();

    }

    /**
     * Nome: excluirContrato Excluir contrato.
     * @param e the e
     * @see
     */
    public void excluirContrato(ActionEvent e) {
        ContratoVO contrato = new ContratoVO();
        contrato.setNumeroContrato(this.numeroContratoSelecionado);
        this.contratoBusiness.excluirContrato(contrato);
    }

    /**
     * Nome: getContratoBusiness Recupera o valor do atributo 'contratoBusiness'.
     * @return valor do atributo 'contratoBusiness'
     * @see
     */
    public ContratoBusiness getContratoBusiness() {
        return contratoBusiness;
    }

    /**
     * Nome: setContratoBusiness Registra o valor do atributo 'contratoBusiness'.
     * @param contratoBusiness valor do atributo contrato business
     * @see
     */
    public void setContratoBusiness(ContratoBusiness contratoBusiness) {
        this.contratoBusiness = contratoBusiness;
    }

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
     * Nome: getResultadoPesquisaContratos Recupera o valor do atributo
     * 'resultadoPesquisaContratos'.
     * @return valor do atributo 'resultadoPesquisaContratos'
     * @see
     */
    public List<ContratoVO> getResultadoPesquisaContratos() {
        return resultadoPesquisaContratos;
    }

    /**
     * Nome: setResultadoPesquisaContratos Registra o valor do atributo
     * 'resultadoPesquisaContratos'.
     * @param resultadoPesquisaContratos valor do atributo resultado pesquisa contratos
     * @see
     */
    public void setResultadoPesquisaContratos(List<ContratoVO> resultadoPesquisaContratos) {
        this.resultadoPesquisaContratos = resultadoPesquisaContratos;
    }

    /**
     * Nome: getSerialversionuid Recupera o valor do atributo 'serialversionuid'.
     * @return valor do atributo 'serialversionuid'
     * @see
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    /**
     * Nome: getNumeroContratoSelecionado Recupera o valor do atributo 'numeroContratoSelecionado'.
     * @return valor do atributo 'numeroContratoSelecionado'
     * @see
     */
    public Integer getNumeroContratoSelecionado() {
        return numeroContratoSelecionado;
    }

    /**
     * Nome: setNumeroContratoSelecionado Registra o valor do atributo 'numeroContratoSelecionado'.
     * @param numeroContratoSelecionado valor do atributo numero contrato selecionado
     * @see
     */
    public void setNumeroContratoSelecionado(Integer numeroContratoSelecionado) {
        this.numeroContratoSelecionado = numeroContratoSelecionado;
    }
}
