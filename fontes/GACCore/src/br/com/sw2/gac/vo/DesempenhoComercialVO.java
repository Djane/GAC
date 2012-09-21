package br.com.sw2.gac.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Classe que representa o relatorio de desempenho comercial.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DesempenhoComercialVO {

    /** Atributo data apuracao. */
    private Date dataApuracao;

    /** Atributo qtd clientes inicio mes. */
    private Integer qtdClientesInicioMes;

    /** Atributo qtde clientes ativos. */
    private Integer qtdeClientesAtivos;

    /** Atributo qtde entrantes mes. */
    private Integer qtdeEntrantesMes;

    /** Atributo qtde cancelados mes. */
    private Integer qtdeCanceladosMes;

    /** Atributo qtde suspensos mes. */
    private Integer qtdeSuspensosMes;

    /** Atributo porcentagem entrantes mes. */
    private BigDecimal porcentagemEntrantesMes;

    /** Atributo porcentagem cancelados mes. */
    private BigDecimal porcentagemCanceladosMes;

    /** Atributo porcentagem suspensos mes. */
    private BigDecimal porcentagemSuspensosMes;

    /** Atributo clientes ativos. */
    private List<ClientesAtivosVO> clientesAtivos;

    /** Atributo movimentacao clientes. */
    private List<MovimentacaoClienteVO> movimentacaoClientes;

    /**
     * Nome: getDataApuracao
     * Recupera o valor do atributo 'dataApuracao'.
     *
     * @return valor do atributo 'dataApuracao'
     * @see
     */
    public Date getDataApuracao() {
        return dataApuracao;
    }

    /**
     * Nome: setDataApuracao
     * Registra o valor do atributo 'dataApuracao'.
     *
     * @param dataApuracao valor do atributo data apuracao
     * @see
     */
    public void setDataApuracao(Date dataApuracao) {
        this.dataApuracao = dataApuracao;
    }

    /**
     * Nome: getQtdClientesInicioMes
     * Recupera o valor do atributo 'qtdClientesInicioMes'.
     *
     * @return valor do atributo 'qtdClientesInicioMes'
     * @see
     */
    public Integer getQtdClientesInicioMes() {
        return qtdClientesInicioMes;
    }

    /**
     * Nome: setQtdClientesInicioMes
     * Registra o valor do atributo 'qtdClientesInicioMes'.
     *
     * @param qtdClientesInicioMes valor do atributo qtd clientes inicio mes
     * @see
     */
    public void setQtdClientesInicioMes(Integer qtdClientesInicioMes) {
        this.qtdClientesInicioMes = qtdClientesInicioMes;
    }

    /**
     * Nome: getQtdeClientesAtivos
     * Recupera o valor do atributo 'qtdeClientesAtivos'.
     *
     * @return valor do atributo 'qtdeClientesAtivos'
     * @see
     */
    public Integer getQtdeClientesAtivos() {
        return qtdeClientesAtivos;
    }

    /**
     * Nome: setQtdeClientesAtivos
     * Registra o valor do atributo 'qtdeClientesAtivos'.
     *
     * @param qtdeClientesAtivos valor do atributo qtde clientes ativos
     * @see
     */
    public void setQtdeClientesAtivos(Integer qtdeClientesAtivos) {
        this.qtdeClientesAtivos = qtdeClientesAtivos;
    }

    /**
     * Nome: getQtdeEntrantesMes
     * Recupera o valor do atributo 'qtdeEntrantesMes'.
     *
     * @return valor do atributo 'qtdeEntrantesMes'
     * @see
     */
    public Integer getQtdeEntrantesMes() {
        return qtdeEntrantesMes;
    }

    /**
     * Nome: setQtdeEntrantesMes
     * Registra o valor do atributo 'qtdeEntrantesMes'.
     *
     * @param qtdeEntrantesMes valor do atributo qtde entrantes mes
     * @see
     */
    public void setQtdeEntrantesMes(Integer qtdeEntrantesMes) {
        this.qtdeEntrantesMes = qtdeEntrantesMes;
    }

    /**
     * Nome: getQtdeCanceladosMes
     * Recupera o valor do atributo 'qtdeCanceladosMes'.
     *
     * @return valor do atributo 'qtdeCanceladosMes'
     * @see
     */
    public Integer getQtdeCanceladosMes() {
        return qtdeCanceladosMes;
    }

    /**
     * Nome: setQtdeCanceladosMes
     * Registra o valor do atributo 'qtdeCanceladosMes'.
     *
     * @param qtdeCanceladosMes valor do atributo qtde cancelados mes
     * @see
     */
    public void setQtdeCanceladosMes(Integer qtdeCanceladosMes) {
        this.qtdeCanceladosMes = qtdeCanceladosMes;
    }

    /**
     * Nome: getQtdeSuspensosMes
     * Recupera o valor do atributo 'qtdeSuspensosMes'.
     *
     * @return valor do atributo 'qtdeSuspensosMes'
     * @see
     */
    public Integer getQtdeSuspensosMes() {
        return qtdeSuspensosMes;
    }

    /**
     * Nome: setQtdeSuspensosMes
     * Registra o valor do atributo 'qtdeSuspensosMes'.
     *
     * @param qtdeSuspensosMes valor do atributo qtde suspensos mes
     * @see
     */
    public void setQtdeSuspensosMes(Integer qtdeSuspensosMes) {
        this.qtdeSuspensosMes = qtdeSuspensosMes;
    }

    /**
     * Nome: getPorcentagemEntrantesMes
     * Recupera o valor do atributo 'porcentagemEntrantesMes'.
     *
     * @return valor do atributo 'porcentagemEntrantesMes'
     * @see
     */
    public BigDecimal getPorcentagemEntrantesMes() {
        return porcentagemEntrantesMes;
    }

    /**
     * Nome: setPorcentagemEntrantesMes
     * Registra o valor do atributo 'porcentagemEntrantesMes'.
     *
     * @param porcentagemEntrantesMes valor do atributo porcentagem entrantes mes
     * @see
     */
    public void setPorcentagemEntrantesMes(BigDecimal porcentagemEntrantesMes) {
        this.porcentagemEntrantesMes = porcentagemEntrantesMes;
    }

    /**
     * Nome: getPorcentagemCanceladosMes
     * Recupera o valor do atributo 'porcentagemCanceladosMes'.
     *
     * @return valor do atributo 'porcentagemCanceladosMes'
     * @see
     */
    public BigDecimal getPorcentagemCanceladosMes() {
        return porcentagemCanceladosMes;
    }

    /**
     * Nome: setPorcentagemCanceladosMes
     * Registra o valor do atributo 'porcentagemCanceladosMes'.
     *
     * @param porcentagemCanceladosMes valor do atributo porcentagem cancelados mes
     * @see
     */
    public void setPorcentagemCanceladosMes(BigDecimal porcentagemCanceladosMes) {
        this.porcentagemCanceladosMes = porcentagemCanceladosMes;
    }

    /**
     * Nome: getPorcentagemSuspensosMes
     * Recupera o valor do atributo 'porcentagemSuspensosMes'.
     *
     * @return valor do atributo 'porcentagemSuspensosMes'
     * @see
     */
    public BigDecimal getPorcentagemSuspensosMes() {
        return porcentagemSuspensosMes;
    }

    /**
     * Nome: setPorcentagemSuspensosMes
     * Registra o valor do atributo 'porcentagemSuspensosMes'.
     *
     * @param porcentagemSuspensosMes valor do atributo porcentagem suspensos mes
     * @see
     */
    public void setPorcentagemSuspensosMes(BigDecimal porcentagemSuspensosMes) {
        this.porcentagemSuspensosMes = porcentagemSuspensosMes;
    }

    /**
     * Nome: getClientesAtivos
     * Recupera o valor do atributo 'clientesAtivos'.
     *
     * @return valor do atributo 'clientesAtivos'
     * @see
     */
    public List<ClientesAtivosVO> getClientesAtivos() {
        return clientesAtivos;
    }

    /**
     * Nome: setClientesAtivos
     * Registra o valor do atributo 'clientesAtivos'.
     *
     * @param clientesAtivos valor do atributo clientes ativos
     * @see
     */
    public void setClientesAtivos(List<ClientesAtivosVO> clientesAtivos) {
        this.clientesAtivos = clientesAtivos;
    }

    /**
     * Nome: getMovimentacaoClientes
     * Recupera o valor do atributo 'movimentacaoClientes'.
     *
     * @return valor do atributo 'movimentacaoClientes'
     * @see
     */
    public List<MovimentacaoClienteVO> getMovimentacaoClientes() {
        return movimentacaoClientes;
    }

    /**
     * Nome: setMovimentacaoClientes
     * Registra o valor do atributo 'movimentacaoClientes'.
     *
     * @param movimentacaoClientes valor do atributo movimentacao clientes
     * @see
     */
    public void setMovimentacaoClientes(List<MovimentacaoClienteVO> movimentacaoClientes) {
        this.movimentacaoClientes = movimentacaoClientes;
    }

}
