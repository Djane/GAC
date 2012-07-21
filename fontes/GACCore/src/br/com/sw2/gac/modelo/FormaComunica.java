package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TblFormaComunica database table.
 * 
 */
@Entity
@Table(name="TblFormaComunica")
public class FormaComunica implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private FormaComunicaPK id;

	@Column(length=12)
	private String foneContato;

	@Column(length=100)
	private String mailContato;

	@Column(length=14)
	private String tpContato;

	//bi-directional many-to-one association to TblContato
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IdContato", nullable=false, insertable=false, updatable=false)
	private Contato tblContato;

	public FormaComunica() {
	}

	public FormaComunicaPK getId() {
		return this.id;
	}

	public void setId(FormaComunicaPK id) {
		this.id = id;
	}

	public String getFoneContato() {
		return this.foneContato;
	}

	public void setFoneContato(String foneContato) {
		this.foneContato = foneContato;
	}

	public String getMailContato() {
		return this.mailContato;
	}

	public void setMailContato(String mailContato) {
		this.mailContato = mailContato;
	}

	public String getTpContato() {
		return this.tpContato;
	}

	public void setTpContato(String tpContato) {
		this.tpContato = tpContato;
	}

	public Contato getTblContato() {
		return this.tblContato;
	}

	public void setTblContato(Contato tblContato) {
		this.tblContato = tblContato;
	}

}