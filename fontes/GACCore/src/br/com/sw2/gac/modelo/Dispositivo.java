package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TblDispositivo database table.
 * 
 */
@Entity
@Table(name="TblDispositivo")
public class Dispositivo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="IdDispositivo", unique=true, nullable=false, length=13)
	private String idDispositivo;

	@Temporal(TemporalType.DATE)
	private Date dtaEntrada;

	@Temporal(TemporalType.DATE)
	private Date dtaFabrica;

	@Temporal(TemporalType.DATE)
	private Date dtaProximaManut;

	@Temporal(TemporalType.DATE)
	private Date dtaSucata;

	@Column(name="Local", length=1)
	private String local;

	@Column(name="TpDispositivo", length=1)
	private String tpDispositivo;

	@Column(length=1)
	private String tpEstado;

	//bi-directional many-to-one association to TbUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Login", nullable=false)
	private Usuario tbUsuario;

	//bi-directional many-to-one association to TblPaciente
	@OneToMany(mappedBy="tblDispositivo1")
	private List<Paciente> tblPacientes1;

	//bi-directional many-to-one association to TblPaciente
	@OneToMany(mappedBy="tblDispositivo2")
	private List<Paciente> tblPacientes2;

	public Dispositivo() {
	}

	public String getIdDispositivo() {
		return this.idDispositivo;
	}

	public void setIdDispositivo(String idDispositivo) {
		this.idDispositivo = idDispositivo;
	}

	public Date getDtaEntrada() {
		return this.dtaEntrada;
	}

	public void setDtaEntrada(Date dtaEntrada) {
		this.dtaEntrada = dtaEntrada;
	}

	public Date getDtaFabrica() {
		return this.dtaFabrica;
	}

	public void setDtaFabrica(Date dtaFabrica) {
		this.dtaFabrica = dtaFabrica;
	}

	public Date getDtaProximaManut() {
		return this.dtaProximaManut;
	}

	public void setDtaProximaManut(Date dtaProximaManut) {
		this.dtaProximaManut = dtaProximaManut;
	}

	public Date getDtaSucata() {
		return this.dtaSucata;
	}

	public void setDtaSucata(Date dtaSucata) {
		this.dtaSucata = dtaSucata;
	}

	public String getLocal() {
		return this.local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getTpDispositivo() {
		return this.tpDispositivo;
	}

	public void setTpDispositivo(String tpDispositivo) {
		this.tpDispositivo = tpDispositivo;
	}

	public String getTpEstado() {
		return this.tpEstado;
	}

	public void setTpEstado(String tpEstado) {
		this.tpEstado = tpEstado;
	}

	public Usuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(Usuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public List<Paciente> getTblPacientes1() {
		return this.tblPacientes1;
	}

	public void setTblPacientes1(List<Paciente> tblPacientes1) {
		this.tblPacientes1 = tblPacientes1;
	}

	public List<Paciente> getTblPacientes2() {
		return this.tblPacientes2;
	}

	public void setTblPacientes2(List<Paciente> tblPacientes2) {
		this.tblPacientes2 = tblPacientes2;
	}

}