package com.ocp.assessment;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Created by helangovan on 1/9/16.
 */
public class AST20 {

    public static void main(String args[]){
        Stream<LocalDate> s = Stream.of(LocalDate.now());
        UnaryOperator<LocalDate> u = l->l;
        s.filter(l->l!=null).map(u).peek(System.out::println);//No terminal operation so peek never runs
    }
}
