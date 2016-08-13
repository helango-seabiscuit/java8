package com.ocp.ch4;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * Created by helangovan on 7/28/16.
 */
public class StreamLambdaTest {
        private String value;
        public StreamLambdaTest(String t) { this.value = t; }
        public String doIt(String v) {
            return "" + v.charAt(0) + value.charAt(0);
        }

        public static void main(String [] args) throws IOException {
            StreamLambdaTest t = new StreamLambdaTest("ABCD");
            Stream.of("P", "Q")
                    .map(t::doIt)
                    .forEach(System.out::println);
            Integer intgr = new Integer(5);
            UnaryOperator<Integer> uo = (a)-> a.intValue() + 10;
          //  LongFunction<Integer> lf = (a)-> a.intValue() + 10;
            ToIntFunction<Integer> toi = (a)-> a.intValue() + 10;
            BiFunction<String, Number, String> bf = (s,n)->s+n;
            BiFunction<String, Number, String> bf1 = (final String s,Number n)->String.format(s,n);
            Function<String,String> fs =(String s)->"Message is: " + s;
            List<Integer> l = new ArrayList();
           // Optional.empty().get(); throws NoSuchException
            System.out.println(Optional.empty().orElseGet(() -> {
                System.out.println("No");
                return false;
            }));

            Map m = new HashMap<>();
            Path path1 = Paths.get("Documents/JavaProjects/Java7");
            Path path2 = Paths.get("Java7/NIO/src");
//Manipulate
            String strResult = path1.resolve(path2).toString();
            System.out.println(strResult);

            fileTest();

            String strPath = "/kaplan/oracle/java7/805.java";
            Path path = Paths.get(strPath);
            System.out.println(path.subpath(2,4));

            


        }

    public static void fileTest(){
        Path filePath = Paths.get("/Users/helangovan/tmp/upgrade.txt");
        OpenOption[] options = new OpenOption[]
            {
            StandardOpenOption.CREATE_NEW, StandardOpenOption.DSYNC};
           try (BufferedWriter br = Files.newBufferedWriter(filePath, Charset.defaultCharset(), options)){
               br.newLine();
               br.write("Modified on " + ZonedDateTime.now());
             } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
    }

}

@FunctionalInterface
interface  Photographer {
    abstract String takePhotograph(String s);
    public abstract boolean equals(Object o);
}


//@FunctionalInterface
//interface Funct2 {
//    static void doStuff(){}
//}

@FunctionalInterface
interface Funct54 extends Funct2 {
    void doStuff();
}
