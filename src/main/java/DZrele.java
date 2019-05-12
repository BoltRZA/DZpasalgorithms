
import org.apache.commons.math3.complex.Complex;


public class DZrele {
	//Ustavki
	private double x1Ust = 0;
	private double alfUst = 1;
	private double bettUst = 2;
	private double gamUst = 3;
	private double b1Ust = 3;
	private boolean yap = false;
	//
	//	private Complex Ua;
	//	private Complex Ub;
	//	private Complex Uc;
	//	private Complex Ia;
	//	private Complex Ib;
	//	private Complex Ic;



	public boolean breaker(double Uad, double Ubd,double Ucd,double Uam,double Ubm,double Ucm,double Iad, double Ibd,double Icd,double Iam,double Ibm,double Icm) {
		//finding Zrele
		double Uabd = Uad - Ubd;
		double Uabm = Uam - Ubm; 

		double Iabd = Iad - Ibd;
		double Iabm = Iam - Ibm; 

		double Zrele_d = Uabd/(Math.pow(Iabd, 2)+Math.pow(Iabm, 2));
		double Zrele_m = Uabm/(Math.pow(Iabd, 2)+Math.pow(Iabm, 2));
		//__________________________________________________________________________1 kvadrant
		if(Zrele_d >=0&Zrele_m >= 0) {
			//_____________________________________________________________________Upper limit
			if(Zrele_d <=0&Zrele_m >= 0) {
				if (Zrele_m<=x1Ust) {
					yap = true;
					return yap;
				}
				//______________________________________________________________________Right limit
				else if (Zrele_m >= Math.tan(gamUst)*(Zrele_d+b1Ust)) {
					yap = true;
					return yap;
				}
			}
			//__________________________________________________________________________2 kvadrant
			if(Zrele_d <=0&Zrele_m >= 0) {
				//____________________________________________________________________Upper limit
				if (Zrele_m<=x1Ust) {
					yap = true;
					return yap;
				}
				//_____________________________________________________________________Left limit
				else if (Zrele_m >= Math.tan(alfUst+90)*Zrele_d) {
					yap = true;
					return yap;
				}

			}
			//__________________________________________________________________________4 kvadrant
			if(Zrele_d >=0&Zrele_m <= 0) {
				//______________________________________________________________________Right limit
				if (Zrele_m >= Math.tan(gamUst)*(Zrele_d+b1Ust)) {
					yap = true;
					return yap;
				}
				//______________________________________________________________________Lower limit
				else if (Zrele_m >= -Math.tan(bettUst)*Zrele_d) {
					yap = true;
					return yap;
				}
			}





		}
		return yap;

	}
}
