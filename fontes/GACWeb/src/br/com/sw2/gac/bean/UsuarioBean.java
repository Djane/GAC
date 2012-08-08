package br.com.sw2.gac.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import br.com.sw2.gac.business.UsuarioBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.tools.Perfil;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Controller da tela de Usuários</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@ViewScoped
public class UsuarioBean extends BaseBean {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -754439588340393763L;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /** Atributo lista usuario. */
    private List<UsuarioVO> listaUsuario;

    /** Atributo lista perfil. */
    private List<SelectItem> listaPerfil;
    
    /** Atributo usuario business. */
    private UsuarioBusiness usuarioBusiness;
    
    /** Atributo crud. */
    private String crud;

    /**
     * Construtor Padrao Instancia um novo objeto UsuarioBean.
     */
    public UsuarioBean() {
        this.usuarioBusiness = new UsuarioBusiness();
        limparAtributos();
        this.listaUsuario = this.obterUsuarios();
        this.listaPerfil = getSelectIems(Perfil.class);
        setTituloCabecalho("label.telausuario.view.title", true);
    }
    
    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        limparAtributos();
        setTituloCabecalho("label.telausuario.view.title", true);
        this.listaUsuario = this.obterUsuarios();
        return "cadastrousuario";
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        limparAtributos();
    }

    /**
     * Nome: editar Editar.
     * @param actionEvent the action event
     * @see
     */
    public void editar(ActionEvent actionEvent) {
        String login = getRequestParameter("login");
        UsuarioVO editar = (UsuarioVO) findInListById(this.listaUsuario, "login", login);
        this.usuario = new UsuarioVO();
        this.usuario.setSenha(editar.getSenha().substring(0, 9));
        this.usuario.setLogin(editar.getLogin());      
        this.usuario.setPerfil(editar.getPerfil());
        this.crud = "U";
    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        UsuarioVO remover = (UsuarioVO) findInListById(this.listaUsuario, "login",
                this.usuario.getLogin());
        try {    
            this.usuarioBusiness.apagarUsuario(this.usuario.getLogin());
        } catch (BusinessException exception) {
            if (exception.getExceptionCode() == BusinessExceptionMessages.DELETE_USUARIO_EM_USO.getValue().intValue()) {
                setFacesMessage("message.telausuario.delete.login.failed");
            } else {
                setFacesMessage("message.generic.system.unavailable");
            }
            
        }
        
        this.listaUsuario.remove(remover);
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {

        if (this.crud.equals("C")) {
            
            UsuarioVO item = new UsuarioVO();
            item.setLogin(this.usuario.getLogin());
            item.setSenha(this.usuario.getSenha());
            PerfilVO perfil = new PerfilVO();
            perfil.setIdPerfil(this.usuario.getPerfil().getIdPerfil());
            item.setPerfil(perfil);
            try {
                this.usuarioBusiness.adicionarNovorUsuario(item);
                //Atualiza lista
                this.listaUsuario = this.usuarioBusiness.obterListaDeUsuarios();
                setFacesMessage("message.telausuario.save.sucess");
                
                limparAtributos();
                
            } catch (BusinessException e) {
                if (e.getExceptionCode() == BusinessExceptionMessages.USUARIO_DUPLICADO.getValue().intValue()) {
                    setFacesMessage("message.telausuario.save.duplicate");
                } else {
                    setFacesMessage("message.generic.system.unavailable");
                }
            }                        
            
        } else  if (this.crud.equals("U")) {
            
            UsuarioVO editar = (UsuarioVO) findInListById(this.listaUsuario, "login",
                    this.usuario.getLogin());            
            
            PerfilVO perfil = new PerfilVO();
            perfil.setIdPerfil(this.usuario.getPerfil().getIdPerfil());
            editar.setPerfil(perfil);
            try {
                String senhaDigitada = this.usuario.getSenha(); 
                String senhaOriginal = editar.getSenha();
                if (senhaDigitada.length() != 9 &&  !senhaOriginal.substring(0,9).equals(senhaDigitada)) {                    
                    editar.setSenha(this.usuario.getSenha());
                }
                this.usuarioBusiness.atualizarUsuario(editar);
                setFacesMessage("message.telausuario.save.sucess");
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            
        }
        
    }

    /**
     * Nome: limparAtributos
     * Limpar atributos.
     *
     * @see
     */
    private void limparAtributos() {
        this.usuario = new UsuarioVO();
        this.usuario.setPerfil(new PerfilVO());
        this.crud = "C";
    }    

    /**
     * Nome: obterUsuarios Obter usuarios.
     * @return list
     * @see
     */
    private List<UsuarioVO> obterUsuarios() {
        List<UsuarioVO> lista;
        try {
            lista = this.usuarioBusiness.obterListaDeUsuarios();
        } catch (BusinessException e) {
            lista = new ArrayList<UsuarioVO>();
        }
        return lista;
    }

    /**
     * Nome: getUsuario Recupera o valor do atributo 'usuario'.
     * @return valor do atributo 'usuario'
     * @see
     */
    public UsuarioVO getUsuario() {
        return usuario;
    }

    /**
     * Nome: setUsuario Registra o valor do atributo 'usuario'.
     * @param usuario valor do atributo usuario
     * @see
     */
    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    /**
     * Nome: getListaUsuario Recupera o valor do atributo 'listaUsuario'.
     * @return valor do atributo 'listaUsuario'
     * @see
     */
    public List<UsuarioVO> getListaUsuario() {
        return listaUsuario;
    }

    /**
     * Nome: setListaUsuario Registra o valor do atributo 'listaUsuario'.
     * @param listaUsuario valor do atributo lista usuario
     * @see
     */
    public void setListaUsuario(List<UsuarioVO> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    /**
     * Nome: getListaPerfil Recupera o valor do atributo 'listaPerfil'.
     * @return valor do atributo 'listaPerfil'
     * @see
     */
    public List<SelectItem> getListaPerfil() {
        return listaPerfil;
    }

    /**
     * Nome: setListaPerfil Registra o valor do atributo 'listaPerfil'.
     * @param listaPerfil valor do atributo lista perfil
     * @see
     */
    public void setListaPerfil(List<SelectItem> listaPerfil) {
        this.listaPerfil = listaPerfil;
    }

    /**
     * Nome: getCrud
     * Recupera o valor do atributo 'crud'.
     *
     * @return valor do atributo 'crud'
     * @see
     */
    public String getCrud() {
        return crud;
    }

    /**
     * Nome: setCrud
     * Registra o valor do atributo 'crud'.
     *
     * @param crud valor do atributo crud
     * @see
     */
    public void setCrud(String crud) {
        this.crud = crud;
    }

}
