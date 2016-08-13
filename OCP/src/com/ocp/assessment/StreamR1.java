package com.ocp.assessment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Created by helangovan on 1/10/16.
 */
public class StreamR1 {

    public static void main(String args[]){
        Stream<String> st =Stream.iterate("", (s) -> s + "1");
        System.out.print(st.limit(2).map(x->x+"2"));
        Stream<Integer> s = Stream.of(1);
        Stream<Integer> si = Stream.iterate(5, x -> ++x);
        System.out.println(si.limit(10).collect(Collectors.partitioningBy(x -> x % 2 == 0)));
    }
}
