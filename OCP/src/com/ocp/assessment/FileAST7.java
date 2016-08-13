package com.ocp.assessment;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by helangovan on 1/9/16.
 */
public class FileAST7 {
    public static void main(String []args) throws Exception{
        Path path1 = Paths.get("/bats/night","../").resolve(Paths.get("./sleep.txt")).normalize();
        Path path2 = new File("../sleep.txt").toPath().toRealPath();
    }
}
