package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

/**
 * Created by helangovan on 2/17/16.
 */
public class BasicFileAttributeViewSample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/Users/helangovan");
        BasicFileAttributeView view = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes data = view.readAttributes();
        FileTime lastModifiedTime = FileTime.fromMillis(data.lastModifiedTime().toMillis()+10_000);
        view.setTimes(lastModifiedTime,null,null);
    }
}
