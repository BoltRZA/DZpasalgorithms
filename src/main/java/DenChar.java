

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class DenChar  {
    private Stage ps;


    public static void StartPorn(ObservableList<XYChart.Data> datas ) {
         double x1Ust = 50;
        double alfUst = 25 * Math.PI / 180;
        double bettUst = 15 * Math.PI / 180;
        double gamUst = 65 * Math.PI / 180;
        double b1Ust = 38;

       // primaryStage.setTitle("JavaFX Chart (Series)");

        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();

        LineChart<Number, Number> numberLineChart = new LineChart<Number, Number>(x,y);
        numberLineChart.setTitle("Series");
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();
        XYChart.Series series4 = new XYChart.Series();

        series1.setName("1");
        series2.setName("2");
        series3.setName("3");
        series4.setName("4");
        //ObservableList<XYChart.Data> datas = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas3 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas4 = FXCollections.observableArrayList();

        double x1, x2,x3,x4;
        for(double r=-100;r<100; r+=0.5){
            x1 = x1Ust;

            x2 = Math.tan(gamUst)*(r-b1Ust);
            if (-10<=x2&x2<=x1Ust){
                datas.add(new XYChart.Data(r,x2));
            }
            x3 = Math.tan(alfUst+90)*r;
            if (0<=x3&x3<=x1Ust){
                datas2.add(new XYChart.Data(r,x3));
            }
            x4 = -Math.tan(bettUst)*r;
            if (x4<=0&x4>=-10){
                datas3.add(new XYChart.Data(r,x4));
            }
            datas4.add(new XYChart.Data(r,x1));



        }


        series1.setData(datas);
        series2.setData(datas2);
        series3.setData(datas3);
        series4.setData(datas4);

        Scene scene = new Scene(numberLineChart, 600,600);
        numberLineChart.getData().add(series1);
        numberLineChart.getData().add(series2);
        numberLineChart.getData().add(series3);
        numberLineChart.getData().add(series4);

    }
    public void setPS(Stage ps){
        this.ps = ps;
    }



}