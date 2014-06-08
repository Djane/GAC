package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.OcorrenciaBusiness;
import br.com.sw2.gac.business.ScriptBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.StatusOcorrenciaException;
import br.com.sw2.gac.socket.PhoneCommand;
import br.com.sw2.gac.socket.SocketPhone;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.tools.StatusOcorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.ScriptVO;

/**
 * <b>Descrição: Bean comun as telas de atendimento e pre-atendimento</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class BaseAtendimentoBean extends BaseContratoBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 6558417579856032801L;

    /** Atributo lista tipos correncia. */
    private List<SelectItem> listaTiposCorrencia;

    /** Atributo lista status ocorrencia. */
    private List<SelectItem> listaStatusOcorrencia = new ArrayList<SelectItem>();

    /** Atributo lista script atendimento. */
    private List<SelectItem> listaScriptAtendimento = new ArrayList<SelectItem>();

    /** Atributo script atendimento selecionado. */
    protected String scriptAtendimentoSelecionado = "";

    /** Atributo ocorrencia business. */
    protected OcorrenciaBusiness ocorrenciaBusiness = new OcorrenciaBusiness();

    /** Atributo socket client. */
    protected SocketPhone socketPhone = null;

    /**
     * Construtor Padrao
     * Instancia um novo objeto BaseAtendimentoBean.
     */
    public BaseAtendimentoBean() {
        this.listaTiposCorrencia = getSelectItems(TipoOcorrencia.class);
        this.listaStatusOcorrencia = getSelectItems(StatusOcorrencia.class);

        popularListaDeScripts();
    }

    /**
     * Nome: popularListaDeScripts Popular lista de scripts.
     * @see
     */
    public void popularListaDeScripts() {
        ScriptBusiness scriptBusiness = new ScriptBusiness();
        List<ScriptVO> listaScriptVO = scriptBusiness.obterListaScripts();
        this.listaScriptAtendimento = getSelectItems(listaScriptVO, "idScript", "tituloScript");
    }



    /**
     * Nome: salvarDadosOcorrencia
     * Salvar dados ocorrencia.
     *
     * @param ocorrencia the ocorrencia
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean salvarDadosOcorrencia(OcorrenciaVO ocorrencia) {
        boolean sucesso = false;
        this.logger.debug(getClass(), "***** Iniciando método salvarDadosContrato(ActionEvent e) *****");

        if (this.scriptAtendimentoSelecionado == "0") {
            ocorrencia.setScript(null);
        } else {
            ocorrencia.setScript(new ScriptVO());
            ocorrencia.getScript().setIdScript(Integer.parseInt(this.scriptAtendimentoSelecionado));
        }

        try {
            this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(ocorrencia);
            setFacesMessage("message.atendimento.save.sucess");
            sucesso = true;
            addCallbackParam("ocorrenciaSalva", sucesso);

        } catch (StatusOcorrenciaException ex) {
            setFacesMessage("message.atendimento.save.status.exception");
            this.logarErro(ex);
        } catch (BusinessException ex) {
            setFacesMessage("message.atendimento.save.failed");
            this.logarErro(ex);
        }

        this.logger.debug(getClass(), "***** Finalizado método salvarDadosContrato(ActionEvent e) *****");

        return sucesso;

    }

    /**
     * Nome: encerrarLigacao
     * Encerrar ligacao.
     *
     * @param numeroLinha the numero linha
     * @see
     */
    protected void encerrarLigacao(Integer numeroLinha) {
        Line line = new Line();
        line.setNumeroLinha(numeroLinha);
        this.encerrarLigacao(line, true);
    }

    /**
     * Nome: encerrarLigacao
     * Encerrar ligacao baseado na linha informada.
     *
     * @param linha the linha
     * @param pesquisarLinhas Indica que o numero da linha informado deve ser consultado na lista de linhas so socketPhone.
     * @see
     */
    protected void encerrarLigacao(Line linha, boolean pesquisarLinhas) {

        Line linhaAEncerrar;
        if (pesquisarLinhas) {
            linhaAEncerrar = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroLinha", linha.getNumeroLinha());
        } else {
            linhaAEncerrar = linha;
        }

        if (linhaAEncerrar.getNumeroDiscado().equals("*9800")) {
            this.socketPhone.encerrarChamadaParaAgente(linhaAEncerrar.getNumeroLinha());
            this.logger.debug(getClass(), "Encerrando ligação dentro de uma chamada para *9800");

        } else {
            if (null != this.socketPhone && null != this.socketPhone.getSocket()) {
                this.socketPhone.encerrarChamada(linha.getNumeroLinha());
            }
            this.logger.debug(getClass(), "Encerrando um a ligação em uma linha");
        }
    }

    /**
     * Envia um sinal para falar ou ouvir. Sinal de comutação
     */
    public void cambio(ActionEvent event) {
        try {
           if (null != this.socketPhone) {
              //  this.socketPhone.enviarMensagem(PhoneCommand.dgTimeStamp(210));
                this.logger.debug(getClass(), "Enviando o comando C para Falar/Ouvir");
                this.socketPhone.enviarSinalComutacao();
            } else {
                this.logger.debug(getClass(),
                    "Não foi possível enviar enviar o comando C para Falar/Ouvir");
            }

        } catch (Exception e) {
            this.logger.error(getClass(), "Erro ao enviar enviar o comando C para Falar/Ouvir: "
                + e.getMessage());
        }
    }
    
    
    /**
     * Nome: getListaTiposCorrencia Recupera o valor do atributo 'listaTiposCorrencia'.
     * @return valor do atributo 'listaTiposCorrencia'
     * @see
     */
    public List<SelectItem> getListaTiposCorrencia() {
        return listaTiposCorrencia;
    }

    /**
     * Nome: setListaTiposCorrencia Registra o valor do atributo 'listaTiposCorrencia'.
     * @param listaTiposCorrencia valor do atributo lista tipos correncia
     * @see
     */
    public void setListaTiposCorrencia(List<SelectItem> listaTiposCorrencia) {
        this.listaTiposCorrencia = listaTiposCorrencia;
    }

    /**
     * Nome: getListaStatusOcorrencia Recupera o valor do atributo 'listaStatusOcorrencia'.
     * @return valor do atributo 'listaStatusOcorrencia'
     * @see
     */
    public List<SelectItem> getListaStatusOcorrencia() {
        return listaStatusOcorrencia;
    }

    /**
     * Nome: setListaStatusOcorrencia Registra o valor do atributo 'listaStatusOcorrencia'.
     * @param listaStatusOcorrencia valor do atributo lista status ocorrencia
     * @see
     */
    public void setListaStatusOcorrencia(List<SelectItem> listaStatusOcorrencia) {
        this.listaStatusOcorrencia = listaStatusOcorrencia;
    }

    /**
     * Nome: getListaScriptAtendimento Recupera o valor do atributo 'listaScriptAtendimento'.
     * @return valor do atributo 'listaScriptAtendimento'
     * @see
     */
    public List<SelectItem> getListaScriptAtendimento() {
        return listaScriptAtendimento;
    }

    /**
     * Nome: setListaScriptAtendimento Registra o valor do atributo 'listaScriptAtendimento'.
     * @param listaScriptAtendimento valor do atributo lista script atendimento
     * @see
     */
    public void setListaScriptAtendimento(List<SelectItem> listaScriptAtendimento) {
        this.listaScriptAtendimento = listaScriptAtendimento;
    }

    /**
     * Nome: getScriptAtendimentoSelecionado Recupera o valor do atributo
     * 'scriptAtendimentoSelecionado'.
     * @return valor do atributo 'scriptAtendimentoSelecionado'
     * @see
     */
    public String getScriptAtendimentoSelecionado() {
        return scriptAtendimentoSelecionado;
    }

    /**
     * Nome: setScriptAtendimentoSelecionado Registra o valor do atributo
     * 'scriptAtendimentoSelecionado'.
     * @param scriptAtendimentoSelecionado valor do atributo script atendimento selecionado
     * @see
     */
    public void setScriptAtendimentoSelecionado(String scriptAtendimentoSelecionado) {
        this.scriptAtendimentoSelecionado = scriptAtendimentoSelecionado;
    }

    /**
     * Nome: getSocketPhone
     * Recupera o valor do atributo 'socketPhone'.
     *
     * @return valor do atributo 'socketPhone'
     * @see
     */
    public SocketPhone getSocketPhone() {
        return socketPhone;
    }

    /**
     * Nome: setSocketPhone
     * Registra o valor do atributo 'socketPhone'.
     *
     * @param socketPhone valor do atributo socket phone
     * @see
     */
    public void setSocketPhone(SocketPhone socketPhone) {
        this.socketPhone = socketPhone;
    }

}
