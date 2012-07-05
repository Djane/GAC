package br.com.smartangel.pulseira.view;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import br.com.smartangel.pulseira.vo.SmsPadraoVO;

@ManagedBean 
@RequestScoped
public class SmsPadraoBean extends BaseBean {

	private String tituloMensagem;
	private String descricaoMensagem;
	private List<SmsPadraoVO> listaMensagens;
	
	public String iniciarPagina() {
		
		setTituloCabecalho("Cadastro SMS Padrão");
		
		listaMensagens  = popularListaMensagens();
		return "smspadrao";
	}
	
	public void salvar(ActionEvent actionEvent) {          
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Mensagem Salva"));
        listaMensagens  = popularListaMensagens();
    }  
	
	public String fechar() {
		return "menuPrincipal";
    }  
	
	private List<SmsPadraoVO> popularListaMensagens() {
		listaMensagens = new ArrayList<SmsPadraoVO>();
		SmsPadraoVO sms = new SmsPadraoVO();
		for (int i=0; i<10; i++) {
			sms = new SmsPadraoVO();
			sms.setTitulo("Titulo Mensagem " + i);
			sms.setDescricao(" desccricao mensagem " + i);
			listaMensagens.add(sms);
		}	
		return listaMensagens;
	}
	
	public String getTituloMensagem() {
		return tituloMensagem;
	}

	public void setTituloMensagem(String tituloMensagem) {
		this.tituloMensagem = tituloMensagem;
	}

	public String getDescricaoMensagem() {
		return descricaoMensagem;
	}

	public void setDescricaoMensagem(String descricaoMensagem) {
		this.descricaoMensagem = descricaoMensagem;
	}

	public List<SmsPadraoVO> getListaMensagens() {
		return listaMensagens;
	}

	public void setListaMensagens(List<SmsPadraoVO> listaMensagens) {
		this.listaMensagens = listaMensagens;
	}
	
}
						

