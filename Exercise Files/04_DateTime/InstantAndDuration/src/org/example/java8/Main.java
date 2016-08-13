package org.example.java8;

import java.time.Duration;
import java.time.Instant;

public class Main {

	public static void main(String[] args) {

		Instant start = Instant.now();


		Instant end = Instant.now();
		Duration elapsed = Duration.between(start,end);
		System.out.print(elapsed.toMillis());
	}

}
