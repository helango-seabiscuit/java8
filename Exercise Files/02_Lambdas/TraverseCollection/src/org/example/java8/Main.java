package org.example.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main {
	public static void main(String args[]){
		 
		List<String> strings = new ArrayList<String>();
		strings.add("AAA");
		strings.add("bbb");
		strings.add("CCC");
		strings.add("ddd");
		strings.add("EEE");
 
		Collections.sort(strings);
		System.out.println("Simple sort");

		// Traverse with for:each
//		for(String str: strings){
//			System.out.println(str);
//		}

		strings.forEach(str -> System.out.println(str));
		
		Comparator<String> comp = (str1, str2) ->
		{
			return str1.compareToIgnoreCase(str2);
		};
		Collections.sort(strings, comp);
		
		System.out.println("Sort with comparator");

		//Traverse with iterator
		strings.forEach(str -> System.out.println(str));
		
	}
}
