package br.com.sw2.gac.jasper.chart;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

/**
 * <b>Descrição: Classes responsável pela customização dos labels do gráfico de pizza do relatório
 * de desempenho comercial.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PieLabelGeneratorDesempenhoComercial implements JRChartCustomizer {

    /**
     * {@inheritDoc}
     */
    public void customize(JFreeChart jFreeChart, JRChart jrChart) {

        //Valor em porcentagem, onde  0.20 equivale a 20%
        final double tamanhoLabel = 0.20;
        PiePlot piePlot = (PiePlot) jFreeChart.getPlot();
        //Recupera a coluna referente a quantidade de clientes
        StandardPieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{1}");
        //Permite que o numero não quebre para uma nova linha
        piePlot.setMaximumLabelWidth(tamanhoLabel);
        piePlot.setLabelGenerator(labelGenerator);

    }
}
