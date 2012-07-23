package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.sw2.gac.tools.GrauRelacao;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.TratamentoVO;

@ManagedBean
@ViewScoped
public class ContratoBean extends BaseBean {

    private Date dtNascimentoContratante;
    private DualListModel<DoencaVO> listaDoencas; 
    private List<TratamentoVO> listaTratamentos;
    private List<ContatoVO> listaContatos;
    private TratamentoVO tratamento;
    private ContatoVO contato;
    private List<SelectItem> listaRelacao;

    public ContratoBean() {
        
        super();
        //Set Nome da tela
        setTituloCabecalho("Contrato");
        
        //Limpa campos
        this.dtNascimentoContratante = new Date();        
        this.tratamento = new TratamentoVO();
        this.contato = new ContatoVO();
        
        //Popula lista de tratamentos
        this.listaTratamentos = obterListaTratamentos();
        
        //Popular conattos cadastrados
        this.listaContatos = obterListaContatos();
        
        //Popular picklist de doenças
        this.listaDoencas = obterPickListDoencas();
        
        //Obter a lista do combo de relação (Parntesco)
        this.listaRelacao = new ArrayList<SelectItem>();
        for (GrauRelacao relacao : GrauRelacao.values()) {           
            this.listaRelacao.add(new SelectItem(relacao.getValue(), relacao.name()));
        }
        
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
    
    
    private List<ContatoVO> obterListaContatos() {
        List<ContatoVO> lista = new ArrayList<ContatoVO>();
        ContatoVO item = new ContatoVO();
        item.setIdContato(1);
        item.setNome("Josue de Jesus Soares");
        item.setGrauParentesco("3");
        item.setEndereco("Alameda dos Arapanés, 125");
        item.setBairro("Moema");
        item.setCidade("São Paulo");
        item.setEstado("SP");
        item.setCep("07456-000");
        item.setContratante(true);
        item.setDataNascimento(new Date());
        item.setSqaChamada(1);
        
        lista.add(item);
        
        return lista;
    }
    
    
    public void novoContato(ActionEvent event) {
        this.contato = new ContatoVO();
    }
    
    public void editarContato(ActionEvent event) {
        Integer idContato = Integer.parseInt(getRequestParameter("idContato"));
        this.contato = new ContatoVO();     
        this.contato = (ContatoVO) findInListById(this.listaContatos, "idContato", idContato);
 
    }
    
    public void adicionarContato(ActionEvent event) {       
        ContatoVO contato = new ContatoVO();
        contato.setIdContato(this.listaContatos.size());
        contato.setNome(this.contato.getNome());
        contato.setGrauParentesco(this.contato.getGrauParentesco());
        contato.setEndereco(this.contato.getEndereco());
        contato.setBairro(this.contato.getBairro());
        contato.setCidade(this.contato.getCidade());
        contato.setEstado(this.contato.getEstado());
        contato.setCep(this.contato.getCep());
        contato.setContratante(this.contato.isContratante());
        contato.setDataNascimento(this.contato.getDataNascimento());
        contato.setSqaChamada(this.contato.getSqaChamada());
        
        this.listaContatos.add(contato);
        
        this.listaTratamentos.add(tratamento); 
    }
    
    public void excluirContato(ActionEvent event) {
        ContatoVO remover = (ContatoVO) findInListById(this.listaContatos, "idContato", this.contato.getIdContato());
        this.listaContatos.remove(remover);
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

    public List<ContatoVO> getListaContatos() {
        return listaContatos;
    }

    public void setListaContatos(List<ContatoVO> listaContatos) {
        this.listaContatos = listaContatos;
    }

    public ContatoVO getContato() {
        return contato;
    }

    public void setContato(ContatoVO contato) {
        this.contato = contato;
    }

    public List<SelectItem> getListaRelacao() {
        return listaRelacao;
    }

    public void setListaRelacao(List<SelectItem> listaRelacao) {
        this.listaRelacao = listaRelacao;
    }    
    
}
