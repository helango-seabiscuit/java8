package com.ocp.ch1;

/**
 * Created by helangovan on 2/25/16.
 */
public class StaticNested {

    private int g=20;
    public static class Nested{
        private int price=6;
        static int x=10;
        public void print(StaticNested sn){
            System.out.println(sn.g);
        }
    }

    public static void main(String[] args) {
        Nested nested = new Nested();
        System.out.println(nested.price);
        StaticNested.Nested n= new StaticNested.Nested();
        StaticNested sn = new StaticNested();
        //StaticNested.Nested.x;

    }
}
