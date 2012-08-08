package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.DispositivoBusiness;
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

    private static final String ID_DISPOSITIVO = "idDispositivo";

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

        DispositivoBusiness business = new DispositivoBusiness();
        this.listaDispositivos = business.recuperaListaDispositivos();
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

        Integer idDispositivo = Integer.parseInt(getRequestParameter(ID_DISPOSITIVO));
        DispositivoVO vo = (DispositivoVO) findInListById(this.listaDispositivos, ID_DISPOSITIVO,
                idDispositivo);
        this.dispositivo = new DispositivoVO();
        this.dispositivo.setId(vo.getId());
        this.dispositivo.setDescricaoDispositivo(vo.getDescricaoDispositivo());
        this.dispositivo.setTipoDispositivo(vo.getTipoDispositivo());
        this.dispositivo.setDataFabricacao(vo.getDataFabricacao());
        this.dispositivo.setDataEntrada(vo.getDataEntrada());
        this.dispositivo.setEstadoAtual(vo.getEstadoAtual());
        this.dispositivo.setDataProximaManutencao(vo.getDataProximaManutencao());
        this.dispositivo.setDataSucata(vo.getDataSucata());
        this.dispositivo.setLocal(vo.getLocal());

    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        DispositivoVO remover = (DispositivoVO) findInListById(this.listaDispositivos,
                ID_DISPOSITIVO, this.dispositivo.getId());
        this.listaDispositivos.remove(remover);
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.cadastrodispositivo.save.sucess");

        DispositivoBusiness business = new DispositivoBusiness();
        business.adicionarNovoDispositivo(this.dispositivo);

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
