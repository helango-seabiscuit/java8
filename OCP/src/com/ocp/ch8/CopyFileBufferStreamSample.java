package com.ocp.ch8;

import java.io.*;

/**
 * Created by helangovan on 2/12/16.
 */
public class CopyFileBufferStreamSample {

    public static void copyFile(File source,File destination){
        try(InputStream in = new BufferedInputStream(new FileInputStream(source));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(destination))){

            byte[] buffer =new byte[1024];
            int lenght;
            while((lenght = in.read(buffer))>0){
                out.write(buffer,0,lenght);
                out.flush();
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File source = new File("s.txt");
        File destination = new File("scopybuffer.txt");
        copyFile(source,destination);

    }
}
