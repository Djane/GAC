package br.com.sw2.gac.vo;

import java.util.Date;

/**
 * Classe que representa o VO do relatório Chamadas por Origem.
 * @author Jefferson Adami
 */
public class RelChamadasPorOrigemVO {

    private Date dia;
    private Date perInicio;
    private Date perFim;
    private Integer recebidas;
    private Integer efetProgr;
    private Integer efetEmerg;

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Date getPerInicio() {
        return perInicio;
    }

    public void setPerInicio(Date perInicio) {
        this.perInicio = perInicio;
    }

    public Date getPerFim() {
        return perFim;
    }

    public void setPerFim(Date perFim) {
        this.perFim = perFim;
    }

    public Integer getRecebidas() {
        return recebidas;
    }

    public void setRecebidas(Integer recebidas) {
        this.recebidas = recebidas;
    }

    public Integer getEfetProgr() {
        return efetProgr;
    }

    public void setEfetProgr(Integer efetProgr) {
        this.efetProgr = efetProgr;
    }

    public Integer getEfetEmerg() {
        return efetEmerg;
    }

    public void setEfetEmerg(Integer efetEmerg) {
        this.efetEmerg = efetEmerg;
    }

}
