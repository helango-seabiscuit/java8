package com.ocp.snippets;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by helangovan on 7/10/16.
 */
public class InfiniteStreamTest {

    public static void main(String[] args) {
        System.out.println(Stream.iterate(1,e->e+1)
                .filter(e->e>1000)
                .findFirst()
                .orElse(0));
//        System.out.println(Stream.iterate(1, e -> e + 1)
//                .count());

        System.out.println(compute1(2,1));
        System.out.println(computeCLear(2,1));
        testParallelize(5);
    }


    public static boolean isPrime(int number){
        return number>1 &&
                IntStream.range(2,number)
                         .noneMatch(i -> number%i ==0);


    }

    public static double computeCLear(int n,int k){
        return Stream.iterate(n,e->e+1)
                .filter(InfiniteStreamTest::isPrime)
                .limit(k)
                .mapToDouble(Math::sqrt)
                .sum();

    }

    public static double compute1(int n,int k){
        int count = 1;
        Double result = 0d;
        for(int start = n;count<=k;start++) {
            if (isPrime(start)) {
                result += Math.sqrt(start);
                count++;
            }
        }

        return result;
    }

    public static void testParallelize(int number){
        long start = System.nanoTime();
        System.out.println(IntStream.rangeClosed(1, number)
                .boxed()
                .parallel()
                .filter(InfiniteStreamTest::isPrime)
                .map(Math::sqrt)
                .reduce(0.0, Double::sum));
        long end = System.nanoTime();
        System.out.println(end-start/1e9);
    }
}
