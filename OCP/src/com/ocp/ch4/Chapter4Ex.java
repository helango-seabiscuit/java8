package com.ocp.ch4;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.*;

/**
 * Created by helangovan on 1/11/16.
 */
public class Chapter4Ex {

    public static void main(String[] args){
        Supplier<LocalDate> s1 = LocalDate::now;
        Supplier<LocalDate> s2 = ()->LocalDate.now();

        LocalDate d1 = s1.get();
        LocalDate d2 = s2.get();
        System.out.print(d1);
        System.out.println(d2);

        //
        Supplier<StringBuilder> sb1 = StringBuilder::new;
        Supplier<StringBuilder> sb2 = ()->new StringBuilder();
        System.out.print(sb1.get());
        System.out.print(sb2.get());

        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = (s)->System.out.println(s);
        c1.accept("Annie");
        c2.accept("Annie");

        Map<String,Integer> map = new HashMap<>();
        BiConsumer<String,Integer> b1 = map::put;
        BiConsumer<String,Integer> b2 = (String k,Integer v)-> {map.put(k,v);return;};
        b1.accept("chicken",7);
        b2.accept("chick",1);
        System.out.print(map);

        //Predicate and Biprdeicate
        Predicate<String> p1 = String::isEmpty;
        Predicate<String> p2 = (s)->s.isEmpty();
        System.out.print(p1.test(""));
        System.out.print(p2.test(""));
        BiPredicate<String,String> bp1 = String::startsWith;
        BiPredicate<String,String> bp2 = (st,prefix)->st.startsWith(prefix);
        System.out.print(bp1.test("chicken", "chick"));
        System.out.println(bp2.test("chicken", "chick"));

        Predicate<String> egg = s->s.contains("egg");
        Predicate<String> brown = s->s.contains("brown");
        Predicate<String> brownEggs = egg.and(brown);
        Predicate<String> otherEggs = egg.and(brown.negate());

        //Function and BiFunction
        Function<String,Integer> f1 = String::length;
        Function<String,Integer> f2 = x->x.length();
        System.out.print(f1.apply("cluck"));
        System.out.println(f2.apply("cluck"));

        BiFunction<String,String,String> bf1 = String::concat;
        BiFunction<String,String,String> bf2 = (st,stAdd)->st.concat(stAdd);
        System.out.print(bf1.apply("baby","chick"));
        System.out.println(bf2.apply("chicken", "chick"));

        //UnaryOperator
        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x->x.toUpperCase();
        System.out.print(u1.apply("chicken"));
        System.out.println(u2.apply("chicken"));

        BinaryOperator<String> bo1 = String::concat;
        BinaryOperator<String> bo2 = (st,stadd)->st.concat(stadd);
        System.out.print(bo1.apply("chicken", "chick"));
        System.out.println(bo2.apply("chicken", "chick"));

        //Not compiling code
//        Function<List<String>> ex1 = x->x.get(0);
//        UnaryOperator<Long> ex2 = (Long l) -> 3.14;
//        Predicate ex3 = String::isEmpty;
//
        optionalSample();


    }

    public static void optionalSample(){
        Optional<String> o = Optional.ofNullable("hello");
        o.ifPresent(s -> {
            s = s.concat(" added");
            System.out.println(s);
        });

        Optional<Double> opt = average();
        System.out.print(opt.orElse(Double.NaN));
        System.out.println(opt.orElseGet(() -> Math.random()));
        System.out.print(opt.orElseThrow(() -> new IllegalArgumentException()));


    }

    public static Optional<Double> average(int... scores){
        if(scores.length==0)
            return Optional.empty();
        int sum =0;
        for(int s:scores){
            sum+=s;
        }
        return Optional.ofNullable((double) sum / scores.length);
       // return Optional.of((double)sum/scores.length);
    }

}
