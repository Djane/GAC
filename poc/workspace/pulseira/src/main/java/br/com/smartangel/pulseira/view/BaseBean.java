package br.com.smartangel.pulseira.view;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class BaseBean {

	private String urlImage = "primefaces-smartangel/images";

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}
	
	public void setTituloCabecalho(String str) {
		setRequestAttribute("screenTitle", str);
	}
	
	public void setRequestAttribute(String str, Object obj) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		request.setAttribute(str, obj);		
	}
	
}
