package br.com.smartangel.pulseira.view;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean 
@ViewScoped
public class ParametrosBean extends BaseBean {

	private Integer codigoCliente;
	private Integer vlrtBemEstarCliente;

	
	public String iniciarPagina() {
		setTituloCabecalho(getMessageFromBundle("title.telaparametros"));
		return "parametros";
	}
	
	public void salvar(ActionEvent event) {	    
	    setFacesMessage("message.save.sucesso");
	}
	
	public String fechar() {
		return "menuPrincipal";
    }

    public Integer getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Integer getVlrtBemEstarCliente() {
        return vlrtBemEstarCliente;
    }

    public void setVlrtBemEstarCliente(Integer vlrtBemEstarCliente) {
        this.vlrtBemEstarCliente = vlrtBemEstarCliente;
    }

}
						

