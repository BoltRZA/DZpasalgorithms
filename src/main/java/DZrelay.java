public class DZrelay {

	public static double x_max = 69;
	public static double alpha_angle = 10 * Math.PI / 180;
	public static double betta_angle = 15 * Math.PI / 180;
	public static double gamma_angle = 65 * Math.PI / 180;
	public static double r_zero = 38;

	private double zRelay_real = 0;
	private double zRelay_imag = 0;
	private double zRelay = 0;

	public boolean relaying(double u1real, double u2real, double u1imag, double u2imag,
							double i1real, double i2real, double i1imag, double i2imag) {

		boolean blink = false;

		double u12_real = u1real - u2real;
		double u12_imag = u1imag - u2imag;

		double i12_real = i1real - i2real;
		double i12_imag = i1imag - i2imag;

		zRelay_real = (u12_real * i12_real + u12_imag * i12_imag) / (Math.pow(i12_real, 2) + Math.pow(i12_imag, 2));
		zRelay_imag = (i12_real * u12_imag - u12_real * i12_imag) / (Math.pow(i12_real, 2) + Math.pow(i12_imag, 2));
		zRelay = Math.sqrt(Math.pow(zRelay_real, 2) + Math.pow(zRelay_imag, 2));

		if (zRelay_imag <= x_max & (zRelay_imag >= Math.tan(gamma_angle) * (zRelay_real - r_zero)) &
				(zRelay_imag >= Math.tan(alpha_angle + 90) * zRelay_real) &
				(zRelay_imag >= -Math.tan(betta_angle) * zRelay_real)) {
			blink = true;
		}

		return blink;
	}

	public double returnR () {
		return zRelay_real;
	}

	public double returnX () {
		return zRelay_imag;
	}

	public double returnZ () {
		return zRelay;
	}

	public double getX_max() {
		return x_max;
	}

	public double getAlpha_angle() {
		return alpha_angle;
	}

	public double getBetta_angle() {
		return betta_angle;
	}

	public double getGamma_angle() {
		return gamma_angle;
	}

	public double getR_zero() {
		return r_zero;
	}
}
