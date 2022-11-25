package com.HKdic.capston.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.HKdic.capston.controller.SpringUploadController.carInformations;
import static com.HKdic.capston.domain.DIR.*;

/* Class For Implement Python File */

public class PythonImplement {

    public static String nameOfCar = "";
    public static ArrayList<String> nameOfCars = new ArrayList<>();

    public Process makeProcess(String command, String pythonFile, String arg1) throws IOException {
        return (new ProcessBuilder(command, pythonFile, arg1)).start();
    }

    public Process makeProcess(String command, String pythonFile, String arg1, String arg2, String arg3, String arg4) throws IOException {
        return (new ProcessBuilder(command, pythonFile, arg1, arg2, arg3, arg4)).start();
    }

    /**
     * Obtain Car's name from image by Machine Learning
     * In python
     * parameter : (command, python File, Image File)
     * return : String (name of Car from image)
     */

    public void implementML() throws Exception{
        Process process = makeProcess(PYTHON_DIR.getVal(), PYTHON_ML_DIR.getVal(), UPLOADED_IMG_DIR.getVal());
        getCarName(process);
    }

    public void getCarName(Process process) throws Exception {
        int exitVal = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;
        int i=0;
        while((result=br.readLine()) != null){
            System.out.println("result = " + result);
            nameOfCars.add(result);
            if(i==3) break;
            i++;
        }

        System.out.println("끝남.");

    }

    /**
     * Obtain car information by Crawling
     * In python
     * parameter : (command, python File, nameOfCarFromImage)
     * return : Car's Basic Information
     */

    public void implementCrawling() throws Exception{
        if(nameOfCars.size() == 0) {
            return;
        }
        Process process = makeProcess(PYTHON_DIR.getVal(), PYTHON_CRAWLING_DIR.getVal(), nameOfCars.get(0), nameOfCars.get(1), nameOfCars.get(2), PYTHON_IMAGE_DIR.getVal());
        getCarInformation(process);
    }

    public void getCarInformation(Process process) throws Exception{
        int exitVal = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;
        ArrayList<String> infos = new ArrayList<>();
        int i=0;
        while((result=br.readLine()) != null){
            System.out.println("result = " + result);
            infos.add(result);
            i++;
            if(i==7){
                i = 0;
                carInformations.add(new CarInformation(infos));
                infos.clear();
            }
        }

        System.out.println("infos = " + infos);

        if(exitVal != 0) return; //비정상 종료
    }

}
