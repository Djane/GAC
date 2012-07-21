package br.com.sw2.gac.tools;

/**
 * Enum que define os Perfils de Acesso ao Aplicativo
 * 
 * @author marcelo
 *
 */
public enum Perfil {
	Gerencial (1),
	Backoffice (2),
	Usuario (3),
	Atendente (4);
	
	private int codPerfil;
	
	private Perfil(int cod) {
		codPerfil = cod;
	}
	
	public int getCodPerfil() {
		return codPerfil;
	}
}
