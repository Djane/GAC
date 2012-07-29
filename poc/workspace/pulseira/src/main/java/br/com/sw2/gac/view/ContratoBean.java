package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.sw2.gac.tools.GrauRelacao;
import br.com.sw2.gac.vo.CentralVO;
import br.com.sw2.gac.vo.ContatoVO;
import br.com.sw2.gac.vo.ContratoVO;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.DoencaVO;
import br.com.sw2.gac.vo.FormaContatoVO;
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

    /** Atributo pick list dispositivos cliente. */
    private DualListModel<DispositivoVO> pickListDispositivosCliente;

    /** Atributo pick list central cliente. */
    private DualListModel<CentralVO> pickListCentralCliente;

    /** Atributo lista doencas. */
    private DualListModel<DoencaVO> pickListDoencas;

    /** Atributo lista tratamentos. */
    private List<TratamentoVO> listaTratamentos;

    /** Atributo lista contatos. */
    private List<ContatoVO> listaContatos;

    /** Atributo contrato. */
    private ContratoVO contrato;

    /** Atributo tratamento. */
    private TratamentoVO tratamento;

    /** Atributo contato. */
    private ContatoVO contato;

    /** Representa os campos a serem preenchidos para edição ou inclusão de nova forma de contato. */
    private FormaContatoVO formaContato;

    /** Atributo lista relacao. */
    private List<SelectItem> listaRelacao;

    /**
     * Construtor Padrao Instancia um novo objeto ContratoBean.
     */
    public ContratoBean() {

        super();
        // Set Nome da tela
        setTituloCabecalho("label.contrato.view.title", true);

        // Limpa campos
        this.dtNascimentoContratante = new Date();
        this.contrato = new ContratoVO();
        this.tratamento = new TratamentoVO();
        this.contato = new ContatoVO();
        this.formaContato = new FormaContatoVO();

        // Popula lista de tratamentos
        this.listaTratamentos = obterListaTratamentos();

        // Popular conattos cadastrados
        this.listaContatos = obterListaContatos();

        //Lista de dispositivos
        this.pickListCentralCliente = obterPickListCentrais();

        //Lista de centrais
        this.pickListDispositivosCliente = obterPickListDispositivos();

        // Popular picklist de doenças
        this.pickListDoencas = obterPickListDoencas();

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

        List<DoencaVO> source = GacMock.getListaDoencas();
        List<DoencaVO> target = new ArrayList<DoencaVO>();
        return new DualListModel<DoencaVO>(source, target);
    }

    /**
     * Nome: obterPickListDispositivos Obter pick list dispositivos.
     * @return dual list model
     * @see
     */
    private DualListModel<DispositivoVO> obterPickListDispositivos() {

        List<DispositivoVO> source = GacMock.getListaDispositivos();
        List<DispositivoVO> target = new ArrayList<DispositivoVO>();
        return new DualListModel<DispositivoVO>(source, target);
    }

    /**
     * Nome: obterPickListCentralis Obter pick list centralis.
     * @return dual list model
     * @see
     */
    private DualListModel<CentralVO> obterPickListCentrais() {

        List<CentralVO> source = GacMock.getListaCentral();
        List<CentralVO> target = new ArrayList<CentralVO>();
        return new DualListModel<CentralVO>(source, target);
    }

    /**
     * Nome: obterListaTratamentos Obter lista tratamentos.
     * @return list
     * @see
     */
    private List<TratamentoVO> obterListaTratamentos() {
        List<TratamentoVO> lista = GacMock.getListaTratamentos();
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
        List<ContatoVO> lista = GacMock.getListaContatos();
        return lista;
    }

    /**
     * Nome: novoContato Novo contato.
     * @param event the event
     * @see
     */
    public void novoContato(ActionEvent event) {
        this.contato = new ContatoVO();
        this.formaContato = new FormaContatoVO();
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
     * Nome: novaFormaContato Nova forma contato.
     * @param event the event
     * @see
     */
    public void novaFormaContato(ActionEvent event) {
        this.formaContato = new FormaContatoVO();
    }

    /**
     * Nome: excluirFormaContato Excluir forma contato.
     * @param event the event
     * @see
     */
    public void excluirFormaContato(ActionEvent event) {
        FormaContatoVO remover = (FormaContatoVO) findInListById(
                this.contato.getListaFormaContato(), "idFormaContato",
                this.formaContato.getIdFormaContato());
        this.contato.getListaFormaContato().remove(remover);
    }

    /**
     * Nome: editarFormaContato Editar forma contato.
     * @param event the event
     * @see
     */
    public void editarFormaContato(ActionEvent event) {
        Integer idFormaContato = Integer.parseInt(getRequestParameter("idFormaContato"));
        this.formaContato = (FormaContatoVO) findInListById(this.contato.getListaFormaContato(),
                "idFormaContato", idFormaContato);
    }

    /**
     * Nome: adicionarFormaContato Adicionar forma contato.
     * @param event the event
     * @see
     */
    public void adicionarFormaContato(ActionEvent event) {
        FormaContatoVO formaContato = new FormaContatoVO();

        formaContato.setTelefone(this.formaContato.getTelefone());
        formaContato.setEmail(this.formaContato.getEmail());
        formaContato.setTipoContato(this.formaContato.getTipoContato());

        if (null == this.contato.getListaFormaContato()) {
            this.contato.setListaFormaContato(new ArrayList<FormaContatoVO>());
        }
        formaContato.setIdFormaContato(this.contato.getListaFormaContato().size() + 1);
        this.contato.getListaFormaContato().add(formaContato);
        this.formaContato = new FormaContatoVO();

    }

    /**
     * Nome: validarCamposFormaContato Validar campos forma contato.
     * @param event the event
     * @see
     */
    public void validarCamposFormaContato(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        UIInput uiText1 = (UIInput) components.findComponent("idTxtFormaContatoTelefone");
        String valueUiText1 = uiText1.getLocalValue().toString();

        UIInput uiText2 = (UIInput) components.findComponent("idTxtFormaContatoEmail");
        String valueUiText2 = uiText2.getLocalValue().toString();

        if ((null == valueUiText1.trim() || "".equals(valueUiText1.trim()))
                && (null == valueUiText2.trim() || "".equals(valueUiText2.trim()))) {
            setFacesMessage("message.contrato.field.telefoneemail.required");
            fc.renderResponse();
        }

    }

    /**
     * Nome: filtrarDispositivoCliente Filtrar dispositivo cliente.
     * @param event the event
     * @see
     */
    public void filtrarDispositivoCliente(ActionEvent event) {
        this.pickListDispositivosCliente = obterPickListDispositivos();
    }

    /**
     * Nome: filtrarCentralCliente Filtrar central cliente.
     * @param event the event
     * @see
     */
    public void filtrarCentralCliente(ActionEvent event) {
        this.pickListCentralCliente = obterPickListCentrais();
    }

    /**
     * Nome: getFormaContato Recupera o valor do atributo 'formaContato'.
     * @return valor do atributo 'formaContato'
     * @see
     */
    public FormaContatoVO getFormaContato() {
        return formaContato;
    }

    /**
     * Nome: setFormaContato Registra o valor do atributo 'formaContato'.
     * @param formaContato valor do atributo forma contato
     * @see
     */
    public void setFormaContato(FormaContatoVO formaContato) {
        this.formaContato = formaContato;
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
     * Nome: getPickListDispositivosCliente Recupera o valor do atributo
     * 'pickListDispositivosCliente'.
     * @return valor do atributo 'pickListDispositivosCliente'
     * @see
     */
    public DualListModel<DispositivoVO> getPickListDispositivosCliente() {
        return pickListDispositivosCliente;
    }

    /**
     * Nome: setPickListDispositivosCliente Registra o valor do atributo
     * 'pickListDispositivosCliente'.
     * @param pickListDispositivosCliente valor do atributo pick list dispositivos cliente
     * @see
     */
    public void setPickListDispositivosCliente(
            DualListModel<DispositivoVO> pickListDispositivosCliente) {
        this.pickListDispositivosCliente = pickListDispositivosCliente;
    }

    /**
     * Nome: getPickListCentralCliente Recupera o valor do atributo 'pickListCentralCliente'.
     * @return valor do atributo 'pickListCentralCliente'
     * @see
     */
    public DualListModel<CentralVO> getPickListCentralCliente() {
        return pickListCentralCliente;
    }

    /**
     * Nome: setPickListCentralCliente Registra o valor do atributo 'pickListCentralCliente'.
     * @param pickListCentralCliente valor do atributo pick list central cliente
     * @see
     */
    public void setPickListCentralCliente(DualListModel<CentralVO> pickListCentralCliente) {
        this.pickListCentralCliente = pickListCentralCliente;
    }

    /**
     * Nome: getPickListDoencas Recupera o valor do atributo 'pickListDoencas'.
     * @return valor do atributo 'pickListDoencas'
     * @see
     */
    public DualListModel<DoencaVO> getPickListDoencas() {
        return pickListDoencas;
    }

    /**
     * Nome: setPickListDoencas Registra o valor do atributo 'pickListDoencas'.
     * @param pickListDoencas valor do atributo pick list doencas
     * @see
     */
    public void setPickListDoencas(DualListModel<DoencaVO> pickListDoencas) {
        this.pickListDoencas = pickListDoencas;
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
     * Nome: getContrato Recupera o valor do atributo 'contrato'.
     * @return valor do atributo 'contrato'
     * @see
     */
    public ContratoVO getContrato() {
        return contrato;
    }

    /**
     * Nome: setContrato Registra o valor do atributo 'contrato'.
     * @param contrato valor do atributo contrato
     * @see
     */
    public void setContrato(ContratoVO contrato) {
        this.contrato = contrato;
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
