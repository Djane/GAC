package br.com.sw2.gac.vo;

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

    /** Atributo clientes ativos. */
    private List<ClientesAtivosVO> clientesAtivos;

    /** Atributo movimentacao clientes. */
    private List<MovimentacaoClienteVO> movimentacaoClientes;

    /**
     * Nome: getDataApuracao Recupera o valor do atributo 'dataApuracao'.
     * @return valor do atributo 'dataApuracao'
     * @see
     */
    public Date getDataApuracao() {
        return dataApuracao;
    }

    /**
     * Nome: setDataApuracao Registra o valor do atributo 'dataApuracao'.
     * @param dataApuracao valor do atributo data apuracao
     * @see
     */
    public void setDataApuracao(Date dataApuracao) {
        this.dataApuracao = dataApuracao;
    }

    /**
     * Nome: getQtdClientesInicioMes Recupera o valor do atributo 'qtdClientesInicioMes'.
     * @return valor do atributo 'qtdClientesInicioMes'
     * @see
     */
    public Integer getQtdClientesInicioMes() {
        return qtdClientesInicioMes;
    }

    /**
     * Nome: setQtdClientesInicioMes Registra o valor do atributo 'qtdClientesInicioMes'.
     * @param qtdClientesInicioMes valor do atributo qtd clientes inicio mes
     * @see
     */
    public void setQtdClientesInicioMes(Integer qtdClientesInicioMes) {
        this.qtdClientesInicioMes = qtdClientesInicioMes;
    }

    /**
     * Nome: getQtdeClientesAtivos Recupera o valor do atributo 'qtdeClientesAtivos'.
     * @return valor do atributo 'qtdeClientesAtivos'
     * @see
     */
    public Integer getQtdeClientesAtivos() {
        return qtdeClientesAtivos;
    }

    /**
     * Nome: setQtdeClientesAtivos Registra o valor do atributo 'qtdeClientesAtivos'.
     * @param qtdeClientesAtivos valor do atributo qtde clientes ativos
     * @see
     */
    public void setQtdeClientesAtivos(Integer qtdeClientesAtivos) {
        this.qtdeClientesAtivos = qtdeClientesAtivos;
    }

    /**
     * Nome: getClientesAtivos Recupera o valor do atributo 'clientesAtivos'.
     * @return valor do atributo 'clientesAtivos'
     * @see
     */
    public List<ClientesAtivosVO> getClientesAtivos() {
        return clientesAtivos;
    }

    /**
     * Nome: setClientesAtivos Registra o valor do atributo 'clientesAtivos'.
     * @param clientesAtivos valor do atributo clientes ativos
     * @see
     */
    public void setClientesAtivos(List<ClientesAtivosVO> clientesAtivos) {
        this.clientesAtivos = clientesAtivos;
    }

    /**
     * Nome: getMovimentacaoClientes Recupera o valor do atributo 'movimentacaoClientes'.
     * @return valor do atributo 'movimentacaoClientes'
     * @see
     */
    public List<MovimentacaoClienteVO> getMovimentacaoClientes() {
        return movimentacaoClientes;
    }

    /**
     * Nome: setMovimentacaoClientes Registra o valor do atributo 'movimentacaoClientes'.
     * @param movimentacaoClientes valor do atributo movimentacao clientes
     * @see
     */
    public void setMovimentacaoClientes(List<MovimentacaoClienteVO> movimentacaoClientes) {
        this.movimentacaoClientes = movimentacaoClientes;
    }

}
