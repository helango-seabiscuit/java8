import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by helangovan on 11/6/15.
 */
public class TryExercise {

    public static void main(String args[]) throws Exception{
        Scanner in = null;
        PrintWriter out = null;
        Exception e = null;
        try {
            in = new Scanner(Paths.get("/usr/share/dict/word"));
            out = new PrintWriter("/tmp/out.txt");

            while (in.hasNext())
                out.println(in.next().toLowerCase());



        }catch(IOException ioe){
              ioe.printStackTrace();
            e=ioe;
        }finally{

            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
                throw new Exception();
            }catch(Exception es){
                if(e!=null){
               e.addSuppressed(es);
                    throw e;
              }else
                    throw es;
            }
        }
    }

    public void process()throws FileNotFoundException,UnknownHostException {
        try {
            if(true) {
                throw new FileNotFoundException();
            }else{
                throw  new UnknownHostException();
            }
        }catch(FileNotFoundException|UnknownHostException ex){
            throw ex;
        }
        }

}
