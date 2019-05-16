import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataComtrade {

    private File comtrCfg;
    private File comtrDat;
    private BufferedReader bufferedReader;
    private String line;
    private String[] lineData;
    private DZrelay AB;
    private DZrelay BC;
    private DZrelay CA;

    private double k1[]= new double [6];
    private double k2[]=new double [6];
    private double[] realIs = new double[3];
    private double[] realUs = new double[3];
    private double[] imagIs = new double[3];
    private double[] imagUs = new double[3];
    private boolean ABflag = false;
    private boolean BCflag = false;
    private boolean CAflag = false;
    private double trip = 0;


    public DataComtrade(String path, String file) {
        comtrCfg = new File(path+file+".cfg");
        comtrDat = new File(path+file+".dat");
    }

    public void run() {
        try {
            bufferedReader = new BufferedReader(new FileReader(comtrCfg));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            int lineNum = 0;
            while((line = bufferedReader.readLine()) != null) {
                if (lineNum == 2) {
                    lineData = line.split(",");
                    k1[0]= Double.parseDouble(lineData[5]);
                    k2[0]= Double.parseDouble(lineData[6]);

                }
                if (lineNum == 3) {
                    lineData = line.split(",");
                    k1[1]= Double.parseDouble(lineData[5]);
                    k2[1]= Double.parseDouble(lineData[6]);
                }
                if (lineNum == 4) {
                    lineData = line.split(",");
                    k1[2]= Double.parseDouble(lineData[5]);
                    k2[2]= Double.parseDouble(lineData[6]);
                }
                if (lineNum == 5) {
                    lineData = line.split(",");
                    k1[3]= Double.parseDouble(lineData[5]);
                    k2[3]= Double.parseDouble(lineData[6]);
                }
                if (lineNum == 6) {
                    lineData = line.split(",");
                    k1[4]= Double.parseDouble(lineData[5]);
                    k2[4]= Double.parseDouble(lineData[6]);
                }
                if (lineNum == 7) {
                    lineData = line.split(",");
                    k1[5]= Double.parseDouble(lineData[5]);
                    k2[5]= Double.parseDouble(lineData[6]);
                }
                lineNum++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //_________________________________________________________________________________________________
        try {
            bufferedReader = new BufferedReader( new FileReader(comtrDat));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Fourier fIa = new Fourier();
        Fourier fIb = new Fourier();
        Fourier fIc = new Fourier();
        Fourier fUa = new Fourier();
        Fourier fUb = new Fourier();
        Fourier fUc = new Fourier();

        Double Uainst;
        Double Ubinst;
        Double Ucinst;
        Double Iainst;
        Double Ibinst;
        Double Icinst;

        try {
            while((line = bufferedReader.readLine()) != null) {
                lineData = line.split(",");
                Uainst = Double.parseDouble(lineData[2])*k1[0]+k2[0];
                Ubinst = Double.parseDouble(lineData[3])*k1[1]+k2[1];
                Ucinst = Double.parseDouble(lineData[4])*k1[2]+k2[2];
                Iainst = Double.parseDouble(lineData[5])*k1[3]+k2[3];
                Ibinst = Double.parseDouble(lineData[6])*k1[4]+k2[4];
                Icinst = Double.parseDouble(lineData[7])*k1[5]+k2[5];

                ChartsForMeasurements.addAnalogData(0,0, 1000 * Iainst);
                ChartsForMeasurements.addAnalogData(0,1, 1000 * Ibinst);
                ChartsForMeasurements.addAnalogData(0,2, 1000 * Icinst);

                ChartsForMeasurements.addAnalogData(1,0, 1000 * Uainst);
                ChartsForMeasurements.addAnalogData(1,1, 1000 * Ubinst);
                ChartsForMeasurements.addAnalogData(1,2, 1000 * Ucinst);

                realIs[0] = fIa.getReal(Iainst);
                realIs[1] = fIb.getReal(Ibinst);
                realIs[2] = fIc.getReal(Icinst);

                imagIs[0] = fIa.getImaginary(Iainst);
                imagIs[1] = fIb.getImaginary(Ibinst);
                imagIs[2] = fIc.getImaginary(Icinst);

                realUs[0] = fUa.getReal(Uainst);
                realUs[1] = fUb.getReal(Ubinst);
                realUs[2] = fUc.getReal(Ucinst);

                imagUs[0] = fUa.getImaginary(Uainst);
                imagUs[1] = fUb.getImaginary(Ubinst);
                imagUs[2] = fUc.getImaginary(Ucinst);

                if (fUc.wait80() == true) {
                    ABflag = AB.relaying(realUs[0], realUs[1], imagUs[0], imagUs[1],
                            realIs[0], realIs[1], imagIs[0], imagIs[1]);
                    BCflag = BC.relaying(realUs[1], realUs[2], imagUs[1], imagUs[2], realIs[1],
                            realIs[2], imagIs[1], imagIs[2]);
                    CAflag = CA.relaying(realUs[2], realUs[0], imagUs[2], imagUs[0], realIs[2],
                            realIs[0], imagIs[2], imagIs[0]);

                    ChartsForImpedance.addZData(0,0,AB.returnR(),AB.returnX());

                    ChartsForMeasurements.addAnalogData(2,0, 1000 *
                            Math.sqrt(Math.pow(realIs[0],2) + Math.pow(imagIs[0],2)));
                    ChartsForMeasurements.addAnalogData(2,1, 1000 *
                            Math.sqrt(Math.pow(realIs[1],2) + Math.pow(imagIs[1],2)));
                    ChartsForMeasurements.addAnalogData(2,2, 1000 *
                            Math.sqrt(Math.pow(realIs[2],2) + Math.pow(imagIs[2],2)));

                    ChartsForMeasurements.addAnalogData(3,0,1000 *
                            Math.sqrt(Math.pow(realUs[0],2) + Math.pow(imagUs[0],2)));
                    ChartsForMeasurements.addAnalogData(3,1,1000 *
                            Math.sqrt(Math.pow(realUs[1],2) + Math.pow(imagUs[1],2)));
                    ChartsForMeasurements.addAnalogData(3,2, 1000 *
                            Math.sqrt(Math.pow(realUs[2],2) + Math.pow(imagUs[2],2)));

                    if (ABflag || BCflag || CAflag) {
                        trip = 1;
                    }

                    ChartsForTrip.addAnalogData(0,0, trip);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setRelayAB(DZrelay r){
        this.AB = r;
    }

    public void setRelayBC(DZrelay r){
        this.BC = r;
    }

    public void setRelayCA(DZrelay r){
        this.CA = r;
    }
}