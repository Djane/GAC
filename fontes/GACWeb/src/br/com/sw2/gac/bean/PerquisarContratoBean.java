package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.ContratoBusiness;
import br.com.sw2.gac.vo.ContratoVO;

/**
 * <b>Descri��o: Controller da tela de pesquisa de contratos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class PerquisarContratoBean extends BaseBean {

    private static final long serialVersionUID = 3582244524115399451L;

    /** Atributo lista resultado pesquisa. */
    private List<ContratoVO> listaResultadoPesquisa = new ArrayList<ContratoVO>();

    private final ContratoBusiness business = new ContratoBusiness();

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.pesquisacontrato.view.title", true);
        return "pesquisaContrato";
    }

    /**
     * Nome: limpar Limpar.
     * @param event the event
     * @see
     */
    public void limpar(ActionEvent event) {
        this.listaResultadoPesquisa = new ArrayList<ContratoVO>();
    }

    /**
     * Nome: pesquisar Pesquisar.
     * @param event the event
     * @see
     */
    public void pesquisar(ActionEvent event) {

        this.listaResultadoPesquisa = business.recuperaListaContrato();

    }

    /**
     * Nome: getListaResultadoPesquisa Recupera o valor do atributo 'listaResultadoPesquisa'.
     * @return valor do atributo 'listaResultadoPesquisa'
     * @see
     */
    public List<ContratoVO> getListaResultadoPesquisa() {
        return listaResultadoPesquisa;
    }

    /**
     * Nome: setListaResultadoPesquisa Registra o valor do atributo 'listaResultadoPesquisa'.
     * @param listaResultadoPesquisa valor do atributo lista resultado pesquisa
     * @see
     */
    public void setListaResultadoPesquisa(List<ContratoVO> listaResultadoPesquisa) {
        this.listaResultadoPesquisa = listaResultadoPesquisa;
    }

}
