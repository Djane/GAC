package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <b>Descrição: Classe que representa uma ocorrência no sistema.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class OcorrenciaVO implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 128336188207252599L;

    /** Atributo id ocorrencia. */
    private Integer idOcorrencia;

    /** Atributo tipo ocorrencia. */
    private TipoOcorrenciaVO tipoOcorrencia;

    /** Atributo data abertura. */
    private Date dataAbertura;

    /** Atributo contrato. */
    private ClienteVO cliente;

    /** Atributo contrato. */
    private ContratoVO contrato;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /** Atributo status ocorrencia. */
    private Integer statusOcorrencia;

    /** Atributo acao ocorrencia. */
    private String descricao;

    /** Atributo conclusao. */
    private String resolucao;

    /** Atributo dta hora abertura. */
    private Date dataHoraAberturaOcorrencia;

    /** Atributo dta hora fechamento. */
    private Date dataHoraFechamentoOcorrencia;

    /** Atributo dta hora inicio. */
    private Date dataHoraInicioContato;

    /** Atributo dta hora termino. */
    private Date dataHoraTerminoContato;

    /** Atributo script. */
    private ScriptVO script;

    /** Atributo cod prioridade. */
    private Integer codigoPrioridade;

    /** Atributo sn dispositivo. */
    private Integer snDispositivo;

    /** Atributo numeror telefone ligado. */
    private String numerorTelefoneLigado;

    /** Atributo id ligacao. */
    private Integer idLigacao;

    /** Atributo lista historico ocorrencias. */
    private List<OcorrenciaVO> listaHistoricoOcorrencias;

    /**
     * Nome: getIdOcorrencia Recupera o valor do atributo 'idOcorrencia'.
     * @return valor do atributo 'idOcorrencia'
     * @see
     */
    public Integer getIdOcorrencia() {
        return idOcorrencia;
    }

    /**
     * Nome: setIdOcorrencia Registra o valor do atributo 'idOcorrencia'.
     * @param idOcorrencia valor do atributo id ocorrencia
     * @see
     */
    public void setIdOcorrencia(Integer idOcorrencia) {
        this.idOcorrencia = idOcorrencia;
    }

    /**
     * Nome: getTipoOcorrencia Recupera o valor do atributo 'tipoOcorrencia'.
     * @return valor do atributo 'tipoOcorrencia'
     * @see
     */
    public TipoOcorrenciaVO getTipoOcorrencia() {
        return tipoOcorrencia;
    }

    /**
     * Nome: setTipoOcorrencia Registra o valor do atributo 'tipoOcorrencia'.
     * @param tipoOcorrencia valor do atributo tipo ocorrencia
     * @see
     */
    public void setTipoOcorrencia(TipoOcorrenciaVO tipoOcorrencia) {
        this.tipoOcorrencia = tipoOcorrencia;
    }

    /**
     * Nome: getDataAbertura Recupera o valor do atributo 'dataAbertura'.
     * @return valor do atributo 'dataAbertura'
     * @see
     */
    public Date getDataAbertura() {
        return dataAbertura;
    }

    /**
     * Nome: setDataAbertura Registra o valor do atributo 'dataAbertura'.
     * @param dataAbertura valor do atributo data abertura
     * @see
     */
    public void setDataAbertura(Date dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /**
     * Nome: getCliente Recupera o valor do atributo 'cliente'.
     * @return valor do atributo 'cliente'
     * @see
     */
    public ClienteVO getCliente() {
        return cliente;
    }

    /**
     * Nome: setCliente Registra o valor do atributo 'cliente'.
     * @param cliente valor do atributo cliente
     * @see
     */
    public void setCliente(ClienteVO cliente) {
        this.cliente = cliente;
    }

    /**
     * Nome: getUsuario Recupera o valor do atributo 'usuario'.
     * @return valor do atributo 'usuario'
     * @see
     */
    public UsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * Nome: setUsuario Registra o valor do atributo 'usuario'.
     * @param usuario valor do atributo usuario
     * @see
     */
    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    /**
     * Nome: getStatusOcorrencia Recupera o valor do atributo 'statusOcorrencia'.
     * @return valor do atributo 'statusOcorrencia'
     * @see
     */
    public Integer getStatusOcorrencia() {
        return statusOcorrencia;
    }

    /**
     * Nome: setStatusOcorrencia Registra o valor do atributo 'statusOcorrencia'.
     * @param statusOcorrencia valor do atributo status ocorrencia
     * @see
     */
    public void setStatusOcorrencia(Integer statusOcorrencia) {
        this.statusOcorrencia = statusOcorrencia;
    }

    /**
     * Nome: getDescricao
     * Recupera o valor do atributo 'descricao'.
     *
     * @return valor do atributo 'descricao'
     * @see
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Nome: setDescricao
     * Registra o valor do atributo 'descricao'.
     *
     * @param descricao valor do atributo descricao
     * @see
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * Nome: getResolucao
     * Recupera o valor do atributo 'resolucao'.
     *
     * @return valor do atributo 'resolucao'
     * @see
     */
    public String getResolucao() {
        return resolucao;
    }

    /**
     * Nome: setResolucao
     * Registra o valor do atributo 'resolucao'.
     *
     * @param resolucao valor do atributo resolucao
     * @see
     */
    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    /**
     * Nome: getDataHoraAberturaOcorrencia Recupera o valor do atributo
     * 'dataHoraAberturaOcorrencia'.
     * @return valor do atributo 'dataHoraAberturaOcorrencia'
     * @see
     */
    public Date getDataHoraAberturaOcorrencia() {
        return dataHoraAberturaOcorrencia;
    }

    /**
     * Nome: setDataHoraAberturaOcorrencia Registra o valor do atributo
     * 'dataHoraAberturaOcorrencia'.
     * @param dataHoraAberturaOcorrencia valor do atributo data hora abertura ocorrencia
     * @see
     */
    public void setDataHoraAberturaOcorrencia(Date dataHoraAberturaOcorrencia) {
        this.dataHoraAberturaOcorrencia = dataHoraAberturaOcorrencia;
    }

    /**
     * Nome: getDataHoraFechamentoOcorrencia Recupera o valor do atributo
     * 'dataHoraFechamentoOcorrencia'.
     * @return valor do atributo 'dataHoraFechamentoOcorrencia'
     * @see
     */
    public Date getDataHoraFechamentoOcorrencia() {
        return dataHoraFechamentoOcorrencia;
    }

    /**
     * Nome: setDataHoraFechamentoOcorrencia Registra o valor do atributo
     * 'dataHoraFechamentoOcorrencia'.
     * @param dataHoraFechamentoOcorrencia valor do atributo data hora fechamento ocorrencia
     * @see
     */
    public void setDataHoraFechamentoOcorrencia(Date dataHoraFechamentoOcorrencia) {
        this.dataHoraFechamentoOcorrencia = dataHoraFechamentoOcorrencia;
    }

    /**
     * Nome: getDataHoraInicioContato Recupera o valor do atributo 'dataHoraInicioContato'.
     * @return valor do atributo 'dataHoraInicioContato'
     * @see
     */
    public Date getDataHoraInicioContato() {
        return dataHoraInicioContato;
    }

    /**
     * Nome: setDataHoraInicioContato Registra o valor do atributo 'dataHoraInicioContato'.
     * @param dataHoraInicioContato valor do atributo data hora inicio contato
     * @see
     */
    public void setDataHoraInicioContato(Date dataHoraInicioContato) {
        this.dataHoraInicioContato = dataHoraInicioContato;
    }

    /**
     * Nome: getDataHoraTerminoContato Recupera o valor do atributo 'dataHoraTerminoContato'.
     * @return valor do atributo 'dataHoraTerminoContato'
     * @see
     */
    public Date getDataHoraTerminoContato() {
        return dataHoraTerminoContato;
    }

    /**
     * Nome: setDataHoraTerminoContato Registra o valor do atributo 'dataHoraTerminoContato'.
     * @param dataHoraTerminoContato valor do atributo data hora termino contato
     * @see
     */
    public void setDataHoraTerminoContato(Date dataHoraTerminoContato) {
        this.dataHoraTerminoContato = dataHoraTerminoContato;
    }

    /**
     * Nome: getScript Recupera o valor do atributo 'script'.
     * @return valor do atributo 'script'
     * @see
     */
    public ScriptVO getScript() {
        return script;
    }

    /**
     * Nome: setScript Registra o valor do atributo 'script'.
     * @param script valor do atributo script
     * @see
     */
    public void setScript(ScriptVO script) {
        this.script = script;
    }

    /**
     * Nome: getCodigoPrioridade Recupera o valor do atributo 'codigoPrioridade'.
     * @return valor do atributo 'codigoPrioridade'
     * @see
     */
    public Integer getCodigoPrioridade() {
        return codigoPrioridade;
    }

    /**
     * Nome: setCodigoPrioridade Registra o valor do atributo 'codigoPrioridade'.
     * @param codigoPrioridade valor do atributo codigo prioridade
     * @see
     */
    public void setCodigoPrioridade(Integer codigoPrioridade) {
        this.codigoPrioridade = codigoPrioridade;
    }

    /**
     * Nome: getSnDispositivo Recupera o valor do atributo 'snDispositivo'.
     * @return valor do atributo 'snDispositivo'
     * @see
     */
    public Integer getSnDispositivo() {
        return snDispositivo;
    }

    /**
     * Nome: setSnDispositivo Registra o valor do atributo 'snDispositivo'.
     * @param snDispositivo valor do atributo sn dispositivo
     * @see
     */
    public void setSnDispositivo(Integer snDispositivo) {
        this.snDispositivo = snDispositivo;
    }

    /**
     * Nome: getNumerorTelefoneLigado Recupera o valor do atributo 'numerorTelefoneLigado'.
     * @return valor do atributo 'numerorTelefoneLigado'
     * @see
     */
    public String getNumerorTelefoneLigado() {
        return numerorTelefoneLigado;
    }

    /**
     * Nome: setNumerorTelefoneLigado Registra o valor do atributo 'numerorTelefoneLigado'.
     * @param numerorTelefoneLigado valor do atributo numeror telefone ligado
     * @see
     */
    public void setNumerorTelefoneLigado(String numerorTelefoneLigado) {
        this.numerorTelefoneLigado = numerorTelefoneLigado;
    }

    /**
     * Nome: getIdLigacao Recupera o valor do atributo 'idLigacao'.
     * @return valor do atributo 'idLigacao'
     * @see
     */
    public Integer getIdLigacao() {
        return idLigacao;
    }

    /**
     * Nome: setIdLigacao Registra o valor do atributo 'idLigacao'.
     * @param idLigacao valor do atributo id ligacao
     * @see
     */
    public void setIdLigacao(Integer idLigacao) {
        this.idLigacao = idLigacao;
    }

    /**
     * Nome: getContrato
     * Recupera o valor do atributo 'contrato'.
     *
     * @return valor do atributo 'contrato'
     * @see
     */
    public ContratoVO getContrato() {
        return contrato;
    }

    /**
     * Nome: setContrato
     * Registra o valor do atributo 'contrato'.
     *
     * @param contrato valor do atributo contrato
     * @see
     */
    public void setContrato(ContratoVO contrato) {
        this.contrato = contrato;
    }

    /**
     * Nome: getListaHistoricoOcorrencias
     * Recupera o valor do atributo 'listaHistoricoOcorrencias'.
     *
     * @return valor do atributo 'listaHistoricoOcorrencias'
     * @see
     */
    public List<OcorrenciaVO> getListaHistoricoOcorrencias() {
        return listaHistoricoOcorrencias;
    }

    /**
     * Nome: setListaHistoricoOcorrencias
     * Registra o valor do atributo 'listaHistoricoOcorrencias'.
     *
     * @param listaHistoricoOcorrencias valor do atributo lista historico ocorrencias
     * @see
     */
    public void setListaHistoricoOcorrencias(List<OcorrenciaVO> listaHistoricoOcorrencias) {
        this.listaHistoricoOcorrencias = listaHistoricoOcorrencias;
    }

}
