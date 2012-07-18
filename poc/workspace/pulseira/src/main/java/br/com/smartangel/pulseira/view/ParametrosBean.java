package br.com.smartangel.pulseira.view;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

@ManagedBean 
@ViewScoped
public class ParametrosBean extends BaseBean {

	private Integer qtdeDiasConfirmacaoContato;
	private Integer qtdeDiasVerificacaoBemEstar;

	
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

	public Integer getQtdeDiasConfirmacaoContato() {
		return qtdeDiasConfirmacaoContato;
	}

	public void setQtdeDiasConfirmacaoContato(Integer qtdeDiasConfirmacaoContato) {
		this.qtdeDiasConfirmacaoContato = qtdeDiasConfirmacaoContato;
	}

	public Integer getQtdeDiasVerificacaoBemEstar() {
		return qtdeDiasVerificacaoBemEstar;
	}

	public void setQtdeDiasVerificacaoBemEstar(Integer qtdeDiasVerificacaoBemEstar) {
		this.qtdeDiasVerificacaoBemEstar = qtdeDiasVerificacaoBemEstar;
	}  
	
}
						

