import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.omg.CORBA.PUBLIC_MEMBER;

public class DataComtrade {
    private File comtrCfg;
    private File comtrDat;
    private BufferedReader br;
    private String line;
    private String[] lineData;

    private double k1[]= new double [6];
    private double k2[]=new double [6];
    //private double U[]=new double [4001];
    //private double I[]=new double [4001];


    public DataComtrade(String path, String file) {
        //Тот метод который выполняется при создании экземпляра.

        comtrCfg = new File(path+file+".cfg");
        comtrDat = new File(path+file+".dat");
    }

    public void run() {
        //ожем обратиться извне, и оно ничего не вернет
        try {
            br= new BufferedReader( new FileReader(comtrCfg));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            //трайкэтч поможет игнорировать ошибку в ктче вывод ошибки и прога продолжит выполнение а не остановится)
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
            br= new BufferedReader( new FileReader(comtrDat));

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //трайкэтч поможет игнорировать ошибку в ктче вывод ошибки и прога продолжит выполнение а не остановится)
        }
        Furie faV = new Furie();
        Furie fbV = new Furie();
        Furie fcV = new Furie();
        Furie faN = new Furie();
        Furie fbN = new Furie();
        Furie fcN = new Furie();
        double IaVrms = 0;
        double IbVrms = 0;
        double IcVrms = 0;
        double IaNrms = 0;
        double IbNrms = 0;
        double IcNrms = 0;

        try {
            int lineNum = 0;
            while((line = br.readLine())!=null) {
                lineData = line.split(",");
                IaVrms = faV.getRMS(Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                IbVrms = fbV.getRMS(Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                IcVrms = fcV.getRMS(Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                IaNrms = faN.getRMS(Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                IbNrms = fbN.getRMS(Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                IcNrms = fcN.getRMS(Double.parseDouble(lineData[7])*k1[5]+k2[5]);

                Charts.addAnalogData(0,0,Double.parseDouble(lineData[2])*k1[0]+k2[0]);
                Charts.addAnalogData(0,1,Double.parseDouble(lineData[3])*k1[1]+k2[1]);
                Charts.addAnalogData(0,2,Double.parseDouble(lineData[4])*k1[2]+k2[2]);
                Charts.addAnalogData(1,0,Double.parseDouble(lineData[5])*k1[3]+k2[3]);
                Charts.addAnalogData(1,1,Double.parseDouble(lineData[6])*k1[4]+k2[4]);
                Charts.addAnalogData(1,2,Double.parseDouble(lineData[7])*k1[5]+k2[5]);
                Charts.addAnalogData(2,0,IaVrms);
                Charts.addAnalogData(2,1,IbVrms);
                Charts.addAnalogData(2,2,IcVrms);
                Charts.addAnalogData(3,0,IaNrms);
                Charts.addAnalogData(3,1,IbNrms);
                Charts.addAnalogData(3,2,IcNrms);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.err.println(k1);
        //System.err.println(U);
        //System.err.println(I);
    }

}