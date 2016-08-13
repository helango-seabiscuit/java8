import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by helangovan on 11/22/15.
 */
public class LambdaExc {

    public static void main(String args[]){
        // listDirectories("/Users/helangovan/jcgProjects/Java_SE_8/code 2/");
        // listFiles("/Users/helangovan/jcgProjects/Java_SE_8/code 2/ch1/sec04","Test1.java");
        sortFiles("/Users/helangovan/jcgProjects/Java_SE_8/code 2/ch4/sec10");
        //new Thread(uncheck(() -> { System.out.println("Zzz"); Thread.sleep(1000); })).start();
        //new Thread(andThen(()->{System.out.print("Hello r1");},()->{System.out.print("Hello r2");})).start();
        isValidLambda();

    }

    public static Runnable uncheck(RunnableEx runner){
        return ()-> {try{runner.run();}catch(Exception e){}};
    }

    public static void listDirectories(String path){
        File file = new File(path);
      //   FileFilter filter = (f)-> f.isDirectory();
      //  File[] directories = file.listFiles(filter);
      //  File[] directories = file.listFiles((f)->f.isDirectory());
        File[] directories = file.listFiles(File::isDirectory);
        System.out.print(Arrays.deepToString(directories));

    }

    public static void listFiles(String path,String extension){
        File file = new File(path);
       String fileNames[]= file.list((directory, es) -> {
           if (es.endsWith(extension)) {
               return true;
           }
           return false;
       });

        System.out.print(Arrays.deepToString(fileNames));
    }

    public static void sortFiles(String path){
        File file = new File(path);
        File[] files = file.listFiles();
        Comparator<File> ct = (first,second)->{
            if(first.isDirectory()){
                if(second.isDirectory()){
                    return first.getName().compareTo(second.getName());
                }
                return -1;
            }else{
                if(second.isDirectory()){
                    return 1;
                }
                return first.getName().compareTo(second.getName());
            }
        };
        Arrays.sort(files,ct);
        System.out.println(Arrays.deepToString(files));

    }

    public static Runnable andThen(Runnable r1,Runnable r2){
       return ()->{
            r1.run();
            r2.run();
        };
    }

    public static void isValidLambda(){
        String[] names = { "Peter", "Paul", "Mary" };
        List<Runnable> runners = new ArrayList<>();
        for (String name : names)
            runners.add(() -> System.out.println(name));

        for(int i=0;i<names.length;i++){
            new Thread(runners.get(i)).start();
        }


    }
}

interface RunnableEx{
    void run() throws Exception;
}
