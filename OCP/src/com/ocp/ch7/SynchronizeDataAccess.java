package com.ocp.ch7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by helangovan on 2/2/16.
 */
public class SynchronizeDataAccess {

    private int sheepCount=0;
    //private AtomicInteger sheepCount=new AtomicInteger(0);
    private Lock lock = new ReentrantLock();

    private void incrementAndReport(){
       // System.out.print((++sheepCount) + " ");
        synchronized (this) {
            System.out.print((++sheepCount) + " ");
        }

        try{
          lock.lock();
            System.out.print((++sheepCount) + " ");
        }finally {
           lock.unlock();
        }
      //  System.out.print((sheepCount.incrementAndGet()) + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try{
            service = Executors.newFixedThreadPool(20);
            SynchronizeDataAccess dataAccess = new SynchronizeDataAccess();
            for(int i=0;i<10;i++){
                service.submit(()->dataAccess.incrementAndReport());
            }
        }finally {
            if(service!=null){
                service.shutdown();
            }
        }
    }
}
