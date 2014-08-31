package br.com.sw2.gac.vo;


/**
 * <b>Descrição: Classe que representa a tabela de motivos de pausa do Intelix</b> <br>.
 *
 * @author: SW2
 * @version 1.0
 *
 * Copyright 2013 SmartAngel.
 */
public class MotivoPausaVO {

    /** Atributo motivo pausa id. */
    private Integer motivoPausaId;

    /** Atributo nome. */
    private String descricaoMotivoPausa;

    /** Atributo produtivo. */
    private Integer produtivo;

    /** Atributo perfil configuracao id. */
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
     * Nome: getDescricaoMotivoPausa
     * Recupera o valor do atributo 'descricaoMotivoPausa'.
     *
     * @return valor do atributo 'descricaoMotivoPausa'
     * @see
     */
    public String getDescricaoMotivoPausa() {
        return descricaoMotivoPausa;
    }

    /**
     * Nome: setDescricaoMotivoPausa
     * Registra o valor do atributo 'descricaoMotivoPausa'.
     *
     * @param descricaoMotivoPausa valor do atributo descricao motivo pausa
     * @see
     */
    public void setDescricaoMotivoPausa(String descricaoMotivoPausa) {
        this.descricaoMotivoPausa = descricaoMotivoPausa;
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
