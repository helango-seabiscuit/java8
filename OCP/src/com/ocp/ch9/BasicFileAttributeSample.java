package com.ocp.ch9;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by helangovan on 2/17/16.
 */
public class BasicFileAttributeSample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/helangovan");
        BasicFileAttributes data = Files.readAttributes(path,BasicFileAttributes.class);

        System.out.println("Is path a directory: "+data.isDirectory());
        System.out.println("Is path a regular file: "+data.isRegularFile());
        System.out.println("Is path a symbolic link: "+data.isSymbolicLink());
        System.out.println("Path not a directory,file or symbolic link: "+data.isOther());
        System.out.println("Size: "+data.size());
        System.out.println("Creation date/time: "+data.creationTime());
        System.out.println("LAst modified time: "+data.lastModifiedTime());
        System.out.println("LAst access time: "+data.lastAccessTime());
        System.out.println("Unique file identifier (if available): "+data.fileKey());

        Float fRate = 0.051f;
        Double d = fRate.doubleValue();
        BigDecimal origRate = BigDecimal.valueOf(d);
        BigDecimal MIN_REFUND_RATE = new BigDecimal("0.05");
        origRate = origRate.setScale(6, RoundingMode.FLOOR);
        if(MIN_REFUND_RATE.compareTo(origRate)<0){
            System.out.println("Bug");
        }


    }
}
