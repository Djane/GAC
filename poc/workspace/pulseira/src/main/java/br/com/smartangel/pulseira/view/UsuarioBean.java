package br.com.smartangel.pulseira.view;


import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;

import br.com.smartangel.pulseira.vo.UsuarioUploadVO;

@ManagedBean 
@ViewScoped
public class UsuarioBean extends BaseBean {

    private String loginSelecionado;
    
	private String username;
	
	private String password;
	
	private Integer perfil;
	
	private List<UsuarioUploadVO> listaUsuario;
		
	public UsuarioBean() {
	    this.listaUsuario  = this.obterUsuarios();	    
	}
	
   public void novo(ActionEvent actionEvent) { 
       this.password = "";
       this.username = "";
       this.perfil = 0;
    }  

   public void editar(ActionEvent actionEvent) { 
       
       String id = getRequestParameter("login");
       UsuarioUploadVO editar = null;
       for (UsuarioUploadVO item : this.listaUsuario) {           
           if (item.getLogin().equals(id)) {
               editar = item;
           }
       }       
       this.password = editar.getSenha();
       this.username = editar.getLogin();
       this.perfil = editar.getPerfil();       
    }  
   
   public void excluir (ActionEvent actionEvent) {
       UsuarioUploadVO remover = null;
       for (UsuarioUploadVO item : this.listaUsuario) {           
           if (item.getLogin().equals(this.loginSelecionado)) {
               remover = item;
           }
       }
       
       if (null != remover) {
           this.listaUsuario.remove(remover);
       }       
   }
	
    public void salvar(ActionEvent actionEvent) {
        UsuarioUploadVO item = new UsuarioUploadVO();
        item.setPerfil(this.perfil);
        if (this.perfil.intValue() == 1) {
            item.setNomePerfil("Administrador");
        } else if (this.perfil.intValue() == 2) {
            item.setNomePerfil("Atendente");
        } else {
            item.setNomePerfil("Gerente");
        }
        item.setLogin(this.username);
        item.setSenha(this.password);
        this.listaUsuario.add(item);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Sucesso, Login Salvo", "Login Salvo"));
    }

	public String iniciarPagina() {
		setTituloCabecalho("Cadastro de usuários");
		this.listaUsuario  = this.obterUsuarios();
		return "cadastrousuario";
	}
	
	private List<UsuarioUploadVO> obterUsuarios() {
	    
	    List<UsuarioUploadVO> lista = new ArrayList<UsuarioUploadVO>();
	    UsuarioUploadVO item = new UsuarioUploadVO();
	    item.setPerfil(1);
	    item.setNomePerfil("Administrador");
	    item.setLogin("Admin");
	    item.setSenha("admin");
	    lista.add(item);
	    
	    return lista;
	    
	}
	
		
	public String fechar() {
		return "menuPrincipal";
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

    public List<UsuarioUploadVO> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<UsuarioUploadVO> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getLoginSelecionado() {
        return loginSelecionado;
    }

    public void setLoginSelecionado(String loginSelecionado) {
        this.loginSelecionado = loginSelecionado;
    }	    
}
						
