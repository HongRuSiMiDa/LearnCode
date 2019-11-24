package juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTickrt {
    // 高内聚低耦合 线程--操作---资源类

    public static void main(String[] args) {
        // 线程
        TiceketOne t =new TiceketOne();
        new Thread(()->{ for(int i=1;i<=40;i++){t.sale();}},"A").start();
        new Thread(()->{ for(int i=1;i<=40;i++){t.sale();}},"B").start();
        new Thread(()->{ for(int i=1;i<=40;i++){t.sale();}},"C").start();

    }
}

// 资源类+操作
class TiceketOne{
    private int number=40;
    Lock l=new ReentrantLock();
    public void sale(){
        l.lock();
        try {
            if(number>0) {
                System.out.println(Thread.currentThread().getName() + "卖出第" + (number--) + "剩余" + number);
            }
        }catch (Exception e){

        }finally {
            l.unlock();
        }
    }
}