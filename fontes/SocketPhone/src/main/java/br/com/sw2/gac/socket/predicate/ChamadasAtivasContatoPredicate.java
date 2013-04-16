package br.com.sw2.gac.socket.predicate;

import org.apache.commons.collections.Predicate;

import br.com.sw2.gac.socket.bean.Line;
import br.com.sw2.gac.socket.constants.StatusLigacao;
import br.com.sw2.gac.socket.constants.TipoLigacao;

/**
 * <b>Descrição: Predicate para filtragem de chamadas ativas (Status 2 ou 3 ) na lista de chamadas do socketPhone. </b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class ChamadasAtivasContatoPredicate implements Predicate {

    /** Atributo status ligacao. */
    private Integer statusLigacao;

    /* (non-Javadoc)
     * @see org.apache.commons.collections.Predicate#evaluate(java.lang.Object)
     */
    @Override
    public boolean evaluate(Object object) {
        boolean retorno = false;
        if (object instanceof Line) {
            Line linha = (Line) object;
            if (linha.getTipoLigacao().intValue() == TipoLigacao.COM_CONTATO.getValue().intValue()
                && (linha.getStatusLinha().intValue() == StatusLigacao.FALANDO.getValue()
                    .intValue() || linha.getStatusLinha().intValue() == StatusLigacao.PAUSA
                    .getValue().intValue())) {
                retorno = true;
            }
        }
        return retorno;

    }

    /**
     * Nome: getStatusLigacao
     * Recupera o valor do atributo 'statusLigacao'.
     *
     * @return valor do atributo 'statusLigacao'
     * @see
     */
    public Integer getStatusLigacao() {
        return statusLigacao;
    }

    /**
     * Nome: setStatusLigacao
     * Registra o valor do atributo 'statusLigacao'.
     *
     * @param statusLigacao valor do atributo status ligacao
     * @see
     */
    public void setStatusLigacao(Integer statusLigacao) {
        this.statusLigacao = statusLigacao;
    }
}
