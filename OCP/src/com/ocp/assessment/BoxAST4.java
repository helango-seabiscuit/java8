package com.ocp.assessment;

/**
 * Created by helangovan on 1/9/16.
 */
public class BoxAST4<T> {
    T value;

    public BoxAST4(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public static void main(String []args){
        BoxAST4<String> bs = new BoxAST4<>("a string");
        BoxAST4<Integer> bi = new BoxAST4<>(123);
        System.out.print(bs.getValue());
        System.out.print(bi.getValue());
    }
}
