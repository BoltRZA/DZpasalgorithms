public class Main {

	public static void main(String[] args) {

//			String path = "C:\\Users\\Автоматика3\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
		String path = "/Users/alexbol96/IdeaProjects/DZpasalgorithms/Comtrades/";
		//String path = "C:\\Users\\alexbol96\\IdeaProjects\\DZpasalgorithms\\Comtrades\\";
		String file = "KZ1";
		DataComtrade comtadeData = new DataComtrade(path, file);

		ChartsForImpedance.createAnalogChart("X, Ohms", 0);
		ChartsForImpedance.addSeries("Z", 0, 0);
		ChartsForImpedance.addSeries("Characteristic", 0, 1);
		ChartsForImpedance.addCharacteristic(0, 1);

		ChartsForTrip.createAnalogChart("Trip ", 0);
		ChartsForTrip.addSeries("Trip", 0, 0);

		ChartsForMeasurements.createAnalogChart("Iins", 0);
		ChartsForMeasurements.addSeries("Ia", 0, 0);
		ChartsForMeasurements.addSeries("Ib", 0, 1);
		ChartsForMeasurements.addSeries("Ic", 0, 2);

		ChartsForMeasurements.createAnalogChart("Uinst ", 1);
		ChartsForMeasurements.addSeries("Ua", 1, 0);
		ChartsForMeasurements.addSeries("Ub", 1, 1);
		ChartsForMeasurements.addSeries("Uc", 1, 2);

		ChartsForMeasurements.createAnalogChart("Irms ", 2);
		ChartsForMeasurements.addSeries("Iarms", 2, 0);
		ChartsForMeasurements.addSeries("Ibrms", 2, 1);
		ChartsForMeasurements.addSeries("Icrms", 2, 2);

		ChartsForMeasurements.createAnalogChart("Urms", 3);
		ChartsForMeasurements.addSeries("Uarms", 3, 0);
		ChartsForMeasurements.addSeries("Ubrms", 3, 1);
		ChartsForMeasurements.addSeries("Ucrms", 3, 2);

		DZrelay AB = new DZrelay();
		DZrelay BC = new DZrelay();
		DZrelay CA = new DZrelay();

		comtadeData.setRelayAB(AB);
		comtadeData.setRelayBC(BC);
		comtadeData.setRelayCA(CA);

		comtadeData.run();
	}
}
