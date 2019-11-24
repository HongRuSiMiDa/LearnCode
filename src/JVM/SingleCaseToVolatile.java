package JVM;

public class SingleCaseToVolatile {
    private static SingleCaseToVolatile singleCaseToVolatile=null;
    private SingleCaseToVolatile(){
        System.out.println(Thread.currentThread().getName()+"\t"+"我是构造函数");
    }
    /*
     *DCL 双端检锁机制 也会出现对象出现空值的情况,因为指令重排序了
     * singleCaseToVolatile = new SingleCaseToVolatile();分为三步指令
     * 1,分配对象内存空间
     * 2,初始化对象
     * 3,设置instance指向刚分配的内存地址,此时instance!=null
     * 但由于指令会发生重排 会出现1->3->2的情况
     * 因此会出现返回null值的情况
      */
    public static SingleCaseToVolatile getSingleCaseToVolatile(){
        if (singleCaseToVolatile==null){
            synchronized (SingleCaseToVolatile.class) {
                if (singleCaseToVolatile==null) {
                    singleCaseToVolatile = new SingleCaseToVolatile();
                }
            }
        }
        return singleCaseToVolatile;
    }

    public static void main(String[] args) {
        //SingleCaseToVolatile s=new SingleCaseToVolatile();
       /* System.out.println(getSingleCaseToVolatile()==getSingleCaseToVolatile());
       System.out.println(getSingleCaseToVolatile()==getSingleCaseToVolatile());
       System.out.println(getSingleCaseToVolatile()==getSingleCaseToVolatile());*/

       for(int i=0;i<20;i++){
           new Thread(new Runnable() {
               @Override
               public void run() {
                   getSingleCaseToVolatile();
               }
           }).start();
       }
    }
}
