package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TblContato database table.
 * 
 */
@Entity
@Table(name="TblContato")
public class Contato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="IdContato", unique=true, nullable=false)
	private int idContato;

	@Column(name="BaiContato", length=60)
	private String baiContato;

	@Column(length=10)
	private String CEPContato;

	@Column(name="CidContato", length=60)
	private String cidContato;

	@Column(name="Contratante", length=1)
	private String contratante;

	@Temporal(TemporalType.DATE)
	private Date dtaNascimento;

	@Column(name="EndContato", length=60)
	private String endContato;

	@Column(name="EstadoContato", length=2)
	private String estadoContato;

	@Column(name="GrauParentesco", length=1)
	private String grauParentesco;

	@Column(name="NomeContato", length=60)
	private String nomeContato;

	private int sqaChamada;

	//bi-directional many-to-one association to TblAcionamento
	@OneToMany(mappedBy="tblContato")
	private List<Acionamento> tblAcionamentos;

	//bi-directional many-to-one association to TblPaciente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NmCPFPaciente", nullable=false)
	private Paciente tblPaciente;

	//bi-directional many-to-one association to TbUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Login", nullable=false)
	private Usuario tbUsuario;

	//bi-directional many-to-one association to TblFormaComunica
	@OneToMany(mappedBy="tblContato")
	private List<FormaComunica> tblFormaComunicas;

	public Contato() {
	}

	public int getIdContato() {
		return this.idContato;
	}

	public void setIdContato(int idContato) {
		this.idContato = idContato;
	}

	public String getBaiContato() {
		return this.baiContato;
	}

	public void setBaiContato(String baiContato) {
		this.baiContato = baiContato;
	}

	public String getCEPContato() {
		return this.CEPContato;
	}

	public void setCEPContato(String CEPContato) {
		this.CEPContato = CEPContato;
	}

	public String getCidContato() {
		return this.cidContato;
	}

	public void setCidContato(String cidContato) {
		this.cidContato = cidContato;
	}

	public String getContratante() {
		return this.contratante;
	}

	public void setContratante(String contratante) {
		this.contratante = contratante;
	}

	public Date getDtaNascimento() {
		return this.dtaNascimento;
	}

	public void setDtaNascimento(Date dtaNascimento) {
		this.dtaNascimento = dtaNascimento;
	}

	public String getEndContato() {
		return this.endContato;
	}

	public void setEndContato(String endContato) {
		this.endContato = endContato;
	}

	public String getEstadoContato() {
		return this.estadoContato;
	}

	public void setEstadoContato(String estadoContato) {
		this.estadoContato = estadoContato;
	}

	public String getGrauParentesco() {
		return this.grauParentesco;
	}

	public void setGrauParentesco(String grauParentesco) {
		this.grauParentesco = grauParentesco;
	}

	public String getNomeContato() {
		return this.nomeContato;
	}

	public void setNomeContato(String nomeContato) {
		this.nomeContato = nomeContato;
	}

	public int getSqaChamada() {
		return this.sqaChamada;
	}

	public void setSqaChamada(int sqaChamada) {
		this.sqaChamada = sqaChamada;
	}

	public List<Acionamento> getTblAcionamentos() {
		return this.tblAcionamentos;
	}

	public void setTblAcionamentos(List<Acionamento> tblAcionamentos) {
		this.tblAcionamentos = tblAcionamentos;
	}

	public Paciente getTblPaciente() {
		return this.tblPaciente;
	}

	public void setTblPaciente(Paciente tblPaciente) {
		this.tblPaciente = tblPaciente;
	}

	public Usuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(Usuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public List<FormaComunica> getTblFormaComunicas() {
		return this.tblFormaComunicas;
	}

	public void setTblFormaComunicas(List<FormaComunica> tblFormaComunicas) {
		this.tblFormaComunicas = tblFormaComunicas;
	}

}