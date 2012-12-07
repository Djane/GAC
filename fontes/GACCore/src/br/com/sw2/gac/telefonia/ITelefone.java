package br.com.sw2.gac.telefonia;

/**
 * <b>Descrição: Interface que define os métodos a serem implementados por quem for manipular uma
 * ligação dentro do GAC (Tela de Atendimento). </b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public interface ITelefone {

    /**
     * Nome: atenderLigacao
     * Atender ligacao e associa ao usuário que está atendendo.
     *
     * @param usuario the usuario
     * @see
     */
    void atenderLigacao(String usuario);

    /**
     * Nome: colocarLigacaoEmEspera
     * Colocar ligacao em espera.
     *
     * @see
     */
    void colocarLigacaoEmEspera();

    /**
     * Nome: removerLigacaoEmEspera
     * Retira a ligação em espera, voltando ao atendimento.
     *
     * @see
     */
    void removerLigacaoEmEspera();

    /**
     * Nome: desligarLigacao
     * Desligar ligacao.
     *
     * @see
     */
    void desligarLigacao();

    /**
     * Nome: efetuarLigcao
     * Efetuar uma ligacao para o numero informado.
     *
     * @param numero the numero
     * @see
     */
    void efetuarLigacao(String numero);

}
