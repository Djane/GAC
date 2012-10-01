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

import org.primefaces.model.DualListModel;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.business.PacoteServicoBusiness;
import br.com.sw2.gac.tools.GrauRelacao;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.validator.EmailValidator;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.TratamentoVO;

/**
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

    /** Atributo filtro dispositivo. */
    private String filtroDispositivo;

    /** Atributo filtro central. */
    private String filtroCentral;
    // Rever
    /** Atributo lista doencas. */
    private DualListModel<DoencaVO> pickListDoencas;

    /** Atributo lista tratamentos. */
    private List<TratamentoVO> listaTratamentos;

    /** Atributo lista contatos. */
    private List<ContatoVO> listaContatos;

    /** Atributo tratamento. */
    private TratamentoVO tratamento;

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

    /** Atributo lista centrais cliente. */
    private List<DispositivoVO> listaCentraisCliente = new ArrayList<DispositivoVO>();

    /** Atributo lista dispositivos disponiveis. */
    private List<DispositivoVO> listaDispositivosDisponiveis = new ArrayList<DispositivoVO>();

    /** Atributo lista centrais cliente. */
    private List<DispositivoVO> listaDispositivosCliente = new ArrayList<DispositivoVO>();

    /** Atributo id dispositivo. */
    private String idDispositivo;

    /**
     * Construtor Padrao Instancia um novo objeto ContratoBean.
     */
    public ContratoBean() {

        super();
        // Set Nome da tela
        setTituloCabecalho("label.contrato.view.title", true);

        // Limpa campos
        this.contrato = new ContratoVO();
        this.tratamento = new TratamentoVO();
        this.contato = new ContatoVO();
        this.formaContato = new FormaContatoVO();
        this.indiceTabAtivo = 0;
        // popular combo de servi?os
        List<PacoteServicoVO> listaPacoteServicoVO = this.pacoteServicoBusiness
            .getListaPacoteServicosValidos();
        this.listaServicos = getSelectItens(listaPacoteServicoVO, "idPacote", "titulo");

        // Lista de dispositivos que podem ser selecionados
        filtrarDispositivosSelecionaveis("");

        // Lista de centrais
        filtrarCentraisSelecionaveis("");

        // Popula lista de tratamentos
        this.listaTratamentos = obterListaTratamentos();

        // Popular conattos cadastrados
        this.listaContatos = obterListaContatos();

        // Popular picklist de doen?as
        this.pickListDoencas = obterPickListDoencas();

        // Obter a lista do combo de rela??o (Parntesco)
        this.listaRelacao = new ArrayList<SelectItem>();
        for (GrauRelacao relacao : GrauRelacao.values()) {
            this.listaRelacao.add(new SelectItem(relacao.getValue(), relacao.name()));
        }

    }

    /**
     * Nome: salvarContrato Recupera os dados da tela e salva o contrato.
     * @param e the e
     * @see
     */
    public void salvarContrato(ActionEvent e) {

        controlarFluxoTabView();

        if (this.indiceTabAtivo > 1 || this.contrato.getCliente().getListaFormaContato().isEmpty()) {
            setFacesErrorMessage("Não foi informado uma forma de contato com o cliente", false);
            if (this.indiceTabAtivo == 2) {
                this.indiceTabAtivo--;
            }
        } else if (this.indiceTabAtivo == 6) {
            this.indiceTabAtivo--;
            this.getLogger().debug("***** Iniciando método salvarContrato(...) *****");
            this.getLogger().debug("********** Aba de contratos **********");
            this.getLogger().debug("CPF Contratante: " + this.getContrato().getCpfContratante());
            this.getLogger().debug("RG Contratante: " + this.getContrato().getRgContratante());
            this.getLogger().debug(
                "Data Inicio Validade: " + this.getContrato().getDtInicioValidade());
            this.getLogger().debug(
                "Data Termino Validade: " + this.getContrato().getDtFinalValidade());
            this.getLogger().debug("Data Suspensão: " + this.getContrato().getDtSuspensao());
            this.getLogger().debug(
                "Pacote Serviço:" + this.getContrato().getPacoteServico().getIdPacote());
            this.getLogger().debug("********** Aba cliente base **********");
            this.getLogger().debug("CPF Cliente: " + this.getContrato().getCliente().getCpf());
            this.getLogger().debug("RG Cliente: " + this.getContrato().getCliente().getRg());
            this.getLogger().debug("Nome Cliente: " + this.getContrato().getCliente().getNome());
            this.getLogger().debug(
                "Endereco Cliente: " + this.getContrato().getCliente().getEndereco().getEndereco());
            this.getLogger().debug(
                "Bairro Cliente: " + this.getContrato().getCliente().getEndereco().getBairro());
            this.getLogger().debug(
                "Cidade Cliente: " + this.getContrato().getCliente().getEndereco().getCidade());
            this.getLogger().debug(
                "Estado Cliente: " + this.getContrato().getCliente().getEndereco().getUf());
            this.getLogger().debug(
                "CEP Cliente: " + this.getContrato().getCliente().getEndereco().getCep());
            this.getLogger().debug("Sexo Cliente: " + this.getContrato().getCliente().getSexo());
            this.getLogger().debug(
                "Data Nascimento Cliente: " + this.getContrato().getCliente().getDataNascimento());
            this.getLogger().debug(
                "Necessidades especiais: "
                    + this.getContrato().getCliente().getNecessidadeEspecial());
            this.getLogger().debug(
                "Plano de saúde: " + this.getContrato().getCliente().getPlanoSaude());
            this.getLogger().debug("Cobertura: " + this.getContrato().getCliente().getCobertura());

            this.getLogger().debug(
                "Quantidade de contatos com o cliente: "
                    + this.getContrato().getCliente().getListaFormaContato().size());

            this.contrato.setNomeContratante("teste");
            this.contrato.setDtProxAtual(new Date());
            this.contrato.setUsuario(getUsuarioLogado());
            this.getContrato().getCliente().setUsuario(getUsuarioLogado());
            this.contratoBusiness.gravarNovoContrato(this.contrato);

        } else {
            this.getLogger().debug(
                "*************** NAO FOI POSSIVEL INICIAR GRAVAÇÃO *****************");
        }
    }

    /**
     * Nome: controlarFluxoTabView Controlar fluxo tab view, desabilitando e informando que dados
     * serão processados pelo botão salvar/avançar.
     * @see
     */
    private void controlarFluxoTabView() {
        this.indiceTabAtivo++;
        if (this.indiceTabAtivo < 1) {
            disabledTabClienteBase = true;
        } else {
            disabledTabClienteBase = false;
            saveProcess += ",frmContrato:idTabContrato:idTabClienteBase";
        }
        if (this.indiceTabAtivo < 2) {
            disabledTabClienteDispositivo = true;
        } else {
            disabledTabClienteDispositivo = false;
            saveProcess += "";
        }
        if (this.indiceTabAtivo < 3) {
            disabledTabClienteDoenca = true;
        } else {
            disabledTabClienteDoenca = false;
            saveProcess += "";
        }
        if (this.indiceTabAtivo < 4) {
            disabledTabClienteTratamento = true;
        } else {
            disabledTabClienteTratamento = false;
            saveProcess += "";
        }
        if (this.indiceTabAtivo < 5) {
            disabledTabContato = true;
        } else {
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
    }

    /**
     * Nome: novoContato Novo contato.
     * @param event the event
     * @see
     */
    public void novoContato(ActionEvent event) {
        this.contato = new ContatoVO();
        this.formaContato = new FormaContatoVO();
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
     * Nome: obterListaContatos Obter lista contatos.
     * @return list
     * @see
     */
    private List<ContatoVO> obterListaContatos() {
        List<ContatoVO> lista = null;
        return lista;
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
        TratamentoVO tratamento = new TratamentoVO();
        tratamento.setIdTratamento(this.listaTratamentos.size());
        tratamento.setNomeTratamento(this.tratamento.getNomeTratamento());
        tratamento.setDescricaoTratamento(this.tratamento.getDescricaoTratamento());
        tratamento.setDataHoraInicial(this.tratamento.getDataHoraInicial());

        this.listaTratamentos.add(tratamento);
    }

    /**
     * Nome: adicionarHorarioTratamento Adicionar horario tratamento.
     * @param event the event
     * @see
     */
    public void adicionarHorarioTratamento(ActionEvent event) {
        if (null == this.tratamento.getListaHorarios()) {
            this.tratamento.setListaHorarios(new ArrayList<String>());
        }
        this.tratamento.getListaHorarios().add(this.horarioTratamento);
    }

    /**
     * Nome: adicionarContato Adicionar contato.
     * @param event the event
     * @see
     */
    public void adicionarContato(ActionEvent event) {
        ContatoVO contato = new ContatoVO();
        contato.setIdContato(this.listaContatos.size());
        contato.setNome(this.contato.getNome());
        contato.setGrauParentesco(this.contato.getGrauParentesco());
        contato.setEndereco(this.contato.getEndereco());
        contato.setBairro(this.contato.getBairro());
        contato.setCidade(this.contato.getCidade());
        contato.setEstado(this.contato.getEstado());
        contato.setCep(this.contato.getCep());
        contato.setContratante(this.contato.isContratante());
        contato.setDataNascimento(this.contato.getDataNascimento());
        contato.setSqaChamada(this.contato.getSqaChamada());

        this.listaContatos.add(contato);
    }

    /**
     * Nome: adicionarFormaContato Adicionar forma contato.
     * @param event the event
     * @see
     */
    public void adicionarFormaContato(ActionEvent event) {
        if (null != this.formaContato.getIdFormaContato()
            && this.formaContato.getIdFormaContato() > 0) {
            Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContatoCliente"));
            FormaContatoVO formaContatoOriginal = (FormaContatoVO) findInListById(this.contrato
                .getCliente().getListaFormaContato(), "idFormaContato", idFormaContato);

            if (TipoContato.Email.getValue().equals(this.formaContato.getTipoContato())) {
                formaContatoOriginal.setTelefone("");
                formaContatoOriginal.setEmail(this.formaContato.getEmail());
            } else {
                formaContatoOriginal.setTelefone(this.formaContato.getTelefone());
                formaContatoOriginal.setEmail("");
            }
            formaContatoOriginal.setTipoContato(this.formaContato.getTipoContato());
        } else {
            FormaContatoVO formaContato = new FormaContatoVO();
            formaContato.setTelefone(this.formaContato.getTelefone());
            formaContato.setEmail(this.formaContato.getEmail());
            formaContato.setTipoContato(this.formaContato.getTipoContato());
            formaContato.setIdFormaContato(this.getContato().getListaFormaContato().size() + 1);
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
        if (null != this.formaContato.getIdFormaContato()
            && this.formaContato.getIdFormaContato() > 0) {
            FormaContatoVO formaContatoOriginal = (FormaContatoVO) findInListById(this.contrato
                .getCliente().getListaFormaContato(), "idFormaContato",
                this.formaContato.getIdFormaContato());

            if (TipoContato.Email.getValue().equals(this.formaContato.getTipoContato())) {
                formaContatoOriginal.setTelefone("");
                formaContatoOriginal.setEmail(this.formaContato.getEmail());
            } else {
                formaContatoOriginal.setTelefone(this.formaContato.getTelefone());
                formaContatoOriginal.setEmail("");
            }
            formaContatoOriginal.setTipoContato(this.formaContato.getTipoContato());
        } else {
            FormaContatoVO formaContato = new FormaContatoVO();
            formaContato.setTelefone(this.formaContato.getTelefone().replace("-", "")
                .replace("(", "").replace(")", ""));
            formaContato.setEmail(this.formaContato.getEmail());
            formaContato.setTipoContato(this.formaContato.getTipoContato());
            formaContato
                .setIdFormaContato(this.contrato.getCliente().getListaFormaContato().size() + 1);
            this.contrato.getCliente().getListaFormaContato().add(formaContato);
        }
        this.getLogger().debug("***** Finalizado método adicionarFormaContatoCliente *****");
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: adicionarCentral Adicionar central.
     * @param e the e
     * @see
     */
    public void adicionarCentral(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método adicionarCentral *****");
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(getRequestParameter("centralSelecionada"));
        this.listaCentraisCliente = new ArrayList<DispositivoVO>();
        this.listaCentraisCliente.add(dispositivo);
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
        this.listaDispositivosCliente = new ArrayList<DispositivoVO>();
        this.listaDispositivosCliente.add(dispositivo);
        this.getLogger().debug("***** Finalizado método adicionarDispositivo *****");
    }

    /**
     * Nome: editarFormaContato Editar forma contato.
     * @param event the event
     * @see
     */
    public void editarFormaContato(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContato"));
        this.formaContato = (FormaContatoVO) findInListById(this.contato.getListaFormaContato(),
            "idFormaContato", idFormaContato);
    }

    /**
     * Nome: editarFormaContato Editar forma contato.
     * @param event the event
     * @see
     */
    public void editarFormaContatoCliente(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContatoCliente"));
        this.formaContato = (FormaContatoVO) findInListById(this.contrato.getCliente()
            .getListaFormaContato(), "idFormaContato", idFormaContato);
    }

    /**
     * Nome: editarTratamento Editar tratamento.
     * @param event the event
     * @see
     */
    public void editarTratamento(ActionEvent event) {
        Integer idTratamento = Integer.parseInt(getRequestParameter("idTratamento"));
        this.tratamento = new TratamentoVO();
        this.tratamento = (TratamentoVO) findInListById(this.listaTratamentos, "idTratamento",
            idTratamento);

    }

    /**
     * Nome: editarContato Editar contato.
     * @param event the event
     * @see
     */
    public void editarContato(ActionEvent event) {
        Integer idContato = Integer.parseInt(getRequestParameter("idContato"));
        this.contato = new ContatoVO();
        this.contato = (ContatoVO) findInListById(this.listaContatos, "idContato", idContato);

    }

    /**
     * Nome: excluirTratamento Excluir tratamento.
     * @param event the event
     * @see
     */
    public void excluirTratamento(ActionEvent event) {
        TratamentoVO remover = (TratamentoVO) findInListById(this.listaTratamentos, "idTratamento",
            this.tratamento.getIdTratamento());
        this.listaTratamentos.remove(remover);
    }

    /**
     * Nome: excluirContato Excluir contato.
     * @param event the event
     * @see
     */
    public void excluirContato(ActionEvent event) {
        ContatoVO remover = (ContatoVO) findInListById(this.listaContatos, "idContato",
            this.contato.getIdContato());
        this.listaContatos.remove(remover);
    }

    /**
     * Nome: excluirFormaContato Excluir forma contato.
     * @param event the event
     * @see
     */
    public void excluirFormaContato(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) findInListById(
            this.contato.getListaFormaContato(), "idFormaContato",
            this.formaContato.getIdFormaContato());
        this.contato.getListaFormaContato().remove(remover);
    }

    /**
     * Nome: excluirFormaContatoCliente Excluir forma contato cliente.
     * @param event the event
     * @see
     */
    public void excluirFormaContatoCliente(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) findInListById(this.contrato.getCliente()
            .getListaFormaContato(), "idFormaContato", this.formaContato.getIdFormaContato());
        this.contrato.getCliente().getListaFormaContato().remove(remover);
    }

    /**
     * Nome: excluirCentralCliente Excluir central cliente.
     * @param event the event
     * @see
     */
    public void excluirCentralCliente(ActionEvent event) {
        this.listaCentraisCliente.remove((DispositivoVO) findInListById(this.listaCentraisCliente,
            "idDispositivo", this.idDispositivo));
    }

    /**
     * Nome: excluirDispositivoCliente Excluir dispositivo cliente.
     * @param event the event
     * @see
     */
    public void excluirDispositivoCliente(ActionEvent event) {
        this.listaDispositivosCliente.remove((DispositivoVO) findInListById(
            this.listaDispositivosCliente, "idDispositivo", this.idDispositivo));
    }

    /**
     * Nome: obterPickListDoencas Obter pick list doencas.
     * @return dual list model
     * @see
     */
    private DualListModel<DoencaVO> obterPickListDoencas() {

        List<DoencaVO> source = new ArrayList<DoencaVO>();
        List<DoencaVO> target = new ArrayList<DoencaVO>();
        return new DualListModel<DoencaVO>(source, target);
    }

    /**
     * Nome: obterListaTratamentos Obter lista tratamentos.
     * @return list
     * @see
     */
    private List<TratamentoVO> obterListaTratamentos() {
        List<TratamentoVO> lista = new ArrayList<TratamentoVO>();
        return lista;
    }

    /**
     * Nome: validarCamposFormaContato Validar campos forma contato.
     * @param event the event
     * @see
     */
    public void validarCamposFormaContatoCliente(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();

        UISelectOne uiSelectOne = (UISelectOne) components
            .findComponent("idCmbTipoFormaContatoCliente");
        UIInput uiText1 = (UIInput) components.findComponent("idTxtFormaContatoClienteTelefone");
        UIInput uiText2 = (UIInput) components.findComponent("idTxtFormaContatoClienteEmail");
        String valueCombo = uiSelectOne.getLocalValue().toString();
        String valorTelefone = uiText1.getLocalValue().toString();
        String valorEmail = uiText2.getLocalValue().toString();

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
    }

    /**
     * Nome: validarCamposFormaContato Validar campos forma contato.
     * @param event the event
     * @see
     */
    public void validarCamposFormaContato(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();
        UIComponent components = event.getComponent();
        UIInput uiText1 = (UIInput) components.findComponent("idTxtFormaContatoTelefone");
        UIInput uiText2 = (UIInput) components.findComponent("idTxtFormaContatoEmail");
        String valueUiText1 = uiText1.getLocalValue().toString();
        String valueUiText2 = uiText2.getLocalValue().toString();

        if ((null == valueUiText1.trim() || "".equals(valueUiText1.trim()))
            && (null == valueUiText2.trim() || "".equals(valueUiText2.trim()))) {
            setFacesMessage("message.contrato.field.telefoneemail.required");
            fc.renderResponse();
        }

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
     * Nome: getListaTratamentos Recupera o valor do atributo 'listaTratamentos'.
     * @return valor do atributo 'listaTratamentos'
     * @see
     */
    public List<TratamentoVO> getListaTratamentos() {
        return listaTratamentos;
    }

    /**
     * Nome: setListaTratamentos Registra o valor do atributo 'listaTratamentos'.
     * @param listaTratamentos valor do atributo lista tratamentos
     * @see
     */
    public void setListaTratamentos(List<TratamentoVO> listaTratamentos) {
        this.listaTratamentos = listaTratamentos;
    }

    /**
     * Nome: getListaContatos Recupera o valor do atributo 'listaContatos'.
     * @return valor do atributo 'listaContatos'
     * @see
     */
    public List<ContatoVO> getListaContatos() {
        return listaContatos;
    }

    /**
     * Nome: setListaContatos Registra o valor do atributo 'listaContatos'.
     * @param listaContatos valor do atributo lista contatos
     * @see
     */
    public void setListaContatos(List<ContatoVO> listaContatos) {
        this.listaContatos = listaContatos;
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
     * Nome: getListaCentraisCliente Recupera o valor do atributo 'listaCentraisCliente'.
     * @return valor do atributo 'listaCentraisCliente'
     * @see
     */
    public List<DispositivoVO> getListaCentraisCliente() {
        return listaCentraisCliente;
    }

    /**
     * Nome: setListaCentraisCliente Registra o valor do atributo 'listaCentraisCliente'.
     * @param listaCentraisCliente valor do atributo lista centrais cliente
     * @see
     */
    public void setListaCentraisCliente(List<DispositivoVO> listaCentraisCliente) {
        this.listaCentraisCliente = listaCentraisCliente;
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
     * Nome: getListaDispositivosCliente Recupera o valor do atributo 'listaDispositivosCliente'.
     * @return valor do atributo 'listaDispositivosCliente'
     * @see
     */
    public List<DispositivoVO> getListaDispositivosCliente() {
        return listaDispositivosCliente;
    }

    /**
     * Nome: setListaDispositivosCliente Registra o valor do atributo 'listaDispositivosCliente'.
     * @param listaDispositivosCliente valor do atributo lista dispositivos cliente
     * @see
     */
    public void setListaDispositivosCliente(List<DispositivoVO> listaDispositivosCliente) {
        this.listaDispositivosCliente = listaDispositivosCliente;
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

}
