package com.ocp.ch7;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

/**
 * Created by helangovan on 2/2/16.
 */
public class AmbiguousLambdaSample {

    public static void useCallable(Callable<Integer> expression){};
    public static void useSupplier(Supplier<Integer> expression){};
    public static void use(Supplier<Integer> expression){}
    public static void use(Callable<Integer> expression){}
    public static void useRunnable(Runnable expression){}

    public static void main(String[] args) {
        useCallable(() -> {
            throw new IOException();
        });
        //useSupplier(()-> {throw new IOException();});
       // use(()->{throw new IOException();});
        use((Callable<Integer>) () -> {
            throw new IOException();
        });
//        try {
//            useRunnable((Runnable) () -> {
//                throw new IOException();
//            });
//        }catch (Exception e){
//
//        }
    }

}
