package br.com.sw2.gac.socket.bean;

/**
 * <b>Descrição: Classe que representa os dados d eum evnto de resposta do DGPhone</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2013 SmartAngel.
 */
public class Event {

    /** Atributo event. */
    private String event;

    /** Atributo response. */
    private String response;

    /** Atributo command. */
    private String command;

    /** Atributo message. */
    private String message;

    /** Atributo status. */
    private String status;

    /** Atributo number. */
    private String number;

    /** Atributo user. */
    private Integer user;

    /** Atributo line. */
    private Integer line;

    /** Atributo agent. */
    private String agent;

    /** Atributo uniqueid. */
    private String uniqueid;

    /** Atributo timestamp. */
    private String timestamp;

    /** Atributo hold. */
    private Integer hold;

    /** Atributo queue. */
    private Integer queue;

    /** Atributo digit. */
    private String digit;

    /** Atributo wait. */
    private String wait;

    /** Atributo paused. */
    private Integer paused;

    /** Atributo reason. */
    private String reason;
    /**
     * Nome: getEvent
     * Recupera o valor do atributo 'event'.
     *
     * @return valor do atributo 'event'
     * @see
     */
    public String getEvent() {
        return event;
    }

    /**
     * Nome: setEvent
     * Registra o valor do atributo 'event'.
     *
     * @param event valor do atributo event
     * @see
     */
    public void setEvent(String event) {
        this.event = event;
    }

    /**
     * Nome: getResponse
     * Recupera o valor do atributo 'response'.
     *
     * @return valor do atributo 'response'
     * @see
     */
    public String getResponse() {
        return response;
    }

    /**
     * Nome: setResponse
     * Registra o valor do atributo 'response'.
     *
     * @param response valor do atributo response
     * @see
     */
    public void setResponse(String response) {
        this.response = response;
    }

    /**
     * Nome: getCommand
     * Recupera o valor do atributo 'command'.
     *
     * @return valor do atributo 'command'
     * @see
     */
    public String getCommand() {
        return command;
    }

    /**
     * Nome: setCommand
     * Registra o valor do atributo 'command'.
     *
     * @param command valor do atributo command
     * @see
     */
    public void setCommand(String command) {
        this.command = command;
    }

    /**
     * Nome: getMessage
     * Recupera o valor do atributo 'message'.
     *
     * @return valor do atributo 'message'
     * @see
     */
    public String getMessage() {
        return message;
    }

    /**
     * Nome: setMessage
     * Registra o valor do atributo 'message'.
     *
     * @param message valor do atributo message
     * @see
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Nome: getStatus
     * Recupera o valor do atributo 'status'.
     *
     * @return valor do atributo 'status'
     * @see
     */
    public String getStatus() {
        return status;
    }

    /**
     * Nome: setStatus
     * Registra o valor do atributo 'status'.
     *
     * @param status valor do atributo status
     * @see
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Nome: getNumber
     * Recupera o valor do atributo 'number'.
     *
     * @return valor do atributo 'number'
     * @see
     */
    public String getNumber() {
        return number;
    }

    /**
     * Nome: setNumber
     * Registra o valor do atributo 'number'.
     *
     * @param number valor do atributo number
     * @see
     */
    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Nome: getUser
     * Recupera o valor do atributo 'user'.
     *
     * @return valor do atributo 'user'
     * @see
     */
    public Integer getUser() {
        return user;
    }

    /**
     * Nome: setUser
     * Registra o valor do atributo 'user'.
     *
     * @param user valor do atributo user
     * @see
     */
    public void setUser(Integer user) {
        this.user = user;
    }

    /**
     * Nome: getLine
     * Recupera o valor do atributo 'line'.
     *
     * @return valor do atributo 'line'
     * @see
     */
    public Integer getLine() {
        return line;
    }

    /**
     * Nome: setLine
     * Registra o valor do atributo 'line'.
     *
     * @param line valor do atributo line
     * @see
     */
    public void setLine(Integer line) {
        this.line = line;
    }

    /**
     * Nome: getAgent
     * Recupera o valor do atributo 'agent'.
     *
     * @return valor do atributo 'agent'
     * @see
     */
    public String getAgent() {
        return agent;
    }

    /**
     * Nome: setAgent
     * Registra o valor do atributo 'agent'.
     *
     * @param agent valor do atributo agent
     * @see
     */
    public void setAgent(String agent) {
        this.agent = agent;
    }

    /**
     * Nome: getUniqueid
     * Recupera o valor do atributo 'uniqueid'.
     *
     * @return valor do atributo 'uniqueid'
     * @see
     */
    public String getUniqueid() {
        return uniqueid;
    }

    /**
     * Nome: setUniqueid
     * Registra o valor do atributo 'uniqueid'.
     *
     * @param uniqueid valor do atributo uniqueid
     * @see
     */
    public void setUniqueid(String uniqueid) {
        this.uniqueid = uniqueid;
    }


    /**
     * Nome: getTimestamp
     * Recupera o valor do atributo 'timestamp'.
     *
     * @return valor do atributo 'timestamp'
     * @see
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * Nome: setTimestamp
     * Registra o valor do atributo 'timestamp'.
     *
     * @param timestamp valor do atributo timestamp
     * @see
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Nome: getHold
     * Recupera o valor do atributo 'hold'.
     *
     * @return valor do atributo 'hold'
     * @see
     */
    public Integer getHold() {
        return hold;
    }

    /**
     * Nome: setHold
     * Registra o valor do atributo 'hold'.
     *
     * @param hold valor do atributo hold
     * @see
     */
    public void setHold(Integer hold) {
        this.hold = hold;
    }

    /**
     * Nome: getQueue
     * Recupera o valor do atributo 'queue'.
     *
     * @return valor do atributo 'queue'
     * @see
     */
    public Integer getQueue() {
        return queue;
    }

    /**
     * Nome: setQueue
     * Registra o valor do atributo 'queue'.
     *
     * @param queue valor do atributo queue
     * @see
     */
    public void setQueue(Integer queue) {
        this.queue = queue;
    }

    /**
     * Nome: getDigit
     * Recupera o valor do atributo 'digit'.
     *
     * @return valor do atributo 'digit'
     * @see
     */
    public String getDigit() {
        return digit;
    }

    /**
     * Nome: setDigit
     * Registra o valor do atributo 'digit'.
     *
     * @param digit valor do atributo digit
     * @see
     */
    public void setDigit(String digit) {
        this.digit = digit;
    }

    /**
     * Nome: getWait
     * Recupera o valor do atributo 'wait'.
     *
     * @return valor do atributo 'wait'
     * @see
     */
    public String getWait() {
        return wait;
    }

    /**
     * Nome: setWait
     * Registra o valor do atributo 'wait'.
     *
     * @param wait valor do atributo wait
     * @see
     */
    public void setWait(String wait) {
        this.wait = wait;
    }

    /**
     * Nome: getPaused
     * Recupera o valor do atributo 'paused'.
     *
     * @return valor do atributo 'paused'
     * @see
     */
    public Integer getPaused() {
        return paused;
    }

    /**
     * Nome: setPaused
     * Registra o valor do atributo 'paused'.
     *
     * @param paused valor do atributo paused
     * @see
     */
    public void setPaused(Integer paused) {
        this.paused = paused;
    }

    /**
     * Nome: getReason
     * Recupera o valor do atributo 'reason'.
     *
     * @return valor do atributo 'reason'
     * @see
     */
    public String getReason() {
        return reason;
    }

    /**
     * Nome: setReason
     * Registra o valor do atributo 'reason'.
     *
     * @param reason valor do atributo reason
     * @see
     */
    public void setReason(String reason) {
        this.reason = reason;
    }
}
