package br.com.sw2.gac.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

/**
 * <b>Descrição: Controller da tela de configuração de parametros</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class ParametrosBean extends BaseBean {

    /** Atributo codigo cliente. */
    private Integer codigoCliente;

    /** Atributo vlrt bem estar cliente. */
    private Integer vlrtBemEstarCliente;

    /** Atributo vlrt tolerancia rotina. */
    private Integer vlrtToleranciaRotina;

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.parametros.view.title", true);
        return "parametros";
    }

    /**
     * Nome: salvar Salvar.
     * @param event the event
     * @see
     */
    public void salvar(ActionEvent event) {
        setFacesMessage("message.parametros.save.sucess");
    }

    /**
     * Nome: getCodigoCliente Recupera o valor do atributo 'codigoCliente'.
     * @return valor do atributo 'codigoCliente'
     * @see
     */
    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    /**
     * Nome: setCodigoCliente Registra o valor do atributo 'codigoCliente'.
     * @param codigoCliente valor do atributo codigo cliente
     * @see
     */
    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    /**
     * Nome: getVlrtBemEstarCliente Recupera o valor do atributo 'vlrtBemEstarCliente'.
     * @return valor do atributo 'vlrtBemEstarCliente'
     * @see
     */
    public Integer getVlrtBemEstarCliente() {
        return vlrtBemEstarCliente;
    }

    /**
     * Nome: setVlrtBemEstarCliente Registra o valor do atributo 'vlrtBemEstarCliente'.
     * @param vlrtBemEstarCliente valor do atributo vlrt bem estar cliente
     * @see
     */
    public void setVlrtBemEstarCliente(Integer vlrtBemEstarCliente) {
        this.vlrtBemEstarCliente = vlrtBemEstarCliente;
    }

    /**
     * Nome: getVlrtToleranciaRotina Recupera o valor do atributo 'vlrtToleranciaRotina'.
     * @return valor do atributo 'vlrtToleranciaRotina'
     * @see
     */
    public Integer getVlrtToleranciaRotina() {
        return vlrtToleranciaRotina;
    }

    /**
     * Nome: setVlrtToleranciaRotina Registra o valor do atributo 'vlrtToleranciaRotina'.
     * @param vlrtToleranciaRotina valor do atributo vlrt tolerancia rotina
     * @see
     */
    public void setVlrtToleranciaRotina(Integer vlrtToleranciaRotina) {
        this.vlrtToleranciaRotina = vlrtToleranciaRotina;
    }

}
