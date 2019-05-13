import org.apache.commons.math3.complex.Complex;


public class DZrele {
	//Ustavki
	private double x1Ust = 69;
	private double alfUst = 25*Math.PI/180;
	private double bettUst = 15*Math.PI/180;
	private double gamUst = 15*Math.PI/180;;
	private double b1Ust = 38;
	private double Zrele_d = 0;
	private double Zrele_m = 0;
	private  double Zrele = 0;

	public boolean Breaker(double Uad, double Ubd,double Uam,double Ubm,double Iad, double Ibd,double Iam,double Ibm) {
		boolean yap = false;
		boolean yap1 = false;
		boolean yap2 = false;
//finding Zrele
		double Uabd = Uad - Ubd;
		double Uabm = Uam - Ubm;

		double Iabd = Iad - Ibd;
		double Iabm = Iam - Ibm;

		Zrele_d = Uabd/(Math.pow(Iabd, 2)+Math.pow(Iabm, 2));
		Zrele_m = Uabm/(Math.pow(Iabd, 2)+Math.pow(Iabm, 2));
		Zrele = Math.sqrt(Math.pow(Zrele_d, 2) + Math.pow(Zrele_m, 2));

//__________________________________________________________________________1 kvadrant
		if(Zrele_d >=0&Zrele_m >= 0) {
//_____________________________________________________________________Upper limit

			if (Zrele_m<=x1Ust) {
				yap1 = true;

			}
//______________________________________________________________________Right limit
			if (Zrele_m >= Math.tan(gamUst)*(Zrele_d+b1Ust)) {
				yap2 = true;


			}
			if (yap1||yap2) {
				yap = true;
				return yap;
			}
		}
//__________________________________________________________________________2 kvadrant
		if(Zrele_d <=0&Zrele_m >= 0) {
//____________________________________________________________________Upper limit
			if (Zrele_m<=x1Ust) {
				yap1 = true;

			}
//_____________________________________________________________________Left limit
			else if (Zrele_m >= Math.tan(alfUst+90)*Zrele_d) {
				yap2 = true;

			}
			if (yap1||yap2) {
				yap = true;
				return yap;
			}
		}
//__________________________________________________________________________4 kvadrant
		if(Zrele_d >=0&Zrele_m <= 0) {
//______________________________________________________________________Right limit
			if (Zrele_m >= Math.tan(gamUst)*(Zrele_d+b1Ust)) {
				yap1 = true;

			}
//______________________________________________________________________Lower limit
			else if (Zrele_m >= -Math.tan(bettUst)*Zrele_d) {
				yap2 = true;

			}
			if (yap1||yap2) {
				yap = true;
				return yap;
			}





		}
		return yap;


	}
	public double returnR(){
		return Zrele_d;
	}
	public double returnX(){
		return Zrele_m;
	}
	public double returnZ(){
		return Zrele;
	}
}