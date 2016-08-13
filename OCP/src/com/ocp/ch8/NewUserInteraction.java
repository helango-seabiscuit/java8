package com.ocp.ch8;

import java.io.Console;
import java.util.Collection;

/**
 * Created by helangovan on 2/15/16.
 */
public class NewUserInteraction {

    public static void main(String[] args) {
        Console console = System.console();
        if(console!=null){
            String userInput = console.readLine();
            console.writer().append("");
            console.writer().println("You entered the following: "+userInput);
        }else{
            System.out.println("Null console");
        }

    }

}
