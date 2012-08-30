package br.com.sw2.test.usuario;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.UsuarioBusiness;
import br.com.sw2.gac.exception.LoginFailedException;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Testes de login e Usuario</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioTest {

    /** Atributo usuario. */
    private UsuarioVO usuario;

    /**
     * Nome: setup Setup.
     * @see
     */
    @Before
    public void setup() {
        usuario = new UsuarioVO();
        usuario.setLogin("admiwn");
        usuario.setSenha("admin");
    }

    /**
     * Nome: pesquisarUsuarioPorLogin Pesquisar usuario por login.
     * @see
     */
    @Test
    public void pesquisarUsuarioPorLogin() {

        UsuarioBusiness business = new UsuarioBusiness();
        UsuarioVO retorno = null;
        try {
            retorno = business.autenticarUsuario(usuario.getLogin(), usuario.getSenha());
            Assert.assertTrue(!retorno.getLogin().isEmpty());
        } catch (LoginFailedException exception) {
            exception.printStackTrace();
        }

    }
}