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

import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.business.PacoteServicoBusiness;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.Periodicidade;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.validator.FormularioFormaPagamentoValidator;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.HorarioVO;
import br.com.sw2.gac.vo.PacoteServicoVO;
import br.com.sw2.gac.vo.TratamentoVO;

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

    /** Atributo contrato business. */
    private ContratoBusiness contratoBusiness = new ContratoBusiness();

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

    /**
     * Representa os campos a serem preenchidos para edição ou inclusão de nova forma de contato do
     * cliente ou da pessoa de contato.
     */
    private FormaContatoVO formaContato = new FormaContatoVO();

    /** Atributo lista forma contato removidos. */
    private List<FormaContatoVO> listaFormaContatoRemovidos = new ArrayList<FormaContatoVO>();

    /** Atributo disabled check contratante. */
    private Boolean disabledCheckContratante = false;

    /** Atributo lista pessoas contato cliente removidos. */
    private List<ContatoVO> listaPessoasContatoClienteRemovidos = new ArrayList<ContatoVO>();

    /** Atributo lista forma contato cliente removidos. */
    private List<FormaContatoVO> listaFormaContatoClienteRemovidos = new ArrayList<FormaContatoVO>();

    /** Atributo lista doencas. */
    private DualListModel<DoencaVO> pickListDoencas;

    /** Atributo filtro central. */
    private String filtroDoenca;

    /** Usado para linkar os campos de preenchimento na tela. */
    private TratamentoVO tratamento;

    /** Atributo lista periodicidade. */
    private List<SelectItem> listaPeriodicidade;

    /** Atributo lista horarios removidos. */
    private List<HorarioVO> listaHorariosRemovidos = new ArrayList<HorarioVO>();

    /** Atributo horario tratamento. */
    private String horarioTratamento;

    /** Atributo lista tratamentos removidos. */
    private List<TratamentoVO> listaTratamentosRemovidos = new ArrayList<TratamentoVO>();

    /** Atributo id dispositivo. */
    private String idDispositivo;

    /** Atributo filtro dispositivo. */
    private String filtroDispositivo;

    /** Atributo lista dispositivos disponiveis. */
    private List<DispositivoVO> listaDispositivosDisponiveis = new ArrayList<DispositivoVO>();

    /** Atributo lista centrais disponiveis. */
    private List<DispositivoVO> listaCentraisDisponiveis = new ArrayList<DispositivoVO>();

    /** Atributo lista dispositivos removidos. */
    private List<DispositivoVO> listaDispositivosRemovidos = new ArrayList<DispositivoVO>();

    /** Atributo filtro central. */
    private String filtroCentral;

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
        this.tratamento = new TratamentoVO();
        this.listaPeriodicidade = getSelectItems(Periodicidade.class);
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
     * Nome: novoTratamento Novo tratamento.
     * @param event the event
     * @see
     */
    public void novoTratamento(ActionEvent event) {
        this.setTratamento(new TratamentoVO());
        this.setHorarioTratamento("");
        this.setListaHorariosRemovidos(new ArrayList<HorarioVO>());
        this.listaTratamentosRemovidos = new ArrayList<TratamentoVO>();
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
     * Nome: excluirTratamento Excluir tratamento.
     * @param event the event
     * @see
     */
    public void excluirTratamento(ActionEvent event) {
        TratamentoVO remover = (TratamentoVO) CollectionUtils.findByAttribute(this.getContrato()
            .getCliente().getListaTratamentos(), "idTratamento", this.getTratamento()
                .getIdTratamento());
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

        HorarioVO remover = (HorarioVO) CollectionUtils.findByAttribute(this.getTratamento()
            .getListaHorarios(), "horaMinuto", this.getHorarioTratamento());
        remover.setCrud(Crud.Delete.getValue());
        this.getListaHorariosRemovidos().add(remover);
        this.getTratamento().getListaHorarios().remove(remover);
        this.setHorarioTratamento("");
    }

    /**
     * Nome: excluirCentralCliente Excluir central cliente.
     * @param event the event
     * @see
     */
    public void excluirCentralCliente(ActionEvent event) {
        this.contrato
            .getCliente()
            .getListaCentrais()
            .remove(
                (DispositivoVO) CollectionUtils.findByAttribute(this.contrato.getCliente()
                    .getListaCentrais(), "idDispositivo", this.idDispositivo));
    }

    /**
     * Nome: excluirDispositivoCliente Excluir dispositivo cliente.
     * @param event the event
     * @see
     */
    public void excluirDispositivoCliente(ActionEvent event) {
        this.contrato
            .getCliente()
            .getListaDispositivos()
            .remove(
                (DispositivoVO) CollectionUtils.findByAttribute(this.contrato.getCliente()
                    .getListaDispositivos(), "idDispositivo", this.idDispositivo));
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
     * Nome: adicionarFormaContatoCliente Adicionar forma contato cliente.
     * @param event the event
     * @see
     */
    public void adicionarFormaContatoCliente(ActionEvent event) {

        if (null != this.formaContato.getIdFormaContato()) {
            /*
             * IdFormaContato diferente de null indica que é uma forma de contato ja existente no
             * banco ou recem inserida, porem sem estar ainda gravada no banco de dados. Qdo o
             * IdFormaContato for menor que 0, significa que a forma foi inserida na lista porem
             * ainda não gravada no banco.
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

        if (validarDadosPreenchidosPessoaDeContatoDoCliente()) {
            if (null == this.pessoaParaContato.getIdContato()) {
                ContatoVO contato = new ContatoVO();
                contato
                    .setIdContato(((this.contrato.getCliente().getListaContatos().size() + 1) * -1));
                contato.setNome(this.pessoaParaContato.getNome());
                contato.setGrauParentesco(this.pessoaParaContato.getGrauParentesco());
                contato.setEndereco(this.pessoaParaContato.getEndereco());
                contato.setContratante(this.pessoaParaContato.isContratante());
                contato.setDataNascimento(this.pessoaParaContato.getDataNascimento());
                contato.setSqaChamada(this.pessoaParaContato.getSqaChamada());
                contato.setListaFormaContato(this.pessoaParaContato.getListaFormaContato());
                contato.setCrud(Crud.Create.getValue());
                contato.setLogin(getUsuarioLogado().getLogin());
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
     * Nome: validarDadosPreenchidosPessoaDeContatoDoCliente Validar dados preenchidos pessoa de
     * contato do cliente.
     * @return true, se sucesso, senão false
     * @see
     */
    protected boolean validarDadosPreenchidosPessoaDeContatoDoCliente() {

        boolean valid = true;

        ContatoVO contato = (ContatoVO) CollectionUtils.findByAttribute(contrato.getCliente()
            .getListaContatos(), "contratante", true);

        if (null != contato) {
            valid = false;
            setFacesErrorMessage("message.contrato.sequenciachamada.validation.duplicated");
        }

        if (null != CollectionUtils.findByAttribute(this.contrato.getCliente().getListaContatos(),
            "sqaChamada", this.pessoaParaContato.getSqaChamada()) && null == this.pessoaParaContato.getIdContato()) {
            setFacesErrorMessage("message.contrato.sequenciachamada.validation.duplicated");
            valid = false;
        }

        if (!this.pessoaParaContato.isContratante() && this.pessoaParaContato.getSqaChamada() == 0) {
            setFacesErrorMessage("message.contrato.sequenciachamada.validation.zero.failed");
            valid = false;
        }

        if (CollectionUtils.isEmptyOrNull(this.pessoaParaContato.getListaFormaContato())) {
            setFacesErrorMessage("message.contrato.field.formacontato.required");
            valid = false;
        }

        return valid;
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
     * Nome: adicionarTratamento Adicionar tratamento.
     * @param event the event
     * @see
     */
    public void adicionarTratamento(ActionEvent event) {

        if (validarDadosTratamento()) {
            if (null == this.getTratamento().getIdTratamento()) {
                // Tratamento novo
                TratamentoVO tratamento = new TratamentoVO();
                tratamento.setCrud(Crud.Create.getValue());
                tratamento.setNomeTratamento(this.getTratamento().getNomeTratamento());
                tratamento.setDescricaoTratamento(this.getTratamento().getDescricaoTratamento());
                tratamento.setDataHoraInicial(this.getTratamento().getDataHoraInicial());
                tratamento.setFrequencia(this.getTratamento().getFrequencia());
                tratamento.setListaHorarios(this.getTratamento().getListaHorarios());
                if (null == this.getContrato().getCliente().getListaTratamentos()) {
                    this.getContrato().getCliente()
                        .setListaTratamentos(new ArrayList<TratamentoVO>());
                }
                tratamento.setIdTratamento(((this.getContrato().getCliente().getListaTratamentos()
                    .size() + 1) * (-1)));
                this.getContrato().getCliente().getListaTratamentos().add(tratamento);

            } else {
                TratamentoVO tratamentoOriginal = (TratamentoVO) CollectionUtils.findByAttribute(
                    this.getContrato().getCliente().getListaTratamentos(), "idTratamento", this
                        .getTratamento().getIdTratamento());
                tratamentoOriginal.setNomeTratamento(this.getTratamento().getNomeTratamento());
                tratamentoOriginal.setDescricaoTratamento(this.getTratamento()
                    .getDescricaoTratamento());
                tratamentoOriginal.setDataHoraInicial(this.getTratamento().getDataHoraInicial());
                tratamentoOriginal.setFrequencia(this.getTratamento().getFrequencia());
                tratamentoOriginal.setListaHorarios(this.getTratamento().getListaHorarios());

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
            this.setTratamento(new TratamentoVO());
        }
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
            this.listaDispositivosRemovidos.addAll(this.getContrato().getCliente()
                .getListaDispositivos());
            this.contrato.getCliente().setListaDispositivos(new ArrayList<DispositivoVO>());
            this.contrato.getCliente().getListaDispositivos().add(dispositivo);
        }
        this.getLogger().debug("***** Finalizado método adicionarDispositivo *****");
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

        if (CollectionUtils.findByAttribute(this.getContrato().getCliente().getListaCentrais(),
            "idDispositivo", dispositivo.getIdDispositivo()) == null) {
            // verifica se a central possui menos de 8 dispositivos
            RequestContext reqCtx = RequestContext.getCurrentInstance();
            if (this.contratoBusiness
                .centralAceitaNovosDispositivos(dispositivo.getIdDispositivo())) {
                dispositivo.setCrud(Crud.Create.getValue());
                this.listaDispositivosRemovidos.addAll(this.getContrato().getCliente()
                    .getListaCentrais());
                this.getContrato().getCliente().setListaCentrais(new ArrayList<DispositivoVO>());
                this.getContrato().getCliente().getListaCentrais().add(dispositivo);
                reqCtx.addCallbackParam("validationError", false);
            } else {
                reqCtx.addCallbackParam("validationError", true);
                setFacesErrorMessage("A central ja atingiu o limite máximo de pulseiras", false);
            }
        }
        this.getLogger().debug("***** Finalizado método adicionarCentral *****");
    }

    /**
     * Nome: adicionarHorarioTratamento Adicionar horario tratamento.
     * @param event the event
     * @see
     */
    public void adicionarHorarioTratamento(ActionEvent event) {
        if (null == this.getTratamento().getListaHorarios()) {
            this.tratamento.setListaHorarios(new ArrayList<HorarioVO>());
        }
        if (this.tratamento.getListaHorarios().contains(this.getHorarioTratamento())) {
            setFacesErrorMessage("message.contrato.horariotratamento.duplicated");
        } else {
            HorarioVO horario = new HorarioVO(this.getHorarioTratamento());
            horario.setCrud("C");
            this.tratamento.getListaHorarios().add(horario);
        }
    }

    /**
     * Nome: validarDadosTratamento Validar dados tratamento.
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean validarDadosTratamento() {
        boolean dadosPreenchidos = true;
        if (StringUtil.isEmpty(this.tratamento.getNomeTratamento(), true)) {
            setFacesErrorMessage("message.contrato.field.tratamento.nome.required");
            dadosPreenchidos = false;
        }
        if (null == this.tratamento.getDataHoraInicial()) {
            setFacesErrorMessage("message.contrato.field.tratamento.datahorainicio.required");
            dadosPreenchidos = false;
        }
        return dadosPreenchidos;
    }

    /**
     * Nome: validarPreenchimentoCamposFormaContatoCliente Validar preenchimento campos forma
     * contato cliente.
     * @param event the event
     * @see
     */
    public void validarPreenchimentoCamposFormaContatoCliente(ComponentSystemEvent event) {
        UIComponent components = event.getComponent();

        UISelectOne uiSelectOne = (UISelectOne) components
            .findComponent("frmAtendimento:idCmbTipoFormaContatoCliente");
        UIInput uiTelefone = (UIInput) components
            .findComponent("frmAtendimento:idTxtFormaContatoClienteTelefone");
        UIInput uiEmail = (UIInput) components
            .findComponent("frmAtendimento:idTxtFormaContatoClienteEmail");
        new FormularioFormaPagamentoValidator(FacesContext.getCurrentInstance())
            .validarCamposFormaContat(uiSelectOne, uiTelefone, uiEmail);
    }

    /**
     * Nome: validarPreenchimentoCamposFormaContatoPessoaDeContatoDoCliente Validar preenchimento
     * campos forma contato pessoa de contato do cliente.
     * @param event the event
     * @see
     */
    public void validarPreenchimentoCamposFormaContatoPessoaDeContatoDoCliente(
        ComponentSystemEvent event) {

        UIComponent components = event.getComponent();

        UISelectOne uiSelectOne = (UISelectOne) components
            .findComponent("frmAtendimento:idCmbTipoFormaContatoPessoaContatoCliente");
        UIInput uiTelefone = (UIInput) components
            .findComponent("frmAtendimento:idTxtFormaContatoPessoaContatoClienteTelefone");
        UIInput uiEmail = (UIInput) components
            .findComponent("frmAtendimento:idTxtFormaContatoPessoaContatoClienteEmail");
        new FormularioFormaPagamentoValidator(FacesContext.getCurrentInstance())
            .validarCamposFormaContat(uiSelectOne, uiTelefone, uiEmail);
    }

    /**
     * Nome: obterPickListDoencas Obter pick list doencas.
     * @param e the e
     * @see
     */
    public void obterPickListDoencas(ActionEvent e) {
        this.setPickListDoencas(this.obterPickListDoencas(this.filtroDoenca));
    }

    /**
     * Nome: obterPickListDoencas Obter pick list doencas.
     * @param filtro the filtro
     * @return dual list model
     * @see
     */
    public DualListModel<DoencaVO> obterPickListDoencas(String filtro) {
        this.getLogger().debug("***** Iniciando método obterPickListDoencas(String filtro) *****");

        List<DoencaVO> target = new ArrayList<DoencaVO>();

        if (null != this.pickListDoencas
            && !CollectionUtils.isEmptyOrNull(this.pickListDoencas.getTarget())) {
            target = this.pickListDoencas.getTarget();
        } else if (this.getCrud() != null && this.getCrud().equals(Crud.Update.getValue())
            && filtro.equals("@-")) {
            target = this.getContrato().getCliente().getListaDoencas();
            filtro = "";
        } else if (this.getCrud() == null
            && CollectionUtils.isNotEmptyOrNull(this.getContrato().getCliente().getListaDoencas())) {
            target = this.getContrato().getCliente().getListaDoencas();
            filtro = "";
        } else if (filtro.equals("@-")) {
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
     * Nome: rollBackListasExclusaoSalvarDadosContrato
     * Retorna ao estado atual das listas excluindo os itens marcados para exclusao, evitando que reapareçam na tela. "PSEUDO_ROLLBACK".
     *
     * @see
     */
    protected void rollBackListasExclusaoSalvarDadosContrato() {

        this.getContrato().getCliente().getListaFormaContato().removeAll(this.getListaFormaContatoClienteRemovidos());
        this.getContrato().getCliente().getListaContatos().removeAll(this.getListaPessoasContatoClienteRemovidos());
        this.getContrato().getCliente().getListaTratamentos().removeAll(this.getListaTratamentosRemovidos());
        if (!CollectionUtils.isEmptyOrNull(this.getListaFormaContatoRemovidos())) {
            this.getContrato().getCliente().getListaContatos().get(0)
                .setCrud(Crud.Update.getValue());
            this.getContrato().getCliente().getListaContatos().get(0).getListaFormaContato()
                .removeAll(this.getListaFormaContatoRemovidos());
        }

    }

    /**
     * Nome: novaPessoaContatoComCliente Nova pessoa contato com cliente.
     * @see
     */
    public void novaPessoaContatoComCliente() {
        this.pessoaParaContato = new ContatoVO();
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

    /**
     * Nome: getListaFormaContatoClienteRemovidos Recupera o valor do atributo
     * 'listaFormaContatoClienteRemovidos'.
     * @return valor do atributo 'listaFormaContatoClienteRemovidos'
     * @see
     */
    public List<FormaContatoVO> getListaFormaContatoClienteRemovidos() {
        return listaFormaContatoClienteRemovidos;
    }

    /**
     * Nome: setListaFormaContatoClienteRemovidos Registra o valor do atributo
     * 'listaFormaContatoClienteRemovidos'.
     * @param listaFormaContatoClienteRemovidos valor do atributo lista forma contato cliente
     *            removidos
     * @see
     */
    public void setListaFormaContatoClienteRemovidos(
        List<FormaContatoVO> listaFormaContatoClienteRemovidos) {
        this.listaFormaContatoClienteRemovidos = listaFormaContatoClienteRemovidos;
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
     * Nome: getListaHorariosRemovidos Recupera o valor do atributo 'listaHorariosRemovidos'.
     * @return valor do atributo 'listaHorariosRemovidos'
     * @see
     */
    public List<HorarioVO> getListaHorariosRemovidos() {
        return listaHorariosRemovidos;
    }

    /**
     * Nome: setListaHorariosRemovidos Registra o valor do atributo 'listaHorariosRemovidos'.
     * @param listaHorariosRemovidos valor do atributo lista horarios removidos
     * @see
     */
    public void setListaHorariosRemovidos(List<HorarioVO> listaHorariosRemovidos) {
        this.listaHorariosRemovidos = listaHorariosRemovidos;
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
     * Nome: getListaTratamentosRemovidos Recupera o valor do atributo 'listaTratamentosRemovidos'.
     * @return valor do atributo 'listaTratamentosRemovidos'
     * @see
     */
    public List<TratamentoVO> getListaTratamentosRemovidos() {
        return listaTratamentosRemovidos;
    }

    /**
     * Nome: setListaTratamentosRemovidos Registra o valor do atributo 'listaTratamentosRemovidos'.
     * @param listaTratamentosRemovidos valor do atributo lista tratamentos removidos
     * @see
     */
    public void setListaTratamentosRemovidos(List<TratamentoVO> listaTratamentosRemovidos) {
        this.listaTratamentosRemovidos = listaTratamentosRemovidos;
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
     * Nome: getListaDispositivosRemovidos Recupera o valor do atributo
     * 'listaDispositivosRemovidos'.
     * @return valor do atributo 'listaDispositivosRemovidos'
     * @see
     */
    public List<DispositivoVO> getListaDispositivosRemovidos() {
        return listaDispositivosRemovidos;
    }

    /**
     * Nome: setListaDispositivosRemovidos Registra o valor do atributo
     * 'listaDispositivosRemovidos'.
     * @param listaDispositivosRemovidos valor do atributo lista dispositivos removidos
     * @see
     */
    public void setListaDispositivosRemovidos(List<DispositivoVO> listaDispositivosRemovidos) {
        this.listaDispositivosRemovidos = listaDispositivosRemovidos;
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
     * Nome: getListaPessoasContatoClienteRemovidos Recupera o valor do atributo
     * 'listaPessoasContatoClienteRemovidos'.
     * @return valor do atributo 'listaPessoasContatoClienteRemovidos'
     * @see
     */
    public List<ContatoVO> getListaPessoasContatoClienteRemovidos() {
        return listaPessoasContatoClienteRemovidos;
    }

    /**
     * Nome: setListaPessoasContatoClienteRemovidos Registra o valor do atributo
     * 'listaPessoasContatoClienteRemovidos'.
     * @param listaPessoasContatoClienteRemovidos valor do atributo lista pessoas contato cliente
     *            removidos
     * @see
     */
    public void setListaPessoasContatoClienteRemovidos(
        List<ContatoVO> listaPessoasContatoClienteRemovidos) {
        this.listaPessoasContatoClienteRemovidos = listaPessoasContatoClienteRemovidos;
    }

    /**
     * Nome: getContratoBusiness Recupera o valor do atributo 'contratoBusiness'.
     * @return valor do atributo 'contratoBusiness'
     * @see
     */
    public ContratoBusiness getContratoBusiness() {
        return contratoBusiness;
    }

    /**
     * Nome: setContratoBusiness Registra o valor do atributo 'contratoBusiness'.
     * @param contratoBusiness valor do atributo contrato business
     * @see
     */
    public void setContratoBusiness(ContratoBusiness contratoBusiness) {
        this.contratoBusiness = contratoBusiness;
    }

    /**
     * Nome: getPacoteServicoBusiness Recupera o valor do atributo 'pacoteServicoBusiness'.
     * @return valor do atributo 'pacoteServicoBusiness'
     * @see
     */
    public PacoteServicoBusiness getPacoteServicoBusiness() {
        return pacoteServicoBusiness;
    }

    /**
     * Nome: setPacoteServicoBusiness Registra o valor do atributo 'pacoteServicoBusiness'.
     * @param pacoteServicoBusiness valor do atributo pacote servico business
     * @see
     */
    public void setPacoteServicoBusiness(PacoteServicoBusiness pacoteServicoBusiness) {
        this.pacoteServicoBusiness = pacoteServicoBusiness;
    }

    /**
     * Nome: getListaFormaContatoRemovidos Recupera o valor do atributo
     * 'listaFormaContatoRemovidos'.
     * @return valor do atributo 'listaFormaContatoRemovidos'
     * @see
     */
    public List<FormaContatoVO> getListaFormaContatoRemovidos() {
        return listaFormaContatoRemovidos;
    }

    /**
     * Nome: setListaFormaContatoRemovidos Registra o valor do atributo
     * 'listaFormaContatoRemovidos'.
     * @param listaFormaContatoRemovidos valor do atributo lista forma contato removidos
     * @see
     */
    public void setListaFormaContatoRemovidos(List<FormaContatoVO> listaFormaContatoRemovidos) {
        this.listaFormaContatoRemovidos = listaFormaContatoRemovidos;
    }

}
