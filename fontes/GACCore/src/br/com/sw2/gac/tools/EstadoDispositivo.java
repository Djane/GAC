package br.com.sw2.gac.tools;

/**
 * <b>Descrição: ENUM que indica os Status possÃ­veis dos Dispositivos</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum EstadoDispositivo {

    /** Atributo Novo. */
    Novo(1),
    /** Atributo Pronto. */
    Pronto(2),
    /** Atributo Uso. */
    Uso(3),
    /** Atributo Devolvido. */
    Devolvido(4),
    /** Atributo Manutencao. */
    Manutencao(5),
    /** Atributo Defeito. */
    Defeito(6),
    /** Atributo Descarte. */
    Descarte(7),
    /** Atributo Fabrica. */
    Fabrica(8);

    /** Atributo estado disp. */
    private int estadoDisp;

    /**
     * Construtor Padrao Instancia um novo objeto EstadoDispositivo.
     * @param cod the cod
     */
    private EstadoDispositivo(int cod) {
        estadoDisp = cod;
    }

    /**
     * Nome: getEstadoDisp Recupera o valor do atributo 'estadoDisp'.
     * @return valor do atributo 'estadoDisp'
     * @see
     */
    public int getEstadoDisp() {
        return estadoDisp;
    }
}
