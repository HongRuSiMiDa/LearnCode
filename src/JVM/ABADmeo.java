package JVM;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class ABADmeo {
    static AtomicReference<Integer> atomicReference=new AtomicReference<>(100);
    static AtomicStampedReference<Integer>atomicStampedReference=new AtomicStampedReference<>(1,1);
    public static void main(String[] args) {
        System.out.println("ABA的产生");
        new Thread(new Runnable() {
            @Override
            public void run() {
                atomicReference.compareAndSet(100,101);
                atomicReference.compareAndSet(101,100);
            }
        },"t1").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                }catch (Exception e){

                }

                System.out.println(Thread.currentThread().getName()+"\t"+atomicReference.compareAndSet(100,2019)+"\t"+atomicReference.get());
            }
        },"t2").start();

        try{
            Thread.sleep(4000);
        }catch(Exception e){}
        System.out.println("ABA的解决");

        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp=atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"当前的版本号"+stamp);
                try{
                    Thread.sleep(1000);
                    atomicStampedReference.compareAndSet(1,100,stamp,stamp+1);
                    atomicStampedReference.compareAndSet(100,1,atomicStampedReference.getStamp(),atomicStampedReference.getStamp()+1);
                    System.out.println(Thread.currentThread().getName()+"\t"+"当前的版本号"+atomicStampedReference.getStamp());
                    System.out.println(Thread.currentThread().getName()+"\t"+"当前的值"+atomicStampedReference.getReference());

                }catch (Exception e){

                }
            }
        },"t3").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp=atomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"当前的版本号"+stamp);
                try{
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName()+"\t"+atomicStampedReference.compareAndSet(1,2019,stamp,stamp+1)+"\t"+"当前值"+atomicStampedReference.getReference()+
                   "\t"+ "当前的版本"+atomicStampedReference.getStamp());

                }catch (Exception e){

                }
            }
        },"t4").start();
    }
}
