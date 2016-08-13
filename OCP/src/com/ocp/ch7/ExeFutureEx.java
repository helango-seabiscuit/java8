package com.ocp.ch7;

import java.util.concurrent.*;

/**
 * Created by helangovan on 2/1/16.
 */
public class ExeFutureEx {

    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException , ExecutionException{
        ExecutorService service = null;
        try{
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(()->{
                for(int i=0;i<5000000;i++){
                    ExeFutureEx.counter++;
                }
            });

            result.get(1, TimeUnit.MILLISECONDS);
            System.out.println("Reached!");
            System.out.println(counter);

        }catch (TimeoutException e){
            System.out.println("Not reached in time");
        }finally {
            if(service!=null){
                service.shutdown();
            }
        }

    }
}
