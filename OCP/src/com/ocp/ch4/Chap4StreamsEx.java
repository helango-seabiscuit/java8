package com.ocp.ch4;



import java.time.*;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.*;

/**
 * Created by helangovan on 1/11/16.
 */
public class Chap4StreamsEx {

    public static void main(String[] args) {
        //terminal stream operations

        //count()
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(s.count());

        //min and max
        s = Stream.of("monkey", "gorilla", "bonobo");
        Optional<String> min = s.min((x, y) -> x.length() - y.length());
        min.ifPresent(System.out::println);

        Optional<?> minEmpty = Stream.empty().min((s1,s2)->0);
        System.out.println(minEmpty.isPresent());

        //findAny() and findFirst()
        //works with infinite stream
        s=Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        s.findAny().ifPresent(System.out::println);
        infinite.findAny().ifPresent(System.out::println);


        //allMatch(),anyMatch(), noneMatch()
        List<String> list = Arrays.asList("monkey","2","chimp");
        infinite = Stream.generate(()->"chimp");
        Predicate<String> pred = x->Character.isLetter(x.charAt(0));
        System.out.println(list.stream().anyMatch(pred));
        System.out.println(list.stream().allMatch(pred));
        System.out.println(list.stream().noneMatch(pred));

        //forEach()
        s=Stream.of("monkey", "gorilla", "bonobo");
        s.forEach(System.out::println);

        //reduce()
        Stream<String> st = Stream.of("w", "o", "l", "f");
        String word = st.reduce("", (s1, c) -> c + s1);
        System.out.println(word);

        st = Stream.of("w", "o", "l", "f");
        word = st.reduce("",String::concat);
        System.out.println(word);

        Stream<Integer> it = Stream.of(3,5,6);
        Integer res=it.reduce(1,(a,b)->a*b);
        System.out.println(res);

        BinaryOperator<Integer> op = (a,b) -> a*b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3,5,6);

        empty.reduce(op).ifPresent(System.out::println);
        oneElement.reduce(op).ifPresent(System.out::println);
        threeElements.reduce(op).ifPresent(System.out::println);

        //collect()
        Stream<String> stc = Stream.of("w","o","l","f");
        StringBuilder sbc = stc.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println("collect: " + sbc);

        Stream<String> stct = Stream.of("w","o","l","f");
        TreeSet sbct = stct.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println("collect: " + sbct);

        Stream<String> sr = Stream.of("w","o","l","f");
        TreeSet ts = sr.collect(Collectors.toCollection(TreeSet::new));
        System.out.println("collect: " + ts);

        Stream<String> srs = Stream.of("w","o","l","f");
        Set<String> set = srs.collect(Collectors.toSet());
        System.out.println("collect set: " + set);

        intermediateOperations();

        primitiveStream();

        chainOptionals();

        collect();

        groupThings();


        String measures[] = {" 1.2E2 ", " 3.14 ", " 3.9E-3 "};
        List<?> numList = Arrays.asList(measures);
        for (int i =0; i < numList.size(); i++) {
            System.out.print(numList.get(i) + " " );
        }

        
        Stream.of("5","7","3","2","4","1","6")
               .sorted()
                .forEach(System.out::print);

      //  System.out.println((Stream.of("Fred", "Jim", "Sheila").parallel().collect(String::new,String::concat,String::concat)));

    }

    public static void intermediateOperations(){
        //filter()
      Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.filter((st) -> st.startsWith("m")).forEach(System.out::println);

        //distinct
        Stream<String> sc = Stream.of("duck", "duck", "duck", "goose");
        sc.distinct().forEach(System.out::print);
        System.out.println();

        Stream<String> sct = Stream.of("duck", "hannah", "duck", "kilo");
        sct.distinct().forEach(System.out::print);
        System.out.println();

        //limit & skip
        Stream<Integer> it = Stream.iterate(1, n -> n + 1);
        it.skip(5).limit(2).forEach(System.out::print);
        System.out.println();

        //RUns infinitely
//        it = Stream.iterate(1, n -> n + 1);
//        System.out.println(it.collect(Collectors.toList()));

        //map()
        s= Stream.of("monkey", "gorilla", "bonobo");
        //s.map((w)->w.length()).forEach(System.out::print);
        IntSummaryStatistics stats =s.map((w) -> w.length()).collect(Collectors.summarizingInt((x)->x));
        System.out.println(stats.getSum());

        //flatMap
        List<String> zero = Arrays.asList();
        List<String> one = Arrays.asList("Bonobo");
        List<String> two = Arrays.asList("Mama Gorilla","Baby Gorilla");
        Stream<List<String>> animals= Stream.of(zero,one,two);

        animals.flatMap(l->l.stream()).forEach(System.out::println);//removed empty list also

        //sorted
        Stream<String> ss = Stream.of("brown-","bear-");
        ss.sorted().forEach(System.out::println);

        ss = Stream.of("brown bear-","grizzly-");
        ss.sorted(Comparator.<String>reverseOrder()).forEach(System.out::println);
        //ss.sorted(Comparator::reverseOrder).forEach(System.out::println);

        //peek()
        Stream<String> ps = Stream.of("black bear","brown bear","grizzly");
        long count = ps.filter(l->l.startsWith("g")).peek(System.out::print).count();
        System.out.println(count);

        List<Integer> numbers = new ArrayList<>();
        List<Character> letters = new ArrayList<>();
        numbers.add(1);
        letters.add('a');
        Stream<List<?>> stream = Stream.of(numbers, letters);
        stream.map(List::size).forEach(System.out::print);

        StringBuilder builder = new StringBuilder();
        Stream<List<?>> good = Stream.of(numbers, letters);
        good.peek(l->builder.append(l)).map(List::size).forEach(System.out::print);
        System.out.println(builder);

        //bad and dont do
        Stream<List<?>> bad = Stream.of(numbers, letters);
        bad.peek(l->l.remove(0)).map(List::size).forEach(System.out::println);

        Stream<Integer> inf = Stream.iterate(1, i -> i + 1);
        //inf.peek(System.out::print).filter(i->i%2==1).limit(5).forEach(System.out::print);
       // inf.peek(System.out::print).limit(5).filter(i->i%2==1).forEach(System.out::print);//11233455
        //inf.limit(5).peek(System.out::print).filter(i->i%2==1).forEach(System.out::print); //11233455
        //inf.peek(System.out::print).filter(i -> i % 2 == 1).forEach(System.out::print); //keeps printing infinitely and system hangs until we kill it

    }

    private static void primitiveStream(){
        IntStream range = IntStream.range(1, 6);
        range.forEach(System.out::print);

        IntStream rangeClosed = IntStream.rangeClosed(1,5);
        rangeClosed.forEach(System.out::print);

        IntStream stream = IntStream.rangeClosed(1,5);
        OptionalDouble optionalDouble= stream.average();
        optionalDouble.ifPresent(System.out::println);
        System.out.println(optionalDouble.getAsDouble());
        System.out.println((optionalDouble.orElseGet(() -> Double.NaN)));

       // IntStream st = IntStream.range(1,6);
        IntStream st = IntStream.empty();
        IntSummaryStatistics statistics = st.summaryStatistics();
//        if(statistics.getCount()==0){
//            throw new IllegalArgumentException();
//        }
        int mx = statistics.getMax();
        int min = statistics.getMin();
        int d = mx-min;
        System.out.println(statistics.getMax()-statistics.getMin());

    }

    private static void chainOptionals(){
        Optional<Integer> op = Optional.of(123);
        op.map(n->n+"").filter(s->s.length()==3).ifPresent(System.out::print);

        //Optional<Integer> result = op.flatMap()
    }

    private static void collect(){

        //BAsic Collectors
        Stream<String> ohMy = Stream.of("lions","tigers","bears");
        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result);

        ohMy = Stream.of("lions", "tigers", "bears");
        Double res = ohMy.collect(Collectors.averagingInt(String::length));
        System.out.println(res);

        ohMy = Stream.of("lions", "tigers", "bears");
        TreeSet<String> r = ohMy.filter(s->s.startsWith("t")).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(r);

        //Collect in maps
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<String,Integer> map = ohMy.collect(Collectors.toMap(s->s, String::length));
        //Map<String,Integer> map = ohMy.collect(Collectors.toMap(Function.<String>identity(), String::length));
        System.out.println(map);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer,String> mp = ohMy.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2));
        System.out.println(mp);
        System.out.println(map.getClass());

        ohMy = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer,String> tm = ohMy.collect(Collectors.toMap(String::length, k -> k, (s1, s2) -> s1 + "," + s2, TreeMap::new));
        System.out.println(tm);
        System.out.println(tm.getClass());

        Stream<String> s = Stream.iterate("",(sr)->sr+"1");
        s.limit(2).forEach(System.out::println);

        Stream<String> tr = Stream.generate(()->"meow");
        boolean resBool = tr.allMatch(String::isEmpty);
        System.out.println(resBool);

        LongStream ls = LongStream.of(1,2,3);
        //ls.map(n->n*10).filter(n->n<5).findFirst();
        Optional<Long> ol =ls.mapToObj(n -> n * 10).filter(n->n<5).findFirst();

        Stream<Integer> sri = Stream.of(1);
        DoubleStream ds = sri.mapToDouble(x->x);
        IntStream inst = ds.mapToInt(x -> (int)x);


    }

    private static void groupThings(){
        Stream<String> ohMy = Stream.of("lions","tigers","bears");
        Map<Integer,List<String>> map = ohMy.collect(Collectors.groupingBy(String::length));
        System.out.println(map);



        //group in set
        ohMy = Stream.of("lions","tigers","bears");
        Map<Integer,Set<String>> set = ohMy.collect(Collectors.groupingBy(String::length,Collectors.toSet()));
        System.out.println(set);

        ohMy = Stream.of("lions", "tigers", "bears");
        TreeMap<Integer,Set<String>> tm = ohMy.collect(Collectors.groupingBy(String::length, TreeMap::new,Collectors.toSet()));
        System.out.println(tm);

        //partitioning - done for  two values only
        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean,List<String>> mp = ohMy.collect(Collectors.partitioningBy(s -> s.length() <= 5));
        System.out.println(mp);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean,Set<String>> ms = ohMy.collect(Collectors.partitioningBy(s -> s.length() <= 5,Collectors.toSet()));
        System.out.println(ms);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean,Long> mc = ohMy.collect(Collectors.partitioningBy(s -> s.length() <= 5,Collectors.counting()));
        System.out.println(mc);

        ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer,Long> mt = ohMy.collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(mt);


        //pg:223 --not compiling need to work on this
        ohMy = Stream.of("lions", "tigers", "bears");
//        Map<Integer,Optional<Character>> mcc = ohMy.collect(Collectors.groupingBy(String::length, Collectors.mapping(s->s.charAt(0),
//                Collectors.minBy(Comparator.naturalOrder()))));
//        System.out.println(mcc);
        ZonedDateTime dtz = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), ZoneId.of("UTC-7"));
        System.out.println("UTC OFFSET : " + dtz);

        List<String> tst = Arrays.asList("Hello");
        Collections.sort(tst);
        System.out.println(tst);


    }

    interface Ridable {
        Car getCar(String name);
    }

    class Car{
        private String name;
        public Car(String n){
            this.name = n;
        }

        public void testCarInst(){
            Ridable r = snm->  {return new Car(snm);};
           // Ridable r2 = snm-> Car(snm)::new;
            r.getCar("h");

        }
    }
}
