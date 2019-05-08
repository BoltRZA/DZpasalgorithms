public class Furie {
    private double sin[]= new double[80];
    private double cos[]= new double[80];
    private double buffer[]= new double[80];
    private double sigXs = 0.0,sigX = 0.0;
    private double sigYs= 0.0, sigY = 0.0;
    private int num =0;
    private double kf =(double) Math.sqrt(2)/80;
    private double I1[] = new double[128];
    private double buff[]= new double[128];
    private double Idiff;

    public Furie() {
        for (int i=0;i<80; i++) {
            sin[i]=Math.sin(2*Math.PI*i/80);
            cos[i]=Math.cos(2*Math.PI*i/80);

        }
    }
    public double getRMS (double instValue) {
        sigXs=sigXs+ instValue*cos[num]-buffer[num]*cos[num];
        sigYs=sigYs +instValue*sin[num]-buffer[num]*sin[num];
        sigX=sigXs*kf;
        sigY=sigYs*kf;
        buffer[num] = instValue;
        num++;
        if (num==80) num=0;
        return Math.sqrt(sigX*sigX+sigY*sigY);
    }
    public double getReal(double instValue){
        sigXs=sigXs+ instValue*cos[num]-buffer[num]*cos[num];
        sigYs=sigYs +instValue*sin[num]-buffer[num]*sin[num];
        sigX=sigXs*kf;
        sigY=sigYs*kf;
        buffer[num] = instValue;
        num++;
        if (num==80) num=0;
        return sigX;
    }
    public double getImaginary(double instValue){
        sigXs=sigXs+ instValue*cos[num]-buffer[num]*cos[num];
        sigYs=sigYs +instValue*sin[num]-buffer[num]*sin[num];
        sigX=sigXs*kf;
        sigY=sigYs*kf;
        buffer[num] = instValue;
        num++;
        if (num==80) num=0;
        return sigY;
    }

    int w = 128;
    public void setDataW(int j) {
        I1[num] = I1[num] + j -buff[num];
        buff[num] = j;
        num++;
        if (num==w) num=0;

    }
    public int getCycle(int w) {
        int cycles = 0;
        while (w >1) {
            w = w/2;
            cycles++;
        }
        return cycles;
    }

}