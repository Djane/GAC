package br.com.sw2.gac.tools;

public enum EstadosBrasileiros {
    AC("Acre"), 
    AL("Alagoas"), 
    AP("Amap�"), 
    AM("Amazonas"), 
    BA("Bahia"), 
    CE("Cear�"), 
    DF("Distrito Federal"), 
    ES("Esp�rito Santo"), 
    GO("Goi�s"), 
    MA("Maranh�o"), 
    MT("Mato Grosso"), 
    MS("Mato Grosso do Sul"), 
    MG("Minas Gerais"), 
    PA("Par�"), 
    PB("Para�ba"), 
    PR("Paran�"), 
    PE("Pernambuco"), 
    PI("Piau�"), 
    RR("Roraima"), 
    RO("Rond�nia"), 
    RJ("Rio de Janeiro"), 
    RN("Rio Grande do Norte"), 
    RS("Rio Grande do Sul"), 
    SC("Santa Catarina"), 
    SP("S�o Paulo"), 
    SE("Sergipe"), 
    TO("Tocantins");               
            
    private String value;

    
    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    private EstadosBrasileiros(String value) {
        this.value = value;
    }

 }
