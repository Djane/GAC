package br.com.sw2.gac.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean 
@RequestScoped
public class LoginBean {

	private String username;
	
	private String password;
	
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

	public boolean login() {
		
		FacesMessage msg = null;
		boolean loggedIn = false;
		
		if(username != null && username.equals("admin") && password != null  && password.equals("admin")) {
			loggedIn = true;			
		} else {
			loggedIn = false;
			msg = new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Usuário ou senha inválidos", "Usuário ou senha inválidos");
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}		
		return loggedIn;
	}
	
	public String acessarMenu() {
		
		String toViewId = "login";
		if (this.login()) {
			toViewId = "menuPrincipal";
		}
		return toViewId;
		
	}	
	
}
						
