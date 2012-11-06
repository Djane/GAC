package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * Classe que representa o VO do relatório Chamadas por Origem.
 * @author Jefferson Adami
 */
public class RelChamadasPorOrigemVO {

    private Date dia;
    private Date perIncio;
    private Date perFim;
    private Integer recebidas;
    private Integer efetProg;
    private Integer efetEmerg;
    /**
     * @return the dia
     */
    public Date getDia() {
        return dia;
    }
    /**
     * @param dia the dia to set
     */
    public void setDia(Date dia) {
        this.dia = dia;
    }
    /**
     * @return the perIncio
     */
    public Date getPerIncio() {
        return perIncio;
    }
    /**
     * @param perIncio the perIncio to set
     */
    public void setPerIncio(Date perIncio) {
        this.perIncio = perIncio;
    }
    /**
     * @return the perFim
     */
    public Date getPerFim() {
        return perFim;
    }
    /**
     * @param perFim the perFim to set
     */
    public void setPerFim(Date perFim) {
        this.perFim = perFim;
    }
    /**
     * @return the recebidas
     */
    public Integer getRecebidas() {
        return recebidas;
    }
    /**
     * @param recebidas the recebidas to set
     */
    public void setRecebidas(Integer recebidas) {
        this.recebidas = recebidas;
    }
    /**
     * @return the efetProg
     */
    public Integer getEfetProg() {
        return efetProg;
    }
    /**
     * @param efetProg the efetProg to set
     */
    public void setEfetProg(Integer efetProg) {
        this.efetProg = efetProg;
    }
    /**
     * @return the efetEmerg
     */
    public Integer getEfetEmerg() {
        return efetEmerg;
    }
    /**
     * @param efetEmerg the efetEmerg to set
     */
    public void setEfetEmerg(Integer efetEmerg) {
        this.efetEmerg = efetEmerg;
    }

}
