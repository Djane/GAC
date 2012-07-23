package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.ContratoVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class PerquisarContratoBean extends BaseBean {

    /** Atributo lista resultado pesquisa. */
    private List<ContratoVO> listaResultadoPesquisa = new ArrayList<ContratoVO>();

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Pesquisa de Contratos");
        return "pesquisacontrato";
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

        this.listaResultadoPesquisa = new ArrayList<ContratoVO>();
        ContratoVO item = new ContratoVO();
        item.setNumeroContrato("0127/2012");
        item.setDtInicioValidade(new Date(112, 9, 10));
        item.setDtFinalValidade(new Date(113, 9, 9));
        item.setNomeContratante("Carlos Luciano de Souza");
        item.setCpfContratante("123.456.789-00");
        listaResultadoPesquisa.add(item);
        item = new ContratoVO();
        item.setNumeroContrato("0345/2012");
        item.setDtInicioValidade(new Date(112, 10, 14));
        item.setDtFinalValidade(new Date(113, 10, 13));
        item.setNomeContratante("Juliana Isabel Mendes");
        item.setCpfContratante("987.123.456-87");
        listaResultadoPesquisa.add(item);

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
