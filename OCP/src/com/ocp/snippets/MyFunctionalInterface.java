package com.ocp.snippets;

/**
 * Created by helangovan on 3/30/16.
 */

/**
 * If an interface declares an abstract method overriding one of the public methods of java.lang.Object,
 * that also does not count toward the interface’s abstract method count
 * since any implementation of the interface will have an implementation from java.lang.Object
 */
@FunctionalInterface
public interface MyFunctionalInterface {
    public void firstWork();
    @Override
    public String toString();                //Overridden from Object class
    @Override
    public boolean equals(Object obj);        //Overridden from Object class

    public default void print(){}
}
