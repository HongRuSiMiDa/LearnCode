package juc;

/**
 * 函数式接口;一个有且仅有一个抽象方法，但是可以有多个非抽象方法的接口
 */
@FunctionalInterface
interface  Info{
    int show(int a,int b);

    // 可以写实现方法
    default int add(int a,int b){
        return a+b;
    }
    default int add2(int a,int b){
        return a+b;
    }
    // 静态方法
    static int one(int a,int b){
        return a/b;
    }
    static int one1(int a,int b){
        return a/b;
    }

}

/**
 * 总结:1只有函数式接口才能写lambda
 *FunctionalInterface 函数式接口用FunctionalInterface修饰
 * default  可以编写实现方法 实现的方法可以有多个
 * static  可以写静态的实现方法 静态实现方法可以写多个
 */
public class LambdaExpress {
    public static void main(String[] args) {
        Info info=(int a,int b)->{
            System.out.println("方法已经执行");
            return a+b;
        };
        System.out.println(info.show(5,6));

        System.out.println(info.add(9,5));

        System.out.println(Info.one(10,5));
    }
}
