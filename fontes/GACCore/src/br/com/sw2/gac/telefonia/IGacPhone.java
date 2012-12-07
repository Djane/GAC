package br.com.sw2.gac.telefonia;

import java.util.Date;

/**
 * <b>Descrição: Interface que define os métodos a serem implementados pela URA ao receber uma ligação.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public interface IGacPhone {

    /**
     * Nome: inserirligacaoNaFilaGAC
     * Insere a ligação recebida pela URA na fila (Ocorrencias) do GAC.
     *
     * @param numeroTelefone the numero telefone
     * @param snDispositivo the sn dispositivo
     * @param dataRecebimentoLigacao the data recebimento ligacao
     * @param codPrioridade the cod prioridade
     * @param tipoLigacao the tipo ligacao
     * @return integer
     * @see
     */
    Integer inserirligacaoNaFilaGAC(String numeroTelefone, Integer snDispositivo,
        Date dataRecebimentoLigacao, Integer codPrioridade, Integer tipoLigacao);

    /**
     * Nome: avisoDeLigacao Aviso ligacao.
     *
     * @param idOcorrencia ID da ocorrência gerado no momento em que a ligação chegou na URA.
     * @see
     */
    void avisoDeLigacao(Integer idOcorrencia);
}
