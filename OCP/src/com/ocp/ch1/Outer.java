package com.ocp.ch1;

/**
 * Created by helangovan on 2/24/16.
 */
public class Outer {

    private String greeting="hi";
    private int length=5;
    private static int consn=10;

     public   class Inner{
        public int repeat=3;
         //public static int consantt=10; Inner classes cannot declare static members (fields or methods)
        public void go(){
            for(int i=0;i<repeat;i++)
                System.out.println(greeting);
            System.out.println(consn);
        }
    }

    public void calculate(){
        int width=20;
        class Inner{//modifier not allowed and no static varaibles or method declared
            public void multiply(){
                System.out.println(length*width);
            }
        }

        Inner inner = new Inner();
        inner.multiply();
        Outer.Inner in = new Outer().new Inner();
    }

    public void callInner(){
        Inner inner = new Inner();
        inner.go();
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.callInner();
       // new Outer().new Inner().go();
        outer.calculate();
    }


}
