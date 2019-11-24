package juc;
class Product{

    int number=0;
    public synchronized void producer()throws Exception{
        // 判断
        while (number!=0){
            this.wait();
        }
        // 执行
        number++;
        System.out.println(Thread.currentThread().getName()+"生产了"+number);
        // 通知
        this.notifyAll();
    }

    public synchronized void cnsumer()throws Exception{
        // 判断
        while(number==0){
            this.wait();
        }
        // 执行
        number--;
        System.out.println(Thread.currentThread().getName()+"消费了"+number);
        // 通知
        this.notifyAll();
    }
}

/**
 * 生产一个 消费一个 但是有一个bug 多个生产者和多个消费者的时候用if进行判断的时候 会出现错误
 * 官方建议用while尽心判断
 */
public class ProdConsumerDemo {
    public static void main(String[] args) {
        Product product=new Product();
        new Thread(()->{
            for (int i=0;i<10;i++){
                try {
                    product.producer();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"A").start();

        new Thread(()->{
            for (int i=0;i<10;i++){
                try {
                    product.cnsumer();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"B").start();


        new Thread(()->{
            for (int i=0;i<10;i++){
                try {
                    product.producer();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"C").start();

        new Thread(()->{
            for (int i=0;i<10;i++){
                try {
                    product.cnsumer();
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"D").start();
    }
}
