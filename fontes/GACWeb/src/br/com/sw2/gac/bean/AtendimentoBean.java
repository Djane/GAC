package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.converter.TelefoneConverter;
import br.com.sw2.gac.datamodel.ContatoDataModel;
import br.com.sw2.gac.datamodel.FormaContatoDataModel;
import br.com.sw2.gac.datamodel.OcorrenciaDataModel;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.TipoOcorrenciaVO;

/**
 * <b>Descrição: Controller da tela de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class AtendimentoBean extends BaseContratoBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 3822727613830737506L;

    /** Atributo lista historico ocorrencia. */
    private List<OcorrenciaVO> listaHistoricoOcorrencia;

    /** Atributo lista tipos correncia. */
    private List<SelectItem> listaTiposCorrencia;

    /** Atributo contato data model. */
    private ContatoDataModel contatoDataModel;

    /** Atributo contato data model. */
    private FormaContatoDataModel formaContatoDataModel;

    /** Atributo contato selecionado. */
    private ContatoVO contatoSelecionado;

    /** Atributo contato data model. */
    private OcorrenciaDataModel ocorrenciaDataModel;

    /** Atributo ocorrencia selecionada. */
    private OcorrenciaVO ocorrenciaSelecionada;

    /** Atributo ocorrencia em andamento. */
    private OcorrenciaVO ocorrenciaEmAndamento;

    /** Atributo led semaforo verde. */
    private String ledSemaforoVerde = "/img/green_circle_off.png";

    /** Atributo led semaforo amarelo. */
    private String ledSemaforoAmarelo = "/img/yellow_circle_off.png";

    /** Atributo led semaforo vermelho. */
    private String ledSemaforoVermelho = "/img/red_circle_off.png";

    /** Atributo css box mensagem prioridade. */
    private String cssBoxMensagemPrioridade = "areaAmarela";

    /** Atributo telefones contato com cliente. */
    private List<SelectItem> telefonesContatoComCliente = null;

    /**
     * Construtor Padrao Instancia um novo objeto AtendimentoBean.
     */
    public AtendimentoBean() {
        super();
        this.ocorrenciaEmAndamento = new OcorrenciaVO();
        ocorrenciaEmAndamento.setTipoOcorrencia(new TipoOcorrenciaVO(TipoOcorrencia.Emergencia));

        this.setContrato((ContratoVO) getSessionAttribute("contratoAtender"));
        this.ocorrenciaEmAndamento.setCliente(this.getContrato().getCliente());

        this.telefonesContatoComCliente = new ArrayList<SelectItem>();
        for (FormaContatoVO item : this.getContrato().getCliente().getListaFormaContato()) {
            if (!TipoContato.Email.getValue().equals(item.getTipoContato())) {
                SelectItem selectItem = new SelectItem();
                selectItem.setValue(item.getTelefone());
                selectItem.setLabel(new TelefoneConverter().formatarTelefone(item.getTelefone()));
                this.telefonesContatoComCliente.add(selectItem);
            }
        }

        this.listaTiposCorrencia = getSelectItems(TipoOcorrencia.class);
        if (null != this.getContrato() && null != this.getContrato().getCliente().getListaContatos()
            && !this.getContrato().getCliente().getListaContatos().isEmpty()) {
            this.contatoSelecionado = this.getContrato().getCliente().getListaContatos().get(0);
        }
        this.contatoDataModel = new ContatoDataModel(this.getContrato().getCliente()
            .getListaContatos());
        if (this.getContrato().getCliente().getListaContatos().size() > 0) {
            this.formaContatoDataModel = new FormaContatoDataModel(
                this.contatoSelecionado.getListaFormaContato());
        }

        this.ocorrenciaDataModel = new OcorrenciaDataModel(new ArrayList<OcorrenciaVO>());
        this.semafaroOff();
        this.ledSemaforoVerde = "/img/green_circle_on.png";
        this.cssBoxMensagemPrioridade = "areaVerde";

        // Popular picklist de doenças
        this.setPickListDoencas(obterPickListDoencas("@-"));


    }

    /**
     * Nome: atualizarGradeFormaContato Atualizar grade forma contato.
     * @see
     */
    public void atualizarGradeFormaContato() {

        this.formaContatoDataModel = new FormaContatoDataModel(
            this.contatoSelecionado.getListaFormaContato());

    }

    /**
     * Nome: semafaroOff Semafaro off.
     * @see
     */
    private void semafaroOff() {
        this.ledSemaforoVerde = "/img/green_circle_off.png";
        this.ledSemaforoAmarelo = "/img/yellow_circle_off.png";
        this.ledSemaforoVermelho = "/img/red_circle_off.png";
        this.cssBoxMensagemPrioridade = "areaBranca";
    }

    /**
     * Nome: mudarPrioridadeVerde Mudar prioridade verde.
     * @param event the event
     * @see
     */
    public void mudarPrioridadeVerde(ActionEvent event) {

        if (this.ledSemaforoVerde.equals("/img/green_circle_on.png")) {
            this.semafaroOff();
            this.ledSemaforoVerde = "/img/green_circle_off.png";
        } else {
            this.semafaroOff();
            this.ledSemaforoVerde = "/img/green_circle_on.png";
            this.cssBoxMensagemPrioridade = "areaVerde";

        }
    }

    /**
     * Nome: mudarPrioridadeAmarelo Mudar prioridade amarelo.
     * @param event the event
     * @see
     */
    public void mudarPrioridadeAmarelo(ActionEvent event) {

        if (this.ledSemaforoAmarelo.equals("/img/yellow_circle_on.png")) {
            this.semafaroOff();
            this.ledSemaforoAmarelo = "/img/yellow_circle_off.png";
        } else {
            this.semafaroOff();
            this.ledSemaforoAmarelo = "/img/yellow_circle_on.png";
            this.cssBoxMensagemPrioridade = "areaAmarela";
        }
    }

    /**
     * Nome: mudarPrioridadeVermelho Mudar prioridade vermelho.
     * @param event the event
     * @see
     */
    public void mudarPrioridadeVermelho(ActionEvent event) {

        if (this.ledSemaforoVermelho.equals("/img/red_circle_on.png")) {
            this.semafaroOff();
            this.ledSemaforoVermelho = "/img/red_circle_off.png";
        } else {
            this.semafaroOff();
            this.ledSemaforoVermelho = "/img/red_circle_on.png";
            this.cssBoxMensagemPrioridade = "areaVermelha";
        }
    }

    /**
     * Nome: salvarDadosPessoasContatoDoCliente
     * Salvar dados pessoas contato do cliente.
     *
     * @param e the e
     * @see
     */
    public void salvarDadosPessoasContatoDoCliente(ActionEvent e) {

        this.getContrato().getCliente().getListaContatos()
        .addAll(this.getListaPessoasContatoClienteRemovidos());

        if (!CollectionUtils.isEmptyOrNull(this.getListaFormaContatoRemovidos())) {
            this.getContrato().getCliente().getListaContatos().get(0).setCrud(Crud.Update.getValue());
            this.getContrato().getCliente().getListaContatos().get(0).getListaFormaContato()
                .addAll(this.getListaFormaContatoRemovidos());
        }

        try {
            this.getContratoBusiness().atualizarDadosListaPessoasDeContatoDoCliente(this.getContrato());
            this.contatoDataModel = new ContatoDataModel(this.getContrato().getCliente().getListaContatos());
            if (this.getContrato().getCliente().getListaContatos().size() > 0) {
                this.contatoSelecionado = this.getContrato().getCliente().getListaContatos().get(0);
                this.formaContatoDataModel =  new FormaContatoDataModel(this.contatoSelecionado.getListaFormaContato());
            }
            setFacesMessage("message.contrato.save.contato.sucess");

        } catch (BusinessException ex) {
            this.getLogger().error(ex);
            setFacesErrorMessage("message.contrato.save.contato.error");
            this.getLogger().error(getMessageFromBundle("message.contrato.save.contato.error"));
        } finally {
            this.getListaPessoasContatoClienteRemovidos().clear();
        }

        this.getListaFormaContatoRemovidos().clear();
    }

    /**
     * Nome: salvarDadosContrato
     * Salvar dados contrato.
     *
     * @param e the e
     * @see
     */
    public void salvarDadosContrato(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método salvarDadosContrato(ActionEvent e) *****");

        if (validarIntegridadeDadosEditadosDoContrato()) {
            // Prepara itens que precisam ser removidos nas listas
            this.getContrato().getCliente().getListaFormaContato()
                .addAll(this.getListaFormaContatoClienteRemovidos());
            this.getContrato().getCliente().getListaContatos()
                .addAll(this.getListaPessoasContatoClienteRemovidos());

            if (!CollectionUtils.isEmptyOrNull(this.getListaFormaContatoRemovidos())) {
                this.getContrato().getCliente().getListaContatos().get(0)
                    .setCrud(Crud.Update.getValue());
                this.getContrato().getCliente().getListaContatos().get(0).getListaFormaContato()
                    .addAll(this.getListaFormaContatoRemovidos());
            }

            this.getContrato().getCliente().getListaTratamentos()
                .addAll(this.getListaTratamentosRemovidos());

            // Trata se houve alteração na lista de dispositivos e centrais.
            if (!CollectionUtils.isEmptyOrNull(this.getListaDispositivosRemovidos())) {
                for (DispositivoVO dispositivo : this.getListaDispositivosRemovidos()) {
                    dispositivo.setCrud(Crud.Delete.getValue());
                    this.getContrato().getCliente().getListaDispositivos()
                        .addAll(this.getListaDispositivosRemovidos());
                }
            }

            // Processar as doenças selecionadas
            this.getContrato().getCliente().setListaDoencas(new ArrayList<DoencaVO>());
            if (!CollectionUtils.isEmptyOrNull(this.getPickListDoencas().getTarget())) {
                this.getContrato().getCliente().getListaDoencas()
                    .addAll(this.getPickListDoencas().getTarget());
            }

            try {
                this.getContratoBusiness().atualizarContrato(this.getContrato());
                setFacesMessage("message.contrato.save.update");
            } catch (BusinessException ex) {
                this.getLogger().error(ex);
                setFacesErrorMessage("message.contrato.save.failed");
                this.getLogger().error(getMessageFromBundle("message.contrato.save.failed"));
            } finally {
                /*
                 * Remove os itens marcados para exclusao das listas para não serem reapresentados
                 * na tela. Eles foram incluidos nessas listas somente para irem junto com o VO de
                 * contratos ate o business.
                 */
                CollectionUtils.removeAll(this.getContrato().getCliente().getListaDispositivos(),
                    this.getListaDispositivosRemovidos());
                // Zera as lista de itens a excluir, assim em um novo clique no salvar não fica
                // sujeira
                this.getListaFormaContatoClienteRemovidos().clear();
                this.getListaPessoasContatoClienteRemovidos().clear();
                this.getListaTratamentosRemovidos().clear();
                this.getListaDispositivosRemovidos().clear();
                this.getListaHorariosRemovidos().clear();
            }
        }
        this.getLogger().debug("***** Finalizado método salvarDadosContrato(ActionEvent e) *****");
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

    /**
     * Nome: getFormaContatoDataModel Recupera o valor do atributo 'formaContatoDataModel'.
     * @return valor do atributo 'formaContatoDataModel'
     * @see
     */
    public FormaContatoDataModel getFormaContatoDataModel() {
        return formaContatoDataModel;
    }

    /**
     * Nome: setFormaContatoDataModel Registra o valor do atributo 'formaContatoDataModel'.
     * @param formaContatoDataModel valor do atributo forma contato data model
     * @see
     */
    public void setFormaContatoDataModel(FormaContatoDataModel formaContatoDataModel) {
        this.formaContatoDataModel = formaContatoDataModel;
    }

    /**
     * Nome: getCssBoxMensagemPrioridade Recupera o valor do atributo 'cssBoxMensagemPrioridade'.
     * @return valor do atributo 'cssBoxMensagemPrioridade'
     * @see
     */
    public String getCssBoxMensagemPrioridade() {
        return cssBoxMensagemPrioridade;
    }

    /**
     * Nome: setCssBoxMensagemPrioridade Registra o valor do atributo 'cssBoxMensagemPrioridade'.
     * @param cssBoxMensagemPrioridade valor do atributo css box mensagem prioridade
     * @see
     */
    public void setCssBoxMensagemPrioridade(String cssBoxMensagemPrioridade) {
        this.cssBoxMensagemPrioridade = cssBoxMensagemPrioridade;
    }

    /**
     * Nome: getTelefonesContatoComCliente Recupera o valor do atributo
     * 'telefonesContatoComCliente'.
     * @return valor do atributo 'telefonesContatoComCliente'
     * @see
     */
    public List<SelectItem> getTelefonesContatoComCliente() {
        return telefonesContatoComCliente;
    }

    /**
     * Nome: setTelefonesContatoComCliente Registra o valor do atributo
     * 'telefonesContatoComCliente'.
     * @param telefonesContatoComCliente valor do atributo telefones contato com cliente
     * @see
     */
    public void setTelefonesContatoComCliente(List<SelectItem> telefonesContatoComCliente) {
        this.telefonesContatoComCliente = telefonesContatoComCliente;
    }

}
