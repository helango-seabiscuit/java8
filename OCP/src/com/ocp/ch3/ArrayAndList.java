package com.ocp.ch3;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by helangovan on 1/28/16.
 */
public class ArrayAndList {

    public static void main(String[] args) {
        String[] arr = {"gerbil","mouse"};
        List<String> list = Arrays.asList(arr);
       // List<String> list = Arrays.asList(arr);
        list.set(1, "test");
   //     list.add(0, "add");
        arr[0]="new";
        System.out.println(list);
        String[] arr2 = (String[])list.toArray();
        System.out.println(arr2);
        list.remove(1);

        Optional<String> op = Optional.ofNullable("Hello");

        if(op.filter(s->s.startsWith("H")).isPresent()){
            System.out.println("PRESENT AFTER OPTIONAL FILTER");
        }
    }
}
