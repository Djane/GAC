package br.com.smartangel.pulseira.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.smartangel.pulseira.vo.ContratoVO;

@ManagedBean
@ViewScoped
public class PerquisarContratoBean extends BaseBean  {

    private List<ContratoVO> listaResultadoPesquisa = new ArrayList<ContratoVO>();
   
    public String iniciarPagina() {
        setTituloCabecalho("Pesquisa de Contratos");
         return "pesquisacontrato";
    }

    public String fechar() {
        return "menuPrincipal";
    }
    
    
    public void limpar(ActionEvent event) {
        this.listaResultadoPesquisa = new ArrayList<ContratoVO>();
    }
    public void pesquisar(ActionEvent event) {
        
 
        this.listaResultadoPesquisa = new ArrayList<ContratoVO>();
        ContratoVO item = new ContratoVO();
        item.setNumeroContrato("0127/2012");
        item.setDtInicioValidade(new Date(112,9,10));
        item.setDtFinalValidade(new Date(113,9,9));
        item.setNomeContratante("Carlos Luciano de Souza");
        item.setCpfContratante("123.456.789-00");
        listaResultadoPesquisa.add(item);
        item = new ContratoVO();
        item.setNumeroContrato("0345/2012");
        item.setDtInicioValidade(new Date(112,10,14));
        item.setDtFinalValidade(new Date(113,10,13));
        item.setNomeContratante("Juliana Isabel Mendes");
        item.setCpfContratante("987.123.456-87");
        listaResultadoPesquisa.add(item);        
        
    }

    public List<ContratoVO> getListaResultadoPesquisa() {
        return listaResultadoPesquisa;
    }

    public void setListaResultadoPesquisa(List<ContratoVO> listaResultadoPesquisa) {
        this.listaResultadoPesquisa = listaResultadoPesquisa;
    }
    
    
    
}
