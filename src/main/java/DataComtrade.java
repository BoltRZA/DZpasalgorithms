import org.apache.commons.math3.complex.Complex;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataComtrade {
    private File comtrCfg;
    private File comtrDat;
    private BufferedReader br;
    private String line;
    private String[] lineData;
    private DZrele AB;
    private DZrele BC;
    private DZrele CA;
    private XYLineChartExample chart;

    private double k1[]= new double [6];
    private double k2[]=new double [6];
    private double[] realIs = new double[3];
    private double[] realUs = new double[3];
    private double[] imagIs = new double[3];
    private double[] imagUs = new double[3];
    private boolean ABflag = false;
    private boolean BCflag = false;
    private boolean CAflag = false;
    private int trip = 0;

    public DataComtrade(String path, String file) {


        comtrCfg = new File(path+file+".cfg");
        comtrDat = new File(path+file+".dat");
    }

    public void run() {
        try {
            br = new BufferedReader(new FileReader(comtrCfg));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            int lineNum = 0;
            while((line = br.readLine())!=null) {
                //	System.out.println(line);
                if (lineNum==2) {
                    lineData = line.split(",");
                    k1[0]= Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[0]= Double.parseDouble(lineData[6]);

                }
                if (lineNum==3) {
                    lineData = line.split(",");
                    k1[1]= Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[1]= Double.parseDouble(lineData[6]);
                }
                if (lineNum==4) {
                    lineData = line.split(",");
                    k1[2]= Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[2]= Double.parseDouble(lineData[6]);
                }
                if (lineNum==5) {
                    lineData = line.split(",");
                    k1[3]= Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[3]= Double.parseDouble(lineData[6]);
                }
                if (lineNum==6) {
                    lineData = line.split(",");
                    k1[4]= Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[4]= Double.parseDouble(lineData[6]);
                }
                if (lineNum==7) {
                    lineData = line.split(",");
                    k1[5]= Double.parseDouble(lineData[5]); // koefficient preobrazovaniya
                    k2[5]= Double.parseDouble(lineData[6]);
                }
                lineNum++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        //_________________________________________________________________________________________________
        try {
            br = new BufferedReader( new FileReader(comtrDat));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Furie fIa = new Furie();
        Furie fIb = new Furie();
        Furie fIc = new Furie();
        Furie fUa = new Furie();
        Furie fUb = new Furie();
        Furie fUc = new Furie();
        Double Uamgn;
        Double Ubmgn;
        Double Ucmgn;
        Double Iamgn;
        Double Ibmgn;
        Double Icmgn;

        try {
            while((line = br.readLine())!=null) {
                lineData = line.split(",");
                Uamgn = Double.parseDouble(lineData[2])*k1[0]+k2[0];
                Ubmgn = Double.parseDouble(lineData[3])*k1[1]+k2[1];
                Ucmgn = Double.parseDouble(lineData[4])*k1[2]+k2[2];
                Iamgn = Double.parseDouble(lineData[5])*k1[3]+k2[3];
                Ibmgn = Double.parseDouble(lineData[6])*k1[4]+k2[4];
                Icmgn = Double.parseDouble(lineData[7])*k1[5]+k2[5];

                realIs[0] = fIa.getReal(Iamgn);
                realIs[1] = fIb.getReal(Ibmgn);
                realIs[2] = fIc.getReal(Icmgn);

                imagIs[0] = fIa.getImaginary(Iamgn);
                imagIs[1] = fIb.getImaginary(Ibmgn);
                imagIs[2] = fIc.getImaginary(Icmgn);

                realUs[0] = fUa.getReal(Uamgn);
                realUs[1] = fUb.getReal(Ubmgn);
                realUs[2] = fUc.getReal(Ucmgn);

                imagUs[0] = fUa.getImaginary(Uamgn);
                imagUs[1] = fUb.getImaginary(Ubmgn);
                imagUs[2] = fUc.getImaginary(Ucmgn);

                if (fUc.wait80() == true) {
                    ABflag = AB.Breaker(realUs[0], realUs[1], imagUs[0], imagUs[1], realIs[0], realIs[1], imagIs[0], imagIs[1]);
                    BCflag = BC.Breaker(realUs[1], realUs[2], imagUs[1], imagUs[2], realIs[1], realIs[2], imagIs[1], imagIs[2]);
                    CAflag = CA.Breaker(realUs[2], realUs[0], imagUs[2], imagUs[0], realIs[2], realIs[0], imagIs[2], imagIs[0]);
                    chart.setR(AB.returnR());
                    chart.setX(AB.returnX());
                    if (ABflag || BCflag || CAflag) {
                        trip = 1;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setReleAB(DZrele r){
        this.AB = r;
    }
    public void setReleBC(DZrele r){
        this.BC = r;
    }
    public void setReleCA(DZrele r){
        this.CA = r;
    }
    public void setChart(XYLineChartExample ch){
        this.chart = ch;
    }
}