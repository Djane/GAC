package br.com.sw2.gac.tools;
/**
 * ENUM responsável para identificar os sinais provenientes do Dispositivo
 * 
 * @author marcelo
 *
 */
public enum SinalDispositivo {

    Convulsao (1, "Convulsão"),
    Imobilidade (2, "Imobilidade"),
    Queda (3, "Queda"),
    SemPulseira (4, "Sem Pulseira");
    
    private int codSinal;
    private String desSinal;
    
    private SinalDispositivo (int cod, String descr) {
        codSinal = cod;
        desSinal = descr;
    }
    
    public int getCodSinal() {
        return codSinal;
    }
    
    public String getDesSinal() {
        return desSinal;
    }
}
