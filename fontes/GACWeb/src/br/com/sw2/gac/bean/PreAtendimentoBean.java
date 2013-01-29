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
import br.com.sw2.gac.socket.SocketPhone;
import br.com.sw2.gac.socket.SocketException;
import br.com.sw2.gac.tools.StatusOcorrencia;
import br.com.sw2.gac.tools.TipoOcorrencia;
import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.vo.ContratoVO;
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

    /**
     * Construtor Padrao Instancia um novo objeto PreAtendimentoBean.
     */
    public PreAtendimentoBean() {
        try {
            conectarSocketServer();
            //this.socketPhone.ativarRamal("4000");
           // this.socketPhone.autenticarAtendente("*9800", "4000", "1000", "123");

           //TODO fake
            this.socketPhone.setAtendenteAutenticado(true);
            this.socketPhone.setRamalAtivo(true);
        } catch (SocketException e) {
            this.getLogger().error(e);
        }

    }

    /**
     * Nome: getFiltro Recupera o valor do atributo 'filtro'.
     * @return valor do atributo 'filtro'
     * @see
     */
    public FiltroPesquisarPreAtendimento getFiltro() {
        return filtro;
    }

    /**
     * Nome: setFiltro Registra o valor do atributo 'filtro'.
     * @param filtro valor do atributo filtro
     * @see
     */
    public void setFiltro(FiltroPesquisarPreAtendimento filtro) {
        this.filtro = filtro;
    }

    /**
     * Nome: getResultadoPesquisa Recupera o valor do atributo 'resultadoPesquisa'.
     * @return valor do atributo 'resultadoPesquisa'
     * @see
     */
    public List<ContratoVO> getResultadoPesquisa() {
        return resultadoPesquisa;
    }

    /**
     * Nome: setResultadoPesquisa Registra o valor do atributo 'resultadoPesquisa'.
     * @param resultadoPesquisa valor do atributo resultado pesquisa
     * @see
     */
    public void setResultadoPesquisa(List<ContratoVO> resultadoPesquisa) {
        this.resultadoPesquisa = resultadoPesquisa;
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

    /** Atributo liberada leitura socket. */
    private volatile Boolean liberadaLeituraSocket = true;

    /** Atributo alerta chamada. */
    private String alertaChamada = "";

    /** Atributo socket client. */
    private SocketPhone socketPhone = null;

    /**
     * Nome: monitorSocket Monitor socket.
     * @see
     */
    public synchronized void monitorSocket() {
        this.getLogger().debug("***** Iniciando escuta pelo método monitorSocket().  liberadaLeituraSocket:" + liberadaLeituraSocket);

        if (null == this.socketPhone.getSocket()) {
            dispatchError500("Não é possível estabelecer conexão com o servidor de telefonia.");
        } else if (this.socketPhone.isRamalAtivo()) {
            if (this.socketPhone.isAtendenteAutenticado()) {
                if (liberadaLeituraSocket) {
                    this.liberadaLeituraSocket = false;
                    Event eventoRecebido = this.socketPhone.getEvento();
                    if (null != eventoRecebido.getStatus() && eventoRecebido.getStatus().equalsIgnoreCase("AgentCalled")) {
                        //Tem que ir no banco pegar os dados do cliente
                        this.alertaChamada = "Recebendo ligação: " + "1181817140";
                    }
                    liberadaLeituraSocket = true;
                }
            } else {
                dispatchError500(this.socketPhone.getMensagemAtendenteAutenticado());
            }

        } else {
            dispatchError500(this.socketPhone.getMensagemAtivacaoDoRamal());
        }
        this.getLogger().debug("***** Finalizada escuta pelo método monitorSocket(). liberadaLeituraSocket:" + liberadaLeituraSocket);
    }

    /**
     * Nome: conectarSocketServer Conectar socket server.
     * @see
     */
    private void conectarSocketServer() {
        this.getLogger().debug("***** Iniciando conexão com o servidor socket : conectarSocketServer() *****");
        if (null == socketPhone) {
            this.socketPhone = new SocketPhone();

            String host = this.getGACProperty("socket.softphone.address").trim();
            int port = Integer.parseInt(this.getGACProperty("socket.softphone.port").trim());
            int soTimeout = Integer.parseInt(this.getGACProperty("socket.softphone.so_timeout").trim());
            this.getLogger().debug("host socket : " + host);
            this.getLogger().debug("porta socket : " + port);
            this.socketPhone.conectarAoSocketServer(host, port);
            try {
                this.socketPhone.getSocket().setSoTimeout(soTimeout);
            } catch (java.net.SocketException e) {
                this.getLogger().error(e);
            }
            this.getLogger().debug("***** Finalizado método conectarSocketServer()  *****");
        }
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
}
