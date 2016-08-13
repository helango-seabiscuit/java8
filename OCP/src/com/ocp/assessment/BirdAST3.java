package com.ocp.assessment;

import java.io.*;

/**
 * Created by helangovan on 1/9/16.
 */

class Tail{}

public class BirdAST3 implements Serializable {

    private String name;
    private transient int age;
    private Tail tail;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    public static void main(String args[])  {
        try (InputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream("birds.dat")))) {
            //BirdAST3 b = is.readObject();
           // BirdAST3 b = is.read();
        }catch (FileNotFoundException fnf){

        }catch ( IOException e){

        }
    }
}
