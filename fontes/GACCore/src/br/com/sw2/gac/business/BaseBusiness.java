package br.com.sw2.gac.business;

import java.math.BigDecimal;

import br.com.sw2.gac.util.LoggerUtils;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class BaseBusiness {

    /** Atributo logger. */
    protected LoggerUtils logger = LoggerUtils.getInstance(getClass());

    /**
     * Nome: calcularPorcentagemItens Calcular porcentagem itens.
     * @param itens the itens
     * @param totalDeItens the total de itens
     * @return big decimal
     * @see
     */
    public static BigDecimal calcularPorcentagem(double itens, double totalDeItens) {
        final int tetoPorcentagem = 100;
        double porcentagem = (itens * tetoPorcentagem) / totalDeItens;
        return new BigDecimal(porcentagem).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * Nome: getLogger Recupera o valor do atributo 'logger'.
     * @return valor do atributo 'logger'
     * @see
     */
    public LoggerUtils getLogger() {
        return logger;
    }

    /**
     * Nome: setLogger Registra o valor do atributo 'logger'.
     * @param logger valor do atributo logger
     * @see
     */
    public void setLogger(LoggerUtils logger) {
        this.logger = logger;
    }

}
