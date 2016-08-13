package org.example.java8;

import java.util.ArrayList;
import java.util.List;

public class CountItems {
	public static void main(String args[]){

		System.out.println("Creating list");
		List<String> strings = new ArrayList<>();
		for (int i = 0; i < 10000; i++) {
			strings.add("Item " + i);
		}
		
		long count = strings.stream().count();

		System.out.println("Number of strings : "+count);
	
	}
	
}