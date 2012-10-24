package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.primefaces.context.RequestContext;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.util.DateUtil;
import br.com.sw2.gac.util.MenuItem;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DesempenhoComercialVO;
import br.com.sw2.gac.vo.DispositivoEstadoVO;
import br.com.sw2.gac.vo.RelContratosAVencerVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: controller do menu principal.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@SessionScoped
public class MenuBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -1506925064205437167L;

    /** Atributo filtro mes referencia. */
    private Integer filtroMesReferencia;

    /** Atributo filtro ano referencia. */
    private Integer filtroAnoReferencia;

    /** Atributo filtro cpf cliente. */
    private String filtroCpfCliente;

    /** Atributo filtro nome cliente. */
    private String filtroNomeCliente;

    /** Constante TRINTA_DIAS. */
    private static final int TRINTA_DIAS = 30;

    /**
     * Construtor Padrao Instancia um novo objeto MenuBean.
     */
    public MenuBean() {
        this.filtroMesReferencia = 1;
        this.filtroAnoReferencia = DateUtil.getAnoAtual();
    }

    /**
     * Nome: invokarPagina Método que invoca uma página baseada em seu codigo.
     * @return string
     * @see
     */
    public String invokarPagina() {
        String toViewId = "";
        Integer codigo = Integer.parseInt(getRequestParameter("codigoModulo"));

        for (MenuItem item : MenuItem.values()) {
            if (codigo.intValue() == item.getCodigoModulo().intValue()) {
                if (!item.getScreeTitle().equals("")) {
                    setTituloCabecalho(item.getScreeTitle(), true);
                }
                toViewId = item.getViewID();
            }
        }
        return toViewId;
    }

    /**
     * Nome: verificarPermissaoPerfil Verificar permissao perfil.
     * @param codigoModulo the codigo modulo
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean verificarPermissaoPerfil(Integer codigoModulo) {
        UsuarioVO usuario = getUsuarioLogado();
        boolean temPermissao = false;
        for (MenuItem item : MenuItem.values()) {
            if (item.getCodigoModulo().intValue() == codigoModulo.intValue()) {
                for (Integer codigoPeril : item.getPerfilAutorizado()) {

                    if (codigoPeril.intValue() == usuario.getPerfil().getIdPerfil().intValue()) {
                        temPermissao = true;
                        break;
                    }
                }
            }
        }
        return temPermissao;
    }

    /**
     * Nome: imprimirDispositivosPorEstado Imprimir dispositivos por estado.
     * @param event the event
     * @see
     */
    public void imprimirDispositivosPorEstado(ActionEvent event) {
        this.getLogger().debug("Iniciando imprimirDispositivosPorEstado");
        // Obtem os dados que serão exibidos no relatório
        DispositivoBusiness business = new DispositivoBusiness();
        List<DispositivoEstadoVO> lista = business.recuperaQtdeDispositivosPorEstado();
        this.imprimirRelatorioPadrao("dispositivoestado/dispositivoEstado.jasper", lista, null);
        this.getLogger().debug("Finalizado imprimirDispositivosPorEstado");
    }

    /**
     * Nome: imprimirRelatorioDesempenhoComercial Imprimir relatorio desempenho comercial.
     * @param event the event
     * @see
     */
    public void imprimirRelatorioDesempenhoComercial(ActionEvent event) {
        this.getLogger().debug("***** Iniciando imprimirRelatorioDesempenhoComercial(ActionEvent event) *****");
        this.getLogger().debug("Mês selecionado :" + this.filtroMesReferencia);
        this.getLogger().debug("Ano selecionado :" + this.filtroAnoReferencia);
        String reportdir = getPathReport("br/com/sw2/gac/jasper/report/desempenhocomercial/", "desempenhocomercial.jasper");
        this.getLogger().debug("URL SUBDIR " + reportdir);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("SUBREPORT_DIR", reportdir);
        ContratoBusiness contratoBusiness = new ContratoBusiness();
        DesempenhoComercialVO desempenhoComercial = contratoBusiness
                .obterDadosDesempenhoComercial(DateUtil.getDate(this.filtroAnoReferencia,
                        this.filtroMesReferencia, 01));
        Collection<DesempenhoComercialVO> beanCollection = new ArrayList<DesempenhoComercialVO>();
        beanCollection.add(desempenhoComercial);
        this.getLogger().debug("Chamando método de impressão...");
        this.imprimirRelatorioPadrao("desempenhocomercial/desempenhocomercial.jasper", beanCollection, parameters);
        this.getLogger().debug("***** Finalizado imprimirRelatorioDesempenhoComercial(ActionEvent event) *****");
    }


    /**
     * Nome: imprimirContratosAVencer Imprimir contratos a vencer em 30 dias.
     * @param event the event
     * @see
     */
    public void imprimirContratosAVencer(ActionEvent event) {
        this.getLogger().debug("Iniciando imprimirDispositivosPorEstado");
        // Obtem os dados que serão exibidos no relatório
        ContratoBusiness contratoBusiness = new ContratoBusiness();
        List<RelContratosAVencerVO> lista = contratoBusiness.recuperarContratosAtivosAVencerEm(TRINTA_DIAS);

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("TOTAL_REGISTROS", lista.size());
        this.imprimirRelatorioPadrao("contratosAVencer.jasper", lista, parameters);
        this.getLogger().debug("Finalizado imprimirContratosAVencer");
    }


    /**
     * Nome: imprimirExtratoCliente
     * Imprimir extrato cliente.
     *
     * @param event the event
     * @see
     */
    public void imprimirExtratoCliente(ActionEvent event) {
        this.getLogger().debug("Iniciando método imprimirExtratoCliente(ActionEvent event)");

        Map<String, Object> parameters = new HashMap<String, Object>();
        RequestContext reqCtx = RequestContext.getCurrentInstance();
        if (StringUtil.isEmpty(this.filtroCpfCliente, true) && StringUtil.isEmpty(this.filtroNomeCliente, true)) {
            reqCtx.addCallbackParam("validationError", true);
            setFacesErrorMessage("message.extratocliente.filtro.required");
        } else {
            reqCtx.addCallbackParam("validationError", false);
        }

        HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
            .getSession(false);

        ContratoBusiness contratoBusiness = new ContratoBusiness();
        List<ContratoVO> dados = null;
        if (StringUtil.isNotEmpty(this.filtroCpfCliente, true)) {
            dados = contratoBusiness.pesquisarContratosPorCliente(this.filtroCpfCliente, null);
        } else if (StringUtil.isNotEmpty(this.filtroNomeCliente, true)) {
            dados = contratoBusiness.pesquisarContratosPorCliente(null, this.filtroNomeCliente);
        }
        dados.get(0).getCliente().getListaDispositivos().addAll(dados.get(0).getCliente().getListaCentrais());

        session.setAttribute("jasperFile", "extratocliente/extratoCliente.jasper");
        session.setAttribute("beanParameters", parameters);
        session.setAttribute("beanCollection", dados);

        this.getLogger().debug("Finalizado método imprimirExtratoCliente(ActionEvent event)");
    }

    /**
     * Nome: getFiltroMesReferencia Recupera o valor do atributo 'filtroMesReferencia'.
     * @return valor do atributo 'filtroMesReferencia'
     * @see
     */

    public Integer getFiltroMesReferencia() {
        return filtroMesReferencia;
    }

    /**
     * Nome: setFiltroMesReferencia Registra o valor do atributo 'filtroMesReferencia'.
     * @param filtroMesReferencia valor do atributo filtro mes referencia
     * @see
     */

    public void setFiltroMesReferencia(Integer filtroMesReferencia) {
        this.filtroMesReferencia = filtroMesReferencia;
    }

    /**
     * Nome: getFiltroAnoReferencia Recupera o valor do atributo 'filtroAnoReferencia'.
     * @return valor do atributo 'filtroAnoReferencia'
     * @see
     */

    public Integer getFiltroAnoReferencia() {
        return filtroAnoReferencia;
    }

    /**
     * Nome: setFiltroAnoReferencia Registra o valor do atributo 'filtroAnoReferencia'.
     * @param filtroAnoReferencia valor do atributo filtro ano referencia
     * @see
     */
    public void setFiltroAnoReferencia(Integer filtroAnoReferencia) {
        this.filtroAnoReferencia = filtroAnoReferencia;
    }

    /**
     * Nome: getFiltroCpfCliente
     * Recupera o valor do atributo 'filtroCpfCliente'.
     *
     * @return valor do atributo 'filtroCpfCliente'
     * @see
     */
    public String getFiltroCpfCliente() {
        return filtroCpfCliente;
    }

    /**
     * Nome: setFiltroCpfCliente
     * Registra o valor do atributo 'filtroCpfCliente'.
     *
     * @param filtroCpfCliente valor do atributo filtro cpf cliente
     * @see
     */
    public void setFiltroCpfCliente(String filtroCpfCliente) {
        this.filtroCpfCliente = filtroCpfCliente;
    }

    /**
     * Nome: getFiltroNomeCliente
     * Recupera o valor do atributo 'filtroNomeCliente'.
     *
     * @return valor do atributo 'filtroNomeCliente'
     * @see
     */
    public String getFiltroNomeCliente() {
        return filtroNomeCliente;
    }

    /**
     * Nome: setFiltroNomeCliente
     * Registra o valor do atributo 'filtroNomeCliente'.
     *
     * @param filtroNomeCliente valor do atributo filtro nome cliente
     * @see
     */
    public void setFiltroNomeCliente(String filtroNomeCliente) {
        this.filtroNomeCliente = filtroNomeCliente;
    }

}
