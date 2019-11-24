package juc;

import java.util.concurrent.TimeUnit;

/**
 * 1,标准运行,先线程a,在线程b--先发邮件
 * 2,发邮件方法睡眠4秒钟,看看是先邮件还是打电话--发邮件
 * 3新增普通方法,先运行普通方法还是发邮件--先sayHello,因为syaHello不是同步方法,不需要等待等待锁的释放就可以运行,不具有竞态的情况
 * 4,两部手机先发邮件还是先打电话--先打电话,因为两个是不同的资源类对象,互不干扰.
 * 5,两个静态方法 同一个手机 先邮件还是先打电话---先发邮件,因为静态同步方法的锁对象是整个phone类
 * 6,两个静态方法 两部个手机 先邮件还是先打电话---先发邮件,因为静态同步方法的锁对象是整个phone类
 * 7,一个静态同步方法,一个普通同步方法,一个手机  先邮件还是先电话--打电话 因为他们锁的对象不一样
 * 8,一个静态同步方法,一个普通同步方法,两个手机 先邮件还是先电话--打电话
 */
public class eightLock {

    public static void main(String[] args) {
        phone phone=new phone();
        phone phone1=new phone();
        new Thread(new Runnable() {
            @Override
            public void run() {
                phone.sendEmil();
            }
        },"A").start();

        try {
            // 睡眠1秒中,保证线程A能优先运行
            TimeUnit.SECONDS.sleep(2);
        }catch (Exception e){
            e.printStackTrace();
        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                //phone.callPhone();
                //phone.sayHello();
                phone1.callPhone();
            }
        },"B").start();
    }
}
class phone{
    public synchronized static void  sendEmil(){
        try{
            TimeUnit.SECONDS.sleep(4);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("发送邮件Emil");
    }

    public  synchronized  void callPhone(){
        System.out.println("打电话callPhnoe");
    }

    public  void sayHello(){
        System.out.println("sayHelo");
    }
}