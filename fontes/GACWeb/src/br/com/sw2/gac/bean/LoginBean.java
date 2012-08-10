package br.com.sw2.gac.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.HttpSession;

import br.com.sw2.gac.business.UsuarioBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Controller da tela de login.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@ManagedBean
@RequestScoped
public class LoginBean extends BaseBean {

    /** Atributo username. */
    private String username;

    /** Atributo password. */
    private String password;

    /** Atributo usuario business. */
    private UsuarioBusiness usuarioBusiness;

    /**
     * Construtor Padrão Instancia um novo objeto LoginBean.
     */
    public LoginBean() {
        this.usuarioBusiness = new UsuarioBusiness();

    }

    /**
     * Nome: acessarMenu Acessar menu. Efetua a autenticação do usuário e senha.
     * @return string
     * @see
     */
    public String acessarMenu() {

        String toViewId = "login";
        try {
            UsuarioVO usuario = usuarioBusiness.autenticarUsuario(this.username, this.password);
            HttpSession session = (HttpSession) this.getFacesContext().getExternalContext()
                    .getSession(false);
            session.setAttribute("usuariovo", usuario);
            toViewId = "menuPrincipal";
        } catch (BusinessException e) {
            if (e.getExceptionCode() == BusinessExceptionMessages.FALHA_AUTENTICACAO.getValue()) {
                setFacesErrorMessage("message.login.failed");
            } else {
                setFacesErrorMessage("message.generic.system.unavailable");
            }
        }

        return toViewId;

    }

    /**
     * Nome: getUsername Recupera o valor do atributo 'username'.
     * @return valor do atributo 'username'
     * @see
     */
    public String getUsername() {
        return username;
    }

    /**
     * Nome: setUsername Registra o valor do atributo 'username'.
     * @param username valor do atributo username
     * @see
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Nome: getPassword Recupera o valor do atributo 'password'.
     * @return valor do atributo 'password'
     * @see
     */
    public String getPassword() {
        return password;
    }

    /**
     * Nome: setPassword Registra o valor do atributo 'password'.
     * @param password valor do atributo password
     * @see
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
