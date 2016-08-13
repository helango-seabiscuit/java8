package com.ocp.snippets;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by helangovan on 4/3/16.
 */
public class ListToMapExample {

    public static void main(String[] args) {
        List<String> stringList = Arrays.asList("World","wonderful","places","meteora","EuropeGreece");

        //convert string list to map with lenght of each as key and value as String itself
        //Map<Integer,String> stringMap= stringList.stream().collect(Collectors.toMap(s->s.length(), s->s));
        //The above could be written as below
        Map<Integer,String> stringMap= stringList.stream().collect(Collectors.toMap(String::length, Function.identity()));

        System.out.println(stringMap);

        //Handle Duplicate - If two strigns have same lenght then map throws the IllegalStateException.
        // Instead we can handle it in code as what to do if duplicate occurs

        List<String> cards = Arrays.asList("Visa","MasterCard","American Express","Visa","Test");
        Map<String, Integer> cardMap = null;
        try {
            cardMap = cards.stream().collect(Collectors.toMap(Function.<String>identity(), String::length));

        }catch (IllegalStateException ise){
            System.out.println("Got the exception");
            ise.printStackTrace();
        }
        //The above will throw the expcetion as we not handling duplicates
        cardMap = cards.stream().collect(Collectors.toMap(Function.<String>identity(),
                  String::length,(e1,e2)->e2));//use second value
        System.out.println(cardMap);

        //To preserve the list order
        cardMap = cards.stream().collect(Collectors.toMap(Function.<String>identity(),
                   String::length,(e1,e2)->e2, LinkedHashMap::new));
        System.out.println(cardMap);



    }
}
