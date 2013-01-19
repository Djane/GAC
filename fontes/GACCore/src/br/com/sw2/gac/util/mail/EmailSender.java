package br.com.sw2.gac.util.mail;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.sw2.gac.util.CollectionUtils;
import br.com.sw2.gac.util.StringUtil;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class EmailSender {

    /** The properties. */
    private Properties properties = null;

    /**
     * Construtor Padrao Instancia um novo objeto EntregadorEmail.
     */
    public EmailSender() {
        super();
    }

    /**
     * Instantiates a new entregador email.
     * @param properties the properties
     */
    public EmailSender(Properties properties) {
        this.properties = properties;
    }

    /**
     * Enviar email.
     * @param mail the mail
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException the messaging exception
     * @see
     */
    public void sendMessage(final EmailMessage mail) throws UnsupportedEncodingException,
        MessagingException {

        // classe anonima que realiza a autentica��o
        // do usuario no servidor de email.
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.user"),
                    properties.getProperty("mail.password"));
            }
        };

        // Cria a sessao passando as propriedades e a autentica��o
        Session session = Session.getDefaultInstance(this.properties, auth);
        // Gera um log no console referente ao processo de envio
        session.setDebug(true);

        // cria a mensagem setando o remetente e seus destinat�rios
        Message mensagem = new MimeMessage(session);
        // aqui seta o remetente
        mensagem.setFrom(new InternetAddress(mail.getSenderAdress(), mail.getSenderName()));

        recipientTO(mail, mensagem);

        recipientCC(mail, mensagem);

        // Adiciona um Assunto a Mensagem
        mensagem.setSubject(mail.getSubject());

        // Cria o objeto que recebe o texto do corpo do email
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(mail.getBody(), mail.getMessageType());
        Multipart mps = new MimeMultipart();

        // Trata os anexos da mensagem
        if (mail.getListAttach() != null && mail.getListAttach().size() > 0) {
            for (int index = 0; index < mail.getListAttach().size(); index++) {
                // Cria um novo objeto para cada arquivo, e anexa o arquivo
                MimeBodyPart attachFilePart = new MimeBodyPart();
                FileDataSource fds = new FileDataSource(mail.getListAttach().get(index));
                attachFilePart.setDataHandler(new DataHandler(fds));
                attachFilePart.setFileName(fds.getName());
                // adiciona os anexos da mensagem
                mps.addBodyPart(attachFilePart, index);

            }
        }

        // adiciona o corpo texto da mensagem
        mps.addBodyPart(textPart);
        // adiciona a mensagem o conte�do texto e anexo
        mensagem.setContent(mps);

        // Envia a Mensagem
        Transport.send(mensagem);
    }

    /**
     * Nome: recipientTO
     * Recipient to.
     *
     * @param mail the mail
     * @param mensagem the mensagem
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException the messaging exception
     * @see
     */
    private void recipientTO(final EmailMessage mail, Message mensagem)
        throws UnsupportedEncodingException, MessagingException {

        Map<String, String> mapTO = new HashMap<String, String>();
        mapTO.putAll(obterMapDestinatarios(mail.getRecipientTO()));
        mapTO.putAll(obterMapDestinatarios(mail.getListTO()));
        if (mail.getMapTO() != null && !mail.getMapTO().isEmpty()) {
            mapTO.putAll(mail.getMapTO());
        }
        addRecipient(Message.RecipientType.TO, mapTO, mensagem);
    }

    /**
     * Nome: recipientCC
     * Recipient cc.
     *
     * @param mail the mail
     * @param mensagem the mensagem
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException the messaging exception
     * @see
     */
    private void recipientCC(final EmailMessage mail, Message mensagem)
        throws UnsupportedEncodingException, MessagingException {
        //Trata os destinatarios CC
        Map<String, String> mapCC = new HashMap<String, String>();
        if (StringUtil.isNotEmpty(mail.getRecipientCC(), true)) {
            mapCC.putAll(obterMapDestinatarios(mail.getRecipientCC()));
        }
        if (CollectionUtils.isNotEmptyOrNull(mail.getListCC())) {
            mapCC.putAll(obterMapDestinatarios(mail.getListCC()));
        }
        if (mail.getMapCC() != null &&  !mail.getMapCC().isEmpty()) {
            mapCC.putAll(mail.getMapCC());
        }
        addRecipient(Message.RecipientType.CC, mapCC, mensagem);
    }

    /**
     * Obter map destinatarios.
     * @param destinatario the destinatario
     * @return the map
     * @see
     */
    private Map<String, String> obterMapDestinatarios(String destinatario) {
        return obterMapDestinatarios(destinatario, null);
    }

    /**
     * Obter map destinatarios.
     * @param listDestinatario the list destinatario
     * @return the map
     * @see
     */
    private Map<String, String> obterMapDestinatarios(List<String> listDestinatario) {
        return obterMapDestinatarios(null, listDestinatario);
    }

    /**
     * Obter map destinatarios.
     * @param destinatario the destinatario
     * @param listDestinatario the list destinatario
     * @return the map
     * @see
     */
    private Map<String, String> obterMapDestinatarios(String destinatario,
        List<String> listDestinatario) {
        Map<String, String> mapRetorno = new HashMap<String, String>();
        if (destinatario != null) {
            // verifica se h� mais de um email no campo
            if (destinatario.contains(";")) {
                StringTokenizer st = new StringTokenizer(destinatario, ";");
                while (st.hasMoreTokens()) {
                    String endereco = st.nextToken();
                    mapRetorno.put(endereco, endereco);
                }
            } else {
                mapRetorno.put(destinatario, destinatario);
            }
        }

        // Caso tenha sido informada uma lista de endere�os, converte ela na forma de MAP
        if (listDestinatario != null && !listDestinatario.isEmpty()) {
            for (String item : listDestinatario) {
                mapRetorno.put(item, item);
            }
        }

        return mapRetorno;
    }

    /**
     * Adds the recipient.
     * @param type the type
     * @param mapEmails the map emails
     * @param mensagem the mensagem
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws MessagingException the messaging exception
     * @see
     */
    private void addRecipient(RecipientType type, Map<String, String> mapEmails, Message mensagem)
        throws UnsupportedEncodingException, MessagingException {
        if (mapEmails != null) {
            for (Map.Entry<String, String> map : mapEmails.entrySet()) {
                mensagem.addRecipient(type, new InternetAddress(map.getKey(), map.getValue()));
            }
        }
    }

}
