package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TblTratamento database table.
 * 
 */
@Entity
@Table(name="TblTratamento")
public class Tratamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TratamentoPK id;

	@Column(name="DescrTrata", length=60)
	private String descrTrata;

	@Column(name="FreqMinutos")
	private int freqMinutos;

	@Column(name="NomeTrata", length=60)
	private String nomeTrata;

	//bi-directional many-to-one association to TblPaciente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NmCPFPaciente", nullable=false, insertable=false, updatable=false)
	private Paciente tblPaciente;

	public Tratamento() {
	}

	public TratamentoPK getId() {
		return this.id;
	}

	public void setId(TratamentoPK id) {
		this.id = id;
	}

	public String getDescrTrata() {
		return this.descrTrata;
	}

	public void setDescrTrata(String descrTrata) {
		this.descrTrata = descrTrata;
	}

	public int getFreqMinutos() {
		return this.freqMinutos;
	}

	public void setFreqMinutos(int freqMinutos) {
		this.freqMinutos = freqMinutos;
	}

	public String getNomeTrata() {
		return this.nomeTrata;
	}

	public void setNomeTrata(String nomeTrata) {
		this.nomeTrata = nomeTrata;
	}

	public Paciente getTblPaciente() {
		return this.tblPaciente;
	}

	public void setTblPaciente(Paciente tblPaciente) {
		this.tblPaciente = tblPaciente;
	}

}