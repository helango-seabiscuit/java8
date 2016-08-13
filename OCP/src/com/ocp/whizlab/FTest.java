package com.ocp.whizlab;

import java.nio.file.Paths;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.*;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by helangovan on 3/21/16.
 */
public class FTest {

    public static void main(String[] args) {
        q1();
        q2();
        q3();
        q4();
        q5();
        q6();
        q7();
        q8();
        q9();
        q10();
        q11();
        q12();
        q13();
        q14();
        q15();
        q16();
        q17();
        q18();
        q19();
        q20();
        q21();
        q22();
        q23();
        q24();
        q25();
        q26();
        q27();
        q28();
        q29();
        q30();
        q31();
        q32();
        q33();
        q34();
        q35();
        q36();
        q37();
        q38();
        q39();
        q40();
    }

    public static void q1() {
        List<Integer> ins = new ArrayList<>();
        ins.add(12);
        ins.add(2);
        ins.add(10);
        ins.add(4);
        //int max= ins.stream().max(Integer::compare); will not compile because max returns Optional<Integer>
        Optional<Integer> max = ins.stream().max(Integer::compare);
        System.out.println("----Q1----");
        System.out.println(max.get() + ins.stream().count());
        System.out.println("----Q1----");

    }

    public static void q2() {
        Stream<Integer> st = Stream.of(1, 2, 3);
        System.out.println("----Q2----");
        System.out.println(st.peek(System.out::print).findAny().orElse(0));
        System.out.println("----Q2----");

    }

    public static void q3() {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        Consumer<String> cons = System.out::print;
        BiConsumer<Integer, String> bicons = (k, v) -> System.out.print(v);
        //  map.forEach(cons); -> compile fails as it accepts only biconsumer
        System.out.println("----Q3----");
        map.forEach(bicons);
        System.out.println("\n----Q3----");
    }

    public static void q4() {
        IntStream is = IntStream.range(1, 6);
        System.out.println("----Q4----");
        System.out.println(is.average());
        System.out.println("----Q4----");
    }

    public static void q5() {
        IntStream is = IntStream.of(1, 4, 5, 2);
        IntSummaryStatistics sumy = is.summaryStatistics();
        sumy.accept(3);
        System.out.println("----Q5----");
        System.out.println(sumy.getSum());
        sumy.accept(6);
        System.out.println(sumy.getSum());
        System.out.println("----Q5----");
    }

    public static void q6(){
        System.out.println("----Q6----");
        String strs[] = {"A","B","C"};
        Arrays.parallelPrefix(strs, String::concat);
        System.out.println(Arrays.deepToString(strs));
        System.out.println("----Q6----");
    }

    public static void q7(){
        System.out.println("----Q7----");
     Stream<Integer> stream = Stream.of(1,2,3,5,7,8).parallel();
        stream.sequential(); //makes it sequential eventhough it didnt assign to another stream
        System.out.println(stream.isParallel());
        stream.forEach(System.out::print);
        System.out.println();
        System.out.println("----Q7----");
    }

    public static void q8(){
        System.out.println("----Q8----");
        Year year = Year.of(2015);
        YearMonth ym = year.atMonth(Month.DECEMBER);
        LocalDate date = ym.atEndOfMonth();
        System.out.println(date);
        System.out.println(date.getDayOfYear());
        System.out.println("----Q8----");
    }

    public static void q9(){
        System.out.println("----Q9----");
       I i = new I(){};
        I.method();
        i.print();
        I.main(new String[]{});
        System.out.println("----Q9----");
    }

    public static void q10(){
        System.out.println("----Q10----");
        LocalDateTime ldtStart = LocalDateTime.of(2015, 10, 23, 12, 7, 1);
        LocalDateTime ldtEnd   = LocalDateTime.of(2015, 10, 23, 15, 8, 2);

        LocalDateTime ldtStartNew = ldtStart.truncatedTo(ChronoUnit.MINUTES); //truncates anything less than that value
        System.out.println("Start time truncated to minutes : " + ldtStartNew);

        LocalDateTime ldtEndNew = ldtEnd.truncatedTo(ChronoUnit.HOURS);
        System.out.println("End time truncated to hours : " + ldtEndNew);
        ldtEndNew = ldtEnd.truncatedTo(ChronoUnit.DAYS);
        System.out.println("End time truncated to days:" + ldtEndNew);
        //ldtEndNew = ldtEnd.truncatedTo(ChronoUnit.MONTHS);  throws java.time.temporal.UnsupportedTemporalTypeException:
        // Unit is too large to be used for truncation
        System.out.println("----Q10----");
    }

    public static void q11(){
        System.out.println("----Q11----");
        LocalDateTime ldtStart = LocalDateTime.of(2015, 10, 23, 12, 7, 1);
        LocalDateTime ldtEnd   = LocalDateTime.of(2015, 10, 24, 15, 20, 2);

        long numberOfDays = ChronoUnit.DAYS.between(ldtStart, ldtEnd); //days usually cut out the exact hours remaining ex.:it prints only 1 day but not diff between remaining hours
        System.out.println("Between in days : " + numberOfDays);


        long numberOfHours = ChronoUnit.HOURS.between(ldtStart, ldtEnd); //hours usually cut out the minutes remaining ex.:it prints only 3 hrs but not diff between 20 and 7 minutes
        System.out.println("Between in hours : " + numberOfHours);

        long numberOfMinutes = ChronoUnit.MINUTES.between(ldtStart, ldtEnd);
        System.out.println("Between in minutes : " + numberOfMinutes);
        System.out.println("----Q11----");
    }

    public static  void q12(){
        System.out.println("----Q12----");
        List<String> names = Arrays.asList("Volha", "Ivan", "Daria", "Mikalai", "Anastasia");
        //wont print anything as it has no terminal operation
        names.stream()
                .filter(s -> {
                    System.out.println("filtering " + s);
                    return true;
                });
        System.out.println("----Q12----");

    }


    //IMPORTANT see how the intermediate operations are invoked
    public static void q13(){
        System.out.println("----Q13----");
        List<String> names = Arrays.asList("Volha", "Ivan", "John", "Mike", "Alex");
        String name = names.stream()
                .filter(s -> {
                    System.out.println("filtering " + s);
                    return s.length() == 4;
                })
                .map(s -> {
                    System.out.println("uppercasing " + s);
                    return s.toUpperCase();
                })
                .findFirst()
                .get();
        System.out.println(name);


        //Break down of above operation to see how the chaining is handled
        System.out.println("BREAKDOWN OPERATION");
        names = Arrays.asList("Volha", "Ivan", "Daria", "John");
        Stream<String> stream = names.stream()
                .filter(s -> {
                    System.out.println("filtering " + s);
                    return s.length() == 4;
                })
                .map(s -> {
                    System.out.println("uppercasing " + s);
                    return s.toUpperCase();
                });
        System.out.println("Stream was filtered and mapped...");
        name = stream.findFirst().get();
        System.out.println(name);
        System.out.println("----Q13----");
    }

    public static void q14(){
        log("q14", false);
        Optional<String> s = Optional.empty();
        System.out.println(s.orElse(null));
      //  System.out.println(s.get()); throws NPE

       // System.out.println(s.orElseGet(null)); // throw NPE as supplier get invoked on null object
       log("q14", true);
    }

    public static void q15(){
        log("q15",false);
        Stream<String> st = Stream.of("java", "ocjp", "jcp");
       // IntStream inss= st.map(s->s.length()); //stream.map returns Stream<Integer> and not IntStream as by mapToInt
        IntStream ins = st.mapToInt(s -> s.length());
        ins.forEach(System.out::println);
        log("q15",true);
    }

    public static void q16(){
        log("q16", false);
        double arr[]= {1.1,2.2,3.3,4.4,5.5};
        DoubleStream ds = Arrays.stream(arr, 3, 5);
        System.out.println(ds.sum());
        int arrInt[]= {1,2,3,4,5};
         IntStream intStream = Arrays.stream(arrInt);
        System.out.println(intStream.sum());
        log("q16", true);
    }

    public static void q17(){
        log("q17", false);
        Instant now = Instant.now();
        System.out.println(now);
        System.out.println(new Date());
      //  now = now.truncatedTo(ChronoUnit.MONTHS); //throws .UnsupportedTemporalTypeException as cant truncate to Months
         Instant nowDays = now.truncatedTo(ChronoUnit.DAYS);
        System.out.println(nowDays);
        Instant nowHours = now.truncatedTo(ChronoUnit.HOURS);
        System.out.println(nowHours);
        log("q17",true);
    }

    public static void q18(){
        log("q18",false);
        LocalDate dt = LocalDate.of(2014, 2, 15);
        System.out.println(dt.getMonthValue()+":"+dt.getDayOfWeek());
        int lenOfMonth = dt.lengthOfMonth();
        System.out.println("LEngth of month 2: " + lenOfMonth);
        LocalDate date = dt.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(date);
        date = dt.with(TemporalAdjusters.next(DayOfWeek.THURSDAY));
        System.out.println(date);
        Instant instantNow = Instant.now();
        System.out.println(instantNow);
        LocalDate tdt = LocalDate.now();
        tdt = tdt.withDayOfMonth(10).withDayOfMonth(1);
        System.out.println("TDT with twice: "+tdt);
        log("q18",true);
    }

    public static Double q19(){
        log("q19",false);
        Integer i1 = 4;
        Integer i2 = 4;
       // return i1+i2;  //should be cast to Double
        log("q19",true);
        return Double.MAX_VALUE;
    }

    public static void q20(){
         log("q20",false);
        IntStream ins = IntStream.of(1, 2, 3);
        //Stream<String> s = ins.boxed().map(Integer::toString); //compilation issue because there are two methods Integer.toString(i),
        // Integer.toString() overriden method. and reference couldnt decide which method to choose
        Stream<String> s = ins.boxed().map((i) -> Integer.toString(i));
        System.out.println(s.distinct().count());
        Comparator<Integer> c = Comparator.comparing(t->t.intValue());
        Comparator<Integer> comp = Integer::compare;
        log("q20",true);

    }

    public static void q21(){
        log("q21",false);
        ToDoubleBiFunction<Double,Integer> ob = (f1,f2)->f1+f2;
        System.out.println(ob.applyAsDouble(1.0, 2)); //Careful as to use applyAsDouble rather than simply apply

        Map<Integer,Double> map = new HashMap<>();
        map.put(1,1.1);
        map.put(2,2.2);
        map.put(1, 3.3);
        map.forEach((k, v) -> System.out.print(ob.applyAsDouble(v, k)));
        log("q21",true);
    }

    public static void q22(){
        log("q22", false);
        LocalDate  ld = LocalDate.of(2010,1,2);
        Period due = Period.ofDays(-2).plusMonths(1); //careful in seeing ofDays and plusDays
        System.out.println(due);
        ld = ld.plus(due);
        System.out.println(ld);
        Duration d = Duration.ofDays(2).plusHours(5);
        System.out.println(d);
        log("q22",true);
    }

    public static void q23(){
        log("q23", false);
        String[] list = {"1","2","3"};
        Arrays.parallelSetAll(list,x->Integer.toString(x)+list[x]);//second param is the IntUnaryOperator generator which gets the array index
        System.out.println(list[0]);
        System.out.println(Arrays.deepToString(list));
        log("q23",true);
    }

    public static void q24(){
        log("q24",false);
        IntStream ins = IntStream.of(10,20,30,40);
        IntSummaryStatistics stat = ins.summaryStatistics();
        //stat.accept(stat.getCount()); //returns long but accept expects integer
        stat.accept((int) stat.getCount()); //returns long but accept expects integer
        System.out.println(stat.getSum());
        log("q24",true);
    }

    public static void q25(){
        log("q25",false);
        Map<Integer,String> mp = new HashMap<>();
        mp.put(1,"A");
        mp.put(2,"B");
        mp.put(3,"C");
        mp.put(4,"D");

        mp.remove("A");
        mp.remove(3, "C");
        mp.remove(4, "A"); //will not remove if the value didnt match
        System.out.println(mp);
        System.out.println(mp.getOrDefault(5, "E")); //introduced in java 8
        System.out.println(mp);
        log("q25",true);
    }

    public static void q26(){
        log("q26", false);
        Stream<Double> ints = Stream.of(1.1,2.2,3.3);
//        ints.filter(d->d>1.3);
//        ints.filter(d->d>2.3).forEach(System.out::print); //throw excpetion as lit is already opened by previous line
        Stream<Double> ds=ints.filter(d->d>1.3);
        ds.filter(d->d>2.3).forEach(System.out::print);
        log("q26",true);
    }

    public static void q27(){
        log("q27",false);
        Optional<String> ops = Optional.of("1");
        OptionalInt op=OptionalInt.empty();
       // OptionalInt op = ops.map(Integer::parseInt); //map return generic Optional<U> and not OptionalInt
        System.out.println(op);
        log("q27",true);
    }

    public static void q28(){
        log("q28",false);
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate.with(DayOfWeek.MONDAY));//changes current date with starting MONDAY
        System.out.println(localDate.with(Month.JANUARY)); //changes current date to JANUARY month
        log("q28",true);
    }

    public static void q29(){
        log("q29",false);
        LocalDate date = LocalDate.now();                    //Today

        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(endOfMonth.toString());                              //2013-05-31

        LocalDate nextTue = date.with(TemporalAdjusters.next(DayOfWeek.TUESDAY));
        System.out.println(nextTue);                                 //2013-05-21
        log("q29",true);
    }

    public static void q30(){
        log("q30", false);
        IntStream ins = IntStream.of(1, 2, 3);
        OptionalDouble od =ins.average(); //make sure it return OptionalDouble
        System.out.println(od);

        //Comparator.comparing(e->e.length);
        log("q30",true);
    }

    public static void q31() {
        log("q31",false);
        Instant now = Instant.now();
        //Below call wont compile as no signature has instant passed in of()
        // ZonedDateTime zid = ZonedDateTime.of(now,ZoneId.of("Canada/Atlantic"));
        System.out.println(now);
        ZonedDateTime zid = ZonedDateTime.ofInstant(now, ZoneId.of("America/Los_Angeles"));
        System.out.println(zid.getHour());
        System.out.println(zid);
        log("q31",true);
    }


    //TODO MUST SEE AND UNDERSTAND
    public static void q32(){
        log("q32",false);
        LocalDate ld1 = LocalDate.of(2015, 11, 25);
        Year year = Year.of(2014);
       // System.out.println(ld1.adjustInto(year)); Throws Exception
        System.out.println(year.atDay(1));
        ld1.adjustInto(year.atDay(1));
        System.out.println(ld1.adjustInto(year.atDay(1)));
        log("q32",true);
    }

    public static void q33(){
        log("q33", false);
        DoubleSupplier sups = () ->Math.random()*10;
        System.out.println(sups.getAsDouble());//sure to check not get() but getAsDouble
        log("q33",true);
    }

    public static void q34(){
        log("q34",false);
        ZonedDateTime paris = ZonedDateTime.now(ZoneId.of("Europe/Paris"));
        ZonedDateTime london = ZonedDateTime.now(ZoneId.of("Europe/London"));
        System.out.println("Hours difference between Paris and London "+ (paris.getHour()-london.getHour()));
        log("q34",true);
    }

    public static void q35(){
        log("q35",false);
        System.out.println("Last day of month :"+LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println("Last Weekday of month :"+LocalDate.now().with(TemporalAdjusters.lastDayOfMonth()).getDayOfWeek());
        System.out.println("My birthday this year:"+LocalDate.of(2016, Month.JULY, 22).getDayOfWeek());

        LocalDate today = LocalDate.now();
        LocalDate calcday = today.with(Month.JUNE).withDayOfMonth(22);
        System.out.println("calcDay "+calcday);
        System.out.println(Period.between(today,calcday));
        System.out.println(Period.between(calcday, today));//prints values in negative
        System.out.println(Period.between(today,calcday).getDays());
        System.out.println(Period.between(today,calcday).getMonths());
       // System.out.println(Duration.between(today,calcday).toDays());//throws UnsupportedTemporalTypeException

        System.out.println(LocalDate.parse("2013-01-12"));

        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);
        System.out.println(localTime.truncatedTo(ChronoUnit.SECONDS));

        //at methods
        LocalDate date = Year.of(2014).atMonth(Month.JANUARY).atDay(20);
        LocalDateTime dateTime= date.atTime(12, 30);
        OffsetDateTime odt = dateTime.atOffset(ZoneOffset.ofHours(2));
        ZonedDateTime zdt = date.atStartOfDay(ZoneId.of("Europe/London"));
        ZonedDateTime zone =Instant.now().atZone(ZoneId.of("Europe/Paris"));

        // sixtyFourth day of 2014 (2014-03-05)
        LocalDate dateYear = Year.of(2014).atDay(64);
        System.out.println("From Year Day "+dateYear);
        log("q35",true);

    }

    public static void q36() {
        log("q36",false);
        LocalDate date = LocalDate.of(2000, Month.OCTOBER, 15);
        DayOfWeek dotw = date.getDayOfWeek();
        System.out.printf("%s is on a %s%n", date, dotw);

        System.out.printf("first day of Month: %s%n",
                date.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.printf("first Monday of Month: %s%n",
                date.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY)));
        System.out.printf("last day of Month: %s%n",
                date.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.printf("first day of next Month: %s%n",
                date.with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.printf("first day of next Year: %s%n",
                date.with(TemporalAdjusters.firstDayOfNextYear()));
        System.out.printf("first day of Year: %s%n",
                date.with(TemporalAdjusters.firstDayOfYear()));

        String s = null;
        Optional<String> op = Optional.ofNullable(s);

        if (op.filter(l -> l.startsWith("H")).isPresent()) {
            System.out.println("Got it ");
        }
        log("q36",true);

    }

        public static void q37(){
            log("q37",false);
        LocalDate today = LocalDate.now();
        LocalDate birthday = LocalDate.of(1960, Month.JANUARY, 1);

            //The total period of time is represented by all three units together: months, days, and years.
            // To present the amount of time measured in a single unit of time, such as days,
            // you can use the ChronoUnit.DAYS.between method.
        Period p = Period.between(birthday, today);
        long p2 = ChronoUnit.DAYS.between(birthday, today);
        System.out.println("You are " + p.getYears() + " years, " + p.getMonths() +
                " months, and " + p.getDays() +
                " days old. (" + p2 + " days total)");

            System.out.println(UUID.randomUUID());
        log("q37",true);

    }

    public static void q38(){
        log("q38",false);
        IntStream ints = IntStream.of(4, 5, 6);
        //ints.findFirst().getAsInt(); // -- make sure we have getAsInt
        System.out.println(ints.filter(x->x>8).findFirst());
        Map map = new HashMap();
        log("q38",true);
    }

    public static void q39(){
        log("q39",false);
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();

        Instant instant = clock.instant();
        Date legacyDate = Date.from(instant);   // legacy java.util.Date from instant we can get the legacy date

        System.out.println(ZoneId.getAvailableZoneIds());
// prints all available timezone ids

        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        ZoneId zone3 = ZoneId.of("America/Los_Angeles");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());
        System.out.println(zone3.getRules());

        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);

        System.out.println(now1.isBefore(now2));  // false

        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);

        System.out.println(hoursBetween);       // -3
        System.out.println(minutesBetween);     // -239
        log("q39",true);

    }

    public static void q40(){
        log("q40",false);
        Arrays.asList("a1", "a2", "b1", "c2", "c1")
                .parallelStream()
                .filter(s -> {
                    System.out.format("filter: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return true;
                })
                .map(s -> {
                    System.out.format("map: %s [%s]\n",
                            s, Thread.currentThread().getName());
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.format("forEach: %s [%s]\n",
                        s, Thread.currentThread().getName()));

        log("q40",true);
    }

    public static void log(String qno,boolean end){

        if(end){
            System.out.println("\nEND----" + qno + "----END");
        }else {
            System.out.println("\n----" + qno + "----");
        }
    }


    static int i=10;

    public class Inner{
//        public static void print(){  //inner class cannot have static declarations
//            System.out.println(i);
//        }
    }
}

interface I{
    default void print(){
        System.out.println("Print");
    }

    static void method(){
        System.out.println("Method");
    }

    public static void main(String[] args) {

    }

    //compilation error
//    default boolean equals(Object o){
//        return true;
//    }


}



