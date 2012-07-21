package br.com.sw2.gac.tools;

/**
 * ENUM que determina os tipos de Mensagens de SMS possíveis
 * 
 * @author marcelo
 *
 */
public enum TipoMensagemSMS {
    
	AtendimentoEfetuado (1, "Atendimento Efetuado"),
	SinalEmergencia (2, "Aviso de Emergência"),
	AvisoPagamento (3, "Aviso de Pagamento"),
	AvisoFestivo (4, "Mensagem Festiva");
	
	private int codTipoSMS;
	private String desTipoSMS;
	
	private TipoMensagemSMS (int cod, String descr) {
		codTipoSMS = cod;
		desTipoSMS = descr;
	}
	
	public int getCodTipoSMS() {
		return codTipoSMS;
	}
	
	public String getDesTipoSMS() {
		return desTipoSMS;
	}
}
