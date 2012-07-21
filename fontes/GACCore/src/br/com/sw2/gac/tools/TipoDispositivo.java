package br.com.sw2.gac.tools;

/**
 * ENUM que define os Tipos de Dispositivos que podem ser utilizados na Aplicação
 * 
 * @author marcelo
 *
 */
public enum TipoDispositivo {
	Pulseira (1),
	CentralEletronica (2),
	Relogio (3),
	Pingente (4);
	
	private int codTipoDispositivo;
	
	private TipoDispositivo(int cod) {
		codTipoDispositivo = cod;
	}
	
	public int getCodTipoDispositivo() {
		return codTipoDispositivo;
	}
	
}
