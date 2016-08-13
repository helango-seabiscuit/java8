package com.ocp.ch5;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Created by helangovan on 1/16/16.
 */
public class DateTimeEx {

    private static int amcount;
    public static void main(String[] args){
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());

        //both dates are same
        LocalDate date1 = LocalDate.of(2016, Month.JANUARY,20);
        LocalDate date2 = LocalDate.of(2016, 1,20);
        System.out.println(date1);
        System.out.println(date2);

        LocalTime time1 = LocalTime.of(6,15);
        LocalTime time2 = LocalTime.of(6,15,30);
        LocalTime time3 = LocalTime.of(6,15,30,200);
        LocalTime time4 = LocalTime.of(18,15,30,200);
        System.out.println(time1); System.out.println(time2); System.out.println(time3); System.out.println(time4);

        LocalDateTime dateTime1 = LocalDateTime.of(2016,Month.JANUARY,20,6,15,30);
        LocalDateTime dateTime2 = LocalDateTime.of(date1,time2);
        System.out.println(dateTime1);
        System.out.println(dateTime2);

        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zoned1 = ZonedDateTime.of(2015,1,20,6,15,30,200,zone);
        ZonedDateTime zoned2 = ZonedDateTime.of(date1,time1,zone);
        ZonedDateTime zoned3 = ZonedDateTime.of(dateTime1,zone);
        System.out.println(zoned1);
        System.out.println(zoned2);
        System.out.println(zoned3);

        printTimeZones();

        LocalDate date = LocalDate.of(2014,Month.JANUARY,20);
        System.out.println(date);
        date = date.plusDays(2);
        System.out.println(date);
        date = date.plusWeeks(1);
        System.out.println(date);
        date = date.plusMonths(1);
        System.out.println(date);
        date = date.plusYears(5);
        System.out.println(date);


        //go back in time
        date = LocalDate.of(2020, Month.JANUARY, 20);
        LocalTime time = LocalTime.of(5,15);
        LocalDateTime dateTime = LocalDateTime.of(date,time);
        System.out.println(dateTime);
        dateTime = dateTime.minusDays(1);
        System.out.println(dateTime);
        dateTime = dateTime.minusHours(10);
        System.out.println(dateTime);
        dateTime = dateTime.minusSeconds(30);
        System.out.println(dateTime);

        period();

        duration();

        instant();

        daylightSavingsTimeCal();


    }

    private static void printTimeZones(){
        ZoneId.getAvailableZoneIds().stream()
              .filter(z->z.contains("US")|| z.contains("America"))
              .sorted()
              .forEach((z)->{
                  amcount++;
                  System.out.println(z);
                    });
        System.out.print("Total: " + amcount);

        ZoneId.getAvailableZoneIds().stream()
                .filter(z -> z.contains("India"))
                .sorted()
                .forEach(System.out::println);
    }

    private static void period(){
        LocalDate start = LocalDate.of(2015,Month.JANUARY,20);
        LocalDate end = LocalDate.of(2015,Month.MARCH,30);
        Period period = Period.ofMonths(1);

        while(start.isBefore(end)){
            System.out.println("New Toy upto:"+start);
            start = start.plus(period);
        }

        //possible Period
        Period annually = Period.ofYears(1);
        Period quarterly = Period.ofMonths(3);
        Period everyThreeWeeks = Period.ofWeeks(3);
        Period everyOtherDay = Period.ofDays(2);
        Period everyYearAndAWeek = Period.of(1,0,7);//year,month,days

        //wrong
        Period wrong = Period.ofYears(1).ofWeeks(1);
        System.out.println(wrong);//takes only last ! week P7D
        System.out.println(Period.of(0, 20, 47));
        System.out.println(Period.ofWeeks(3)); //P21D   PnYnMnD format n is number of

        //Handling in localdate and datetime but not with localTime
        LocalDate date = LocalDate.of(2015,1,20);
        LocalTime time = LocalTime.of(6,15);
        LocalDateTime dateTime = LocalDateTime.of(date,time);
        Period pd = Period.ofMonths(1);
        System.out.println(date.plus(period));
        System.out.println(dateTime.plus(period));
        //System.out.println(time.plus(period)); // throws UnsupportedTemporalTypeException

    }

    private  static void duration(){
        Duration daily = Duration.ofDays(1);
        Duration hourly = Duration.ofHours(1);
        Duration everyMinute = Duration.ofMinutes(1);
        Duration everyTenSeconds = Duration.ofSeconds(10);
        Duration everyMilli = Duration.ofMillis(1);
        Duration everyNano = Duration.ofNanos(1);

        System.out.println(daily); //PT24H   PTnHnMnS format n is number of years
        System.out.println(hourly); //PT1H
        daily = Duration.of(1, ChronoUnit.DAYS);
        System.out.println("daily = " + daily);
        LocalTime one = LocalTime.of(5,15);
        LocalTime two = LocalTime.of(6,30);
        LocalDate date = LocalDate.of(2016, 1, 20);
        System.out.println(ChronoUnit.HOURS.between(one,two));
        System.out.println(ChronoUnit.MINUTES.between(one,two));
        //System.out.println(ChronoUnit.MINUTES.between(one,date));//Throws DateTimeException

        LocalTime time = LocalTime.of(6, 15);
        LocalDateTime dateTime = LocalDateTime.of(date,time);
        Duration duration = Duration.ofHours(6);
        System.out.println(dateTime.plus(duration));
        System.out.println(time.plus(duration));
      //  System.out.println(date.plus(duration));// UnsupportedTemporalTypeException

        //ATTENTION
        Period period = Period.ofDays(1);
        Duration duration1 = Duration.ofDays(1);
        System.out.println(date.plus(period));
     //   System.out.println(date.plus(duration1));//UnsupportedTemporalTypeException

    }

    private static  void instant(){
        Instant now = Instant.now();
        Instant later = Instant.now();
        Duration duration = Duration.between(now,later);
        System.out.println(duration.toMillis());
        System.out.println(later);
        LocalDate date = LocalDate.of(2015,5,25);
        LocalTime time = LocalTime.of(11,55,00);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date,time,zone);
        Instant instant = zonedDateTime.toInstant();
        System.out.println(zonedDateTime);
        System.out.println(instant);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(date,LocalTime.now(),zone);
        duration = Duration.between(zonedDateTime,zonedDateTime2);
        System.out.println(duration);
    }

    private  static  void daylightSavingsTimeCal(){
        LocalDate date = LocalDate.of(2016,Month.MARCH,13);
        LocalTime time = LocalTime.of(1,30);
        ZoneId zone = ZoneId.of("US/Eastern");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(date,time,zone);
        System.out.println(zonedDateTime);
        zonedDateTime = zonedDateTime.plusHours(1);
        System.out.println(zonedDateTime);//display 3.30 instead of 2.30. moves one hour ahead Smart Java


        date = LocalDate.of(2016,Month.NOVEMBER,6);
        time = LocalTime.of(1,30);
        zone = ZoneId.of("US/Eastern");
        zonedDateTime = ZonedDateTime.of(date,time,zone);
        System.out.println(zonedDateTime);
        ZonedDateTime zonedDateTime1 = zonedDateTime.plusHours(1);
        System.out.println(zonedDateTime1);//same 1.30
        zonedDateTime1 = zonedDateTime.plusHours(1);
        System.out.println(zonedDateTime1); //2.30

        ZonedDateTime zdt = ZonedDateTime.from(ZonedDateTime.now());


        System.out.println("ZonedDateTime TimeZone test check for "+zonedDateTime);
        ZonedDateTime tzTime = zonedDateTime.plusWeeks(1);
        System.out.println(tzTime);
         tzTime = zonedDateTime.plusDays(7);
        System.out.println(tzTime);
         tzTime = zonedDateTime.plus(Period.ofDays(7));
        System.out.println(tzTime);





    }
}
