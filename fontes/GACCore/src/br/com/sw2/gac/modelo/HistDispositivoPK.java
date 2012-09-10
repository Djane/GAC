/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.sw2.gac.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <b>Descrição: Chave Primaria da tabela de historicos de movimentação.</b> <br>
 * .
 * @author rogerio
 */
@Embeddable
public class HistDispositivoPK implements Serializable {

	private static final long serialVersionUID = -5476009211079940908L;

	/** Atributo dthr muda estado. */
    @Basic(optional = false)
    @Column(name = "dthrMudaEstado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dthrMudaEstado;

    /** Atributo id dispositivo. */
    @Basic(optional = false)
    @Column(name = "idDispositivo")
    private String idDispositivo;

    /**
     * Construtor Padrao Instancia um novo objeto TblhistdispositivoPK.
     */
    public HistDispositivoPK() {
    }

    /**
     * Construtor Padrao Instancia um novo objeto TblhistdispositivoPK.
     * @param dthrMudaEstado the dthr muda estado
     * @param idDispositivo the id dispositivo
     */
    public HistDispositivoPK(Date dthrMudaEstado, String idDispositivo) {
        this.dthrMudaEstado = dthrMudaEstado;
        this.idDispositivo = idDispositivo;
    }

    /**
     * Nome: getDthrMudaEstado Recupera o valor do atributo 'dthrMudaEstado'.
     * @return valor do atributo 'dthrMudaEstado'
     * @see
     */
    public Date getDthrMudaEstado() {
        return dthrMudaEstado;
    }

    /**
     * Nome: setDthrMudaEstado Registra o valor do atributo 'dthrMudaEstado'.
     * @param dthrMudaEstado valor do atributo dthr muda estado
     * @see
     */
    public void setDthrMudaEstado(Date dthrMudaEstado) {
        this.dthrMudaEstado = dthrMudaEstado;
    }

    /**
     * Nome: getIdDispositivo Recupera o valor do atributo 'idDispositivo'.
     * @return valor do atributo 'idDispositivo'
     * @see
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Nome: setIdDispositivo Registra o valor do atributo 'idDispositivo'.
     * @param idDispositivo valor do atributo id dispositivo
     * @see
     */
    public void setIdDispositivo(String idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dthrMudaEstado != null ? dthrMudaEstado.hashCode() : 0);
        hash += (idDispositivo != null ? idDispositivo.hashCode() : 0);
        return hash;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HistDispositivoPK)) {
            return false;
        }
        HistDispositivoPK other = (HistDispositivoPK) object;
        if ((this.dthrMudaEstado == null && other.dthrMudaEstado != null)
                || (this.dthrMudaEstado != null && !this.dthrMudaEstado
                        .equals(other.dthrMudaEstado))) {
            return false;
        }
        if ((this.idDispositivo == null && other.idDispositivo != null)
                || (this.idDispositivo != null && !this.idDispositivo.equals(other.idDispositivo))) {
            return false;
        }
        return true;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "br.com.sw2.gac.modelo.TblhistdispositivoPK[ dthrMudaEstado=" + dthrMudaEstado
                + ", idDispositivo=" + idDispositivo + " ]";
    }
}
