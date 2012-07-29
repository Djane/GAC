package br.com.sw2.gac.modelo;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the TblParametro database table.
 * 
 */
@Entity
@Table(name="TblParametro")
public class Parametro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	@Column(name="IdParametro", unique=true, nullable=false)
	private int idParametro;

	@Column(name="DiasBemEstar", nullable=false)
	private int diasBemEstar;

	@Column(name="DiasDados", nullable=false)
	private int diasDados;

	@Column(name="ToleraRotinaCliente")
	private int toleraRotinaCliente;

	public Parametro() {
	}

	public int getIdParametro() {
		return this.idParametro;
	}

	public void setIdParametro(int idParametro) {
		this.idParametro = idParametro;
	}

	public int getDiasBemEstar() {
		return this.diasBemEstar;
	}

	public void setDiasBemEstar(int diasBemEstar) {
		this.diasBemEstar = diasBemEstar;
	}

	public int getDiasDados() {
		return this.diasDados;
	}

	public void setDiasDados(int diasDados) {
		this.diasDados = diasDados;
	}

	public int getToleraRotinaCliente() {
		return this.toleraRotinaCliente;
	}

	public void setToleraRotinaCliente(int toleraRotinaCliente) {
		this.toleraRotinaCliente = toleraRotinaCliente;
	}

}