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

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.business.PacoteServicoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.GrauRelacao;
import br.com.sw2.gac.tools.Periodicidade;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.validator.EmailValidator;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.HorarioVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.TratamentoVO;

/**af
 * <b>Descrição : Controller da tela de contratos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class ContratoBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 4073641075943575551L;

    /** Atributo pacote servico business. */
    private PacoteServicoBusiness pacoteServicoBusiness = new PacoteServicoBusiness();

    /** Atributo contrato business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

    /** Atributo contrato. */
    private ContratoVO contrato;

    /** Representa os campos a serem preenchidos para edição ou inclusão de nova forma de contato. */
    private FormaContatoVO formaContato = new FormaContatoVO();

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

    /** Atributo filtro dispositivo. */
    private String filtroDispositivo;

    /** Atributo filtro central. */
    private String filtroCentral;

    /** Atributo filtro central. */
    private String filtroDoenca;

    /** Usado para linkar os campos de preenchimento na tela. */
    private TratamentoVO tratamento;

    /** Atributo lista doencas. */
    private DualListModel<DoencaVO> pickListDoencas;

    /** Atributo lista periodicidade. */
    private List<SelectItem> listaPeriodicidade;

    /** Atributo contato. */
    private ContatoVO contato = new ContatoVO();

    /** Atributo lista relacao. */
    private List<SelectItem> listaRelacao;

    /** Atributo lista servicos. */
    private List<SelectItem> listaServicos;

    /** Atributo horario tratamento. */
    private String horarioTratamento;

    /** Atributo save process. */
    private String saveProcess = "@this,frmContrato:idTxtIndiceTab,frmContrato:idTabContrato:idContrato";

    /** Atributo lista centrais disponiveis. */
    private List<DispositivoVO> listaCentraisDisponiveis = new ArrayList<DispositivoVO>();

    /** Atributo lista dispositivos disponiveis. */
    private List<DispositivoVO> listaDispositivosDisponiveis = new ArrayList<DispositivoVO>();

    /** Atributo lista dispositivos removidos. */
    private List<DispositivoVO> listaDispositivosRemovidos = new ArrayList<DispositivoVO>();

    /** Atributo lista forma contato cliente removidos. */
    private List<FormaContatoVO> listaFormaContatoClienteRemovidos = new ArrayList<FormaContatoVO>();

    /** Atributo lista forma contato removidos. */
    private List<FormaContatoVO> listaFormaContatoRemovidos = new ArrayList<FormaContatoVO>();

    /** Atributo lista contatos removidos. */
    private List<ContatoVO> listaContatosRemovidos = new ArrayList<ContatoVO>();

    /** Atributo lista tratamentos removidos. */
    private List<TratamentoVO> listaTratamentosRemovidos = new ArrayList<TratamentoVO>();

    /** Atributo lista horarios removidos. */
    private List<HorarioVO> listaHorariosRemovidos = new ArrayList<HorarioVO>();

    /** Atributo id dispositivo. */
    private String idDispositivo;

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

    private static final int INICIO_VALIDADE_MAXIMO_DIAS_ANTERIOR_DATA_ATUAL = 30;

    /** Atributo data minima inicio validade. */
    private Date dataMinimaInicioValidade = null;

    /**
     * Construtor Padrao Instancia um novo objeto ContratoBean.
     */
    public ContratoBean() {

        // Set Nome da tela
        setTituloCabecalho("label.contrato.view.title", true);

        this.getLogger().debug("***** Iniciando construtor da classe ContratoBean *****");
        // Determina se é uma ação de (C) CREATE/Novo ou (U) Update/Editar.
        String editNumeroContrato = (String) getHttpServLetRequest().getAttribute(
            "editNumeroContrato");
        this.indiceTabAtivo = 0;

        this.tratamento = new TratamentoVO();
        this.formaContato = new FormaContatoVO();
        this.contato = new ContatoVO();
        if (null == editNumeroContrato) {
            this.setCrud(Crud.Create.getValue());
            // Limpa campos
            this.contrato = new ContratoVO();
            this.valueBtnSalvarAvancar = "Avançar";
        } else {
            this.setCrud(Crud.Update.getValue());
            this.disabledTabClienteBase = false;
            this.disabledTabClienteDispositivo = false;
            this.disabledTabClienteDoenca = false;
            this.disabledTabClienteTratamento = false;
            this.disabledTabContato = false;
            this.disabledCheckContratante = false;
            this.contrato = this.contratoBusiness.obterDadosContrato(Integer
                .parseInt(editNumeroContrato));
            this.valueBtnSalvarAvancar = "Salvar";
            StringBuffer process = new StringBuffer();
            process.append("@this,frmContrato:idTxtIndiceTab");
            process.append(",frmContrato:idTabContrato:idContrato");
            process.append(",frmContrato:idTabContrato:idTabClienteBase");
            process.append(",:frmContrato:idTabContrato:idPgdPickList");
            this.saveProcess = process.toString();
        }

        // popular combo de serviços
        List<PacoteServicoVO> listaPacoteServicoVO = this.pacoteServicoBusiness
            .getListaPacoteServicosValidos();
        this.listaServicos = getSelectItens(listaPacoteServicoVO, "idPacote", "titulo");
        this.listaPeriodicidade = getSelectItems(Periodicidade.class);

        // Popular picklist de doenças
        this.pickListDoencas = obterPickListDoencas("@-");

        // Obter a lista do combo de rela??o (Parntesco)
        this.listaRelacao = new ArrayList<SelectItem>();
        for (GrauRelacao relacao : GrauRelacao.values()) {
            this.listaRelacao.add(new SelectItem(relacao.getValue(), relacao.name()));
        }

        this.dataMinimaInicioValidade = DateUtil.subtrairDias(new Date(), INICIO_VALIDADE_MAXIMO_DIAS_ANTERIOR_DATA_ATUAL);
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
                this.contrato.getCliente().getListaFormaContato()
                    .addAll(this.listaFormaContatoClienteRemovidos);
                this.contrato.getCliente().getListaContatos().addAll(this.listaContatosRemovidos);
                if (!CollectionUtils.isEmptyOrNull(this.listaFormaContatoRemovidos)) {
                    this.contrato.getCliente().getListaContatos().get(0).setCrud(Crud.Update.getValue());
                    this.contrato.getCliente().getListaContatos().get(0).getListaFormaContato()
                        .addAll(this.listaFormaContatoRemovidos);
                }
                this.contrato.getCliente().getListaTratamentos()
                    .addAll(this.listaTratamentosRemovidos);

                // Trata se houve alteração na lista de dispositivos e centrais.
                if (!CollectionUtils.isEmptyOrNull(this.listaDispositivosRemovidos)) {
                    for (DispositivoVO dispositivo : this.listaDispositivosRemovidos) {
                        dispositivo.setCrud(Crud.Delete.getValue());
                        this.contrato.getCliente().getListaDispositivos()
                            .addAll(this.listaDispositivosRemovidos);
                    }
                }

                // Processar as doenças selecionadas
                this.contrato.getCliente().setListaDoencas(new ArrayList<DoencaVO>());
                if (!CollectionUtils.isEmptyOrNull(this.pickListDoencas.getTarget())) {
                    this.contrato.getCliente().getListaDoencas()
                        .addAll(this.pickListDoencas.getTarget());
                }

                try {
                    this.contratoBusiness.atualizarContrato(this.contrato);
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
                    CollectionUtils.removeAll(this.contrato.getCliente().getListaDispositivos(),
                        this.listaDispositivosRemovidos);
                    // Zera as lista de itens a excluir, assim em um novo clique no salvar não fica
                    // sujeira
                    this.listaFormaContatoClienteRemovidos.clear();
                    this.listaContatosRemovidos.clear();
                    this.listaTratamentosRemovidos.clear();
                    this.listaDispositivosRemovidos.clear();
                    this.listaHorariosRemovidos.clear();
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
                this.contrato.setUsuario(getUsuarioLogado());
                this.getContrato().getCliente().setUsuario(getUsuarioLogado());
                // Se não houver ainda uma central selecionada, adiciona uma central que ja exista
                // para o
                // endereço informado
                if (CollectionUtils.isEmptyOrNull(this.contrato.getCliente().getListaCentrais())) {
                    DispositivoVO disp = this.contratoBusiness
                        .obterCentralDisponivelParaEndereco(this.getContrato().getCliente());
                    if (null != disp) {
                        this.contrato.getCliente().getListaCentrais().add(disp);
                    }

                }

            } else if (this.indiceTabAtivo == INDICE_TAB_CONTATO) {
                this.valueBtnSalvarAvancar = "Salvar";
            } else if (this.indiceTabAtivo > INDICE_TAB_CONTATO) {
                this.indiceTabAtivo = INDICE_TAB_CONTATO;

                // Seta informações do contratate atraves do contato
                ContatoVO contato = (ContatoVO) CollectionUtils.findByAttribute(this.contrato
                    .getCliente().getListaContatos(), "contratante", true);
                if (contato != null) {
                    this.contrato.setNomeContratante(contato.getNome());
                    this.contrato.setDtProxAtual(new Date());
                    // Recupera as doenças, caso existam
                    this.getLogger().debug(
                        "Qtde de doenças " + this.pickListDoencas.getTarget().size());
                    if (!CollectionUtils.isEmptyOrNull(this.pickListDoencas.getTarget())) {
                        this.getContrato().getCliente()
                            .setListaDoencas(this.pickListDoencas.getTarget());
                    }

                    this.contrato = this.contratoBusiness.gravarNovoContrato(this.contrato);
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
     * Nome: novoTratamento Novo tratamento.
     * @param event the event
     * @see
     */
    public void novoTratamento(ActionEvent event) {
        this.tratamento = new TratamentoVO();
        this.horarioTratamento = "";
        this.listaHorariosRemovidos = new ArrayList<HorarioVO>();
        this.listaTratamentosRemovidos = new ArrayList<TratamentoVO>();
    }

    /**
     * Nome: novoContato Novo contato.
     * @param event the event
     * @see
     */
    public void novoContato(ActionEvent event) {
        novoContato();
    }

    /**
     * Nome: novoContato Novo contato.
     * @see
     */
    public void novoContato() {
        this.contato = new ContatoVO();
        this.formaContato = new FormaContatoVO();
        this.listaContatosRemovidos = new ArrayList<ContatoVO>();
        this.listaFormaContatoRemovidos = new ArrayList<FormaContatoVO>();
        disableEnableCheckContratante();
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
     * Nome: novoContrato Novo contrato.
     * @return string
     * @see
     */
    public String novoContrato() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    /**
     * Nome: filtrarDispositivosSelecionaveis Filtrar dispositivos selecionaveis.
     * @param e the e
     * @see
     */
    public void filtrarDispositivosSelecionaveis(ActionEvent e) {
        this.filtrarDispositivosSelecionaveis(this.filtroDispositivo);
    }

    /**
     * Nome: filtrarDispositivosSelecionaveis Filtrar dispositivos selecionaveis.
     * @param filtro the filtro
     * @see
     */
    private void filtrarDispositivosSelecionaveis(String filtro) {
        this.getLogger()
            .debug("***** Iniciando método filtrarDispositivosSelecionaveis(...) *****");
        this.getLogger().debug("Filtro informado: " + filtro);
        this.listaDispositivosDisponiveis = this.contratoBusiness
            .obterListaDispositivosSelecionaveis(filtro);
        this.getLogger().debug(
            "***** Finalizado método filtrarDispositivosSelecionaveis(...) *****");
    }

    /**
     * Nome: filtrarCentraisSelecionaveis Filtrar centrais selecionaveis.
     * @param e the e
     * @see
     */
    public void filtrarCentraisSelecionaveis(ActionEvent e) {
        this.filtrarCentraisSelecionaveis(this.filtroCentral);
    }

    /**
     * Nome: filtrarCentraisSelecionaveis Filtrar centrais selecionaveis.
     * @param filtro the filtro
     * @see
     */
    private void filtrarCentraisSelecionaveis(String filtro) {
        this.getLogger().debug("***** Iniciando método filtrarCentraisSelecionaveis(...) *****");
        this.getLogger().debug("Filtro informado: " + filtro);
        this.listaCentraisDisponiveis = this.contratoBusiness
            .obterListaCentraisSelecionaveis(filtro);
        this.getLogger().debug("***** Finalizado método filtrarCentraisSelecionaveis(...) *****");
    }

    /**
     * Nome: adicionarTratamento Adicionar tratamento.
     * @param event the event
     * @see
     */
    public void adicionarTratamento(ActionEvent event) {

        if (null == this.tratamento.getIdTratamento()) {
            // Tratamento novo
            TratamentoVO tratamento = new TratamentoVO();
            tratamento.setCrud(Crud.Create.getValue());
            tratamento.setNomeTratamento(this.tratamento.getNomeTratamento());
            tratamento.setDescricaoTratamento(this.tratamento.getDescricaoTratamento());
            tratamento.setDataHoraInicial(this.tratamento.getDataHoraInicial());
            tratamento.setFrequencia(this.tratamento.getFrequencia());
            tratamento.setListaHorarios(this.tratamento.getListaHorarios());
            if (null == this.getContrato().getCliente().getListaTratamentos()) {
                this.getContrato().getCliente().setListaTratamentos(new ArrayList<TratamentoVO>());
            }
            tratamento.setIdTratamento(((this.getContrato().getCliente().getListaTratamentos()
                .size() + 1) * (-1)));
            this.getContrato().getCliente().getListaTratamentos().add(tratamento);

        } else {
            TratamentoVO tratamentoOriginal = (TratamentoVO) CollectionUtils.findByAttribute(this
                .getContrato().getCliente().getListaTratamentos(), "idTratamento",
                this.tratamento.getIdTratamento());
            tratamentoOriginal.setNomeTratamento(this.tratamento.getNomeTratamento());
            tratamentoOriginal.setDescricaoTratamento(this.tratamento.getDescricaoTratamento());
            tratamentoOriginal.setDataHoraInicial(this.tratamento.getDataHoraInicial());
            tratamentoOriginal.setFrequencia(this.tratamento.getFrequencia());
            tratamentoOriginal.setListaHorarios(this.tratamento.getListaHorarios());

            if (tratamentoOriginal.getIdTratamento() > 0) {
                tratamentoOriginal.setCrud(Crud.Update.getValue());
                if (!CollectionUtils.isEmptyOrNull(this.listaHorariosRemovidos)) {
                    tratamentoOriginal.getListaHorarios().addAll(listaHorariosRemovidos);
                }
            } else {
                tratamentoOriginal.setCrud(Crud.Create.getValue());
            }
        }
        this.horarioTratamento = "";
        this.tratamento = new TratamentoVO();

    }

    /**
     * Nome: adicionarHorarioTratamento Adicionar horario tratamento.
     * @param event the event
     * @see
     */
    public void adicionarHorarioTratamento(ActionEvent event) {
        if (null == this.tratamento.getListaHorarios()) {
            this.tratamento.setListaHorarios(new ArrayList<HorarioVO>());
        }
        if (this.tratamento.getListaHorarios().contains(this.horarioTratamento)) {
            setFacesErrorMessage("message.contrato.horariotratamento.duplicated");
        } else {
            HorarioVO horario = new HorarioVO(this.horarioTratamento);
            horario.setCrud("C");
            this.tratamento.getListaHorarios().add(horario);
        }
    }

    /**
     * Nome: adicionarContato Adicionar contato.
     * @param event the event
     * @see
     */
    public void adicionarContato(ActionEvent event) {
        if (CollectionUtils.isEmptyOrNull(this.contato.getListaFormaContato())) {
            setFacesErrorMessage("message.contrato.field.formacontato.required");
        } else {
            if (null == this.contato.getIdContato()) {
                ContatoVO contato = new ContatoVO();
                contato
                    .setIdContato(((this.contrato.getCliente().getListaContatos().size() + 1) + -1));
                contato.setNome(this.contato.getNome());
                contato.setGrauParentesco(this.contato.getGrauParentesco());
                contato.setEndereco(this.contato.getEndereco());
                contato.setContratante(this.contato.isContratante());
                contato.setDataNascimento(this.contato.getDataNascimento());
                contato.setSqaChamada(this.contato.getSqaChamada());
                contato.setListaFormaContato(this.contato.getListaFormaContato());
                contato.setCrud(Crud.Create.getValue());
                this.contrato.getCliente().getListaContatos().add(contato);
                novoContato();
            } else {
                ContatoVO contatoOriginal = (ContatoVO) CollectionUtils.findByAttribute(
                    this.contrato.getCliente().getListaContatos(), "idContato",
                    this.contato.getIdContato());
                contatoOriginal.setNome(this.contato.getNome());
                contatoOriginal.setGrauParentesco(this.contato.getGrauParentesco());
                contatoOriginal.setEndereco(this.contato.getEndereco());
                contatoOriginal.setContratante(this.contato.isContratante());
                contatoOriginal.setDataNascimento(this.contato.getDataNascimento());
                contatoOriginal.setSqaChamada(this.contato.getSqaChamada());
                contatoOriginal.setListaFormaContato(this.contato.getListaFormaContato());
                if (this.contato.getIdContato() > 0) {
                    contatoOriginal.setCrud(Crud.Update.getValue());
                } else {
                    contatoOriginal.setCrud(Crud.Create.getValue());
                }
                disableEnableCheckContratante();
            }
            this.contato = new ContatoVO();
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
                this.contato.getListaFormaContato(), "idFormaContato",
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
            formaContato.setIdFormaContato((this.getContato().getListaFormaContato().size() + 1)
                + -1);
            formaContato.setCrud(Crud.Create.getValue());
            this.contato.getListaFormaContato().add(formaContato);
        }
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: adicionarFormaContatoCliente Adicionar forma contato cliente.
     * @param event the event
     * @see
     */
    public void adicionarFormaContatoCliente(ActionEvent event) {
        this.getLogger().debug("***** Iniciando método adicionarFormaContato *****");
        this.getLogger().debug("Id da forma contato: " + this.formaContato.getIdFormaContato());
        if (null != this.formaContato.getIdFormaContato()) {
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
                + (-1));
            formaContato.setCrud(Crud.Create.getValue());
            this.contrato.getCliente().getListaFormaContato().add(formaContato);
        }
        this.getLogger().debug("***** Finalizado método adicionarFormaContatoCliente *****");
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: adicionarCentral Adicionar central a lista de centrais do cliente.
     * @param e the e
     * @see
     */
    public void adicionarCentral(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método adicionarCentral *****");
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(getRequestParameter("centralSelecionada"));

        if (CollectionUtils.findByAttribute(this.contrato.getCliente().getListaCentrais(),
            "idDispositivo", dispositivo.getIdDispositivo()) == null) {
            // verifica se a central possui menos de 8 dispositivos
            RequestContext reqCtx = RequestContext.getCurrentInstance();
            if (this.contratoBusiness
                .centralAceitaNovosDispositivos(dispositivo.getIdDispositivo())) {
                dispositivo.setCrud(Crud.Create.getValue());
                this.listaDispositivosRemovidos.addAll(this.contrato.getCliente()
                    .getListaCentrais());
                this.contrato.getCliente().setListaCentrais(new ArrayList<DispositivoVO>());
                this.contrato.getCliente().getListaCentrais().add(dispositivo);
                reqCtx.addCallbackParam("validationError", false);
            } else {
                reqCtx.addCallbackParam("validationError", true);
                setFacesErrorMessage("A central ja atingiu o limite máximo de pulseiras", false);
            }
        }
        this.getLogger().debug("***** Finalizado método adicionarCentral *****");
    }

    /**
     * Nome: adicionarDispositivo Adicionar dispositivo.
     * @param e the e
     * @see
     */
    public void adicionarDispositivo(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método adicionarDispositivo *****");
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(getRequestParameter("dispositivoSelecionado"));
        dispositivo.setCrud(Crud.Create.getValue());

        if (CollectionUtils.findByAttribute(this.contrato.getCliente().getListaDispositivos(),
            "idDispositivo", dispositivo.getIdDispositivo()) == null) {
            this.listaDispositivosRemovidos.addAll(this.contrato.getCliente()
                .getListaDispositivos());
            this.contrato.getCliente().setListaDispositivos(new ArrayList<DispositivoVO>());
            this.contrato.getCliente().getListaDispositivos().add(dispositivo);
        }
        this.getLogger().debug("***** Finalizado método adicionarDispositivo *****");
    }

    /**
     * Nome: editarFormaContato Editar forma contato.
     * @param event the event
     * @see
     */
    public void editarFormaContato(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContato"));
        this.formaContato = new FormaContatoVO((FormaContatoVO) CollectionUtils.findByAttribute(
            this.contato.getListaFormaContato(), "idFormaContato", idFormaContato));
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
     * Nome: editarTratamento Editar tratamento.
     * @param event the event
     * @see
     */
    public void editarTratamento(ActionEvent event) {
        Integer idTratamento = Integer.parseInt(getRequestParameter("idTratamento"));
        this.tratamento = new TratamentoVO((TratamentoVO) CollectionUtils.findByAttribute(this
            .getContrato().getCliente().getListaTratamentos(), "idTratamento", idTratamento));

    }

    /**
     * Nome: editarContato Editar contato.
     * @param event the event
     * @see
     */
    public void editarContato(ActionEvent event) {
        Integer idContato = Integer.parseInt(getRequestParameter("idContato"));
        this.contato = new ContatoVO((ContatoVO) CollectionUtils.findByAttribute(this.contrato
            .getCliente().getListaContatos(), "idContato", idContato));
        this.disabledCheckContratante = false;
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
     * Nome: excluirTratamento Excluir tratamento.
     * @param event the event
     * @see
     */
    public void excluirTratamento(ActionEvent event) {
        TratamentoVO remover = (TratamentoVO) CollectionUtils.findByAttribute(this.getContrato()
            .getCliente().getListaTratamentos(), "idTratamento", this.tratamento.getIdTratamento());
        remover.setCrud(Crud.Delete.getValue());
        this.listaTratamentosRemovidos.add(remover);
        this.getContrato().getCliente().getListaTratamentos().remove(remover);
    }

    /**
     * Nome: excluirHorarioTratamento Excluir horario tratamento.
     * @param event the event
     * @see
     */
    public void excluirHorarioTratamento(ActionEvent event) {

        HorarioVO remover = (HorarioVO) CollectionUtils.findByAttribute(
            this.tratamento.getListaHorarios(), "horaMinuto", this.horarioTratamento);
        remover.setCrud(Crud.Delete.getValue());
        this.listaHorariosRemovidos.add(remover);
        this.tratamento.getListaHorarios().remove(remover);
        this.horarioTratamento = "";
    }

    /**
     * Nome: excluirContato Excluir contato.
     * @param event the event
     * @see
     */
    public void excluirContato(ActionEvent event) {
        ContatoVO remover = (ContatoVO) CollectionUtils.findByAttribute(this.contrato.getCliente()
            .getListaContatos(), "idContato", this.contato.getIdContato());
        remover.setCrud(Crud.Delete.getValue());
        this.listaContatosRemovidos.add(remover);
        this.contrato.getCliente().getListaContatos().remove(remover);
        novoContato();
    }

    /**
     * Nome: excluirFormaContato Excluir forma contato.
     * @param event the event
     * @see
     */
    public void excluirFormaContato(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) CollectionUtils.findByAttribute(
            this.contato.getListaFormaContato(), "idFormaContato",
            this.formaContato.getIdFormaContato());
        remover.setCrud(Crud.Delete.getValue());
        this.listaFormaContatoRemovidos.add(remover);
        this.contato.getListaFormaContato().remove(remover);
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
     * Nome: obterPickListDoencas Obter pick list doencas.
     * @param e the e
     * @see
     */
    public void obterPickListDoencas(ActionEvent e) {
        this.pickListDoencas = this.obterPickListDoencas(this.filtroDoenca);
    }

    /**
     * Nome: obterPickListDoencas Obter pick list doencas.
     * @param filtro the filtro
     * @return dual list model
     * @see
     */
    private DualListModel<DoencaVO> obterPickListDoencas(String filtro) {
        this.getLogger().debug("***** Iniciando método obterPickListDoencas(String filtro) *****");

        List<DoencaVO> target = new ArrayList<DoencaVO>();
        if (null != this.pickListDoencas
            && !CollectionUtils.isEmptyOrNull(this.pickListDoencas.getTarget())) {
            target = this.pickListDoencas.getTarget();
        } else if (this.getCrud().equals(Crud.Update.getValue()) && filtro.equals("@-")) {
            target = this.getContrato().getCliente().getListaDoencas();
            filtro = "";
        }

        List<DoencaVO> source = this.contratoBusiness.obtertListaDoencas(filtro);
        // Verifica os dados do soure que ja foram selecionados e os remove do source.
        for (DoencaVO doenca : target) {
            DoencaVO doencaNoSource = (DoencaVO) CollectionUtils.findByAttribute(source,
                "codigoCID", doenca.getCodigoCID());
            if (null != doencaNoSource) {
                source.remove(doencaNoSource);
            }
        }

        this.getLogger().debug("***** Finalizado método obterPickListDoencas(String filtro) *****");
        return new DualListModel<DoencaVO>(source, target);
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

        String valorTelefone = uiTelefone.getLocalValue().toString();
        String valorEmail = uiEmail.getLocalValue().toString();
        String valueCombo = uiSelectOne.getLocalValue().toString();

        if (TipoContato.Email.getValue().equals(valueCombo)) {
            if (StringUtil.isVazio(valorEmail, true)) {
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
            if (StringUtil.isVazio(valorTelefone, true)) {
                setFacesErrorMessage("message.generic.field.telefone.required");
                fc.renderResponse();
            }
        }
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
            dadosValidos = validarFormAlterar(dadosValidos);
        }
        return dadosValidos;
    }

    /**
     * Nome: validarFormAlterar
     * Validar form alterar.
     *
     * @param dadosValidos the dados validos
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean validarFormAlterar(boolean dadosValidos) {
        if (CollectionUtils.isEmptyOrNull(this.contrato.getCliente().getListaFormaContato())) {
            setFacesErrorMessage("Não foi informado uma forma de contato com o cliente", false);
            dadosValidos = false;
        }

        if ((CollectionUtils.isEmptyOrNull(this.getContrato().getCliente()
            .getListaDispositivos()) || CollectionUtils.isEmptyOrNull(this.getContrato()
                .getCliente().getListaCentrais()))) {
            setFacesErrorMessage(
                "Não foi informado uma central ou dispositivo para o cliente !", false);
            dadosValidos = false;
        }

        if ((CollectionUtils.isEmptyOrNull(this.getContrato().getCliente().getListaDispositivos()) || CollectionUtils
            .isEmptyOrNull(this.getContrato().getCliente().getListaContatos()))) {
            setFacesErrorMessage("Não foi informado um contato para o contrato !", false);
            dadosValidos = false;
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
            && CollectionUtils.isEmptyOrNull(this.contrato.getCliente().getListaFormaContato())) {
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
     * Nome: getFiltroDispositivo Recupera o valor do atributo 'filtroDispositivo'.
     * @return valor do atributo 'filtroDispositivo'
     * @see
     */
    public String getFiltroDispositivo() {
        return filtroDispositivo;
    }

    /**
     * Nome: setFiltroDispositivo Registra o valor do atributo 'filtroDispositivo'.
     * @param filtroDispositivo valor do atributo filtro dispositivo
     * @see
     */
    public void setFiltroDispositivo(String filtroDispositivo) {
        this.filtroDispositivo = filtroDispositivo;
    }

    /**
     * Nome: getFiltroCentral Recupera o valor do atributo 'filtroCentral'.
     * @return valor do atributo 'filtroCentral'
     * @see
     */
    public String getFiltroCentral() {
        return filtroCentral;
    }

    /**
     * Nome: setFiltroCentral Registra o valor do atributo 'filtroCentral'.
     * @param filtroCentral valor do atributo filtro central
     * @see
     */
    public void setFiltroCentral(String filtroCentral) {
        this.filtroCentral = filtroCentral;
    }

    /**
     * Nome: getPickListDoencas Recupera o valor do atributo 'pickListDoencas'.
     * @return valor do atributo 'pickListDoencas'
     * @see
     */
    public DualListModel<DoencaVO> getPickListDoencas() {
        return pickListDoencas;
    }

    /**
     * Nome: setPickListDoencas Registra o valor do atributo 'pickListDoencas'.
     * @param pickListDoencas valor do atributo pick list doencas
     * @see
     */
    public void setPickListDoencas(DualListModel<DoencaVO> pickListDoencas) {
        this.pickListDoencas = pickListDoencas;
    }

    /**
     * Nome: getTratamento Recupera o valor do atributo 'tratamento'.
     * @return valor do atributo 'tratamento'
     * @see
     */
    public TratamentoVO getTratamento() {
        return tratamento;
    }

    /**
     * Nome: setTratamento Registra o valor do atributo 'tratamento'.
     * @param tratamento valor do atributo tratamento
     * @see
     */
    public void setTratamento(TratamentoVO tratamento) {
        this.tratamento = tratamento;
    }

    /**
     * Nome: getContato Recupera o valor do atributo 'contato'.
     * @return valor do atributo 'contato'
     * @see
     */
    public ContatoVO getContato() {
        return contato;
    }

    /**
     * Nome: setContato Registra o valor do atributo 'contato'.
     * @param contato valor do atributo contato
     * @see
     */
    public void setContato(ContatoVO contato) {
        this.contato = contato;
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
     * Nome: getHorarioTratamento Recupera o valor do atributo 'horarioTratamento'.
     * @return valor do atributo 'horarioTratamento'
     * @see
     */
    public String getHorarioTratamento() {
        return horarioTratamento;
    }

    /**
     * Nome: setHorarioTratamento Registra o valor do atributo 'horarioTratamento'.
     * @param horarioTratamento valor do atributo horario tratamento
     * @see
     */
    public void setHorarioTratamento(String horarioTratamento) {
        this.horarioTratamento = horarioTratamento;
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
     * Nome: getListaCentraisDisponiveis Recupera o valor do atributo 'listaCentraisDisponiveis'.
     * @return valor do atributo 'listaCentraisDisponiveis'
     * @see
     */
    public List<DispositivoVO> getListaCentraisDisponiveis() {
        return listaCentraisDisponiveis;
    }

    /**
     * Nome: setListaCentraisDisponiveis Registra o valor do atributo 'listaCentraisDisponiveis'.
     * @param listaCentraisDisponiveis valor do atributo lista centrais disponiveis
     * @see
     */
    public void setListaCentraisDisponiveis(List<DispositivoVO> listaCentraisDisponiveis) {
        this.listaCentraisDisponiveis = listaCentraisDisponiveis;
    }

    /**
     * Nome: getListaDispositivosDisponiveis Recupera o valor do atributo
     * 'listaDispositivosDisponiveis'.
     * @return valor do atributo 'listaDispositivosDisponiveis'
     * @see
     */
    public List<DispositivoVO> getListaDispositivosDisponiveis() {
        return listaDispositivosDisponiveis;
    }

    /**
     * Nome: setListaDispositivosDisponiveis Registra o valor do atributo
     * 'listaDispositivosDisponiveis'.
     * @param listaDispositivosDisponiveis valor do atributo lista dispositivos disponiveis
     * @see
     */
    public void setListaDispositivosDisponiveis(List<DispositivoVO> listaDispositivosDisponiveis) {
        this.listaDispositivosDisponiveis = listaDispositivosDisponiveis;
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getFiltroDoenca Recupera o valor do atributo 'filtroDoenca'.
     * @return valor do atributo 'filtroDoenca'
     * @see
     */
    public String getFiltroDoenca() {
        return filtroDoenca;
    }

    /**
     * Nome: setFiltroDoenca Registra o valor do atributo 'filtroDoenca'.
     * @param filtroDoenca valor do atributo filtro doenca
     * @see
     */
    public void setFiltroDoenca(String filtroDoenca) {
        this.filtroDoenca = filtroDoenca;
    }

    /**
     * Nome: getListaPeriodicidade Recupera o valor do atributo 'listaPeriodicidade'.
     * @return valor do atributo 'listaPeriodicidade'
     * @see
     */
    public List<SelectItem> getListaPeriodicidade() {
        return listaPeriodicidade;
    }

    /**
     * Nome: setListaPeriodicidade Registra o valor do atributo 'listaPeriodicidade'.
     * @param listaPeriodicidade valor do atributo lista periodicidade
     * @see
     */
    public void setListaPeriodicidade(List<SelectItem> listaPeriodicidade) {
        this.listaPeriodicidade = listaPeriodicidade;
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

    /**
     * Nome: getDataMinimaInicioValidade
     * Recupera o valor do atributo 'dataMinimaInicioValidade'.
     *
     * @return valor do atributo 'dataMinimaInicioValidade'
     * @see
     */
    public Date getDataMinimaInicioValidade() {
        return dataMinimaInicioValidade;
    }

    /**
     * Nome: setDataMinimaInicioValidade
     * Registra o valor do atributo 'dataMinimaInicioValidade'.
     *
     * @param dataMinimaInicioValidade valor do atributo data minima inicio validade
     * @see
     */
    public void setDataMinimaInicioValidade(Date dataMinimaInicioValidade) {
        this.dataMinimaInicioValidade = dataMinimaInicioValidade;
    }

}
