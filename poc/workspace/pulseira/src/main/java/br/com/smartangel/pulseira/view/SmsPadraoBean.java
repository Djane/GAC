package br.com.smartangel.pulseira.view;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.smartangel.pulseira.vo.SmsPadraoVO;
import br.com.smartangel.pulseira.vo.UsuarioVO;

@ManagedBean 
@ViewScoped
public class SmsPadraoBean extends BaseBean {

    private Integer idSms;
	private String tituloMensagem;
	private String descricaoMensagem;
	private List<SmsPadraoVO> listaMensagens;
			
	public SmsPadraoBean() {
	    this.listaMensagens  = popularListaMensagens();
    }

    public String iniciarPagina() {		
		setTituloCabecalho("Cadastro SMS Padrão");		
		this.listaMensagens  = popularListaMensagens();
		return "smspadrao";
	}
    
    public void novo(ActionEvent actionEvent) { 
        this.tituloMensagem = "";
        this.descricaoMensagem = "";        
     }  
	
	public void salvar(ActionEvent actionEvent) {          
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso", "Mensagem Salva"));
        listaMensagens  = popularListaMensagens();
    }  
	
	public void excluir (ActionEvent actionEvent) {
	        SmsPadraoVO remover = null;
	       for (SmsPadraoVO item : this.listaMensagens) {           
	           if (item.getIdSms().equals(this.idSms)) {
	               remover = item;
	           }
	       }	       
	       if (null != remover) {
	           this.listaMensagens.remove(remover);
	       }
    }

	public String fechar() {
		return "menuPrincipal";
    }  
	
	private List<SmsPadraoVO> popularListaMensagens() {
		listaMensagens = new ArrayList<SmsPadraoVO>();
		SmsPadraoVO sms = new SmsPadraoVO();
		for (int i=0; i<10; i++) {
			sms = new SmsPadraoVO();
			sms.setIdSms(i);
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

    public Integer getIdSms() {
        return idSms;
    }

    public void setIdSms(Integer idSms) {
        this.idSms = idSms;
    }
	
}
						

