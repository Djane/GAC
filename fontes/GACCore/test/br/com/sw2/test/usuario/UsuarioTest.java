package br.com.sw2.test.usuario;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import br.com.sw2.gac.business.UsuarioBusiness;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.vo.PerfilVO;
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
        usuario.setLogin("admin");
        usuario.setSenha("admin");

        PerfilVO perfil = new PerfilVO();
        perfil.setIdPerfil(1);

        usuario.setPerfil(perfil);
    }

    /**
     * Nome: autenticarUsuario Autenticar usuario.
     * @see
     */
    @Test
    public void autenticarUsuario() {

        UsuarioBusiness business = new UsuarioBusiness();
        UsuarioVO retorno = null;
        try {
            retorno = business.autenticarUsuario(usuario.getLogin(), usuario.getSenha());
            Assert.assertTrue(!retorno.getLogin().isEmpty());
        } catch (BusinessException exception) {
            exception.printStackTrace();
        }

    }

    /**
     * Nome: updateUsuario Update usuario.
     * @see
     */
    @Test
    public void updateUsuario() {
        usuario.getPerfil().setIdPerfil(1);
        UsuarioBusiness business = new UsuarioBusiness();
        business.atualizarUsuario(usuario);
    }

    /**
     * Nome: incluirUsuario
     * Incluir usuario.
     *
     * @see
     */
    @Test
    public void incluirUsuario() {
        UsuarioVO novo = new UsuarioVO();
        novo.setLogin("admin2");
        novo.setSenha("admin");
        novo.setNomeUsuario("admin");
        PerfilVO perfil = new PerfilVO();
        perfil.setIdPerfil(1);
        novo.setPerfil(perfil);
        UsuarioBusiness business = new UsuarioBusiness();
        try {
            business.adicionarNovorUsuario(novo);
        } catch (BusinessException exception) {
            exception.printStackTrace();
        }
    }

}