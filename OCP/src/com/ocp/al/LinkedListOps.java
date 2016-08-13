package com.ocp.al;

/**
 * Created by helangovan on 6/22/16.
 */
public class LinkedListOps {

    static Node head;
    static Node tail;

    public static void main(String[] args) throws java.lang.Exception {
        Node a = new Node().new LinkNodeBuilder().setNext(new Node(2)).setNext(new Node(5)).setNext(new Node(7)).setNext(new Node(12)).build();
        Node b = new Node().new LinkNodeBuilder().setNext(new Node(3)).setNext(new Node(11)).setNext(new Node(12)).setNext(new Node(20)).build();
        display(a);
        System.out.println();
        display(b);
        System.out.println();
        Node res = mergeLists(a, b);
        display(res);
        System.out.println();
        //Node reversed = reverse(res);
        Node reversed = reverseSol(res);
        display(reversed);
        System.out.println();
        reversed = reverseSubList(reversed,3,5);
        display(reversed);


    }

    public static void display(Node r) {
        if (r == null) {
            return;
        }
        System.out.print(" " + r.num + " ");
        display(r.next);
    }

    public static Node mergeLists(Node L, Node F) {

        while (L != null && F != null) {
            if (L.num < F.num) {
                merge(L);
                L = L.next;
            } else if (L.num > F.num) {
                merge(F);
                F = F.next;
            } else {
                merge(L);
                L = L.next;
                merge(F);
                F = F.next;
            }
        }

        if (L == null && F != null) {
            merge(F);
        } else if (F == null && L != null) {
            merge(L);
        }

        return head;

    }

    public static void merge(Node t) {
        if (head == null) {
            head = t;
            tail = t;
        } else {
            tail.next = t;
            tail = tail.next;
        }
    }

    public static Node reverse(Node head) {

        if (head == null || head.next == null) {
            return head;
        }

        Node temp = head;
        Node current = head.next;
        Node tracker = current.next;
        temp.next = null;
        current.next = temp;
        while (tracker != null) {
            temp = tracker.next;
            tracker.next = current;
            current = tracker;
            tracker = temp;
        }
        return current;
    }

    public static Node reverseSol(Node head) {
        Node prev = null;
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.next;
            current.next = prev;
            prev = current;
            current = temp;
        }
        return prev;

    }

    public static Node reverseSubList(Node head,int s,int f){
        Node prev = null;
        Node current = head;
        Node temp = null;
        Node beforeRev = null;
        Node firstRev = null;
        int count = 1;
        while (current!=null){
            temp = current.next;
            if(count==s){
                beforeRev = prev;
                firstRev = current;
            }
            if(count>s && count<=f) {
                current.next = prev;
            }
            if(count == f){
                if(s!=1) {
                    beforeRev.next = current;
                }
                firstRev.next = temp;
                //break;
            }
            prev = current;
            current = temp;
            count++;
        }
        if(s==1){
            return prev;
        }
        return head;
    }

}

