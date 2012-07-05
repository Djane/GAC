package br.com.smartangel.pulseira.view;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean 
@RequestScoped
public class ParametrosBean extends BaseBean {

	private Integer qtdeDiasConfirmacaoContato;
	private Integer qtdeDiasVerificacaoBemEstar;

	
	public String iniciarPagina() {
		setTituloCabecalho("Parâmetros");
		return "parametros";
	}
	
	public void salvar(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Parâmetros  Salvos"));		
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
						

