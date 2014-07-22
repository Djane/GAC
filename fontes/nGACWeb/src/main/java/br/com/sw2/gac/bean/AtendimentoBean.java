package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.context.RequestContext;

import br.com.sw2.gac.business.SmsBusiness;
import br.com.sw2.gac.converter.TelefoneConverter;
import br.com.sw2.gac.datamodel.ContatoDataModel;
import br.com.sw2.gac.datamodel.FormaContatoDataModel;
import br.com.sw2.gac.datamodel.OcorrenciaDataModel;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.ContratanteNaoEncontradoException;
import br.com.sw2.gac.exception.DadosIncompletosException;
import br.com.sw2.gac.socket.PhoneCommand;
import br.com.sw2.gac.socket.SocketPhone;
import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.socket.constants.TipoLigacao;
import br.com.sw2.gac.socket.predicate.ChamadasAtivasContatoPredicate;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.TipoContato;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.mail.EmailMessage;
import br.com.sw2.gac.util.mail.EmailSender;
import br.com.sw2.gac.vo.AcionamentoEmailVO;
import br.com.sw2.gac.vo.AcionamentoVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.SmsVO;

/**
 * <b>Descrição: Controller da tela de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class AtendimentoBean extends BaseAtendimentoBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 3822727613830737506L;

    /** Atributo lista historico ocorrencia. */
    private List<OcorrenciaVO> listaHistoricoOcorrencia;

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

    /** Atributo lista doencas docliente. */
    private List<SelectItem> listaDoencasDocliente = new ArrayList<SelectItem>();

    /** Atributo acionamento contato. */
    private FormaContatoVO formaContatoAcionamentoTelefonico = new FormaContatoVO();

    /** Atributo acionamento sm sm andamento pessoa contato. */
    private AcionamentoVO acionamentoSMSmAndamentoPessoaContato = new AcionamentoVO();

    /** Atributo acionamento email andamento pessoa contato. */
    private AcionamentoEmailVO acionamentoEmailAndamentoPessoaContato = null;

    /** Atributo lista sms padrao. */
    private List<SelectItem> listaSmsPadrao = new ArrayList<SelectItem>();

    /** Atributo disabled cmd email pessoa de contato cliente. */
    private Boolean disabledCmdEmailPessoaDeContatoCliente = true;

    /** Atributo disabled cmd sms pessoa de contato cliente. */
    private Boolean disabledCmdSmsPessoaDeContatoCliente = true;

    /** Atributo disabled cmd ligar pessoa contato do cliente. */
    private Boolean disabledCmdLigarPessoaContatoDoCliente = true;

    /** Atributo lista ligacoes pessoa contato. */
    private List<Line> listaLigacoesPessoaContato = new ArrayList<Line>();

    /** Atributo telefone do cliente selecionado. */
    private String telefoneDoClienteSelecionado = "";

    /** Atributo disabled cmd ligar cliente. */
    private Boolean disabledCmdLigarCliente = true;

    /** Atributo disabled cmd pausar cliente. */
    private Boolean disabledCmdPausarCliente = true;

    /** Atributo disabled cmd desligar cliente. */
    private Boolean disabledCmdDesligarCliente = true;

    /** Atributo ligacao com o cliente. */
    private Line ligacaoComOCliente = null;

    /** Atributo displayid pgd status ligacao com cliente. */
    private String displayidPgdStatusLigacaoComCliente = "none";

    /** Atributo display panel grid barra botoes gac phone pessoa contato. */
    private String displayPanelGridBarraBotoesGACPhonePessoaContato = "none";
    
    private String comandoDtmfLivre;
    
    /**
     * Construtor Padrao Instancia um novo objeto AtendimentoBean.
     */
    public AtendimentoBean() {
        super();
        this.ocorrenciaEmAndamento = (OcorrenciaVO) getSessionAttribute("atenderOcorrencia");

        this.socketPhone = (SocketPhone) getSessionAttribute("socketPhone");
        this.setContrato(this.ocorrenciaEmAndamento.getContrato());
        
        
        //seta se existe dispositivo configurado
        if (CollectionUtils.isNotEmptyOrNull(this.getContrato().getCliente().getListaDispositivos()) 
            && this.getContrato().getCliente().getListaDispositivos().get(0).getNumeroSequencialDisponisitoNaCentralInteger() != null 
                && this.getContrato().getCliente().getListaDispositivos().get(0).getNumeroSequencialDisponisitoNaCentralInteger().intValue() >= 0 ) {
            this.dispositivoConfigurado = true;
        } else {
            this.dispositivoConfigurado = false;
        }        
        
        popularRadioButtonTelefonesCliente();

        if (null != this.getContrato()
            && null != this.getContrato().getCliente().getListaContatos()
            && !this.getContrato().getCliente().getListaContatos().isEmpty()) {
            this.contatoSelecionado = this.getContrato().getCliente().getListaContatos().get(0);
        }
        this.contatoDataModel = new ContatoDataModel(this.getContrato().getCliente()
            .getListaContatos());
        if (this.getContrato().getCliente().getListaContatos().size() > 0) {
            this.formaContatoDataModel = new FormaContatoDataModel(
                this.contatoSelecionado.getListaFormaContato());
            for (FormaContatoVO item : this.contatoSelecionado.getListaFormaContato()) {
                if (!item.getTipoContato().equals(TipoContato.Email.getValue())) {
                    this.formaContatoAcionamentoTelefonico = item;
                }
            }
        }
        this.listaDoencasDocliente = getSelectItems(this.getContrato().getCliente()
            .getListaDoencas(), "codigoCID", "nomeDoenca");


        if (this.ocorrenciaEmAndamento.getScript() == null) {
            this.scriptAtendimentoSelecionado = "0";
        } else {
            this.scriptAtendimentoSelecionado = this.ocorrenciaEmAndamento.getScript().getIdScript().toString();
        }

        this.ocorrenciaDataModel = new OcorrenciaDataModel(
            this.ocorrenciaEmAndamento.getListaHistoricoOcorrencias());

        this.semafaroOff();
        this.ledSemaforoVerde = "/img/green_circle_on.png";
        this.cssBoxMensagemPrioridade = "areaVerde";

        // Popular picklist de doenças
        this.setPickListDoencas(obterPickListDoencas("@-"));

        this.listaSmsPadrao = getSelectItems(popularListaSmsPadrao(), "idSms", "titulo");

        formaContatoPessoaClienteSelecioada();

        this.logger.debug("Prioridade da ocorrencia :" + this.ocorrenciaEmAndamento.getCodigoPrioridade());


        atualizarListaChamadasParaPessoaContato();
        this.displayidPgdStatusLigacaoComCliente = "none";
        this.disabledCmdLigarCliente = false;


        if (null != this.socketPhone) {

            this.logger.debug("Linhas do socketPhone ****************************************************");
            for (Line line : this.socketPhone.getLinhas()) {
                this.logger.debug("Linha: " + line.getNumeroLinha() + " Número discado: "
                    + line.getNumeroDiscado() + " Sub número: " + line.getSubNumeroDiscado()
                    + " Status da linha:" + line.getStatusLinha() + " Tipo de ligação: "
                    + line.getTipoLigacao());
            }


            Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "statusLinha", StatusLigacao.FALANDO.getValue());
            if (null == linha) {
                // Esta logado mas não tem ligação
                mudarPrioridade(2);
                this.socketPhone.setAlertaChamada(TipoOcorrencia.AtendimentoManual.getLabel());
            } else {

                String numeroLigacaoRecebida = null;
                if (linha.getNumeroDiscado().equals(this.socketPhone.getNumeroDiscagemLoginAgente())) {
                    numeroLigacaoRecebida = linha.getSubNumeroDiscado();
                } else {
                    numeroLigacaoRecebida = linha.getNumeroDiscado();
                }
                mudarPrioridade(this.ocorrenciaEmAndamento.getCodigoPrioridade());
                if (CollectionUtils.findByAttribute(this.getContrato().getCliente()
                    .getListaFormaContato(), "telefone", numeroLigacaoRecebida) != null) {
                    // Ligação recebida de cliente
                    linha.setTipoLigacao(TipoLigacao.COM_CLIENTE.getValue());
                    this.ligacaoComOCliente = linha;
                    this.telefoneDoClienteSelecionado = numeroLigacaoRecebida;
                    statusLigacaoClienteEmAndamento();

                } else {
                    // ver se é uma ligação de contato
                    for (ContatoVO contato : this.getContrato().getCliente().getListaContatos()) {
                        if (CollectionUtils.findByAttribute(contato.getListaFormaContato(), "telefone", numeroLigacaoRecebida) != null) {
                            linha.setTipoLigacao(TipoLigacao.COM_CONTATO.getValue());
                            atualizarListaChamadasParaPessoaContato();
                            exibirGradeLigacoesContatos();
                        }
                    }
                }
            }

            this.socketPhone.enviarMensagem(PhoneCommand.dgTimeStamp(this.getUsuarioLogado().getRamal()));
            this.socketPhone.selecionarLinha(1);
        }

    }

    /**
     * Nome: popularRadioButtonTelefonesCliente
     * Popular radio button telefones cliente.
     *
     * @see
     */
    private void popularRadioButtonTelefonesCliente() {
        this.telefonesContatoComCliente = new ArrayList<SelectItem>();
        for (FormaContatoVO item : this.getContrato().getCliente().getListaFormaContato()) {
            if (!TipoContato.Email.getValue().equals(item.getTipoContato())) {
                SelectItem selectItem = new SelectItem();
                selectItem.setValue(item.getTelefone());
                selectItem.setLabel(new TelefoneConverter().formatarTelefone(item.getTelefone()));
                this.telefonesContatoComCliente.add(selectItem);
            }
        }
    }

    /**
     * Nome: monitorarSocket
     * Monitorar socket.
     *
     * @see
     */
    public synchronized void monitorarSocket() {
        this.logger.debug("Iniciandom MonitorSocket da tela de registro de ocorrências ********************");
        addCallbackParam("continuarMonitorar", true);

        if (null != this.socketPhone && this.socketPhone.getSocket() != null
            && this.socketPhone.isAtendenteAutenticado()) {

            try {

                List<Event> eventos = this.socketPhone.aguardarEvento();

                for (Event evento : eventos) {

                    this.socketPhone.tratarEventoSocket(evento);

                    if (this.socketPhone.isAvisoLigacaoEmergencia()) {
                        addCallbackParam("avisoLigacaoEmergencia", true); //Avisa a tela para exibir o popup de alerta
                        this.socketPhone.setAvisoLigacaoEmergencia(false); // Reset
                    }

                    if (null != evento.getUser() && evento.getUser().intValue() == this.getUsuarioLogado().getRamal().intValue()) {
                        // Detectado evento para o usuário
                        Line line = null;
                        if (null != evento.getLine()) {
                            line = (Line) CollectionUtils.findByAttribute(this.socketPhone
                                .getLinhas(), "numeroLinha", evento.getLine());
                        }

                        if (null != evento.getStatus()) {
                            if (evento.getStatus().equals("Answer")) {
                                addCallbackParam("hideDialing", true);

                            } else if (evento.getStatus().equals("Error")) {
                                setFacesMessage("Não é possível completar a ligação !");

                            } else if (evento.getStatus().equals("Hold")) {
                                if (evento.getHold().equals("1")) {
                                    setFacesMessage("A ligação para " + evento.getNumber() + " foi colocada em espera", false);
                                } else if (evento.getHold().equals("0")) {
                                    setFacesMessage("A ligação para " + evento.getNumber() + " foi retirada da em espera", false);
                                }

                            } else if (evento.getStatus().equals("Busy")) {
                                setFacesErrorMessage("O numero está ocupado !", false);

                            } else if (evento.getStatus().equals("Hangup")) {
                                setFacesErrorMessage("Uma ligação foi encerrada na linha " + line.getNumeroLinha(), false);

                            } else if (evento.getStatus().equals("Ready")) {
                                if (line.getTipoLigacao().intValue() == 1) {
                                    this.telefoneDoClienteSelecionado = null;
                                    this.statusSemLigacaoParaCliente();
                                }
                                addCallbackParam("hideDialing", true);

                            }
                        }
                    }
                }

            } catch (Exception e) {
                logarErro(e);
            }

            atualizarListaChamadasParaPessoaContato();
            exibirGradeLigacoesContatos();

        } else {
            this.logger.debug("monitorarSocket: Atendente não está logado");
        }

        this.logger.debug("Finalizando monitorSocket da tela de registro de ocorrências ********************");
    }

    /**
     * Nome: atualizarListaChamadasParaPessoaContato
     * Atualizar lista chamadas para pessoa contato.
     *
     * @see
     */
    private void atualizarListaChamadasParaPessoaContato() {
        ChamadasAtivasContatoPredicate predicate = new ChamadasAtivasContatoPredicate();
        if (null != this.socketPhone) {
            this.listaLigacoesPessoaContato = (List<Line>) CollectionUtils.select(this.socketPhone.getLinhas(), predicate);
        }
    }

    /**
     * Nome: atualizarGradeFormaContato Atualizar grade forma contato.
     * @see
     */
    public void atualizarGradeFormaContato() {

        this.formaContatoDataModel = new FormaContatoDataModel(
            this.contatoSelecionado.getListaFormaContato());

        for (FormaContatoVO item : this.contatoSelecionado.getListaFormaContato()) {
            if (!item.getTipoContato().equals(TipoContato.Email.getValue())) {
                this.formaContatoAcionamentoTelefonico = item;
                formaContatoPessoaClienteSelecioada();
            }
        }

    }

    /**
     * Nome: atualizarGradeFormaContato Atualizar grade forma contato.
     * @see
     */
    public void formaContatoPessoaClienteSelecioada() {

        this.logger.debug("***** Iniciado método formaContatoPessoaClienteSelecioada() *****");

        this.disabledCmdEmailPessoaDeContatoCliente = true;
        this.disabledCmdSmsPessoaDeContatoCliente = true;
        this.disabledCmdLigarPessoaContatoDoCliente = true;

        if (this.formaContatoAcionamentoTelefonico.getTipoContato().equals(
            TipoContato.Email.getValue())) {
            this.disabledCmdEmailPessoaDeContatoCliente = false;
            this.acionamentoEmailAndamentoPessoaContato = new AcionamentoEmailVO();
            this.acionamentoEmailAndamentoPessoaContato
                .setTo(this.formaContatoAcionamentoTelefonico.getEmail());
        } else {
            this.acionamentoEmailAndamentoPessoaContato = null;
        }

        if (this.formaContatoAcionamentoTelefonico.getTipoContato().equals(
            TipoContato.TelefoneCelular.getValue())) {
            this.disabledCmdSmsPessoaDeContatoCliente = false;
            this.disabledCmdLigarPessoaContatoDoCliente = false;
        }

        if (this.formaContatoAcionamentoTelefonico.getTipoContato().equals(
            TipoContato.TelefoneResidencial.getValue())
            || this.formaContatoAcionamentoTelefonico.getTipoContato().equals(
                TipoContato.TelefoneComercial.getValue())) {
            this.disabledCmdLigarPessoaContatoDoCliente = false;
        }

        this.logger.debug(
            "***** Finalizado método formaContatoPessoaClienteSelecioada() *****");

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
     * Nome: mudarPrioridade Mudar prioridade.
     * @param codigoPrioridade the codigo prioridade
     * @see
     */
    public void mudarPrioridade(int codigoPrioridade) {
        this.logger.debug("***** Iniciado método mudarPrioridadeVerde() *****");
        semafaroOff();
        if (codigoPrioridade == 1) {
            this.ledSemaforoVerde = "/img/green_circle_on.png";
            this.cssBoxMensagemPrioridade = "areaVerde";
        } else if (codigoPrioridade == 2) {
            this.ledSemaforoAmarelo = "/img/yellow_circle_on.png";
            this.cssBoxMensagemPrioridade = "areaAmarela";
        } else if (codigoPrioridade == 3) {
            this.ledSemaforoVermelho = "/img/red_circle_on.png";
            this.cssBoxMensagemPrioridade = "areaVermelha";
        }
        this.ocorrenciaEmAndamento.setCodigoPrioridade(codigoPrioridade);
        this.logger.debug("***** Nova prioridade: " + codigoPrioridade);
        this.logger.debug("***** Finalizado método mudarPrioridadeVerde() *****");
    }

    /**
     * Nome: salvarDadosPessoasContatoDoCliente Salvar dados pessoas contato do cliente.
     * @param e the e
     * @see
     */
    public void salvarDadosPessoasContatoDoCliente(ActionEvent e) {

        this.getContrato().getCliente().getListaContatos().addAll(this.getListaPessoasContatoClienteRemovidos());
        if (!CollectionUtils.isEmptyOrNull(this.getListaFormaContatoRemovidos())) {
            this.getContrato().getCliente().getListaContatos().get(0).setCrud(Crud.Update.getValue());
            this.getContrato().getCliente().getListaContatos().get(0).getListaFormaContato()
                .addAll(this.getListaFormaContatoRemovidos());
        }

        try {
            this.getContratoBusiness().atualizarDadosListaPessoasDeContatoDoCliente(
                this.getContrato());
            this.contatoDataModel = new ContatoDataModel(this.getContrato().getCliente()
                .getListaContatos());
            if (this.getContrato().getCliente().getListaContatos().size() > 0) {
                this.contatoSelecionado = this.getContrato().getCliente().getListaContatos().get(0);
                this.formaContatoDataModel = new FormaContatoDataModel(this.contatoSelecionado.getListaFormaContato());
            }
            setFacesMessage("message.contrato.save.contato.sucess");

        } catch (ContratanteNaoEncontradoException ex) {
            setFacesErrorMessage(ex.getMessage());
        } catch (BusinessException ex) {
            this.logarErro(ex);
            setFacesErrorMessage("message.contrato.save.contato.failed");
            this.logarErro(getMessageFromBundle("message.contrato.save.contato.failed"));
        } finally {
            this.getListaPessoasContatoClienteRemovidos().clear();
        }

        this.getListaFormaContatoRemovidos().clear();
    }

    /**
     * Nome: salvarDadosOcorrencia Salvar dados ocorrencia.
     * @param e the e
     * @see
     */
    public void salvarDadosOcorrencia(ActionEvent e) {
        super.salvarDadosOcorrencia(ocorrenciaEmAndamento);
    }

    /**
     * Nome: salvarDadosContrato Salvar dados contrato.
     * @param e the e
     * @see
     */
    public void salvarDadosContrato(ActionEvent e) {
        this.logger.debug("***** Iniciando método salvarDadosContrato(ActionEvent e) *****");

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

            /*
             * Remove os itens marcados para exclusao das listas para não serem reapresentados na
             * tela. Eles foram incluidos nessas listas somente para irem junto com o VO de
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
            this.popularRadioButtonTelefonesCliente();

        } catch (DadosIncompletosException ex) {
            for (String key : ex.getListKeyMessage()) {
                setFacesErrorMessage(key);
            }
            rollBackListasExclusaoSalvarDadosContrato();
        } catch (BusinessException ex) {
            this.logarErro(ex);
            setFacesErrorMessage("message.contrato.save.failed");
            this.logarErro(getMessageFromBundle("message.contrato.save.failed"));
            rollBackListasExclusaoSalvarDadosContrato();
        }

        this.logger.debug("***** Finalizado método salvarDadosContrato(ActionEvent e) *****");
    }

    /**
     * Nome: salvarDadosDispositivos Salvar dados dispositivos.
     * @param e the e
     * @see
     */
    public void salvarDadosDispositivos(ActionEvent e) {
        // Trata se houve alteração na lista de dispositivos e centrais.
        if (!CollectionUtils.isEmptyOrNull(this.getListaDispositivosRemovidos())) {
            for (DispositivoVO dispositivo : this.getListaDispositivosRemovidos()) {
                dispositivo.setCrud(Crud.Delete.getValue());
                this.getContrato().getCliente().getListaDispositivos()
                    .addAll(this.getListaDispositivosRemovidos());
            }
        }

        try {
            this.getContratoBusiness().atualizarDadosDispositivos(this.getContrato());
            setFacesMessage("message.contrato.save.dispositivo.sucess");
        } catch (BusinessException ex) {
            setFacesErrorMessage("message.contrato.save.dispositivo.failed");
            this.logarErro(ex);
        }
    }

    /**
     * Nome: liberadoParaLigar
     * Liberado para ligar.
     *
     * @param numeroTelefone the numero telefone
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean liberadoParaLigar(String numeroTelefone) {
        boolean retorno = false;

        if (null == numeroTelefone || "".equals(numeroTelefone.trim())) {
            setFacesErrorMessage("Não foi informado um numero para discagem !", false);
            addCallbackValidationError(true);
        } else if (null != CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroDiscado", numeroTelefone)) {
            setFacesErrorMessage("Há uma ligação em andamento para este número !", false);
            addCallbackValidationError(true);
        } else if (null !=  CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "statusLinha", StatusLigacao.FALANDO.getValue())) {
            setFacesErrorMessage("Existe uma ligação em andamento. Coloque a ligação em espera antes de ligar para outro número !", false);
            addCallbackValidationError(true);
        } else {
            retorno = true;
            addCallbackValidationError(false);
        }

        return retorno;
    }

    /**
     * Nome: ligarParaCliente
     * Ligar para cliente.
     *
     * @param e the e
     * @see
     */
    public void ligarParaCliente(ActionEvent e) {
        if (liberadoParaLigar(this.telefoneDoClienteSelecionado)) {

            //iniciar ligação
            Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "statusLinha", StatusLigacao.LIVRE.getValue());

            if (null == linha) {
                setFacesErrorMessage("Não há linhas disponíveis para ligação", false);
            } else {
                this.socketPhone.discar(this.telefoneDoClienteSelecionado,  linha.getNumeroLinha());

                linha.setNumeroDiscado(this.telefoneDoClienteSelecionado);
                linha.setStatusLinha(StatusLigacao.FALANDO.getValue()); // Indica ligação em andamento. "Falando"
                linha.setTipoLigacao(TipoLigacao.COM_CLIENTE.getValue()); // Indica uma ligação para o cliente
                this.ligacaoComOCliente = linha;
                statusLigacaoClienteEmAndamento();
            }
        } else {
            statusSemLigacaoParaCliente();
        }

        this.logger.debug("Ligando para cliente: " + this.telefoneDoClienteSelecionado);
    }

    /**
     * Nome: statusSemLigacaoParaCliente
     * Status sem ligacao para cliente.
     *
     * @see
     */
    private void statusSemLigacaoParaCliente() {
        this.disabledCmdLigarCliente = false;
        this.disabledCmdDesligarCliente = true;
        this.disabledCmdPausarCliente = true;
        this.displayidPgdStatusLigacaoComCliente = "none";
    }

    /**
     * Nome: statusLigacaoClienteEmAndamento
     * Status ligacao cliente em andamento.
     *
     * @see
     */
    private void statusLigacaoClienteEmAndamento() {
        this.displayidPgdStatusLigacaoComCliente = "block";
        this.disabledCmdLigarCliente = true;
        this.disabledCmdDesligarCliente = false;
        this.disabledCmdPausarCliente = false;
    }

    /**
     * Nome: ligarParaPessoaDeContato
     * Ligar para pessoa de contato.
     * @param e the e
     * @see
     */
    public void ligarParaPessoaDeContato(ActionEvent e) {
        this.logger.debug("***** Iniciando método ligarParaPessoaDeContato(ActionEvent e) *****");

        Date dataHoraDoAcionamento = new Date();
        Date dataHoraInicioConversa = null;

        if (liberadoParaLigar(this.formaContatoAcionamentoTelefonico.getTelefone().toString())) {

            // addCallbackParam("callComplete", chamadaCompletada);

            // Após concluir a ligação
            dataHoraInicioConversa = new Date();

            // Busca uma linha disponivel
            Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "statusLinha", StatusLigacao.LIVRE.getValue());

            if (null != linha) {
                this.socketPhone.discar(this.formaContatoAcionamentoTelefonico.getTelefone(), linha.getNumeroLinha());

                linha.setNumeroDiscado(this.formaContatoAcionamentoTelefonico.getTelefone().toString());
                linha.setStatusLinha(StatusLigacao.FALANDO.getValue()); // Indica ligação em andamento. "Falando"
                linha.setTipoLigacao(TipoLigacao.COM_CONTATO.getValue()); // Indica uma ligação para contato

                AcionamentoVO acionamentoVO = new AcionamentoVO();
                acionamentoVO.setIdOcorrencia(this.ocorrenciaEmAndamento.getIdOcorrencia());
                acionamentoVO.setDataHoraDoAcionamento(dataHoraDoAcionamento);
                acionamentoVO.setDataHoraInicioConversa(dataHoraInicioConversa);
                acionamentoVO.setIdContato(this.formaContatoAcionamentoTelefonico.getIdContato());
                acionamentoVO = this.ocorrenciaBusiness.registrarNovaLigacaoPessoaDeContatoCliente(acionamentoVO);
                linha.setAcionamento(acionamentoVO);

                atualizarListaChamadasParaPessoaContato();
                this.disabledCmdLigarPessoaContatoDoCliente = true;

            } else {
                setFacesErrorMessage("Não há linhas disponíveis para efetuar a ligação", false);
                this.logger.debug("Não há linhas disponíveis para efetuar a ligação");
            }

            this.logger.debug("***** Finalizado método ligarParaPessoaDeContato(ActionEvent e) *****");
        } else {
            addCallbackParam("callComplete", false);
        }

        if (CollectionUtils.isEmptyOrNull(this.listaLigacoesPessoaContato)) {
            addCallbackParam("exibirGradeLigacoesContatos", false);
        } else {
            addCallbackParam("exibirGradeLigacoesContatos", true);
        }

    }

    /**
     * Nome: colocarRemoverLigacaoEmEspera
     * Colocar remover ligacao em espera.
     *
     * @param numeroLinha the numero linha
     * @param statusLinha the status linha
     * @see
     */
    public void colocarRemoverLigacaoEmEspera(Integer numeroLinha, Integer statusLinha) {

        Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroLinha", numeroLinha);
        if (statusLinha.intValue() == StatusLigacao.PAUSA.getValue().intValue()) {
            for (Line item : this.socketPhone.getLinhas()) {
                // Verifica se tem algum em status de conversa e coloca em espera
                if (item.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    item.setStatusLinha(StatusLigacao.PAUSA.getValue());
                }
            }
            //Muda o status da linha informada para Falando.
            linha.setStatusLinha(StatusLigacao.FALANDO.getValue());
            
            if (null != this.socketPhone) {
                this.socketPhone.enviarMensagem(PhoneCommand.selecionarLinha(this.getUsuarioLogado().getRamal(), numeroLinha));
            }
            
            this.logger.debug("PAUSA PARA PAUSA");
        
        } else if (statusLinha.intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
            linha.setStatusLinha(StatusLigacao.PAUSA.getValue());
            
            this.logger.debug("FALANDO PARA PAUSA");

            if (null != this.socketPhone) {
                this.socketPhone.colocarRemoverChamadaEmEspera(linha.getNumeroLinha());
            }            
            
        }

        if (null == this.ligacaoComOCliente) {
            addCallbackParam("pgdStatusLigacaoComClienteRendered", false);
        } else {
            addCallbackParam("pgdStatusLigacaoComClienteRendered", true);
        }

    }

    /**
     * Nome: colocarRemoverLigacaoClienteEmEspera
     * Colocar remover ligacao cliente em espera.
     *
     * @param event the event
     * @see
     */
    public void colocarRemoverLigacaoClienteEmEspera(ActionEvent event) {
        this.colocarRemoverLigacaoEmEspera(this.ligacaoComOCliente.getNumeroLinha(), this.ligacaoComOCliente.getStatusLinha());
        if (this.ligacaoComOCliente.getNumeroDiscado().equals(this.socketPhone.getNumeroDiscagemLoginAgente())) {
            this.telefoneDoClienteSelecionado = this.ligacaoComOCliente.getSubNumeroDiscado();
        } else {
            this.telefoneDoClienteSelecionado = this.ligacaoComOCliente.getNumeroDiscado();
        }
        this.displayidPgdStatusLigacaoComCliente = "block";
    }

    /**
     * Nome: colocarRemoverContatoEmEspera
     * Colocar remover contato em espera.
     *
     * @param event the event
     * @see
     */
    public void colocarRemoverContatoEmEspera(ActionEvent event) {
        String numeroLinha = (String) getFacesContext().getExternalContext().getRequestParameterMap().get("numeroLinha");
        String statusLinha = (String) getFacesContext().getExternalContext().getRequestParameterMap().get("statusLinha");

        this.colocarRemoverLigacaoEmEspera(Integer.parseInt(numeroLinha), Integer.parseInt(statusLinha));

    }

    /**
     * Nome: encerrarLigacaoParaCliente
     * Encerrar ligacao para cliente.
     *
     * @param e the e
     * @see
     */
    public void encerrarLigacaoParaCliente(ActionEvent e) {

        this.encerrarLigacao(this.ligacaoComOCliente, true);
        this.displayidPgdStatusLigacaoComCliente = "none";
        this.ligacaoComOCliente = null;
        this.disabledCmdLigarCliente = false;
        this.disabledCmdDesligarCliente = true;
        this.disabledCmdPausarCliente = true;

    }

    /**
     * Nome: encerrarLigacaoParaPessoaDeContato Encerrar ligacao para pessoa de contato.
     * @param e the e
     * @see
     */
    public void encerrarLigacaoParaPessoaDeContato(ActionEvent e) {

        String numeroLinha = (String) getFacesContext().getExternalContext().getRequestParameterMap().get("numeroLinha");
        Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroLinha", Integer.parseInt(numeroLinha));
        linha.getAcionamento().setDataHoraFinalConversa(new Date());
        this.ocorrenciaBusiness.encerrarLigacaoPessoaDeContatoCliente(linha.getAcionamento());
        this.encerrarLigacao(linha, false);

        atualizarListaChamadasParaPessoaContato();

        if (CollectionUtils.isEmptyOrNull(this.listaLigacoesPessoaContato)) {
            addCallbackParam("exibirGradeLigacoesContatos", false);
        } else {
            addCallbackParam("exibirGradeLigacoesContatos", true);
        }

        this.logger.debug("***** Finalizado método encerrarLigacaoParaPessoaDeContato(ActionEvent e) *****");
    }

    /**
     * Nome: enviarSmsPessoaDeContato Enviar sms pessoa de contato.
     * @param e the e
     * @see
     */
    public void enviarSmsPessoaDeContato(ActionEvent e) {
        this.logger.debug("***** Iniciando método enviarSmsPessoaDeContato(ActionEvent e) *****");
        boolean smsEnviado = true;
        Date dataHoraDoAcionamento = new Date();
        AcionamentoVO acionamentoVO = new AcionamentoVO();
        acionamentoVO.setIdOcorrencia(this.ocorrenciaEmAndamento.getIdOcorrencia());
        acionamentoVO.setDataHoraDoAcionamento(dataHoraDoAcionamento);
        acionamentoVO.setIdContato(this.formaContatoAcionamentoTelefonico.getIdContato());
        acionamentoVO.setTextLivreSMS(this.acionamentoSMSmAndamentoPessoaContato.getTextLivreSMS());
        acionamentoVO.setIdSMSPadrao(this.acionamentoSMSmAndamentoPessoaContato.getIdSMSPadrao());
        acionamentoVO.setSucesso(smsEnviado);
        try {
            this.ocorrenciaBusiness.registrarEnvioSmsPessoaDeContatoCliente(acionamentoVO);
            setFacesMessage("message.generic.sms.send.sucess");
        } catch (BusinessException ex) {
            this.logarErro(ex);
            setFacesErrorMessage("message.generic.system.operation.failed");
            this.logarErro(getMessageFromBundle("message.generic.system.operation.failed"));

        }
        this.debug("***** Finalizado método enviarSmsPessoaDeContato(ActionEvent e) *****");

    }

    /**
     * Nome: enviarEmailParaPessoaDeContato Enviar email para pessoa de contato.
     * @param e the e
     * @see
     */
    public void enviarEmailParaPessoaDeContato(ActionEvent e) {
        this.debug("***** Finalizado método enviarEmailParaPessoaDeContato(ActionEvent e) *****");
        this.debug(this.acionamentoEmailAndamentoPessoaContato.getTo());
        this.debug(this.acionamentoEmailAndamentoPessoaContato.getCc());
        this.debug(this.acionamentoEmailAndamentoPessoaContato.getCorpo());
        this.acionamentoEmailAndamentoPessoaContato.setAssunto("SmartAngel Ocorrencia nº "
            + this.ocorrenciaEmAndamento.getIdOcorrencia() + " Acionamento de contato");

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.mime.charset", "ISO-8859-1");
        properties.put("mail.password", "@james123");
        properties.put("mail.user", "james.secretario@gmail.com");
        properties.put("mail.sender.address", "james.secretario@gmail.com");
        properties.put("mail.sender.name", "Atendimento SmartAngel");

        EmailMessage mj = new EmailMessage();
        mj.setSenderAdress(properties.getProperty("mail.sender.address"));
        mj.setSenderName(properties.getProperty("mail.sender.name"));
        mj.setSubject(this.acionamentoEmailAndamentoPessoaContato.getAssunto());
        mj.setBody(this.acionamentoEmailAndamentoPessoaContato.getCorpo());
        mj.setMessageType(EmailMessage.TYPE_TEXT_PLAIN);
        mj.setRecipientTO(this.acionamentoEmailAndamentoPessoaContato.getTo());
        mj.setRecipientCC(this.acionamentoEmailAndamentoPessoaContato.getCc());

        this.debug(this.acionamentoEmailAndamentoPessoaContato.getAssunto());

        RequestContext reqCtx = RequestContext.getCurrentInstance();
        try {
            new EmailSender(properties).sendMessage(mj);
            setFacesMessage("message.generic.email.send.sucess");
            reqCtx.addCallbackParam("sucess", true);
        } catch (Exception ex) {
            setFacesErrorMessage("message.generic.email.send.failed");
            reqCtx.addCallbackParam("sucess", false);
            this.logarErro("message.generic.email.send.failed");
            this.logarErro(ex);
        }
        this.debug("***** Finalizado método enviarEmailParaPessoaDeContato(ActionEvent e) *****");
    }
    
    
    /**
     * 
     * @param e
     */
    public void programarDispositivoAcionamento(ActionEvent e) {
        this.logger.debug(getClass(), "Iniciando método para configuração / programação de dispositivo de acionamento");
        this.socketPhone.programarDispositivoAcionamento();        
    }
    
    /**
     * 
     */
    public void apagarDispositivoAcionamento(ActionEvent e) {   
        try {
            Integer numeroDispositivo = this.getContrato().getCliente().getListaDispositivos().get(0).getNumeroSequencialDisponisitoNaCentralInteger();
            this.socketPhone.apagarDispositivoAcionamento(numeroDispositivo);
        } catch (Exception ex) {
            this.getLogger().warn("Não há dispositivos para remover: " + ex.getMessage());
        }
    }
    
    
    public void entrarEmModoTeste(ActionEvent e) {   
        try {            
            this.socketPhone.entrarEmModoTeste();
        } catch (Exception ex) {
            this.getLogger().error(e);
        }
    }
    
    public void sairDoModoTeste(ActionEvent e) {   
        try {           
            this.socketPhone.sairDoModoTeste();
        } catch (Exception ex) {
            this.getLogger().error(e);
        }
    }
    
    /**
     * Nome: popularListaSmsPadrao Popular lista sms padrao.
     * @return list
     * @see
     */
    private List<SmsVO> popularListaSmsPadrao() {
        SmsVO sms = new SmsVO();
        sms.setDtTerminoValidade(DateUtil.getDataAtual());
        SmsBusiness smsBusiness = new SmsBusiness();
        return smsBusiness.obterListaMensagensAtivas(sms);
    }


    /**
     * Nome: exibirGradeLigacoesContatos
     * Exibir grade ligacoes contatos.
     *
     * @see
     */
    public void exibirGradeLigacoesContatos() {
        if (CollectionUtils.isNotEmptyOrNull(this.listaLigacoesPessoaContato)) {
            addCallbackParam("exibirGradeLigacoesContatos", true);
            this.displayPanelGridBarraBotoesGACPhonePessoaContato = "block";
            this.logger.debug("Ligação para um contato detectada. Setando exibirGradeLigacoesContato para true");
        } else {
            addCallbackParam("exibirGradeLigacoesContatos", false);
            this.displayPanelGridBarraBotoesGACPhonePessoaContato = "none";
            this.logger.debug("Não existe ligação para contato. Setando exibirGradeLigacoesContatos para false");
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
     * Nome: encerrarTelaRegistroOcorrencia
     * Encerrar tela registro ocorrencia.
     *
     * @return string
     * @see
     */
    public String encerrarTelaRegistroOcorrencia() {
        this.logger.debug("Acionado método encerrarTelaRegistroOcorrencia");
        
        Integer ramal = this.getUsuarioLogado().getRamal();
        for (Line linhaAEncerrar : this.socketPhone.getLinhas()) {
            /*
             * Verifica se há alguma linha em uso. Caso sim libera a linha
             */
            if (linhaAEncerrar.getStatusLinha().intValue() != StatusLigacao.LIVRE.getValue().intValue()) {
                //Se a linha chegou com status diferente de livre nessa parte precisa ser encerrada.
                this.encerrarLigacao(linhaAEncerrar.getNumeroLinha());
            }
        }
        
        setRequestAttribute("ativarMonitorSocket", true);
        return "preAtendimento";
    }

    /**
     * Nome: ativarAutoFalante
     * Ativar auto falante.
     *
     * @param e the e
     * @see
     */
    public void ativarAutoFalante(ActionEvent e) {
        this.socketPhone.ativarAltoFalante();
        
    }

    /**
     * Nome: desativarAutoFalante
     * Desativar auto falante.
     *
     * @param e the e
     * @see
     */
    public void desativarAutoFalante(ActionEvent e) {
        this.socketPhone.desativarAltoFalante();        
    }

    /**
     * Nome: ativarMicrofone
     * Ativar microfone.
     *
     * @param e the e
     * @see
     */
    public void ativarMicrofone(ActionEvent e) {
        this.socketPhone.ativarMicrofone();
        
    }

    /**
     * Nome: desativarMicrofone
     * Desativar microfone.
     *
     * @param e the e
     * @see
     */
    public void desativarMicrofone(ActionEvent e) {
        this.socketPhone.desativarMicrofone();
        
    }

    public void enviarComandoLivre(ActionEvent e) {        
        this.socketPhone.enviarMensagem(PhoneCommand.enviarDtmf(getUsuarioLogado().getRamal(), this.comandoDtmfLivre));      
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
     * Nome: getListaDoencasDocliente Recupera o valor do atributo 'listaDoencasDocliente'.
     * @return valor do atributo 'listaDoencasDocliente'
     * @see
     */
    public List<SelectItem> getListaDoencasDocliente() {
        return listaDoencasDocliente;
    }

    /**
     * Nome: setListaDoencasDocliente Registra o valor do atributo 'listaDoencasDocliente'.
     * @param listaDoencasDocliente valor do atributo lista doencas docliente
     * @see
     */
    public void setListaDoencasDocliente(List<SelectItem> listaDoencasDocliente) {
        this.listaDoencasDocliente = listaDoencasDocliente;
    }

    /**
     * Nome: getFormaContatoAcionamentoTelefonico Recupera o valor do atributo
     * 'formaContatoAcionamentoTelefonico'.
     * @return valor do atributo 'formaContatoAcionamentoTelefonico'
     * @see
     */
    public FormaContatoVO getFormaContatoAcionamentoTelefonico() {
        return formaContatoAcionamentoTelefonico;
    }

    /**
     * Nome: setFormaContatoAcionamentoTelefonico Registra o valor do atributo
     * 'formaContatoAcionamentoTelefonico'.
     * @param formaContatoAcionamentoTelefonico valor do atributo forma contato acionamento
     *            telefonico
     * @see
     */
    public void setFormaContatoAcionamentoTelefonico(
        FormaContatoVO formaContatoAcionamentoTelefonico) {
        this.formaContatoAcionamentoTelefonico = formaContatoAcionamentoTelefonico;
    }

    /**
     * Nome: getAcionamentoSMSmAndamentoPessoaContato Recupera o valor do atributo
     * 'acionamentoSMSmAndamentoPessoaContato'.
     * @return valor do atributo 'acionamentoSMSmAndamentoPessoaContato'
     * @see
     */
    public AcionamentoVO getAcionamentoSMSmAndamentoPessoaContato() {
        return acionamentoSMSmAndamentoPessoaContato;
    }

    /**
     * Nome: setAcionamentoSMSmAndamentoPessoaContato Registra o valor do atributo
     * 'acionamentoSMSmAndamentoPessoaContato'.
     * @param acionamentoSMSmAndamentoPessoaContato valor do atributo acionamento sm sm andamento
     *            pessoa contato
     * @see
     */
    public void setAcionamentoSMSmAndamentoPessoaContato(
        AcionamentoVO acionamentoSMSmAndamentoPessoaContato) {
        this.acionamentoSMSmAndamentoPessoaContato = acionamentoSMSmAndamentoPessoaContato;
    }

    /**
     * Nome: getListaSmsPadrao Recupera o valor do atributo 'listaSmsPadrao'.
     * @return valor do atributo 'listaSmsPadrao'
     * @see
     */
    public List<SelectItem> getListaSmsPadrao() {
        return listaSmsPadrao;
    }

    /**
     * Nome: setListaSmsPadrao Registra o valor do atributo 'listaSmsPadrao'.
     * @param listaSmsPadrao valor do atributo lista sms padrao
     * @see
     */
    public void setListaSmsPadrao(List<SelectItem> listaSmsPadrao) {
        this.listaSmsPadrao = listaSmsPadrao;
    }

    /**
     * Nome: getDisabledCmdEmailPessoaDeContatoCliente Recupera o valor do atributo
     * 'disabledCmdEmailPessoaDeContatoCliente'.
     * @return valor do atributo 'disabledCmdEmailPessoaDeContatoCliente'
     * @see
     */
    public Boolean getDisabledCmdEmailPessoaDeContatoCliente() {
        return disabledCmdEmailPessoaDeContatoCliente;
    }

    /**
     * Nome: setDisabledCmdEmailPessoaDeContatoCliente Registra o valor do atributo
     * 'disabledCmdEmailPessoaDeContatoCliente'.
     * @param disabledCmdEmailPessoaDeContatoCliente valor do atributo disabled cmd email pessoa de
     *            contato cliente
     * @see
     */
    public void setDisabledCmdEmailPessoaDeContatoCliente(
        Boolean disabledCmdEmailPessoaDeContatoCliente) {
        this.disabledCmdEmailPessoaDeContatoCliente = disabledCmdEmailPessoaDeContatoCliente;
    }

    /**
     * Nome: getDisabledCmdSmsPessoaDeContatoCliente Recupera o valor do atributo
     * 'disabledCmdSmsPessoaDeContatoCliente'.
     * @return valor do atributo 'disabledCmdSmsPessoaDeContatoCliente'
     * @see
     */
    public Boolean getDisabledCmdSmsPessoaDeContatoCliente() {
        return disabledCmdSmsPessoaDeContatoCliente;
    }

    /**
     * Nome: setDisabledCmdSmsPessoaDeContatoCliente Registra o valor do atributo
     * 'disabledCmdSmsPessoaDeContatoCliente'.
     * @param disabledCmdSmsPessoaDeContatoCliente valor do atributo disabled cmd sms pessoa de
     *            contato cliente
     * @see
     */
    public void setDisabledCmdSmsPessoaDeContatoCliente(Boolean disabledCmdSmsPessoaDeContatoCliente) {
        this.disabledCmdSmsPessoaDeContatoCliente = disabledCmdSmsPessoaDeContatoCliente;
    }

    /**
     * Nome: getDisabledCmdLigarPessoaContatoDoCliente Recupera o valor do atributo
     * 'disabledCmdLigarPessoaContatoDoCliente'.
     * @return valor do atributo 'disabledCmdLigarPessoaContatoDoCliente'
     * @see
     */
    public Boolean getDisabledCmdLigarPessoaContatoDoCliente() {
        return disabledCmdLigarPessoaContatoDoCliente;
    }

    /**
     * Nome: setDisabledCmdLigarPessoaContatoDoCliente Registra o valor do atributo
     * 'disabledCmdLigarPessoaContatoDoCliente'.
     * @param disabledCmdLigarPessoaContatoDoCliente valor do atributo disabled cmd ligar pessoa
     *            contato do cliente
     * @see
     */
    public void setDisabledCmdLigarPessoaContatoDoCliente(
        Boolean disabledCmdLigarPessoaContatoDoCliente) {
        this.disabledCmdLigarPessoaContatoDoCliente = disabledCmdLigarPessoaContatoDoCliente;
    }

    /**
     * Nome: getAcionamentoEmailAndamentoPessoaContato Recupera o valor do atributo
     * 'acionamentoEmailAndamentoPessoaContato'.
     * @return valor do atributo 'acionamentoEmailAndamentoPessoaContato'
     * @see
     */
    public AcionamentoEmailVO getAcionamentoEmailAndamentoPessoaContato() {
        return acionamentoEmailAndamentoPessoaContato;
    }

    /**
     * Nome: setAcionamentoEmailAndamentoPessoaContato Registra o valor do atributo
     * 'acionamentoEmailAndamentoPessoaContato'.
     * @param acionamentoEmailAndamentoPessoaContato valor do atributo acionamento email andamento
     *            pessoa contato
     * @see
     */
    public void setAcionamentoEmailAndamentoPessoaContato(
        AcionamentoEmailVO acionamentoEmailAndamentoPessoaContato) {
        this.acionamentoEmailAndamentoPessoaContato = acionamentoEmailAndamentoPessoaContato;
    }

    /**
     * Nome: getListaLigacoesPessoaContato
     * Recupera o valor do atributo 'listaLigacoesPessoaContato'.
     *
     * @return valor do atributo 'listaLigacoesPessoaContato'
     * @see
     */
    public List<Line> getListaLigacoesPessoaContato() {
        return listaLigacoesPessoaContato;
    }

    /**
     * Nome: setListaLigacoesPessoaContato
     * Registra o valor do atributo 'listaLigacoesPessoaContato'.
     *
     * @param listaLigacoesPessoaContato valor do atributo lista ligacoes pessoa contato
     * @see
     */
    public void setListaLigacoesPessoaContato(List<Line> listaLigacoesPessoaContato) {
        this.listaLigacoesPessoaContato = listaLigacoesPessoaContato;
    }

    /**
     * Nome: getTelefoneDoClienteSelecionado
     * Recupera o valor do atributo 'telefoneDoClienteSelecionado'.
     *
     * @return valor do atributo 'telefoneDoClienteSelecionado'
     * @see
     */
    public String getTelefoneDoClienteSelecionado() {
        return telefoneDoClienteSelecionado;
    }

    /**
     * Nome: setTelefoneDoClienteSelecionado
     * Registra o valor do atributo 'telefoneDoClienteSelecionado'.
     *
     * @param telefoneDoClienteSelecionado valor do atributo telefone do cliente selecionado
     * @see
     */
    public void setTelefoneDoClienteSelecionado(String telefoneDoClienteSelecionado) {
        this.telefoneDoClienteSelecionado = telefoneDoClienteSelecionado;
    }

    /**
     * Nome: getDisabledCmdLigarCliente
     * Recupera o valor do atributo 'disabledCmdLigarCliente'.
     *
     * @return valor do atributo 'disabledCmdLigarCliente'
     * @see
     */
    public Boolean getDisabledCmdLigarCliente() {
        return disabledCmdLigarCliente;
    }

    /**
     * Nome: setDisabledCmdLigarCliente
     * Registra o valor do atributo 'disabledCmdLigarCliente'.
     *
     * @param disabledCmdLigarCliente valor do atributo disabled cmd ligar cliente
     * @see
     */
    public void setDisabledCmdLigarCliente(Boolean disabledCmdLigarCliente) {
        this.disabledCmdLigarCliente = disabledCmdLigarCliente;
    }

    /**
     * Nome: getLigacaoComOCliente
     * Recupera o valor do atributo 'ligacaoComOCliente'.
     *
     * @return valor do atributo 'ligacaoComOCliente'
     * @see
     */
    public Line getLigacaoComOCliente() {
        return ligacaoComOCliente;
    }

    /**
     * Nome: setLigacaoComOCliente
     * Registra o valor do atributo 'ligacaoComOCliente'.
     *
     * @param ligacaoComOCliente valor do atributo ligacao com o cliente
     * @see
     */
    public void setLigacaoComOCliente(Line ligacaoComOCliente) {
        this.ligacaoComOCliente = ligacaoComOCliente;
    }

    /**
     * Nome: getDisabledCmdPausarCliente
     * Recupera o valor do atributo 'disabledCmdPausarCliente'.
     *
     * @return valor do atributo 'disabledCmdPausarCliente'
     * @see
     */
    public Boolean getDisabledCmdPausarCliente() {
        return disabledCmdPausarCliente;
    }

    /**
     * Nome: setDisabledCmdPausarCliente
     * Registra o valor do atributo 'disabledCmdPausarCliente'.
     *
     * @param disabledCmdPausarCliente valor do atributo disabled cmd pausar cliente
     * @see
     */
    public void setDisabledCmdPausarCliente(Boolean disabledCmdPausarCliente) {
        this.disabledCmdPausarCliente = disabledCmdPausarCliente;
    }

    /**
     * Nome: getDisabledCmdDesligarCliente
     * Recupera o valor do atributo 'disabledCmdDesligarCliente'.
     *
     * @return valor do atributo 'disabledCmdDesligarCliente'
     * @see
     */
    public Boolean getDisabledCmdDesligarCliente() {
        return disabledCmdDesligarCliente;
    }

    /**
     * Nome: setDisabledCmdDesligarCliente
     * Registra o valor do atributo 'disabledCmdDesligarCliente'.
     *
     * @param disabledCmdDesligarCliente valor do atributo disabled cmd desligar cliente
     * @see
     */
    public void setDisabledCmdDesligarCliente(Boolean disabledCmdDesligarCliente) {
        this.disabledCmdDesligarCliente = disabledCmdDesligarCliente;
    }

    /**
     * Nome: getDisplayidPgdStatusLigacaoComCliente
     * Recupera o valor do atributo 'displayidPgdStatusLigacaoComCliente'.
     *
     * @return valor do atributo 'displayidPgdStatusLigacaoComCliente'
     * @see
     */
    public String getDisplayidPgdStatusLigacaoComCliente() {
        return displayidPgdStatusLigacaoComCliente;
    }

    /**
     * Nome: setDisplayidPgdStatusLigacaoComCliente
     * Registra o valor do atributo 'displayidPgdStatusLigacaoComCliente'.
     *
     * @param displayidPgdStatusLigacaoComCliente valor do atributo displayid pgd status ligacao com cliente
     * @see
     */
    public void setDisplayidPgdStatusLigacaoComCliente(String displayidPgdStatusLigacaoComCliente) {
        this.displayidPgdStatusLigacaoComCliente = displayidPgdStatusLigacaoComCliente;
    }

    /**
     * Nome: getDisplayPanelGridBarraBotoesGACPhonePessoaContato
     * Recupera o valor do atributo 'displayPanelGridBarraBotoesGACPhonePessoaContato'.
     *
     * @return valor do atributo 'displayPanelGridBarraBotoesGACPhonePessoaContato'
     * @see
     */
    public String getDisplayPanelGridBarraBotoesGACPhonePessoaContato() {
        return displayPanelGridBarraBotoesGACPhonePessoaContato;
    }

    /**
     * Nome: setDisplayPanelGridBarraBotoesGACPhonePessoaContato
     * Registra o valor do atributo 'displayPanelGridBarraBotoesGACPhonePessoaContato'.
     *
     * @param displayPanelGridBarraBotoesGACPhonePessoaContato valor do atributo display panel grid barra botoes gac phone pessoa contato
     * @see
     */
    public void setDisplayPanelGridBarraBotoesGACPhonePessoaContato(
        String displayPanelGridBarraBotoesGACPhonePessoaContato) {
        this.displayPanelGridBarraBotoesGACPhonePessoaContato = displayPanelGridBarraBotoesGACPhonePessoaContato;
    }

    public String getComandoDtmfLivre() {
        return comandoDtmfLivre;
    }

    public void setComandoDtmfLivre(String comandoDtmfLivre) {
        this.comandoDtmfLivre = comandoDtmfLivre;
    }

    
    
    
}
