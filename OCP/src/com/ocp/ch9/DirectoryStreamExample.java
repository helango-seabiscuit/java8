package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by helangovan on 7/26/16.
 */
public class DirectoryStreamExample {

    public static void main(String[] args) {
        Path path = Paths.get("/Users/helangovan/Documents/");
        try(DirectoryStream<Path> ds = Files.newDirectoryStream(path)){

            for(Path ph:ds){
                System.out.println(ph.getFileName()+" --- directory? "+Files.isDirectory(ph));
            }
        }catch (IOException ex){

        }
    }
}
