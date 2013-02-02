package br.com.sw2.gac.socket;

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
     * @throws SocketCommandException Retornada  caso a ligação não seja completada.
     * @see
     */
    void iniciarLigacao(String numeroTelefone) throws SocketCommandException;


    /**
     * Nome: atenderLigacao
     * Atender uma ligação recebida e identifica pelo número da linha ou pelo id da ligação.
     *
     * @param idLigacao Id da ligação chamando.
     * @param numeroDalinha Número da linha chamando.
     * @throws SocketCommandException the socket command exception
     * @see
     */
    void atenderLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException;


    /**
     * Nome: iniciarPausaNaLigacao
     * Iniciar uma pausa (hold) na ligacao informada e identificada pelo numedo da linha ou id da ligação.
     *
     * @param idLigacao Id da ligação a ser colocada em pausa.
     * @param numeroDalinha Número da linha a ser colocada em pauda.
     * @throws SocketCommandException the socket command exception
     * @see
     */
    void iniciarPausaNaLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException;


    /**
     * Nome: removerPausaDaLigacao
     * Remover pausa da ligacao.
     *
     * @param idLigacao Id da ligação a ser tirada da pausa.
     * @param numeroDalinha Número da linha a ser colocada em tirada da pausa.
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void removerPausaDaLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException;

    /**
     * Nome: encerrarLigacao
     * Encerrar ligacao identificada atraves do id da ligação ou do número da linha.
     *
     * @param idLigacao id da ligação a ser encerrada
     * @param numeroDalinha numero da linha a encerrar a ligação.
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void encerrarLigacao(String idLigacao, int numeroDalinha) throws SocketCommandException;

    /**
     * Nome: ativarRamal
     * Ativar ramal (login).
     *
     * @param numeroRamal do ramal a ser ativado.
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void ativarRamal(String numeroRamal) throws SocketCommandException;

    /**
     * Nome: desativarRamal
     * Desativar ramal (logoff).
     *
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void desativarRamal() throws SocketCommandException;

    /**
     * Nome: logarAtendente
     * Logar atendente no ramal.
     *
     * @param codigoAgente Código do atendente
     * @param senhaAgente Senha doa tendente
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void logarAtendente(String codigoAgente, String senhaAgente) throws SocketCommandException;

    /**
     * Nome: logarAtendente
     * Logoff do atendente no ramal.
     *
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void logoffAtendente() throws SocketCommandException;

    /**
     * Nome: mudarStatusDisponibilidade
     * Mudar status do agente (atendente)para disponível ou indisponível.
     *
     * @param codigoStatus Código do status. 1 - Disponível, 2 - Indisponível.
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void mudarStatusDisponibilidade(int codigoStatus) throws SocketCommandException;

    /**
     * Nome: breakAtendente
     * Coloca ou retira o atendente do estado de Break.
     *
     * @param estado True indica que o atendente entrará em break. False indicará que o atendente sairá do estado de break.
     * @throws SocketCommandException O comando não pode ser executado.
     * @see
     */
    void breakAtendente(int estado) throws SocketCommandException;

}
