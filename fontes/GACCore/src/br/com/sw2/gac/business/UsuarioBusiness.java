package br.com.sw2.gac.business;

import br.com.sw2.gac.dao.UsuarioDao;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.LoginFailedException;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.tools.Perfil;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.PerfilVO;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descri��o: Classe de neg�cio respons�vel por a��es com os dados de usu�rios.</b> <br>
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

        if (null == senha || "".equals(senha.trim())) {
            throw new LoginFailedException("Senha n�o informada !");
        }

        String senhaCriptografada = StringUtil.encriptarTexto(senha);

        Usuario entity = new Usuario();
        entity.setLogin(login);
        entity.setSenha(senhaCriptografada);
        entity = this.dao.getUsuario(entity);

        UsuarioVO retorno = null;
        if (null == entity) {
            throw new LoginFailedException("Usu�rio n�o encontrado. Verifique o usu�rio ou senha  !");
        } else  {
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
        }
        return retorno;
    }
}
