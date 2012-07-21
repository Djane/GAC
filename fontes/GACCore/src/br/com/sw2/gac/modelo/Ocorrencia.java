package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TblOcorrencia database table.
 * 
 */
@Entity
@Table(name="TblOcorrencia")
public class Ocorrencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="IdOcorrencia", unique=true, nullable=false)
	private int idOcorrencia;

	@Lob
	@Column(name="AcaoOcorrencia")
	private String acaoOcorrencia;

	@Lob
	@Column(name="Conclusao")
	private String conclusao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaAtend;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaHoraAbertura;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaHoraFechamento;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaHoraInicio;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaHoraTermino;

	@Lob
	@Column(name="ReclOcorrencia")
	private String reclOcorrencia;

	@Column(name="StatusOcorre", length=1)
	private String statusOcorre;

	private int tpOcorrencia;

	//bi-directional many-to-one association to TblAcionamento
	@OneToMany(mappedBy="tblOcorrencia")
	private List<Acionamento> tblAcionamentos;

	//bi-directional many-to-one association to TbUsuario
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="Login", nullable=false)
	private Usuario tbUsuario;

	//bi-directional many-to-one association to TblPaciente
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="NmCPFPaciente", nullable=false)
	private Paciente tblPaciente;

	//bi-directional many-to-one association to TblScript
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdScript", nullable=false)
	private Script tblScript;

	public Ocorrencia() {
	}

	public int getIdOcorrencia() {
		return this.idOcorrencia;
	}

	public void setIdOcorrencia(int idOcorrencia) {
		this.idOcorrencia = idOcorrencia;
	}

	public String getAcaoOcorrencia() {
		return this.acaoOcorrencia;
	}

	public void setAcaoOcorrencia(String acaoOcorrencia) {
		this.acaoOcorrencia = acaoOcorrencia;
	}

	public String getConclusao() {
		return this.conclusao;
	}

	public void setConclusao(String conclusao) {
		this.conclusao = conclusao;
	}

	public Date getDtaAtend() {
		return this.dtaAtend;
	}

	public void setDtaAtend(Date dtaAtend) {
		this.dtaAtend = dtaAtend;
	}

	public Date getDtaHoraAbertura() {
		return this.dtaHoraAbertura;
	}

	public void setDtaHoraAbertura(Date dtaHoraAbertura) {
		this.dtaHoraAbertura = dtaHoraAbertura;
	}

	public Date getDtaHoraFechamento() {
		return this.dtaHoraFechamento;
	}

	public void setDtaHoraFechamento(Date dtaHoraFechamento) {
		this.dtaHoraFechamento = dtaHoraFechamento;
	}

	public Date getDtaHoraInicio() {
		return this.dtaHoraInicio;
	}

	public void setDtaHoraInicio(Date dtaHoraInicio) {
		this.dtaHoraInicio = dtaHoraInicio;
	}

	public Date getDtaHoraTermino() {
		return this.dtaHoraTermino;
	}

	public void setDtaHoraTermino(Date dtaHoraTermino) {
		this.dtaHoraTermino = dtaHoraTermino;
	}

	public String getReclOcorrencia() {
		return this.reclOcorrencia;
	}

	public void setReclOcorrencia(String reclOcorrencia) {
		this.reclOcorrencia = reclOcorrencia;
	}

	public String getStatusOcorre() {
		return this.statusOcorre;
	}

	public void setStatusOcorre(String statusOcorre) {
		this.statusOcorre = statusOcorre;
	}

	public int getTpOcorrencia() {
		return this.tpOcorrencia;
	}

	public void setTpOcorrencia(int tpOcorrencia) {
		this.tpOcorrencia = tpOcorrencia;
	}

	public List<Acionamento> getTblAcionamentos() {
		return this.tblAcionamentos;
	}

	public void setTblAcionamentos(List<Acionamento> tblAcionamentos) {
		this.tblAcionamentos = tblAcionamentos;
	}

	public Usuario getTbUsuario() {
		return this.tbUsuario;
	}

	public void setTbUsuario(Usuario tbUsuario) {
		this.tbUsuario = tbUsuario;
	}

	public Paciente getTblPaciente() {
		return this.tblPaciente;
	}

	public void setTblPaciente(Paciente tblPaciente) {
		this.tblPaciente = tblPaciente;
	}

	public Script getTblScript() {
		return this.tblScript;
	}

	public void setTblScript(Script tblScript) {
		this.tblScript = tblScript;
	}

}