package br.com.sw2.gac.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.SmsPadraoVO;

/**
 * <b>Descrição: Controller da tela de cadastro de SMS.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class SmsPadraoBean extends BaseBean {

    /** Atributo id sms. */
    private Integer idSms = 0;

    /** Atributo titulo mensagem. */
    private String tituloMensagem = "";

    /** Atributo descricao mensagem. */
    private String descricaoMensagem = "";

    /** Atributo lista mensagens. */
    private List<SmsPadraoVO> listaMensagens;

    /**
     * Construtor Padrao Instancia um novo objeto SmsPadraoBean.
     */
    public SmsPadraoBean() {
        this.listaMensagens = popularListaMensagens();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Cadastro SMS Padrão");
        this.listaMensagens = popularListaMensagens();
        return "smspadrao";
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        this.idSms = 0;
        this.tituloMensagem = "";
        this.descricaoMensagem = "";
    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        int id = Integer.parseInt(getRequestParameter("idSms"));
        SmsPadraoVO edit = null;
        for (SmsPadraoVO item : this.listaMensagens) {
            if (item.getIdSms().intValue() == id) {
                edit = item;
                break;
            }
        }
        this.tituloMensagem = edit.getTitulo();
        this.descricaoMensagem = edit.getDescricao();
        this.idSms = edit.getIdSms();
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.smspadrao.save.sucess");
        if (this.idSms > 0) {
            for (SmsPadraoVO item : this.listaMensagens) {
                if (item.getIdSms().equals(this.idSms)) {
                    item.setIdSms(this.idSms);
                    item.setTitulo(this.tituloMensagem);
                    item.setDescricao(this.descricaoMensagem);
                }
            }
        } else {
            SmsPadraoVO vo = new SmsPadraoVO();
            vo.setTitulo(this.tituloMensagem);
            vo.setDescricao(this.descricaoMensagem);
            vo.setIdSms(this.listaMensagens.size());
            this.listaMensagens.add(vo);
        }

    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        SmsPadraoVO remover = null;
        for (SmsPadraoVO item : this.listaMensagens) {
            if (item.getIdSms().equals(this.idSms)) {
                remover = item;
            }
        }
        if (null != remover) {
            this.listaMensagens.remove(remover);
        }
    }

    /**
     * Nome: popularListaMensagens Popular lista mensagens.
     * @return list
     * @see
     */
    private List<SmsPadraoVO> popularListaMensagens() {
        return GacMock.getListaMensagensSMS();
    }

    /**
     * Nome: getTituloMensagem Recupera o valor do atributo 'tituloMensagem'.
     * @return valor do atributo 'tituloMensagem'
     * @see
     */
    public String getTituloMensagem() {
        return tituloMensagem;
    }

    /**
     * Nome: setTituloMensagem Registra o valor do atributo 'tituloMensagem'.
     * @param tituloMensagem valor do atributo titulo mensagem
     * @see
     */
    public void setTituloMensagem(String tituloMensagem) {
        this.tituloMensagem = tituloMensagem;
    }

    /**
     * Nome: getDescricaoMensagem Recupera o valor do atributo 'descricaoMensagem'.
     * @return valor do atributo 'descricaoMensagem'
     * @see
     */
    public String getDescricaoMensagem() {
        return descricaoMensagem;
    }

    /**
     * Nome: setDescricaoMensagem Registra o valor do atributo 'descricaoMensagem'.
     * @param descricaoMensagem valor do atributo descricao mensagem
     * @see
     */
    public void setDescricaoMensagem(String descricaoMensagem) {
        this.descricaoMensagem = descricaoMensagem;
    }

    /**
     * Nome: getListaMensagens Recupera o valor do atributo 'listaMensagens'.
     * @return valor do atributo 'listaMensagens'
     * @see
     */
    public List<SmsPadraoVO> getListaMensagens() {
        return listaMensagens;
    }

    /**
     * Nome: setListaMensagens Registra o valor do atributo 'listaMensagens'.
     * @param listaMensagens valor do atributo lista mensagens
     * @see
     */
    public void setListaMensagens(List<SmsPadraoVO> listaMensagens) {
        this.listaMensagens = listaMensagens;
    }

    /**
     * Nome: getIdSms Recupera o valor do atributo 'idSms'.
     * @return valor do atributo 'idSms'
     * @see
     */
    public Integer getIdSms() {
        return idSms;
    }

    /**
     * Nome: setIdSms Registra o valor do atributo 'idSms'.
     * @param idSms valor do atributo id sms
     * @see
     */
    public void setIdSms(Integer idSms) {
        this.idSms = idSms;
    }

}
