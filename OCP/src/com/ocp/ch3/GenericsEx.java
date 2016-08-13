package com.ocp.ch3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by helangovan on 1/29/16.
 */
public class GenericsEx {

    public static void main(String[] args) {
        List<? super IOException> exceptions = new ArrayList<Exception>();
//        exceptions.add(new Exception());
//        exceptions.add(new IOException());
//        exceptions.add(new FileNotFoundException());

//        List<? extends Bird> birds = new ArrayList<Bird>();
//        birds.add(new Sparrow());
//        birds.add(new Bird());
        List<?> bd = new ArrayList<>();

    }

    static class Sparrow extends Bird{}

    static class Bird{}

}
