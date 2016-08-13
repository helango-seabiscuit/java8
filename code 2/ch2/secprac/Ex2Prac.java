import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by helangovan on 11/26/15.
 */
public class Ex2Prac {

    public static void main(String args[]) throws IOException, ExecutionException, InterruptedException {

        Path pathh = Paths.get("/Users/helangovan/jcgProjects/Java_SE_8/code 2/ch2/secprac/hadooptest.txt");
        List<String> contentr = Files.readAllLines(pathh);
        String[] cl = contentr.get(0).split(",");


        Path path = Paths.get("/Users/helangovan/jcgProjects/Java_SE_8/code 2/ch2/sec02/war-and-peace.txt");
        String contents = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);

        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        Long longWords = words.stream().parallel().filter(s -> s.length() > 12).count();
        System.out.print("Size :"+longWords);
        final AtomicInteger counter = new AtomicInteger(0);

       List<String> res = new ArrayList<>();
        ForkJoinPool pool = new ForkJoinPool(2);
        res.addAll(pool.submit(() -> words.stream().parallel().filter(s -> s.length() > 12).collect(Collectors.toList())
        ).get());
        System.out.print("Size :" + res.size());

        //verify after first five long words dont call filter method
        List<String> filWords = Arrays.asList(contents.split("[\\P{L}]+"));
        System.out.print(filWords.stream().filter(s -> s.length() > 15).limit(5).peek(System.out::println).collect(Collectors.toList()));
        long start = System.currentTimeMillis();
        filWords.stream().filter(s -> s.length() > 12).collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.print("Sequential time: "+(end-start));

        start = System.currentTimeMillis();
        filWords.parallelStream().filter(s -> s.length() > 12).collect(Collectors.toList());
        end = System.currentTimeMillis();
        System.out.print("Parallel time: "+(end-start));

        int[] values = { 1, 4, 9, 16 };
        Stream<Integer> i = Stream.of(new Integer[]{1, 4, 9, 16});
        IntStream integerStream = Arrays.stream(values);
        System.out.print(integerStream.count());

//        Stream<Date> stream = Stream.generate(() -> {
//            return new Date();
//        });
//        stream.forEach(p -> System.out.println(p));

        long seed = 1;
        long a = 25214903917l;
        long c = 11, m = Double.valueOf(Math.pow(2,48)).longValue();
        Stream.iterate(seed,(s)->(a*s+c)%m).peek((e)->System.out.print(" "+e)).limit(20).toArray();

        List<String> streamWords = Arrays.asList(contents.split("[\\P{L}]+"));
        int sz=streamWords.size();
        List<Character> cr = new ArrayList<>();
        IntStream is = streamWords.stream().mapToInt(s -> s.length());
        System.out.println("Count of file characters   " + cr.size());
        ArrayList<String> l1 = new ArrayList<String>();
        l1.add("hello");
        l1.add("welcome");
        l1.add("today");
        l1.add("great");
        ArrayList<String> l2 =new ArrayList<>();
        l2.add("hello");
        l2.add("welcome");
        l2.add("today");
        l2.add("great");

        List<ArrayList<String>> last = new ArrayList<>();
        last.add(l1);
        last.add(l2);
        System.out.print(combine(last.stream()));
        Stream<Double> doubleStream = Stream.of(1.5d,6.0d,2.0d);
        System.out.print(avg(doubleStream));

        //List<String> shortWords = Arrays.asList(contents.split("[\\P{L}]+"));
        List<String> shortWords = Arrays.asList("test","you","sfdfdfgdfgfgqessdfdgdf");
        AtomicInteger[] shortWordCounts = new AtomicInteger[12];
        for(int j=0;j<12;j++){
            shortWordCounts[j]= new AtomicInteger(0);
        }
        shortWords.stream().parallel().forEach(
                s -> {
                    if (s.length() < 12) shortWordCounts[s.length()].getAndIncrement();
                }); // Error—race condition!
        System.out.println(Arrays.toString(shortWordCounts));

        System.out.print(shortWords.stream().filter(s->s.length()<12).
                collect(Collectors.groupingBy(String::length, Collectors.counting())));

    }

    public static  Stream<String> zip(Stream<String> first, Stream<String> second){

        return Stream.concat(first,second);

    }

    public static List<String> combine(Stream<ArrayList<String>> st){
       return  st.flatMap(l->l.stream()).collect(Collectors.toList());
    }

    public static  Double avg(Stream<Double> dbls){
        AtomicInteger count = new AtomicInteger(0);
        Double res = dbls.reduce(0d, (x, y) -> {
            count.incrementAndGet();
            return x + y;
        });
        return res/count.get();
       //  return dbls.collect(Collectors.summarizingDouble(Double::new)).getAverage();
    }
}
