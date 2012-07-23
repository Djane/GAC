package br.com.sw2.gac.view;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import br.com.sw2.gac.vo.UsuarioVO;

@ManagedBean 
@ViewScoped
public class UsuarioBean extends BaseBean {

    private String loginSelecionado;
    
	private UsuarioVO usuario;
	
	private List<UsuarioVO> listaUsuario;
		
	public UsuarioBean() {
	    this.usuario = new UsuarioVO();
	    this.listaUsuario  = this.obterUsuarios();	    
	}
	
   public void novo(ActionEvent actionEvent) { 
       this.usuario = new UsuarioVO();
    }  

   public void editar(ActionEvent actionEvent) {        
       String login = getRequestParameter("login");
       UsuarioVO editar =(UsuarioVO) findInListById(this.listaUsuario, "login", login);
       this.usuario = new UsuarioVO();
       this.usuario.setSenha(editar.getSenha());
       this.usuario.setLogin(editar.getLogin());
       this.usuario.setPerfil(editar.getPerfil());       
    }  
   
   public void excluir (ActionEvent actionEvent) {       
       UsuarioVO remover = (UsuarioVO) findInListById(this.listaUsuario, "login", this.loginSelecionado);
       this.listaUsuario.remove(remover);      
   }
	
    public void salvar(ActionEvent actionEvent) {
        UsuarioVO item = new UsuarioVO();
        item.setPerfil(this.usuario.getPerfil());
        if (this.usuario.getPerfil().intValue() == 1) {
            item.setNomePerfil("Administrador");
        } else if (this.usuario.getPerfil().intValue() == 2) {
            item.setNomePerfil("Atendente");
        } else {
            item.setNomePerfil("Gerente");
        }
        item.setLogin(this.usuario.getLogin());
        item.setSenha(this.usuario.getSenha());
        this.listaUsuario.add(item);
        
        setFacesMessage("message.telausuario.save.sucess");
    }

	public String iniciarPagina() {
		setTituloCabecalho("Cadastro de usuários");
		this.listaUsuario  = this.obterUsuarios();
		return "cadastrousuario";
	}
	
	private List<UsuarioVO> obterUsuarios() {
	    
	    List<UsuarioVO> lista = new ArrayList<UsuarioVO>();
	    UsuarioVO item = new UsuarioVO();
	    item.setPerfil(1);
	    item.setNomePerfil("Administrador");
	    item.setLogin("Admin");
	    item.setSenha("admin");
	    lista.add(item);
	    
	    return lista;
	    
	}


    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioVO> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<UsuarioVO> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public String getLoginSelecionado() {
        return loginSelecionado;
    }

    public void setLoginSelecionado(String loginSelecionado) {
        this.loginSelecionado = loginSelecionado;
    }	    
}
						
