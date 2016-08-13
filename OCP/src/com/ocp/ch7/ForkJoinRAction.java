package com.ocp.ch7;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

/**
 * Created by helangovan on 2/9/16.
 */
public class ForkJoinRAction extends RecursiveAction{

    private int start;
    private int end;
    private Double weights[];

    public ForkJoinRAction(Double[] weights,int start,int end){
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    protected void compute(){

        if(end-start<=3){
            for(int i=start;i<end;i++){
                weights[i]= (double) new Random().nextInt(100);
                System.out.println("Animal weighed: "+i);
            }
        }else{
            int middle = start+((end-start)/2);
            System.out.println("[start="+start+",middle="+middle+",end="+end+"]");
            invokeAll(new ForkJoinRAction(weights,start,middle),
                    new ForkJoinRAction(weights,middle,end));
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new ForkJoinRAction(weights,0,weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        System.out.println();
        System.out.println("Weights: ");
        Arrays.asList(weights).stream().forEach(d-> System.out.print(d.intValue()+" "));
    }
}
