import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by helangovan on 11/22/15.
 */
public class LambdaPrac implements Person1,Named{

    static int p;

    public int getId(){
        return 1;
    }
    public static void main(String args[]){
        String words[] = "Good morning maryln manroe Ever beauty..".split(" ");
        Arrays.sort(words, (first, second) -> Integer.compare(first.length(), second.length()));
        System.out.print(Arrays.deepToString(words));

        List<String> labels = Arrays.asList(words);
        labels.forEach(System.out::print);
        Stream<Button> btns = labels.stream().map(Button::new);
        List<Button> buttons = btns.collect(Collectors.toList());
        System.out.print(buttons.size());

        new LambdaPrac().repeatMessage("Hema", 20);
        f();
        Named.f();
    }

    public  void repeatMessage(String text,int count){
        Runnable r = ()->{
         for(int i=0;i<count;i++){
             System.out.println(text+":"+i);
             p--;

         }
            System.out.print(this.getClass());
        };
        new Thread(r).start();

    }


    static void f(){
        System.out.print("LambdaPrac");
    }

    public  String getName(){
        return "Person1";
    }
}

interface  Person1{
    int getId();
    default String getName(){
        return "Person1";
    }

    static void f(){
        System.out.print("Person1");
    }

}

interface Named{
    default String getName(){
        return "Named";
    }

    static void f(){
        System.out.print("Named");
    }
}
