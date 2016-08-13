package com.ocp.ch1;

/**
 * Created by helangovan on 2/23/16.
 */
public enum Season {

    WINTER{
        public  void printHours(){
            System.out.println(this.name());
        }
    },
    SPRING{
        public  void printHours(){
            System.out.println(this.name());
        }
        public void greet(){
            System.out.println("Welcome Spring");
        }
    },
    SUMMER{
        public  void printHours(){
            System.out.println(this.name());
        }
    },
    FALL{
        public  void printHours(){
            System.out.println(this.name());
        }
    };

    public abstract void printHours();
    public void greet(){
        System.out.println("Welcome");
    }

    public static void main(String[] args) {
        for(Season s:values()){
            s.printHours();
            s.greet();
        }
    }
}
