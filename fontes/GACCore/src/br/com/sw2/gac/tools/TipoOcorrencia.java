package br.com.sw2.gac.tools;

/**
 * ENUM que demonstra os Tipos de Ocorrência 
 * 
 * @author marcelo
 *
 */
public enum TipoOcorrencia {
    Emergencia ("AT1", "Atendimento de Emergência"),
    AtendimentoRealizado ("AT2", "Atendimento Realizado"),
    Comercial("CO1", "Área Comercial"),
    Tecnica("TE1", "Área Técnica"),
    Financeira("FI1", "Área Financeira");
    
    private String codTipoOcorrencia;
    private String desTipoOcorrencia;
    
    private TipoOcorrencia (String cod, String descr) {
        codTipoOcorrencia = cod;
        desTipoOcorrencia = descr;
    }
    
    public String getCodTipoOcorrencia() {
        return codTipoOcorrencia;
    }
    
    public String getDesTipoOcorrencia() {
        return desTipoOcorrencia;
    }

}
