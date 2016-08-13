package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.nio.file.attribute.UserPrincipal;

/**
 * Created by helangovan on 2/16/16.
 */
public class FileAttributes {

    public static void main(String[] args) {

        System.out.println(Files.isDirectory(Paths.get("/Users/helangovan")));
        System.out.println(Files.isRegularFile(Paths.get("/Users/helangovan")));
        System.out.println(Files.isSymbolicLink(Paths.get("/Users/helangovan")));


        try{
            System.out.println("Hidden: "+Files.isHidden(Paths.get("/Users/helangovan/.idlerc")));
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("Readable: "+Files.isReadable(Paths.get("/Users/helangovan")));
        System.out.println("Executable: "+Files.isExecutable(Paths.get("/Users/helangovan")));

        try {
            System.out.println("Size: " + Files.size(Paths.get("/Users/helangovan")));
        }catch (IOException e){
            e.printStackTrace();
        }

        final Path path = Paths.get("/Users/helangovan");
        try{
            System.out.println("Last Modified Time: "+Files.getLastModifiedTime(path));
            Files.setLastModifiedTime(path, FileTime.fromMillis(System.currentTimeMillis()));
            System.out.println("Last Modified Time after setting: "+Files.getLastModifiedTime(path));
        }catch (IOException e){
            e.printStackTrace();
        }

        try {
            UserPrincipal owner = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("lcmacadmin");
            //Path path1 = Paths.get("/Users/helangovan");
            Path path1 = Paths.get("/Users/helangovan/youtubedata.txt");
            System.out.println("Owner: "+Files.getOwner(path1).getName());
            Files.setOwner(path1, owner);
            System.out.println("After Owner set: " + Files.getOwner(path1).getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
