package org.example.java8;

public class TestJava8 {

	public static void main(String[] args) {

		// An instance of a functional interface (Single abstract method)  using a lambda expression
		                     // method signature ->(arrow token/lambda operator)
		TestInterface tester = () -> System.out.println("Java SE 8 is working!");
		tester.test();

	}

}
