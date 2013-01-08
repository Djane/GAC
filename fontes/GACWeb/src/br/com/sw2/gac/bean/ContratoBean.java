package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.Date;
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

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.GrauRelacao;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.validator.FormularioFormaPagamentoValidator;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.TratamentoVO;

/**
 * <b>Descrição : Controller da tela de contratos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class ContratoBean extends BaseContratoBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 4073641075943575551L;

    /** Atributo contrato business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

    /** Atributo que indica a tab ativa na tela de contratos. */
    private Integer indiceTabAtivo = 0;

    /** Indica se a tab deve ou não ser desabilitada na tela. */
    private Boolean disabledTabClienteBase = true;

    /** Indica se a tab deve ou não ser desabilitada na tela. */
    private Boolean disabledTabClienteDispositivo = true;

    /** Indica se a tab deve ou não ser desabilitada na tela. */
    private Boolean disabledTabClienteDoenca = true;

    /** Indica se a tab deve ou não ser desabilitada na tela. */
    private Boolean disabledTabClienteTratamento = true;

    /** Indica se a tab deve ou não ser desabilitada na tela. */
    private Boolean disabledTabContato = true;

    /** Atributo disabled check contratante. */
    private Boolean disabledCheckContratante = false;

    /** Atributo lista relacao. */
    private List<SelectItem> listaRelacao;

    /** Atributo save process. */
    private String saveProcess = "@this,frmContrato:idTxtIndiceTab,frmContrato:idTabContrato:idContrato";

    /** Atributo lista forma contato removidos. */
    private List<FormaContatoVO> listaFormaContatoRemovidos = new ArrayList<FormaContatoVO>();

    /** Atributo lista contatos removidos. */
    private List<ContatoVO> listaContatosRemovidos = new ArrayList<ContatoVO>();

    /** Atributo value btn salvar avancar. */
    private String valueBtnSalvarAvancar = "Avançar";

    /** Constante INDICE_TAB_CONTRATO. */
    private static final int INDICE_TAB_CONTRATO = 0;

    /** Constante INDICE_TAB_CLIENTE. */
    private static final int INDICE_TAB_CLIENTE = 1;

    /** Constante INDICE_TAB_DISPOSITIVO. */
    private static final int INDICE_TAB_DISPOSITIVO = 2;

    /** Constante INDICE_TAB_DOENCA. */
    private static final int INDICE_TAB_DOENCA = 3;

    /** Constante INDICE_TAB_TRATAMENTO. */
    private static final int INDICE_TAB_TRATAMENTO = 4;

    /** Constante INDICE_TAB_CONTATO. */
    private static final int INDICE_TAB_CONTATO = 5;

    /**
     * Construtor Padrao Instancia um novo objeto ContratoBean.
     */
    public ContratoBean() {
        super();
        // Set Nome da tela
        setTituloCabecalho("label.contrato.view.title", true);

        this.getLogger().debug("***** Iniciando construtor da classe ContratoBean *****");
        // Determina se é uma ação de (C) CREATE/Novo ou (U) Update/Editar.
        String editNumeroContrato = (String) getHttpServLetRequest().getAttribute(
            "editNumeroContrato");
        this.indiceTabAtivo = 0;

        this.setTratamento(new TratamentoVO());
        this.setFormaContato(new FormaContatoVO());
        this.setPessoaParaContato(new ContatoVO());
        if (null == editNumeroContrato) {
            this.setCrud(Crud.Create.getValue());
            // Limpa campos
            this.setContrato(new ContratoVO());
            this.valueBtnSalvarAvancar = "Avançar";
        } else {
            this.setCrud(Crud.Update.getValue());
            this.disabledTabClienteBase = false;
            this.disabledTabClienteDispositivo = false;
            this.disabledTabClienteDoenca = false;
            this.disabledTabClienteTratamento = false;
            this.disabledTabContato = false;
            this.disabledCheckContratante = false;
            this.setContrato(this.contratoBusiness.obterDadosContrato(Integer
                .parseInt(editNumeroContrato)));
            this.valueBtnSalvarAvancar = "Salvar";
            StringBuffer process = new StringBuffer();
            process.append("@this,frmContrato:idTxtIndiceTab");
            process.append(",frmContrato:idTabContrato:idContrato");
            process.append(",frmContrato:idTabContrato:idTabClienteBase");
            process.append(",:frmContrato:idTabContrato:idPgdPickList");
            this.saveProcess = process.toString();
        }

        // Popular picklist de doenças
        this.setPickListDoencas(obterPickListDoencas("@-"));

        // Obter a lista do combo de rela??o (Parntesco)
        this.listaRelacao = new ArrayList<SelectItem>();
        for (GrauRelacao relacao : GrauRelacao.values()) {
            this.listaRelacao.add(new SelectItem(relacao.getValue(), relacao.name()));
        }

    }

    /**
     * Nome: salvarContrato Salvar contrato.
     * @param e the e
     * @see
     */
    public void salvarContrato(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método salvarContrato(...) *****");
        if (this.getCrud().equals(Crud.Create.getValue())) {
            salvarNovoContrato();
        } else {
            if (validarForm()) {
                this.getLogger().debug("***** Iniciando UPDATE de  contrato *****");

                // Prepara itens que precisam ser removidos nas listas
                this.getContrato().getCliente().getListaFormaContato()
                    .addAll(this.getListaFormaContatoClienteRemovidos());
                this.getContrato().getCliente().getListaContatos().addAll(this.listaContatosRemovidos);
                if (!CollectionUtils.isEmptyOrNull(this.listaFormaContatoRemovidos)) {
                    this.getContrato().getCliente().getListaContatos().get(0).setCrud(Crud.Update.getValue());
                    this.getContrato().getCliente().getListaContatos().get(0).getListaFormaContato()
                        .addAll(this.listaFormaContatoRemovidos);
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
                    this.contratoBusiness.atualizarContrato(this.getContrato());
                    setFacesMessage("message.contrato.save.update");
                } catch (BusinessException ex) {
                    this.getLogger().error(ex);
                    setFacesErrorMessage("message.contrato.save.failed");
                    this.getLogger().error(getMessageFromBundle("message.contrato.save.failed"));
                } finally {
                    /*
                     * Remove os itens marcados para exclusao das listas para não serem
                     * reapresentados na tela. Eles foram incluidos nessas listas somente para irem
                     * junto com o VO de contratos ate o business.
                     */
                    CollectionUtils.removeAll(this.getContrato().getCliente().getListaDispositivos(),
                        this.getListaDispositivosRemovidos());
                    // Zera as lista de itens a excluir, assim em um novo clique no salvar não fica
                    // sujeira
                    this.getListaFormaContatoClienteRemovidos().clear();
                    this.listaContatosRemovidos.clear();
                    this.getListaTratamentosRemovidos().clear();
                    this.getListaDispositivosRemovidos().clear();
                    this.getListaHorariosRemovidos().clear();
                }

            }
        }

        this.getLogger().debug("***** Finalizado método salvarContrato(...) *****");
    }

    /**
     * Nome: salvarNovoContrato Método que efetiva a gravação d eum novo contrato.
     * @see
     */
    private void salvarNovoContrato() {
        this.getLogger().debug("***** Iniciando método salvarNovoContrato() *****");
        if (validarForm()) {
            controlarFluxoTabView();
            if (this.indiceTabAtivo == INDICE_TAB_DISPOSITIVO) {
                this.getContrato().setUsuario(getUsuarioLogado());
                this.getContrato().getCliente().setUsuario(getUsuarioLogado());
                // Se não houver ainda uma central selecionada, adiciona uma central que ja exista
                // para o
                // endereço informado
                if (CollectionUtils.isEmptyOrNull(this.getContrato().getCliente().getListaCentrais())) {
                    DispositivoVO disp = this.contratoBusiness
                        .obterCentralDisponivelParaEndereco(this.getContrato().getCliente());
                    if (null != disp) {
                        this.getContrato().getCliente().getListaCentrais().add(disp);
                    }

                }

            } else if (this.indiceTabAtivo == INDICE_TAB_CONTATO) {
                this.valueBtnSalvarAvancar = "Salvar";
            } else if (this.indiceTabAtivo > INDICE_TAB_CONTATO) {
                this.indiceTabAtivo = INDICE_TAB_CONTATO;

                // Seta informações do contratate atraves do contato
                ContatoVO contato = (ContatoVO) CollectionUtils.findByAttribute(this.getContrato()
                    .getCliente().getListaContatos(), "contratante", true);
                if (contato != null) {
                    this.getContrato().getContratante().setNomeContratante(contato.getNome());
                    this.getContrato().setDtProxAtual(new Date());
                    // Recupera as doenças, caso existam
                    this.getLogger().debug(
                        "Qtde de doenças " + this.getPickListDoencas().getTarget().size());
                    if (!CollectionUtils.isEmptyOrNull(this.getPickListDoencas().getTarget())) {
                        this.getContrato().getCliente()
                            .setListaDoencas(this.getPickListDoencas().getTarget());
                    }

                    this.setContrato(this.contratoBusiness.gravarNovoContrato(this.getContrato()));
                    setFacesMessage("message.contrato.save.insert");
                } else {
                    setFacesErrorMessage("message.contrato.rule.contratante.uninformed");
                }
                this.getLogger().debug("***** Finalizado método salvarNovoContrato() *****");
            }
        }
    }

    /**
     * Nome: controlarFluxoTabView Controlar fluxo tab view, desabilitando e informando que dados
     * serão processados pelo botão salvar/avançar.
     * @see
     */
    private void controlarFluxoTabView() {
        this.indiceTabAtivo++;
        if (this.indiceTabAtivo == INDICE_TAB_CLIENTE) {
            disabledTabClienteBase = false;
            saveProcess += ",frmContrato:idTabContrato:idTabClienteBase";
        }

        if (this.indiceTabAtivo == INDICE_TAB_DISPOSITIVO) {
            disabledTabClienteDispositivo = false;
            saveProcess += "";
        }

        if (this.indiceTabAtivo == INDICE_TAB_DOENCA) {
            disabledTabClienteDoenca = false;
            saveProcess += ",:frmContrato:idTabContrato:idPgdPickList";
        }

        if (this.indiceTabAtivo == INDICE_TAB_TRATAMENTO) {
            disabledTabClienteTratamento = false;
            saveProcess += "";
        }

        if (this.indiceTabAtivo == INDICE_TAB_CONTATO) {
            disabledTabContato = false;
            saveProcess += "";
        }
    }


    /**
     * Nome: novoContrato Novo contrato.
     * @return string
     * @see
     */
    public String novoContrato() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    /**
     * Nome: adicionarFormaContatoCliente Adicionar forma contato cliente.
     * @param event the event
     * @see
     */
    public void adicionarFormaContatoCliente(ActionEvent event) {
        this.getLogger().debug("***** Iniciando método adicionarFormaContato *****");
        this.getLogger().debug("Id da forma contato: " + this.getFormaContato().getIdFormaContato());
        if (null != this.getFormaContato().getIdFormaContato()) {
            FormaContatoVO formaContatoOriginal = (FormaContatoVO) CollectionUtils.findByAttribute(
                this.getContrato().getCliente().getListaFormaContato(), "idFormaContato",
                this.getFormaContato().getIdFormaContato());

            if (TipoContato.Email.getValue().equals(this.getFormaContato().getTipoContato())) {
                formaContatoOriginal.setTelefone("");
                formaContatoOriginal.setEmail(this.getFormaContato().getEmail());
            } else {
                formaContatoOriginal.setTelefone(this.getFormaContato().getTelefone());
                formaContatoOriginal.setEmail("");
            }
            formaContatoOriginal.setTipoContato(this.getFormaContato().getTipoContato());
            if (this.getFormaContato().getIdFormaContato() > 0) {
                formaContatoOriginal.setCrud(Crud.Update.getValue());
            } else {
                formaContatoOriginal.setCrud(Crud.Create.getValue());
            }
        } else {
            FormaContatoVO formaContato = new FormaContatoVO();
            formaContato.setTelefone(this.getFormaContato().getTelefone());
            formaContato.setEmail(this.getFormaContato().getEmail());
            formaContato.setTipoContato(this.getFormaContato().getTipoContato());
            formaContato.setIdFormaContato((this.getContrato().getCliente().getListaFormaContato()
                .size() + 1)
                + (-1));
            formaContato.setCrud(Crud.Create.getValue());
            this.getContrato().getCliente().getListaFormaContato().add(formaContato);
        }
        this.getLogger().debug("***** Finalizado método adicionarFormaContatoCliente *****");
        this.setFormaContato(new FormaContatoVO());
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
        // Descobre qual a tab esta ativa para determinar que tipo de forma de contato validar
        UIInput idTxtIndiceTab = (UIInput) components.findComponent("idTxtIndiceTab");
        int valueTabEmUso = Integer.parseInt(idTxtIndiceTab.getLocalValue().toString());
        this.getLogger().debug("Tab a ser validada: " + valueTabEmUso);
        UISelectOne uiSelectOne = null;
        UIInput uiTelefone = null;
        UIInput uiEmail = null;

        if (valueTabEmUso == INDICE_TAB_CONTATO) {
            uiSelectOne = (UISelectOne) components.findComponent("idCmbTipoFormaContato");
            uiTelefone = (UIInput) components.findComponent("idTxtFormaContatoTelefone");
            uiEmail = (UIInput) components.findComponent("idTxtFormaContatoEmail");
        } else if (valueTabEmUso == INDICE_TAB_CLIENTE) {
            uiSelectOne = (UISelectOne) components.findComponent("idCmbTipoFormaContatoCliente");
            uiTelefone = (UIInput) components.findComponent("idTxtFormaContatoClienteTelefone");
            uiEmail = (UIInput) components.findComponent("idTxtFormaContatoClienteEmail");
        }

        new FormularioFormaPagamentoValidator(fc).validarCamposFormaContat(uiSelectOne, uiTelefone, uiEmail);

        this.getLogger().debug(
            "***** Finalizado método validarCamposFormaContato(ComponentSystemEvent event) *****");
    }

    /**
     * Nome: validarForm Validar os dados do form que não podem ser validados pelo primefaces.
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean validarForm() {
        boolean dadosValidos = true;

        if (this.getCrud().equals(Crud.Create.getValue())) {
            dadosValidos = validarFormIncluir(dadosValidos);
        } else {
            dadosValidos = validarIntegridadeDadosEditadosDoContrato();
        }
        return dadosValidos;
    }

    /**
     * Nome: validarFormIncluir
     * Validar form incluir.
     *
     * @param dadosValidos the dados validos
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean validarFormIncluir(boolean dadosValidos) {
        if (this.indiceTabAtivo == INDICE_TAB_CLIENTE
            && CollectionUtils.isEmptyOrNull(this.getContrato().getCliente().getListaFormaContato())) {
            setFacesErrorMessage("Não foi informado uma forma de contato com o cliente", false);
            dadosValidos = false;
        }

        if (this.indiceTabAtivo == INDICE_TAB_DISPOSITIVO
            && (CollectionUtils.isEmptyOrNull(this.getContrato().getCliente()
                .getListaDispositivos()) || CollectionUtils.isEmptyOrNull(this.getContrato()
                    .getCliente().getListaCentrais()))) {
            setFacesErrorMessage("Não foi informado uma central ou dispositivo para o cliente !",
                false);
            dadosValidos = false;
        }
        if (this.indiceTabAtivo == INDICE_TAB_CONTATO && CollectionUtils.isEmptyOrNull(this.getContrato().getCliente().getListaContatos())) {
            setFacesErrorMessage("Não foi informado um contato para o contrato !", false);
            dadosValidos = false;
        }
        return dadosValidos;
    }

    /**
     * Nome: voltar Voltar.
     * @return string
     * @see
     */
    public String voltar() {
        return MenuItem.PESQUISAR_CONTRATO.getViewID();
    }

    /**
     * Nome: getIndiceTabAtivo Recupera o valor do atributo 'indiceTabAtivo'.
     * @return valor do atributo 'indiceTabAtivo'
     * @see
     */
    public Integer getIndiceTabAtivo() {
        return indiceTabAtivo;
    }

    /**
     * Nome: setIndiceTabAtivo Registra o valor do atributo 'indiceTabAtivo'.
     * @param indiceTabAtivo valor do atributo indice tab ativo
     * @see
     */
    public void setIndiceTabAtivo(Integer indiceTabAtivo) {
        this.indiceTabAtivo = indiceTabAtivo;
    }

    /**
     * Nome: getDisabledTabClienteBase Recupera o valor do atributo 'disabledTabClienteBase'.
     * @return valor do atributo 'disabledTabClienteBase'
     * @see
     */
    public Boolean getDisabledTabClienteBase() {
        return disabledTabClienteBase;
    }

    /**
     * Nome: setDisabledTabClienteBase Registra o valor do atributo 'disabledTabClienteBase'.
     * @param disabledTabClienteBase valor do atributo disabled tab cliente base
     * @see
     */
    public void setDisabledTabClienteBase(Boolean disabledTabClienteBase) {
        this.disabledTabClienteBase = disabledTabClienteBase;
    }

    /**
     * Nome: getDisabledTabClienteDispositivo Recupera o valor do atributo
     * 'disabledTabClienteDispositivo'.
     * @return valor do atributo 'disabledTabClienteDispositivo'
     * @see
     */
    public Boolean getDisabledTabClienteDispositivo() {
        return disabledTabClienteDispositivo;
    }

    /**
     * Nome: setDisabledTabClienteDispositivo Registra o valor do atributo
     * 'disabledTabClienteDispositivo'.
     * @param disabledTabClienteDispositivo valor do atributo disabled tab cliente dispositivo
     * @see
     */
    public void setDisabledTabClienteDispositivo(Boolean disabledTabClienteDispositivo) {
        this.disabledTabClienteDispositivo = disabledTabClienteDispositivo;
    }

    /**
     * Nome: getDisabledTabClienteDoenca Recupera o valor do atributo 'disabledTabClienteDoenca'.
     * @return valor do atributo 'disabledTabClienteDoenca'
     * @see
     */
    public Boolean getDisabledTabClienteDoenca() {
        return disabledTabClienteDoenca;
    }

    /**
     * Nome: setDisabledTabClienteDoenca Registra o valor do atributo 'disabledTabClienteDoenca'.
     * @param disabledTabClienteDoenca valor do atributo disabled tab cliente doenca
     * @see
     */
    public void setDisabledTabClienteDoenca(Boolean disabledTabClienteDoenca) {
        this.disabledTabClienteDoenca = disabledTabClienteDoenca;
    }

    /**
     * Nome: getDisabledTabClienteTratamento Recupera o valor do atributo
     * 'disabledTabClienteTratamento'.
     * @return valor do atributo 'disabledTabClienteTratamento'
     * @see
     */
    public Boolean getDisabledTabClienteTratamento() {
        return disabledTabClienteTratamento;
    }

    /**
     * Nome: setDisabledTabClienteTratamento Registra o valor do atributo
     * 'disabledTabClienteTratamento'.
     * @param disabledTabClienteTratamento valor do atributo disabled tab cliente tratamento
     * @see
     */
    public void setDisabledTabClienteTratamento(Boolean disabledTabClienteTratamento) {
        this.disabledTabClienteTratamento = disabledTabClienteTratamento;
    }

    /**
     * Nome: getDisabledTabContato Recupera o valor do atributo 'disabledTabContato'.
     * @return valor do atributo 'disabledTabContato'
     * @see
     */
    public Boolean getDisabledTabContato() {
        return disabledTabContato;
    }

    /**
     * Nome: setDisabledTabContato Registra o valor do atributo 'disabledTabContato'.
     * @param disabledTabContato valor do atributo disabled tab contato
     * @see
     */
    public void setDisabledTabContato(Boolean disabledTabContato) {
        this.disabledTabContato = disabledTabContato;
    }

    /**
     * Nome: getListaRelacao Recupera o valor do atributo 'listaRelacao'.
     * @return valor do atributo 'listaRelacao'
     * @see
     */
    public List<SelectItem> getListaRelacao() {
        return listaRelacao;
    }

    /**
     * Nome: setListaRelacao Registra o valor do atributo 'listaRelacao'.
     * @param listaRelacao valor do atributo lista relacao
     * @see
     */
    public void setListaRelacao(List<SelectItem> listaRelacao) {
        this.listaRelacao = listaRelacao;
    }

    /**
     * Nome: getSaveProcess Recupera o valor do atributo 'saveProcess'.
     * @return valor do atributo 'saveProcess'
     * @see
     */
    public String getSaveProcess() {
        return saveProcess;
    }

    /**
     * Nome: setSaveProcess Registra o valor do atributo 'saveProcess'.
     * @param saveProcess valor do atributo save process
     * @see
     */
    public void setSaveProcess(String saveProcess) {
        this.saveProcess = saveProcess;
    }

    /**
     * Nome: getValueBtnSalvarAvancar Recupera o valor do atributo 'valueBtnSalvarAvancar'.
     * @return valor do atributo 'valueBtnSalvarAvancar'
     * @see
     */
    public String getValueBtnSalvarAvancar() {
        return valueBtnSalvarAvancar;
    }

    /**
     * Nome: setValueBtnSalvarAvancar Registra o valor do atributo 'valueBtnSalvarAvancar'.
     * @param valueBtnSalvarAvancar valor do atributo value btn salvar avancar
     * @see
     */
    public void setValueBtnSalvarAvancar(String valueBtnSalvarAvancar) {
        this.valueBtnSalvarAvancar = valueBtnSalvarAvancar;
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

}
