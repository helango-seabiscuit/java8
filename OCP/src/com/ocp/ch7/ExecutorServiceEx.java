package com.ocp.ch7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by helangovan on 2/1/16.
 */
public class ExecutorServiceEx {

    public static void main(String[] args) {
        ExecutorService service = null;

        try{
            service = Executors.newFixedThreadPool(5);
            System.out.println("begin");
            service.execute(() -> System.out.println("Printing Zoo Inventory"));
            service.execute(()->{
                for(int i=0;i<3;i++){
                    try {
                        System.out.println("Printing record: " + i);
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                }
            });
            service.execute(()-> System.out.println("Printing Zoo Inventory"));
            System.out.println("end");
        }finally {
            if(service!=null){
                service.shutdown();
            }
        }
    }
}


