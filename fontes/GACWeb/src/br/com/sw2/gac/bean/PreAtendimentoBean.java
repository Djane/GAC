package br.com.sw2.gac.bean;

import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.OcorrenciaBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.filtro.FiltroPesquisarPreAtendimento;
import br.com.sw2.gac.socket.Event;
import br.com.sw2.gac.socket.SocketException;
import br.com.sw2.gac.socket.SocketPhone;
import br.com.sw2.gac.tools.SinalDispositivo;
import br.com.sw2.gac.tools.StatusOcorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.LigacaoVO;
import br.com.sw2.gac.vo.OcorrenciaVO;
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
public class PreAtendimentoBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -7387330971721094361L;

    /** Atributo filtro. */
    private FiltroPesquisarPreAtendimento filtro = new FiltroPesquisarPreAtendimento();

    /** Atributo resultado pesquisa. */
    private List<ContratoVO> resultadoPesquisa;

    /** Atributo ocorrencia business. */
    private OcorrenciaBusiness ocorrenciaBusiness = new OcorrenciaBusiness();

    /** Atributo id pgd painel de alerta rendered. */
    private boolean idPgdPainelDeAlertaRendered = false;

    /** Atributo alerta chamada. */
    private String alertaChamada = "";

    /** Atributo socket client. */
    private SocketPhone socketPhone = null;

    /** Atributo id pgd painel de alerta style. */
    private String idPgdPainelDeAlertaStyle = "";

    /** Atributo btn login value. */
    private String btnLoginValue = "Login";

    /** Atributo btn login disabled. */
    private boolean btnLoginDisabled = false;

    /** Atributo btn break disabled. */
    private boolean btnBreakDisabled = false;

    /** Atributo btn disponibilidade value. */
    private String btnDisponibilidadeValue = "Indisponível";

    /** Atributo btn disponibilidade disabled. */
    private boolean btnDisponibilidadeDisabled = false;

    /**
     * Construtor Padrao Instancia um novo objeto PreAtendimentoBean.
     */
    public PreAtendimentoBean() {
        reset();
    }

    /**
     * Nome: reset
     * Reset.
     *
     * @see
     */
    public void reset() {
        this.idPgdPainelDeAlertaStyle = "";
        this.alertaChamada = "";
        this.btnDisponibilidadeValue = "Indisponível";
        this.btnDisponibilidadeDisabled = false;
        this.btnLoginValue = "Login";
        this.btnLoginDisabled = false;
        this.btnBreakDisabled = false;
        this.socketPhone = null;
    }

    /**
     * Nome: pesquisarCliente Pesquisar cliente.
     * @param e the e
     * @see
     */
    public void pesquisarCliente(ActionEvent e) {
        this.getLogger().debug("***** Iniciando método pesquisarCliente(ActionEvent e) *****");
        this.getLogger().debug("***** Campos preenchidos ***** ");
        this.getLogger().debug("Numero contrato:" + filtro.getNumeroContrato());
        this.getLogger().debug("CPF do cliente: " + filtro.getNumeroCPFCliente());
        this.getLogger().debug("Telefone do cliente: " + filtro.getTelefone());
        this.getLogger().debug("Nome do cliente: " + filtro.getNomeCliente());

        try {
            this.resultadoPesquisa = this.ocorrenciaBusiness
                .pesquisarContratosPreAtendimento(filtro);
        } catch (BusinessException businessException) {
            if (businessException.getExceptionCode() == BusinessExceptionMessages.FILTRO_PESQUISA_PRE_ATENDIMENTO_NAO_INFORMADO
                .getValue().intValue()) {
                setFacesErrorMessage("message.preatendimento.filtro.empty");
            } else {
                setFacesErrorMessage("message.generic.system.unavailable");
            }
        }

        this.getLogger().debug("***** Finalizando método pesquisarCliente(ActionEvent e) *****");
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

        Integer numeroContrato = Integer.parseInt(getRequestParameter("numeroContratoAtender"));
        ContratoVO contrato = (ContratoVO) CollectionUtils.findByAttribute(this.resultadoPesquisa,
            "numeroContrato", numeroContrato);

        // Inicia a gravação do registo na tabela de ocorencias (Gerar Fila)
        OcorrenciaVO ocorrenciaAberta = this.ocorrenciaBusiness
            .obterOcorrenciaPendenteDoCliente(contrato.getCliente());
        OcorrenciaVO ocorrencia = new OcorrenciaVO();
        if (null == ocorrenciaAberta) {
            ocorrencia.setTipoOcorrencia(new TipoOcorrenciaVO(TipoOcorrencia.AtendimentoManual));
            ocorrencia.setUsuario(new UsuarioVO());
            ocorrencia.getUsuario().setLogin(getUsuarioLogado().getLogin());
            ocorrencia.setDataAbertura(new Date());
            ocorrencia.setStatusOcorrencia(StatusOcorrencia.EmAtendimento.getValue());
            ocorrencia.setCodigoPrioridade(2);
            ocorrencia.setContrato(contrato);
            Integer codigoOcorrencia = this.ocorrenciaBusiness.gravarNovaOcorrencia(ocorrencia);
            ocorrencia.setIdOcorrencia(codigoOcorrencia);
        } else {
            ocorrencia = ocorrenciaAberta;
            ocorrencia.setContrato(contrato);
        }

        List<OcorrenciaVO> listaOcorrenciasDoCliente = this.ocorrenciaBusiness
            .obterListaOcorrenciaDoCliente(contrato.getCliente());
        ocorrencia.setListaHistoricoOcorrencias(listaOcorrenciasDoCliente);
        setSessionAttribute("atenderOcorrencia", ocorrencia);
        return "atendimento";

    }

    /**
     * Nome: monitorSocket Monitor socket.
     * @see
     */
    public synchronized void monitorSocket() {
        this.getLogger().debug("***** Iniciando escuta pelo método monitorSocket().  liberadaLeituraSocket:");
        boolean stopMonitorChamadas = true;
        if (null != this.socketPhone) {
            if (null == this.socketPhone.getSocket()) {
                dispatchError500("Não é possível estabelecer conexão com o servidor de telefonia.");
            } else if (this.socketPhone.isRamalAtivo()) {
                if (this.socketPhone.isAtendenteAutenticado()) {
                    Event eventoRecebido = this.socketPhone.escutar();

                    if (null != eventoRecebido) {
                        this.getLogger().debug("Evento recebido:" + eventoRecebido.getEvent());
                        this.getLogger().debug(
                            "Status do evento recebido:" + eventoRecebido.getStatus());
                        this.getLogger().debug("Uniqueid recebido:" + eventoRecebido.getUniqueid());
                    } else {
                        this.getLogger().debug("Nenhum evento recebido.");
                    }

                    if (null != eventoRecebido.getStatus()
                        && eventoRecebido.getStatus().equalsIgnoreCase("AgentCalled")) {

                        // Com o ID da ligação, recupero os dados da ligação gravados no banco de
                        // dados.
                        LigacaoVO ligacao = this.ocorrenciaBusiness
                            .obterDadosNovaLigacaoAtendente(eventoRecebido.getUniqueid());
                        this.resultadoPesquisa = ligacao.getListaContratosCliente();

                        determinarTipoDeAcionamento(ligacao);

                    }
                    stopMonitorChamadas = false;
                } else {
                    dispatchError500(this.socketPhone.getMensagemAtendenteAutenticado());
                }
            } else {
                dispatchError500(this.socketPhone.getMensagemAtivacaoDoRamal());
            }
        }
        addCallbackParam("stopMonitorChamadas", stopMonitorChamadas);
        this.getLogger().debug("***** Finalizada escuta pelo método monitorSocket(). liberadaLeituraSocket:");
    }

    /**
     * Nome: determinarTipoDeAcionamento
     * Determinar tipo de acionamento.
     *
     * @param ligacao the ligacao
     * @see
     */
    private void determinarTipoDeAcionamento(LigacaoVO ligacao) {

        if (ligacao.getNumeroDispositivo() < 7) {
            for (SinalDispositivo item : SinalDispositivo.values()) {
                if (item.getValue().equals(ligacao.getCodigoSinalDispositivo())) {
                    this.alertaChamada = item.getLabel();
                }
            }
            this.idPgdPainelDeAlertaStyle = "areaVermelha";
        } else if (null == ligacao.getCodigoEnviadoPulseira()) {
            this.alertaChamada = "Recebendo ligação de " + ligacao.getNumeroTelefoneOrigem();
            this.idPgdPainelDeAlertaStyle = "areaVerde";
        } else {
            this.alertaChamada = "Acionamento indefinido";
            this.idPgdPainelDeAlertaStyle = "areaVerde";
        }
        addCallbackParam("avisoDeChamada", true);

    }

    /**
     * Nome: conectarSocketServer Conectar socket server.
     * @see
     */
    private void conectarSocketServer() {
        this.getLogger().debug("***** Iniciando conexão com o servidor socket : conectarSocketServer() *****");
        if (null == socketPhone) {
            try {
                this.socketPhone = new SocketPhone();

                String host = this.getGACProperty("socket.softphone.address").trim();
                int port = Integer.parseInt(this.getGACProperty("socket.softphone.port").trim());
                this.getLogger().debug("host socket : " + host);
                this.getLogger().debug("porta socket : " + port);
                this.socketPhone.conectarAoSocketServer(host, port);

                boolean precisaLogarRamal = Boolean.parseBoolean(getGACProperty("socket.softphone.login.ramal.required"));
                boolean precisaLoginAtendente = Boolean.parseBoolean(getGACProperty("socket.softphone.login.atendente.required"));
                this.getLogger().debug("Login de ramal necessário: " + precisaLogarRamal);
                this.getLogger().debug("Login de atendente necessário: " + precisaLoginAtendente);
                if (precisaLogarRamal) {
                    this.socketPhone.ativarRamal("4000");
                } else {
                    this.socketPhone.setRamalAtivo(true);
                }
                if (precisaLoginAtendente) {
                    this.socketPhone.autenticarAtendente("*9800", "4000", "1000", "123");
                } else {
                    this.socketPhone.setAtendenteAutenticado(true);
                }
                this.getLogger().debug("***** Finalizado método conectarSocketServer()  *****");
            } catch (Exception e) {
                reset();
                throw new SocketException("Não é possível estabelecer conexão com o servidor de telefonia.", e);
            }
        }
    }

    /**
     * Nome: disponibilidadeAtendente
     * Disponibilidade atendente.
     *
     * @param event the event
     * @see
     */
    public void disponibilidadeAtendente(ActionEvent event) {

        if (this.btnDisponibilidadeValue.equals("Login")) {
            this.btnDisponibilidadeValue = "Disponível";
            this.getLogger().debug("******************Atendente Disponível: "  + this.getUsuarioLogado().getRegistroAtendente());
        } else {
            this.btnDisponibilidadeValue = "Indisponível";
            this.getLogger().debug("******************Atendente Indisponível: "  + this.getUsuarioLogado().getRegistroAtendente());
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
            this.getLogger().debug("******************Logando atendente: "  + this.getUsuarioLogado().getRegistroAtendente());
            //Iniciar servidor socket.
            try {
                conectarSocketServer();
                this.btnLoginValue = "Logout";
                this.btnDisponibilidadeValue = "Disponível";
                addCallbackParam("loginSucess", true);
            } catch (SocketException e) {
                dispatchError500("Não é possível estabelecer conexão com o servidor de telefonia.");
                this.getLogger().debug("******************Login falhou");
            }
        } else {
            this.getLogger().debug("******************Deslogando atendente: "  + this.getUsuarioLogado().getRegistroAtendente());

            this.socketPhone.fecharConexaoSocket();
            reset();
            addCallbackParam("loginSucess", false);
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
        this.socketPhone.setEmAtendimento(true);
        this.btnDisponibilidadeDisabled = true;
        this.btnBreakDisabled = true;
        this.btnLoginDisabled = true;

        try {
            this.socketPhone.atenderLigacao(null, 1);
        } catch (SocketException ex) {
            this.getLogger().error(ex);
        }

        this.getLogger().debug("***** Chamada atendida *****");
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
     * Nome: getAlertaChamada
     * Recupera o valor do atributo 'alertaChamada'.
     *
     * @return valor do atributo 'alertaChamada'
     * @see
     */
    public String getAlertaChamada() {
        return alertaChamada;
    }

    /**
     * Nome: setAlertaChamada
     * Registra o valor do atributo 'alertaChamada'.
     *
     * @param alertaChamada valor do atributo alerta chamada
     * @see
     */
    public void setAlertaChamada(String alertaChamada) {
        this.alertaChamada = alertaChamada;
    }

    /**
     * Nome: getSocketPhone
     * Recupera o valor do atributo 'socketPhone'.
     *
     * @return valor do atributo 'socketPhone'
     * @see
     */
    public SocketPhone getSocketPhone() {
        return socketPhone;
    }

    /**
     * Nome: setSocketPhone
     * Registra o valor do atributo 'socketPhone'.
     *
     * @param socketPhone valor do atributo socket phone
     * @see
     */
    public void setSocketPhone(SocketPhone socketPhone) {
        this.socketPhone = socketPhone;
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
     * Nome: isBtnBreakDisabled
     * Verifica se e btn break disabled.
     *
     * @return true, se for btn break disabled senão retorna false
     * @see
     */
    public boolean isBtnBreakDisabled() {
        return btnBreakDisabled;
    }

    /**
     * Nome: setBtnBreakDisabled
     * Registra o valor do atributo 'btnBreakDisabled'.
     *
     * @param btnBreakDisabled valor do atributo btn break disabled
     * @see
     */
    public void setBtnBreakDisabled(boolean btnBreakDisabled) {
        this.btnBreakDisabled = btnBreakDisabled;
    }

    /**
     * Nome: getBtnDisponibilidadeValue
     * Recupera o valor do atributo 'btnDisponibilidadeValue'.
     *
     * @return valor do atributo 'btnDisponibilidadeValue'
     * @see
     */
    public String getBtnDisponibilidadeValue() {
        return btnDisponibilidadeValue;
    }

    /**
     * Nome: setBtnDisponibilidadeValue
     * Registra o valor do atributo 'btnDisponibilidadeValue'.
     *
     * @param btnDisponibilidadeValue valor do atributo btn disponibilidade value
     * @see
     */
    public void setBtnDisponibilidadeValue(String btnDisponibilidadeValue) {
        this.btnDisponibilidadeValue = btnDisponibilidadeValue;
    }

    /**
     * Nome: isBtnDisponibilidadeDisabled
     * Verifica se e btn disponibilidade disabled.
     *
     * @return true, se for btn disponibilidade disabled senão retorna false
     * @see
     */
    public boolean isBtnDisponibilidadeDisabled() {
        return btnDisponibilidadeDisabled;
    }

    /**
     * Nome: setBtnDisponibilidadeDisabled
     * Registra o valor do atributo 'btnDisponibilidadeDisabled'.
     *
     * @param btnDisponibilidadeDisabled valor do atributo btn disponibilidade disabled
     * @see
     */
    public void setBtnDisponibilidadeDisabled(boolean btnDisponibilidadeDisabled) {
        this.btnDisponibilidadeDisabled = btnDisponibilidadeDisabled;
    }

}
