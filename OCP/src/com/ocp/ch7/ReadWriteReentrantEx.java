package com.ocp.ch7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by helangovan on 2/20/16.
 */
public class ReadWriteReentrantEx {

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private List<String> names = new ArrayList<>();

    public ReadWriteReentrantEx(){
        names.add("John Smith");
        names.add("Sarah Smith");
        names.add("James Johnson");
    }

    private String readNames(int i){
        Lock lock = readWriteLock.readLock();
        try{
            lock.lock();
            System.out.println("Read lock obtained");
            return names.get(i%names.size());
        }finally {
            System.out.println("Read lock released");
            lock.unlock();
        }
    }

    private void addNames(String name){
     Lock lock = readWriteLock.writeLock();
        try{
            lock.lock();
            System.out.println("Write lock obtained");
            Thread.sleep(1000);
            names.add(name);
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            System.out.println("Write lock released");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteReentrantEx ex = new ReadWriteReentrantEx();
        ExecutorService service = null;
        try{
            service = Executors.newFixedThreadPool(20);
            for(int i=0;i<100;i++){
                int employeeNumber = i;
                service.submit(()->ex.readNames(employeeNumber));
            }
            service.submit(()->ex.addNames("Grace Hopper"));
            service.submit(()->ex.addNames("Josephine Davis"));
        }finally {
            if(service!=null){
                service.shutdown();
            }
        }
    }
}
