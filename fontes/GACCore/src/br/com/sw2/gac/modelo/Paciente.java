package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TblPaciente database table.
 * 
 */
@Entity
@Table(name="TblPaciente")
public class Paciente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="NmCPFPaciente", unique=true, nullable=false, length=14)
	private String nmCPFPaciente;

	@Column(nullable=false, length=60)
	private String dsBairro;

	@Column(nullable=false, length=10)
	private String dsCEP;

	@Column(nullable=false, length=60)
	private String dsCidade;

	@Lob
	private String dsCobertura;

	@Column(length=100)
	private String dsEmail;

	@Column(nullable=false, length=60)
	private String dsEndereco;

	@Column(nullable=false, length=2)
	private String dsEstado;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtNascimento;

	@Column(length=12)
	private String nmCelular;

	@Lob
	@Column(name="NmNecessidadeEspecial")
	private String nmNecessidadeEspecial;

	@Column(name="NmPaciente", nullable=false, length=60)
	private String nmPaciente;

	@Column(length=60)
	private String nmPlanoSaude;

	@Column(nullable=false, length=14)
	private String nmRG;

	@Column(length=12)
	private String nmTelefone;

	@Column(name="TpSexo", length=1)
	private String tpSexo;

	//bi-directional many-to-one association to TblContato
	@OneToMany(mappedBy="tblPaciente")
	private List<Contato> tblContatos;

	//bi-directional many-to-one association to TblMonitoramento
	@OneToMany(mappedBy="tblPaciente")
	private List<Monitoramento> tblMonitoramentos;

	//bi-directional many-to-one association to TblOcorrencia
	@OneToMany(mappedBy="tblPaciente")
	private List<Ocorrencia> tblOcorrencias;

	//bi-directional many-to-many association to CID
	@ManyToMany
	@JoinTable(
		name="TblPacXDoenca"
		, joinColumns={
			@JoinColumn(name="NmCPFPaciente", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="CdCID", nullable=false)
			}
		)
	private List<CID> tbCids;

	//bi-directional many-to-one association to TblDispositivo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdCentral", nullable=false)
	private Dispositivo tblDispositivo1;

	//bi-directional many-to-one association to TblContrato
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="nmContrato")
	private Contrato tblContrato;

	//bi-directional many-to-one association to TblDispositivo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdDispositivo")
	private Dispositivo tblDispositivo2;

	//bi-directional many-to-one association to TbUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Login", nullable=false)
	private Usuario tbUsuario;

	//bi-directional many-to-one association to TblTratamento
	@OneToMany(mappedBy="tblPaciente")
	private List<Tratamento> tblTratamentos;

	public Paciente() {
	}

	public String getNmCPFPaciente() {
		return this.nmCPFPaciente;
	}

	public void setNmCPFPaciente(String nmCPFPaciente) {
		this.nmCPFPaciente = nmCPFPaciente;
	}

	public String getDsBairro() {
		return this.dsBairro;
	}

	public void setDsBairro(String dsBairro) {
		this.dsBairro = dsBairro;
	}

	public String getDsCEP() {
		return this.dsCEP;
	}

	public void setDsCEP(String dsCEP) {
		this.dsCEP = dsCEP;
	}

	public String getDsCidade() {
		return this.dsCidade;
	}

	public void setDsCidade(String dsCidade) {
		this.dsCidade = dsCidade;
	}

	public String getDsCobertura() {
		return this.dsCobertura;
	}

	public void setDsCobertura(String dsCobertura) {
		this.dsCobertura = dsCobertura;
	}

	public String getDsEmail() {
		return this.dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getDsEndereco() {
		return this.dsEndereco;
	}

	public void setDsEndereco(String dsEndereco) {
		this.dsEndereco = dsEndereco;
	}

	public String getDsEstado() {
		return this.dsEstado;
	}

	public void setDsEstado(String dsEstado) {
		this.dsEstado = dsEstado;
	}

	public Date getDtNascimento() {
		return this.dtNascimento;
	}

	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getNmCelular() {
		return this.nmCelular;
	}

	public void setNmCelular(String nmCelular) {
		this.nmCelular = nmCelular;
	}

	public String getNmNecessidadeEspecial() {
		return this.nmNecessidadeEspecial;
	}

	public void setNmNecessidadeEspecial(String nmNecessidadeEspecial) {
		this.nmNecessidadeEspecial = nmNecessidadeEspecial;
	}

	public String getNmPaciente() {
		return this.nmPaciente;
	}

	public void setNmPaciente(String nmPaciente) {
		this.nmPaciente = nmPaciente;
	}

	public String getNmPlanoSaude() {
		return this.nmPlanoSaude;
	}

	public void setNmPlanoSaude(String nmPlanoSaude) {
		this.nmPlanoSaude = nmPlanoSaude;
	}

	public String getNmRG() {
		return this.nmRG;
	}

	public void setNmRG(String nmRG) {
		this.nmRG = nmRG;
	}

	public String getNmTelefone() {
		return this.nmTelefone;
	}

	public void setNmTelefone(String nmTelefone) {
		this.nmTelefone = nmTelefone;
	}

	public String getTpSexo() {
		return this.tpSexo;
	}

	public void setTpSexo(String tpSexo) {
		this.tpSexo = tpSexo;
	}

	public List<Contato> getTblContatos() {
		return this.tblContatos;
	}

	public void setTblContatos(List<Contato> tblContatos) {
		this.tblContatos = tblContatos;
	}

	public List<Monitoramento> getTblMonitoramentos() {
		return this.tblMonitoramentos;
	}

	public void setTblMonitoramentos(List<Monitoramento> tblMonitoramentos) {
		this.tblMonitoramentos = tblMonitoramentos;
	}

	public List<Ocorrencia> getTblOcorrencias() {
		return this.tblOcorrencias;
	}

	public void setTblOcorrencias(List<Ocorrencia> tblOcorrencias) {
		this.tblOcorrencias = tblOcorrencias;
	}

	public List<CID> getTbCids() {
		return this.tbCids;
	}

	public void setTbCids(List<CID> tbCids) {
		this.tbCids = tbCids;
	}

	public Dispositivo getTblDispositivo1() {
		return this.tblDispositivo1;
	}

	public void setTblDispositivo1(Dispositivo tblDispositivo1) {
		this.tblDispositivo1 = tblDispositivo1;
	}

	public Contrato getTblContrato() {
		return this.tblContrato;
	}

	public void setTblContrato(Contrato tblContrato) {
		this.tblContrato = tblContrato;
	}

	public Dispositivo getTblDispositivo2() {
		return this.tblDispositivo2;
	}

	public void setTblDispositivo2(Dispositivo tblDispositivo2) {
		this.tblDispositivo2 = tblDispositivo2;
	}

	public Usuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(Usuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public List<Tratamento> getTblTratamentos() {
		return this.tblTratamentos;
	}

	public void setTblTratamentos(List<Tratamento> tblTratamentos) {
		this.tblTratamentos = tblTratamentos;
	}

}