package juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class Mythread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("heloo callable");
        return 1204;
    }
}

/**
 * 获取线程的方法旧版有2中
 * 新版有4种
 *
 * 1,继承thread
 * 2,实现Runnable接口
 * 3,从线程池获取    ExecutorService service = Executors.newFixedThreadPool(2)
 * 4,实现Callable
 */
public class CallableDemo {
    public static void main(String[] args) throws Exception{
        FutureTask<Integer>futureTask=new FutureTask<>(new Mythread());

        new Thread(futureTask,"a").start();

        Integer i=futureTask.get();
        System.out.println(i);
    }
}
