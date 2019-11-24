package JVM;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo {
    public static void main(String[] args) {
        volatileNoAtomicity();
    }
    public static void volatileNoAtomicity(){
        Studen s=new Studen();
        for (int i=0;i<20;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int j=0;j<1000;j++){
                        s.addNumberTo();
                        s.addAtomic();
                    }
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("总计算 int "+s.number);
        System.out.println("总计算 AtomicInteger"+s.atomicInteger);
    }
}
class Studen{
    int number=0;
    public synchronized void  addNumberTo(){
        this.number++;
    }
    AtomicInteger atomicInteger=new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
    //Atomicity


}
