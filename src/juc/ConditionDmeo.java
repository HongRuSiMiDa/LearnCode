package juc;

import java.util.Collections;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class shareDate {
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    private int number = 1;

    public void print5() {
        lock.lock();
        try {
            // 判断
            while (number != 1) {
                c1.await();
                //c1.wait();
            }
            // 执行
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            // 通知
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            // 判断
            while (number != 2) {
                c2.await();
            }
            // 执行
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            // 通知
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            // 判断
            while (number != 3) {
                c3.await();
            }
            // 执行
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + i);
            }
            // 通知
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}


/**
 * 需求:功能调度 A->B->C
 * 要求:依次执行A打印5次 B打印10次 C打印15次
 * 打印10轮
 */
public class ConditionDmeo {
    public static void main(String[] args) {
        shareDate shareDate=new shareDate();

        new Thread(()->{
            for (int i=0;i<10;i++){
                shareDate.print5();
            }
        },"A").start();

        new Thread(()->{
            for (int i=0;i<10;i++){
                shareDate.print10();
            }
        },"B").start();

        new Thread(()->{
            for (int i=0;i<10;i++){
                shareDate.print15();
            }
        },"C").start();
    }
}
