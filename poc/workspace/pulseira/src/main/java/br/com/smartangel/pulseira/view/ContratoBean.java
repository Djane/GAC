package br.com.smartangel.pulseira.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.apache.commons.beanutils.BeanPredicate;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.EqualPredicate;
import org.primefaces.model.DualListModel;

import br.com.smartangel.pulseira.vo.DoencaVO;
import br.com.smartangel.pulseira.vo.TratamentoVO;

@ManagedBean
@ViewScoped
public class ContratoBean extends BaseBean {

    private Date dtNascimentoContratante;

    private DualListModel<DoencaVO> listaDoencas;    

    
    //Tab Tratamento
    private List<TratamentoVO> listaTratamentos;
    private TratamentoVO tratamento;

    public ContratoBean() {
        //Set Nome da tela
        setTituloCabecalho("Contrato");
        
        //Limpa campos
        this.dtNascimentoContratante = new Date();        
        this.tratamento = new TratamentoVO();
        
        //Popula lista de tratamentos
        this.listaTratamentos = obterListaTratamentos();
        
        this.listaDoencas = obterPickListDoencas();
        
    }

    private  DualListModel<DoencaVO>  obterPickListDoencas() {
       
        List<DoencaVO> source = new ArrayList<DoencaVO>();  
        List<DoencaVO> target = new ArrayList<DoencaVO>();
        
        DoencaVO doenca = new DoencaVO();
        doenca.setIdDoenca(1);
        doenca.setNomeDoenca("Doença 1");
        source.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(2);
        doenca.setNomeDoenca("Doença 2");
        source.add(doenca);
        doenca = new DoencaVO();
        doenca.setIdDoenca(3);
        doenca.setNomeDoenca("Doença 3");
        source.add(doenca);
        
        return new DualListModel<DoencaVO>(source, target);
    }
    
    private List<TratamentoVO> obterListaTratamentos() {
        List<TratamentoVO> lista = new ArrayList<TratamentoVO>();
        TratamentoVO item = new TratamentoVO();
        item.setIdTratamento(1);
        item.setNomeTratamento("Tratamento 1");
        item.setDescricaoTratamento("Descrição do tratamento 1");
        item.setFrequenciaMinutos(10);
        lista.add(item);
        
        return lista;
    }
    
    
    public void novoTratamento(ActionEvent event) {
        this.tratamento = new TratamentoVO();
    }
    
    public void editarTratamento(ActionEvent event) {
        Integer idTratamento = Integer.parseInt(getRequestParameter("idTratamento"));
        this.tratamento = new TratamentoVO();        
        this.tratamento = (TratamentoVO) findInListById(this.listaTratamentos, "idTratamento", idTratamento);
 
    }
    
    public void adicionarTratamento(ActionEvent event) {       
        TratamentoVO tratamento = new TratamentoVO();
        tratamento.setIdTratamento(this.listaTratamentos.size());
        tratamento.setNomeTratamento(this.tratamento.getNomeTratamento());
        tratamento.setDescricaoTratamento(this.tratamento.getDescricaoTratamento());
        tratamento.setFrequenciaMinutos(this.tratamento.getFrequenciaMinutos());
        
        this.listaTratamentos.add(tratamento); 
    }
    
    public void excluirTratamento(ActionEvent event) {
        TratamentoVO remover = (TratamentoVO) findInListById(this.listaTratamentos, "idTratamento", this.tratamento.getIdTratamento());
        this.listaTratamentos.remove(remover);
    }
    
    
    public String iniciarPagina() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    public String novoContrato() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    public Date getDtNascimentoContratante() {
        return dtNascimentoContratante;
    }

    public void setDtNascimentoContratante(Date dtNascimentoContratante) {
        this.dtNascimentoContratante = dtNascimentoContratante;
    }

    public DualListModel<DoencaVO> getListaDoencas() {
        return listaDoencas;
    }

    public void setListaDoencas(DualListModel<DoencaVO> listaDoencas) {
        this.listaDoencas = listaDoencas;
    }

    public List<TratamentoVO> getListaTratamentos() {
        return listaTratamentos;
    }

    public void setListaTratamentos(List<TratamentoVO> listaTratamentos) {
        this.listaTratamentos = listaTratamentos;
    }

    public TratamentoVO getTratamento() {
        return tratamento;
    }

    public void setTratamento(TratamentoVO tratamento) {
        this.tratamento = tratamento;
    }
    
}
