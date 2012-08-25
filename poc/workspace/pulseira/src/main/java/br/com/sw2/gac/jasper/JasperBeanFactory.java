package br.com.sw2.gac.jasper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Vector;

import br.com.sw2.gac.vo.DispositivoEstadoVO;

/**
 * A factory for creating JasperBean objects.
 */
public class JasperBeanFactory {

    /**
     * Nome: getDados Recupera o valor do atributo 'dados'.
     * @return valor do atributo 'dados'
     * @see
     */
    public static Collection createBeanCollection() {
        Vector v = new Vector();
        DispositivoEstadoVO dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Novo");
        dispositivo.setQuantidade(62);
        dispositivo.setPorcentagem(new BigDecimal(30.54));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Manutenção");
        dispositivo.setQuantidade(5);
        dispositivo.setPorcentagem(new BigDecimal(2.46));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Pronto");
        dispositivo.setQuantidade(36);
        dispositivo.setPorcentagem(new BigDecimal(17.73));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Defeito");
        dispositivo.setQuantidade(2);
        dispositivo.setPorcentagem(new BigDecimal(0.99));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Em uso");
        dispositivo.setQuantidade(87);
        dispositivo.setPorcentagem(new BigDecimal(42.86));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Descarte");
        dispositivo.setQuantidade(2);
        dispositivo.setPorcentagem(new BigDecimal(0.99));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Devolvido");
        dispositivo.setQuantidade(4);
        dispositivo.setPorcentagem(new BigDecimal(1.97));
        v.add(dispositivo);

        dispositivo = new DispositivoEstadoVO();
        dispositivo.setEstado("Fábrica");
        dispositivo.setQuantidade(5);
        dispositivo.setPorcentagem(new BigDecimal(1.97));
        v.add(dispositivo);

        return v;
    }
}
