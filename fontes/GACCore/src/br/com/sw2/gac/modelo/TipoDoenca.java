package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * <b>Descrição: The persistent class for the TbTipoDoenca database table.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
@Entity
@Table(name = "TbTipoDoenca")
public class TipoDoenca implements Serializable {

    /** Constante serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** Atributo cd tipo doenca. */
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(unique = true, nullable = false)
    private String cdTipoDoenca;

    /** Atributo ds tipo doenca. */
    @Column()
    private String dsTipoDoenca;

    // bi-directional many-to-one association to CID
    /** Atributo tb cids. */
    @OneToMany(mappedBy = "tbTipoDoenca")
    private List<CID> tbCids;

    /**
     * Construtor Padrao Instancia um novo objeto TipoDoenca.
     */
    public TipoDoenca() {
    }

    /**
     * Nome: getCdTipoDoenca Recupera o valor do atributo 'cdTipoDoenca'.
     * @return valor do atributo 'cdTipoDoenca'
     * @see
     */
    public String getCdTipoDoenca() {
        return this.cdTipoDoenca;
    }

    /**
     * Nome: setCdTipoDoenca Registra o valor do atributo 'cdTipoDoenca'.
     * @param cdTipoDoenca valor do atributo cd tipo doenca
     * @see
     */
    public void setCdTipoDoenca(String cdTipoDoenca) {
        this.cdTipoDoenca = cdTipoDoenca;
    }

    /**
     * Nome: getDsTipoDoenca Recupera o valor do atributo 'dsTipoDoenca'.
     * @return valor do atributo 'dsTipoDoenca'
     * @see
     */
    public String getDsTipoDoenca() {
        return this.dsTipoDoenca;
    }

    /**
     * Nome: setDsTipoDoenca Registra o valor do atributo 'dsTipoDoenca'.
     * @param dsTipoDoenca valor do atributo ds tipo doenca
     * @see
     */
    public void setDsTipoDoenca(String dsTipoDoenca) {
        this.dsTipoDoenca = dsTipoDoenca;
    }

    /**
     * Nome: getTbCids Recupera o valor do atributo 'tbCids'.
     * @return valor do atributo 'tbCids'
     * @see
     */
    public List<CID> getTbCids() {
        return this.tbCids;
    }

    /**
     * Nome: setTbCids Registra o valor do atributo 'tbCids'.
     * @param tbCids valor do atributo tb cids
     * @see
     */
    public void setTbCids(List<CID> tbCids) {
        this.tbCids = tbCids;
    }

}