package com.HKdic.capston.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.HKdic.capston.domain.DIR.*;

/* Class For Implement Python File */

public class PythonImplement {

    public static String nameOfCar = "";

    public Process makeProcess(String command, String pythonFile, String arg1) throws IOException {
        return (new ProcessBuilder(command, pythonFile, arg1)).start();
    }

    public Process makeProcess(String command, String pythonFile, String arg1, String arg2) throws IOException {
        return (new ProcessBuilder(command, pythonFile, arg1, arg2)).start();
    }

    /**
     * Obtain Car's name from image by Machine Learning
     * In python
     * parameter : (command, python File, Image File)
     * return : String (name of Car from image)
     */

    public void implementML() throws Exception{
        Process process = makeProcess(PYTHON_DIR.getVal(), PYTHON_ML_DIR.getVal(), UPLOADED_IMG_DIR.getVal());
        nameOfCar = getCarName(process);
    }

    public String getCarName(Process process) throws Exception {
        int exitVal = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;
        result = br.readLine(); //get car's name from PythonFile
        System.out.println("result = " + result);
        if(exitVal != 0) return null;

        return result;
    }

    /**
     * Obtain car information by Crawling
     * In python
     * parameter : (command, python File, nameOfCarFromImage)
     * return : Car's Basic Information
     */

    public CarInformation implementCrawling() throws Exception{
        if(nameOfCar == " ") {
            return null;
        }
        Process process = makeProcess(PYTHON_DIR.getVal(), PYTHON_CRAWLING_DIR.getVal(), nameOfCar, PYTHON_IMAGE_DIR.getVal());
        return getCarInformation(process);

    }

    public CarInformation getCarInformation(Process process) throws Exception{
        int exitVal = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));
        String result = br.readLine();
        System.out.println("result = " + result);

        if(exitVal != 0) return null; //비정상 종료
        ArrayList<String> infos = new ArrayList<>(Arrays.asList(result.split(",")));
        System.out.println(infos.toString());
        return new CarInformation(infos);
    }

}
