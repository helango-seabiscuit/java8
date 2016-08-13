package com.ocp.ch7;

import java.util.concurrent.*;

/**
 * Created by helangovan on 2/2/16.
 */
public class AddData {

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        ExecutorService service = null;

        try{
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(()->30+11);
            System.out.println(result.get());
        }finally {
            if(service!=null){
                service.shutdown();;
            }
        }

        if(service!=null){
            service.awaitTermination(1, TimeUnit.MINUTES);

            if(service.isTerminated()){
                System.out.println("All tasks finished");
            }else{
                System.out.println("At least one task is still running");
            }
        }
    }
}
