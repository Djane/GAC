package br.com.sw2.gac.vo;

import java.io.Serializable;

/**
 * @author Daniel Castilho
 */
public class ParametroVO implements Serializable {

    private static final long serialVersionUID = -5777458164226753051L;
    
    private Integer idParametro;
    private Integer diasDados;
    private Integer diasBemEstar;
    private Integer toleraRotinaCliente;
    
    public Integer getIdParametro() {
        return idParametro;
    }
    
    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }
    
    public Integer getDiasDados() {
        return diasDados;
    }
    
    public void setDiasDados(Integer diasDados) {
        this.diasDados = diasDados;
    }
    
    public Integer getDiasBemEstar() {
        return diasBemEstar;
    }
    
    public void setDiasBemEstar(Integer diasBemEstar) {
        this.diasBemEstar = diasBemEstar;
    }
    
    public Integer getToleraRotinaCliente() {
        return toleraRotinaCliente;
    }
    
    public void setToleraRotinaCliente(Integer toleraRotinaCliente) {
        this.toleraRotinaCliente = toleraRotinaCliente;
    }
    
    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
