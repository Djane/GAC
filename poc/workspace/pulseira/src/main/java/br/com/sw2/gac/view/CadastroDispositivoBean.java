package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;
import br.com.sw2.gac.vo.SmsPadraoVO;

@ManagedBean
@ViewScoped
public class CadastroDispositivoBean extends BaseBean {

    private DispositivoVO dispositivo;
    
    private List<DispositivoVO> listaDispositivos;

    private List<SelectItem> listaTipoDispositivo;
    private List<SelectItem> listaEstadoDispositivo;
    private List<SelectItem> listaLocalizacaoDispositivo;
    
    public CadastroDispositivoBean() {
        
        SelectItem selecione = new SelectItem("0", "Selecione");
        this.dispositivo = new DispositivoVO();
        this.listaTipoDispositivo = new ArrayList<SelectItem>();
        this.listaTipoDispositivo.add(selecione);
        for (TipoDispositivo item : TipoDispositivo.values()) {
            this.listaTipoDispositivo.add(new SelectItem(item.getCodTipoDispositivo(), item.name()));
        }

        this.listaEstadoDispositivo = new ArrayList<SelectItem>();
        this.listaEstadoDispositivo.add(selecione);
        for (EstadoDispositivo item : EstadoDispositivo.values()) {
            this.listaEstadoDispositivo.add(new SelectItem(item.getValue(), item.getLabel()));
        }
        
        this.listaLocalizacaoDispositivo = new ArrayList<SelectItem>();
        this.listaLocalizacaoDispositivo.add(selecione);
        for (LocalizacaoDispositivo item : LocalizacaoDispositivo.values()) {
            this.listaLocalizacaoDispositivo.add(new SelectItem(item.getCodLocal(), item.getDescLocal()));
        }
        
        setTituloCabecalho("label.cadastrodispositivo.view.title", true);
        this.listaDispositivos = GacMock.getListaDispositivos();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.cadastrodispositivo.view.title", true);
        return "cadastroDispositivo";
    }

    public void novo(ActionEvent actionEvent) {
        this.dispositivo = new DispositivoVO();
    }
    
    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        Integer idDispositivo = Integer.parseInt(getRequestParameter("idDispositivo"));
        DispositivoVO editar = (DispositivoVO) findInListById(this.listaDispositivos, "idDispositivo", idDispositivo);
        this.dispositivo = new DispositivoVO();
        this.dispositivo.setIdDispositivo(editar.getIdDispositivo());
        this.dispositivo.setDescricaoDispositivo(editar.getDescricaoDispositivo());
        this.dispositivo.setTipoDispositivo(editar.getTipoDispositivo());
        this.dispositivo.setDataFabricacao(editar.getDataFabricacao());
        this.dispositivo.setDataEntrada(editar.getDataEntrada());        
        this.dispositivo.setEstado(editar.getEstado());
        this.dispositivo.setDataProximaManutencao(editar.getDataProximaManutencao());
        this.dispositivo.setDataSucata(editar.getDataSucata());
        this.dispositivo.setLocal(editar.getLocal());

    }    
    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {        
        DispositivoVO remover = (DispositivoVO) findInListById(this.listaDispositivos, "idDispositivo", this.dispositivo.getIdDispositivo());
        this.listaDispositivos.remove(remover);
    }
    
    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.cadastrodispositivo.save.sucess");
        if (this.dispositivo.getIdDispositivo() > 0) {
            
            for (DispositivoVO item : this.listaDispositivos) {
                if (item.getIdDispositivo().equals(this.getDispositivo().getIdDispositivo())) {
                    item.setIdDispositivo(this.dispositivo.getIdDispositivo());
                    item.setDescricaoDispositivo(this.dispositivo.getDescricaoDispositivo());
                    item.setTipoDispositivo(this.dispositivo.getTipoDispositivo());
                    item.setDataFabricacao(this.dispositivo.getDataFabricacao());
                    item.setDataEntrada(this.dispositivo.getDataEntrada());        
                    item.setEstado(this.dispositivo.getEstado());
                    item.setDataProximaManutencao(this.dispositivo.getDataProximaManutencao());
                    item.setDataSucata(this.dispositivo.getDataSucata());
                    item.setLocal(this.dispositivo.getLocal());
                }            
            } 
        } else {
            DispositivoVO dispositivo = new DispositivoVO();
            dispositivo.setIdDispositivo(this.listaDispositivos.size()+1);
            dispositivo.setDescricaoDispositivo(this.dispositivo.getDescricaoDispositivo());
            dispositivo.setTipoDispositivo(this.dispositivo.getTipoDispositivo());
            dispositivo.setDataFabricacao(this.dispositivo.getDataFabricacao());
            dispositivo.setDataEntrada(this.dispositivo.getDataEntrada());        
            dispositivo.setEstado(this.dispositivo.getEstado());
            dispositivo.setDataProximaManutencao(this.dispositivo.getDataProximaManutencao());
            dispositivo.setDataSucata(this.dispositivo.getDataSucata());
            dispositivo.setLocal(this.dispositivo.getLocal());
            this.listaDispositivos.add(dispositivo);
        }
    }    
    
    public DispositivoVO getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(DispositivoVO dispositivo) {
        this.dispositivo = dispositivo;
    }

    public List<DispositivoVO> getListaDispositivos() {
        return listaDispositivos;
    }

    public void setListaDispositivos(List<DispositivoVO> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }

    public List<SelectItem> getListaTipoDispositivo() {
        return listaTipoDispositivo;
    }

    public void setListaTipoDispositivo(List<SelectItem> listaTipoDispositivo) {
        this.listaTipoDispositivo = listaTipoDispositivo;
    }

    public List<SelectItem> getListaEstadoDispositivo() {
        return listaEstadoDispositivo;
    }

    public void setListaEstadoDispositivo(List<SelectItem> listaEstadoDispositivo) {
        this.listaEstadoDispositivo = listaEstadoDispositivo;
    }

    public List<SelectItem> getListaLocalizacaoDispositivo() {
        return listaLocalizacaoDispositivo;
    }

    public void setListaLocalizacaoDispositivo(List<SelectItem> listaLocalizacaoDispositivo) {
        this.listaLocalizacaoDispositivo = listaLocalizacaoDispositivo;
    }
}
