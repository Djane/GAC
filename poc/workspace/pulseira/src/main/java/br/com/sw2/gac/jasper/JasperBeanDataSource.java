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
public final class JasperBeanDataSource {

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
    }

    /**
     * Construtor Padrao Instancia um novo objeto JasperBeanDataSource.
     */
    private JasperBeanDataSource() {
        super();
    }

}
