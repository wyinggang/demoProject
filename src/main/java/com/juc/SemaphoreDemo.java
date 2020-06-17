package com.juc;

import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    /**
     * 信号量主要用于控制并发线程数，
     * @param args
     */

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);     //模拟3个停车位

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢到车位.");
                    Thread.sleep(3000);
                    System.out.println(Thread.currentThread().getName() + "\t停车3秒钟后离开车位.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }, String.valueOf(i)).start();
        }
    }
}