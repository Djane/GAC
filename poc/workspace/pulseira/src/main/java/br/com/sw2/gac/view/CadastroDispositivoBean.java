package br.com.sw2.gac.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.LocalizacaoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class CadastroDispositivoBean extends BaseBean {

    /** Atributo dispositivo. */
    private DispositivoVO dispositivo;

    /** Atributo lista dispositivos. */
    private List<DispositivoVO> listaDispositivos;

    /** Atributo lista tipo dispositivo. */
    private List<SelectItem> listaTipoDispositivo;

    /** Atributo lista estado dispositivo. */
    private List<SelectItem> listaEstadoDispositivo;

    /** Atributo lista localizacao dispositivo. */
    private List<SelectItem> listaLocalizacaoDispositivo;

    /**
     * Construtor Padrao Instancia um novo objeto CadastroDispositivoBean.
     */
    public CadastroDispositivoBean() {
        this.dispositivo = new DispositivoVO();
        this.listaTipoDispositivo = getSelectIems(TipoDispositivo.class);
        this.listaEstadoDispositivo = getSelectIems(EstadoDispositivo.class);
        this.listaLocalizacaoDispositivo = getSelectIems(LocalizacaoDispositivo.class);

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

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
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
        DispositivoVO editar = (DispositivoVO) findInListById(this.listaDispositivos,
                "idDispositivo", idDispositivo);
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
        DispositivoVO remover = (DispositivoVO) findInListById(this.listaDispositivos,
                "idDispositivo", this.dispositivo.getIdDispositivo());
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
            dispositivo.setIdDispositivo(this.listaDispositivos.size() + 1);
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

    /**
     * Nome: getDispositivo Recupera o valor do atributo 'dispositivo'.
     * @return valor do atributo 'dispositivo'
     * @see
     */
    public DispositivoVO getDispositivo() {
        return dispositivo;
    }

    /**
     * Nome: setDispositivo Registra o valor do atributo 'dispositivo'.
     * @param dispositivo valor do atributo dispositivo
     * @see
     */
    public void setDispositivo(DispositivoVO dispositivo) {
        this.dispositivo = dispositivo;
    }

    /**
     * Nome: getListaDispositivos Recupera o valor do atributo 'listaDispositivos'.
     * @return valor do atributo 'listaDispositivos'
     * @see
     */
    public List<DispositivoVO> getListaDispositivos() {
        return listaDispositivos;
    }

    /**
     * Nome: setListaDispositivos Registra o valor do atributo 'listaDispositivos'.
     * @param listaDispositivos valor do atributo lista dispositivos
     * @see
     */
    public void setListaDispositivos(List<DispositivoVO> listaDispositivos) {
        this.listaDispositivos = listaDispositivos;
    }

    /**
     * Nome: getListaTipoDispositivo Recupera o valor do atributo 'listaTipoDispositivo'.
     * @return valor do atributo 'listaTipoDispositivo'
     * @see
     */
    public List<SelectItem> getListaTipoDispositivo() {
        return listaTipoDispositivo;
    }

    /**
     * Nome: setListaTipoDispositivo Registra o valor do atributo 'listaTipoDispositivo'.
     * @param listaTipoDispositivo valor do atributo lista tipo dispositivo
     * @see
     */
    public void setListaTipoDispositivo(List<SelectItem> listaTipoDispositivo) {
        this.listaTipoDispositivo = listaTipoDispositivo;
    }

    /**
     * Nome: getListaEstadoDispositivo Recupera o valor do atributo 'listaEstadoDispositivo'.
     * @return valor do atributo 'listaEstadoDispositivo'
     * @see
     */
    public List<SelectItem> getListaEstadoDispositivo() {
        return listaEstadoDispositivo;
    }

    /**
     * Nome: setListaEstadoDispositivo Registra o valor do atributo 'listaEstadoDispositivo'.
     * @param listaEstadoDispositivo valor do atributo lista estado dispositivo
     * @see
     */
    public void setListaEstadoDispositivo(List<SelectItem> listaEstadoDispositivo) {
        this.listaEstadoDispositivo = listaEstadoDispositivo;
    }

    /**
     * Nome: getListaLocalizacaoDispositivo Recupera o valor do atributo
     * 'listaLocalizacaoDispositivo'.
     * @return valor do atributo 'listaLocalizacaoDispositivo'
     * @see
     */
    public List<SelectItem> getListaLocalizacaoDispositivo() {
        return listaLocalizacaoDispositivo;
    }

    /**
     * Nome: setListaLocalizacaoDispositivo Registra o valor do atributo
     * 'listaLocalizacaoDispositivo'.
     * @param listaLocalizacaoDispositivo valor do atributo lista localizacao dispositivo
     * @see
     */
    public void setListaLocalizacaoDispositivo(List<SelectItem> listaLocalizacaoDispositivo) {
        this.listaLocalizacaoDispositivo = listaLocalizacaoDispositivo;
    }
}
