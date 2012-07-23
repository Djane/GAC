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

/**
 * <b>Descrição: Controller da tela de contartos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class ContratoBean extends BaseBean {

    /** Atributo dt nascimento contratante. */
    private Date dtNascimentoContratante;

    /** Atributo lista doencas. */
    private DualListModel<DoencaVO> listaDoencas;

    /** Atributo lista tratamentos. */
    private List<TratamentoVO> listaTratamentos;

    /** Atributo lista contatos. */
    private List<ContatoVO> listaContatos;

    /** Atributo tratamento. */
    private TratamentoVO tratamento;

    /** Atributo contato. */
    private ContatoVO contato;

    /** Atributo lista relacao. */
    private List<SelectItem> listaRelacao;

    /**
     * Construtor Padrao Instancia um novo objeto ContratoBean.
     */
    public ContratoBean() {

        super();
        // Set Nome da tela
        setTituloCabecalho("Contrato");

        // Limpa campos
        this.dtNascimentoContratante = new Date();
        this.tratamento = new TratamentoVO();
        this.contato = new ContatoVO();

        // Popula lista de tratamentos
        this.listaTratamentos = obterListaTratamentos();

        // Popular conattos cadastrados
        this.listaContatos = obterListaContatos();

        // Popular picklist de doenças
        this.listaDoencas = obterPickListDoencas();

        // Obter a lista do combo de relação (Parntesco)
        this.listaRelacao = new ArrayList<SelectItem>();
        for (GrauRelacao relacao : GrauRelacao.values()) {
            this.listaRelacao.add(new SelectItem(relacao.getValue(), relacao.name()));
        }

    }

    /**
     * Nome: obterPickListDoencas Obter pick list doencas.
     * @return dual list model
     * @see
     */
    private DualListModel<DoencaVO> obterPickListDoencas() {

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

    /**
     * Nome: obterListaTratamentos Obter lista tratamentos.
     * @return list
     * @see
     */
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

    /**
     * Nome: novoTratamento Novo tratamento.
     * @param event the event
     * @see
     */
    public void novoTratamento(ActionEvent event) {
        this.tratamento = new TratamentoVO();
    }

    /**
     * Nome: editarTratamento Editar tratamento.
     * @param event the event
     * @see
     */
    public void editarTratamento(ActionEvent event) {
        Integer idTratamento = Integer.parseInt(getRequestParameter("idTratamento"));
        this.tratamento = new TratamentoVO();
        this.tratamento = (TratamentoVO) findInListById(this.listaTratamentos, "idTratamento",
                idTratamento);

    }

    /**
     * Nome: adicionarTratamento Adicionar tratamento.
     * @param event the event
     * @see
     */
    public void adicionarTratamento(ActionEvent event) {
        TratamentoVO tratamento = new TratamentoVO();
        tratamento.setIdTratamento(this.listaTratamentos.size());
        tratamento.setNomeTratamento(this.tratamento.getNomeTratamento());
        tratamento.setDescricaoTratamento(this.tratamento.getDescricaoTratamento());
        tratamento.setFrequenciaMinutos(this.tratamento.getFrequenciaMinutos());

        this.listaTratamentos.add(tratamento);
    }

    /**
     * Nome: excluirTratamento Excluir tratamento.
     * @param event the event
     * @see
     */
    public void excluirTratamento(ActionEvent event) {
        TratamentoVO remover = (TratamentoVO) findInListById(this.listaTratamentos, "idTratamento",
                this.tratamento.getIdTratamento());
        this.listaTratamentos.remove(remover);
    }

    /**
     * Nome: obterListaContatos Obter lista contatos.
     * @return list
     * @see
     */
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

    /**
     * Nome: novoContato Novo contato.
     * @param event the event
     * @see
     */
    public void novoContato(ActionEvent event) {
        this.contato = new ContatoVO();
    }

    /**
     * Nome: editarContato Editar contato.
     * @param event the event
     * @see
     */
    public void editarContato(ActionEvent event) {
        Integer idContato = Integer.parseInt(getRequestParameter("idContato"));
        this.contato = new ContatoVO();
        this.contato = (ContatoVO) findInListById(this.listaContatos, "idContato", idContato);

    }

    /**
     * Nome: adicionarContato Adicionar contato.
     * @param event the event
     * @see
     */
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

    /**
     * Nome: excluirContato Excluir contato.
     * @param event the event
     * @see
     */
    public void excluirContato(ActionEvent event) {
        ContatoVO remover = (ContatoVO) findInListById(this.listaContatos, "idContato",
                this.contato.getIdContato());
        this.listaContatos.remove(remover);
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    /**
     * Nome: novoContrato Novo contrato.
     * @return string
     * @see
     */
    public String novoContrato() {
        setTituloCabecalho("Contrato");
        return "contrato";
    }

    /**
     * Nome: getDtNascimentoContratante Recupera o valor do atributo 'dtNascimentoContratante'.
     * @return valor do atributo 'dtNascimentoContratante'
     * @see
     */
    public Date getDtNascimentoContratante() {
        return dtNascimentoContratante;
    }

    /**
     * Nome: setDtNascimentoContratante Registra o valor do atributo 'dtNascimentoContratante'.
     * @param dtNascimentoContratante valor do atributo dt nascimento contratante
     * @see
     */
    public void setDtNascimentoContratante(Date dtNascimentoContratante) {
        this.dtNascimentoContratante = dtNascimentoContratante;
    }

    /**
     * Nome: getListaDoencas Recupera o valor do atributo 'listaDoencas'.
     * @return valor do atributo 'listaDoencas'
     * @see
     */
    public DualListModel<DoencaVO> getListaDoencas() {
        return listaDoencas;
    }

    /**
     * Nome: setListaDoencas Registra o valor do atributo 'listaDoencas'.
     * @param listaDoencas valor do atributo lista doencas
     * @see
     */
    public void setListaDoencas(DualListModel<DoencaVO> listaDoencas) {
        this.listaDoencas = listaDoencas;
    }

    /**
     * Nome: getListaTratamentos Recupera o valor do atributo 'listaTratamentos'.
     * @return valor do atributo 'listaTratamentos'
     * @see
     */
    public List<TratamentoVO> getListaTratamentos() {
        return listaTratamentos;
    }

    /**
     * Nome: setListaTratamentos Registra o valor do atributo 'listaTratamentos'.
     * @param listaTratamentos valor do atributo lista tratamentos
     * @see
     */
    public void setListaTratamentos(List<TratamentoVO> listaTratamentos) {
        this.listaTratamentos = listaTratamentos;
    }

    /**
     * Nome: getTratamento Recupera o valor do atributo 'tratamento'.
     * @return valor do atributo 'tratamento'
     * @see
     */
    public TratamentoVO getTratamento() {
        return tratamento;
    }

    /**
     * Nome: setTratamento Registra o valor do atributo 'tratamento'.
     * @param tratamento valor do atributo tratamento
     * @see
     */
    public void setTratamento(TratamentoVO tratamento) {
        this.tratamento = tratamento;
    }

    /**
     * Nome: getListaContatos Recupera o valor do atributo 'listaContatos'.
     * @return valor do atributo 'listaContatos'
     * @see
     */
    public List<ContatoVO> getListaContatos() {
        return listaContatos;
    }

    /**
     * Nome: setListaContatos Registra o valor do atributo 'listaContatos'.
     * @param listaContatos valor do atributo lista contatos
     * @see
     */
    public void setListaContatos(List<ContatoVO> listaContatos) {
        this.listaContatos = listaContatos;
    }

    /**
     * Nome: getContato Recupera o valor do atributo 'contato'.
     * @return valor do atributo 'contato'
     * @see
     */
    public ContatoVO getContato() {
        return contato;
    }

    /**
     * Nome: setContato Registra o valor do atributo 'contato'.
     * @param contato valor do atributo contato
     * @see
     */
    public void setContato(ContatoVO contato) {
        this.contato = contato;
    }

    /**
     * Nome: getListaRelacao Recupera o valor do atributo 'listaRelacao'.
     * @return valor do atributo 'listaRelacao'
     * @see
     */
    public List<SelectItem> getListaRelacao() {
        return listaRelacao;
    }

    /**
     * Nome: setListaRelacao Registra o valor do atributo 'listaRelacao'.
     * @param listaRelacao valor do atributo lista relacao
     * @see
     */
    public void setListaRelacao(List<SelectItem> listaRelacao) {
        this.listaRelacao = listaRelacao;
    }

}
