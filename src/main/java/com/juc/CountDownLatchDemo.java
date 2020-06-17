package com.juc;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo  {

    /**
     * 让一个或一些线程等待另一些线程执行完一些操作后才被唤醒，如本例：主线程调用await()方法阻塞，
     * 其余线程调用countDown()使aqs的state减一，当state为0时主线程开始执行
     * @param args
     */

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++) {
            new Thread(CountryEnum.forEach_CountryEnum(i).getRetMsg()){
                @Override
                public void run() {
                    System.out.println(getName() + "\t国被灭.");
                    countDownLatch.countDown();
                }
            }.start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t秦国一统天下.");
    }

}
