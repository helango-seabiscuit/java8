package com.ocp.ch2;

/**
 * Created by helangovan on 7/14/16.
 */

interface  Fly{
    public int getWingSpan() throws Exception;
    public static final int MAX_SPEED=100;

    public default void land(){
        System.out.println("Animal is landing");
    }

    public static double calculateSpeed(float distance,double time){
        return  distance/time;
    }
}
public  class  Eagle implements Fly{
  public int getWingSpan(){
      return 15;

  }

    public void land(){
        System.out.println("Eagle is driving fast");
        System.out.println(MAX_SPEED);
    }

    public static void main(String[] args) {
        Fly eg = new Eagle();
        Eagle e = new Eagle();


    }

}
