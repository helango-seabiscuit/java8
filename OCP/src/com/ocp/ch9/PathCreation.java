package com.ocp.ch9;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by helangovan on 2/15/16.
 */
public class PathCreation {

    public static void main(String[] args) throws URISyntaxException {
        Path path1 = Paths.get("panda/cuddly.png");
        Path path2 = Paths.get("c:]\\zooinfo\\November\\employees.txt");
        Path path3 = Paths.get("/home/zoodirectory");
        Path path4 = Paths.get("/","home","zoodirectory");
        Path path5 = Paths.get(new URI("file:///home/zoodirectory"));// new URI throws URISyntaxException
     //   Path path6 = Paths.get(new URI("http://www.wiley.com"));
        URI uri4 = path4.toUri();

        for(Path p: path5){
            System.out.println(p);
        }

        //M2
        Path path7 = FileSystems.getDefault().getPath("/home/zoodirectory");//local FS
        

        //Remote FS
     //   FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://www.selikoff.net"));
       // Path path = fileSystem.getPath("duck.txt");

        int grade=5;
        switch(grade){
            case 5:
            case 2*5:
            case 5/2:
                System.out.println("In double");
        }
        long p = 1_0000_000;
        System.out.println(p);


    }
}
