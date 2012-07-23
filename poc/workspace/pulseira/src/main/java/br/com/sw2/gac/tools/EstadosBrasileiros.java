package br.com.sw2.gac.tools;

public enum EstadosBrasileiros {
    AC("Acre"), 
    AL("Alagoas"), 
    AP("Amapá"), 
    AM("Amazonas"), 
    BA("Bahia"), 
    CE("Ceará"), 
    DF("Distrito Federal"), 
    ES("Espírito Santo"), 
    GO("Goiás"), 
    MA("Maranhão"), 
    MT("Mato Grosso"), 
    MS("Mato Grosso do Sul"), 
    MG("Minas Gerais"), 
    PA("Pará"), 
    PB("Paraíba"), 
    PR("Paraná"), 
    PE("Pernambuco"), 
    PI("Piauí"), 
    RR("Roraima"), 
    RO("Rondônia"), 
    RJ("Rio de Janeiro"), 
    RN("Rio Grande do Norte"), 
    RS("Rio Grande do Sul"), 
    SC("Santa Catarina"), 
    SP("São Paulo"), 
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
