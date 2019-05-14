

public class Main {
	public static void main(String[] args) {

			String path = "C:\\Users\\Автоматика3\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
			String file = "KZ1";
//			Charts ch = new Charts();
			DataComtrade cd = new DataComtrade(path, file);
//		cd.setChart(ch);
		DZChart.create();
		Charts.createAnalogChart("Z",0);
		Charts.addSeries("Zab",0,0);

		Charts.createAnalogChart("TRIP ",1);
		Charts.addSeries("TripAB",1,0);

//		cd.setChart(ch);
		DZrele AB = new DZrele();
		DZrele BC = new DZrele();
		DZrele CA = new DZrele();

		cd.setReleAB(AB);
		cd.setReleBC(BC);
		cd.setReleCA(CA);
		cd.run();
		}
}
