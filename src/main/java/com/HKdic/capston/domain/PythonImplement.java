package com.HKdic.capston.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Class For Implement Python File */

public class PythonImplement {

    public static String pythonCommand = "C:\\Users\\jibae\\AppData\\Local\\Programs\\Python\\Python39\\python.exe";
    public static String pythonCommandDesktop = "C:\\Users\\whipbaek\\AppData\\Local\\Programs\\Python\\Python39\\python.exe";
    public static String argMLPython = "C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\ML.py";
    public static String argCrawlingPython = "C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\pythonfile\\CrawlingCar.py";
    public static String carImageDir = "C:\\Users\\jibae\\Projects\\capston\\src\\main\\resources\\static\\images\\testFile.jpg";
    public static String nameOfCar = "";

    public Process makeProcess(String command, String pythonFile, String arg1) throws IOException {
        return (new ProcessBuilder(command, pythonFile, arg1)).start();
    }

    /**
     * Obtain Car's name from image by Machine Learning
     * In python
     * parameter : (command, python File, Image File)
     * return : String (name of Car from image)
     */

    public void implementML() throws Exception{
        Process process = makeProcess(pythonCommand, argMLPython, carImageDir);

        int exitVal = process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;
        result = br.readLine(); //get car's name
        if(exitVal != 0) return; // 비정상 종료

        nameOfCar = result;
    }

    /**
     * Obtain car information by Crawling
     * In python
     * parameter : (command, python File, nameOfCarFromImage)
     * return : Car's Basic Information
     */

    public CarInformation implementCrawling() throws Exception{
        if(nameOfCar == " ") return null;

        Process process = makeProcess(pythonCommand, argCrawlingPython, nameOfCar);
        int exitVal = process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;

        while((result = br.readLine()) != null){
            System.out.println("result = " + result);
        }

        if(exitVal != 0) return null; //비정상 종료

        return new CarInformation();
    }

}
