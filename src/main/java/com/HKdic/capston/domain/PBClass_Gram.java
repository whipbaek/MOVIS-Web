package com.HKdic.capston.domain;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PBClass_Gram {

    public String ImplementPython() throws Exception {
        String command = "C:\\Users\\jibae\\AppData\\Local\\Programs\\Python\\Python39\\python.exe";
        String arg = "C:\\Users\\jibae\\Projects\\capston\\src\\main\\java\\com\\HKdic\\capston\\domain\\imgpy.py";
        ProcessBuilder builder = new ProcessBuilder(command, arg);
        Process process = builder.start();
        int exitVal = process.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "euc-kr"));

        String result;
        result = br.readLine();

        if(exitVal != 0){
            System.out.println("비정상 종료 감지");
            return "";
        }

        return result;
    }

    public static void main(String[] args) throws Exception {
        String s = new PBClass_Gram().ImplementPython();
        System.out.println("s = " + s);
    }
}
