package br.com.smartangel.pulseira.view;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean 
@RequestScoped
public class UsuarioBean extends BaseBean {

	private String username;
	
	private String password;
	
	private Integer perfil;
	
	private List<String> listaLogin = new ArrayList<String>();
	
	
	public UsuarioBean() {
		super();
		this.listaLogin.add("XXX");
	}
	
	public void salvar(ActionEvent actionEvent) {          
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso, Login Salvo", "Login Salvo"));
    }  

	public String iniciarPagina() {
		return "cadastrousuario";
	}
		
	public String fechar() {
		return "menuPrincipal";
    }  
	
	public List<String> getListaLogin() {
		return listaLogin;
	}

	public void setListaLogin(List<String> listaLogin) {
		this.listaLogin = listaLogin;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Integer getPerfil() {
		return perfil;
	}

	public void setPerfil(Integer perfil) {
		this.perfil = perfil;
	}	
}
						
