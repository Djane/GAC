package br.com.sw2.gac.jasper;

import net.sf.jasperreports.charts.fill.JRFillPieDataset;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartCustomizer;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;

/**
 * <b>Descrição:</b> <br>
 * .
 * @author: SW2
 * @version 1.0 Copyright 2012 SmartAngel.
 */
public class PieLabels implements JRChartCustomizer {

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

    /**
     * Nome: innn Innn.
     * @param jFreeChart the j free chart
     * @param jrChart the jr chart
     * @see
     */
    public void innn(JFreeChart jFreeChart, JRChart jrChart) {
        JRFillPieDataset dataset = (JRFillPieDataset) jrChart.getDataset();

        /*
         * if (dataset.getLabelGenerator().get != null) { String label =
         * dataset.getLabelExpression().getText(); label = label.substring(1, label.length() - 1);
         * PiePlot piePlot = (PiePlot) jFreeChart.getPlot(); StandardPieSectionLabelGenerator
         * labelGenerator = new StandardPieSectionLabelGenerator(label);
         * piePlot.setLabelGenerator(labelGenerator);
         * piePlot.setLegendLabelGenerator(labelGenerator); }
         */
    }

}
