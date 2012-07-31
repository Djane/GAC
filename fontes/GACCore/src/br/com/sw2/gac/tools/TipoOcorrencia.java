package br.com.sw2.gac.tools;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoOcorrencia {

    /** Atributo Emergencia. */
    Emergencia("AT1", "Atendimento de EmergÃªncia"),

    /** Atributo Atendimento realizado. */
    AtendimentoRealizado("AT2", "Atendimento Realizado"),

    /** Atributo Comercial. */
    Comercial("CO1", "Ã�rea Comercial"),

    /** Atributo Tecnica. */
    Tecnica("TE1", "Ã�rea TÃ©cnica"),

    /** Atributo Financeira. */
    Financeira("FI1", "Ã�rea Financeira");

    /** Atributo cod tipo ocorrencia. */
    private String codTipoOcorrencia;

    /** Atributo des tipo ocorrencia. */
    private String desTipoOcorrencia;

    /**
     * Construtor Padrao Instancia um novo objeto TipoOcorrencia.
     * @param cod the cod
     * @param descr the descr
     */
    private TipoOcorrencia(String cod, String descr) {
        codTipoOcorrencia = cod;
        desTipoOcorrencia = descr;
    }

    /**
     * Nome: getCodTipoOcorrencia Recupera o valor do atributo 'codTipoOcorrencia'.
     * @return valor do atributo 'codTipoOcorrencia'
     * @see
     */
    public String getCodTipoOcorrencia() {
        return codTipoOcorrencia;
    }

    /**
     * Nome: getDesTipoOcorrencia Recupera o valor do atributo 'desTipoOcorrencia'.
     * @return valor do atributo 'desTipoOcorrencia'
     * @see
     */
    public String getDesTipoOcorrencia() {
        return desTipoOcorrencia;
    }
}
