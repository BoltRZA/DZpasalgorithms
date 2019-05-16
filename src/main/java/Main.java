

public class Main {
	public static void main(String[] args) {

//			String path = "C:\\Users\\Автоматика3\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
			String path = "/Users/alexbol96/IdeaProjects/DZpasalgorithms/Comtrades/";
			//String path = "C:\\Users\\alexbol96\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
			String file = "KZ1";
//			Charts ch = new Charts();
			DataComtrade cd = new DataComtrade(path, file);
//		cd.setChart(ch);
//		DZChart.create();
//		Charts.createAnalogChart("Z",0);
//		Charts.addSeries("Zab",0,0);

//		Charts.createAnalogChart("TRIP ",1);
//		Charts.addSeries("TripAB",1,0);
//		XYLineChartExample example = new XYLineChartExample("XY Chart");
//		example.setSize(800, 400);
//		example.setLocationRelativeTo(null);
//		example.setVisible(true);
//		cd.setChart(ch);

		Charts.createAnalogChartXY("Z",0);
		Charts.addSeriesXY("Z",0,0);

		DZrele AB = new DZrele();
		DZrele BC = new DZrele();
		DZrele CA = new DZrele();
//		cd.setChart(example);
		cd.setReleAB(AB);
		cd.setReleBC(BC);
		cd.setReleCA(CA);
		cd.run();
		}
}
