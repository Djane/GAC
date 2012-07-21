package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TbUsuario database table.
 * 
 */
@Entity
@Table(name="TbUsuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="Login", unique=true, nullable=false, length=10)
	private String login;

	@Column(name="CdPerfil")
	private int cdPerfil;

	private int nmFuncao;

	@Column(length=12)
	private String nmTelCelular;

	@Column(length=12)
	private String nmTelFixo;

	@Column(nullable=false, length=60)
	private String nmUsuario;

	@Column(nullable=false, length=8)
	private String senha;

	//bi-directional many-to-one association to TblContato
	@OneToMany(mappedBy="tbUsuario")
	private List<Contato> tblContatos;

	//bi-directional many-to-one association to TblContrato
	@OneToMany(mappedBy="tbUsuario")
	private List<Contrato> tblContratos;

	//bi-directional many-to-one association to TblDispositivo
	@OneToMany(mappedBy="tbUsuario")
	private List<Dispositivo> tblDispositivos;

	//bi-directional many-to-one association to TblOcorrencia
	@OneToMany(mappedBy="tbUsuario")
	private List<Ocorrencia> tblOcorrencias;

	//bi-directional many-to-one association to TblPaciente
	@OneToMany(mappedBy="tbUsuario")
	private List<Paciente> tblPacientes;

	public Usuario() {
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public int getCdPerfil() {
		return this.cdPerfil;
	}

	public void setCdPerfil(int cdPerfil) {
		this.cdPerfil = cdPerfil;
	}

	public int getNmFuncao() {
		return this.nmFuncao;
	}

	public void setNmFuncao(int nmFuncao) {
		this.nmFuncao = nmFuncao;
	}

	public String getNmTelCelular() {
		return this.nmTelCelular;
	}

	public void setNmTelCelular(String nmTelCelular) {
		this.nmTelCelular = nmTelCelular;
	}

	public String getNmTelFixo() {
		return this.nmTelFixo;
	}

	public void setNmTelFixo(String nmTelFixo) {
		this.nmTelFixo = nmTelFixo;
	}

	public String getNmUsuario() {
		return this.nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Contato> getTblContatos() {
		return this.tblContatos;
	}

	public void setTblContatos(List<Contato> tblContatos) {
		this.tblContatos = tblContatos;
	}

	public List<Contrato> getTblContratos() {
		return this.tblContratos;
	}

	public void setTblContratos(List<Contrato> tblContratos) {
		this.tblContratos = tblContratos;
	}

	public List<Dispositivo> getTblDispositivos() {
		return this.tblDispositivos;
	}

	public void setTblDispositivos(List<Dispositivo> tblDispositivos) {
		this.tblDispositivos = tblDispositivos;
	}

	public List<Ocorrencia> getTblOcorrencias() {
		return this.tblOcorrencias;
	}

	public void setTblOcorrencias(List<Ocorrencia> tblOcorrencias) {
		this.tblOcorrencias = tblOcorrencias;
	}

	public List<Paciente> getTblPacientes() {
		return this.tblPacientes;
	}

	public void setTblPacientes(List<Paciente> tblPacientes) {
		this.tblPacientes = tblPacientes;
	}

}