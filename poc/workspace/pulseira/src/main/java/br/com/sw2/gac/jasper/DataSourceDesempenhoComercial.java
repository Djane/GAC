package br.com.sw2.gac.jasper;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import br.com.sw2.gac.vo.ClientesAtivosVO;
import br.com.sw2.gac.vo.DesempenhoComercialVO;
import br.com.sw2.gac.vo.MovimentacaoClienteVO;

/**
 * <b>Descrição: Datasource para os dados do relatório de desempenho comercial.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class DataSourceDesempenhoComercial {

    /**
     * Nome: createBeanCollection Creates the bean collection.
     * @return collection
     * @see
     */
    public static Collection<?> createBeanCollection() {

        /*
         * XStream xstream = new XStream(); InputStream inputStream =
         * ClassLoaderUtils.getDefaultClassLoader().getResourceAsStream(
         * "desempenhoComercialMock.xml"); Vector v = (Vector) xstream.fromXML(inputStream); return
         * v;
         */

        Vector list = new Vector();
        ClientesAtivosVO ca = new ClientesAtivosVO();
        ca.setNomePacote("SLIM");
        ca.setQtdeCliente(345L);
        ca.setPorcCliente(new BigDecimal(62.27));
        list.add(ca);
        ca = new ClientesAtivosVO();
        ca.setNomePacote("PLUS");
        ca.setQtdeCliente(124L);
        ca.setPorcCliente(new BigDecimal(22.38));
        list.add(ca);
        ca = new ClientesAtivosVO();
        ca.setNomePacote("SUPER");
        ca.setQtdeCliente(85L);
        ca.setPorcCliente(new BigDecimal(15.34));
        list.add(ca);
        Vector listaMC = new Vector();
        // Movimentação
        MovimentacaoClienteVO mc = new MovimentacaoClienteVO();
        mc.setDia(1);
        mc.setEntrante(3);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(2);
        mc.setEntrante(5);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(3);
        mc.setEntrante(6);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(4);
        mc.setEntrante(7);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(5);
        mc.setEntrante(2);
        mc.setCancelado(2);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(6);
        mc.setEntrante(3);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(7);
        mc.setEntrante(5);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(8);
        mc.setEntrante(4);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(9);
        mc.setEntrante(3);
        mc.setCancelado(1);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(10);
        mc.setEntrante(4);
        mc.setCancelado(1);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(11);
        mc.setEntrante(6);
        mc.setCancelado(1);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(12);
        mc.setEntrante(7);
        mc.setCancelado(1);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(13);
        mc.setEntrante(8);
        mc.setCancelado(1);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(14);
        mc.setEntrante(10);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(15);
        mc.setEntrante(1);
        mc.setCancelado(1);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(16);
        mc.setEntrante(11);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(17);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(18);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(19);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(20);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(21);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(22);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(23);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(24);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(25);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(26);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(27);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(28);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(29);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(30);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        mc = new MovimentacaoClienteVO();
        mc.setDia(31);
        mc.setEntrante(0);
        mc.setCancelado(0);
        listaMC.add(mc);
        Vector listaDesempenho = new Vector();
        DesempenhoComercialVO dc = new DesempenhoComercialVO();
        dc.setQtdClientesInicioMes(760);
        dc.setQtdeClientesAtivos(554);
        dc.setClientesAtivos(list);
        dc.setMovimentacaoClientes(listaMC);
        dc.setDataApuracao(new Date());
        listaDesempenho.add(dc);

        return listaDesempenho;

    }
}
