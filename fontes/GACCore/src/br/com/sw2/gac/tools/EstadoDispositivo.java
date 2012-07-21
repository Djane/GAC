package br.com.sw2.gac.tools;

/**
 * ENUM que indica os Status possíveis dos Dispositivos
 * 
 * @author marcelo
 *
 */
public enum EstadoDispositivo {
	Novo (1),
	Pronto (2),
	Uso (3),
	Devolvido (4),
	Manutencao (5),
	Defeito (6),
	Descarte (7),
	Fabrica (8);
	
	private int estadoDisp;
	
	private EstadoDispositivo(int cod) {
		estadoDisp = cod;
	}
	
	public int getEstadoDisp() {
		return estadoDisp;
	}
}
