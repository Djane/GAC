package br.com.sw2.gac.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

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

    /** Atributo login selecionado. */
    private String loginSelecionado;

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /** Atributo lista usuario. */
    private List<UsuarioVO> listaUsuario;

    /**
     * Construtor Padrao Instancia um novo objeto UsuarioBean.
     */
    public UsuarioBean() {
        this.usuario = new UsuarioVO();
        this.listaUsuario = this.obterUsuarios();
    }

    /**
     * Nome: novo Novo.
     * @param actionEvent the action event
     * @see
     */
    public void novo(ActionEvent actionEvent) {
        this.usuario = new UsuarioVO();
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
        this.usuario.setSenha(editar.getSenha());
        this.usuario.setLogin(editar.getLogin());
        this.usuario.setIdPerfil(editar.getIdPerfil());
    }

    /**
     * Nome: excluir Excluir.
     * @param actionEvent the action event
     * @see
     */
    public void excluir(ActionEvent actionEvent) {
        UsuarioVO remover = (UsuarioVO) findInListById(this.listaUsuario, "login",
                this.loginSelecionado);
        this.listaUsuario.remove(remover);
    }

    /**
     * Nome: salvar Salvar.
     * @param actionEvent the action event
     * @see
     */
    public void salvar(ActionEvent actionEvent) {
        UsuarioVO item = new UsuarioVO();
        item.setIdPerfil(this.usuario.getIdPerfil());

        item.setLogin(this.usuario.getLogin());
        item.setSenha(this.usuario.getSenha());
        this.listaUsuario.add(item);

        setFacesMessage("message.telausuario.save.sucess");
    }

    /**
     * Nome: iniciarPagina Iniciar pagina.
     * @return string
     * @see
     */
    public String iniciarPagina() {
        setTituloCabecalho("label.telausuario.view.title", true);
        this.listaUsuario = this.obterUsuarios();
        return "cadastrousuario";
    }

    /**
     * Nome: obterUsuarios Obter usuarios.
     * @return list
     * @see
     */
    private List<UsuarioVO> obterUsuarios() {
        return GacMock.getListaUsuarios();
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
     * Nome: getLoginSelecionado Recupera o valor do atributo 'loginSelecionado'.
     * @return valor do atributo 'loginSelecionado'
     * @see
     */
    public String getLoginSelecionado() {
        return loginSelecionado;
    }

    /**
     * Nome: setLoginSelecionado Registra o valor do atributo 'loginSelecionado'.
     * @param loginSelecionado valor do atributo login selecionado
     * @see
     */
    public void setLoginSelecionado(String loginSelecionado) {
        this.loginSelecionado = loginSelecionado;
    }
}
