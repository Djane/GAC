package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TbCID database table.
 * 
 */
@Entity
@Table(name="TbCID")
public class CID implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="CdCID", unique=true, nullable=false, length=10)
	private String cdCID;

	@Column(length=60)
	private String nmDoenca;

	//bi-directional many-to-one association to TipoDoenca
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cdTipoDoenca", nullable=false)
	private TipoDoenca tbTipoDoenca;

	//bi-directional many-to-many association to TblPaciente
	@ManyToMany(mappedBy="tbCids")
	private List<Paciente> tblPacientes;

	public CID() {
	}

	public String getCdCID() {
		return this.cdCID;
	}

	public void setCdCID(String cdCID) {
		this.cdCID = cdCID;
	}

	public String getNmDoenca() {
		return this.nmDoenca;
	}

	public void setNmDoenca(String nmDoenca) {
		this.nmDoenca = nmDoenca;
	}

	public TipoDoenca getTbTipoDoenca() {
		return this.tbTipoDoenca;
	}

	public void setTbTipoDoenca(TipoDoenca tbTipoDoenca) {
		this.tbTipoDoenca = tbTipoDoenca;
	}

	public List<Paciente> getTblPacientes() {
		return this.tblPacientes;
	}

	public void setTblPacientes(List<Paciente> tblPacientes) {
		this.tblPacientes = tblPacientes;
	}

}