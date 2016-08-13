package com.ocp.ch7;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by helangovan on 2/7/16.
 */
public class ParallelStreamEx {

    public static void main(String[] args) {
        Arrays.asList(1,2,3,4,5,6)
                .parallelStream()
                .forEach(s -> System.out.print(s + " "));

        System.out.println();

        Arrays.asList(1,2,3,4,5,6)
                .parallelStream()
                .forEachOrdered(s -> System.out.print(s + " "));

       // parallelPerformance();

//        orderStudy();
//
//        avoidStatefulOperations();

        System.out.println("------------------------------");
        System.out.println(Arrays.asList('w', 'o', 'l', 'f').parallelStream().reduce("", (c, s1) -> s1+c, (s2, s3) -> s2 + s3));

        System.out.println(Arrays.asList(1,2,3,4,5,6)
        .parallelStream()
        .reduce(0, (a, b) -> a - b));

        System.out.println(Arrays.asList("w","o","l","f")
        .parallelStream()
        .reduce("X",String::concat));

        Stream<String> stream = Stream.of("w","o","l","f").parallel();
        SortedSet<String> set = stream.collect(ConcurrentSkipListSet::new,Set::add,Set::addAll);
        System.out.println(set);

        stream = Stream.of("o","w","o","l","f").parallel();
        Set<String> st = stream.collect(Collectors.toSet());
        System.out.println(st);

        stream = Stream.of("o","w","o","l","f").parallel();
        List<String> t = stream.collect(Collectors.toList());
        System.out.println(t);
    }

    private static void parallelPerformance() {
        WhaleDataCalculator dataCalculator = new WhaleDataCalculator();

        List<Integer> data = new ArrayList<>();
        for(int i=0;i<4000;i++){
            data.add(i);
        }

        long start = System.currentTimeMillis();
        dataCalculator.processRecordData(data);
        double time = (System.currentTimeMillis()-start)/1000.0;

        System.out.println("Tasks completed in " + time + " seconds");
    }

    public static void orderStudy(){
        Arrays.asList("jackal","kangaroo","lemur")
                .parallelStream().parallel()
                .map(s->{
                    System.out.println(s);
                    return  s.toUpperCase();
                })
                .forEach(s -> System.out.println(s));
    }

    private static  void avoidStatefulOperations(){

        List<Integer> data = Collections.synchronizedList(new ArrayList<Integer>());
        Arrays.asList(1,2,3,4,5,6).parallelStream()
                .map(i->{data.add(i);return i;})
                .forEachOrdered(i -> System.out.print(i + " "));

        System.out.println();
        for(Integer e:data)
            System.out.print(e + " ");
    }
}


class WhaleDataCalculator{
    public int processRecord(int input){
        try{
            Thread.sleep(10);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        return  input+1;
    }

    public void processRecordData(List<Integer> data){
      // data.stream().map(a -> processRecord(a)).count();
       data.parallelStream().map(a -> processRecord(a)).count();
    }
}
