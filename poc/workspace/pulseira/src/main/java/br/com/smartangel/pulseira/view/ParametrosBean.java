package br.com.smartangel.pulseira.view;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean 
@RequestScoped
public class ParametrosBean extends BaseBean {

	private Integer minToleranciaPanico;
	private Integer minToleranciaDesmaioImovel;
	private Integer minToleranciaConvulsao;
	private Integer minToleranciaRetirada;
	
	private String intervaloAcionamentoInicial;
	private String intervaloAcionamentoFinal;
	
	private String qtdeAcionamentoInicial;
	private String qtdeAcionamentoFinal;

	private String valueCboBotaoPanico = "0";
	private String valueCboDesmaioImovel = "0";
	private String valueCboConvulsao = "0";
	private String valueCboRetirada = "0";
	
	public String iniciarPagina() {
		return "parametros";
	}
	
	public void salvar(ActionEvent event) {
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Parâmetros  Salvos"));		
	}
	
	public String fechar() {
		return "menuPrincipal";
    }  
	
	public Integer getMinToleranciaPanico() {
		return minToleranciaPanico;
	}
	
	public void setMinToleranciaPanico(Integer minToleranciaPanico) {
		this.minToleranciaPanico = minToleranciaPanico;
	}
	
	public Integer getMinToleranciaDesmaioImovel() {
		return minToleranciaDesmaioImovel;
	}
	
	public void setMinToleranciaDesmaioImovel(Integer minToleranciaDesmaioImovel) {
		this.minToleranciaDesmaioImovel = minToleranciaDesmaioImovel;
	}
	
	public Integer getMinToleranciaConvulsao() {
		return minToleranciaConvulsao;
	}
	
	public void setMinToleranciaConvulsao(Integer minToleranciaConvulsao) {
		this.minToleranciaConvulsao = minToleranciaConvulsao;
	}

	public Integer getMinToleranciaRetirada() {
		return minToleranciaRetirada;
	}

	public void setMinToleranciaRetirada(Integer minToleranciaRetirada) {
		this.minToleranciaRetirada = minToleranciaRetirada;
	}

	public String getIntervaloAcionamentoInicial() {
		return intervaloAcionamentoInicial;
	}

	public void setIntervaloAcionamentoInicial(String intervaloAcionamentoInicial) {
		this.intervaloAcionamentoInicial = intervaloAcionamentoInicial;
	}

	public String getIntervaloAcionamentoFinal() {
		return intervaloAcionamentoFinal;
	}

	public void setIntervaloAcionamentoFinal(String intervaloAcionamentoFinal) {
		this.intervaloAcionamentoFinal = intervaloAcionamentoFinal;
	}

	public String getQtdeAcionamentoInicial() {
		return qtdeAcionamentoInicial;
	}

	public void setQtdeAcionamentoInicial(String qtdeAcionamentoInicial) {
		this.qtdeAcionamentoInicial = qtdeAcionamentoInicial;
	}

	public String getQtdeAcionamentoFinal() {
		return qtdeAcionamentoFinal;
	}

	public String getValueCboBotaoPanico() {
		return valueCboBotaoPanico;
	}

	public void setValueCboBotaoPanico(String valueCboBotaoPanico) {
		this.valueCboBotaoPanico = valueCboBotaoPanico;
	}

	public String getValueCboDesmaioImovel() {
		return valueCboDesmaioImovel;
	}

	public void setValueCboDesmaioImovel(String valueCboDesmaioImovel) {
		this.valueCboDesmaioImovel = valueCboDesmaioImovel;
	}

	public String getValueCboConvulsao() {
		return valueCboConvulsao;
	}

	public void setValueCboConvulsao(String valueCboConvulsao) {
		this.valueCboConvulsao = valueCboConvulsao;
	}

	public String getValueCboRetirada() {
		return valueCboRetirada;
	}

	public void setValueCboRetirada(String valueCboRetirada) {
		this.valueCboRetirada = valueCboRetirada;
	}

	public void setQtdeAcionamentoFinal(String qtdeAcionamentoFinal) {
		this.qtdeAcionamentoFinal = qtdeAcionamentoFinal;
	}
	
}
						

