package com.ocp.ch1;

/**
 * Created by helangovan on 2/25/16.
 */
public class AnonInner {

    abstract class SaleTodayOnly{
        abstract int dollarsOff();
    }

    public int admission(int basePrice){
        SaleTodayOnly sale = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 3;
            }

        };
        return basePrice-sale.dollarsOff();
    }
}
