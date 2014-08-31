package br.com.sw2.gac.modelo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <b>Descrição: Classe que representa a tabela de motivos de pausa do Intelix</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
@Entity
@Table(name = "MotivoPausa")
public class MotivoPausa {

    /** Atributo motivo pausa id. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column
    private Integer motivoPausaId; // chave

    /** Atributo nome. */
    @Column
    private String nome;

    /** Atributo produtivo. */
    @Column
    private Integer produtivo;

    /** Atributo perfil configuracao id. */
    @Column
    private Integer perfilConfiguracaoId;

    /**
     * Nome: getMotivoPausaId Recupera o valor do atributo 'motivoPausaId'.
     * @return valor do atributo 'motivoPausaId'
     * @see
     */
    public Integer getMotivoPausaId() {
        return motivoPausaId;
    }

    /**
     * Nome: setMotivoPausaId Registra o valor do atributo 'motivoPausaId'.
     * @param motivoPausaId valor do atributo motivo pausa id
     * @see
     */
    public void setMotivoPausaId(Integer motivoPausaId) {
        this.motivoPausaId = motivoPausaId;
    }

    /**
     * Nome: getNome Recupera o valor do atributo 'nome'.
     * @return valor do atributo 'nome'
     * @see
     */
    public String getNome() {
        return nome;
    }

    /**
     * Nome: setNome Registra o valor do atributo 'nome'.
     * @param nome valor do atributo nome
     * @see
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Nome: getProdutivo Recupera o valor do atributo 'produtivo'.
     * @return valor do atributo 'produtivo'
     * @see
     */
    public Integer getProdutivo() {
        return produtivo;
    }

    /**
     * Nome: setProdutivo Registra o valor do atributo 'produtivo'.
     * @param produtivo valor do atributo produtivo
     * @see
     */
    public void setProdutivo(Integer produtivo) {
        this.produtivo = produtivo;
    }

    /**
     * Nome: getPerfilConfiguracaoId Recupera o valor do atributo 'perfilConfiguracaoId'.
     * @return valor do atributo 'perfilConfiguracaoId'
     * @see
     */
    public Integer getPerfilConfiguracaoId() {
        return perfilConfiguracaoId;
    }

    /**
     * Nome: setPerfilConfiguracaoId Registra o valor do atributo 'perfilConfiguracaoId'.
     * @param perfilConfiguracaoId valor do atributo perfil configuracao id
     * @see
     */
    public void setPerfilConfiguracaoId(Integer perfilConfiguracaoId) {
        this.perfilConfiguracaoId = perfilConfiguracaoId;
    }

}
