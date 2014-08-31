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
     * Inserirligacao na fila gac.
     *
     * @param numeroTelefone the numero telefone
     * @param snDispositivo the sn dispositivo
     * @param dataRecebimentoLigacao the data recebimento ligacao
     * @param codPrioridade the cod prioridade
     * @param tipoOcorrencia the tipo ocorrencia
     * @param usuario the usuario
     * @param script the script
     * @param idLigacao the id ligacao
     * @return integer
     * @see
     */
    Integer inserirligacaoNaFilaGAC(String numeroTelefone, Integer snDispositivo,
        Date dataRecebimentoLigacao, Integer codPrioridade, Integer tipoOcorrencia,
        String usuario, Integer script, Integer idLigacao);

    /**
     * Nome: avisoDeLigacao Aviso ligacao.
     *
     * @param idOcorrencia ID da ocorrência gerado no momento em que a ligação chegou na URA.
     * @see
     */
    void avisoDeLigacao(Integer idOcorrencia);
}
