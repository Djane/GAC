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
 * <b>Descrição: Controller da tela de cadastro de dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class CadastroDispositivoBean extends BaseBean {

	private static final long serialVersionUID = -9165566529873204003L;

    /** 
     * Objeto que representa os dados do dispositivo a ser salvo. 
     * */
    private DispositivoVO dispositivo;

    /** 
     * Lista de dispositivos existentes exibidos na tela. 
     * */
    private List<DispositivoVO> listaDispositivos;

    /** 
     * Atributo lista tipo dispositivo. 
     * */
    private List<SelectItem> listaTipoDispositivo;

    /** 
     * Atributo que guarda o ID original de um dispositivo alterado. 
     * */
    private String idOriginal;

    /**
     * Objeto de negócio para manipulação de dispositivos.
     */
    private DispositivoBusiness dispositivoBusiness = new DispositivoBusiness();

    public CadastroDispositivoBean() {
        this.dispositivo = new DispositivoVO();
        // Todo dispositivo quando cadastrado assume o Estado Novo
        this.dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
        this.listaTipoDispositivo = getSelectItems(TipoDispositivo.class);

        setTituloCabecalho("label.cadastrodispositivo.view.title", true);

        DispositivoBusiness business = new DispositivoBusiness();
        this.listaDispositivos = business.recuperaListaDispositivos();
    }

    /**
     * Inicia a página do cadastro de dispositivo.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.cadastrodispositivo.view.title", true);
        return "cadastroDispositivo";        
    }

    /**
     * Método que prepara a tela para digitação de um novo dispositivo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        this.dispositivo = new DispositivoVO();
     // Todo dispositivo quando cadastrado assume o Estado Novo
        this.dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
        this.idOriginal = null;
    }

    /**
     * Método que prepara os dados para serem editados na tela de cadastro.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        String idDispositivo = getRequestParameter("idDispositivo");
        this.setIdOriginal(idDispositivo);
        DispositivoVO vo = (DispositivoVO) findInListById(this.listaDispositivos, "idDispositivo", idDispositivo);

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
     * ActionListener responsável por apagar fisicamente um dispositivo do cadastro.
     * @param ActionEvent actionEvent
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        DispositivoVO remover = (DispositivoVO) findInListById(this.listaDispositivos, "idDispositivo", this.dispositivo.getIdDispositivo());
        try {            
			this.dispositivoBusiness.apagarDispositivo(this.dispositivo.getIdDispositivo());
			this.logger.registrarAcao(this.getUsuarioLogado().getLogin(), "O dispositivo com id " + this.dispositivo.getIdDispositivo() + " foi excluido. Detalhes: " + this.dispositivo.toString());
			this.listaDispositivos.remove(remover);			
		} catch (BusinessException e) {
		    setFacesErrorBusinessMessage(e.getBusinessMessage(e.getMessage()));
		    this.logger.registrarAcao(this.getUsuarioLogado().getLogin(), "Não foi possível excluir o dispositivo com o id " + this.dispositivo.getIdDispositivo() + ". Exception: " + e.getMessage());
		}
    }

    /**
     * ActionListener responsável por recupera os dados preenchidos na página de cadastro de 
     * dispositivos e salva-los.
     * @param ActionEvent actionEvent
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        // Recuperar o usuário logado na sessão e colocar no VO do dispositivo
        BaseBean base = new BaseBean();
        this.dispositivo.setUsuario(base.getUsuarioLogado());

       // Criar o novo dispositivo com os dados informados pelo usuário
        try {
			this.dispositivoBusiness.adicionarNovoDispositivo(this.dispositivo, this.idOriginal);
			this.logger.registrarAcao(this.getUsuarioLogado().getLogin(), "O dispositivo com id " + this.idOriginal + " foi salvo com os seguintes dados "+ this.dispositivo.toString());
			
			// Atualiza a lista de dispositivos cadastrados
	        this.listaDispositivos = this.dispositivoBusiness.recuperaListaDispositivos();
			
			// Remove os dados da tela
			this.dispositivo = new DispositivoVO();
			this.dispositivo.setEstadoAtual(EstadoDispositivo.Novo.getValue());
			
			setFacesMessage("message.cadastrodispositivo.save.sucess");			
			
		} catch (BusinessException e) {
			setFacesErrorBusinessMessage(e.getBusinessMessage(e.getMessage()));
			this.logger.registrarAcao(this.getUsuarioLogado().getLogin(), "Não foi possível salvar o dispositivo com id " + this.idOriginal + ". Dados: "+ this.dispositivo.toString() + ". Exception: " + e.getMessage());
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

	public String getIdOriginal() {
		return idOriginal;
	}

	public void setIdOriginal(String idOriginal) {
		this.idOriginal = idOriginal;
	}
}
