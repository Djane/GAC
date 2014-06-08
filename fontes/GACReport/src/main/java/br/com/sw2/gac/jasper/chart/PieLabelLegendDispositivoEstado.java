package br.com.sw2.gac.jasper.chart;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

/**
 * <b>Descrição: Customização dos labels e legenda do gráfico de pizza do relatorio de dispositivos
 * por estado.</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PieLabelLegendDispositivoEstado implements JRChartCustomizer {

    /**
     * {@inheritDoc}
     */
    public void customize(JFreeChart jFreeChart, JRChart jrChart) {

        PiePlot piePlot = (PiePlot) jFreeChart.getPlot();
        StandardPieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0} {2}");
        piePlot.setLabelGenerator(labelGenerator);
        piePlot.setLegendLabelGenerator(labelGenerator);

    }
}
