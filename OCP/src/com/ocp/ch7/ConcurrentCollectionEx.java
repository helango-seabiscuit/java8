package com.ocp.ch7;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by helangovan on 2/3/16.
 */
public class ConcurrentCollectionEx {

    public static void main(String[] args) {
//       testConcurrentModException();
//        testConcurrentCollection();
        testCopyOnWrite();
//        testSynchronizedCollection();
    }

    public static void testConcurrentModException(){
        Map<String,Object> foodData = new HashMap<>();
        foodData.put("penguin",1);
        foodData.put("flamingo",2);

        try {
            for (String key : foodData.keySet())
                foodData.remove(key);
        }catch (Exception e){  //throws concurrentModificationException
            e.printStackTrace();
        }

        foodData = new ConcurrentHashMap<>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);
        for (String key : foodData.keySet()) { //it doesnot throw the CME
            foodData.remove(key);
            foodData.put("bird",2);
        }
        System.out.println(foodData.size());

    }

    public static void testConcurrentCollection(){
        Map<String,Integer> map = new ConcurrentHashMap<>();
        map.put("zebra",52);
        map.put("elephant", 12);
        System.out.println(map.get("elephant"));

        Queue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(31);
        System.out.println(queue.peek());
        System.out.println(queue.poll());

        Deque<Integer> deque = new ConcurrentLinkedDeque<>();
        deque.offer(10);
        deque.push(12);
        System.out.println(deque.peek());
        System.out.println(deque.pop());

        try{
            BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
            blockingQueue.offer(39);
            blockingQueue.offer(3,4,TimeUnit.SECONDS);

            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(10,TimeUnit.MILLISECONDS));
        }catch (InterruptedException e){ //thrown by offer(x,time,timeUnit) method if it didnt happen within the specified time

        }

        try{
            BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
            blockingDeque.offer(91);
            blockingDeque.offerFirst(5, 2, TimeUnit.MINUTES);
            blockingDeque.offerLast(47, 100, TimeUnit.MILLISECONDS);
            blockingDeque.offer(3, 4, TimeUnit.SECONDS);

            System.out.println(blockingDeque.poll());
            System.out.println(blockingDeque.poll(950,TimeUnit.MILLISECONDS));
            System.out.println(blockingDeque.pollFirst(200, TimeUnit.NANOSECONDS));
            System.out.println(blockingDeque.pollLast(1,TimeUnit.SECONDS));
        }catch (InterruptedException e){

        }
    }

    public static void testCopyOnWrite(){
        List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(4,3,52));
        for(Integer item:list){
            System.out.print(item+" ");
            list.add(9);
        }
        System.out.println();
        System.out.println("size:"+list.size());
    }

    public static void testSynchronizedCollection(){
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>(Arrays.asList(4,3,52)));
        synchronized (list){
            for(int data:list)
                System.out.print(data + " ");
        }

        Map<String,Object> foodData = new HashMap<>();
        foodData.put("penguin",1);
        foodData.put("flamingo",2);
        Map<String,Object> synchronizedFoodData = Collections.synchronizedMap(foodData); //even it synchronized it will not allow modification as COncurrentHashMap
        for(String key:synchronizedFoodData.keySet()){ //throws ConcurrentModificationException
            synchronizedFoodData.remove(key);
        }
    }
}
