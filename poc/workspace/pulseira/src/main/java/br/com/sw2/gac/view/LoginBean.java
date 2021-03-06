package br.com.sw2.gac.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * <b>Descri��o: Controller da tela de login.</b> <br>
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

    /**
     * Nome: login Login.
     * @return true, se sucesso, sen�o false
     * @see
     */
    public boolean login() {
        boolean loggedIn = false;

        if (username != null && username.equals("admin") && password != null
                && password.equals("admin")) {
            loggedIn = true;
        } else {
            loggedIn = false;
            setFacesMessage("message.login.failed");
        }
        return loggedIn;
    }

    /**
     * Nome: acessarMenu Acessar menu.
     * @return string
     * @see
     */
    public String acessarMenu() {

        String toViewId = "login";
        if (this.login()) {
            toViewId = "menuPrincipal";
        }
        return toViewId;

    }

}
