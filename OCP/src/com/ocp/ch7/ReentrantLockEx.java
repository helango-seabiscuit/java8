package com.ocp.ch7;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by helangovan on 2/20/16.
 */
public class ReentrantLockEx {

    private static  int birdcount;
    public static void main(String[] args) {

        //old synchronization way
        Object object = new Object();
        synchronized (object){
            System.out.println(" "+(++birdcount));
        }

        //with lock
        Lock lock = new ReentrantLock();
        try{
            lock.lock();
            System.out.println(" "+(++birdcount));
        }finally {
            lock.unlock();
        }

        //tryLock
        if(lock.tryLock()){
            try{
                System.out.println(" "+(++birdcount));
            }finally {
                lock.unlock();
            }
        }else{
            System.out.println("unable to acquire lock,doing something else");
        }

        //tryLock
        try {
            if(lock.tryLock(10, TimeUnit.SECONDS)){
                try{
                    System.out.println(" "+(++birdcount));
                }finally {
                    lock.unlock();
                }
            }else{
                System.out.println("unable to acquire lock,doing something else");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try{
            lock.lock();
            lock.lock();
            System.out.println("Locked twice");
        }finally {
            lock.unlock();
        }

        new Thread(()->{
           if(lock.tryLock()){
                try{
                    System.out.println("Acquired");
                }finally {
                    lock.unlock();
                }
           }else{
               System.out.println("Unavailable");
           }
        }).start();
    }
}
