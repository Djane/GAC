package br.com.sw2.gac.business;

import br.com.sw2.gac.dao.UsuarioDao;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
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
            throw new BusinessException(BusinessExceptionMessages.SENHA_NAO_INFORMADA);
        }

        String senhaCriptografada = StringUtil.encriptarTexto(senha);

        Usuario entity = new Usuario();
        entity.setLogin(login);
        entity.setSenha(senhaCriptografada);
        UsuarioVO retorno = null;
        try {
            entity = this.dao.getUsuario(entity);

            if (null == entity) {
                throw new BusinessException(BusinessExceptionMessages.FALHA_AUTENTICA�AO);
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
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
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

        Usuario entity = vo2Entity(usuario);
        try {
            Usuario existeLogin = this.dao.getUsuario(entity);
            if (null != existeLogin) {
                throw new BusinessException(BusinessExceptionMessages.USUARIO_DUPLICADO);
            } else {
                dao.gravar(entity);
            }
        } catch (DataBaseException exception) {
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }

    /**
     * Nome: salvarUsuario Adiciona ou altera um usu�rio na base de dados.
     * @param usuario the usuario
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarUsuario(UsuarioVO usuario) throws BusinessException {

        if (null == usuario || StringUtil.isVazio(usuario.getSenha(), true)
                || StringUtil.isVazio(usuario.getLogin(), true)) {
            throw new BusinessException(BusinessExceptionMessages.SALVAR_USUARIO_DADOS_INVALIDOS);
        }

        Usuario entity = vo2Entity(usuario);
        dao.gravar(entity);

    }

    /**
     * Nome: vo2Entity Converte os dados do VO usuario em uam entoty a ser enviada ao DAO.
     * @param usuario the usuario
     * @return usuario
     * @see
     */
    private Usuario vo2Entity(UsuarioVO usuario) {

        String senhaCriptografada = StringUtil.encriptarTexto(usuario.getSenha());
        Usuario entity = new Usuario();
        entity.setSenha(senhaCriptografada);
        entity.setLogin(usuario.getLogin());
        entity.setNmUsuario(usuario.getLogin());
        entity.setCdPerfil(usuario.getPerfil().getIdPerfil());
        return entity;

    }

    /**
     * Nome: apagarUsuario Apagar usuario.
     * @param login the login
     * @see
     */
    public void apagarUsuario(String login) {
        try {
            dao.apagar(login);
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                throw new BusinessException(BusinessExceptionMessages.DELETE_USUARIO_EM_USO);
            }
        }
    }

}
