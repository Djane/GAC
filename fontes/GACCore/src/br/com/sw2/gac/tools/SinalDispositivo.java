package br.com.sw2.gac.tools;

/**
 * <b>DescriÁ„o: ENUM respons√°vel para identificar os sinais provenientes do Dispositivo.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum SinalDispositivo {

    /** Atributo Convulsao. */
    Convulsao(1, "Convuls√£o"),

    /** Atributo Imobilidade. */
    Imobilidade(2, "Imobilidade"),

    /** Atributo Queda. */
    Queda(3, "Queda"),

    /** Atributo Sem pulseira. */
    SemPulseira(4, "Sem Pulseira");

    /** Atributo cod sinal. */
    private int codSinal;

    /** Atributo des sinal. */
    private String desSinal;

    /**
     * Construtor Padrao Instancia um novo objeto SinalDispositivo.
     * @param cod the cod
     * @param descr the descr
     */
    private SinalDispositivo(int cod, String descr) {
        codSinal = cod;
        desSinal = descr;
    }

    /**
     * Nome: getCodSinal Recupera o valor do atributo 'codSinal'.
     * @return valor do atributo 'codSinal'
     * @see
     */
    public int getCodSinal() {
        return codSinal;
    }

    /**
     * Nome: getDesSinal Recupera o valor do atributo 'desSinal'.
     * @return valor do atributo 'desSinal'
     * @see
     */
    public String getDesSinal() {
        return desSinal;
    }
}
