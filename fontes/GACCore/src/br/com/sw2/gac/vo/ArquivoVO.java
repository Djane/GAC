package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * <b>Descrição:</b> <br>.
 *
 * @author: lucianor
 * @version 1.0
 *
 * Copyright 2012 SmartAngel.
 */
public class ArquivoVO {

    /** Atributo data envio. */
    private Date dataEnvio;

    /** Atributo status. */
    private String status;

    /** Atributo caminho. */
    private String caminho;

    private UsuarioVO usuario;

    /**
     * Nome: getDataEnvio
     * Recupera o valor do atributo 'dataEnvio'.
     *
     * @return valor do atributo 'dataEnvio'
     * @see
     */
    public Date getDataEnvio() {
        return dataEnvio;
    }

    /**
     * Nome: setDataEnvio
     * Registra o valor do atributo 'dataEnvio'.
     *
     * @param dataEnvio valor do atributo data envio
     * @see
     */
    public void setDataEnvio(Date dataEnvio) {
        this.dataEnvio = dataEnvio;
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
     * Nome: getCaminho
     * Recupera o valor do atributo 'caminho'.
     *
     * @return valor do atributo 'caminho'
     * @see
     */
    public String getCaminho() {
        return caminho;
    }

    /**
     * Nome: setCaminho
     * Registra o valor do atributo 'caminho'.
     *
     * @param caminho valor do atributo caminho
     * @see
     */
    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

	public UsuarioVO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioVO usuario) {
		this.usuario = usuario;
	}

}
