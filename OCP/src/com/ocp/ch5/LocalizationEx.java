package com.ocp.ch5;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * Created by helangovan on 1/20/16.
 */
public class LocalizationEx {
    public static void main(String[] args) {

        System.out.println(Locale.GERMAN); //de
        System.out.println(Locale.GERMANY); //de_DE

        System.out.println(new Locale("fr"));
        System.out.println(new Locale("hi", "IN"));

        Locale l1 = new Locale.Builder().setRegion("US").setLanguage("en").build();
        Locale l2 = new Locale.Builder().setLanguage("en").setRegion("US").build();

        System.out.println(Locale.getDefault());
        Locale locale = new Locale("fr");
        Locale.setDefault(locale);
        System.out.println(Locale.getDefault());


        Locale us = new Locale("en","US");
        Locale france = new Locale("fr","FR");
        printProperties(us);
        System.out.println();



        //convert rb to properties
        Properties prop = new Properties();
        ResourceBundle rb = ResourceBundle.getBundle("Zoo",locale);
        //if ResourceBundle.getBundle("Zoo") uses default Locale
        rb.keySet().stream().forEach(k -> prop.put(k, rb.getString(k)));
        System.out.println(prop.getProperty("notRealProperty"));
        System.out.println(prop.getProperty("notRealProperty", "123"));

        System.out.println(Locale.forLanguageTag("fr"));
//        Duration.of(50, ChronoUnit.DECADES);

        LocalDate.of(2016, 8, 25); // 1
     //   LocalDate.of(2016, Month.APRIL, 50); // 2
     //   LocalDate date = new LocalDate(); // 3
        LocalDate.now(); // 4

        SimpleDateFormat df = new SimpleDateFormat("a", Locale.US);
        System.out.println(df.format(new Date()));
        SimpleDateFormat df1 = new SimpleDateFormat("G", Locale.US);
        System.out.println(df1.format(new Date()));
        SimpleDateFormat df2 = new SimpleDateFormat("zzzz", Locale.US);
        System.out.println(df2.format(new Date()));
        SimpleDateFormat df3 = new SimpleDateFormat("zzzz", Locale.UK);
        System.out.println(df3.format(new Date()));
        System.out.println(ZonedDateTime.now().plus(Period.ofDays(7)));
        System.out.println(ZonedDateTime.now().plus(Period.ofWeeks(1)));
        System.out.println(ZonedDateTime.now().plusDays(7));

        ZonedDateTime depart = ZonedDateTime.of(
                2016, 9, 30, 22, 0, 0, 0, ZoneId.of("Europe/Paris"));
        ZonedDateTime arrive = ZonedDateTime.of(
                2016, 10, 1, 1, 30, 0, 0, ZoneId.of("Europe/Sofia"));
        System.out.println(depart);
        System.out.println(arrive);

        Duration flightTime = Duration.between(depart, arrive); // line n1
        System.out.println("Flight time is : " + flightTime);
        france = new Locale("de","DE");
        System.out.println(france);

        printProperties(france);

    }

    public static void printProperties(Locale locale){
        ResourceBundle rb = ResourceBundle.getBundle("Zoo",locale);
        System.out.println(rb.getString("hello"));
        System.out.println(rb.getString("open"));
        String format = rb.getString("helloByName");
        String formatted = MessageFormat.format(format,"Tammy");
        System.out.println(formatted);

        Set<String> keys = rb.keySet();
        keys.stream().map(k->k+"---"+rb.getString(k)).forEach(System.out::println);
    }
}
