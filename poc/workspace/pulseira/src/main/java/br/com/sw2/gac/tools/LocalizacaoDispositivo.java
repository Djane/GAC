package br.com.sw2.gac.tools;

/**
 * <b>Descri��o: Enum que determina a localização dos Dispositivos.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public enum LocalizacaoDispositivo {

    /** Atributo Estoque interno. */
    EstoqueInterno(1, "Estoque das Dependências da SmartAngel"),

    /** Atributo Estoque externo. */
    EstoqueExterno(2, "Pertence a SmartAngel, mas está fora das dependências"),

    /** Atributo Transito. */
    Transito(3, "Em Trânsito"),

    /** Atributo Manutencao. */
    Manutencao(4, "Em Manutenção na Fábrica"),

    /** Atributo Manutencao terceiros. */
    ManutencaoTerceiros(5, "Em Manutenção em uma Assitência"),

    /** Atributo Em uso. */
    EmUso(6, "Em uso com o paciente"),

    /** Atributo Extravio. */
    Extravio(7, "Extraviada"),

    /** Atributo Descarte. */
    Descarte(8, "Descartada");

    /** Atributo cod local. */
    private int codLocal;

    /** Atributo desc local. */
    private String descLocal;

    /**
     * Construtor Padrao Instancia um novo objeto LocalizacaoDispositivo.
     * @param cod the cod
     * @param local the local
     */
    private LocalizacaoDispositivo(int cod, String local) {
        codLocal = cod;
        descLocal = local;
    }

    /**
     * Nome: getCodLocal Recupera o valor do atributo 'codLocal'.
     * @return valor do atributo 'codLocal'
     * @see
     */
    public int getCodLocal() {
        return codLocal;
    }

    /**
     * Nome: getDescLocal Recupera o valor do atributo 'descLocal'.
     * @return valor do atributo 'descLocal'
     * @see
     */
    public String getDescLocal() {
        return descLocal;
    }
}
