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

import br.com.sw2.gac.business.OcorrenciaBusiness;
import br.com.sw2.gac.business.ScriptBusiness;
import br.com.sw2.gac.business.SmsBusiness;
import br.com.sw2.gac.converter.TelefoneConverter;
import br.com.sw2.gac.datamodel.ContatoDataModel;
import br.com.sw2.gac.datamodel.FormaContatoDataModel;
import br.com.sw2.gac.datamodel.OcorrenciaDataModel;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.ContratanteNaoEncontradoException;
import br.com.sw2.gac.exception.DadosIncompletosException;
import br.com.sw2.gac.exception.StatusOcorrenciaException;
import br.com.sw2.gac.tools.Crud;
import br.com.sw2.gac.tools.StatusOcorrencia;
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
import br.com.sw2.gac.vo.ScriptVO;
import br.com.sw2.gac.vo.SmsVO;

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

    /** Atributo lista doencas docliente. */
    private List<SelectItem> listaDoencasDocliente = new ArrayList<SelectItem>();

    /** Atributo ocorrencia business. */
    private OcorrenciaBusiness ocorrenciaBusiness = new OcorrenciaBusiness();

    /** Atributo lista status ocorrencia. */
    private List<SelectItem> listaStatusOcorrencia = new ArrayList<SelectItem>();

    /** Atributo lista script atendimento. */
    private List<SelectItem> listaScriptAtendimento = new ArrayList<SelectItem>();

    /** Atributo script atendimento selecionado. */
    private String scriptAtendimentoSelecionado = "";

    /** Atributo acionamento contato. */
    private FormaContatoVO formaContatoAcionamentoTelefonico = new FormaContatoVO();

    /** Atributo id acionamento em andamento pessoa contato. */
    private AcionamentoVO acionamentoTelefonicoEmAndamentoPessoaContato = null;

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

    /**
     * Construtor Padrao Instancia um novo objeto AtendimentoBean.
     */
    public AtendimentoBean() {
        super();
        this.ocorrenciaEmAndamento = (OcorrenciaVO) getSessionAttribute("atenderOcorrencia");
        this.setContrato(this.ocorrenciaEmAndamento.getContrato());
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
        if (null != this.getContrato()
            && null != this.getContrato().getCliente().getListaContatos()
            && !this.getContrato().getCliente().getListaContatos().isEmpty()) {
            this.contatoSelecionado = this.getContrato().getCliente().getListaContatos().get(0);
        }
        this.contatoDataModel = new ContatoDataModel(this.getContrato().getCliente()
            .getListaContatos());
        if (this.getContrato().getCliente().getListaContatos().size() > 0) {
            this.formaContatoDataModel = new FormaContatoDataModel(this.contatoSelecionado.getListaFormaContato());
            for (FormaContatoVO item  : this.contatoSelecionado.getListaFormaContato()) {
                if (!item.getTipoContato().equals(TipoContato.Email.getValue())) {
                    this.formaContatoAcionamentoTelefonico = item;
                }
            }
        }
        this.listaDoencasDocliente = getSelectItems(this.getContrato().getCliente()
            .getListaDoencas(), "codigoCID", "nomeDoenca");
        this.listaStatusOcorrencia = getSelectItems(StatusOcorrencia.class);

        popularListaDeScripts();

        this.ocorrenciaDataModel = new OcorrenciaDataModel(
            this.ocorrenciaEmAndamento.getListaHistoricoOcorrencias());

        this.semafaroOff();
        this.ledSemaforoVerde = "/img/green_circle_on.png";
        this.cssBoxMensagemPrioridade = "areaVerde";

        // Popular picklist de doenças
        this.setPickListDoencas(obterPickListDoencas("@-"));

        this.listaSmsPadrao = getSelectItems(popularListaSmsPadrao(), "idSms", "titulo");

        formaContatoPessoaClienteSelecioada();

        mudarPrioridade(this.ocorrenciaEmAndamento.getCodigoPrioridade());

    }

    /**
     * Nome: popularListaDeScripts Popular lista de scripts.
     * @see
     */
    private void popularListaDeScripts() {
        ScriptBusiness scriptBusiness = new ScriptBusiness();
        List<ScriptVO> listaScriptVO = scriptBusiness.obterListaScripts();
        this.listaScriptAtendimento = getSelectItems(listaScriptVO, "idScript", "tituloScript");
        if (this.ocorrenciaEmAndamento.getScript() == null) {
            this.scriptAtendimentoSelecionado = "0";
        } else {
            this.scriptAtendimentoSelecionado = this.ocorrenciaEmAndamento.getScript()
                .getIdScript().toString();
        }
    }

    /**
     * Nome: atualizarGradeFormaContato Atualizar grade forma contato.
     * @see
     */
    public void atualizarGradeFormaContato() {

        this.formaContatoDataModel = new FormaContatoDataModel(
            this.contatoSelecionado.getListaFormaContato());

        for (FormaContatoVO item  : this.contatoSelecionado.getListaFormaContato()) {
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

        this.getLogger().debug("***** Iniciado método formaContatoPessoaClienteSelecioada() *****");

        this.disabledCmdEmailPessoaDeContatoCliente = true;
        this.disabledCmdSmsPessoaDeContatoCliente = true;
        this.disabledCmdLigarPessoaContatoDoCliente = true;

        if (this.formaContatoAcionamentoTelefonico.getTipoContato().equals(
            TipoContato.Email.getValue())) {
            this.disabledCmdEmailPessoaDeContatoCliente = false;
            this.acionamentoEmailAndamentoPessoaContato = new AcionamentoEmailVO();
            this.acionamentoEmailAndamentoPessoaContato.setTo(this.formaContatoAcionamentoTelefonico.getEmail());
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

        if (null != this.acionamentoTelefonicoEmAndamentoPessoaContato) {
            this.disabledCmdLigarPessoaContatoDoCliente = true;
            this.getLogger().debug("***** ligação para contato em andamento : this.disabledCmdLigarPessoaContatoDoCliente = true  *****");
        }

        this.getLogger().debug("***** Finalizado método formaContatoPessoaClienteSelecioada() *****");

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
        this.ocorrenciaEmAndamento.setCodigoPrioridade(2);
    }


    /**
     * Nome: mudarPrioridade
     * Mudar prioridade.
     *
     * @param codigoPrioridade the codigo prioridade
     * @see
     */
    public void mudarPrioridade(int codigoPrioridade) {
        this.getLogger().debug("***** Iniciado método mudarPrioridadeVerde() *****");
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
        this.getLogger().debug("***** Nova prioridade: " + codigoPrioridade);
        this.getLogger().debug("***** Finalizado método mudarPrioridadeVerde() *****");
    }

    /**
     * Nome: salvarDadosPessoasContatoDoCliente Salvar dados pessoas contato do cliente.
     * @param e the e
     * @see
     */
    public void salvarDadosPessoasContatoDoCliente(ActionEvent e) {

        this.getContrato().getCliente().getListaContatos()
            .addAll(this.getListaPessoasContatoClienteRemovidos());

        if (!CollectionUtils.isEmptyOrNull(this.getListaFormaContatoRemovidos())) {
            this.getContrato().getCliente().getListaContatos().get(0)
                .setCrud(Crud.Update.getValue());
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
                this.formaContatoDataModel = new FormaContatoDataModel(
                    this.contatoSelecionado.getListaFormaContato());
            }
            setFacesMessage("message.contrato.save.contato.sucess");

        } catch (ContratanteNaoEncontradoException ex) {
            setFacesErrorMessage(ex.getMessage());
        } catch (BusinessException ex) {
            this.getLogger().error(ex);
            setFacesErrorMessage("message.contrato.save.contato.failed");
            this.getLogger().error(getMessageFromBundle("message.contrato.save.contato.failed"));
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
        this.getLogger().debug("***** Iniciando método salvarDadosContrato(ActionEvent e) *****");

        if (this.scriptAtendimentoSelecionado == "0") {
            this.ocorrenciaEmAndamento.setScript(null);
        } else {
            this.ocorrenciaEmAndamento.setScript(new ScriptVO());
            this.ocorrenciaEmAndamento.getScript().setIdScript(
                Integer.parseInt(this.scriptAtendimentoSelecionado));
        }

        try {
            this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(this.ocorrenciaEmAndamento);
            setFacesMessage("message.atendimento.save.sucess");
        } catch (StatusOcorrenciaException ex) {
            setFacesMessage("message.atendimento.save.status.exception");
            this.getLogger().error(ex);
        } catch (BusinessException ex) {
            setFacesMessage("message.atendimento.save.failed");
            this.getLogger().error(ex);
        }

        this.getLogger().debug("***** Finalizado método salvarDadosContrato(ActionEvent e) *****");

    }

    /**
     * Nome: salvarDadosContrato Salvar dados contrato.
     * @param e the e
     * @see
     */
    public void salvarDadosContrato(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método salvarDadosContrato(ActionEvent e) *****");

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

        } catch (DadosIncompletosException ex) {
            for (String key : ex.getListKeyMessage()) {
                setFacesErrorMessage(key);
            }
            rollBackListasExclusaoSalvarDadosContrato();
        } catch (BusinessException ex) {
            this.getLogger().error(ex);
            setFacesErrorMessage("message.contrato.save.failed");
            this.getLogger().error(getMessageFromBundle("message.contrato.save.failed"));
            rollBackListasExclusaoSalvarDadosContrato();
        }

        this.getLogger().debug("***** Finalizado método salvarDadosContrato(ActionEvent e) *****");
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
            this.getLogger().error(ex);
        }
    }

    /**
     * Nome: ligarParaPessoaDeContato Ligar para pessoa de contato.
     * @param e the e
     * @see
     */
    public void ligarParaPessoaDeContato(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método ligarParaPessoaDeContato(ActionEvent e) *****");
        boolean chamadaCompletada = true;
        Date dataHoraDoAcionamento = new Date();
        Date dataHoraInicioConversa = null;

        RequestContext reqCtx = RequestContext.getCurrentInstance();
        reqCtx.addCallbackParam("callComplete", chamadaCompletada);

        if (chamadaCompletada) {
            //Após concluir a ligação
            dataHoraInicioConversa = new Date();
        } else {
            reqCtx.addCallbackParam("GACPhoneMessage", "A chamada não pode ser completada !");
        }

        AcionamentoVO acionamentoVO = new AcionamentoVO();
        acionamentoVO.setIdOcorrencia(this.ocorrenciaEmAndamento.getIdOcorrencia());
        acionamentoVO.setDataHoraDoAcionamento(dataHoraDoAcionamento);
        acionamentoVO.setDataHoraInicioConversa(dataHoraInicioConversa);
        acionamentoVO.setIdContato(this.formaContatoAcionamentoTelefonico.getIdContato());
        this.acionamentoTelefonicoEmAndamentoPessoaContato = this.ocorrenciaBusiness.registrarNovaLigacaoPessoaDeContatoCliente(acionamentoVO);
        this.disabledCmdLigarPessoaContatoDoCliente = true;

        this.getLogger().debug("***** Finalizado método ligarParaPessoaDeContato(ActionEvent e) *****");

    }

    /**
     * Nome: enviarSmsPessoaDeContato
     * Enviar sms pessoa de contato.
     *
     * @param e the e
     * @see
     */
    public void enviarSmsPessoaDeContato(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método enviarSmsPessoaDeContato(ActionEvent e) *****");
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
            this.getLogger().error(ex);
            setFacesErrorMessage("message.generic.system.operation.failed");
            this.getLogger().error(getMessageFromBundle("message.generic.system.operation.failed"));

        }
        this.getLogger().debug("***** Finalizado método enviarSmsPessoaDeContato(ActionEvent e) *****");

    }

    /**
     * Nome: encerrarLigacaoParaPessoaDeContato
     * Encerrar ligacao para pessoa de contato.
     *
     * @param e the e
     * @see
     */
    public void encerrarLigacaoParaPessoaDeContato(ActionEvent e) {
        this.getLogger().debug("***** Finalizado método encerrarLigacaoParaPessoaDeContato(ActionEvent e) *****");
        this.acionamentoTelefonicoEmAndamentoPessoaContato.setDataHoraFinalConversa(new Date());
        this.ocorrenciaBusiness.encerrarLigacaoPessoaDeContatoCliente(this.acionamentoTelefonicoEmAndamentoPessoaContato);
        this.acionamentoTelefonicoEmAndamentoPessoaContato = null;
        this.getLogger().debug("***** Finalizado método encerrarLigacaoParaPessoaDeContato(ActionEvent e) *****");
    }

    /**
     * Nome: enviarEmailParaPessoaDeContato
     * Enviar email para pessoa de contato.
     *
     * @param e the e
     * @see
     */
    public void enviarEmailParaPessoaDeContato(ActionEvent e) {
        this.getLogger().debug("***** Finalizado método enviarEmailParaPessoaDeContato(ActionEvent e) *****");
        this.getLogger().debug(this.acionamentoEmailAndamentoPessoaContato.getTo());
        this.getLogger().debug(this.acionamentoEmailAndamentoPessoaContato.getCc());
        this.getLogger().debug(this.acionamentoEmailAndamentoPessoaContato.getCorpo());
        this.acionamentoEmailAndamentoPessoaContato.setAssunto("SmartAngel Ocorrencia nº "
            + this.ocorrenciaEmAndamento.getIdOcorrencia() + " Acionamento de contato");

        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.host", "smtp.gmail.com");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port" , "587");
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

        this.getLogger().debug(this.acionamentoEmailAndamentoPessoaContato.getAssunto());

        RequestContext reqCtx = RequestContext.getCurrentInstance();
        try {
            new EmailSender(properties).sendMessage(mj);
            setFacesMessage("message.generic.email.send.sucess");
            reqCtx.addCallbackParam("sucess", true);
        } catch (Exception ex) {
            setFacesErrorMessage("message.generic.email.send.failed");
            reqCtx.addCallbackParam("sucess", false);
            this.getLogger().error("message.generic.email.send.failed");
            this.getLogger().error(ex);
        }

        this.getLogger().debug("***** Finalizado método enviarEmailParaPessoaDeContato(ActionEvent e) *****");
    }

    /**
     * Nome: popularListaSmsPadrao
     * Popular lista sms padrao.
     *
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
     * Nome: getFormaContatoAcionamentoTelefonico
     * Recupera o valor do atributo 'formaContatoAcionamentoTelefonico'.
     *
     * @return valor do atributo 'formaContatoAcionamentoTelefonico'
     * @see
     */
    public FormaContatoVO getFormaContatoAcionamentoTelefonico() {
        return formaContatoAcionamentoTelefonico;
    }

    /**
     * Nome: setFormaContatoAcionamentoTelefonico
     * Registra o valor do atributo 'formaContatoAcionamentoTelefonico'.
     *
     * @param formaContatoAcionamentoTelefonico valor do atributo forma contato acionamento telefonico
     * @see
     */
    public void setFormaContatoAcionamentoTelefonico(FormaContatoVO formaContatoAcionamentoTelefonico) {
        this.formaContatoAcionamentoTelefonico = formaContatoAcionamentoTelefonico;
    }

    /**
     * Nome: getAcionamentoSMSmAndamentoPessoaContato
     * Recupera o valor do atributo 'acionamentoSMSmAndamentoPessoaContato'.
     *
     * @return valor do atributo 'acionamentoSMSmAndamentoPessoaContato'
     * @see
     */
    public AcionamentoVO getAcionamentoSMSmAndamentoPessoaContato() {
        return acionamentoSMSmAndamentoPessoaContato;
    }

    /**
     * Nome: setAcionamentoSMSmAndamentoPessoaContato
     * Registra o valor do atributo 'acionamentoSMSmAndamentoPessoaContato'.
     *
     * @param acionamentoSMSmAndamentoPessoaContato valor do atributo acionamento sm sm andamento pessoa contato
     * @see
     */
    public void setAcionamentoSMSmAndamentoPessoaContato(
        AcionamentoVO acionamentoSMSmAndamentoPessoaContato) {
        this.acionamentoSMSmAndamentoPessoaContato = acionamentoSMSmAndamentoPessoaContato;
    }

    /**
     * Nome: getListaSmsPadrao
     * Recupera o valor do atributo 'listaSmsPadrao'.
     *
     * @return valor do atributo 'listaSmsPadrao'
     * @see
     */
    public List<SelectItem> getListaSmsPadrao() {
        return listaSmsPadrao;
    }

    /**
     * Nome: setListaSmsPadrao
     * Registra o valor do atributo 'listaSmsPadrao'.
     *
     * @param listaSmsPadrao valor do atributo lista sms padrao
     * @see
     */
    public void setListaSmsPadrao(List<SelectItem> listaSmsPadrao) {
        this.listaSmsPadrao = listaSmsPadrao;
    }

    /**
     * Nome: getDisabledCmdEmailPessoaDeContatoCliente
     * Recupera o valor do atributo 'disabledCmdEmailPessoaDeContatoCliente'.
     *
     * @return valor do atributo 'disabledCmdEmailPessoaDeContatoCliente'
     * @see
     */
    public Boolean getDisabledCmdEmailPessoaDeContatoCliente() {
        return disabledCmdEmailPessoaDeContatoCliente;
    }

    /**
     * Nome: setDisabledCmdEmailPessoaDeContatoCliente
     * Registra o valor do atributo 'disabledCmdEmailPessoaDeContatoCliente'.
     *
     * @param disabledCmdEmailPessoaDeContatoCliente valor do atributo disabled cmd email pessoa de contato cliente
     * @see
     */
    public void setDisabledCmdEmailPessoaDeContatoCliente(Boolean disabledCmdEmailPessoaDeContatoCliente) {
        this.disabledCmdEmailPessoaDeContatoCliente = disabledCmdEmailPessoaDeContatoCliente;
    }

    /**
     * Nome: getDisabledCmdSmsPessoaDeContatoCliente
     * Recupera o valor do atributo 'disabledCmdSmsPessoaDeContatoCliente'.
     *
     * @return valor do atributo 'disabledCmdSmsPessoaDeContatoCliente'
     * @see
     */
    public Boolean getDisabledCmdSmsPessoaDeContatoCliente() {
        return disabledCmdSmsPessoaDeContatoCliente;
    }

    /**
     * Nome: setDisabledCmdSmsPessoaDeContatoCliente
     * Registra o valor do atributo 'disabledCmdSmsPessoaDeContatoCliente'.
     *
     * @param disabledCmdSmsPessoaDeContatoCliente valor do atributo disabled cmd sms pessoa de contato cliente
     * @see
     */
    public void setDisabledCmdSmsPessoaDeContatoCliente(Boolean disabledCmdSmsPessoaDeContatoCliente) {
        this.disabledCmdSmsPessoaDeContatoCliente = disabledCmdSmsPessoaDeContatoCliente;
    }

    /**
     * Nome: getDisabledCmdLigarPessoaContatoDoCliente
     * Recupera o valor do atributo 'disabledCmdLigarPessoaContatoDoCliente'.
     *
     * @return valor do atributo 'disabledCmdLigarPessoaContatoDoCliente'
     * @see
     */
    public Boolean getDisabledCmdLigarPessoaContatoDoCliente() {
        return disabledCmdLigarPessoaContatoDoCliente;
    }

    /**
     * Nome: setDisabledCmdLigarPessoaContatoDoCliente
     * Registra o valor do atributo 'disabledCmdLigarPessoaContatoDoCliente'.
     *
     * @param disabledCmdLigarPessoaContatoDoCliente valor do atributo disabled cmd ligar pessoa contato do cliente
     * @see
     */
    public void setDisabledCmdLigarPessoaContatoDoCliente(Boolean disabledCmdLigarPessoaContatoDoCliente) {
        this.disabledCmdLigarPessoaContatoDoCliente = disabledCmdLigarPessoaContatoDoCliente;
    }

    /**
     * Nome: getAcionamentoEmailAndamentoPessoaContato
     * Recupera o valor do atributo 'acionamentoEmailAndamentoPessoaContato'.
     *
     * @return valor do atributo 'acionamentoEmailAndamentoPessoaContato'
     * @see
     */
    public AcionamentoEmailVO getAcionamentoEmailAndamentoPessoaContato() {
        return acionamentoEmailAndamentoPessoaContato;
    }

    /**
     * Nome: setAcionamentoEmailAndamentoPessoaContato
     * Registra o valor do atributo 'acionamentoEmailAndamentoPessoaContato'.
     *
     * @param acionamentoEmailAndamentoPessoaContato valor do atributo acionamento email andamento pessoa contato
     * @see
     */
    public void setAcionamentoEmailAndamentoPessoaContato(
        AcionamentoEmailVO acionamentoEmailAndamentoPessoaContato) {
        this.acionamentoEmailAndamentoPessoaContato = acionamentoEmailAndamentoPessoaContato;
    }
}
