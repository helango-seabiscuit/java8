package com.ocp.snippets;
import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.stream.Stream;

public class prac{
	
	public static void main(String[] args){
		Stream<Integer> st = Stream.of(1,2,3);
		System.out.println(st.peek(System.out::println).findAny().orElse(0));
		Double d = Double.valueOf(10.28);
		Double balance = 10.27999999d;
		if(d > balance){
			System.out.println("Incorrect");
		}

		BigDecimal bd = new BigDecimal(Double.valueOf(35.3799993716d));
		System.out.println(bd.doubleValue());

		try(TRCage c = new TRCage();Window w = new Window()){
			System.out.println("Got in ");
		}

	}
}

class TRCage implements  AutoCloseable{


	@Override
	public void close()  {
		System.out.println("hello close me");
	}
}

class Window implements Closeable{

	@Override
	public void close()  {
		System.out.println("W");
        throw new RuntimeException();
	}
}