package com.ocp.ch4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserPrincipal;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by helangovan on 7/25/16.
 */
public class StreamTest {

    public static void main(String[] args) {
      //  Stream.of(new Student("t1",3.8f),new Student("t2",4f)).sorted().forEachOrdered(System.out::println);
        Stream.of(new Student("t1",3.8f),new Student("t2",4f)).map(s->s.grade).forEachOrdered(System.out::println);
        List<String> ls = Arrays.asList("Fred", "Jim", "Sheila");
        Function<String,String> f = (String s) -> "Message is: "+s;
        Stream.generate(() -> {
            System.out.println(".");
            return 0;
        });




        //Following will run infintely as new iterator will be created everytime
//        while (ls.iterator().hasNext()) {
//            System.out.println(ls.iterator().next());
//        }

        Iterator it = ls.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }

//        URI uri = null;
//        try {
//            uri = new URI("http://kaplan.com/oracle/java7/805.html");
//        } catch (URISyntaxException ex) {
//            System.err.println(ex.getMessage());
//        }
//        Path path = Paths.get(uri);  Throws FileSystemNotFoundException

        int [] res = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .parallel()
                .collect(() -> new int[2],                      // line n1
                        (a, b) -> {
                            if (b % 2 == 0) a[1] += b;
                            else a[0] += b;
                        }, // line n2
                        (a, b) -> {
                            a[0] += b[0];
                            a[1] += b[1];
                        });
        System.out.println("Odd sum = " + res[0] + " even sum = " + res[1]);

        LocalDate ld = LocalDate.of(2015, Month.APRIL, 1);
        Duration d = Duration.ofHours(24);
//        ld = ld.plus(d); // line n1
        System.out.println(ld);
        System.out.println(d);

        printNumbers();

        System.out.println(Stream.of(new Student("t1", 3.8f), new Student("t2", 4f)).reduce((stu, s) -> stu));
        try {
            Files.lines(Paths.get("quick.txt"))
                 //   .flatMap(s -> s.split("\\W+"))           // line n1
                    .forEachOrdered(System.out::println);
        }catch (IOException e){

        }

        String strPath = "/kaplan/oracle/java7/805.java";
        Path path = Paths.get(strPath);
        System.out.println(path.getNameCount());
        System.out.println(path.subpath(2, 4).toString());
//        Duration.of(5, ChronoUnit.DECADES); RTE (unsupportedTemporalTypeException
       //  Period.of(5, ChronoUnit.DECADES);compile error

        System.out.println(
                Stream.of(new StringBuilder("A"), new StringBuilder("B"))
                        .collect(Collectors.joining(",", ">", "<"))); // line n1

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher("glob:*.{jar,class}");

        Path fPath = Paths.get("/Users/helangovan/Documents/codes/sample");
        try {
            UserPrincipal jhester = fPath.getFileSystem().getUserPrincipalLookupService().
                    lookupPrincipalByName("helangovan");
            FileOwnerAttributeView fView = Files.getFileAttributeView(fPath, FileOwnerAttributeView.class);
            fView.setOwner(jhester);
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

        List<Map<String,Integer>> keywordRanking=new ArrayList<>();
       // List<Map<String,Integer>> keywordRanking=new ArrayList<Map<String,Integer>>();


        SimpleDateFormat df = new SimpleDateFormat("a", Locale.US);
        System.out.println(df.format(new Date()));
         df = new SimpleDateFormat("G", Locale.US);
        System.out.println(df.format(new Date()));
        df = new SimpleDateFormat("zzzz", Locale.US);
        System.out.println(df.format(new Date()));

        ToIntFunction<String> cP = StreamTest::countLower;


        printMeasurements();

        float number = 3.464_101_61e5F;
        System.out.println("Number: " + number);

        try (StringReader reader = new StringReader("");
             StringWriter writer = new StringWriter();
        ){
            //Perform IO operations
        }catch (IOException e){

        }
        try (BufferedReader reader = Files.newBufferedReader(path, Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null)
                System.out.println(line);
        }catch (IllegalStateException |IOException e){
            //e=new IllegalStateException();
        }

        new AlphabetSoup().doStuff();

        System.out.println(
                Stream.of("Fred", "Jim", "Sheila")
                        .reduce((a, b) -> a + " " + b)); //no identity so optional

        Stream.of("Fred", "Jim", "Sheila")
                .reduce((a,b)->a + " " + b)
                .ifPresent(System.out::println);

        System.out.println(
                Stream.of("Fred", "Jim", "Sheila")
                        .reduce(" ", (a, b) -> a + b));

//        System.out.println(
//                Stream.of("Fred", "Jim", "Sheila")
//                        .collect(" ", (a,b)->{a=a+b;}));


        System.out.println(IntStream.iterate(0, (n) -> n + 1)
                // line n1
                .parallel()
                .findAny());

        Arrays.asList("Fred", "Jim", "Sheila")
                .stream()
                .peek(System.out::println) // line n1
                .noneMatch(s -> s.startsWith("F"));

        String [] rainbow = {
                "Red", "Orange", "Yellow", "Green",
                "Blue", "Indigo", "Violet"
        };
         Arrays.stream(rainbow, 2, 5)
        .forEach(System.out::println);

        String rts= Stream.of("Fred", "Jim", "Sheila")
                .parallel()
                .collect(String::new, String::concat, String::concat); // line n1
        System.out.println(rts);

        System.out.println(Arrays.asList("one","two","three","four").stream().parallel()
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString());

        System.out.println(Stream.of(new Student("t1", 3.8f), new Student("t2", 4f)).max(Comparator.comparing(sd -> sd.grade)).get());

        Stream<String> ss = Stream.of("A", "B", "c", "D", "e");
        System.out.println(ss.sequential().findAny());

        System.out.println(
                Stream.of(new StringBuilder("A"), new StringBuilder("B"))
                        .collect(Collectors.joining(",", ">", "<")));

        List<String> user1Books = Arrays.asList("Java", "C++");
        List<String> user2Books = Arrays.asList("Real Analysis", "Discrete Mathematics");

        Stream.of(user1Books, user2Books)
         .flatMap(u->u.stream())
           .forEach(System.out::println);

        List<String> cities;
        cities = new ArrayList<>(Arrays.asList("Las Vegas", "Los Angeles", "Chicago", "New York"));

        System.out.println(cities.stream().peek(c -> System.out.println(c)).anyMatch(c -> c.contains("y") == false));

         List<Double> dbl = Arrays.asList(2.1d,2.3d,5.6d);

        String label = "helLo";
        switch (label){
            case "Hello":
                System.out.println("Hello");
                break;
            case "hello":
                System.out.println("hello");
                break;
        }



    }

    public static void printMeasurements() {
        String measures[] = {" 1.2E2 ", " 3.14 ", " 3.9E-3 "};
        List<?> numList = Arrays.asList(measures);
        for (int i =0; i < numList.size(); i++) {
            System.out.print(numList.get(i) + " " );
        }
    }

    public static int countLower(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                count++;
            }
        }
        return count;
    }

    public static void printNumbers() {
        Double numbers[] = {1.2, 3d, 3.9E14};
        //insert code here
        List<Number> numList = Arrays.asList(numbers);
        //  List<? extends Number> numList = Arrays.asList(numbers);
        for (Number element : numList) {
            System.out.println(element);
        }


        IntStream.iterate(0, (n) -> n + 1)
                .parallel().limit(1000)
                .forEach(System.out::println);


    }
}


class Student{
    public String name;
    public float grade;
    public Student(String name, float grade) {
        this.name = name; this.grade = grade;
    }
    public String toString() {
        return "Student{" + name + ", " + grade + " }";
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
}


interface Funct2 {
    static void doStuff(){}
}

@FunctionalInterface
interface Funct5 extends Funct2 {
    void doStuff();
}


interface A1 {
    default void doStuff(){
        System.out.println("A1.doStuff()");
    }
}

interface A2 extends A1 {
//    default void doStuff(){ // line n1
//        System.out.println("B1.doStuff()");
//    }

}

interface B1 extends A1 {
    default void doStuff(){ // line n1
        System.out.println("B1.doStuff()");
    }
}

class AlphabetSoup implements A2, B1 { // line n2
}

interface Peelable {
    void removeSkin();
    void compostSkin();
    default void peel(){
        removeSkin();
        compostSkin();
    }
}

interface P { default void x() {} }
interface Q extends P { default void x() {} }
interface R extends P {  }
interface S extends Q, R { }


