import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by helangovan on 11/6/15.
 */
public class FileExercise {

    public static void main(String args[]) throws IOException {
        Path path = Paths.get("/", "Users/helangovan/jcgProjects/Java_SE_8/code 2/ch9/sec02", "FileExercise.java");
        byte[] bytes = Files.readAllBytes(path);
        byte [] revBytes = new byte[bytes.length];
        Path pathRev = Paths.get("/", "Users/helangovan/jcgProjects/Java_SE_8/code 2/ch9/sec02", "FilesReverseDemo.txt");
        for(int j=0,i=bytes.length-1;i>=0;i--,j++){
             revBytes[j]=bytes[i];
        }
        Files.write(pathRev, revBytes);


        //copy from url
              URL url = new URL("http://horstmann.com");
//
        boolean deleted = Files.deleteIfExists(Paths.get("/","Users/helangovan/jcgProjects/Java_SE_8/code 2/ch9/sec02","FilesWeb.out"));
        System.out.println(deleted);
//
       Files.copy(url.openStream(), Paths.get("/","Users/helangovan/jcgProjects/Java_SE_8/code 2/ch9/sec02","FilesWeb.out"));
//      ByteArrayOutputStream out = new ByteArrayOutputStream();
//      Files.copy(Paths.get("FilesDemo3.out"), out);
//      System.out.println(out.toString("UTF-8").substring(0, 100) + "...");
//
    }
}
