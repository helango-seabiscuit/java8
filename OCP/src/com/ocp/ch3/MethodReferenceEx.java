package com.ocp.ch3;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by helangovan on 1/30/16.
 */
public class MethodReferenceEx {

    public static void main(String[] args) {
        Consumer<List<Integer>> methodRef1 = Collections::sort;
        Consumer<List<Integer>> lambda1 = (l)->Collections.sort(l);

        String str="abc";
        Predicate<String> methodRef2 = str::startsWith;
        Predicate<String> lambda2 = s->str.startsWith(s);

        Predicate<String> methodRef3 = String::isEmpty;
        Predicate<String> lambda3 = s->s.isEmpty();

        Supplier<ArrayList> methodRef4 = ArrayList::new;
        Supplier<ArrayList> lambda4 = ()->new ArrayList();

        List<String> list = methodRef4.get();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list);
        list.removeIf((st) -> st.startsWith("A"));
        System.out.println(list);

        List<Integer> list1 = Arrays.asList(1,2,3);
        list1.replaceAll(x -> x * 2);
        System.out.println(list1);

        list1.forEach(c -> System.out.println(c));
        list1.forEach(System.out::println);

        Map<String,String> favorites = new HashMap<>();
        favorites.put("Jenny","Bus Tour");
        System.out.println(favorites);
        favorites.put("Jenny", "Tram"); //old value replaced by new value
        System.out.println(favorites);
        favorites.put("Tom", null);
        System.out.println(favorites);
        favorites.putIfAbsent("Jenny", "Bus Tour");
        favorites.putIfAbsent("Sam", "Tram");
        favorites.putIfAbsent("Tom", "Tram");
        System.out.println(favorites);
        //favorites.replace()

        mergeCheck();

        computeIfPresentAbsent();

        System.out.println(
                Stream.of("Trois", "Un", "Quatre", "Deux")
                        .sorted(Comparator.comparing(String::length).reversed())
                        .collect(Collectors.toList()));

    }

    public static void mergeCheck(){
        System.out.println("Merge Check");
        BiFunction<String,String,String> mapper = (v1,v2)->v1.length()>v2.length()?v1:v2; // order to mapping function - oldValue,newValue
        Map<String,String> favorites = new HashMap<>();
        favorites.put("Jenny","Bus Tour");
        favorites.put("Tom","Tram");

        String jenny = favorites.merge("Jenny","Skyride",mapper);
        String tom = favorites.merge("Tom","Skyride",mapper);
        System.out.println(favorites);
        System.out.println(jenny);
        System.out.println(tom);

        favorites.put("Sam", null);
        System.out.println(favorites);
        favorites.merge("Sam", "Skyride", mapper);
        System.out.println(favorites);

        //when mapper returns null it removes element from the map
        BiFunction<String,String,String> mapper1 = (v1,v2)->null;
        favorites = new HashMap<>();
        favorites.put("Jenny","Bus Tour");
        favorites.put("Tom","Bus Tour");

        System.out.println(favorites);
        favorites.merge("Jenny", "Skyride", mapper1);
        favorites.merge("Sam", "Skyride", mapper1);
        System.out.println(favorites);
        favorites.merge("Tom", "Skyride", mapper1);
        System.out.println(favorites);
    }

    public static void computeIfPresentAbsent(){
        System.out.println("COMPUTE IF PRESENT ABSENT");
        Map<String,Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);

        BiFunction<String,Integer,Integer> mapper = (k,v)->v+1;
        Integer jenny= counts.computeIfPresent("Jenny", mapper);
        Integer sam= counts.computeIfPresent("Sam", mapper);
        System.out.println(counts);
        System.out.println(jenny);
        System.out.println(sam);

        counts = new HashMap<>();
        counts.put("Jenny",15);
        counts.put("Tom",null);

        Function<String,Integer> mapper1 = (k)->1;
        int jenn = counts.computeIfAbsent("Jenny",mapper1);
        int sm = counts.computeIfAbsent("Sam",mapper1);
        int tm = counts.computeIfAbsent("Tom",mapper1);
        System.out.println(counts);

        //mapper returns null
        counts = new HashMap<>();
        counts.put("Jenny",1);

        counts.computeIfPresent("Jenny", (k, v) -> null);
        counts.computeIfAbsent("Sam", k -> null);
        System.out.println(counts);




        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.forEach((k, v) -> System.out.println("Key : " + k + ", value :" + v));

        map.compute("A", (k, v) -> (v == null) ? 0 : v + 1);
        map.compute("A", (k, v) -> (v == null) ? 0 : v + 1);
        map.compute("B", (k, v) -> (v == null) ? 0 : v + 1);
        System.out.println(map);



    }
}


