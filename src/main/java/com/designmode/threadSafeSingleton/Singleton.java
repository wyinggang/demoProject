package com.designmode.threadSafeSingleton;

import java.io.Serializable;

public class Singleton implements Serializable {

    /** 持有私有静态实例，防止被引用，此处赋值为null，目的是实现延迟加载
     * volatile防止指令重排序，内存可见(缓存中的变化及时刷到主存，并且其他的内存失效，必须从主存获取)
     * */
    private static  volatile  Singleton singleton = null;

    /** 私有构造方法，防止被new */
    private Singleton(){}

    public static Singleton getInstance(){
        //第一次判断，假设会有好多线程，如果singleton没有被实例化，那么就会到下一步获取锁，只有一个能获取到，
        //如果已经实例化，那么直接返回了，减少除了初始化时之外的所有锁获取等待过程
        if (singleton == null){
            synchronized (Singleton.class){
                //第二次判断是因为假设有两个线程A、B,两个同时通过了第一个if，然后A获取了锁，进入然后判断singleton是null，他就实例化了singleton，然后他出了锁，
                //这时候线程B经过等待A释放的锁，B获取锁了，如果没有第二个判断，那么他还是会去new Singleton()，再创建一个实例，所以为了防止这种情况，需要第二次判断
                if (singleton == null){
                    //下面这句代码其实分为三步：
                    //1.开辟内存分配给这个对象
                    //2.初始化对象
                    //3.将内存地址赋给虚拟机栈内存中的singleton变量
                    //注意上面这三步，第2步和第3步的顺序是随机的，这是计算机指令重排序的问题
                    //假设有两个线程，其中一个线程执行下面这行代码，如果第三步先执行了，就会把没有初始化的内存赋值给singleton
                    //然后恰好这时候有另一个线程执行了第一个判断if(singleton == null)，然后就会发现singleton指向了一个内存地址
                    //这另一个线程就直接返回了这个没有初始化的内存，所以要防止第2步和第3步重排序
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    /** 防止实现序列化接口后，经过序列化与反序列化会生成两个对象 */
    private Object readResolve(){
        return singleton;
    }

}
