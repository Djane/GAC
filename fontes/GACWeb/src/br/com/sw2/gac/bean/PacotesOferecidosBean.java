package br.com.sw2.gac.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.PacoteServicoBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.util.DateUtil;
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

    /** Atributo pacote servico business. */
    private PacoteServicoBusiness pacoteServicoBusiness = new PacoteServicoBusiness();

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 3403284748175381179L;

    /** Atributo id pacote. */
    private Integer idPacote = 0;

    /** Atributo titulo pacote. */
    private String tituloPacote = "";

    /** Atributo descricao pacote. */
    private String descricaoPacote = "";

    /** Atributo preco. */
    private BigDecimal preco;

    /** Atributo data inicio. */
    private Date dataInicio;

    /** Atributo data fim. */
    private Date dataFim;

    /** Atributo lista pacotes. */
    private List<PacoteServicoVO> listaPacotes;

    /** Atributo pacote vencido. */
    private boolean pacoteVencido = false;

    /** Atributo validade iniciada. */
    private boolean validadeIniciada = false;

    /**
     * Construtor Padrao Instancia um novo objeto PacotesOferecidosBean.
     */
    public PacotesOferecidosBean() {
        resetForm();
        this.listaPacotes = popularlistaPacotes();
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        resetForm();
    }

    /**
     * Nome: limparAtributos Limpar atributos.
     * @see
     */
    private void resetForm() {
        this.idPacote = 0;
        this.tituloPacote = "";
        this.descricaoPacote = "";
        this.preco = new BigDecimal("0.00");
        this.dataInicio = null;
        this.dataFim = null;

        this.pacoteVencido = false;
        this.validadeIniciada = false;
    }

    /**
     * Nome: editar Carrega os dados a serem editados na tela.
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
        this.dataInicio = edit.getDataInicioValidade();
        this.dataFim = edit.getDataFinalValidade();

        if (DateUtil.compareIgnoreTime(this.dataInicio, this.getDataAtual()) <= 0) {
            this.validadeIniciada = true;
        } else {
            this.validadeIniciada = false;
        }

    }

    /**
     * Nome: salvar Grava os dados da tela (Insere ou Atualiza).
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        if (validarDatas()) {
            if (this.idPacote > 0) {
                for (PacoteServicoVO item : this.listaPacotes) {
                    if (item.getIdPacote().equals(this.idPacote)) {
                        item.setIdPacote(this.idPacote);
                        item.setTitulo(this.tituloPacote);
                        item.setDescricao(this.descricaoPacote);
                        item.setPreco(this.preco);
                        item.setDataInicioValidade(this.dataInicio);
                        item.setDataFinalValidade(this.dataFim);
                        try {
                            this.pacoteServicoBusiness.atualizarPacoteServico(item);
                            this.listaPacotes = pacoteServicoBusiness
                                    .getListaPacoteServicosValidos();
                            setFacesMessage("message.pacotesoferecidos.save.sucess");
                            break;
                        } catch (BusinessException e) {
                            setFacesErrorMessage(e.getMessage(), false);
                        }
                    }
                }
            } else {
                PacoteServicoVO vo = new PacoteServicoVO();
                vo.setTitulo(this.tituloPacote);
                vo.setDescricao(this.descricaoPacote);
                vo.setPreco(this.preco);
                vo.setDataInicioValidade(this.dataInicio);
                vo.setDataFinalValidade(this.dataFim);
                this.listaPacotes.add(vo);

                try {
                    this.pacoteServicoBusiness.adicionarNovoPacote(vo);
                    this.listaPacotes = pacoteServicoBusiness.getListaPacoteServicosValidos();
                    setFacesMessage("message.pacotesoferecidos.save.sucess");
                } catch (BusinessException e) {
                    setFacesErrorMessage(e.getMessage(), false);
                }

            }
            resetForm();
        }
    }

    /**
     * Nome: excluir Excluir o pacote selecionado na tela.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        PacoteServicoVO remover = new PacoteServicoVO();
        remover.setIdPacote(this.idPacote);
        this.pacoteServicoBusiness.excluirPacoteServico(remover);
        this.listaPacotes = pacoteServicoBusiness.getListaPacoteServicosValidos();
        resetForm();
    }

    /**
     * Nome: popularlistaPacotes Popularlista pacotes.
     * @return list
     * @see
     */
    private List<PacoteServicoVO> popularlistaPacotes() {
        return this.pacoteServicoBusiness.getListaPacoteServicosValidos();
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
     * Nome: getPreco Recupera o valor do atributo 'preco'.
     * @return valor do atributo 'preco'
     * @see
     */
    public BigDecimal getPreco() {
        return preco;
    }

    /**
     * Nome: setPreco Registra o valor do atributo 'preco'.
     * @param preco valor do atributo preco
     * @see
     */
    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    /**
     * Nome: getDataInicio
     * Recupera o valor do atributo 'dataInicio'.
     *
     * @return valor do atributo 'dataInicio'
     * @see
     */
    public Date getDataInicio() {
        return this.dataInicio;
    }

    /**
     * Nome: setDataInicio
     * Registra o valor do atributo 'dataInicio'.
     *
     * @param dataInicio valor do atributo data inicio
     * @see
     */
    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    /**
     * Nome: getDataFim
     * Recupera o valor do atributo 'dataFim'.
     *
     * @return valor do atributo 'dataFim'
     * @see
     */
    public Date getDataFim() {
        return this.dataFim;
    }

    /**
     * Nome: setDataFim
     * Registra o valor do atributo 'dataFim'.
     *
     * @param dataFim valor do atributo data fim
     * @see
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * Nome: isPacoteVencido
     * Verifica se e pacote vencido.
     *
     * @return true, se for pacote vencido senão retorna false
     * @see
     */
    public boolean isPacoteVencido() {
        return pacoteVencido;
    }

    /**
     * Nome: setPacoteVencido
     * Registra o valor do atributo 'pacoteVencido'.
     *
     * @param pacoteVencido valor do atributo pacote vencido
     * @see
     */
    public void setPacoteVencido(boolean pacoteVencido) {
        this.pacoteVencido = pacoteVencido;
    }

    /**
     * Nome: isValidadeIniciada
     * Verifica se e validade iniciada.
     *
     * @return true, se for validade iniciada senão retorna false
     * @see
     */
    public boolean isValidadeIniciada() {
        return validadeIniciada;
    }

    /**
     * Nome: setValidadeIniciada
     * Registra o valor do atributo 'validadeIniciada'.
     *
     * @param validadeIniciada valor do atributo validade iniciada
     * @see
     */
    public void setValidadeIniciada(boolean validadeIniciada) {
        this.validadeIniciada = validadeIniciada;
    }

    /**
     * Nome: validarDatas Validar datas.
     * @return true, se sucesso, senão false
     * @see
     */
    private boolean validarDatas() {
        boolean retorno = true;
        if (null != this.dataInicio && null != this.dataFim) {
            if (DateUtil.compareIgnoreTime(this.dataInicio, this.dataFim) > 0) {
                setFacesErrorMessage("message.pacotesoferecidos.field.datainicioinferiordinal");
                retorno = false;
            }
        }
        return retorno;
    }

}
