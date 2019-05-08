

public class Main {
	public static void main(String[] args) {

			String path = "C:\\Users\\User\\Desktop\\holskz\\";
			String file = "KZ1";
			Furie f = new Furie();
//		Chart ch = new Chart();
			DataComtrade cd = new DataComtrade(path, file);
//		cd.setChart(ch);

			Charts.createAnalogChart("High",0);
			Charts.addSeries("IaH",0,0);
			Charts.addSeries("IbH",0,1);
			Charts.addSeries("IcH",0,2);

			Charts.createAnalogChart("Low ",1);
			Charts.addSeries("IaL",1,0);
			Charts.addSeries("IbL",1,1);
			Charts.addSeries("IcL",1,2);

			Charts.createAnalogChart("HighRMS ",2);
			Charts.addSeries("IaHrms",2,0);
			Charts.addSeries("IbHrms",2,1);
			Charts.addSeries("IcHrms",2,2);

			Charts.createAnalogChart("LowRMS ",3);
			Charts.addSeries("IaLrms",3,0);
			Charts.addSeries("IbLrms",3,1);
			Charts.addSeries("IcLrms",3,2);

			cd.run();
		}
}
