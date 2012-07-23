package br.com.sw2.gac.tools;

public enum GrauRelacao {
    Amigo("1"),
    Filho("2"),
    Irmão("3"),
    Mae("4"),
    Pai("5"),
    Tio("6"),
    Outros("7");
                            
    private String value;
    
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private GrauRelacao(String value) {
        this.value = value;
    }

 }
