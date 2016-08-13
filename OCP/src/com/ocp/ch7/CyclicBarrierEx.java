package com.ocp.ch7;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by helangovan on 2/8/16.
 */
public class CyclicBarrierEx {

    private void removeAnimals(){
        System.out.println("Removing Animals");
    }

    private void cleanPen(){
        System.out.println("Cleaning Pen");
    }

    private void addAnimals(){
        System.out.println("Adding animals");
    }

    public void performTasks(CyclicBarrier c1,CyclicBarrier c2){
        try {
            removeAnimals();
            c1.await();
            cleanPen();
            c2.await();
            addAnimals();
        }catch (InterruptedException | BrokenBarrierException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        CyclicBarrierEx ex = new CyclicBarrierEx();
        try{
            service = Executors.newFixedThreadPool(3);
            CyclicBarrier c1 = new CyclicBarrier(4);
            CyclicBarrier c2 = new CyclicBarrier(4,()-> System.out.println("Pen cleaned!!!!!"));
            for(int i=0;i<4;i++){
                service.submit(()->ex.performTasks(c1,c2));
            }
        }finally {
            if(service!=null){
                service.shutdown();
            }
        }

        double amount = 10.9899;
        System.out.println(new BigDecimal(amount).setScale(2,RoundingMode.HALF_DOWN));
    }
}
