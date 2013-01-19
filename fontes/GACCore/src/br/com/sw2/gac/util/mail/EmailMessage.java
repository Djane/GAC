package br.com.sw2.gac.util.mail;

import java.util.List;
import java.util.Map;

/**
 * <b>Descrição: Classe responsável pelo envio de emails.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class EmailMessage {

    /** Constante TYPE_TEXT_PLAIN. */
    public static final String TYPE_TEXT_PLAIN = "text/plain";

    /** Constante TYPE_TEXT_HTML. */
    public static final String TYPE_TEXT_HTML = "text/html";

    /** Atributo destinatario to. */
    private String recipientTO;

    /** Atributo destinatario cc. */
    private String recipientCC;

    /** Atributo destinatario cco. */
    private String recipientCCO;

    /** Atributo list to. */
    private List<String> listTO;

    /** Atributo list cc. */
    private List<String> listCC;

    /** Atributo list cco. */
    private List<String> listCCO;

    /** Atributo map to. */
    private Map<String, String> mapTO;

    /** Atributo mapt cc. */
    private Map<String, String> mapCC;

    /** Atributo mapt cco. */
    private Map<String, String> mapCCO;

    /** Atributo email remetente. */
    private String senderAdress;

    /** Atributo nome remetente. */
    private String senderName;

    /** Atributo assunto. */
    private String subject;

    /** Atributo corpo mensagem. */
    private String body;

    /** Atributo tipo mensagem. */
    private String messageType;

    /** Atributo list anexos. */
    private List<String> listAttach;

    /**
     * Nome: getRecipientTO
     * Recupera o valor do atributo 'recipientTO'.
     *
     * @return valor do atributo 'recipientTO'
     * @see
     */
    public String getRecipientTO() {
        return recipientTO;
    }

    /**
     * Nome: setRecipientTO
     * Registra o valor do atributo 'recipientTO'.
     *
     * @param recipientTO valor do atributo recipient to
     * @see
     */
    public void setRecipientTO(String recipientTO) {
        this.recipientTO = recipientTO;
    }

    /**
     * Nome: getRecipientCC
     * Recupera o valor do atributo 'recipientCC'.
     *
     * @return valor do atributo 'recipientCC'
     * @see
     */
    public String getRecipientCC() {
        return recipientCC;
    }

    /**
     * Nome: setRecipientCC
     * Registra o valor do atributo 'recipientCC'.
     *
     * @param recipientCC valor do atributo recipient cc
     * @see
     */
    public void setRecipientCC(String recipientCC) {
        this.recipientCC = recipientCC;
    }

    /**
     * Nome: getRecipientCCO
     * Recupera o valor do atributo 'recipientCCO'.
     *
     * @return valor do atributo 'recipientCCO'
     * @see
     */
    public String getRecipientCCO() {
        return recipientCCO;
    }

    /**
     * Nome: setRecipientCCO
     * Registra o valor do atributo 'recipientCCO'.
     *
     * @param recipientCCO valor do atributo recipient cco
     * @see
     */
    public void setRecipientCCO(String recipientCCO) {
        this.recipientCCO = recipientCCO;
    }

    /**
     * Nome: getListTO
     * Recupera o valor do atributo 'listTO'.
     *
     * @return valor do atributo 'listTO'
     * @see
     */
    public List<String> getListTO() {
        return listTO;
    }

    /**
     * Nome: setListTO
     * Registra o valor do atributo 'listTO'.
     *
     * @param listTO valor do atributo list to
     * @see
     */
    public void setListTO(List<String> listTO) {
        this.listTO = listTO;
    }

    /**
     * Nome: getListCC
     * Recupera o valor do atributo 'listCC'.
     *
     * @return valor do atributo 'listCC'
     * @see
     */
    public List<String> getListCC() {
        return listCC;
    }

    /**
     * Nome: setListCC
     * Registra o valor do atributo 'listCC'.
     *
     * @param listCC valor do atributo list cc
     * @see
     */
    public void setListCC(List<String> listCC) {
        this.listCC = listCC;
    }

    /**
     * Nome: getListCCO
     * Recupera o valor do atributo 'listCCO'.
     *
     * @return valor do atributo 'listCCO'
     * @see
     */
    public List<String> getListCCO() {
        return listCCO;
    }

    /**
     * Nome: setListCCO
     * Registra o valor do atributo 'listCCO'.
     *
     * @param listCCO valor do atributo list cco
     * @see
     */
    public void setListCCO(List<String> listCCO) {
        this.listCCO = listCCO;
    }

    /**
     * Nome: getMapTO
     * Recupera o valor do atributo 'mapTO'.
     *
     * @return valor do atributo 'mapTO'
     * @see
     */
    public Map<String, String> getMapTO() {
        return mapTO;
    }

    /**
     * Nome: setMapTO
     * Sets the map to.
     *
     * @param mapTO the map to
     * @see
     */
    public void setMapTO(Map<String, String> mapTO) {
        this.mapTO = mapTO;
    }

    /**
     * Nome: getMaptCC
     * Recupera o valor do atributo 'maptCC'.
     *
     * @return valor do atributo 'maptCC'
     * @see
     */
    public Map<String, String> getMapCC() {
        return mapCC;
    }

    /**
     * Nome: setMaptCC
     * Sets the mapt cc.
     *
     * @param maptCC the mapt cc
     * @see
     */
    public void setMapCC(Map<String, String> maptCC) {
        this.mapCC = maptCC;
    }

    /**
     * Nome: getMaptCCO
     * Recupera o valor do atributo 'maptCCO'.
     *
     * @return valor do atributo 'maptCCO'
     * @see
     */
    public Map<String, String> getMapCCO() {
        return mapCCO;
    }

    /**
     * Nome: setMaptCCO
     * Sets the mapt cco.
     *
     * @param maptCCO the mapt cco
     * @see
     */
    public void setMapCCO(Map<String, String> maptCCO) {
        this.mapCCO = maptCCO;
    }

    /**
     * Nome: getSenderAdress
     * Recupera o valor do atributo 'senderAdress'.
     *
     * @return valor do atributo 'senderAdress'
     * @see
     */
    public String getSenderAdress() {
        return senderAdress;
    }

    /**
     * Nome: setSenderAdress
     * Registra o valor do atributo 'senderAdress'.
     *
     * @param senderAdress valor do atributo sender adress
     * @see
     */
    public void setSenderAdress(String senderAdress) {
        this.senderAdress = senderAdress;
    }

    /**
     * Nome: getSenderName
     * Recupera o valor do atributo 'senderName'.
     *
     * @return valor do atributo 'senderName'
     * @see
     */
    public String getSenderName() {
        return senderName;
    }

    /**
     * Nome: setSenderName
     * Registra o valor do atributo 'senderName'.
     *
     * @param senderName valor do atributo sender name
     * @see
     */
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    /**
     * Nome: getSubject
     * Recupera o valor do atributo 'subject'.
     *
     * @return valor do atributo 'subject'
     * @see
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Nome: setSubject
     * Registra o valor do atributo 'subject'.
     *
     * @param subject valor do atributo subject
     * @see
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Nome: getBody
     * Recupera o valor do atributo 'body'.
     *
     * @return valor do atributo 'body'
     * @see
     */
    public String getBody() {
        return body;
    }

    /**
     * Nome: setBody
     * Registra o valor do atributo 'body'.
     *
     * @param body valor do atributo body
     * @see
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Nome: getMessageType
     * Recupera o valor do atributo 'messageType'.
     *
     * @return valor do atributo 'messageType'
     * @see
     */
    public String getMessageType() {
        return messageType;
    }

    /**
     * Nome: setMessageType
     * Registra o valor do atributo 'messageType'.
     *
     * @param messageType valor do atributo message type
     * @see
     */
    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    /**
     * Nome: getListAttach
     * Recupera o valor do atributo 'listAttach'.
     *
     * @return valor do atributo 'listAttach'
     * @see
     */
    public List<String> getListAttach() {
        return listAttach;
    }

    /**
     * Nome: setListAttach
     * Registra o valor do atributo 'listAttach'.
     *
     * @param listAttach valor do atributo list attach
     * @see
     */
    public void setListAttach(List<String> listAttach) {
        this.listAttach = listAttach;
    }
}
