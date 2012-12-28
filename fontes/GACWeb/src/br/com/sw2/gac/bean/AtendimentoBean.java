package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.converter.TelefoneConverter;
import br.com.sw2.gac.datamodel.ContatoDataModel;
import br.com.sw2.gac.datamodel.FormaContatoDataModel;
import br.com.sw2.gac.datamodel.OcorrenciaDataModel;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.validator.EmailValidator;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
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
public class AtendimentoBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 3822727613830737506L;

    /** Atributo contrato. */
    private ContratoVO contrato;

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

    /** Dados das pessoas para contato com o cliente. */
    private ContatoVO pessoaParaContato = new ContatoVO();

    /** Representa os campos a serem preenchidos para edição ou inclusão de nova forma de contato. */
    private FormaContatoVO formaContato = new FormaContatoVO();
    /** Atributo lista forma contato removidos. */
    private List<FormaContatoVO> listaFormaContatoRemovidos = new ArrayList<FormaContatoVO>();
    /** Atributo disabled check contratante. */
    private Boolean disabledCheckContratante = false;

    /** Atributo lista pessoas contato cliente removidos. */
    private List<ContatoVO> listaPessoasContatoClienteRemovidos = new ArrayList<ContatoVO>();

    /**
     * Construtor Padrao Instancia um novo objeto AtendimentoBean.
     */
    public AtendimentoBean() {
        this.ocorrenciaEmAndamento = new OcorrenciaVO();
        ocorrenciaEmAndamento.setTipoOcorrencia(new TipoOcorrenciaVO(TipoOcorrencia.Emergencia));

        this.contrato = (ContratoVO) getSessionAttribute("contratoAtender");
        this.ocorrenciaEmAndamento.setCliente(contrato.getCliente());

        this.telefonesContatoComCliente = new ArrayList<SelectItem>();
        for (FormaContatoVO item : this.contrato.getCliente().getListaFormaContato()) {
            if (!TipoContato.Email.getValue().equals(item.getTipoContato())) {
                SelectItem selectItem = new SelectItem();
                selectItem.setValue(item.getTelefone());
                selectItem.setLabel(new TelefoneConverter().formatarTelefone(item.getTelefone()));
                this.telefonesContatoComCliente.add(selectItem);
            }
        }

        if (null != contrato && null != this.contrato.getCliente().getListaContatos()
            && !this.contrato.getCliente().getListaContatos().isEmpty()) {
            this.contatoSelecionado = this.contrato.getCliente().getListaContatos().get(0);
        }
        this.listaTiposCorrencia = getSelectItems(TipoOcorrencia.class);
        this.contatoDataModel = new ContatoDataModel(this.contrato.getCliente().getListaContatos());
        this.ocorrenciaDataModel = new OcorrenciaDataModel(new ArrayList<OcorrenciaVO>());
        this.semafaroOff();
        this.ledSemaforoVerde = "/img/green_circle_on.png";
        this.cssBoxMensagemPrioridade = "areaVerde";
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
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Atendimento");
        return "atendimento";
    }

    /**
     * Nome: adicionarContato Adicionar contato.
     * @param event the event
     * @see
     */
    public void adicionarPessoaContato(ActionEvent event) {

        if (!this.pessoaParaContato.isContratante() && this.pessoaParaContato.getSqaChamada() == 0) {
            setFacesErrorMessage("message.contrato.sequenciachamada.validation.zero.failed");
        } else if (CollectionUtils.isEmptyOrNull(this.pessoaParaContato.getListaFormaContato())) {
            setFacesErrorMessage("message.contrato.field.formacontato.required");
        } else {
            if (null == this.pessoaParaContato.getIdContato()) {
                ContatoVO contato = new ContatoVO();
                contato
                    .setIdContato(((this.contrato.getCliente().getListaContatos().size() + 1) + -1));
                contato.setNome(this.pessoaParaContato.getNome());
                contato.setGrauParentesco(this.pessoaParaContato.getGrauParentesco());
                contato.setEndereco(this.pessoaParaContato.getEndereco());
                contato.setContratante(this.pessoaParaContato.isContratante());
                contato.setDataNascimento(this.pessoaParaContato.getDataNascimento());
                contato.setSqaChamada(this.pessoaParaContato.getSqaChamada());
                contato.setListaFormaContato(this.pessoaParaContato.getListaFormaContato());
                contato.setCrud(Crud.Create.getValue());
                this.contrato.getCliente().getListaContatos().add(contato);
                this.pessoaParaContato = new ContatoVO();
            } else {
                ContatoVO contatoOriginal = (ContatoVO) CollectionUtils.findByAttribute(
                    this.contrato.getCliente().getListaContatos(), "idContato",
                    this.pessoaParaContato.getIdContato());
                contatoOriginal.setNome(this.pessoaParaContato.getNome());
                contatoOriginal.setGrauParentesco(this.pessoaParaContato.getGrauParentesco());
                contatoOriginal.setEndereco(this.pessoaParaContato.getEndereco());
                contatoOriginal.setContratante(this.pessoaParaContato.isContratante());
                contatoOriginal.setDataNascimento(this.pessoaParaContato.getDataNascimento());
                contatoOriginal.setSqaChamada(this.pessoaParaContato.getSqaChamada());
                contatoOriginal.setListaFormaContato(this.pessoaParaContato.getListaFormaContato());
                if (this.pessoaParaContato.getIdContato() > 0) {
                    contatoOriginal.setCrud(Crud.Update.getValue());
                } else {
                    contatoOriginal.setCrud(Crud.Create.getValue());
                }
                disableEnableCheckContratante();
            }
            this.pessoaParaContato = new ContatoVO();
        }
    }

    /**
     * Nome: adicionarFormaContato Adicionar forma contato.
     * @param event the event
     * @see
     */
    public void adicionarFormaContato(ActionEvent event) {
        if (null != this.formaContato.getIdFormaContato()) {
            FormaContatoVO formaContatoOriginal = (FormaContatoVO) CollectionUtils.findByAttribute(
                this.pessoaParaContato.getListaFormaContato(), "idFormaContato",
                this.formaContato.getIdFormaContato());

            if (TipoContato.Email.getValue().equals(this.formaContato.getTipoContato())) {
                formaContatoOriginal.setTelefone("");
                formaContatoOriginal.setEmail(this.formaContato.getEmail());
            } else {
                formaContatoOriginal.setTelefone(this.formaContato.getTelefone());
                formaContatoOriginal.setEmail("");
            }
            formaContatoOriginal.setTipoContato(this.formaContato.getTipoContato());

            if (this.formaContato.getIdFormaContato() > 0) {
                formaContatoOriginal.setCrud(Crud.Update.getValue());
            } else {
                formaContatoOriginal.setCrud(Crud.Create.getValue());
            }

        } else {
            FormaContatoVO formaContato = new FormaContatoVO();
            formaContato.setTelefone(this.formaContato.getTelefone());
            formaContato.setEmail(this.formaContato.getEmail());
            formaContato.setTipoContato(this.formaContato.getTipoContato());
            formaContato
                .setIdFormaContato((this.pessoaParaContato.getListaFormaContato().size() + 1) * -1);
            formaContato.setCrud(Crud.Create.getValue());
            this.pessoaParaContato.getListaFormaContato().add(formaContato);
        }
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: novaPessoaContatoComCliente Nova pessoa contato com cliente.
     * @see
     */
    public void novaPessoaContatoComCliente() {
        this.pessoaParaContato = new ContatoVO();
    }

    /**
     * Nome: novaFormaContato Nova forma contato.
     * @param event the event
     * @see
     */
    public void novaFormaContato(ActionEvent event) {
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: editarContato Editar contato.
     * @param event the event
     * @see
     */
    public void editarPessoaContatoComCliente(ActionEvent event) {
        Integer idContato = Integer.parseInt(getRequestParameter("idContato"));
        this.pessoaParaContato = new ContatoVO((ContatoVO) CollectionUtils.findByAttribute(
            this.contrato.getCliente().getListaContatos(), "idContato", idContato));
        this.disabledCheckContratante = false;
    }

    /**
     * Nome: editarFormaContato Editar forma contato.
     * @param event the event
     * @see
     */
    public void editarFormaContato(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContato"));
        this.formaContato = new FormaContatoVO((FormaContatoVO) CollectionUtils.findByAttribute(
            this.pessoaParaContato.getListaFormaContato(), "idFormaContato", idFormaContato));
    }

    /**
     * Nome: excluirPessoaContatoCliente Excluir pessoa contato cliente.
     * @param event the event
     * @see
     */
    public void excluirPessoaContatoCliente(ActionEvent event) {
        ContatoVO remover = (ContatoVO) CollectionUtils.findByAttribute(this.contrato.getCliente()
            .getListaContatos(), "idContato", this.pessoaParaContato.getIdContato());
        remover.setCrud(Crud.Delete.getValue());
        this.listaPessoasContatoClienteRemovidos.add(remover);
        this.contrato.getCliente().getListaContatos().remove(remover);
        this.pessoaParaContato = new ContatoVO();
    }

    /**
     * Nome: excluirFormaContato Excluir forma contato.
     * @param event the event
     * @see
     */
    public void excluirFormaContato(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) CollectionUtils.findByAttribute(
            this.pessoaParaContato.getListaFormaContato(), "idFormaContato",
            this.formaContato.getIdFormaContato());
        remover.setCrud(Crud.Delete.getValue());
        this.listaFormaContatoRemovidos.add(remover);
        this.pessoaParaContato.getListaFormaContato().remove(remover);
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: disableEnableCheckContratante Disable enable check contratante.
     * @see
     */
    private void disableEnableCheckContratante() {
        // So é permitido um contratante.
        if (null == CollectionUtils.findByAttribute(this.contrato.getCliente().getListaContatos(),
            "contratante", true)) {
            disabledCheckContratante = false;
        } else {
            disabledCheckContratante = true;
        }
    }

    /**
     * Nome: validarCamposFormaContato Validar campos forma contato.
     * @param event the event
     * @see
     */
    public void validarCamposFormaContato(ComponentSystemEvent event) {
        this.getLogger().debug(
            "***** Iniciando método validarCamposFormaContato(ComponentSystemEvent event) *****");
        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UISelectOne uiSelectOne = null;
        UIInput uiTelefone = null;
        UIInput uiEmail = null;

        uiSelectOne = (UISelectOne) components.findComponent("idCmbTipoFormaContatoCliente");
        uiTelefone = (UIInput) components.findComponent("idTxtFormaContatoClienteTelefone");
        uiEmail = (UIInput) components.findComponent("idTxtFormaContatoClienteEmail");

        String valorTelefone = uiTelefone.getLocalValue().toString();
        String valorEmail = uiEmail.getLocalValue().toString();
        String valueCombo = uiSelectOne.getLocalValue().toString();

        if (TipoContato.Email.getValue().equals(valueCombo)) {
            if (StringUtil.isEmpty(valorEmail, true)) {
                setFacesErrorMessage("message.generic.field.email.required");
                fc.renderResponse();
            } else {
                EmailValidator emailValidator = new EmailValidator();
                if (!emailValidator.isEmailValido(valorEmail)) {
                    setFacesErrorMessage("message.generic.field.email.invalid");
                    fc.renderResponse();
                }
            }
        } else {
            if (StringUtil.isEmpty(valorTelefone, true)) {
                setFacesErrorMessage("message.generic.field.telefone.required");
                fc.renderResponse();
            }
        }
        this.getLogger().debug(
            "***** Finalizado método validarCamposFormaContato(ComponentSystemEvent event) *****");
    }

    /**
     * Nome: getDisabledCheckContratante Recupera o valor do atributo 'disabledCheckContratante'.
     * @return valor do atributo 'disabledCheckContratante'
     * @see
     */
    public Boolean getDisabledCheckContratante() {
        return disabledCheckContratante;
    }

    /**
     * Nome: setDisabledCheckContratante Registra o valor do atributo 'disabledCheckContratante'.
     * @param disabledCheckContratante valor do atributo disabled check contratante
     * @see
     */
    public void setDisabledCheckContratante(Boolean disabledCheckContratante) {
        this.disabledCheckContratante = disabledCheckContratante;
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

    /**
     * Nome: getPessoaParaContato Recupera o valor do atributo 'pessoaParaContato'.
     * @return valor do atributo 'pessoaParaContato'
     * @see
     */
    public ContatoVO getPessoaParaContato() {
        return pessoaParaContato;
    }

    /**
     * Nome: setPessoaParaContato Registra o valor do atributo 'pessoaParaContato'.
     * @param pessoaParaContato valor do atributo pessoa para contato
     * @see
     */
    public void setPessoaParaContato(ContatoVO pessoaParaContato) {
        this.pessoaParaContato = pessoaParaContato;
    }

    /**
     * Nome: getFormaContato Recupera o valor do atributo 'formaContato'.
     * @return valor do atributo 'formaContato'
     * @see
     */
    public FormaContatoVO getFormaContato() {
        return formaContato;
    }

    /**
     * Nome: setFormaContato Registra o valor do atributo 'formaContato'.
     * @param formaContato valor do atributo forma contato
     * @see
     */
    public void setFormaContato(FormaContatoVO formaContato) {
        this.formaContato = formaContato;
    }

}
