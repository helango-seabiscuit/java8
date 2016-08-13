import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by helangovan on 11/29/15.
 */
public class PracPrograms {

    public static void main(String args[]){
        LocalDate programmersDay = LocalDate.of(2014, 1, 1).plus(255, ChronoUnit.DAYS);
        LocalDate programmersDay2 = LocalDate.of(2014, 1, 1).plus(Period.ofDays(255));
        System.out.println(programmersDay);
        System.out.println(programmersDay2);

        LocalDate leapYearAddOne = LocalDate.of(2000, 2, 29).plusYears(1);
        LocalDate leapYearAddFour = LocalDate.of(2000, 2, 29).plusYears(4);
        Duration leapYear = Duration.ofDays(255).multipliedBy(4);
        long days = leapYear.toDays();
        LocalDate leapYearAddOneTimeFour = LocalDate.of(2000, 2, 29).plusDays(days);
        System.out.println(leapYearAddOne);
        System.out.println(leapYearAddFour);
        System.out.println(leapYearAddOneTimeFour);

        LocalDate today = LocalDate.now();
        System.out.println("After Adjuster:"+today.with(next(w -> w.getDayOfWeek().getValue() < 6)));

        Instant start = LocalDate.of(1986,7,22).atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = Instant.now();
        Duration lived = Duration.between(start,end);
        System.out.println(lived.toDays());
         printCalendar(8,1999);
        friday13thOf20thCentury();
        todayInDifferentZone();

        //
        ZonedDateTime startTime =ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.of("America/Los_Angeles"));
        System.out.println(startTime.withZoneSameInstant(ZoneId.of("Europe/Berlin")));
        Duration duration = Duration.ofHours(10).plusMinutes(50);
        printEndTime(startTime, ZoneId.of("Europe/Berlin"),duration);

        calculateTimeDifference();

    }


    public static TemporalAdjuster next(Predicate<LocalDate> satisfier){
        TemporalAdjuster NEXT_ADJUSTER = w -> {
            LocalDate result = (LocalDate) w;
            while(!satisfier.test(result)){
                result = result.plusDays(1);
            }
            return result;
        };
        return  NEXT_ADJUSTER;
    }

    public static void printCalendar(int month,int year){
        int start = 1;
        LocalDate startDate = LocalDate.of(year,month,start);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);
        System.out.println(startDate);
        System.out.println(endDate);
        int [] weekDays = new int[8];
        for(int i=0;i<8;i++)
            weekDays[i]=0;

        while (!startDate.isAfter(endDate)){
         weekDays[startDate.getDayOfWeek().getValue()] = startDate.getDayOfMonth();
            if(startDate.getDayOfWeek().getValue()==7){
                printLine(weekDays);
                for(int i=0;i<8;i++)
                    weekDays[i]=0;
            }
            startDate = startDate.plusDays(1);

        }
        printLine(weekDays);
    }



    public static void printLine(int[] days){
        for(int i:days){
            if(i==0){
                System.out.print("  ");
            }else{
                System.out.print(" "+i);
            }
        }
        System.out.println();
    }

    public static void friday13thOf20thCentury(){
        LocalDate startDate = LocalDate.of(1900,1,13);
        LocalDate endDate = LocalDate.of(1999,12,31);
        while (!startDate.isAfter(endDate)){
            if(startDate.getDayOfWeek().getValue()==5){
                System.out.println(startDate);
            }
            startDate = startDate.plusMonths(1);
        }
    }

    public static void todayInDifferentZone(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        List<ZonedDateTime> zonedToday = zoneIds.stream().map(s -> ZonedDateTime.now(ZoneId.of(s))).collect(Collectors.toList());
        System.out.println(zonedToday);
    }

    public static void printEndTime(ZonedDateTime startDateTime,ZoneId end, Duration duration){
        ZonedDateTime dateTime = startDateTime.plus(duration);
        ZonedDateTime endDateTime = dateTime.withZoneSameInstant(end);
//       ZonedDateTime endDateTime = ZonedDateTime.of(dateTime.getYear(),dateTime.getMonth().getValue(),dateTime.getDayOfMonth(),
//               dateTime.getHour(),dateTime.getMinute(),dateTime.getSecond(),dateTime.getNano(),end);

        System.out.print(endDateTime);
    }

    public static void calculateTimeDifference(){
        ZonedDateTime frankfurtDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(14, 5), ZoneId.of("Europe/Berlin"));
        ZonedDateTime laDateTime = ZonedDateTime.of(LocalDate.now(), LocalTime.of(16, 40), ZoneId.of("America/Los_Angeles"));


        System.out.print(Duration.between(frankfurtDateTime, laDateTime).getSeconds());

    }
}
