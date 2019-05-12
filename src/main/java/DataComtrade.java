import org.apache.commons.math3.complex.Complex;

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

    private double k1[]= new double [6];
    private double k2[]=new double [6];
    public static Complex[] iS;
    public static Complex[] uS;
    public static Complex[] zS;

    public DataComtrade(String path, String file) {


        comtrCfg = new File(path+file+".cfg");
        comtrDat = new File(path+file+".dat");
    }

    public void run() {
        try {
            br= new BufferedReader(new FileReader(comtrCfg));
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
            // TODO Auto-generated catch block
            e.printStackTrace();
            //трайкэтч поможет игнорировать ошибку в ктче вывод ошибки и прога продолжит выполнение а не остановится)
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
                Uamgn = 1000 * Double.parseDouble(lineData[2])*k1[0]+k2[0];
                Ubmgn = 1000 * Double.parseDouble(lineData[3])*k1[1]+k2[1];
                Ucmgn = 1000 * Double.parseDouble(lineData[4])*k1[2]+k2[2];
                Iamgn = 1000 * Double.parseDouble(lineData[5])*k1[3]+k2[3];
                Ibmgn = 1000 * Double.parseDouble(lineData[6])*k1[4]+k2[4];
                Icmgn = 1000 * Double.parseDouble(lineData[7])*k1[5]+k2[5];

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println(k1);
        //System.err.println(U);
        //System.err.println(I);
    }

}