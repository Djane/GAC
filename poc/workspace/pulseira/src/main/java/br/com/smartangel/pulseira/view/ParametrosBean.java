package br.com.smartangel.pulseira.view;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean 
@ViewScoped
public class ParametrosBean extends BaseBean {

	private Integer codigoCliente;
	private Integer vlrtBemEstarCliente;
	private Integer vlrtToleranciaRotina;

	
	public String iniciarPagina() {
		setTituloCabecalho(getMessageFromBundle("label.parametros.header.title"));
		return "parametros";
	}
	
	public void salvar(ActionEvent event) {	    
	    setFacesMessage("message.parametros.save.sucess");
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

    public Integer getVlrtToleranciaRotina() {
        return vlrtToleranciaRotina;
    }

    public void setVlrtToleranciaRotina(Integer vlrtToleranciaRotina) {
        this.vlrtToleranciaRotina = vlrtToleranciaRotina;
    }

}
						

