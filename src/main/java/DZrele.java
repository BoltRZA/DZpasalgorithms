import org.apache.commons.math3.complex.Complex;

import javax.swing.*;


public class DZrele {
	//Ustavki
	private double x1Ust = 500;
	private double alfUst = 25 * Math.PI / 180;
	private double bettUst = 15 * Math.PI / 180;
	private double gamUst = 15 * Math.PI / 180;
	;
	private double b1Ust = 38;
	private double Zrele_d = 0;
	private double Zrele_m = 0;
	private double Zrele = 0;

	public boolean Breaker(double Uad, double Ubd, double Uam, double Ubm, double Iad, double Ibd, double Iam, double Ibm) {
		boolean yap = false;

//finding Zrele
		double Uabd = Uad - Ubd;
		double Uabm = Uam - Ubm;



		double Iabd = Iad - Ibd;
		double Iabm = Iam - Ibm;
		//System.out.println(Uabd+"  "+Uabm+"  "+Iabd+"  "+Iabm);
		Zrele_d = (Uabd * Iabd + Uabm * Iabm) / (Math.pow(Iabd, 2) + Math.pow(Iabm, 2));
		Zrele_m = (Iabd * Uabm - Uabd * Iabm) / (Math.pow(Iabd, 2) + Math.pow(Iabm, 2));
		Zrele = Math.sqrt(Math.pow(Zrele_d, 2) + Math.pow(Zrele_m, 2));



		if (Zrele_m <= x1Ust & (Zrele_m >= Math.tan(gamUst) * (Zrele_d + b1Ust)) & (Zrele_m >= Math.tan(alfUst + 90) * Zrele_d) & (Zrele_m >= -Math.tan(bettUst) * Zrele_d)) {
			yap = true;
			return yap;
		}

	return yap;
	}
	public double returnR () {
		return Zrele_d;
	}
	public double returnX () {
		return Zrele_m;
	}
	public double returnZ () {
		return Zrele;
	}
}