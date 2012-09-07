package br.com.sw2.gac.jasper;

import java.io.InputStream;
import java.util.Collection;
import java.util.Vector;

import br.com.sw2.gac.util.ClassLoaderUtils;

import com.thoughtworks.xstream.XStream;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public abstract class JasperBeanDataSource {

    /**
     * Nome: getDados Recupera o valor do atributo 'dados'.
     * @return valor do atributo 'dados'
     * @see
     */
    public static Collection<?> createBeanCollection() {

        XStream xstream = new XStream();
        InputStream inputStream = ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream(
                "dispositivoEstadoMock.xml");
        Vector v = (Vector) xstream.fromXML(inputStream);

        return v;
        /*
         * Vector v = new Vector(); DispositivoEstadoVO dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Novo");
         * dispositivo.setQuantidade(30); dispositivo.setPorcentagem(new BigDecimal(30));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Manutenção");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Pronto");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Defeito");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Em uso");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Descarte");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Devolvido");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Central"); dispositivo.setEstado("Fábrica");
         * dispositivo.setQuantidade(10); dispositivo.setPorcentagem(new BigDecimal(10));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Novo");
         * dispositivo.setQuantidade(62); dispositivo.setPorcentagem(new BigDecimal(30.54));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Manutenção");
         * dispositivo.setQuantidade(5); dispositivo.setPorcentagem(new BigDecimal(2.46));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Pronto");
         * dispositivo.setQuantidade(36); dispositivo.setPorcentagem(new BigDecimal(17.73));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Defeito");
         * dispositivo.setQuantidade(2); dispositivo.setPorcentagem(new BigDecimal(0.99));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Em uso");
         * dispositivo.setQuantidade(87); dispositivo.setPorcentagem(new BigDecimal(42.86));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Descarte");
         * dispositivo.setQuantidade(2); dispositivo.setPorcentagem(new BigDecimal(0.99));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Devolvido");
         * dispositivo.setQuantidade(4); dispositivo.setPorcentagem(new BigDecimal(1.97));
         * v.add(dispositivo); dispositivo = new DispositivoEstadoVO();
         * dispositivo.setTipo("Pulseira"); dispositivo.setEstado("Fábrica");
         * dispositivo.setQuantidade(5); dispositivo.setPorcentagem(new BigDecimal(1.97));
         * v.add(dispositivo);
         */
        // return v;

    }

}
