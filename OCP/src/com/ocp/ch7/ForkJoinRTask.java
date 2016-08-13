package com.ocp.ch7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Created by helangovan on 2/9/16.
 */
public class ForkJoinRTask extends RecursiveTask<Double>{

    private int start;
    private int end;
    private Double weights[];

    public ForkJoinRTask(Double[] weights, int start, int end){
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    protected Double compute(){

        if(end-start<=3){
            double sum = 0;
            for(int i=start;i<end;i++){
                weights[i]= (double) new Random().nextInt(100);
                System.out.println("Animal weighed: "+i);
                sum+=weights[i];
            }
            return sum;
        }else{
            int middle = start+((end-start)/2);
            System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
            RecursiveTask<Double> otherTask = new ForkJoinRTask(weights,start,middle);
            otherTask.fork();
            return new ForkJoinRTask(weights,middle,end).compute()+otherTask.join();
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new ForkJoinRTask(weights,0,weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);

        System.out.println("Sum: "+sum);
        System.out.println();
        System.out.println("Weights: ");
        Arrays.asList(weights).stream().forEach(d-> System.out.print(d.intValue() + " "));
    }
}
