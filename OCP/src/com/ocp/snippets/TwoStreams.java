import java.util.*;
import java.util.stream.IntStream;


public class TwoStreams{
	
	public static void main(String [] args){
		final List<Integer> numbers1 = Arrays.asList(1,2,3,4);
		final List<Integer> numbers2 = Arrays.asList(10,20,30);
		IntStream.range(0,Math.min(numbers1.size(),numbers2.size()))
		.mapToObj(i->numbers1.get(i)+numbers2.get(i))
		.forEach(System.out::println);
	}
}