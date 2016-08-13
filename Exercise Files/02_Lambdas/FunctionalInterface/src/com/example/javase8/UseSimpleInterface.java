package com.example.javase8;

/**
 * Created by helangovan on 10/26/15.
 */
public class UseSimpleInterface {

    public static  void main(String args[]){
        SimpleInterface si = () -> System.out.println("Hello Lambda");
        si.doSomething();

        SimpleInterfaceWithArgs sa = (v1,v2) -> {
          int result = v1*v2;
            System.out.println("Result is "+result);
        };
        sa.doSomething(10,5);
    }
}
