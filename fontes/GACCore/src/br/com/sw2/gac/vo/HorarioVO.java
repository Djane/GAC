package br.com.sw2.gac.vo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import br.com.sw2.gac.util.DateUtil;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class HorarioVO extends BaseVO {

    /** Atributo horario. */
    private Date horario;

    /** Atributo Hora minuto. */
    private String horaMinuto;

    /**
     * Construtor Padrao Instancia um novo objeto HorarioVO baseado em uma string no formato hh:mm.
     * @param str the str
     */
    public HorarioVO(String str) {
        Calendar calendar = DateUtil.stringToTime(str);
        this.horario = calendar.getTime();
    }

    /**
     * Construtor Padrao Instancia um novo objeto HorarioVO.
     */
    public HorarioVO() {
        super();
    }

    /**
     * Nome: getHorario Recupera o valor do atributo 'horario'.
     * @return valor do atributo 'horario'
     * @see
     */
    public Date getHorario() {
        return horario;
    }

    /**
     * Nome: setHorario Registra o valor do atributo 'horario'.
     * @param horario valor do atributo horario
     * @see
     */
    public void setHorario(Date horario) {
        this.horario = horario;
    }

    /**
     * Nome: getHoraMinuto Recupera o valor do atributo 'horaMinuto'.
     * @return valor do atributo 'horaMinuto'
     * @see
     */
    public String getHoraMinuto() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        horaMinuto = format.format(this.horario);
        return horaMinuto;
    }

    /**
     * Nome: setHoraMinuto Registra o valor do atributo 'horaMinuto'.
     * @param horaMinuto valor do atributo hora minuto
     * @see
     */
    public void setHoraMinuto(String horaMinuto) {
        Calendar calendar = DateUtil.stringToTime(horaMinuto);
        this.horario = calendar.getTime();
    }

}
