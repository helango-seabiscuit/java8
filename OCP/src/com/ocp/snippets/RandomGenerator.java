// package com.ocp.snippets;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by helangovan on 7/11/16.
 */
public class RandomGenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        UtilRandom();
        mathRandom();
        secureRandom();
        threadLocalRandom();

    }

    public static void UtilRandom(){
        Random random = new Random(); //default low limit is 0

        System.out.println("UtilRandom ");
        for(int i=0;i<=5;i++){
            System.out.println(random.nextInt(50)); //set upperlimit
        }

        int min=20;
        int max=60;
        for(int i=0;i<=5;i++){
            System.out.println(random.nextInt((max-min)+1)+min);
        }
        System.out.println("UtilRandom ended");
    }

    public static void mathRandom(){
        System.out.println("mathRandom started");
        for (int i=0;i<=5;i++){
            System.out.println(Math.random());
        }

        System.out.println("mathRandom ended");
    }

    public static void secureRandom() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");

        System.out.println("secureRandom started");
        byte [] salt = new byte[16];
        sr.nextBytes(salt);
        System.out.println(salt);
        System.out.println("secureRandom ended");
    }

    public static void threadLocalRandom(){

        System.out.println("threadLocalRandom started");
        for (int counter = 1; counter <= 5; ++counter)
        {
            int randomInteger  = ThreadLocalRandom.current().nextInt(50);
            System.out.println("Generated : " + randomInteger);
        }
        System.out.println("threadLocalRandom ended");
    }
}
