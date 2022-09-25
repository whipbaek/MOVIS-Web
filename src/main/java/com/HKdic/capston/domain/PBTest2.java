package com.HKdic.capston.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PBTest2 {

    public String ImplPB2() throws Exception{
        String command = "C:\\Users\\whipbaek\\AppData\\Local\\Programs\\Python\\Python39\\python.exe";
        String arg1 = "C:\\Users\\whipbaek\\Desktop\\testpy.py";
        String arg2 = "you need python";
        ProcessBuilder builder = new ProcessBuilder(command, arg1, arg2);
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

        System.out.println(result);

        return result;
    }

    public static void main(String[] args) throws Exception {
        new PBTest2().ImplPB2();
    }
}
