package com.ocp.whizlab;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by helangovan on 4/13/16.
 */
public class Test2 implements I1,I2{

    static int y=0;


    public static void main(String[] args) {

        abstract class A{
            public abstract int cal(int x);
        }

        A a = new A(){
            public int cal(int x){
                return x*y;
            }
            public void print(int x){
                System.out.println(cal(x));
            }

        };
        System.out.println(a.cal(2));
        I2 w = new Test2();
        //w.print() compilation issue
        I2.print();
          q1();
        q2();
        q3();
        q4();
        q5();
        q6();
        q7();
        q8();
        q10();
        q11();
        q12();
        q13();
        q14();
        q15();
        q16();
    }

    public static void q1(){
        Stream<Integer> s = Stream.of(12, 40, 11, 22);
        //Optional<Integer> min = s.filter((Integer p)-> p%2==0).min(); not compile as we dont pass comparator to generic stream
        Optional<Integer> min = s.filter((Integer p)-> p%2==0).min(Integer::compare);
        System.out.println(min);
    }

    public static void q2(){
        Stream<String> streams = Stream.of("OCAJP","OCM","OCA","OCEA");
        Optional<String> ops = streams.findAny();
        ops.ofNullable(null).ifPresent(System.out::print);
        System.out.println(ops);

        //know
        //Comparator.comparing(Function)//expect a function
    }

    public static void q3(){
        List<String> strings = new ArrayList<>();
        strings.add("Oracle");
        strings.add("Java");
        strings.add("1ZO-808");
        strings.add("Test I");

        Map<Boolean,List<String>> map = strings.stream().collect(Collectors.partitioningBy(s->s.length()>4));
        System.out.println(map);
    }

    public static void q4(){
       Stream<Vendor> stream = Stream.of(new Vendor("Dell",7),new Vendor("HP",9),new Vendor("Cisco",6));
        ToIntFunction<Vendor> toInt = p->p.rating;
        IntStream ins = stream.mapToInt(toInt);
        System.out.println(ins.skip(1).sum());
    }

    static class Vendor{
         private int rating;
        private String name;
        Vendor(String n,int r){
            this.name = n;
            this.rating = r;
        }
    }

    public static void q5(){
        Stream<Double> stream = Stream.of(2.2,2.8,2.5);
       // Stream<Integer> out = stream.mapToInt(Double::intValue); not compile as it gives IntStream
        IntStream out = stream.mapToInt(Double::intValue);
        System.out.println(out.distinct().count());
    }

    public static void q6(){
        Stream<Integer> stream = Stream.of(2, 0, 4, 1);
        //System.out.println(stream.peek(x->x++).sum()); will not compile becasue the stream dont have sum method
        stream.peek(x->x++).forEach(System.out::print);
        System.out.println();
    }

    public static void q7(){
        Stream stream = Stream.of(10, 20, "30","5");
     //   stream.forEachOrdered(System.out::print);
          boolean out = stream.allMatch(in -> in instanceof Number);
          System.out.println(out);

        List<String> st = new ArrayList<>();
        st.add("A");
        st.add("a");
        st.add("Ba");

        // st.forEachOrdered(System.out::print); not compile as forEachOrdered is in Stream only
        st.forEach(System.out::print);
    }

    public static void q8(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");


        System.out.println(list);
        System.out.println(list.set(3,"3")); // returns the element that is removed
        System.out.println(list);

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"A");
        map.put(2, "B");
        map.put(3, "C");
        map.put(4, "D");
        map.replaceAll((k, v) -> Integer.toString(k));
        System.out.println(map.values());
    }

    public static void q9() throws IOException{
        Path path = Paths.get("in");
      //  Stream<String> out = Files.list(path); not compile as it gives stream of path
        Stream<Path> out = Files.list(path);
        out.forEach(s->System.out.print(s+" | "));
    }

    public static void q10(){
        Stream<String> stream = Stream.of("Cat", "Dog", "Rat");
        Stream<Integer> list = stream.flatMap(s -> Stream.of(s.length()));
        System.out.println(list.collect(Collectors.toSet()));
    }

    public static void q11(){
        IntStream.Builder b = IntStream.builder();
        b.add(0);
        b.accept(1);
        b.add(2);
        IntStream ins = b.build();
      //  b.accept(3); will throw IllegalStateException becasue it can accept after the stream is built
      //  b.add(3);
        ins.forEach(System.out::print);
    }

    public static void q12(){
        IntStream ints = IntStream.of(11, 2, 7, 32, 4, 8, 21, 9);
       // int out = ints.filter(in->in%2==0).reduce(Integer::sum);  intstream reduce will give Optional when we pass one argument BiOperator
        OptionalInt out = ints.filter(in->in%2==0).reduce(Integer::sum);
        System.out.println(out);

        Period p = Period.ofMonths(13);
        System.out.println(p);
        System.out.println(p.normalized());
        System.out.println(Period.ZERO.getUnits());
    }

    public static void q13(){
        LocalTime lt = LocalTime.of(2,2,15);
        System.out.println(lt.isSupported(ChronoField.YEAR));//ChronoUnit.YEARS
//        System.out.println(lt.isSupported(ChronoField.YEARS));
//        System.out.println(lt.isSupported(ChronoUnit.MILLI_OF_SECOND));
        System.out.println(lt.isSupported(ChronoUnit.MILLIS));//ChronoField.MILLI_OF_SECOND
    }

    public static void q14(){
       List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(5);
        list.add(10);
        list.add(11);
        Predicate<Integer> ps = p->p>5;
        Predicate<Integer> p2 = ps.and(p->p<10);
        System.out.println(list.removeIf(p2));
    }

    public static void q15(){
        LongUnaryOperator lou = l->l*2;
        LongUnaryOperator lou2 = l->l*3;
        long l = lou.compose(lou2).applyAsLong(3);
        System.out.println(l);

      //  Function<Double,Integer> f= Function.identity(); incompatible type
        Function<Double,Double> f= Function.identity();
        System.out.println(f.apply(10.0));
    }

    public static void q16(){ //QNO:76
        LongStream ls = LongStream.of(1,2,3,4);
        LongFunction lnf = l->(int)l;
        Stream<Integer> ins = ls.mapToObj(lnf);


        Date now = new Date();
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(now);

        Double hourFloor = new Double(calendar.get(Calendar.HOUR_OF_DAY));

        int numd = getNumberOfDecimalPlaces(19.40d);
    }

    public static int getNumberOfDecimalPlaces(double val){
        int numDecimalPlaces = 0;
        numDecimalPlaces = BigDecimal.valueOf(val).stripTrailingZeros().scale();
        return  numDecimalPlaces;
    }

}

interface I1{
    public static void print(){
        System.out.println("I1");
    }
}

interface I2{
    public static void print(){
        System.out.println("I2");
    }
}
