package br.com.sw2.gac.tools;

/**
 * <b>Descrição: ENUM que define os Tipos de Dispositivos que podem ser utilizados na
 * AplicaÃ§Ã£o.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum TipoDispositivo {

    /** Atributo Pulseira. */
    Pulseira(1),

    /** Atributo Central eletronica. */
    CentralEletronica(2),

    /** Atributo Relogio. */
    Relogio(3),

    /** Atributo Pingente. */
    Pingente(4);

    /** Atributo cod tipo dispositivo. */
    private int codTipoDispositivo;

    /**
     * Construtor Padrao Instancia um novo objeto TipoDispositivo.
     * @param cod the cod
     */
    private TipoDispositivo(int cod) {
        codTipoDispositivo = cod;
    }

    /**
     * Nome: getCodTipoDispositivo Recupera o valor do atributo 'codTipoDispositivo'.
     * @return valor do atributo 'codTipoDispositivo'
     * @see
     */
    public int getCodTipoDispositivo() {
        return codTipoDispositivo;
    }

}
