package br.com.sw2.gac.business;

import br.com.sw2.gac.dao.UsuarioDao;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.tools.BusinessExceptionMessages;
import br.com.sw2.gac.tools.Perfil;
import br.com.sw2.gac.util.StringUtil;
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

        if (null == senha || "".equals(senha.trim())) {
            throw new BusinessException(BusinessExceptionMessages.SENHA_NAO_INFORMADA);
        }

        String senhaCriptografada = StringUtil.encriptarTexto(senha);

        Usuario entity = new Usuario();
        entity.setLogin(login);
        entity.setSenha(senhaCriptografada);
        entity = this.dao.getUsuario(entity);

        UsuarioVO retorno = null;
        if (null == entity) {
            throw new BusinessException(BusinessExceptionMessages.FALHA_AUTENTICAÇAO);
        } else {
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

    /**
     * Nome: adicionarNovorUsuario Adicionar novor usuario.
     * @param usuario the usuario
     * @throws BusinessException the business exception
     * @see
     */
    public void adicionarNovorUsuario(UsuarioVO usuario) throws BusinessException {

        if (null == usuario || StringUtil.isVazio(usuario.getSenha(), true)
                || StringUtil.isVazio(usuario.getLogin(), true)) {
            throw new BusinessException(BusinessExceptionMessages.SALVAR_USUARIO_DADOS_INVALIDOS);
        }

        String senhaCriptografada = StringUtil.encriptarTexto(usuario.getSenha());

        Usuario entity = new Usuario();
        entity.setLogin(usuario.getLogin());
        entity.setSenha(senhaCriptografada);
        entity.setCdPerfil(usuario.getPerfil().getIdPerfil());
        entity.setNmUsuario(usuario.getLogin());

        Usuario existeLogin = this.dao.getUsuario(entity);

        if (null != existeLogin) {
            throw new BusinessException(BusinessExceptionMessages.USUARIO_DUPLICADO);
        } else {
            dao.gravar(entity);
        }

    }

    /**
     * Nome: salvarUsuario Adiciona ou altera um usuário na base de dados.
     * @param usuario the usuario
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarUsuario(UsuarioVO usuario) throws BusinessException {

        Usuario entity = new Usuario();
        entity.setLogin(usuario.getLogin());
        String senhaCriptografada = StringUtil.encriptarTexto(usuario.getSenha());

        entity.setSenha(senhaCriptografada);
        entity.setNmUsuario(usuario.getLogin());
        entity.setCdPerfil(usuario.getPerfil().getIdPerfil());

        dao.gravar(entity);

    }

}
