package JVM;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * CAS==>compareAndSet
 * 比较并交换
 */
public class CASDmeo {
    public static void main(String[] args) {

        AtomicInteger atomicInteger=new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5,2000)+"\t"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5,1000)+"\t"+atomicInteger.get());
    }
}
