package br.com.sw2.gac.tools;

/**
 * Enum que determina a localização dos Dispositivos
 * 
 * @author marcelo
 *
 */
public enum LocalizacaoDispositivo {
	EstoqueInterno (1, "Estoque das Dependências da SmartAngel"),
	EstoqueExterno (2,"Pertence a SmartAngel, mas está fora das dependências"),
	Transito (3,"Em Trânsito"),
	Manutencao (4,"Em Manutenção na Fábrica"),
	ManutencaoTerceiros (5,"Em Manutenção em uma Assitência"),
	EmUso (6, "Em uso com o paciente"),
	Extravio (7,"Extraviada"),
	Descarte (8,"Descartada");
	
	private int codLocal;
	private String descLocal;
	
	private LocalizacaoDispositivo(int cod, String local) {
		codLocal = cod;
		descLocal = local;
	}
	
	public int getCodLocal() {
		return codLocal;
	}
	
	public String getDescLocal() {
		return descLocal;
	}
}
