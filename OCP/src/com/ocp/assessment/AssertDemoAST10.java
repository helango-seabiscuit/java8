package com.ocp.assessment;

/**
 * Created by helangovan on 1/9/16.
 */
public class AssertDemoAST10 {

    public static void main(String[] args){
        Integer x = 10;
        x++;
        assert x == null && x >=0; //have to enable assertion
        System.out.print(x);
    }
}
