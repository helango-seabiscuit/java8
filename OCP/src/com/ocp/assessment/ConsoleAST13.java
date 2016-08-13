package com.ocp.assessment;

import java.io.Console;

/**
 * Created by helangovan on 1/9/16.
 */
public class ConsoleAST13 {

    public static void main(String []args){
        String line;
        Console c = System.console();
        if((line = c.readLine())!=null){
            System.out.print(line);
        }
    }
}
