import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ChartsForImpedance {

    private static ChartsForImpedance charts;
    private static ArrayList<XYSeriesCollection> datasetsAnalog = new ArrayList<XYSeriesCollection>();
    private static CombinedDomainXYPlot plot;
    private static JFreeChart chart;
    private static JFrame frame;
    private static XYSeries tempSeries;

    @SuppressWarnings("deprecation")
    public ChartsForImpedance(){
        plot = new CombinedDomainXYPlot(new NumberAxis("R, Ohms"));

        chart = new JFreeChart(plot);
        chart.setBorderPaint(Color.black);
        chart.setBorderVisible(true);
        chart.setBackgroundPaint(Color.white);

        frame = new JFrame("Complex Impedance");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setSize(1024,768);
        frame.show();
    }


    public static void createAnalogChart(String name, int number){
        if(charts==null) charts = new ChartsForImpedance();

        XYSeriesCollection dataset = new XYSeriesCollection();
        NumberAxis rangeAxis = new NumberAxis(name); rangeAxis.setAutoRangeIncludesZero(false);
        XYPlot subplot = new XYPlot(dataset, null, rangeAxis, new StandardXYItemRenderer() );
        subplot.setBackgroundPaint(Color.BLACK);
        plot.add(subplot);
        subplot.setWeight(7);
        datasetsAnalog.add(dataset);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesLinesVisible(0, false);
        renderer.setSeriesShapesVisible(0, true);
        renderer.setSeriesLinesVisible(1, false);
        renderer.setSeriesShapesVisible(1, true);
        subplot.setRenderer(renderer);
    }

    public static void addSeries(String name, int chartNumber, int number){
        XYSeries series = new XYSeries(name);
        datasetsAnalog.get(chartNumber).addSeries(series);
    }

    public static void addZData(int chart, int series, double R, double X){
        tempSeries = (XYSeries) datasetsAnalog.get(chart).getSeries().get(series);
        tempSeries.add(R, X);
    }

    public static void addCharacteristic(int chart, int series){
        tempSeries = (XYSeries) datasetsAnalog.get(chart).getSeries().get(series);
        double x_max = DZrelay.x_max;
        double alpha_angle = DZrelay.alpha_angle;
        double betta_angle = DZrelay.betta_angle;
        double gamma_angle = DZrelay.gamma_angle;
        double r_zero = DZrelay.r_zero;

        double x1, x2,x3,x4;
        for(double r = -51.3; r < 70.2; r+= 0.1){
            x1 = x_max;

            x2 = Math.tan(gamma_angle) * (r - r_zero);
            if (-10 <= x2 & x2 <= x_max & r >= 33.8){
                tempSeries.add(r,x2);
            }
            x3 = Math.tan(alpha_angle + 90) * r;
            if (0 <= x3 & x3 <= x_max){
                tempSeries.add(r,x3);
            }
            x4 = -Math.tan(betta_angle) * r;
            if (x4 <= 0 & x4 >= -10 & r<= 33.8){
                tempSeries.add(r,x4);
            }
            tempSeries.add(r,x1);
        }
    }
}