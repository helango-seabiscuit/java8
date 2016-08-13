package com.ocp.ch7;

import java.util.Arrays;

/**
 * Created by helangovan on 2/10/16.
 */
public class TestSamples {

    public static void main(String[] args) {
        System.out.println(Arrays.asList("duck","chicken","flamingo","pelican")
           .parallelStream()
           .parallel()
           .reduce(0,
                   (c1, c2) -> c2.length() + c1,
                   (s1, s2) -> s1 + s2));
    }
}
