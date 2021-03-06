package br.com.sw2.gac.socket;

import br.com.sw2.gac.socket.bean.Event;
import br.com.sw2.gac.socket.exception.SocketException;

/**
 * <b>Descrição:Interface que define os comandos utilizados na comunicação com o Softphone.</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public interface SocketCommandInterface {


    /**
     * Nome: iniciarLigacao
     * Inicia a discagem para um numero de telefone espeficificado.
     *
     * @param numeroTelefone Numero do telefone a ser discado.
     * @return Event evento de resposta da discagem.
     * @throws SocketException Retornada  caso a ligação não seja completada.
     * @see
     */
    Event iniciarLigacao(String numeroTelefone) throws SocketException;


    /**
     * Nome: atenderLigacao
     * Atender uma ligação recebida e identifica pelo número da linha ou pelo id da ligação.
     *
     * @param idLigacao Id da ligação chamando.
     * @param numeroDalinha Número da linha chamando.
     * @throws SocketException the socket command exception
     * @see
     */
    void atenderLigacao(String idLigacao, int numeroDalinha) throws SocketException;


    /**
     * Nome: iniciarPausaNaLigacao
     * Iniciar uma pausa (hold) na ligacao informada e identificada pelo numedo da linha ou id da ligação.
     *
     * @param idLigacao Id da ligação a ser colocada em pausa.
     * @param numeroDalinha Número da linha a ser colocada em pauda.
     * @throws SocketException the socket command exception
     * @see
     */
    void iniciarPausaNaLigacao(String idLigacao, int numeroDalinha) throws SocketException;


    /**
     * Nome: removerPausaDaLigacao
     * Remover pausa da ligacao.
     *
     * @param idLigacao Id da ligação a ser tirada da pausa.
     * @param numeroDalinha Número da linha a ser colocada em tirada da pausa.
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void removerPausaDaLigacao(String idLigacao, int numeroDalinha) throws SocketException;

    /**
     * Nome: encerrarLigacao
     * Encerrar ligacao identificada atraves do id da ligação ou do número da linha.
     *
     * @param idLigacao id da ligação a ser encerrada
     * @param numeroDalinha numero da linha a encerrar a ligação.
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void encerrarLigacao(String idLigacao, int numeroDalinha) throws SocketException;

    /**
     * Nome: ativarRamal
     * Ativar ramal (login).
     *
     * @param numeroRamal do ramal a ser ativado.
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void ativarRamal(String numeroRamal) throws SocketException;

    /**
     * Nome: desativarRamal
     * Desativar ramal (logoff).
     *
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void desativarRamal() throws SocketException;

    /**
     * Nome: logarAtendente
     * Logar atendente no ramal.
     *
     * @param codigoAgente Código do atendente
     * @param senhaAgente Senha doa tendente
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void logarAtendente(String codigoAgente, String senhaAgente) throws SocketException;

    /**
     * Nome: logarAtendente
     * Logoff do atendente no ramal.
     *
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void logoffAtendente() throws SocketException;

    /**
     * Nome: mudarStatusDisponibilidade
     * Mudar status do agente (atendente)para disponível ou indisponível.
     *
     * @param codigoStatus Código do status. 1 - Disponível, 2 - Indisponível.
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void mudarStatusDisponibilidade(int codigoStatus) throws SocketException;

    /**
     * Nome: breakAtendente
     * Coloca ou retira o atendente do estado de Break.
     *
     * @param estado True indica que o atendente entrará em break. False indicará que o atendente sairá do estado de break.
     * @throws SocketException O comando não pode ser executado.
     * @see
     */
    void breakAtendente(int estado) throws SocketException;

}
