package com.ocp.al;

import java.math.BigDecimal;

/**
 * Created by helangovan on 5/15/16.
 */
public class Strcmp {

    public static void main(String[] args) {

        BigDecimal amountToNotify = BigDecimal.valueOf(2.3456);
        if(BigDecimal.ZERO.compareTo(amountToNotify)< 0){
            System.out.println("Pass");

        }

        BigDecimal p = new BigDecimal("-0.0015541077");
        BigDecimal i = new BigDecimal("0.0015541077");
        System.out.println(p.add(i));
       Node a = new Node().new LinkNodeBuilder().setNext(new Node('g')).setNext(new Node('e')).setNext(new Node('e')).setNext(new Node('s')).build();
       Node b = new Node().new LinkNodeBuilder().setNext(new Node('g')).setNext(new Node('e')).setNext(new Node('e')).setNext(new Node('k')).build();
       Node absSort = new Node().new LinkNodeBuilder().setNext(new Node(1)).setNext(new Node(2)).setNext(new Node(-3))
                     .setNext(new Node(4)).setNext(new Node(-5)).setNext(new Node(6)).setNext(new Node(-7)).setNext(new Node(-8)).build();

        System.out.println(comp(a,b));
        Node ordered = arrangeSortedNums(absSort);
        printListNum(ordered);

    }

    public static void printListNum(Node a){
        while (a!=null){
            System.out.print("("+a.getNum()+")");
            a=a.getNext();
            if(a!=null){
                System.out.print("-->");
            }
        }
    }

    public static  int comp(Node a ,Node b){
        if(a==null && b==null){
            return 0;
        }
        else if(a!=null && b==null){
            return  1;
        }else if(a==null && b!=null){
            return  -1;
        }

        int cmp = (a.data > b.data)?1:(a.data ==b.data)?0:-1;

        if(cmp ==0){
            cmp = comp(a.next,b.next);
        }
        return cmp;
    }

    public static Node arrangeSortedNums(Node head){
        Node tempHead = head;
        Node first = head.next;
        Node prev = head;
        while (first!=null){
            if(first.getNum()<0){
                prev.setNext(first.getNext());
                first.setNext(tempHead);
                tempHead = first;
                first = prev.getNext();
            }else {
                prev = first;
                first = first.next;
            }
        }

        return  tempHead;

    }

}

class Node{
    public Node next;
    public char data;
    public int num;
    Node(){}
    Node (int n){
        this.num = n;
    }
    Node(char a){
        this.data = a;
    }

     public Node getNext() {
         return next;
     }

     public void setNext(Node next) {
         this.next = next;
     }

     public char getData() {
         return data;
     }

     public void setData(char data) {
         this.data = data;
     }

    public int getNum() {
        return num;
    }

    public void setNum(int n) {
        this.num = n;
    }

     class LinkNodeBuilder {
         Node previous;
         Node head;
         public  Node build(){
            return  head;
         }

         public LinkNodeBuilder setNext(Node a ){
             if(head == null){
                 head = a;
                 previous =a;
             }else {
                 previous.setNext(a);
                 previous = a;
             }
             return  this;

         }
     }
 }
