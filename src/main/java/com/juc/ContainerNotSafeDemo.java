package com.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题，使用对应线程安全的容器替代
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
        Map<String, String> hashMap = new HashMap<>();
        Map<String, String> synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 0; i < 30; i++) {
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    map.put(getName(), UUID.randomUUID().toString().substring(0 ,8));
                    System.out.println(map);
                }
            }.start();
        }
    }

    public static void setNotSafe() {
        Set<String> hashSet = new HashSet<>();
        Set<String> synchronizedSet = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    set.add(UUID.randomUUID().toString().substring(0, 8));
                    System.out.println(getName() + "\t" + set);
                }
            }.start();
        }

        /**
         *
         * private final CopyOnWriteArrayList<E> al;
         * public CopyOnWriteArraySet() {
         *    al = new CopyOnWriteArrayList<E>();
         * }
         */


        /**
         * HashSet底层是HashMap
         *  public HashSet() {
         *     map = new HashMap<>();
         *  }
         * 那为什么map要传键和值两个参数,而HashSet只需要一个参数
         * 因为HashSet的add方法,把要传入的值作为键,而值是一个名为PRESENT的Object类型静态常量
         *  public boolean add(E e) {
         *     return map.put(e, PRESENT)==null;
         *  }
         *  private static final Object PRESENT = new Object();
         *
         *
         */
        new HashSet<>().add("a");
    }

    public static void listNotSafe() {
        //List<String> list = new ArrayList<>();
        //List<String> list = new Vector<>();
        //List<String> list = Collections.synchronizedList(new ArrayList<>());
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(String.valueOf(i)){
                @Override
                public void run() {
                    list.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list);
                }
            }.start();
        }


        /**
         * CopyOnWrite：写时复制
         * 查询的时候，都不需要加锁，随便访问，只有在写入/删除的时候，才会从原来的数据复制一个副本出来，
         * 然后修改这个副本，最后把原数据替换成当前的副本。修改操作的同时，读操作不会被阻塞，而是继续读取旧的数据。
         */

        /**
         *  1.故障现象
         *      java.util.ConcurrentModificationException
         *  2.导致原因
         *      并发争抢修改导致,参考花名册签名,一个人正在写,另一个同学过来抢,导致数据不一致异常
         *  3.解决方案
         *      3.1 new Vector<>();
         *      3.2 Collections.synchronizedList(new ArrayList<>());
         *      3.3 new CopyOnWriteArrayList<>()
         *  4.优化建议
         */

    }

}


