package br.com.sw2.gac.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b>Descrição: The persistent class for the TblParametro database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TblParametro")
public class Parametro implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo id parametro. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "IdParametro", unique = true, nullable = false)
    private int idParametro;

    /** Atributo dias bem estar. */
    @Column(name = "DiasBemEstar", nullable = false)
    private int diasBemEstar;

    /** Atributo dias dados. */
    @Column(name = "DiasDados", nullable = false)
    private int diasDados;

    /** Atributo tolera rotina cliente. */
    @Column(name = "ToleraRotinaCliente")
    private int toleraRotinaCliente;

    /**
     * Construtor Padrao Instancia um novo objeto Parametro.
     */
    public Parametro() {
    }

    /**
     * Nome: getIdParametro Recupera o valor do atributo 'idParametro'.
     * @return valor do atributo 'idParametro'
     * @see
     */
    public int getIdParametro() {
        return this.idParametro;
    }

    /**
     * Nome: setIdParametro Registra o valor do atributo 'idParametro'.
     * @param idParametro valor do atributo id parametro
     * @see
     */
    public void setIdParametro(int idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * Nome: getDiasBemEstar Recupera o valor do atributo 'diasBemEstar'.
     * @return valor do atributo 'diasBemEstar'
     * @see
     */
    public int getDiasBemEstar() {
        return this.diasBemEstar;
    }

    /**
     * Nome: setDiasBemEstar Registra o valor do atributo 'diasBemEstar'.
     * @param diasBemEstar valor do atributo dias bem estar
     * @see
     */
    public void setDiasBemEstar(int diasBemEstar) {
        this.diasBemEstar = diasBemEstar;
    }

    /**
     * Nome: getDiasDados Recupera o valor do atributo 'diasDados'.
     * @return valor do atributo 'diasDados'
     * @see
     */
    public int getDiasDados() {
        return this.diasDados;
    }

    /**
     * Nome: setDiasDados Registra o valor do atributo 'diasDados'.
     * @param diasDados valor do atributo dias dados
     * @see
     */
    public void setDiasDados(int diasDados) {
        this.diasDados = diasDados;
    }

    /**
     * Nome: getToleraRotinaCliente Recupera o valor do atributo 'toleraRotinaCliente'.
     * @return valor do atributo 'toleraRotinaCliente'
     * @see
     */
    public int getToleraRotinaCliente() {
        return this.toleraRotinaCliente;
    }

    /**
     * Nome: setToleraRotinaCliente Registra o valor do atributo 'toleraRotinaCliente'.
     * @param toleraRotinaCliente valor do atributo tolera rotina cliente
     * @see
     */
    public void setToleraRotinaCliente(int toleraRotinaCliente) {
        this.toleraRotinaCliente = toleraRotinaCliente;
    }

}