package com.HKdic.capston.domain;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.HKdic.capston.controller.SpringUploadController.carInformations;
import static com.HKdic.capston.domain.DIR.*;

/* Class For Implement Python File */

@Slf4j
public class PythonImplement {

    public static ArrayList<String> nameOfCars = new ArrayList<>();
    public static ArrayList<String> carInfoText = new ArrayList<>();
    public static ArrayList<String> percentages = new ArrayList<>();

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
        int count = 1;
        while((result=br.readLine()) != null){
            if(i%2 == 0) {
                log.info("{}번째 자동차 : {}", count, result);
                nameOfCars.add(result);
            }
            else {
                log.info("{}번째 자동차 일치율 : {}", count++, result);
                percentages.add(result);
            }
            if(i==6) break;
            i++;
        }
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
        int count = 1;
        while((result=br.readLine()) != null){
            if(i==0){
                log.info("{}번째 차의 정보", count);
                count+=1;
            }
            log.info("{}", result);
            infos.add(result);
            i++;
            if(i==7){
                i = 0;
                carInformations.add(new CarInformation(infos));
                infos.clear();
                log.info("");
            }
        }
        if(exitVal != 0) return; //비정상 종료
    }

    /**
     * tts
     */

    public void implementTTS() throws Exception {
        Process process = makeProcess(PYTHON_DIR.getVal(), PYTHON_TTS_DIR.getVal(), carInformations.get(0).makeTTS(),
                carInformations.get(1).makeTTS(), carInformations.get(2).makeTTS(), PYTHON_TTS_VOICE_DIR.getVal());
        getTTS(process);

    }

    public void getTTS(Process process) throws Exception{
        int exitVal = process.waitFor();
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;
        while((result=br.readLine()) != null){
            }
        }

}


