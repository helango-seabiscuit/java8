package com.ocp.ch8;

import com.ocp.ch1.Outer;
import com.ocp.ch1.StaticNested;

import java.io.*;

/**
 * Created by helangovan on 2/12/16.
 */
public class CopyFileSample {


    public static void copy(File source,File destination){
        try(InputStream fis = new FileInputStream(source);
            OutputStream fos = new FileOutputStream(destination)){
            int b;
            while((b=fis.read())!=-1){
                fos.write(b);
            }
        }catch (IOException fo){
            fo.printStackTrace();
        }
    }


    public static void main(String[] args) {

        File source = new File("s.txt");
        File destination = new File("scopy.txt");
        copy(source,destination);




    }
}
