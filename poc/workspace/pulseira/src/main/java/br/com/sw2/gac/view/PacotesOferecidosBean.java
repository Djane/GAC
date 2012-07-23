package br.com.sw2.gac.view;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.PacoteVO;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class PacotesOferecidosBean extends BaseBean {

    /** Atributo id pacote. */
    private Integer idPacote = 0;

    /** Atributo titulo pacote. */
    private String tituloPacote = "";

    /** Atributo descricao pacote. */
    private String descricaoPacote = "";

    /** Atributo lista pacotes. */
    private List<PacoteVO> listaPacotes;

    /**
     * Construtor Padrao Instancia um novo objeto PacotesOferecidosBean.
     */
    public PacotesOferecidosBean() {
        this.listaPacotes = popularlistaPacotes();
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("Cadastro de pacotes oferecidos");
        this.listaPacotes = popularlistaPacotes();
        return "pacotesoferecidos";
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        limparAtributos();
    }

    /**
     * Nome: limparAtributos Limpar atributos.
     * @see
     */
    private void limparAtributos() {
        this.idPacote = 0;
        this.tituloPacote = "";
        this.descricaoPacote = "";
    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        int id = Integer.parseInt(getRequestParameter("idPacote"));
        PacoteVO edit = null;
        for (PacoteVO item : this.listaPacotes) {
            if (item.getIdPacote().intValue() == id) {
                edit = item;
                break;
            }
        }
        this.tituloPacote = edit.getTitulo();
        this.descricaoPacote = edit.getDescricao();
        this.idPacote = edit.getIdPacote();
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.pacotesoferecidos.save.sucess");
        if (this.idPacote > 0) {
            for (PacoteVO item : this.listaPacotes) {
                if (item.getIdPacote().equals(this.idPacote)) {
                    item.setIdPacote(this.idPacote);
                    item.setTitulo(this.tituloPacote);
                    item.setDescricao(this.descricaoPacote);
                }
            }
        } else {
            PacoteVO vo = new PacoteVO();
            vo.setTitulo(this.tituloPacote);
            vo.setDescricao(this.descricaoPacote);
            vo.setIdPacote(this.listaPacotes.size());
            this.listaPacotes.add(vo);
        }
        limparAtributos();

    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        PacoteVO remover = null;
        for (PacoteVO item : this.listaPacotes) {
            if (item.getIdPacote().equals(this.idPacote)) {
                remover = item;
            }
        }
        if (null != remover) {
            this.listaPacotes.remove(remover);
        }
        limparAtributos();
    }

    /**
     * Nome: popularlistaPacotes Popularlista pacotes.
     * @return list
     * @see
     */
    private List<PacoteVO> popularlistaPacotes() {
        this.listaPacotes = new ArrayList<PacoteVO>();
        PacoteVO pacote = new PacoteVO();
        pacote.setIdPacote(1);
        pacote.setTitulo("Titulo pacote 1");
        pacote.setDescricao("Descrição pacote 1");
        this.listaPacotes.add(pacote);
        return listaPacotes;
    }

    /**
     * Nome: getIdPacote Recupera o valor do atributo 'idPacote'.
     * @return valor do atributo 'idPacote'
     * @see
     */
    public Integer getIdPacote() {
        return idPacote;
    }

    /**
     * Nome: setIdPacote Registra o valor do atributo 'idPacote'.
     * @param idPacote valor do atributo id pacote
     * @see
     */
    public void setIdPacote(Integer idPacote) {
        this.idPacote = idPacote;
    }

    /**
     * Nome: getTituloPacote Recupera o valor do atributo 'tituloPacote'.
     * @return valor do atributo 'tituloPacote'
     * @see
     */
    public String getTituloPacote() {
        return tituloPacote;
    }

    /**
     * Nome: setTituloPacote Registra o valor do atributo 'tituloPacote'.
     * @param tituloPacote valor do atributo titulo pacote
     * @see
     */
    public void setTituloPacote(String tituloPacote) {
        this.tituloPacote = tituloPacote;
    }

    /**
     * Nome: getDescricaoPacote Recupera o valor do atributo 'descricaoPacote'.
     * @return valor do atributo 'descricaoPacote'
     * @see
     */
    public String getDescricaoPacote() {
        return descricaoPacote;
    }

    /**
     * Nome: setDescricaoPacote Registra o valor do atributo 'descricaoPacote'.
     * @param descricaoPacote valor do atributo descricao pacote
     * @see
     */
    public void setDescricaoPacote(String descricaoPacote) {
        this.descricaoPacote = descricaoPacote;
    }

    /**
     * Nome: getListaPacotes Recupera o valor do atributo 'listaPacotes'.
     * @return valor do atributo 'listaPacotes'
     * @see
     */
    public List<PacoteVO> getListaPacotes() {
        return listaPacotes;
    }

    /**
     * Nome: setListaPacotes Registra o valor do atributo 'listaPacotes'.
     * @param listaPacotes valor do atributo lista pacotes
     * @see
     */
    public void setListaPacotes(List<PacoteVO> listaPacotes) {
        this.listaPacotes = listaPacotes;
    }
}
