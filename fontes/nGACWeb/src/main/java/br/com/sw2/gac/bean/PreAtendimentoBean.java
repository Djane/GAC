package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.business.ParametroBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.socket.PhoneCommand;
import br.com.sw2.gac.socket.SocketPhone;
import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.socket.constants.TipoLigacao;
import br.com.sw2.gac.socket.exception.SocketConnectionException;
import br.com.sw2.gac.socket.exception.SocketException;
import br.com.sw2.gac.socket.exception.SocketLoginException;
import br.com.sw2.gac.tools.SinalDispositivo;
import br.com.sw2.gac.tools.StatusOcorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.LigacaoVO;
import br.com.sw2.gac.vo.MotivoPausaVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
import br.com.sw2.gac.vo.ParametroVO;
import br.com.sw2.gac.vo.TipoOcorrenciaVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Controller da tela de atendimento.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class PreAtendimentoBean extends BaseAtendimentoBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -7387330971721094361L;

    /** Atributo filtro. */
    private FiltroPesquisarPreAtendimento filtro = new FiltroPesquisarPreAtendimento();

    /** Atributo resultado pesquisa. */
    private List<ContratoVO> resultadoPesquisa;

    /** Atributo id pgd painel de alerta rendered. */
    private boolean idPgdPainelDeAlertaRendered = false;

    /** Atributo id pgd painel de alerta style. */
    private String idPgdPainelDeAlertaStyle = "";

    /** Atributo id pgd painel de alerta style. */
    private String idPgdPainelStatusLigacaoSemContratoStyle = "";

    /** Atributo id pgd painel status ligacao sem contrato message. */
    private String idPgdPainelStatusLigacaoSemContratoMessage = "";

    /** Atributo btn login value. */
    private String btnLoginValue = "Login";

    /** Atributo btn login disabled. */
    private boolean btnLoginDisabled = false;

    /** Atributo auto run monitor socket. */
    private boolean autoRunMonitorSocket = false;

    /** Atributo lbl tipo atendimento rendered. */
    private boolean lblTipoAtendimentoRendered = false;

    /** Atributo ocorrencia aberta. */
    private OcorrenciaVO ocorrenciaAberta = null;

    /** Atributo ocorrencia sem contrato. */
    private OcorrenciaVO ocorrenciaSemContrato = null;

    /** Atributo lista combo motivos pausa. */
    private List<SelectItem> listaComboMotivosPausa = new ArrayList<SelectItem>();

    /** Atributo motivo pausa selecionado. */
    private MotivoPausaVO motivoPausaSelecionado;

    /** Atributo id cmd registro sem contrato disabled. */
    private boolean idCmdRegistroSemContratoDisabled = true;

    /** Atributo atendente logado. */
    protected boolean atendenteLogado = false;
    
    private ParametroBusiness parametroBusiness = new ParametroBusiness();

    /**
     * Construtor Padrao Instancia um novo objeto PreAtendimentoBean.
     */
    public PreAtendimentoBean() {
        reset();

        //Verific se ja existe uma conexão socket ativa. Se tiver usa ela.
        SocketPhone socketPhoneSalvo = (SocketPhone) getSessionAttribute("socketPhone");
        if (null == this.socketPhone && null != socketPhoneSalvo) {
            this.socketPhone = socketPhoneSalvo;
            this.socketPhone.setEmAtendimento(false);
            if (this.socketPhone.isAtendenteAutenticado()) {
                this.btnLoginValue = "Logout";
                this.btnLoginDisabled = false;
            }

            this.logger.debug(getClass(), "***** Recuperando conexão socket ja existente *****");
        }

        this.autoRunMonitorSocket = getRequestBooleanAttribute("ativarMonitorSocket");
        //True indica que a página esta sendo chamada da página de registro de ocorrência.
        if (autoRunMonitorSocket && null != this.socketPhone) {
            this.socketPhone.setAlertaChamada(null);
            //Ramal (user)
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

            this.socketPhone.enviarMensagem(PhoneCommand.dgTimeStamp(this.getUsuarioLogado().getRamal()));
            this.socketPhone.enviarMensagem(PhoneCommand.agentPause(ramal, this.getUsuarioLogado().getRegistroAtendente(), false, 1));
            this.socketPhone.selecionarLinha(1);
        }

        atendenteDisponivel();
        this.ocorrenciaSemContrato = new OcorrenciaVO();
        this.ocorrenciaSemContrato.setTipoOcorrencia(new TipoOcorrenciaVO());

    }

    /**
     * Nome: atendenteDisponivel
     * Atendente disponivel.
     *
     * @see
     */
    private void atendenteDisponivel() {
        this.motivoPausaSelecionado = new MotivoPausaVO();
        this.motivoPausaSelecionado.setMotivoPausaId(-1);
    }

    /**
     * Nome: reset
     * Reset.
     *
     * @see
     */
    public void reset() {
        this.idPgdPainelDeAlertaStyle = "";
        if (null != this.socketPhone) {
            this.socketPhone.setAlertaChamada("");
        }
        this.btnLoginValue = "Login";
        this.btnLoginDisabled = false;
        this.socketPhone = null;
        this.listaComboMotivosPausa = new ArrayList<SelectItem>();
    }

    /**
     * Nome: pesquisarCliente Pesquisar cliente.
     * @param e the e
     * @see
     */
    public void pesquisarCliente(ActionEvent e) {
        this.logger.debug(getClass(), "***** Iniciando método pesquisarCliente(ActionEvent e) *****");
        this.logger.debug(getClass(), "***** Campos preenchidos ***** ");
        this.logger.debug(getClass(), "Numero contrato:" + filtro.getNumeroContrato());
        this.logger.debug(getClass(), "CPF do cliente: " + filtro.getNumeroCPFCliente());
        this.logger.debug(getClass(), "Telefone do cliente: " + filtro.getTelefone());
        this.logger.debug(getClass(), "Nome do cliente: " + filtro.getNomeCliente());

        try {
            this.resultadoPesquisa = this.ocorrenciaBusiness.pesquisarContratosPreAtendimento(filtro);
        } catch (BusinessException businessException) {
            if (businessException.getExceptionCode() == BusinessExceptionMessages.FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO
                .getValue().intValue()) {
                setFacesErrorMessage("message.preatendimento.filtro.empty");
            } else {
                setFacesErrorMessage("message.generic.system.unavailable");
            }
        }

        this.logger.debug(getClass(), "***** Finalizando método pesquisarCliente(ActionEvent e) *****");
    }

    /**
     * Nome: resetPesquisa Método responsável por limpar o resultado e o filtro utilizado na
     * pesquisa.
     * @param e the e
     * @see
     */
    public void resetPesquisa(ActionEvent e) {
        this.resultadoPesquisa = null;
        this.filtro = new FiltroPesquisarPreAtendimento();
    }

    /**
     * Nome: iniciarAtendimento Iniciar atendimento.
     * @return string
     * @see
     */
    public String iniciarAtendimento() {

        String toViewId;
        if (this.socketPhone == null || this.socketPhone.getSocket() == null && !this.socketPhone.isAtendenteAutenticado()) {
            setFacesErrorMessage("message.socketphone.agent.login.off");
            toViewId = null;
        } else {
            Integer numeroContrato = Integer.parseInt(getRequestParameter("numeroContratoAtender"));
            ContratoVO contrato = (ContratoVO) CollectionUtils.findByAttribute(this.resultadoPesquisa, "numeroContrato", numeroContrato);

            gerarOcorrencia(contrato, TipoOcorrencia.AtendimentoManual);

            if (null != this.socketPhone) {
                setSessionAttribute("socketPhone", socketPhone);
            }
            toViewId = "atendimento";
        }
        return toViewId;

    }

    /**
     * Nome: gerarOcorrencia
     * Gerar ocorrencia.
     *
     * @param contrato the contrato
     * @param tipoOcorrencia the tipo ocorrencia
     * @param codigoSinalDispositivo the codigo sinal dispositivo
     * @see
     */
    private void gerarOcorrencia(ContratoVO contrato, TipoOcorrencia tipoOcorrencia) {

        if (null == this.ocorrenciaAberta && null != contrato) {
            this.ocorrenciaAberta =  this.ocorrenciaBusiness.obterOcorrenciaPendenteDoCliente(contrato.getCliente());
        }

        OcorrenciaVO ocorrencia = new OcorrenciaVO();
        if (null == tipoOcorrencia) {
            if (null != this.getSocketPhone().getChamadaParaOAgente()) {
                String codigoEvento = this.getSocketPhone().getChamadaParaOAgente().getCodigoEvento();
                if (null == this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo()) {
                    tipoOcorrencia = TipoOcorrencia.Outros;
                    ocorrencia.setSnDispositivo(this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo());
                
                } else if (this.getSocketPhone().getChamadaParaOAgente().getNumeroDispositivo().intValue() < 8 && this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() > 0 && this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() < 7) {
                    tipoOcorrencia = TipoOcorrencia.Emergencia;
                    ocorrencia.setSnDispositivo(this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo());
                
                } else if (this.getSocketPhone().getChamadaParaOAgente().getNumeroDispositivo().intValue() == 9 && this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() == 2){
                    tipoOcorrencia = TipoOcorrencia.KeepAlive;
                    ocorrencia.setSnDispositivo(SinalDispositivo.EventoPeriodico.getValue());
                    ocorrencia.setDescricao("KEEPALIVE em andamento");
                
                } else if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.FaltaDeAlimentacaoEnergia.getValue().toString())){
                    tipoOcorrencia = TipoOcorrencia.AtendimentoAutomatico;
                    ocorrencia.setSnDispositivo(SinalDispositivo.FaltaDeAlimentacaoEnergia.getValue());
                    ocorrencia.setDescricao("Falta de alimentação de energia na central. Operando em modo bateria");
                
                } else if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.VoltaDeAlimentacaoEnergia.getValue().toString())){                
                    tipoOcorrencia = TipoOcorrencia.AtendimentoAutomatico;                                        
                
                } else if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.CaboTelefoneConectado.getValue().toString())){
                    tipoOcorrencia = TipoOcorrencia.AtendimentoAutomatico;                                   
             
                } else if (null != codigoEvento && codigoEvento.equals(SinalDispositivo.CaboTelefoneDesconectado.getValue().toString())){
                    tipoOcorrencia = TipoOcorrencia.AtendimentoAutomatico;                                   
                    ocorrencia.setSnDispositivo(SinalDispositivo.CaboTelefoneDesconectado.getValue());
                    ocorrencia.setDescricao("O Cabo de telefone está desconectado na central !");
             
                } else if (this.getSocketPhone().getChamadaParaOAgente().getNumeroDispositivo().intValue() == 9){
                    tipoOcorrencia = TipoOcorrencia.Emergencia;
                    ocorrencia.setSnDispositivo(this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo());
                }   
            } else {
                tipoOcorrencia = TipoOcorrencia.Outros;
                ocorrencia.setSnDispositivo(this.getSocketPhone().getChamadaParaOAgente().getCodigoSinalDispositivo());
            }              
        }              
        
        if (null == this.ocorrenciaAberta) {
            ocorrencia.setTipoOcorrencia(new TipoOcorrenciaVO(tipoOcorrencia));
            if (tipoOcorrencia.equals(TipoOcorrencia.Emergencia)) {
                ocorrencia.setCodigoPrioridade(3);
            } else if (tipoOcorrencia.equals(TipoOcorrencia.Outros)) {
                ocorrencia.setCodigoPrioridade(1);
            } else {
                ocorrencia.setCodigoPrioridade(2);
            }

            if (StringUtil.isEmpty(this.socketPhone.getAlertaChamada(), true) && tipoOcorrencia.equals(TipoOcorrencia.AtendimentoManual)) {
                this.socketPhone.setAlertaChamada(tipoOcorrencia.getLabel());
            }
            
            ocorrencia.setUsuario(new UsuarioVO());
            ocorrencia.getUsuario().setLogin(getUsuarioLogado().getLogin());

            if (this.getSocketPhone().getChamadaParaOAgente() != null) {
                ocorrencia.setDataAbertura(this.getSocketPhone().getChamadaParaOAgente().getDataHoraLigacao());
                ocorrencia.setDataHoraAberturaOcorrencia(this.getSocketPhone().getChamadaParaOAgente().getDataHorarAtendimento());
                ocorrencia.setDataHoraInicioContato(this.getSocketPhone().getChamadaParaOAgente().getDataHorarAtendimento());
            } else {
                ocorrencia.setDataAbertura(new Date());
                ocorrencia.setDataHoraAberturaOcorrencia(new Date());
            }

            ocorrencia.setStatusOcorrencia(StatusOcorrencia.EmAtendimento.getValue());

            ocorrencia.setContrato(contrato);
            if (null != this.getSocketPhone().getChamadaParaOAgente()) {
                ocorrencia.setIdLigacao(this.getSocketPhone().getChamadaParaOAgente().getIdUniqueid());
                ocorrencia.setNumerorTelefoneLigado(this.getSocketPhone().getChamadaParaOAgente().getNumeroTelefoneOrigem());
            }
            Integer codigoOcorrencia = this.ocorrenciaBusiness.gravarNovaOcorrencia(ocorrencia);
            ocorrencia.setIdOcorrencia(codigoOcorrencia);
            this.ocorrenciaAberta = ocorrencia;

        } else {
            ocorrencia = this.ocorrenciaAberta;
            ocorrencia.setContrato(contrato);
        }

        if (null != contrato && null != contrato.getCliente()) {
            List<OcorrenciaVO> listaOcorrenciasDoCliente = this.ocorrenciaBusiness.obterListaOcorrenciaDoCliente(contrato.getCliente());
            ocorrencia.setListaHistoricoOcorrencias(listaOcorrenciasDoCliente);
        }
        setSessionAttribute("atenderOcorrencia", ocorrencia);

    }

    /**
     * Nome: monitorSocket Monitor socket.
     * @see
     */
    public synchronized void monitorSocket() {

        int ramal = this.getUsuarioLogado().getRamal();

        this.autoRunMonitorSocket = false;

        if (null != this.socketPhone) {
            this.atendenteLogado = this.socketPhone.isAtendenteAutenticado();
            if (null == this.socketPhone.getSocket()) {
                dispatchError500(getMessageFromBundle("message.socketphone.error.connect.failed"));

            } else if (this.socketPhone.isRamalAtivo()) {
                if (this.socketPhone.isAtendenteAutenticado()) {

                    if (this.socketPhone.isAtendenteDisponivel()) {
                        atendenteDisponivel();
                    }

                    //Fica em loop ate achar um evento que necessita atualizar a tela.
                    try {
                        this.logger.debug(getClass(), "MonitorSocket da tela de pre-atendimento ********************");
                        List<Event> eventos = this.socketPhone.aguardarEvento(); //Escutando socket
                        for (Event eventoRecebido : eventos) {
                            tratarEvento(ramal, eventoRecebido);
                        }
                        addCallbackParam("stopMonitorChamadas", false);                                                 
                        addCallbackParam("keepAliveCentral", this.getSocketPhone().isKeepAliveDaCentral());
                        
                    } catch (Exception sce) {
                        logarErro(sce.getMessage());

                        reset();
                        addCallbackParam("stopMonitorChamadas", true);
                        addCallbackParam("hideDlgGacPhoneChamada", true);
                        addCallbackParam("loginSucess", false);
                        addCallbackParam("keepAliveCentral", false);
                        addCallbackParam("FaltaEnergiaNaCentral", false);                                                
                        addCallbackParam("retornoEnergiaNaCentral", false);
                        addCallbackParam("caboTelefoneConectadoNaCentral", false);
                        addCallbackParam("caboTelefoneDesConectadoNaCentral", false);
                        
                        removeSessionAttribute("socketPhone");
                        if (null != this.socketPhone) {
                            Line linhaCliente = (Line) CollectionUtils.findByAttribute(this.socketPhone .getLinhas(), "numeroLinha", 1);
                            linhaCliente.setSubNumeroDiscado("");
                            this.socketPhone.fecharConexaoSocket(ramal);
                        }
                        setFacesMessage("Parando de monitorar o socket de telefonia", false);
                    }

                } else {
                    dispatchError500(getMessageFromBundle("message.socketphone.agent.login.off"));
                }
            } else {
                dispatchError500(getMessageFromBundle("message.socketphone.user.login.off"));
            }
        } else {
            addCallbackParam("stopMonitorChamadas", true);
        }
        this.logger.debug(getClass(), "Finalizando monitorSocket da tela de pre-atendimento ********************");
    }

    /**
     * Nome: tratarEvento
     * Tratar evento recebido na tela de pre atendimento.
     *
     * @param ramal the ramal
     * @param eventoRecebido the evento recebido
     * @see
     */
    private void tratarEvento(int ramal, Event eventoRecebido) {

        if (null != eventoRecebido.getStatus()) {

            this.socketPhone.tratarEventoSocket(eventoRecebido);

            if (this.socketPhone.isAbandonoNaFila()) {
                this.socketPhone.setAbandonoNaFila(false);
                addCallbackParam("hideDlgGacPhoneChamada", true);
                this.logger.debug(getClass(), "##### Ligação perdida ************************");
            }

            if (this.socketPhone.isAgenteNaoAtende()) {
                this.btnLoginDisabled = false;
                this.lblTipoAtendimentoRendered = false;
                this.ocorrenciaAberta = null;
                this.socketPhone.setAgenteNaoAtende(false);
                addCallbackParam("hideDlgGacPhoneChamada", true);

                this.logger.debug(getClass(), "##### Ligação não atendida. Irá retornar a fila ");
            }

            if (this.socketPhone.isAgenteAtendeuLigacao()) {
                this.socketPhone.setAgenteAtendeuLigacao(false);
                this.btnLoginDisabled = true;
                this.lblTipoAtendimentoRendered = true;
                this.idCmdRegistroSemContratoDisabled = false;
                this.ocorrenciaAberta = null;
                this.idPgdPainelStatusLigacaoSemContratoStyle = "areaVerde";
                this.motivoPausaSelecionado = new MotivoPausaVO();
                this.motivoPausaSelecionado.setMotivoPausaId(1);

                if (null != socketPhone.getChamadaParaOAgente().getCodigoEvento() && 
                    socketPhone.getChamadaParaOAgente().getCodigoEvento().equalsIgnoreCase(SinalDispositivo.DispositivoNaoProgramado.getValue().toString())) {
                    this.idCmdRegistroSemContratoDisabled = true;    
                }
                
                try {
                    ContratoVO contratoDoCliente = null;
                    if (CollectionUtils.isNotEmptyOrNull(this.resultadoPesquisa) && this.resultadoPesquisa.size() == 1) {
                        //Se existe um unico contrato utiliza ele por padrão.
                        contratoDoCliente = this.resultadoPesquisa.get(0);
                    }
                    
                    this.gerarOcorrencia(contratoDoCliente, null);
                    Line linhaCliente = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroLinha", 1);
                    linhaCliente.setSubNumeroDiscado(this.getSocketPhone().getChamadaParaOAgente().getNumeroTelefoneOrigem());
                    linhaCliente.setStatusLinha(StatusLigacao.FALANDO.getValue());

                    this.socketPhone.enviarMensagem(PhoneCommand.agentPause(ramal, this.getUsuarioLogado().getRegistroAtendente(),
                        true, this.motivoPausaSelecionado.getMotivoPausaId()));

                    if (this.socketPhone.isKeepAliveDaCentral()) {      
                        ParametroVO parametro = this.parametroBusiness.recuperarParametros();
                        this.socketPhone.responderKeepAlive(ramal, parametro, "08");

                        //Gravar ocorrencia e liberar usuario para novo atendimento
                        this.ocorrenciaAberta.setStatusOcorrencia(StatusOcorrencia.Fechado.getValue());                    
                        this.ocorrenciaAberta.setResolucao("Executado procedimento de KEEPALIVE");                    
                        this.ocorrenciaAberta.setScript(null);
                        this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(ocorrenciaAberta);                    
                       
                        this.resetEventosCentralSocket();
                        
                        encerrarAtendimentoAutomatico(); 
                    
                    } else if (this.socketPhone.isEventoCaboTelefoneConectado()) {                            
                        this.ocorrenciaAberta.setStatusOcorrencia(StatusOcorrencia.Fechado.getValue());                    
                        this.ocorrenciaAberta.setResolucao("O cabo telefonico foi conectado.");
                        this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(ocorrenciaAberta);                    

                        this.resetEventosCentralSocket();                        
                        encerrarAtendimentoAutomatico();                                            
                        
                    } else if (this.socketPhone.isEventoCaboTelefoneDesconectado()) {
                        //Gravar ocorrencia e liberar usuario para novo atendimento
                        this.ocorrenciaAberta.setStatusOcorrencia(StatusOcorrencia.EmEspera.getValue());
                        this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(ocorrenciaAberta);                   
                       
                        this.resetEventosCentralSocket();
                        
                        encerrarAtendimentoAutomatico();                           
                        
                    } else if (this.socketPhone.isEventoFaltaEnergia()) {                        
                        //Gravar ocorrencia e liberar usuario para novo atendimento
                        this.ocorrenciaAberta.setStatusOcorrencia(StatusOcorrencia.EmEspera.getValue());                    
                        this.ocorrenciaAberta.setResolucao(null);                    
                        this.ocorrenciaAberta.setScript(null);
                        this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(ocorrenciaAberta);                   
                       
                        this.resetEventosCentralSocket();
                        
                        encerrarAtendimentoAutomatico();                         
                        
                    } else if (this.socketPhone.isEventoRetornoEnergia()) {                       
                        this.ocorrenciaAberta.setStatusOcorrencia(StatusOcorrencia.Fechado.getValue());                    
                        this.ocorrenciaAberta.setResolucao("Energia reestabelecida na central. Saindo do modo bateria");
                        this.ocorrenciaBusiness.salvarDadosOcorrenciaEmAtendimento(ocorrenciaAberta);                    

                        this.resetEventosCentralSocket();                        
                        encerrarAtendimentoAutomatico();                     }

                } catch (SocketException ex) {
                    this.logarErro(ex);
                }

                addCallbackParam("hideDlgGacPhoneChamada", true);
                this.logger.debug(getClass(), "Ligação atendida ************************");
            }

            if (this.getSocketPhone().isAgenteSendoChamado()) {
                this.getSocketPhone().setAgenteSendoChamado(false);
                // Com o ID da ligação, recupero os dados da ligação gravados no banco de dados.
                try {
                    //Recupera contratos associados ao numero do telefone que esta ligando.
                    FiltroPesquisarPreAtendimento filtro = new FiltroPesquisarPreAtendimento();

                    if (null == this.getSocketPhone().getChamadaParaOAgente()) {
                        filtro.setTelefone("Sem Registro");
                    } else {
                        filtro.setTelefone(this.getSocketPhone().getChamadaParaOAgente().getNumeroTelefoneOrigem());
                        
                        if (this.socketPhone.getChamadaParaOAgente() != null && 
                            this.socketPhone.getChamadaParaOAgente().getNumeroDispositivo() != null &&
                            this.socketPhone.getChamadaParaOAgente().getNumeroDispositivo() == 9 && 
                            this.socketPhone.getChamadaParaOAgente().getCodigoSinalDispositivo() != null && 
                            (
                                this.socketPhone.getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() == 2 || 
                                this.socketPhone.getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() == 3 ||
                                this.socketPhone.getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() == 4 ||
                                this.socketPhone.getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() == 6 ||    
                                this.socketPhone.getChamadaParaOAgente().getCodigoSinalDispositivo().intValue() == 7 
                            )) {
                            this.resultadoPesquisa = this.ocorrenciaBusiness.pesquisarContratosPorNumeroContatoCliente(this.socketPhone.getChamadaParaOAgente().getNumeroTelefoneOrigem());
                        } else {
                            this.resultadoPesquisa = this.ocorrenciaBusiness.pesquisarContratosPreAtendimento(filtro);
                        }
                        
                        
                        this.getSocketPhone().getChamadaParaOAgente().setListaContratosCliente(this.resultadoPesquisa);
                    }
                } catch (BusinessException e) {
                    this.logarErro(e.getMessage());
                }
                this.determinarTipoDeAcionamento(this.getSocketPhone().getChamadaParaOAgente());
            }

            if (null != eventoRecebido.getLine() && eventoRecebido.getLine().intValue() == 1) {
                Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroLinha", eventoRecebido.getLine());
                if (linha.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue().intValue()) {
                    this.idPgdPainelStatusLigacaoSemContratoStyle = "areaVerde";
                    this.idPgdPainelStatusLigacaoSemContratoMessage = StatusLigacao.FALANDO.getLabel();
                } else if (linha.getStatusLinha().intValue() == StatusLigacao.PAUSA.getValue().intValue()) {
                    this.idPgdPainelStatusLigacaoSemContratoStyle = "areaVermelha";
                    this.idPgdPainelStatusLigacaoSemContratoMessage = StatusLigacao.PAUSA.getLabel();
                }
            }
        }
        
        if (null != this.socketPhone.getConfigurarDispositivoRecuperado()) {
            ContratoVO contrato = this.getResultadoPesquisa().get(0);
            String cpf = contrato.getCliente().getCpf();
            String idDispositivo = contrato.getCliente().getListaDispositivos().get(0).getIdDispositivo();
            
            try {
                ContratoBusiness business = new ContratoBusiness();
                business.atualizarNumeroSequencialDispositivo(cpf, idDispositivo, this.socketPhone.getConfigurarDispositivoRecuperado());        
                this.getLogger().registrarAcao(this.getUsuarioLogado().getLogin(), "Registrando número de sequencia: " + this.socketPhone.getConfigurarDispositivoRecuperado() + " para o cliente com CPF: " + cpf + " id da central " + idDispositivo);
                this.getSocketPhone().setConfigurarDispositivoRecuperado(null);
            } catch (Exception e) {
                this.getLogger().registrarAcao(this.getUsuarioLogado().getLogin(), "Não foi possível registrar o número sequencial do dispositivo do cliente com CPF: " + cpf + " id da central " + idDispositivo + " : Exception: " + e.getMessage());
            }
        }
    }

    private void encerrarAtendimentoAutomatico() {
        
        this.socketPhone.setEmAtendimento(false);
        this.socketPhone.setAlertaChamada("");
        this.lblTipoAtendimentoRendered = false;
        this.idCmdRegistroSemContratoDisabled = true;
        this.ocorrenciaAberta = null;
        this.ocorrenciaSemContrato = new OcorrenciaVO();
        this.ocorrenciaSemContrato.setTipoOcorrencia(new TipoOcorrenciaVO());
        this.idPgdPainelDeAlertaStyle = "";
        this.btnLoginDisabled = false;
        this.resultadoPesquisa = null;
        
        atendenteDisponivel();
        super.encerrarLigacao(1);
        this.socketPhone.enviarMensagem(PhoneCommand.agentPause(
                                          this.getUsuarioLogado().getRamal(), 
                                          this.getUsuarioLogado().getRegistroAtendente(), false,
                                          this.motivoPausaSelecionado.getMotivoPausaId()));
        this.ocorrenciaAberta = null;
    }

    /**
     * Nome: determinarTipoDeAcionamento
     * Determinar tipo de acionamento.
     *
     * @param ligacao the ligacao
     * @see
     */
    private void determinarTipoDeAcionamento(LigacaoVO ligacao) {

        if (null == ligacao) {
            this.socketPhone.setAlertaChamada("Acionamento indefinido");
            this.idPgdPainelDeAlertaStyle = "areaVerde";

        } else {
            if (null == ligacao.getNumeroDispositivo()) {
                this.socketPhone.setAlertaChamada(ligacao.getNumeroTelefoneOrigem());
                this.idPgdPainelDeAlertaStyle = "areaVerde";

            } else  if (ligacao.getNumeroDispositivo() < 7 ) {
                for (SinalDispositivo item : SinalDispositivo.values()) {
                    if (item.getValue().equals(ligacao.getCodigoSinalDispositivo())) {
                        this.socketPhone.setAlertaChamada(item.getLabel());
                    }
                }
                this.idPgdPainelDeAlertaStyle = "areaVermelha";
            
            } else if (ligacao.getNumeroDispositivo() == 9) {    
                
                this.logger.debug(getClass(), "Identificado evento da central " + ligacao.getNumeroDispositivo()  + "-" + ligacao.getCodigoSinalDispositivo());
                
                
                //Eventos da central                
                Integer evento = null;
                try {
                    evento = Integer.parseInt(ligacao.getNumeroDispositivo().toString() + ligacao.getCodigoSinalDispositivo().toString());
                } catch (Exception e) {    
                    evento = 9999;
                }    
                
                SinalDispositivo enumEvento = null;
                
                for (SinalDispositivo item : SinalDispositivo.values()) {                    
                    if (item.getValue().equals(evento)) {
                        enumEvento = item;                        
                        this.logger.debug(getClass(), "ENUM para evento " + item);                        
                    }                    
                }
                
                if (null == enumEvento) {                    
                    this.socketPhone.setKeepAliveDaCentral(false);
                    this.socketPhone.setAlertaChamada("Acionamento indefinido");
                    this.idPgdPainelDeAlertaStyle = "areaVerde";
                
                } else if (enumEvento.equals(SinalDispositivo.EventoPeriodico)) {                    
                    this.idPgdPainelDeAlertaStyle = "areaVermelha";
                    this.socketPhone.setKeepAliveDaCentral(true);
                    this.socketPhone.atenderLigacaoParaAgente(this.getUsuarioLogado().getRamal());
                
                } else if (enumEvento.equals(SinalDispositivo.CaboTelefoneConectado)) {
                    this.socketPhone.setEventoCaboTelefoneConectado(true);
                    this.socketPhone.atenderLigacaoParaAgente(this.getUsuarioLogado().getRamal());
                
                } else if (enumEvento.equals(SinalDispositivo.CaboTelefoneDesconectado)) {                    
                    this.socketPhone.setEventoCaboTelefoneDesconectado(true);
                    this.socketPhone.atenderLigacaoParaAgente(this.getUsuarioLogado().getRamal());
                
                } else if (enumEvento.equals(SinalDispositivo.FaltaDeAlimentacaoEnergia)) {
                    this.idPgdPainelDeAlertaStyle = "areaVermelha";
                    this.socketPhone.setEventoFaltaEnergia(true);
                    this.socketPhone.atenderLigacaoParaAgente(this.getUsuarioLogado().getRamal());
                
                } else if (enumEvento.equals(SinalDispositivo.VoltaDeAlimentacaoEnergia)) {
                    this.idPgdPainelDeAlertaStyle = "areaVermelha";
                    this.socketPhone.setEventoRetornoEnergia(true);
                    this.socketPhone.atenderLigacaoParaAgente(this.getUsuarioLogado().getRamal());
                
                } else {                    
                    this.socketPhone.setAlertaChamada(enumEvento.getLabel());                
                    this.resetEventosCentralSocket();
                    this.idPgdPainelDeAlertaStyle = "areaVermelha";
                }
                
            } else if (null == ligacao.getCodigoEnviadoPulseira()) {
                this.socketPhone.setEventoFaltaEnergia(false);
                this.socketPhone.setEventoRetornoEnergia(false);
                this.socketPhone.setKeepAliveDaCentral(false);
                this.socketPhone.setAlertaChamada("Recebendo ligação de " + ligacao.getNumeroTelefoneOrigem());
                this.idPgdPainelDeAlertaStyle = "areaVerde";                
            } else {
                this.socketPhone.setEventoFaltaEnergia(false);
                this.socketPhone.setEventoRetornoEnergia(false);
                this.socketPhone.setKeepAliveDaCentral(false);
                this.socketPhone.setAlertaChamada("Ligação com evento não definido");
                this.idPgdPainelDeAlertaStyle = "areaVerde";                 
            }            
        }
        
        //Callbacks apra informar a tela que modal de aguarde exibir
        if (this.socketPhone.isKeepAliveDaCentral()) {
            addCallbackParam("avisoDeChamada", false);
            addCallbackParam("keepAliveCentral", true);        
            addCallbackParam("faltaEnergiaNaCentral", false);
            addCallbackParam("retornoEnergiaNaCentral", false);
        
        } else if (this.socketPhone.isEventoFaltaEnergia()) {
            this.resetEventosCentralSocket();    
            this.socketPhone.setEventoFaltaEnergia(true);
            addCallbackParam("faltaEnergiaNaCentral", true);            
        
        } else if (this.socketPhone.isEventoRetornoEnergia()) {
            this.resetEventosCentralSocket();
            this.socketPhone.setEventoRetornoEnergia(true);
            addCallbackParam("retornoEnergiaNaCentral", true);            

        } else if (this.socketPhone.isEventoCaboTelefoneDesconectado()) {  
            this.resetEventosCentralSocket();
            this.socketPhone.setEventoCaboTelefoneDesconectado(true);
            addCallbackParam("caboTelefoneDesConectadoNaCentral", true);
            
        } else if (this.socketPhone.isEventoCaboTelefoneConectado()) {  
            this.resetEventosCentralSocket();
            this.socketPhone.setEventoCaboTelefoneConectado(true);
            addCallbackParam("caboTelefoneConectadoNaCentral", true);            
            
        } else {
            addCallbackParam("avisoDeChamada", true);
            addCallbackParam("keepAliveCentral", false);
            addCallbackParam("faltaEnergiaNaCentral", false);
            addCallbackParam("retornoEnergiaNaCentral", false);
        }
    }

    /**
     * Nome: conectarSocketServer Conectar socket server.
     *
     * @throws SocketConnectionException the socket connection exception
     * @see
     */
    private void conectarSocketServer() throws SocketConnectionException {
        this.logger.debug(getClass(), "***** Conectando com o servidor doscket *****");
        if (null == socketPhone) {
            try {
                this.socketPhone = new SocketPhone();

                String host = this.getGACProperty("socket.softphone.address").trim();
                int port = Integer.parseInt(this.getGACProperty("socket.softphone.port").trim());
                this.logger.debug(getClass(), "Endereço de conexão: " + host + " porta: " + port);
                this.socketPhone.conectarAoSocketServer(host, port);
                this.logger.debug(getClass(), "***** Conectado ao servidor socket  *****");
            } catch (Exception e) {
                reset();
                throw new SocketConnectionException(getMessageFromBundle("message.socketphone.error.connect.failed"), e);
            }
        }
    }

    /**
     * Nome: disponibilidadeAtendente
     * Disponibilidade atendente.
     *
     * @see
     */
    public void disponibilidadeAtendente() {
    	if (null == this.socketPhone || !this.socketPhone.isAtendenteAutenticado()) {
    		setFacesMessage("message.socketphone.agent.login.off");
    	} else {

    	    boolean ficarIndisponivel = true;
    	    int motivo = this.motivoPausaSelecionado.getMotivoPausaId().intValue();
    	    if (this.motivoPausaSelecionado.getMotivoPausaId().intValue() < 0) {
    	        ficarIndisponivel = false;
    	    }

            this.socketPhone.enviarMensagem(PhoneCommand.agentPause(this.getUsuarioLogado()
                .getRamal(), this.getUsuarioLogado().getRegistroAtendente(), ficarIndisponivel, motivo));

    	}
    }    

    /**
     * Nome: logarAtendente
     * Logar atendente.
     *
     * @param event the event
     * @see
     */
    public void loginLogoutAtendente(ActionEvent event) {

        if (this.btnLoginValue.equals("Login")) {
            this.logger.debug(getClass(), "***** Iniciando processo de login do atendente (agente): "
                + this.getUsuarioLogado().getRegistroAtendente());
            //Iniciar servidor socket.
            try {

                if (null == this.socketPhone) {
                    conectarSocketServer();
                } else {
                    this.logger.debug(getClass(), "Utilizando conexão socket existente. Não foi necessário reconectar *****");
                }

                //Ativar ramal
                try {
                    this.socketPhone.login(this.getUsuarioLogado().getRamal());
                } catch (SocketLoginException e) {
                    logarErro(e);
                    String key = "message.socketphone.error." + e.getExceptionCode().toString().replace("_", ".").toLowerCase();
                    logarErro("Chave da mensagem de parametro do login do ramal: " + key);
                    logarErro("Exception code do login do ramal: " + e.getExceptionCode());
                    if (e.getExceptionCode().getCode() < SocketLoginException.ExceptionCode.UNDEFINED.getCode()) {
                        String mensagemParametro = getMessageFromBundle(key);
                        this.logarErro("Mensagem de parametro da exception de login do ramal: " + mensagemParametro);
                        throw new SocketLoginException(getMessageFromBundle("message.socketphone.error.user.login.failed", mensagemParametro));
                    } else {
                        throw new SocketLoginException(getMessageFromBundle("message.socketphone.error.user.login.failed", ""));
                    }
                }

                //logar atendente
                try {
                    this.socketPhone.loginAgente(getUsuarioLogado().getRegistroAtendente(), getUsuarioLogado().getSenha());
                } catch (SocketLoginException e) {
                    this.logarErro("Erro ao logar atendente (Agente");
                    this.logarErro(e.getMessage());
                    this.logarErro("Exception code do login do atendente (agente): " + e.getExceptionCode());
                    if (e.getExceptionCode().equals(SocketLoginException.ExceptionCode.UNDEFINED)) {
                        throw new SocketException(getMessageFromBundle("message.socketphone.error.agent.login.failed"));
                    } else {
                        String key = "message.socketphone.error." + e.getExceptionCode().toString().replace("_", ".").toLowerCase();
                        String mensagem = getMessageFromBundle("message.socketphone.error.agent.login.failed")
                            + " (" + getMessageFromBundle(key) + ")";
                        throw new SocketLoginException(mensagem);
                    }
                }

                Line line = (Line) CollectionUtils.findByAttribute(this.socketPhone.getLinhas(), "numeroLinha", 1);
                line.setStatusLinha(StatusLigacao.PAUSA.getValue());
                line.setNumeroDiscado(this.socketPhone.getNumeroDiscagemLoginAgente());
                line.setTipoLigacao(TipoLigacao.INDEFINIDO.getValue());
                this.btnLoginValue = "Logout";
                this.socketPhone.setQtdeLigacoesFilaAtendimentoEmergencia(0);
                addCallbackParam("loginSucess", true);
                this.listaComboMotivosPausa = getSelectItems(this.socketPhone.getListaMotivosPausa(), "motivoPausaId", "descricaoMotivoPausa");
                atendenteDisponivel();
            } catch (SocketConnectionException e) {
                dispatchError500(e.getMessage());
            } catch (SocketLoginException e) {
                this.socketPhone.fecharConexaoSocket(getUsuarioLogado().getRamal());
                reset();
                addCallbackParam("loginSucess", false);
                dispatchErrorAtendimento(e.getMessage());
            } catch (SocketException e) {
                this.socketPhone.fecharConexaoSocket(getUsuarioLogado().getRamal());
                reset();
                addCallbackParam("loginSucess", false);
                dispatchError500(e.getMessage());
            }
            if (null != this.socketPhone) {
                this.atendenteLogado = this.socketPhone.isAtendenteAutenticado();
            }
        } else {
            if (null != this.socketPhone) {
                this.socketPhone.fecharConexaoSocket(getUsuarioLogado().getRamal());
            }
            reset();
            addCallbackParam("loginSucess", false);
            this.atendenteLogado = false;
            addCallbackParam("stopMonitorChamadas", true);
            removeSessionAttribute("socketPhone");
        }
    }

    /**
     * Nome: atenderChamada
     * Atender chamada.
     *
     * @param e the e
     * @see
     */
    public void atenderChamada(ActionEvent e) {
        this.socketPhone.atenderLigacaoParaAgente(this.getUsuarioLogado().getRamal());
    }

    /**
     * Nome: encerrarPreAtendimento
     * Encerrar pre atendimento.
     *
     * @return string
     *
     * @see
     */
    public String encerrarPreAtendimento() {
        this.encerrarSocketPhone();
        return "menuPrincipal";
    }

    /**
     * Nome: encerrarSocketPhone
     * Encerrar socket phone.
     *
     * @see
     */
    public void encerrarSocketPhone() {
        this.logger.debug(getClass(), "Encerrando socketphone ***************************");
        if (null != this.socketPhone && null != this.socketPhone.getSocket()) {
            this.socketPhone.fecharConexaoSocket(this.getUsuarioLogado().getRamal());
            removeSessionAttribute("socketPhone");
            this.socketPhone = null;
        }
        this.logger.debug(getClass(), "Fim do Encerrando socketphone ***************************");
    }

    /**
     * Nome: salvarDadosOcorrencia
     * Salvar dados ocorrencia.
     *
     * @param e the e
     * @see
     */
    public void salvarDadosOcorrencia(ActionEvent e) {
        this.ocorrenciaAberta.setTipoOcorrencia(this.ocorrenciaSemContrato.getTipoOcorrencia());
        this.ocorrenciaAberta.setStatusOcorrencia(this.ocorrenciaSemContrato.getStatusOcorrencia());
        this.ocorrenciaAberta.setResolucao(this.ocorrenciaSemContrato.getResolucao());
        this.ocorrenciaAberta.setDescricao(this.ocorrenciaSemContrato.getDescricao());
        
        this.ocorrenciaAberta.setDataHoraTerminoContato(new Date());
        if (this.salvarDadosOcorrencia(this.ocorrenciaAberta)) {
            this.socketPhone.setEmAtendimento(false);
            this.socketPhone.setAlertaChamada("");
            this.lblTipoAtendimentoRendered = false;
            this.idCmdRegistroSemContratoDisabled = true;
            this.ocorrenciaAberta = null;
            this.ocorrenciaSemContrato = new OcorrenciaVO();
            this.ocorrenciaSemContrato.setTipoOcorrencia(new TipoOcorrenciaVO());
            this.idPgdPainelDeAlertaStyle = "";
            this.btnLoginDisabled = false;

            encerrarAtendimentoAutomatico();
        }

    }

    /**
     * Nome: encerrarLigacaoSemContrato
     * Encerrar ligacao sem contrato.
     *
     * @param e the e
     * @see
     */
    public void pausarLigacaoSemContrato(ActionEvent e) {
        this.socketPhone.colocarRemoverChamadaEmEspera(1);
    }

    /**
     * Nome: encerrarLigacaoSemContrato
     * Encerrar ligacao sem contrato.
     *
     * @param e the e
     * @see
     */
    public void encerrarLigacaoSemContrato(ActionEvent e) {
        this.idPgdPainelStatusLigacaoSemContratoMessage = StatusLigacao.LIVRE.getLabel();
        this.resultadoPesquisa = null;
        super.encerrarLigacao(1);
    }

    /**
     * Nome: getFiltro
     * Recupera o valor do atributo 'filtro'.
     *
     * @return valor do atributo 'filtro'
     * @see
     */
    public FiltroPesquisarPreAtendimento getFiltro() {
        return filtro;
    }

    /**
     * Nome: setFiltro
     * Registra o valor do atributo 'filtro'.
     *
     * @param filtro valor do atributo filtro
     * @see
     */
    public void setFiltro(FiltroPesquisarPreAtendimento filtro) {
        this.filtro = filtro;
    }

    /**
     * Nome: getResultadoPesquisa
     * Recupera o valor do atributo 'resultadoPesquisa'.
     *
     * @return valor do atributo 'resultadoPesquisa'
     * @see
     */
    public List<ContratoVO> getResultadoPesquisa() {
        return resultadoPesquisa;
    }

    /**
     * Nome: setResultadoPesquisa
     * Registra o valor do atributo 'resultadoPesquisa'.
     *
     * @param resultadoPesquisa valor do atributo resultado pesquisa
     * @see
     */
    public void setResultadoPesquisa(List<ContratoVO> resultadoPesquisa) {
        this.resultadoPesquisa = resultadoPesquisa;
    }

    /**
     * Nome: isIdPgdPainelDeAlertaRendered
     * Verifica se e id pgd painel de alerta rendered.
     *
     * @return true, se for id pgd painel de alerta rendered senão retorna false
     * @see
     */
    public boolean isIdPgdPainelDeAlertaRendered() {
        return idPgdPainelDeAlertaRendered;
    }

    /**
     * Nome: setIdPgdPainelDeAlertaRendered
     * Registra o valor do atributo 'idPgdPainelDeAlertaRendered'.
     *
     * @param idPgdPainelDeAlertaRendered valor do atributo id pgd painel de alerta rendered
     * @see
     */
    public void setIdPgdPainelDeAlertaRendered(boolean idPgdPainelDeAlertaRendered) {
        this.idPgdPainelDeAlertaRendered = idPgdPainelDeAlertaRendered;
    }

    /**
     * Nome: getIdPgdPainelDeAlertaStyle
     * Recupera o valor do atributo 'idPgdPainelDeAlertaStyle'.
     *
     * @return valor do atributo 'idPgdPainelDeAlertaStyle'
     * @see
     */
    public String getIdPgdPainelDeAlertaStyle() {
        return idPgdPainelDeAlertaStyle;
    }

    /**
     * Nome: setIdPgdPainelDeAlertaStyle
     * Registra o valor do atributo 'idPgdPainelDeAlertaStyle'.
     *
     * @param idPgdPainelDeAlertaStyle valor do atributo id pgd painel de alerta style
     * @see
     */
    public void setIdPgdPainelDeAlertaStyle(String idPgdPainelDeAlertaStyle) {
        this.idPgdPainelDeAlertaStyle = idPgdPainelDeAlertaStyle;
    }

    /**
     * Nome: getBtnLoginValue
     * Recupera o valor do atributo 'btnLoginValue'.
     *
     * @return valor do atributo 'btnLoginValue'
     * @see
     */
    public String getBtnLoginValue() {
        return btnLoginValue;
    }

    /**
     * Nome: setBtnLoginValue
     * Registra o valor do atributo 'btnLoginValue'.
     *
     * @param btnLoginValue valor do atributo btn login value
     * @see
     */
    public void setBtnLoginValue(String btnLoginValue) {
        this.btnLoginValue = btnLoginValue;
    }

    /**
     * Nome: isBtnLoginDisabled
     * Verifica se e btn login disabled.
     *
     * @return true, se for btn login disabled senão retorna false
     * @see
     */
    public boolean isBtnLoginDisabled() {
        return btnLoginDisabled;
    }

    /**
     * Nome: setBtnLoginDisabled
     * Registra o valor do atributo 'btnLoginDisabled'.
     *
     * @param btnLoginDisabled valor do atributo btn login disabled
     * @see
     */
    public void setBtnLoginDisabled(boolean btnLoginDisabled) {
        this.btnLoginDisabled = btnLoginDisabled;
    }

    /**
     * Nome: getOcorrenciaAberta
     * Recupera o valor do atributo 'ocorrenciaAberta'.
     *
     * @return valor do atributo 'ocorrenciaAberta'
     * @see
     */
    public OcorrenciaVO getOcorrenciaAberta() {
        return ocorrenciaAberta;
    }

    /**
     * Nome: setOcorrenciaAberta
     * Registra o valor do atributo 'ocorrenciaAberta'.
     *
     * @param ocorrenciaAberta valor do atributo ocorrencia aberta
     * @see
     */
    public void setOcorrenciaAberta(OcorrenciaVO ocorrenciaAberta) {
        this.ocorrenciaAberta = ocorrenciaAberta;
    }


    /**
     * Nome: simularchamada
     * Simularchamada.
     *
     * @param e the e
     * @see
     */
  /*  public void simularchamada(ActionEvent e) {
        this.socketPhone.enviarMensagem(PhoneCommand.selecionarLinha(this.getUsuarioLogado().getRamal(), 6));
        this.socketPhone.enviarMensagem(PhoneCommand.discar("4031", this.getUsuarioLogado().getRamal(), 6));
        Line linha = (Line) CollectionUtils.findByAttribute(this.socketPhone .getLinhas(), "numeroLinha", 6);
        linha.setStatusLinha(StatusLigacao.PAUSA.getValue());
        this.logger.debug(getClass(), "discagem feita para testes de AgentCalled");
    }*/

    /**
     * Nome: isAutoRunMonitorSocket Verifica se e auto run monitor socket.
     * @return true, se for auto run monitor socket senão retorna false
     * @see
     */
    public boolean isAutoRunMonitorSocket() {
        return autoRunMonitorSocket;
    }

    /**
     * Nome: setAutoRunMonitorSocket
     * Registra o valor do atributo 'autoRunMonitorSocket'.
     *
     * @param autoRunMonitorSocket valor do atributo auto run monitor socket
     * @see
     */
    public void setAutoRunMonitorSocket(boolean autoRunMonitorSocket) {
        this.autoRunMonitorSocket = autoRunMonitorSocket;
    }

    /**
     * Nome: isLblTipoAtendimentoRendered
     * Verifica se e lbl tipo atendimento rendered.
     *
     * @return true, se for lbl tipo atendimento rendered senão retorna false
     * @see
     */
    public boolean isLblTipoAtendimentoRendered() {
        return lblTipoAtendimentoRendered;
    }

    /**
     * Nome: setLblTipoAtendimentoRendered
     * Registra o valor do atributo 'lblTipoAtendimentoRendered'.
     *
     * @param lblTipoAtendimentoRendered valor do atributo lbl tipo atendimento rendered
     * @see
     */
    public void setLblTipoAtendimentoRendered(boolean lblTipoAtendimentoRendered) {
        this.lblTipoAtendimentoRendered = lblTipoAtendimentoRendered;
    }

    /**
     * Nome: getListaComboMotivosPausa
     * Recupera o valor do atributo 'listaComboMotivosPausa'.
     *
     * @return valor do atributo 'listaComboMotivosPausa'
     * @see
     */
    public List<SelectItem> getListaComboMotivosPausa() {
        return listaComboMotivosPausa;
    }

    /**
     * Nome: setListaComboMotivosPausa
     * Registra o valor do atributo 'listaComboMotivosPausa'.
     *
     * @param listaComboMotivosPausa valor do atributo lista combo motivos pausa
     * @see
     */
    public void setListaComboMotivosPausa(List<SelectItem> listaComboMotivosPausa) {
        this.listaComboMotivosPausa = listaComboMotivosPausa;
    }

    /**
     * Nome: getMotivoPausaSelecionado
     * Recupera o valor do atributo 'motivoPausaSelecionado'.
     *
     * @return valor do atributo 'motivoPausaSelecionado'
     * @see
     */
    public MotivoPausaVO getMotivoPausaSelecionado() {
        return motivoPausaSelecionado;
    }

    /**
     * Nome: setMotivoPausaSelecionado
     * Registra o valor do atributo 'motivoPausaSelecionado'.
     *
     * @param motivoPausaSelecionado valor do atributo motivo pausa selecionado
     * @see
     */
    public void setMotivoPausaSelecionado(MotivoPausaVO motivoPausaSelecionado) {
        this.motivoPausaSelecionado = motivoPausaSelecionado;
    }

    /**
     * Nome: getOcorrenciaSemContrato
     * Recupera o valor do atributo 'ocorrenciaSemContrato'.
     *
     * @return valor do atributo 'ocorrenciaSemContrato'
     * @see
     */
    public OcorrenciaVO getOcorrenciaSemContrato() {
        return ocorrenciaSemContrato;
    }

    /**
     * Nome: setOcorrenciaSemContrato
     * Registra o valor do atributo 'ocorrenciaSemContrato'.
     *
     * @param ocorrenciaSemContrato valor do atributo ocorrencia sem contrato
     * @see
     */
    public void setOcorrenciaSemContrato(OcorrenciaVO ocorrenciaSemContrato) {
        this.ocorrenciaSemContrato = ocorrenciaSemContrato;
    }

    /**
     * Nome: isIdCmdRegistroSemContratoDisabled
     * Verifica se e id cmd registro sem contrato disabled.
     *
     * @return true, se for id cmd registro sem contrato disabled senão retorna false
     * @see
     */
    public boolean isIdCmdRegistroSemContratoDisabled() {
        return idCmdRegistroSemContratoDisabled;
    }

    /**
     * Nome: setIdCmdRegistroSemContratoDisabled
     * Registra o valor do atributo 'idCmdRegistroSemContratoDisabled'.
     *
     * @param idCmdRegistroSemContratoDisabled valor do atributo id cmd registro sem contrato disabled
     * @see
     */
    public void setIdCmdRegistroSemContratoDisabled(boolean idCmdRegistroSemContratoDisabled) {
        this.idCmdRegistroSemContratoDisabled = idCmdRegistroSemContratoDisabled;
    }

    /**
     * Nome: isAtendenteLogado
     * Verifica se e atendente logado.
     *
     * @return true, se for atendente logado senão retorna false
     * @see
     */
    public boolean isAtendenteLogado() {
        return atendenteLogado;
    }

    /**
     * Nome: setAtendenteLogado
     * Registra o valor do atributo 'atendenteLogado'.
     *
     * @param atendenteLogado valor do atributo atendente logado
     * @see
     */
    public void setAtendenteLogado(boolean atendenteLogado) {
        this.atendenteLogado = atendenteLogado;
    }

    /**
     * Nome: getIdPgdPainelStatusLigacaoSemContratoStyle
     * Recupera o valor do atributo 'idPgdPainelStatusLigacaoSemContratoStyle'.
     *
     * @return valor do atributo 'idPgdPainelStatusLigacaoSemContratoStyle'
     * @see
     */
    public String getIdPgdPainelStatusLigacaoSemContratoStyle() {
        return idPgdPainelStatusLigacaoSemContratoStyle;
    }

    /**
     * Nome: setIdPgdPainelStatusLigacaoSemContratoStyle
     * Registra o valor do atributo 'idPgdPainelStatusLigacaoSemContratoStyle'.
     *
     * @param idPgdPainelStatusLigacaoSemContratoStyle valor do atributo id pgd painel status ligacao sem contrato style
     * @see
     */
    public void setIdPgdPainelStatusLigacaoSemContratoStyle(
        String idPgdPainelStatusLigacaoSemContratoStyle) {
        this.idPgdPainelStatusLigacaoSemContratoStyle = idPgdPainelStatusLigacaoSemContratoStyle;
    }

    /**
     * Nome: getIdPgdPainelStatusLigacaoSemContratoMessage
     * Recupera o valor do atributo 'idPgdPainelStatusLigacaoSemContratoMessage'.
     *
     * @return valor do atributo 'idPgdPainelStatusLigacaoSemContratoMessage'
     * @see
     */
    public String getIdPgdPainelStatusLigacaoSemContratoMessage() {
        return idPgdPainelStatusLigacaoSemContratoMessage;
    }

    /**
     * Nome: setIdPgdPainelStatusLigacaoSemContratoMessage
     * Registra o valor do atributo 'idPgdPainelStatusLigacaoSemContratoMessage'.
     *
     * @param idPgdPainelStatusLigacaoSemContratoMessage valor do atributo id pgd painel status ligacao sem contrato message
     * @see
     */
    public void setIdPgdPainelStatusLigacaoSemContratoMessage(
        String idPgdPainelStatusLigacaoSemContratoMessage) {
        this.idPgdPainelStatusLigacaoSemContratoMessage = idPgdPainelStatusLigacaoSemContratoMessage;
    }

    private void resetEventosCentralSocket() {
        this.socketPhone.setKeepAliveDaCentral(false);
        this.socketPhone.setEventoCaboTelefoneConectado(false);
        this.socketPhone.setEventoCaboTelefoneDesconectado(false);
        this.socketPhone.setEventoRetornoEnergia(false);
        this.socketPhone.setEventoFaltaEnergia(false);        
    }
    
}
