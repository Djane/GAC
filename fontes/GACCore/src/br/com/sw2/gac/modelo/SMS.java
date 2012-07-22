package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the TblSMS database table.
 * 
 */
@Entity
@Table(name="TblSMS")
public class SMS implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="IdSMS", unique=true, nullable=false)
	private int idSMS;

	@Column(nullable=false, length=100)
	private String dsMensagem;

	@Temporal(TemporalType.DATE)
	@Column(nullable=false)
	private Date dtInicioValidade;

	@Temporal(TemporalType.DATE)
	private Date dtTerminoValidade;

	@Column(length=1)
	private String idMomento;

	@Column(nullable=false, length=20)
	private String tpMensagem;

	//bi-directional many-to-one association to TblAcionamento
	@OneToMany(mappedBy="tblSm")
	private List<Acionamento> tblAcionamentos;

	public SMS() {
	}

	public int getIdSMS() {
		return this.idSMS;
	}

	public void setIdSMS(int idSMS) {
		this.idSMS = idSMS;
	}

	public String getDsMensagem() {
		return this.dsMensagem;
	}

	public void setDsMensagem(String dsMensagem) {
		this.dsMensagem = dsMensagem;
	}

	public Date getDtInicioValidade() {
		return this.dtInicioValidade;
	}

	public void setDtInicioValidade(Date dtInicioValidade) {
		this.dtInicioValidade = dtInicioValidade;
	}

	public Date getDtTerminoValidade() {
		return this.dtTerminoValidade;
	}

	public void setDtTerminoValidade(Date dtTerminoValidade) {
		this.dtTerminoValidade = dtTerminoValidade;
	}

	public String getIdMomento() {
		return this.idMomento;
	}

	public void setIdMomento(String idMomento) {
		this.idMomento = idMomento;
	}

	public String getTpMensagem() {
		return this.tpMensagem;
	}

	public void setTpMensagem(String tpMensagem) {
		this.tpMensagem = tpMensagem;
	}

	public List<Acionamento> getTblAcionamentos() {
		return this.tblAcionamentos;
	}

	public void setTblAcionamentos(List<Acionamento> tblAcionamentos) {
		this.tblAcionamentos = tblAcionamentos;
	}

}