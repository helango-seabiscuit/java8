package com.ocp.ch5;

import java.nio.file.Files;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

/**
 * Created by helangovan on 1/20/16.
 */
public class NumberFormatEx {

    public static void main(String[] args) {
        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear/12;
        NumberFormat us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(attendeesPerMonth));
        NumberFormat ger = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(ger.format(attendeesPerMonth));
        NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(attendeesPerMonth));

        NumberFormat ust = NumberFormat.getCurrencyInstance(Locale.US);
        double price = 48;
        System.out.println(ust.format(price));


        NumberFormat en = NumberFormat.getInstance(Locale.US);
        NumberFormat fr = NumberFormat.getInstance(Locale.FRANCE);
        String s ="40.45";
        try {
            System.out.println(en.parse(s));
            System.out.println(fr.parse(s));

            String amt="$92,807.99";
            NumberFormat cf = NumberFormat.getCurrencyInstance();
            double value = (Double)cf.parse(amt);
            System.out.println(value);
        }catch (ParseException pe){
            pe.printStackTrace();
        }

//        try{
//             int l = 5;
//        }catch(ParseException aio){
//
//        }

       formatDate();
    }

    public static void formatDate() {
        LocalDate date = LocalDate.of(2020, Month.JANUARY,20);
        System.out.println(date.getDayOfWeek());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getDayOfYear());

        LocalTime time = LocalTime.of(11,12,34);
        LocalDateTime dateTime = LocalDateTime.of(date,time);
        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        DateTimeFormatter shortDateTime = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        System.out.println(shortDateTime.format(dateTime));
        System.out.println(shortDateTime.format(date));
       // System.out.println(shortDateTime.format(time)); throws UnsupportedTemporalTypeException

        System.out.println(dateTime.format(shortDateTime));
        System.out.println(date.format(shortDateTime));

       // System.out.println(time.format(shortDateTime));

        DateTimeFormatter shortF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        DateTimeFormatter mediumF = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(shortF.format(dateTime));
        System.out.println(mediumF.format(dateTime));

        //parse
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MMM dd,yyyy,hh:mm");
        System.out.println(dateTime.format(f));

        DateTimeFormatter t = DateTimeFormatter.ofPattern("hh:mm");
//        System.out.println(f.format(dateTime));
//        System.out.println(f.format(date));
        System.out.println(t.format(time));

        f = DateTimeFormatter.ofPattern("MM dd yyyy");
        LocalDate dt = LocalDate.parse("01 02 2015",f);
        LocalTime tm = LocalTime.parse("11:22");
        System.out.println(dt);
        System.out.println(tm);

        LocalDate ld = LocalDate.of(2015, Month.APRIL, 1);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");
        System.out.println(dtf.format(ld));

        DateFormat dateFormatter;

        dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, Locale.UK);
        System.out.println(dateFormatter.format(new Date()));
    }
}
