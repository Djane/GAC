package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectOne;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.PacoteServicoBusiness;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.validator.FormularioFormaPagamentoValidator;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.PacoteServicoVO;

/**
 * <b>Descrição: Super Classe para Managed Beans que irão manipular dados de contrato.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class BaseContratoBean extends BaseBean {

    /**
     * serial.
     */
    private static final long serialVersionUID = -2029147622661408182L;

    /** Atributo pacote servico business. */
    private PacoteServicoBusiness pacoteServicoBusiness = new PacoteServicoBusiness();

    /** Constante INICIO_VALIDADE_MAXIMO_DIAS_ANTERIOR_DATA_ATUAL. */
    private static final int INICIO_VALIDADE_MAXIMO_DIAS_ANTERIOR_DATA_ATUAL = 30;

    /** Atributo contrato. */
    private ContratoVO contrato;

    /** Atributo lista servicos. */
    private List<SelectItem> listaServicos;

    /** Atributo data minima inicio validade. */
    private Date dataMinimaInicioValidade = null;

    /** Representa os dados de uma pessoa de contato com o cliente. (Inclusao e edição) */
    private ContatoVO pessoaParaContato = new ContatoVO();

    /** Representa os campos a serem preenchidos para edição ou inclusão de nova forma de contato do cliente ou da pessoa de contato. */
    private FormaContatoVO formaContato = new FormaContatoVO();

    /** Atributo lista forma contato removidos. */
    private List<FormaContatoVO> listaFormaContatoRemovidos = new ArrayList<FormaContatoVO>();

    /** Atributo disabled check contratante. */
    private Boolean disabledCheckContratante = false;

    /** Atributo lista pessoas contato cliente removidos. */
    private List<ContatoVO> listaPessoasContatoClienteRemovidos = new ArrayList<ContatoVO>();

    /** Atributo lista forma contato cliente removidos. */
    private List<FormaContatoVO> listaFormaContatoClienteRemovidos = new ArrayList<FormaContatoVO>();

    /**
     * Construtor Padrao Instancia um novo objeto BaseContratoBean.
     */
    public BaseContratoBean() {
        this.dataMinimaInicioValidade = DateUtil.subtrairDias(new Date(),
            INICIO_VALIDADE_MAXIMO_DIAS_ANTERIOR_DATA_ATUAL);

        // popular combo com a lista de pacotes de serviços
        List<PacoteServicoVO> listaPacoteServicoVO = this.pacoteServicoBusiness
            .getListaPacoteServicosValidos();
        this.listaServicos = getSelectItens(listaPacoteServicoVO, "idPacote", "titulo");
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
    public void editarFormaContatoPessoaDeContatoDoCliente(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContato"));
        this.formaContato = new FormaContatoVO((FormaContatoVO) CollectionUtils.findByAttribute(
            this.pessoaParaContato.getListaFormaContato(), "idFormaContato", idFormaContato));
    }

    /**
     * Nome: editarFormaContato Editar forma contato.
     * @param event the event
     * @see
     */
    public void editarFormaContatoCliente(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContatoCliente"));
        this.formaContato = new FormaContatoVO((FormaContatoVO) CollectionUtils.findByAttribute(
            this.contrato.getCliente().getListaFormaContato(), "idFormaContato", idFormaContato));

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
    public void excluirFormaContatoPessoaDeContatoDoCliente(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) CollectionUtils.findByAttribute(
            this.pessoaParaContato.getListaFormaContato(), "idFormaContato",
            this.formaContato.getIdFormaContato());
        remover.setCrud(Crud.Delete.getValue());
        this.listaFormaContatoRemovidos.add(remover);
        this.pessoaParaContato.getListaFormaContato().remove(remover);
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: excluirFormaContatoCliente Excluir forma contato cliente.
     * @param event the event
     * @see
     */
    public void excluirFormaContatoCliente(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) CollectionUtils.findByAttribute(this.contrato
            .getCliente().getListaFormaContato(), "idFormaContato", this.formaContato
            .getIdFormaContato());
        remover.setCrud(Crud.Delete.getValue());
        this.listaFormaContatoClienteRemovidos.add(remover);
        this.contrato.getCliente().getListaFormaContato().remove(remover);
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
     * Nome: adicionarFormaContatoCliente
     * Adicionar forma contato cliente.
     *
     * @param event the event
     * @see
     */
    public void adicionarFormaContatoCliente(ActionEvent event) {

        if (null != this.formaContato.getIdFormaContato()) {
            /*
             *  IdFormaContato diferente de null indica que é uma forma de contato ja existente no banco
             *  ou recem inserida, porem sem estar ainda gravada no banco de dados.
             *  Qdo o  IdFormaContato for menor que 0, significa que a forma foi inserida na lista porem ainda não
             *  gravada no banco.
             */
            FormaContatoVO formaContatoOriginal = (FormaContatoVO) CollectionUtils.findByAttribute(
                this.contrato.getCliente().getListaFormaContato(), "idFormaContato",
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
            formaContato.setIdFormaContato((this.contrato.getCliente().getListaFormaContato()
                .size() + 1)
                * (-1));
            formaContato.setCrud(Crud.Create.getValue());
            this.contrato.getCliente().getListaFormaContato().add(formaContato);
        }
        this.formaContato = new FormaContatoVO();
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
     * Nome: validarPreenchimentoCamposFormaContatoCliente
     * Validar preenchimento campos forma contato cliente.
     *
     * @param event the event
     * @see
     */
    public void validarPreenchimentoCamposFormaContatoCliente(ComponentSystemEvent event) {
        UIComponent components = event.getComponent();

        UISelectOne uiSelectOne = (UISelectOne) components
            .findComponent("frmAtendimento:idCmbTipoFormaContatoCliente");
        UIInput uiTelefone = (UIInput) components.findComponent("frmAtendimento:idTxtFormaContatoClienteTelefone");
        UIInput uiEmail = (UIInput) components.findComponent("frmAtendimento:idTxtFormaContatoClienteEmail");
        new FormularioFormaPagamentoValidator(FacesContext.getCurrentInstance()).validarCamposFormaContat(uiSelectOne, uiTelefone, uiEmail);
    }


    /**
     * Nome: validarPreenchimentoCamposFormaContatoPessoaDeContatoDoCliente
     * Validar preenchimento campos forma contato pessoa de contato do cliente.
     *
     * @param event the event
     * @see
     */
    public void validarPreenchimentoCamposFormaContatoPessoaDeContatoDoCliente(ComponentSystemEvent event) {

        UIComponent components = event.getComponent();

        UISelectOne uiSelectOne = (UISelectOne) components
            .findComponent("frmAtendimento:idCmbTipoFormaContatoPessoaContatoCliente");
        UIInput uiTelefone = (UIInput) components.findComponent("frmAtendimento:idTxtFormaContatoPessoaContatoClienteTelefone");
        UIInput uiEmail = (UIInput) components.findComponent("frmAtendimento:idTxtFormaContatoPessoaContatoClienteEmail");
        new FormularioFormaPagamentoValidator(FacesContext.getCurrentInstance()).validarCamposFormaContat(uiSelectOne, uiTelefone, uiEmail);
    }

    /**
     * Nome: novaPessoaContatoComCliente Nova pessoa contato com cliente.
     * @see
     */
    public void novaPessoaContatoComCliente() {
        this.pessoaParaContato = new ContatoVO();
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
     * Nome: getPessoaParaContato
     * Recupera o valor do atributo 'pessoaParaContato'.
     *
     * @return valor do atributo 'pessoaParaContato'
     * @see
     */
    public ContatoVO getPessoaParaContato() {
        return pessoaParaContato;
    }

    /**
     * Nome: setPessoaParaContato
     * Registra o valor do atributo 'pessoaParaContato'.
     *
     * @param pessoaParaContato valor do atributo pessoa para contato
     * @see
     */
    public void setPessoaParaContato(ContatoVO pessoaParaContato) {
        this.pessoaParaContato = pessoaParaContato;
    }

    /**
     * Nome: getFormaContato
     * Recupera o valor do atributo 'formaContato'.
     *
     * @return valor do atributo 'formaContato'
     * @see
     */
    public FormaContatoVO getFormaContato() {
        return formaContato;
    }

    /**
     * Nome: setFormaContato
     * Registra o valor do atributo 'formaContato'.
     *
     * @param formaContato valor do atributo forma contato
     * @see
     */
    public void setFormaContato(FormaContatoVO formaContato) {
        this.formaContato = formaContato;
    }

    /**
     * Nome: getDisabledCheckContratante
     * Recupera o valor do atributo 'disabledCheckContratante'.
     *
     * @return valor do atributo 'disabledCheckContratante'
     * @see
     */
    public Boolean getDisabledCheckContratante() {
        return disabledCheckContratante;
    }

    /**
     * Nome: setDisabledCheckContratante
     * Registra o valor do atributo 'disabledCheckContratante'.
     *
     * @param disabledCheckContratante valor do atributo disabled check contratante
     * @see
     */
    public void setDisabledCheckContratante(Boolean disabledCheckContratante) {
        this.disabledCheckContratante = disabledCheckContratante;
    }

    /**
     * Nome: getListaServicos Recupera o valor do atributo 'listaServicos'.
     * @return valor do atributo 'listaServicos'
     * @see
     */
    public List<SelectItem> getListaServicos() {
        return listaServicos;
    }

    /**
     * Nome: setListaServicos Registra o valor do atributo 'listaServicos'.
     * @param listaServicos valor do atributo lista servicos
     * @see
     */
    public void setListaServicos(List<SelectItem> listaServicos) {
        this.listaServicos = listaServicos;
    }

    /**
     * Nome: getDataMinimaInicioValidade Recupera o valor do atributo 'dataMinimaInicioValidade'.
     * @return valor do atributo 'dataMinimaInicioValidade'
     * @see
     */
    public Date getDataMinimaInicioValidade() {
        return dataMinimaInicioValidade;
    }

    /**
     * Nome: setDataMinimaInicioValidade Registra o valor do atributo 'dataMinimaInicioValidade'.
     * @param dataMinimaInicioValidade valor do atributo data minima inicio validade
     * @see
     */
    public void setDataMinimaInicioValidade(Date dataMinimaInicioValidade) {
        this.dataMinimaInicioValidade = dataMinimaInicioValidade;
    }

}
