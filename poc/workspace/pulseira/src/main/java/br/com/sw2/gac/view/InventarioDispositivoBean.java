package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.primefaces.model.DualListModel;

import br.com.sw2.gac.vo.DispositivoVO;

@ManagedBean
@ViewScoped
public class InventarioDispositivoBean extends BaseBean {

    
    private Integer idDispositivo;
    private String descricaoDispositivo;
    private Integer valorStatusAtual;
    private Integer valorNovoStatus;
    private DualListModel<DispositivoVO> listaDispositivos;  
    private List<SelectItem> listaStatusAtual;
    private List<SelectItem> listaNovoStatus;
    
    public InventarioDispositivoBean() {
        inicializar();
    }

    public String iniciarPagina() {
        setTituloCabecalho("Manutenção de dispositivos");
        inicializar();
        
        return "inventariodispositivo";
    }

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
        this. listaNovoStatus.add(lns);
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
        
        List<DispositivoVO> source = new ArrayList<DispositivoVO>();  
        List<DispositivoVO> target = new ArrayList<DispositivoVO>();
        DispositivoVO dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(1);
        dispositivo.setDescricaoDispositivo("Dispositivo 1");
        source.add(dispositivo);
        
        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(2);
        dispositivo.setDescricaoDispositivo("Dispositivo 2");
        source.add(dispositivo);        
        
        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(3);
        dispositivo.setDescricaoDispositivo("Dispositivo 3");
        source.add(dispositivo);  
     
        dispositivo = new DispositivoVO();
        dispositivo.setIdDispositivo(4);
        dispositivo.setDescricaoDispositivo("Dispositivo 4");
        source.add(dispositivo);  
        
        this.listaDispositivos = new DualListModel<DispositivoVO>(source, target);
        
        
    }
    
    public void salvar(ActionEvent actionEvent) {
        setFacesMessage("message.inventariodispositivo.save.sucess");
    }

    public Integer getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(Integer idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getDescricaoDispositivo() {
        return descricaoDispositivo;
    }

    public void setDescricaoDispositivo(String descricaoDispositivo) {
        this.descricaoDispositivo = descricaoDispositivo;
    }

    public DualListModel<DispositivoVO> getListaDispositivos() {
        return listaDispositivos;
    }

    public void setListaDispositivos(DualListModel<DispositivoVO> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }

    public List<SelectItem> getListaStatusAtual() {
        return listaStatusAtual;
    }

    public void setListaStatusAtual(List<SelectItem> listaStatusAtual) {
        this.listaStatusAtual = listaStatusAtual;
    }

    public List<SelectItem> getListaNovoStatus() {
        return listaNovoStatus;
    }

    public void setListaNovoStatus(List<SelectItem> listaNovoStatus) {
        this.listaNovoStatus = listaNovoStatus;
    }

    public Integer getValorStatusAtual() {
        return valorStatusAtual;
    }

    public void setValorStatusAtual(Integer valorStatusAtual) {
        this.valorStatusAtual = valorStatusAtual;
    }

    public Integer getValorNovoStatus() {
        return valorNovoStatus;
    }

    public void setValorNovoStatus(Integer valorNovoStatus) {
        this.valorNovoStatus = valorNovoStatus;
    }    
    
    
}
