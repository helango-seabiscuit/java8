package com.ocp.al;

/**
 * Created by helangovan on 5/23/16.
 */
public class StrDupRemoval {

    public static void main(String[] args) {
        System.out.println(stringClean("aabb"));
        System.out.println(stringClean("yyzzza"));// → "yza"
        System.out.println(stringClean("abbbcdd"));// → "abcd"
        System.out.println(stringClean("Hello"));// → "Helo"
        System.out.println(stringClean("XXabcYY"));// → "XabcY"
        System.out.println(stringClean("112ab445"));// → "12ab45"
        System.out.println(stringClean("Hello Bookkeeper") );//→ "Helo Bokeper"

        //Iterative Soluntion
        System.out.println(stringCleanIter("aabb"));
        System.out.println(stringCleanIter("yyzzza"));
    }

    public static  String stringClean(String input){
        if(input==null || input.isEmpty()){
            return "";
        }
        if(input.length()==1){
            return input;
        }
        char a = input.charAt(0);
        String res = stringClean(input.substring(1));
        if(a!=res.charAt(0)){
            res = a+res;
        }
        return res;
    }

    public static String stringCleanIter(String input){
        if(input==null || input.isEmpty()){
            return "";
        }
        if(input.length()==1){
            return input;
        }
        char [] inp = input.toCharArray();
        char prev = input.charAt(0);
        int currPos = 0;
        int len = input.length();

        for(int i=1;i<input.length();i++){
            if(inp[i]==prev){
                continue;
            }
            if(i!=len) {
                inp[++currPos] = inp[i];
                prev = inp[i];
            }
        }
        return new String(inp,0,currPos);

    }
}
