package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TbTipoDoenca database table.
 * 
 */
@Entity
@Table(name="TbTipoDoenca")
public class TipoDoenca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(unique=true, nullable=false, length=10)
	private String cdTipoDoenca;

	@Column(length=60)
	private String dsTipoDoenca;

	//bi-directional many-to-one association to CID
	@OneToMany(mappedBy="tbTipoDoenca")
	private List<CID> tbCids;

	public TipoDoenca() {
	}

	public String getCdTipoDoenca() {
		return this.cdTipoDoenca;
	}

	public void setCdTipoDoenca(String cdTipoDoenca) {
		this.cdTipoDoenca = cdTipoDoenca;
	}

	public String getDsTipoDoenca() {
		return this.dsTipoDoenca;
	}

	public void setDsTipoDoenca(String dsTipoDoenca) {
		this.dsTipoDoenca = dsTipoDoenca;
	}

	public List<CID> getTbCids() {
		return this.tbCids;
	}

	public void setTbCids(List<CID> tbCids) {
		this.tbCids = tbCids;
	}

}