package br.com.sw2.gac.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.sw2.gac.util.DateUtil;

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
