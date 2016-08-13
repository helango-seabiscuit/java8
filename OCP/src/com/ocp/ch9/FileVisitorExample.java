package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by helangovan on 7/25/16.
 */
public class FileVisitorExample {
}


class LargeFileCounter extends SimpleFileVisitor<Path> {
    private final long sizeForFiles = 1_048_576L; //1 MB
    private int numLargeFiles;


    @Override
    public FileVisitResult preVisitDirectory(Path p,BasicFileAttributes a){

        return FileVisitResult.CONTINUE;
        //return FileVisitResult.SKIP_SIBLINGS;
    }

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attrs) {
       // if (attrs.size() >= sizeForFiles)
            numLargeFiles++;
        return FileVisitResult.CONTINUE;
    }

    public FileVisitResult postVisitDirectory(Path p,IOException e){
        return FileVisitResult.SKIP_SIBLINGS;

    }
    public int getFileCount() {
        return numLargeFiles;
    }
}
