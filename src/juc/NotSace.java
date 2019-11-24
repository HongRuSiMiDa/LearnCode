package juc;

import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 一:故障现象:
 * 并发修改异常 java.util.ConcurrentModificationException
 * 二导致原因:
 *多个线程争取一个资源类 并且没有加上锁
 * 三 解决方法:
 * 解决list并发修改异常的方法
 * 1,使用Vector()
 * 2,使用Collections.synchronizedList(new ArrayList());
 * 3,使用CopyOnWriteArrayList()
 * new ArrayList();创建的是长度为10的objec对象数组
 * 每次增长上次长度的二分之一
 * 线性不安全
 */
public class NotSace {
    public static void main(String[] args) {
        notSaceMap();
        //noSaceSet();
        //notSaceList();
    }

    /**
     * java.util.ConcurrentModificationException
     * 1,并发修改异常
     * 2,解决方法
     * 2.1 Collections.synchronizedMap(new HashMap())
     * 2.2 new ConcurrentHashMap()
     *
     */
    private static void notSaceMap() {
        Map map=new HashMap();//new ConcurrentHashMap();//Collections.synchronizedMap(new HashMap());//new HashMap();
        for(int i=0;i<30;i++){
            new Thread(()->{
                map.put(Thread.currentThread().getName(),Math.random());
                System.out.println(map);
            }).start();
        }
    }

    /**
     * 1线性不安全 有并发修改异常
     *
     * 2,解决方法
     *  2.1Collections.synchronizedSet(new HashSet())
     *  2.2 new CopyOnWriteArraySet()   CopyOnWriteArraySet的底层调用的也是CopyOnWriteArrayList
     * 3 set的底层是hashMash,key值时set对象add进去的值,value是一个常量对象
     */
    private static void noSaceSet() {
        Set set= new CopyOnWriteArraySet();//Collections.synchronizedSet(new HashSet());// new HashSet();
        for(int i=0;i<30;i++){
            new Thread(()->{
                set.add(Math.random());
                System.out.println(set);
            }).start();
        }
    }

    /**
     * 解决list并发修改异常的方法
     *  * 1,使用Vector()
     *  * 2,使用Collections.synchronizedList(new ArrayList());
     *  * 3,使用CopyOnWriteArrayList()
     *  * new ArrayList();创建的是长度为10的objec对象数组
     *  * 每次增长上次长度的二分之一
     *  * 线性不安全
     */
    private static void notSaceList() {
        List list= new CopyOnWriteArrayList();//Collections.synchronizedList(new ArrayList());//new Vector(); //ArrayList();
        for(int i=0;i<30;i++){
            new Thread(()->{
                list.add(Math.random());
                System.out.println(list);
            }).start();
        }
    }
}
