package com.ocp.ch3;

import java.util.*;

/**
 * Created by helangovan on 1/29/16.
 */
public class CollectionEx {



    public static void main(String[] args) {

        List<Integer> nums = new ArrayList<>();
        nums.add(5);
        nums.add(8);
        nums.add(10);
        nums.size();
        System.out.println(nums.remove(0));//remove returns the object that is removed
        System.out.println(nums);
        System.out.println(nums.remove(new Integer(10)));//remove methos is overloaded when passed
        // object looks for match and remove by ret boolean
        System.out.println(nums);
        nums.set(0, 3);
        System.out.println(nums);


        NavigableSet<Integer> set = new TreeSet<>();

        for(int i=0;i<=20;i++){
            set.add(i);
        }

        System.out.println(set.lower(10));//9
        System.out.println(set.floor(10));//10
        System.out.println(set.ceiling(20));//20
        System.out.println(set.higher(20));//null
        System.out.println(set.higher(10));//11

        queueAndStackRepr();


//        Comparator<Duck> byWeight = new Comparator<Duck>() {
//            @Override
//            public int compare(Duck o1, Duck o2) {
//                return o1.getWeight()-o2.getWeight();
//            }
//        };
        Comparator<Duck> byWeight = (o1,o2)-> o1.getWeight()-o2.getWeight();
        byWeight = DuckHelper::compareByWeight;
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("Quack", 7));
        ducks.add(new Duck("Puddles", 10));
        ducks.add(new Duck("Puddles", 11));
        Collections.sort(ducks);
        System.out.println(ducks);
        Collections.sort(ducks, byWeight);
        System.out.println(ducks);
        ducks.sort(Comparator.comparing(Duck::getWeight));
        Collections.sort(ducks,new ChainingComparator());
        System.out.println(ducks);

        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());
        Comparator<Rabbit> comparator = (r1,r2)->r1.id - r2.id;
        Collections.sort(rabbits,comparator);
        


        Set<Rabbit> rabbits1 = new TreeSet<>((c1,c2)->c1.id-c2.id);
        rabbits1.add(new Rabbit());


        List<String> names = Arrays.asList("Fluffy","Hoppy");
        Comparator<String> comparator1 = Comparator.reverseOrder();
       // comparator1 = (s,t)->s.compareTo(t);
        int index = Collections.binarySearch(names,"Hoppy",comparator1);
        System.out.println(index);
        System.out.println(names);


        List<Integer> q = new LinkedList<>();
        q.add(10);
        q.add(12);
        q.remove(1);
        System.out.println(q);

        Queue<Integer> qu = new LinkedList<>();
        qu.add(10);
        qu.add(12);
        qu.remove(1);
        System.out.println(qu);



    }

    static class Rabbit{
        int id;
    }



    public static void queueAndStackRepr(){
        Queue<Integer> queue = new ArrayDeque<>();
        System.out.println(queue.offer(10)); //true
        System.out.println(queue.offer(4));//true //offer element added at back of list/queue
        System.out.println(queue.peek());//10
        System.out.println(queue.poll());//10
        System.out.println(queue.poll());//4
        System.out.println(queue.peek());//null

        //ArrayDequeue as Stack
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(10); //push is pushed in front of the queue/list
        stack.push(4);
        System.out.println(stack.peek());//4
        System.out.println(stack.poll());//4
        System.out.println(stack.poll());//10
        System.out.println(stack.peek());//null
    }
}

class DuckHelper {
    public static int compareByWeight(Duck d1,Duck d2){
        return d1.getWeight()-d2.getWeight();
    }

    public static int compareByName(Duck d1,Duck d2){
        return d1.getName().compareTo(d2.getName());
    }

}

class Duck implements Comparable<Duck>{

    private String name;
    private int weight;

    public Duck(String n,int weight){
        this.name = n;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name + " "+weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(Duck o) {
        return name.compareTo(o.name);
    }
}

class ChainingComparator implements Comparator<Duck>{

    public int compare(Duck d1,Duck d2){
        Comparator<Duck> c = Comparator.comparing(d->d.getName());
        c =c.thenComparing(s->s.getWeight(),(d3,d4)->d4-d3);
        return c.compare(d1,d2);
    }
}
