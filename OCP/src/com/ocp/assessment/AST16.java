package com.ocp.assessment;

import java.time.Duration;
import java.time.Period;

/**
 * Created by helangovan on 1/9/16.
 */
public class AST16 {

    public static void main(String[] args){
        Duration dur = Duration.ofDays(1);
        Period per = Period.ofDays(1);
        String d = dur.toString();
        String p = per.toString();

        boolean b1 = d==p;
        boolean b2 = d.equals(p);
        System.out.print(b1+" "+b2);
    }
}
