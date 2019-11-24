package JVM;

import sun.applet.Main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdConsumer_BlockQueueDamo {
    private volatile  boolean flag=true;
    private static  AtomicInteger atomicInteger=new AtomicInteger();
    public BlockingQueue blockingQueue=null;

    public ProdConsumer_BlockQueueDamo(BlockingQueue blockingQueue){
        this.blockingQueue=blockingQueue;
    }

    /**
     * 生产
     */
    public void produce(){
        String data=null;

        while (flag){
            data=String.valueOf(atomicInteger.incrementAndGet());
            try {
                blockingQueue.offer(data,2, TimeUnit.SECONDS);
            }catch (Exception e){

            }

        }
    }

    public static void main(String[] args) {
        System.out.println(atomicInteger.incrementAndGet());
    }
}
