package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TblAcionamento database table.
 * 
 */
@Entity
@Table(name="TblAcionamento")
public class Acionamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="IdAciona", unique=true, nullable=false)
	private int idAciona;

	@Lob
	@Column(name="AcaoPedida")
	private String acaoPedida;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaHoraAciona;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DtaHoraFinal")
	private Date dtaHoraFinal;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dtaHoraInicio;

	@Column(name="Sucesso", length=1)
	private String sucesso;

	@Column(name="TextoLivreSMS", length=100)
	private String textoLivreSMS;

	//bi-directional many-to-one association to TblSM
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdSMS")
	private SMS tblSm;

	//bi-directional many-to-one association to TblContato
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdContato", nullable=false)
	private Contato tblContato;

	//bi-directional many-to-one association to TblOcorrencia
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdOcorrencia", nullable=false)
	private Ocorrencia tblOcorrencia;

	public Acionamento() {
	}

	public int getIdAciona() {
		return this.idAciona;
	}

	public void setIdAciona(int idAciona) {
		this.idAciona = idAciona;
	}

	public String getAcaoPedida() {
		return this.acaoPedida;
	}

	public void setAcaoPedida(String acaoPedida) {
		this.acaoPedida = acaoPedida;
	}

	public Date getDtaHoraAciona() {
		return this.dtaHoraAciona;
	}

	public void setDtaHoraAciona(Date dtaHoraAciona) {
		this.dtaHoraAciona = dtaHoraAciona;
	}

	public Date getDtaHoraFinal() {
		return this.dtaHoraFinal;
	}

	public void setDtaHoraFinal(Date dtaHoraFinal) {
		this.dtaHoraFinal = dtaHoraFinal;
	}

	public Date getDtaHoraInicio() {
		return this.dtaHoraInicio;
	}

	public void setDtaHoraInicio(Date dtaHoraInicio) {
		this.dtaHoraInicio = dtaHoraInicio;
	}

	public String getSucesso() {
		return this.sucesso;
	}

	public void setSucesso(String sucesso) {
		this.sucesso = sucesso;
	}

	public String getTextoLivreSMS() {
		return this.textoLivreSMS;
	}

	public void setTextoLivreSMS(String textoLivreSMS) {
		this.textoLivreSMS = textoLivreSMS;
	}

	public SMS getTblSm() {
		return this.tblSm;
	}

	public void setTblSm(SMS tblSm) {
		this.tblSm = tblSm;
	}

	public Contato getTblContato() {
		return this.tblContato;
	}

	public void setTblContato(Contato tblContato) {
		this.tblContato = tblContato;
	}

	public Ocorrencia getTblOcorrencia() {
		return this.tblOcorrencia;
	}

	public void setTblOcorrencia(Ocorrencia tblOcorrencia) {
		this.tblOcorrencia = tblOcorrencia;
	}

}