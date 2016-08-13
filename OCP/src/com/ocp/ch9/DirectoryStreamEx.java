package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by helangovan on 7/25/16.
 */
public class DirectoryStreamEx {

    public static void main(String[] args) {
        Path dirPath = Paths.get("Java7");
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(dirPath, "*.class|*.jar")) {
            for (Path entry: stream) {
                System.out.print(entry.getFileName() + " ");
            }
        } catch (IOException ex) {
            System.err.println("Error Happened!");
        }
    }
}
