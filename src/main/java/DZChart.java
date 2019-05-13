import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DZChart {

	/**
	 * (r0, x0, p)
	 */
	private double x0 = 37.92;
	private double r0 = 5.55;
	private double p = 38.321;

	private double x1Ust = 69;
	private double alfUst = 25*Math.PI/180;
	private double bettUst = 15*Math.PI/180;
	private double gamUst = 70*Math.PI/180;;
	private double b1Ust = 38;

	/**
	 *
	 */
	private static DZChart dztc;
	private XYSeries tripPointP = new XYSeries("TripPointP (Om)");
	private XYSeries tripPointN = new XYSeries("TripPointN (Om)");
	private XYSeries resistance = new XYSeries("{R,X} (Om)");
	private XYSeries tripSeries = new XYSeries("Trip");
	private double samples = 80;
	private int samplesBuffer = (int) (80/samples);
	private int sum=0;

//"deprecation"
	public DZChart() {
		JFrame frame = new JFrame();
		JFreeChart chart = createCombinedChart();
		ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
		panel.setPreferredSize(new java.awt.Dimension(500, 270));
		frame.getContentPane().add(new ChartPanel(chart)); frame.setSize(800,1000); frame.setContentPane(panel);
		panel.setLayout(null);
		frame.show(); frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		double x1, x2,x3,x4;
		for(double r=-100;r<100; r+=0.5){
			x1 = x1Ust;

			x2 = Math.tan(gamUst)*(r-b1Ust);
			if (-10<=x2&x2<=x1Ust){
				resistance.add(r,x2);
			}
			x3 = Math.tan(alfUst+90)*r;
			if (0<=x3&x3<=x1Ust){
				resistance.add(r,x3);
			}
			x4 = -Math.tan(bettUst)*r;
			if (x4<=0&x4>=-10){
				resistance.add(r,x4);
			}
			resistance.add(r,x1);



		}
	}

	private JFreeChart createCombinedChart() {

		XYSeriesCollection data = new XYSeriesCollection(); data.addSeries(tripPointP); data.addSeries(tripPointN); data.addSeries(resistance);

// XYItemRenderer renderer1 = new StandardXYItemRenderer();
		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
		renderer1.setSeriesLinesVisible(2, false);
// renderer1.setSeriesStroke(2, false);
		NumberAxis rangeAxis1 = new NumberAxis("X, Om");
		rangeAxis1.setAutoRange(true);
		XYPlot subplot1 = new XYPlot(data, null, rangeAxis1, renderer1);
		XYSeriesCollection tripData = new XYSeriesCollection(); tripData.addSeries(tripSeries);

		XYItemRenderer renderer2 = new StandardXYItemRenderer();
		NumberAxis rangeAxis2 = new NumberAxis("Trip");
		XYPlot subplot2 = new XYPlot(tripData, null, rangeAxis2, renderer2);

		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("R, Om"));

		plot.add(subplot1, 5);
		plot.add(subplot2, 1);
		plot.setOrientation(PlotOrientation.VERTICAL);
		return new JFreeChart("TripSeries", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}

	public void setData(double r, double x, int trip){
		resistance.add(r, x);
		tripSeries.add(r, trip);
	}

	public static void create(){
		dztc = new DZChart(); }
	public static DZChart get(){ return dztc; }
}