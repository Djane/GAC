package br.com.sw2.gac.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
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

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = -7676850008026600324L;

    /** Atributo username. */
    private String username;

    /** Atributo password. */
    private String password;

    /** Atributo usuario business. */
    private UsuarioBusiness usuarioBusiness;

    /**
     * Construtor Padrao Instancia um novo objeto LoginBean.
     */
    public LoginBean() {
        config();
        try {
            this.getLogger().debug("Versão do gac: " + this.getGACProperty("gac.version"));
            this.usuarioBusiness = new UsuarioBusiness();
        } catch (Exception e) {
            setFacesErrorMessage("Não foi possível conectar ao banco de dados", false);
            setRequestAttribute("mensagemErro500", "Não é possível conectar ao banco de dados.");
            this.getLogger().error(e);
            try {
                FacesContext.getCurrentInstance().getExternalContext().dispatch("error/c500.xhtml");
            } catch (IOException e1) {
                this.getLogger().error(e);
            }
        }
    }

    /**
     * Nome: acessar Acessar.
     * @return string
     * @see
     */
    public String acessar() {

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
     * Nome: config Config.
     * @see
     */
    public void config() {

        String externalFolder = this.getExternalWorkFolder();

        // Verifica se a pasta de trabalho externa existe
        if ((new File(externalFolder)).exists()) {
            this.getLogger().debug(
                "Pasta de configurações definina no web.xml: " + externalFolder);
        } else {
            StringTokenizer token = new StringTokenizer(externalFolder, "/");
            String folderCreation = "";
            while (token.hasMoreTokens()) {
                String nextfolder = "/" + token.nextToken();
                folderCreation += nextfolder;
                if (!(new File(folderCreation)).exists()) {
                    makeDirectory(folderCreation);
                }
            }
        }

        // Verifica a existencia dos arquivos na pasta de trabalho;

        // Arquivo de propriedades padrão
        if (!(new File(externalFolder + "gac.properties")).exists()) {

            try {
                FileOutputStream fos = new FileOutputStream(externalFolder + "gac.properties");

                Properties properties = new Properties();
                properties.put("gac.version", "1.0");
                properties.store(fos, "Arquivo de configuração do GAC");

            } catch (IOException e) {
                this.getLogger().error(
                    "Não foi possível criar o arquivo: " + externalFolder + "gac.properties");
                this.getLogger().error(
                    "Será necessária configuração manual de " + externalFolder + "gac.properties");
            }

        }

        // Arquivo LOG4J
        // Arquivo de propriedades padrão
        if (!(new File(externalFolder + "log4j-gac.properties")).exists()) {
            try {
                FileOutputStream fos = new FileOutputStream(externalFolder + "log4j-gac.properties");

                Properties properties = new Properties();
                properties.put("log4j.rootLogger", "debug, stdout, FILE");
                properties.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
                properties.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
                properties.put("log4j.appender.stdout.layout.ConversionPattern",
                    "%5p [%t] (%F:%L) - %m%n");
                properties.put("log4j.appender.FILE", "org.apache.log4j.DailyRollingFileAppender");
                properties.put("log4j.appender.FILE.File", externalFolder + "logs/gac-Log.log");
                properties.put("log4j.appender.FILE.DatePattern", "'.' dd-MM-yyyy");
                properties.put("log4j.appender.FILE.layout", "org.apache.log4j.PatternLayout");
                properties.put("log4j.appender.FILE.layout.ConversionPattern", "%p %t %c - %m%n");
                properties.store(fos, "Arquivo de configuração do GAC");

                this.getLogger().debug("Criado arquivo: " + externalFolder + "logs/gac-Log.log");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Nome: makeDirectory Make directory.
     * @param pasta the pasta
     * @see
     */
    public void makeDirectory(String pasta) {
        File dir = new File(pasta);
        if (dir.mkdir()) {
            this.getLogger().debug("Pasta " + pasta + "criada");
        } else {
            this.getLogger().debug("Não foi possível criar a pasta " + pasta + "criada");
        }
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
