import java.util.concurrent.Callable;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by helangovan on 11/28/15.
 */
public class Ch3Prac{

    private static int[] a;
    public static void main(String [] args){
     a = new int[10];
        logIf(Level.INFO,()-> {return 9;}, (i)->{
            System.out.print("a[i]="+ a[i]);
        });

        ReentrantLock myLock = new ReentrantLock();
        withLock(myLock, () -> { System.out.print("Hello world");return 0;});
    }

    public static void logIf(Level level,Supplier<Integer> input,Consumer<Integer> message){

        if(Logger.getGlobal().isLoggable(level)){
            message.accept(input.get());
        }

    }

    public static void withLock(ReentrantLock lock,Callable r){
       lock.lock();
        try{
          r.call();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }
    }
}
