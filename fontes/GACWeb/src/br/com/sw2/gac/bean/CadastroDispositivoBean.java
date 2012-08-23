package br.com.sw2.gac.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.DispositivoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.tools.EstadoDispositivo;
import br.com.sw2.gac.tools.TipoDispositivo;
import br.com.sw2.gac.vo.DispositivoVO;

/**
 * Bean responsável pelo cadastro dos dispositivos via tela.
 * @author: ddiniz
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class CadastroDispositivoBean extends BaseBean {

	private static final String CADASTRO_DISPOSITIVO_TITLE = "label.cadastrodispositivo.view.title";

	private static final String CADASTRO_DISPOSITIVO = "cadastroDispositivo";

	private static final long serialVersionUID = -9165566529873204003L;

	private static final String ID_DISPOSITIVO = "idDispositivo";

    /** Atributo dispositivo. */
    private DispositivoVO dispositivo;

    /** Atributo lista dispositivos. */
    private List<DispositivoVO> listaDispositivos;

    /** Atributo lista tipo dispositivo. */
    private List<SelectItem> listaTipoDispositivo;

    /** Atributo que guarda o ID original de um dispositivo alterado. */
    private String idOriginal;

    private DispositivoBusiness business = new DispositivoBusiness();

    /**
     * Construtor Padrao Instancia um novo objeto CadastroDispositivoBean.
     */
    public CadastroDispositivoBean() {
        this.dispositivo = new DispositivoVO();
        // Todo dispositivo quando cadastrado assume o Estado Novo
        this.dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
        this.listaTipoDispositivo = getSelectIems(TipoDispositivo.class);

        setTituloCabecalho(CADASTRO_DISPOSITIVO_TITLE, true);

        DispositivoBusiness business = new DispositivoBusiness();
        this.listaDispositivos = business.recuperaListaDispositivos();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho(CADASTRO_DISPOSITIVO_TITLE, true);
        return CADASTRO_DISPOSITIVO;
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        this.dispositivo = new DispositivoVO();
        this.idOriginal = null;
    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        String idDispositivo = getRequestParameter(ID_DISPOSITIVO);
        this.setIdOriginal(idDispositivo);
        DispositivoVO vo = (DispositivoVO) findInListById(this.listaDispositivos, ID_DISPOSITIVO,
                idDispositivo);

        this.dispositivo = new DispositivoVO();
        this.dispositivo.setIdDispositivo(vo.getIdDispositivo());
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
                ID_DISPOSITIVO, this.dispositivo.getIdDispositivo());
        try {
			business.apagarDispositivo(this.dispositivo.getIdDispositivo());
			this.listaDispositivos.remove(remover);
		} catch (BusinessException e) {
			setFacesErrorMessage("message.cadastrodispositivo.delete.error");
		}
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        // Recuperar o usuário logado na sessão e colocar no VO do dispositivo
        BaseBean base = new BaseBean();
        this.dispositivo.setUsuario(base.getUsuarioLogado());

       // Criar o novo dispositivo com os dados informados pelo usuário
        try {
			business.adicionarNovoDispositivo(this.dispositivo, this.idOriginal);
			// Atualiza a lista de dispositivos cadastrados
			this.listaDispositivos.add(this.dispositivo);
			setFacesMessage("message.cadastrodispositivo.save.sucess");
			// Remove os dados da tela
			this.dispositivo = new DispositivoVO();
			this.dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
		} catch (BusinessException e) {
			setFacesErrorBusinessMessage(e.getBusinessMessage(e.getMessage()));
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

	public String getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(String idOriginal) {
		this.idOriginal = idOriginal;
	}
}
