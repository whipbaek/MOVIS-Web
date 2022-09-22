package com.HKdic.capston.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ProcessBuilderTest{

    public String ImplPB() throws Exception{
        String command = "C:\\Users\\jibae\\AppData\\Local\\Programs\\Python\\Python39\\python.exe";
        String arg1 = "C:\\Users\\jibae\\OneDrive\\바탕 화면\\testpy.py";
        ProcessBuilder builder = new ProcessBuilder(command, arg1);
        Process process = builder.start();
        int exitVal = process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr")); // 서브 프로세스가 출력하는 내용을 받기 위해
        String line;
        String result ="";
        while((line = br.readLine()) != null) {
            result += line;
        }

        if (exitVal != 0) {
            System.out.println("비정상 종료 감지");
        }

        return result;
    }

}
