import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class XYLineChartExample extends JFrame {
    private double X;
    private double R;
    private XYSeriesCollection dataset = new XYSeriesCollection();
    private XYSeries series = new XYSeries("X(R)");

    public void setX(double x) {
        this.X = x;
    }

    public void setR(double r) {
        this.R = r;
    }

    public XYLineChartExample(String title) {
        super(title);

        // Create dataset

        // Create chart
        JFreeChart chart = ChartFactory.createXYLineChart(
                "XY Line Chart Example",
                "X-Axis",
                "Y-Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }
    public void createDataset() {
        series.add(R, X);
        //Add series to dataset
        dataset.addSeries(series);
    }

}