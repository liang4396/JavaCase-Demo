package com;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author llp
 * @date 2020/7/28 15:02
 */
public class SynchronousQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> syncBlock = new SynchronousQueue<>();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName()+"\t 放入1");
                    syncBlock.put("1");
                    System.out.println(Thread.currentThread().getName()+"\t 放入2");
                    syncBlock.put("2");
                    System.out.println(Thread.currentThread().getName()+"\t 放入3");
                    syncBlock.put("3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t 取走"+syncBlock.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t 取走"+syncBlock.take());
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println(Thread.currentThread().getName()+"\t 取走"+syncBlock.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
    }
}
