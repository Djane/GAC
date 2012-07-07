package br.com.sw2.gac.tools;

public enum Perfil {
	Gerencial (1),
	Backoffice (2),
	Usuario (3);
	
	private int codPerfil;
	
	private Perfil(int cod) {
		codPerfil = cod;
	}
	
	public int getCodPerfil() {
		return codPerfil;
	}
}
