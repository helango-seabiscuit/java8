package com.ocp.assessment;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by helangovan on 1/9/16.
 */
public class MagicStreamAST11 {

    private static void magic(Stream<Integer> s){
        Optional o = s.filter(x->x<5).limit(3).max((x,y)->x-y);
        System.out.print(o.get());
    }

    public static void main(String[] args){
      //  magic(Stream.empty());
       // magic(Stream.iterate(1,x->x++));
        magic(Stream.of(5,10));
    }
}
