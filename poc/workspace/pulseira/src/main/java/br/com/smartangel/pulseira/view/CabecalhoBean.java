package br.com.smartangel.pulseira.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.smartangel.pulseira.util.DateUtil;

@ManagedBean 
@SessionScoped
public class CabecalhoBean extends BaseBean {

	private String dataAtualExtenso = DateUtil.getDataCompleta();

	public String getDataAtualExtenso() {
		return dataAtualExtenso;
	}

	public void setDataAtualExtenso(String dataAtualExtenso) {
		this.dataAtualExtenso = dataAtualExtenso;
	}
	
	
}
