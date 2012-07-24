package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.sw2.gac.vo.DispositivoVO;

/**
 * <b>Descrição: Controller da tela de inventário de dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class InventarioDispositivoBean extends BaseBean {

    /** Atributo id dispositivo. */
    private Integer idDispositivo;

    /** Atributo descricao dispositivo. */
    private String descricaoDispositivo;

    /** Atributo valor status atual. */
    private Integer valorStatusAtual;

    /** Atributo valor novo status. */
    private Integer valorNovoStatus;

    /** Atributo lista dispositivos. */
    private DualListModel<DispositivoVO> listaDispositivos;

    /** Atributo lista status atual. */
    private List<SelectItem> listaStatusAtual;

    /** Atributo lista novo status. */
    private List<SelectItem> listaNovoStatus;

    /**
     * Construtor Padrao Instancia um novo objeto InventarioDispositivoBean.
     */
    public InventarioDispositivoBean() {
        inicializar();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Manutenção de dispositivos");
        inicializar();

        return "inventariodispositivo";
    }

    /**
     * Nome: inicializar Inicializar.
     * @see
     */
    private void inicializar() {
        this.listaStatusAtual = new ArrayList<SelectItem>();
        SelectItem lsa = new SelectItem();
        lsa.setValue("1");
        lsa.setLabel("Pronto");
        this.listaStatusAtual.add(lsa);
        lsa = new SelectItem();
        lsa.setValue("2");
        lsa.setLabel("Defeito");
        this.listaStatusAtual.add(lsa);
        lsa = new SelectItem();
        lsa.setValue("3");
        lsa.setLabel("Em uso");
        this.listaStatusAtual.add(lsa);
        lsa = new SelectItem();
        lsa.setValue("4");
        lsa.setLabel("Descarte");
        this.listaStatusAtual.add(lsa);
        lsa = new SelectItem();
        lsa.setValue("5");
        lsa.setLabel("Devolvido");
        this.listaStatusAtual.add(lsa);
        lsa = new SelectItem();
        lsa.setValue("6");
        lsa.setLabel("Fabrica");
        this.listaStatusAtual.add(lsa);
        this.listaNovoStatus = new ArrayList<SelectItem>();
        SelectItem lns = new SelectItem();
        lns.setValue("1");
        lns.setLabel("Pronto");
        this.listaNovoStatus.add(lns);
        lns = new SelectItem();
        lns.setValue("2");
        lns.setLabel("Defeito");
        this.listaNovoStatus.add(lns);
        lns = new SelectItem();
        lns.setValue("3");
        lns.setLabel("Em uso");
        this.listaNovoStatus.add(lns);
        lns = new SelectItem();
        lns.setValue("4");
        lns.setLabel("Descarte");
        this.listaNovoStatus.add(lns);
        lns = new SelectItem();
        lns.setValue("5");
        lns.setLabel("Devolvido");
        this.listaNovoStatus.add(lns);
        lns = new SelectItem();
        lns.setValue("6");
        lns.setLabel("Fabrica");
        this.listaNovoStatus.add(lns);

        List<DispositivoVO> source = GacMock.getListaDispositivos();
        List<DispositivoVO> target = new ArrayList<DispositivoVO>();

        this.listaDispositivos = new DualListModel<DispositivoVO>(source, target);

    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {
        setFacesMessage("message.inventariodispositivo.save.sucess");
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getDescricaoDispositivo Recupera o valor do atributo 'descricaoDispositivo'.
     * @return valor do atributo 'descricaoDispositivo'
     * @see
     */
    public String getDescricaoDispositivo() {
        return descricaoDispositivo;
    }

    /**
     * Nome: setDescricaoDispositivo Registra o valor do atributo 'descricaoDispositivo'.
     * @param descricaoDispositivo valor do atributo descricao dispositivo
     * @see
     */
    public void setDescricaoDispositivo(String descricaoDispositivo) {
        this.descricaoDispositivo = descricaoDispositivo;
    }

    /**
     * Nome: getListaDispositivos Recupera o valor do atributo 'listaDispositivos'.
     * @return valor do atributo 'listaDispositivos'
     * @see
     */
    public DualListModel<DispositivoVO> getListaDispositivos() {
        return listaDispositivos;
    }

    /**
     * Nome: setListaDispositivos Registra o valor do atributo 'listaDispositivos'.
     * @param listaDispositivos valor do atributo lista dispositivos
     * @see
     */
    public void setListaDispositivos(DualListModel<DispositivoVO> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }

    /**
     * Nome: getListaStatusAtual Recupera o valor do atributo 'listaStatusAtual'.
     * @return valor do atributo 'listaStatusAtual'
     * @see
     */
    public List<SelectItem> getListaStatusAtual() {
        return listaStatusAtual;
    }

    /**
     * Nome: setListaStatusAtual Registra o valor do atributo 'listaStatusAtual'.
     * @param listaStatusAtual valor do atributo lista status atual
     * @see
     */
    public void setListaStatusAtual(List<SelectItem> listaStatusAtual) {
        this.listaStatusAtual = listaStatusAtual;
    }

    /**
     * Nome: getListaNovoStatus Recupera o valor do atributo 'listaNovoStatus'.
     * @return valor do atributo 'listaNovoStatus'
     * @see
     */
    public List<SelectItem> getListaNovoStatus() {
        return listaNovoStatus;
    }

    /**
     * Nome: setListaNovoStatus Registra o valor do atributo 'listaNovoStatus'.
     * @param listaNovoStatus valor do atributo lista novo status
     * @see
     */
    public void setListaNovoStatus(List<SelectItem> listaNovoStatus) {
        this.listaNovoStatus = listaNovoStatus;
    }

    /**
     * Nome: getValorStatusAtual Recupera o valor do atributo 'valorStatusAtual'.
     * @return valor do atributo 'valorStatusAtual'
     * @see
     */
    public Integer getValorStatusAtual() {
        return valorStatusAtual;
    }

    /**
     * Nome: setValorStatusAtual Registra o valor do atributo 'valorStatusAtual'.
     * @param valorStatusAtual valor do atributo valor status atual
     * @see
     */
    public void setValorStatusAtual(Integer valorStatusAtual) {
        this.valorStatusAtual = valorStatusAtual;
    }

    /**
     * Nome: getValorNovoStatus Recupera o valor do atributo 'valorNovoStatus'.
     * @return valor do atributo 'valorNovoStatus'
     * @see
     */
    public Integer getValorNovoStatus() {
        return valorNovoStatus;
    }

    /**
     * Nome: setValorNovoStatus Registra o valor do atributo 'valorNovoStatus'.
     * @param valorNovoStatus valor do atributo valor novo status
     * @see
     */
    public void setValorNovoStatus(Integer valorNovoStatus) {
        this.valorNovoStatus = valorNovoStatus;
    }

}
