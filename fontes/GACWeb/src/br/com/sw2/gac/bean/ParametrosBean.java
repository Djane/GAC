package br.com.sw2.gac.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.business.ParametroBusiness;
import br.com.sw2.gac.vo.ParametroVO;

/**
 * <b>Descricao: Controller da tela de configuracao de parametros</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class ParametrosBean extends BaseBean {

    private static final long serialVersionUID = 4107789141198966008L;
    
    private ParametroVO parametro;

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
        
        setFacesMessage("message.cadastrodispositivo.save.sucess");

        // Recuperar o usuario logado na sessao e colocar no VO do dispositivo
        BaseBean base = new BaseBean();
        
        // Criar o novo parametro com os dados informados pelo usuario
        ParametroBusiness business = new ParametroBusiness();
        business.adicionarNovoParametro(this.parametro);
    }
    
    public ParametroVO getParametro() {
        return parametro;
    }

    public void setParametro(ParametroVO parametro) {
        this.parametro = parametro;
    }
}
