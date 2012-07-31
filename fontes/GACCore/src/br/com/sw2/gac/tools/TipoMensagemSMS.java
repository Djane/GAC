package br.com.sw2.gac.tools;

/**
 * <b>Descrição: ENUM que determina os tipos de Mensagens de SMS possÃ­veis.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoMensagemSMS {

    /** Atributo Atendimento efetuado. */
    AtendimentoEfetuado(1, "Atendimento Efetuado"),

    /** Atributo Sinal emergencia. */
    SinalEmergencia(2, "Aviso de EmergÃªncia"),

    /** Atributo Aviso pagamento. */
    AvisoPagamento(3, "Aviso de Pagamento"),

    /** Atributo Aviso festivo. */
    AvisoFestivo(4, "Mensagem Festiva");

    /** Atributo cod tipo sms. */
    private int codTipoSMS;

    /** Atributo des tipo sms. */
    private String desTipoSMS;

    /**
     * Construtor Padrao Instancia um novo objeto TipoMensagemSMS.
     * @param cod the cod
     * @param descr the descr
     */
    private TipoMensagemSMS(int cod, String descr) {
        codTipoSMS = cod;
        desTipoSMS = descr;
    }

    /**
     * Nome: getCodTipoSMS Recupera o valor do atributo 'codTipoSMS'.
     * @return valor do atributo 'codTipoSMS'
     * @see
     */
    public int getCodTipoSMS() {
        return codTipoSMS;
    }

    /**
     * Nome: getDesTipoSMS Recupera o valor do atributo 'desTipoSMS'.
     * @return valor do atributo 'desTipoSMS'
     * @see
     */
    public String getDesTipoSMS() {
        return desTipoSMS;
    }
}
