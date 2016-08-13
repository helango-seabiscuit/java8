//package com.ocp.snippets;

/**
 * Created by helangovan on 5/5/16.
 */
public class BitCheck {

    public static void main(String[] args) {

        int  k = 21;
        String binaryRep =Integer.toBinaryString(k);
        System.out.println(binaryRep);
         int l =0;
         int r = binaryRep.length()-1;
        for(;l<=r;l++,r--){
            if(binaryRep.charAt(l)!=binaryRep.charAt(r)){
                System.out.println("Not a palindrome");
                break;

            }
        }

    }

}
