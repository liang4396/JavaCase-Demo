package com;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author llp
 * @date 2020/7/28 14:06
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println(Thread.currentThread().getName()+"抢到车位！");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println(Thread.currentThread().getName()+"停车3秒离开车位！");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                    }
                    System.out.println();
                }
            },String.valueOf(i)).start();
        }
    }
}
