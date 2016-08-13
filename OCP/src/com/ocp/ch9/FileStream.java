package com.ocp.ch9;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by helangovan on 2/16/16.
 */
public class FileStream {

    public static void main(String[] args) throws IOException {

        try {
            Files.walk(Paths.get("/Users/helangovan/SQL"))
                    .filter(p -> p.toString().endsWith(".sql"))
                    .forEach(p->System.out.println(p.getFileName()));
        }catch (IOException e){
            e.printStackTrace();
        }

        Path path = Paths.get("/Users/helangovan");
        long dateFilter = 1420070400000l;
        Stream<Path> stream = Files.find(path, 10,
                (p, a) -> p.toString().endsWith(".java")
                        && a.lastModifiedTime().toMillis() > dateFilter);
        System.out.println("Files find filter");
        //stream.forEach(System.out::println);


        Files.list(path)
                .filter(p->!Files.isDirectory(p))
                .map(p -> p.toAbsolutePath())
                .forEach(System.out::println);

        Path path1 = Paths.get("/Users/helangovan/jcgProjects/edu-hadoop/src/main/java/com/edu/hadoop/youtube/ExtractYoutubeMap.java");

        Files.lines(path1).forEach(System.out::println); //Print entire file content
        System.out.println(Files.lines(path1)
                .filter(s -> s.startsWith("import"))
                .map(s -> s.substring(6))
                .collect(Collectors.toList()));

        Path p = Paths.get("zoo");
        System.out.println("Walking files of " + p.toAbsolutePath());
        System.out.println(Files.walk(p).count());

        System.out.println("Walking 2 levels files of " + p.toAbsolutePath());
        System.out.println(Files.walk(p, 1).count());//forEach(System.out::println);
        Files.walk(p, 0, FileVisitOption.FOLLOW_LINKS).forEach(System.out::println);

        Files.find(p, 1, (Path pt, BasicFileAttributes ba) -> {
            try {
                return Files.isDirectory(pt) && ba.size() > 0;
            } catch (Exception e) {
                return false;
            }
        }).forEach(System.out::println);

        LargeFileCounter cnt = new LargeFileCounter();
        System.out.println(Files.walkFileTree(p, EnumSet.of(FileVisitOption.FOLLOW_LINKS), 3, cnt).toAbsolutePath());
        System.out.println(cnt.getFileCount());

        System.out.println("Listing Files");
        Files.list(p).forEach(System.out::println);

        System.out.println("DirectoryStream Search");
        try(DirectoryStream<Path> ds =Files.newDirectoryStream(p, "*.lck")){
            for(Path ph:ds){
                System.out.println(ph);
            }
        }
        }
    }
