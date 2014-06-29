package br.com.sw2.gac.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class ParametroVO implements Serializable {

    private static final long serialVersionUID = -5777458164226753051L;
    private Integer idParametro;
    private Integer diasDados;
    private Integer diasBemEstar;
    private Integer toleraRotinaCliente;    
    private Integer horasrParaKeepAlive;    
    private String telefoneCentral1;    
    private String telefoneCentral2;    
    private String telefoneCentral3;    
    private String telefoneCentral4;    
    private String telefoneCentral5;    
    private String telefoneCentral6;    
    private String horarGSM;    
    private Date dataUltimaAlteracao;
    
    
    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(" idParametro:");
        str.append(idParametro);        
        str.append("diasDados: ");
        str.append(diasDados);
        str.append(" diasBemEstar: ");
        str.append(diasBemEstar);
        str.append("toleraRotinaCliente: ");
        str.append(toleraRotinaCliente);
        str.append(" horasrParaKeepAlive: " );
        str.append(horasrParaKeepAlive);
        str.append(" horarGSM: " );
        str.append(horarGSM);        
        str.append(" telefoneCentral1: ");
        str.append(telefoneCentral1);        
        str.append(" telefoneCentral2: ");
        str.append(telefoneCentral2);        
        str.append(" telefoneCentral3: ");
        str.append(telefoneCentral3);
        str.append(" telefoneCentral4: ");
        str.append(telefoneCentral4);
        str.append(" telefoneCentral5: ");
        str.append(telefoneCentral5);
        str.append(" telefoneCentral6: ");        
        str.append(telefoneCentral6);      
        str.append(" dataUltimaAlteracao: ");
        str.append(dataUltimaAlteracao);

        return str.toString();
    }
    
    /**
     * Nome: getIdParametro Recupera o valor do atributo 'idParametro'.
     * @return valor do atributo 'idParametro'
     * @see
     */
    public Integer getIdParametro() {
        return idParametro;
    }

    /**
     * Nome: setIdParametro Registra o valor do atributo 'idParametro'.
     * @param idParametro valor do atributo id parametro
     * @see
     */
    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    /**
     * Nome: getDiasDados Recupera o valor do atributo 'diasDados'.
     * @return valor do atributo 'diasDados'
     * @see
     */
    public Integer getDiasDados() {
        return diasDados;
    }

    /**
     * Nome: setDiasDados Registra o valor do atributo 'diasDados'.
     * @param diasDados valor do atributo dias dados
     * @see
     */
    public void setDiasDados(Integer diasDados) {
        this.diasDados = diasDados;
    }

    /**
     * Nome: getDiasBemEstar Recupera o valor do atributo 'diasBemEstar'.
     * @return valor do atributo 'diasBemEstar'
     * @see
     */
    public Integer getDiasBemEstar() {
        return diasBemEstar;
    }

    /**
     * Nome: setDiasBemEstar Registra o valor do atributo 'diasBemEstar'.
     * @param diasBemEstar valor do atributo dias bem estar
     * @see
     */
    public void setDiasBemEstar(Integer diasBemEstar) {
        this.diasBemEstar = diasBemEstar;
    }

    /**
     * Nome: getToleraRotinaCliente Recupera o valor do atributo 'toleraRotinaCliente'.
     * @return valor do atributo 'toleraRotinaCliente'
     * @see
     */
    public Integer getToleraRotinaCliente() {
        return toleraRotinaCliente;
    }

    /**
     * Nome: setToleraRotinaCliente Registra o valor do atributo 'toleraRotinaCliente'.
     * @param toleraRotinaCliente valor do atributo tolera rotina cliente
     * @see
     */
    public void setToleraRotinaCliente(Integer toleraRotinaCliente) {
        this.toleraRotinaCliente = toleraRotinaCliente;
    }

    /**
     * Nome: getSerialversionuid Recupera o valor do atributo 'serialversionuid'.
     * @return valor do atributo 'serialversionuid'
     * @see
     */
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public Integer getHorasrParaKeepAlive() {
        return horasrParaKeepAlive;
    }

    public void setHorasrParaKeepAlive(Integer horasrParaKeepAlive) {
        this.horasrParaKeepAlive = horasrParaKeepAlive;
    }

    public String getTelefoneCentral1() {
        return telefoneCentral1;
    }

    public void setTelefoneCentral1(String telefoneCentral1) {
        this.telefoneCentral1 = telefoneCentral1;
    }

    public String getTelefoneCentral2() {
        return telefoneCentral2;
    }

    public void setTelefoneCentral2(String telefoneCentral2) {
        this.telefoneCentral2 = telefoneCentral2;
    }

    public String getTelefoneCentral3() {
        return telefoneCentral3;
    }

    public void setTelefoneCentral3(String telefoneCentral3) {
        this.telefoneCentral3 = telefoneCentral3;
    }

    public String getTelefoneCentral4() {
        return telefoneCentral4;
    }

    public void setTelefoneCentral4(String telefoneCentral4) {
        this.telefoneCentral4 = telefoneCentral4;
    }

    public String getTelefoneCentral5() {
        return telefoneCentral5;
    }

    public void setTelefoneCentral5(String telefoneCentral5) {
        this.telefoneCentral5 = telefoneCentral5;
    }

    public String getTelefoneCentral6() {
        return telefoneCentral6;
    }

    public void setTelefoneCentral6(String telefoneCentral6) {
        this.telefoneCentral6 = telefoneCentral6;
    }

    public String getHorarGSM() {
        return horarGSM;
    }

    public void setHorarGSM(String horarGSM) {
        this.horarGSM = horarGSM;
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dataUltimaAlteracao) {
        this.dataUltimaAlteracao = dataUltimaAlteracao;
    }    
}
