package br.com.yrsoares.analises;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Paint;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Grafico extends ApplicationFrame {

	Integer width = 1100, height = 600;
	final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

	public Grafico(final String title, CategoryDataset dataset, int qtdUsuarios) throws IOException {

		super(title);

		final JFreeChart chart = createChart(dataset, qtdUsuarios);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(800, 270));
		setContentPane(chartPanel);

		ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
		ChartUtilities.saveChartAsPNG(new File("resultados" + qtdUsuarios + "alunos.png"), chart, width, height, info);

	}

	private JFreeChart createChart(final CategoryDataset dataset, int qtdUsuarios) throws IOException {

		// create the chart...
		final JFreeChart chart = ChartFactory.createBarChart(null, // chart title
				"Categoria", // domain axis label
				"Valores", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips?
				false // URLs?
		);

		final CategoryPlot plot = chart.getCategoryPlot();
		final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		final DecimalFormat format = new DecimalFormat("#0.##");
		//final DecimalFormat format = new DecimalFormat("#,##0.00%");
		
		

		renderer.setDrawBarOutline(false);
	
		// cria o de formatação para o label do item
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", format));
		// torna o label visível para cada item
		renderer.setBaseItemLabelsVisible(true);
			
		
		// cores dos labels
		renderer.setSeriesPaint(0, Color.black);
		renderer.setSeriesPaint(1, Color.blue);
		renderer.setSeriesPaint(2, Color.red);
		renderer.setSeriesPaint(3, Color.yellow);
		renderer.setSeriesPaint(4, Color.green);
		
		//define intervalo do eixo y
		ValueAxis eixoY = plot.getRangeAxis();  
        eixoY.setRange(0, 0.4);
		
        /*
		//linha de meta
		double meta = 0.6;
		ValueMarker valueMarker = new ValueMarker(meta, Color.black, new BasicStroke(4));
        plot.addRangeMarker(valueMarker);
		*/		
		return chart;

	}
}