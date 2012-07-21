package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the TblTratamento database table.
 * 
 */
@Embeddable
public class TratamentoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="IdTratamento", unique=true, nullable=false)
	private int idTratamento;

	@Column(name="NmCPFPaciente", unique=true, nullable=false, length=14)
	private String nmCPFPaciente;

	public TratamentoPK() {
	}
	public int getIdTratamento() {
		return this.idTratamento;
	}
	public void setIdTratamento(int idTratamento) {
		this.idTratamento = idTratamento;
	}
	public String getNmCPFPaciente() {
		return this.nmCPFPaciente;
	}
	public void setNmCPFPaciente(String nmCPFPaciente) {
		this.nmCPFPaciente = nmCPFPaciente;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TratamentoPK)) {
			return false;
		}
		TratamentoPK castOther = (TratamentoPK)other;
		return 
			(this.idTratamento == castOther.idTratamento)
			&& this.nmCPFPaciente.equals(castOther.nmCPFPaciente);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idTratamento;
		hash = hash * prime + this.nmCPFPaciente.hashCode();
		
		return hash;
	}
}