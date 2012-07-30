package br.com.sw2.gac.business;

import br.com.sw2.gac.Exception.BusinessException;
import br.com.sw2.gac.Exception.LoginFailedException;
import br.com.sw2.gac.dao.UsuarioDao;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.tools.Perfil;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Classe de negócio responsável por ações com os dados de usuários.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioBusiness {

    /** Atributo dao. */
    private UsuarioDao dao = new UsuarioDao();

    /**
     * Nome: autenticarUsuario Autenticar usuario.
     * @param login the login
     * @param senha the senha
     * @return usuario vo
     * @throws BusinessException the business exception
     * @see
     */
    public UsuarioVO autenticarUsuario(String login, String senha) throws BusinessException {

        // validar a senha - Verificar criptografia

        String senhaCriptografada = senha;

        Usuario entity = new Usuario();
        entity.setLogin(login);
        entity.setSenha(senha);

        entity = this.dao.getUsuario(entity);

        // Senha confere
        UsuarioVO retorno = null;
        if (senhaCriptografada.equals(entity.getSenha())) {
            retorno = new UsuarioVO();
            retorno.setLogin(entity.getLogin());
            retorno.setSenha(entity.getSenha());

            // Definir o perfil
            for (Perfil item : Perfil.values()) {
                if (item.getValue() == entity.getCdPerfil()) {
                    PerfilVO perfil = new PerfilVO(item.getValue(), item.getLabel());
                    retorno.setPerfil(perfil);
                }
            }
        } else {
            throw new LoginFailedException();
        }
        return retorno;
    }
}
