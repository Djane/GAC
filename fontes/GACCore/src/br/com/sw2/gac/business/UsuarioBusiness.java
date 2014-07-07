package br.com.sw2.gac.business;

import java.util.ArrayList;
import java.util.List;

import br.com.sw2.gac.dao.UsuarioDAO;
import br.com.sw2.gac.exception.BusinessException;
import br.com.sw2.gac.exception.BusinessExceptionMessages;
import br.com.sw2.gac.exception.DataBaseException;
import br.com.sw2.gac.modelo.Usuario;
import br.com.sw2.gac.util.ParseUtils;
import br.com.sw2.gac.util.StringUtil;
import br.com.sw2.gac.vo.UsuarioVO;

/**
 * <b>Descrição: Classe de negócio responsável por ações com os dados de usuários.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class UsuarioBusiness extends BaseBusiness {

    /** Atributo dao. */
    private UsuarioDAO daoUsuario = new UsuarioDAO();

    /**
     * Nome: usuarioExiste Verifica se um usuário existe na base ou não.
     * @param userName the user name
     * @return true, se sucesso, senão false
     * @see
     */
    public boolean usuarioExiste(String userName) {

        boolean retorno;
        Usuario usuario = (Usuario) this.daoUsuario.getEntityManager().find(Usuario.class, userName);

        if (null == usuario) {
            retorno = false;
        } else {
            retorno = true;
        }
        return retorno;

    }

    /**
     * Nome: autenticarUsuario Autenticar usuario.
     * @param login the login
     * @param senha the senha
     * @return usuario vo
     * @throws BusinessException the business exception
     * @see
     */
    public UsuarioVO autenticarUsuario(String login, String senha) throws BusinessException {
        
        if (StringUtil.isEmpty(senha)) {
            this.logger.info(getClass(), BusinessExceptionMessages.SENHA_NAO_INFORMADA.getLabel());
            throw new BusinessException(BusinessExceptionMessages.SENHA_NAO_INFORMADA);
        }

        String senhaCriptografada = StringUtil.cryptoN1(senha);
        this.logger.debug(getClass(), "Senha criptografada");

        Usuario entity = new Usuario();
        entity.setLogin(login);
        entity.setSenha(senhaCriptografada);
        UsuarioVO retorno = null;
        try {
            entity = this.daoUsuario.getUsuario(entity);
            if (null == entity) {
                this.logger.info(getClass(), BusinessExceptionMessages.FALHA_AUTENTICACAO.getLabel());
                throw new BusinessException(BusinessExceptionMessages.FALHA_AUTENTICACAO);
            } else {
                retorno = ParseUtils.parse(entity);
                this.logger.registrarAcao("O usuário " + login + " se autenticou com sucesso no gac");               
            }
        } catch (Exception exception) {
            this.logger.registrarAcao("O usuário " + login + " não conseguiu se autenticar no GAC: " + exception.getMessage());            
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }

        return retorno;
    }

    /**
     * Nome: obterListaDeUsuarios Obter lista de usuários.
     * @return list
     * @throws BusinessException the business exception
     * @see
     */
    public List<UsuarioVO> obterListaDeUsuarios() throws BusinessException {
        List<UsuarioVO> listaVO = new ArrayList<UsuarioVO>();
        try {
            List<Usuario> listaEntity = this.daoUsuario.getListaUsuarios();

            if (!listaEntity.isEmpty()) {

                for (Usuario entity : listaEntity) {
                    UsuarioVO usuario = ParseUtils.parse(entity);
                    listaVO.add(usuario);
                }

            }

        } catch (Exception exception) {
            this.logger.error(getClass(), exception.getMessage());
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }

        return listaVO;
    }

    /**
     * Nome: adicionarNovorUsuario Adicionar novor usuario.
     * @param usuario the usuario
     * @throws BusinessException the business exception
     * @see
     */
    public void adicionarNovorUsuario(UsuarioVO usuario) throws BusinessException {

        if (null == usuario || StringUtil.isEmpty(usuario.getSenha(), true)
                || StringUtil.isEmpty(usuario.getLogin(), true)) {
            throw new BusinessException(BusinessExceptionMessages.SALVAR_USUARIO_DADOS_INVALIDOS);
        }

        Usuario entity = ParseUtils.parse(usuario);
        try {
            Usuario existeLogin = this.daoUsuario.getUsuario(entity);
            if (null != existeLogin) {
                this.logger.info(getClass(), BusinessExceptionMessages.USUARIO_DUPLICADO.getLabel());
                throw new BusinessException(BusinessExceptionMessages.USUARIO_DUPLICADO);
            } else {
                this.daoUsuario.gravar(entity);
            }
        } catch (DataBaseException exception) {
            this.logger.error(getClass(), exception.getMessage());
            throw new BusinessException(BusinessExceptionMessages.SISTEMA_INDISPONIVEL);
        }
    }

    /**
     * Nome: salvarUsuario Adiciona ou altera um usuário na base de dados.
     * @param usuario the usuario
     * @throws BusinessException the business exception
     * @see
     */
    public void atualizarUsuario(UsuarioVO usuario) throws BusinessException {
        final int limiteSenha = 10;
        if (null == usuario || StringUtil.isEmpty(usuario.getSenha(), true)
                || StringUtil.isEmpty(usuario.getLogin(), true)) {
            this.logger.info(getClass(), BusinessExceptionMessages.SALVAR_USUARIO_DADOS_INVALIDOS.getLabel());
            throw new BusinessException(BusinessExceptionMessages.SALVAR_USUARIO_DADOS_INVALIDOS);
        }
        Usuario entity = ParseUtils.parse(usuario);
        if (usuario.getSenha().length() > limiteSenha) {
            entity.setSenha(usuario.getSenha());
        }
        this.daoUsuario.gravar(entity);

    }

    /**
     * Nome: apagarUsuario Apagar usuario.
     * @param login the login
     * @see
     */
    public void apagarUsuario(String login) {
        try {
            this.daoUsuario.apagar(login);
        } catch (DataBaseException exception) {
            if (exception.getExceptionCode() == DataBaseException.DELETE_VIOLACAO_CONSTRAINT) {
                this.logger.info(getClass(), BusinessExceptionMessages.DELETE_USUARIO_EM_USO.getLabel());
                throw new BusinessException(BusinessExceptionMessages.DELETE_USUARIO_EM_USO);
            }
        }
    }

    /**
     * Nome: getUsuario Recupera os dados de um usuário através do seu login.
     * @param userID the user id
     * @return valor do atributo 'usuario'
     * @see
     */
    public UsuarioVO getUsuario(String userID) {
        Usuario entity = this.daoUsuario.getEntityById(userID);
        UsuarioVO vo;
        if (null == entity) {
            vo = null;
        } else {
            vo = ParseUtils.parse(entity);
        }
        return vo;
    }

}
