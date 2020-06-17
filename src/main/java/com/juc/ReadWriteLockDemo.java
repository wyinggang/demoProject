package com.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class ReadWriteLockDemo  {  //资源类

        private volatile Map<String, Object> map = new HashMap<>();

        private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        public void put(String key, Object value){
            readWriteLock.writeLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t正在写入：" + key);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                map.put(key, value);
                System.out.println(Thread.currentThread().getName() + "\t写入完成");
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                readWriteLock.writeLock().unlock();
            }
        }

        public void get(String key){
            readWriteLock.readLock().lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t正在读取：");
            /*try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
                Object value = map.get(key);
                System.out.println(Thread.currentThread().getName() + "\t读取完成：" + value);
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                readWriteLock.readLock().unlock();
            }
        }

    public static void main(String[] args) {

        /**
         * * 多个线程同时读一个资源类没问题,所以为了满足并发量,读取共享资源该可以同时进行
         *  * 但是,
         *  * 如果有一个线程想去写共享资源,就不该再有其它线程可以对该资源进行读或写
         *  * 写操作：原子+独占,整个过程必须是一个完整的统一体,中间不允许被分割、被打断
         */

        ReadWriteLockDemo readWriteLock = new ReadWriteLockDemo();

        //5个线程写
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    readWriteLock.put(String.valueOf(tempInt), tempInt);
                }
            }.start();
        }

        //5个线程读
        for (int i = 1; i <= 5; i++) {
            final int tempInt = i;
            new Thread(String.valueOf(i)) {
                @Override
                public void run() {
                    readWriteLock.get(String.valueOf(tempInt));
                }
            }.start();
        }
    }

}
