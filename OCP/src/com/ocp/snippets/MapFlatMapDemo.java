package com.ocp.snippets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by helangovan on 3/5/16.
 */
public class MapFlatMapDemo {

    public static void main(String[] args) {

        List<String> loseWeight = Arrays.asList("Avacados", "Beans", "Brocolli", "Oats");

        System.out.println("Before mapping :"+loseWeight);
        List<Integer> lsize = loseWeight.stream().map(String::length).collect(Collectors.toList());
        System.out.println("After Mapping:" +lsize);

        List<List<Integer>> listsLists = new ArrayList<>();
        listsLists.add(Arrays.asList(2,4));
        listsLists.add(Arrays.asList(1,3));
        listsLists.add(Arrays.asList(5,6,7));

        System.out.println("Before flatMap: "+listsLists);
        List<Integer> res = listsLists.stream().flatMap(l->l.stream()).collect(Collectors.toList());

        System.out.println("After flatMap: "+res);
    }

}
