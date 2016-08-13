package org.example.java8;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.example.java8.model.Person;

public class SumAndAverage {

	public static void main(String args[]){

		List<Person> people = new ArrayList<>();

		people.add(new Person("Joe", 48));
		people.add(new Person("Mary", 30));
		people.add(new Person("Mike", 73));

		int sum = people.stream()
				.mapToInt(p -> p.getAge())
				.sum();

		System.out.println("Sum pf ages : "+sum);

		OptionalDouble avg = people.stream()
				             .mapToInt(p -> p.getAge())
				             .average();

		if(avg.isPresent()){
			System.out.println("Average of ages : "+avg.getAsDouble());
		}else{
			System.out.println("Average not calculated");
		}

		IntSummaryStatistics stats = people.stream().filter((p)-> p.getName().startsWith("M")).mapToInt(p->p.getAge()).summaryStatistics();
		System.out.println(stats.getAverage() + " " + stats.getMax() + " " + stats.getMin() + " " + stats.getSum() + " " + stats.getCount());

		Stream<Integer> is =Stream.of(12, 16, 18, 20, 22, 24, 26, 28, 30);

		//System.out.println(is.map(x->""+x).collect(Collectors.joining(",","Start[","]End")));

		System.out.println(is.collect(Collectors.minBy((x,y)->x-y)).orElseGet(() -> Integer.MIN_VALUE));

	}
	
}