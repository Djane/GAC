package br.com.sw2.gac.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.datamodel.ContatoDataModel;
import br.com.sw2.gac.datamodel.OcorrenciaDataModel;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.OcorrenciaVO;

/**
 * <b>Descrição: Controller da tela de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class AtendimentoBean extends BaseBean {

    /** Atributo contrato. */
    private ContratoVO contrato;

    /** Atributo lista forma contato. */
    private List<FormaContatoVO> listaFormasContato;

    /** Atributo lista historico ocorrencia. */
    private List<OcorrenciaVO> listaHistoricoOcorrencia;

    /** Atributo lista tipos correncia. */
    private List<SelectItem> listaTiposCorrencia;

    /** Atributo contato data model. */
    private ContatoDataModel contatoDataModel;

    /** Atributo contato selecionado. */
    private ContatoVO contatoSelecionado;

    /** Atributo contato data model. */
    private OcorrenciaDataModel ocorrenciaDataModel;

    /** Atributo ocorrencia selecionada. */
    private OcorrenciaVO ocorrenciaSelecionada;

    /** Atributo ocorrencia em andamento. */
    private OcorrenciaVO ocorrenciaEmAndamento;

    /** Atributo led semaforo verde. */
    private String ledSemaforoVerde = "img/green_circle_off.png";

    /** Atributo led semaforo amarelo. */
    private String ledSemaforoAmarelo = "img/yellow_circle_off.png";

    /** Atributo led semaforo vermelho. */
    private String ledSemaforoVermelho = "img/red_circle_off.png";

    /**
     * Construtor Padrao Instancia um novo objeto AtendimentoBean.
     */
    public AtendimentoBean() {
        this.ocorrenciaEmAndamento = new OcorrenciaVO();
        this.contrato = GacMock.getContrato();
        if (null != contrato && null != this.contrato.getListaContatos()
                && !this.contrato.getListaContatos().isEmpty()) {
            this.contatoSelecionado = this.contrato.getListaContatos().get(0);
        }
        this.listaTiposCorrencia = getSelectIems(TipoOcorrencia.class);
        this.contatoDataModel = new ContatoDataModel(this.contrato.getListaContatos());
        this.ocorrenciaDataModel = new OcorrenciaDataModel(GacMock.getHistoricoOcorrencias());
    }

    /**
     * Nome: atualizarGradeFormaContato Atualizar grade forma contato.
     * @see
     */
    public void atualizarGradeFormaContato() {

        ContatoVO contato = (ContatoVO) findInListById(this.getContrato().getListaContatos(),
                "idContato", 1);
        this.listaFormasContato = contato.getListaFormaContato();
    }

    /**
     * Nome: semafaroOff Semafaro off.
     * @see
     */
    private void semafaroOff() {
        this.ledSemaforoVerde = "img/green_circle_off.png";
        this.ledSemaforoAmarelo = "img/yellow_circle_off.png";
        this.ledSemaforoVermelho = "img/red_circle_off.png";
    }

    /**
     * Nome: mudarPrioridadeVerde Mudar prioridade verde.
     * @param event the event
     * @see
     */
    public void mudarPrioridadeVerde(ActionEvent event) {

        if (this.ledSemaforoVerde.equals("img/green_circle_on.png")) {
            this.semafaroOff();
            this.ledSemaforoVerde = "img/green_circle_off.png";
        } else {
            this.semafaroOff();
            this.ledSemaforoVerde = "img/green_circle_on.png";
        }
    }

    /**
     * Nome: mudarPrioridadeAmarelo Mudar prioridade amarelo.
     * @param event the event
     * @see
     */
    public void mudarPrioridadeAmarelo(ActionEvent event) {

        if (this.ledSemaforoAmarelo.equals("img/yellow_circle_on.png")) {
            this.semafaroOff();
            this.ledSemaforoAmarelo = "img/yellow_circle_off.png";
        } else {
            this.semafaroOff();
            this.ledSemaforoAmarelo = "img/yellow_circle_on.png";
        }
    }

    /**
     * Nome: mudarPrioridadeVermelho Mudar prioridade vermelho.
     * @param event the event
     * @see
     */
    public void mudarPrioridadeVermelho(ActionEvent event) {

        if (this.ledSemaforoVermelho.equals("img/red_circle_on.png")) {
            this.semafaroOff();
            this.ledSemaforoVermelho = "img/red_circle_off.png";
        } else {
            this.semafaroOff();
            this.ledSemaforoVermelho = "img/red_circle_on.png";
        }
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Atendimento");
        return "atendimento";
    }

    /**
     * Nome: getContrato Recupera o valor do atributo 'contrato'.
     * @return valor do atributo 'contrato'
     * @see
     */
    public ContratoVO getContrato() {
        return contrato;
    }

    /**
     * Nome: setContrato Registra o valor do atributo 'contrato'.
     * @param contrato valor do atributo contrato
     * @see
     */
    public void setContrato(ContratoVO contrato) {
        this.contrato = contrato;
    }

    /**
     * Nome: getListaFormasContato Recupera o valor do atributo 'listaFormasContato'.
     * @return valor do atributo 'listaFormasContato'
     * @see
     */
    public List<FormaContatoVO> getListaFormasContato() {
        return listaFormasContato;
    }

    /**
     * Nome: setListaFormasContato Registra o valor do atributo 'listaFormasContato'.
     * @param listaFormasContato valor do atributo lista formas contato
     * @see
     */
    public void setListaFormasContato(List<FormaContatoVO> listaFormasContato) {
        this.listaFormasContato = listaFormasContato;
    }

    /**
     * Nome: getListaHistoricoOcorrencia Recupera o valor do atributo 'listaHistoricoOcorrencia'.
     * @return valor do atributo 'listaHistoricoOcorrencia'
     * @see
     */
    public List<OcorrenciaVO> getListaHistoricoOcorrencia() {
        return listaHistoricoOcorrencia;
    }

    /**
     * Nome: setListaHistoricoOcorrencia Registra o valor do atributo 'listaHistoricoOcorrencia'.
     * @param listaHistoricoOcorrencia valor do atributo lista historico ocorrencia
     * @see
     */
    public void setListaHistoricoOcorrencia(List<OcorrenciaVO> listaHistoricoOcorrencia) {
        this.listaHistoricoOcorrencia = listaHistoricoOcorrencia;
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
     * Nome: getContatoDataModel Recupera o valor do atributo 'contatoDataModel'.
     * @return valor do atributo 'contatoDataModel'
     * @see
     */
    public ContatoDataModel getContatoDataModel() {
        return contatoDataModel;
    }

    /**
     * Nome: setContatoDataModel Registra o valor do atributo 'contatoDataModel'.
     * @param contatoDataModel valor do atributo contato data model
     * @see
     */
    public void setContatoDataModel(ContatoDataModel contatoDataModel) {
        this.contatoDataModel = contatoDataModel;
    }

    /**
     * Nome: getContatoSelecionado Recupera o valor do atributo 'contatoSelecionado'.
     * @return valor do atributo 'contatoSelecionado'
     * @see
     */
    public ContatoVO getContatoSelecionado() {
        return contatoSelecionado;
    }

    /**
     * Nome: setContatoSelecionado Registra o valor do atributo 'contatoSelecionado'.
     * @param contatoSelecionado valor do atributo contato selecionado
     * @see
     */
    public void setContatoSelecionado(ContatoVO contatoSelecionado) {
        this.contatoSelecionado = contatoSelecionado;
    }

    /**
     * Nome: getOcorrenciaDataModel Recupera o valor do atributo 'ocorrenciaDataModel'.
     * @return valor do atributo 'ocorrenciaDataModel'
     * @see
     */
    public OcorrenciaDataModel getOcorrenciaDataModel() {
        return ocorrenciaDataModel;
    }

    /**
     * Nome: setOcorrenciaDataModel Registra o valor do atributo 'ocorrenciaDataModel'.
     * @param ocorrenciaDataModel valor do atributo ocorrencia data model
     * @see
     */
    public void setOcorrenciaDataModel(OcorrenciaDataModel ocorrenciaDataModel) {
        this.ocorrenciaDataModel = ocorrenciaDataModel;
    }

    /**
     * Nome: getOcorrenciaSelecionada Recupera o valor do atributo 'ocorrenciaSelecionada'.
     * @return valor do atributo 'ocorrenciaSelecionada'
     * @see
     */
    public OcorrenciaVO getOcorrenciaSelecionada() {
        return ocorrenciaSelecionada;
    }

    /**
     * Nome: setOcorrenciaSelecionada Registra o valor do atributo 'ocorrenciaSelecionada'.
     * @param ocorrenciaSelecionada valor do atributo ocorrencia selecionada
     * @see
     */
    public void setOcorrenciaSelecionada(OcorrenciaVO ocorrenciaSelecionada) {
        this.ocorrenciaSelecionada = ocorrenciaSelecionada;
    }

    /**
     * Nome: getOcorrenciaEmAndamento Recupera o valor do atributo 'ocorrenciaEmAndamento'.
     * @return valor do atributo 'ocorrenciaEmAndamento'
     * @see
     */
    public OcorrenciaVO getOcorrenciaEmAndamento() {
        return ocorrenciaEmAndamento;
    }

    /**
     * Nome: setOcorrenciaEmAndamento Registra o valor do atributo 'ocorrenciaEmAndamento'.
     * @param ocorrenciaEmAndamento valor do atributo ocorrencia em andamento
     * @see
     */
    public void setOcorrenciaEmAndamento(OcorrenciaVO ocorrenciaEmAndamento) {
        this.ocorrenciaEmAndamento = ocorrenciaEmAndamento;
    }

    /**
     * Nome: getLedSemaforoVerde Recupera o valor do atributo 'ledSemaforoVerde'.
     * @return valor do atributo 'ledSemaforoVerde'
     * @see
     */
    public String getLedSemaforoVerde() {
        return ledSemaforoVerde;
    }

    /**
     * Nome: setLedSemaforoVerde Registra o valor do atributo 'ledSemaforoVerde'.
     * @param ledSemaforoVerde valor do atributo led semaforo verde
     * @see
     */
    public void setLedSemaforoVerde(String ledSemaforoVerde) {
        this.ledSemaforoVerde = ledSemaforoVerde;
    }

    /**
     * Nome: getLedSemaforoAmarelo Recupera o valor do atributo 'ledSemaforoAmarelo'.
     * @return valor do atributo 'ledSemaforoAmarelo'
     * @see
     */
    public String getLedSemaforoAmarelo() {
        return ledSemaforoAmarelo;
    }

    /**
     * Nome: setLedSemaforoAmarelo Registra o valor do atributo 'ledSemaforoAmarelo'.
     * @param ledSemaforoAmarelo valor do atributo led semaforo amarelo
     * @see
     */
    public void setLedSemaforoAmarelo(String ledSemaforoAmarelo) {
        this.ledSemaforoAmarelo = ledSemaforoAmarelo;
    }

    /**
     * Nome: getLedSemaforoVermelho Recupera o valor do atributo 'ledSemaforoVermelho'.
     * @return valor do atributo 'ledSemaforoVermelho'
     * @see
     */
    public String getLedSemaforoVermelho() {
        return ledSemaforoVermelho;
    }

    /**
     * Nome: setLedSemaforoVermelho Registra o valor do atributo 'ledSemaforoVermelho'.
     * @param ledSemaforoVermelho valor do atributo led semaforo vermelho
     * @see
     */
    public void setLedSemaforoVermelho(String ledSemaforoVermelho) {
        this.ledSemaforoVermelho = ledSemaforoVermelho;
    }

}
