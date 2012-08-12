package br.com.sw2.gac.view;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.PacoteServicoVO;

/**
 * <b>Descrição: Controller da tela de cadstro de pacotes.</b> <br>
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

    /** Atributo preco. */
    private BigDecimal preco;

    /** Atributo lista pacotes. */
    private List<PacoteServicoVO> listaPacotes;

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
        setTituloCabecalho("label.pacotesoferecidos.view.title", true);
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
        this.preco = new BigDecimal("0.00");
    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {

        int id = Integer.parseInt(getRequestParameter("idPacote"));
        PacoteServicoVO edit = null;
        for (PacoteServicoVO item : this.listaPacotes) {
            if (item.getIdPacote().intValue() == id) {
                edit = item;
                break;
            }
        }
        this.tituloPacote = edit.getTitulo();
        this.descricaoPacote = edit.getDescricao();
        this.idPacote = edit.getIdPacote();
        this.preco = edit.getPreco();
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        setFacesMessage("message.pacotesoferecidos.save.sucess");
        if (this.idPacote > 0) {
            for (PacoteServicoVO item : this.listaPacotes) {
                if (item.getIdPacote().equals(this.idPacote)) {
                    item.setIdPacote(this.idPacote);
                    item.setTitulo(this.tituloPacote);
                    item.setDescricao(this.descricaoPacote);
                }
            }
        } else {
            PacoteServicoVO vo = new PacoteServicoVO();
            vo.setTitulo(this.tituloPacote);
            vo.setDescricao(this.descricaoPacote);
            vo.setPreco(this.preco);
            vo.setIdPacote(this.listaPacotes.size() + 1);
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
        PacoteServicoVO remover = null;
        for (PacoteServicoVO item : this.listaPacotes) {
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
    private List<PacoteServicoVO> popularlistaPacotes() {
        return GacMock.getListaPacotesServico();
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
    public List<PacoteServicoVO> getListaPacotes() {
        return listaPacotes;
    }

    /**
     * Nome: setListaPacotes Registra o valor do atributo 'listaPacotes'.
     * @param listaPacotes valor do atributo lista pacotes
     * @see
     */
    public void setListaPacotes(List<PacoteServicoVO> listaPacotes) {
        this.listaPacotes = listaPacotes;
    }

    /**
     * Nome: getPreco
     * Recupera o valor do atributo 'preco'.
     *
     * @return valor do atributo 'preco'
     * @see
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Nome: setPreco
     * Registra o valor do atributo 'preco'.
     *
     * @param preco valor do atributo preco
     * @see
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
