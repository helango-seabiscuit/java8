package com.ocp.ch1.species;

import com.ocp.ch1.BigCat;
import com.sun.xml.internal.bind.v2.runtime.RuntimeUtil;

/**
 * Created by helangovan on 7/13/16.
 */
public class Lynx extends BigCat {

    public static void main(String[] args) {
        Lynx cat = new Lynx();
        System.out.println(cat.hasFur);
        System.out.println(cat.name);

        BigCat ct = new Lynx();
        System.out.println(ct.name);
        //System.out.println(ct.hasFur);

        BigCat ct1 = new BigCat();
        System.out.println(ct1.name);
    }

    @Override
    public String toString(){
        return "";
    }
}
