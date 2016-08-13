package com.ocp.ch1;

/**
 * Created by helangovan on 3/29/16.
 */
public class A {
    private int x=10;
    class B{
        private int x=20;
        class C{
            private int x=30;
            public void allTheX(){
                System.out.println(x);
                System.out.println(this.x);
                System.out.println(B.this.x);
                System.out.println(A.this.x);
            }
        }
    }

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        //B b = a.new B(); can do this
        A.B.C c = b.new C();
       // B.C c = b.new C(); can do this
        c.allTheX();
    }
}
