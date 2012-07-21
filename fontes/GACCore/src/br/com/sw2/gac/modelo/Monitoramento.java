package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TblMonitoramento database table.
 * 
 */
@Entity
@Table(name="TblMonitoramento")
public class Monitoramento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(unique=true, nullable=false)
	private Date dtaInicioMonitora;

	@Column(name="Acontecimento", length=1)
	private String acontecimento;

	@Column(length=1)
	private String tpMonitora;

	//bi-directional many-to-one association to TblPaciente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NmCPFPaciente", nullable=false)
	private Paciente tblPaciente;

	public Monitoramento() {
	}

	public Date getDtaInicioMonitora() {
		return this.dtaInicioMonitora;
	}

	public void setDtaInicioMonitora(Date dtaInicioMonitora) {
		this.dtaInicioMonitora = dtaInicioMonitora;
	}

	public String getAcontecimento() {
		return this.acontecimento;
	}

	public void setAcontecimento(String acontecimento) {
		this.acontecimento = acontecimento;
	}

	public String getTpMonitora() {
		return this.tpMonitora;
	}

	public void setTpMonitora(String tpMonitora) {
		this.tpMonitora = tpMonitora;
	}

	public Paciente getTblPaciente() {
		return this.tblPaciente;
	}

	public void setTblPaciente(Paciente tblPaciente) {
		this.tblPaciente = tblPaciente;
	}

}