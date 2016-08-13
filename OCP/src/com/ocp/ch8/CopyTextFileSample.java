package com.ocp.ch8;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helangovan on 2/15/16.
 */
public class CopyTextFileSample {

    public static List<String> readFile(File source) throws IOException {
        List<String> data = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(source))){
            String s;
            while((s=reader.readLine())!=null){
                data.add(s);
            }
        }
        return data;
    }

    public static void writeFile(List<String> data,File destination) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(destination))){
            for(String s:data){
                writer.write(s);
                writer.newLine();
            }
        }
        float value = 1f*0;
        double nt = 1_0_0_0.00d;

        int dow=5;
        switch (dow){
            default:
                System.out.println("Weekday");
                break;
            case  0_0:
                System.out.println("Sunday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 6*6:
        }


    }

    public static void main(String[] args) throws IOException{
        File source = new File("s.txt");
        File destination = new File("scopy_fileBuffer.txt");
        List<String> data = readFile(source);
        writeFile(data,destination);
    }
}
