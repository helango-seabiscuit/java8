package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by helangovan on 2/15/16.
 */
public class PathObjects {

    public static void main(String[] args) {
        Path path = Paths.get("/land/hippo/harry.happy");
       // Path path = Paths.get("land/hippo/harry.happy");
        System.out.println("The path name is:"+path);
        System.out.println(path.getFileName());
        for(int i=0;i<path.getNameCount();i++){
            System.out.println(" Element "+i+" is : "+path.getName(i));
        }
        printPathInformation(path);
        absoluteCheck();
        subpath();
        derivePathWith();
        toReal();
    }

    public static void printPathInformation(Path path){
        System.out.println("Filename is: " + path.getFileName());
        System.out.println("Root is: " + path.getRoot());

        Path currentPath = path;
        while((currentPath = currentPath.getParent())!=null){
            System.out.println(" Current parent is: "+currentPath);
        }
    }

    public static void absoluteCheck(){
        Path path1 = Paths.get("/birds/egret.txt");
        System.out.println("Path1 is absolute? "+path1.isAbsolute());
        System.out.println("Absolute Path1: "+path1.toAbsolutePath());
    }

    public static void subpath(){
        Path path = Paths.get("/mammal/carnivore/raccoon.image");
        System.out.println("Path is: "+path);
        System.out.println("Subpath from 0 to 3 is:"+path.subpath(0, 3));
        System.out.println("Subpath from 1 to 3 is:"+path.subpath(1,3));
        System.out.println("Subpath from 1 to 2 is:"+path.subpath(1,2));
        //below throws IAE
//        System.out.println("Subpath from 0 to 4 is:"+path.subpath(0,4));
//        System.out.println("Subpath from 1 to 1 is:"+path.subpath(1,1));
    }

    public static void derivePathWith(){
        Path path1 = Paths.get("fish.txt");
        Path path2 = Paths.get("birds.txt");
        System.out.println(path1.relativize(path2));
        System.out.println(path2.relativize(path1));

        Path path3 = Paths.get("/habitat");
        Path path4 = Paths.get("/sanctuary/raven");
        System.out.println(path3.relativize(path4));
        System.out.println(path4.relativize(path3));

        //RESOLVE
        final Path path5 = Paths.get("/cats/../panther");
        final Path path6 = Paths.get("food");
        System.out.println(path5.resolve(path6));
        System.out.println(path6.resolve(path5)); //path5 only used

        //resolve absolute path
        final Path path7 = Paths.get("/turkey/food");
        final  Path path8 = Paths.get("/tiger/cage");
        System.out.println(path7.resolve(path8));

        //NORMALIZE
        final Path path9 = Paths.get("/data");
        final  Path path10 = Paths.get("/user/home");
        Path relativePath = path9.relativize(path10);
        System.out.println(path9.resolve(relativePath).normalize());
    }

    public static void toReal(){
        try{
            System.out.println(Paths.get("/Users/helangovan/export.sql").toRealPath());
            System.out.println(Paths.get("s.txt").toRealPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
