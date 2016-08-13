package com.ocp.ch9;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by helangovan on 2/15/16.
 */
public class FilesObjects {
    public static void main(String[] args) {
        System.out.println(Files.exists(Paths.get("/ostrich/feathers.png")));
        System.out.println(Files.exists(Paths.get("/Users")));


//        try{
//            System.out.println(Files.isSameFile(Paths.get("/user/home/cobra"),Paths.get("/user/home/snake")));
//            System.out.println(Files.isSameFile(Paths.get("/user/tree/../monkey"),Paths.get("/user/monkey")));
//            System.out.println(Files.isSameFile(Paths.get("/leaves/./giraffee.exe"),Paths.get("/leaves/giraffee.exe")));
//            System.out.println(Files.isSameFile(Paths.get("/user/home/cobra"),Paths.get("/user/home/snake")));
//        }catch (IOException e){
//             e.printStackTrace();
//        }

//        try {
//            Files.createDirectory(Paths.get("/bison/field"));
//            Files.createDirectories(Paths.get("/bison/field"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }

//        try{
//           Files.copy(Paths.get("s.txt"),Paths.get("files_scopy.txt"));
//        }catch (IOException e){
//            e.printStackTrace();
//        }

        try{
          Files.move(Paths.get("/tmp/admin_new.js"),Paths.get("/Users/helangovan/tmp/admin.js"));
        }catch (IOException e){
            e.printStackTrace();
        }

         ReadWriteFiles();

    }

    private static void ReadWriteFiles(){
        Path path = Paths.get("/Users/helangovan/tmp/admin.js");
        try(BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())){
          String currentLine = null;
            while ((currentLine = reader.readLine())!=null){
                System.out.println(currentLine);
            }
        }catch (IOException e){
           e.printStackTrace();
        }

        Path path1 = Paths.get("/Users/helangovan/tmp/sm.txt");
        try(BufferedWriter writer = Files.newBufferedWriter(path1, Charset.defaultCharset())){
             writer.write("Hello World");
        }catch (IOException e){
            e.printStackTrace();
        }

        try{
            final List<String> lines = Files.readAllLines(path);
            System.out.println("No of lines in "+path+" is: "+lines.size());
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
