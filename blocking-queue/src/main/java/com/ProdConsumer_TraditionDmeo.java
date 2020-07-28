package com;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author llp
 * @date 2020/7/28 16:23
 */
public class ProdConsumer_TraditionDmeo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();

        new Thread(new Runnable() {
            @Override
            public void run() {
                    for (int i = 0; i < 5; i++) {
                        try {
                        shareData.increment();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            }
        }, "A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.decrement();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "B").start();
    }
}

/**
 * 资源类
 */
class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws Exception {
        lock.lock();
        try {
            while (number != 0) {
                //等待不能生成
                condition.await();
            }
            //干活
            number++;
            System.out.println(Thread.currentThread().getName() + "\t number:" + number);
            //通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void decrement() throws Exception {
        lock.lock();
        try {
            while (number == 0) {
                //等待不能生成
                condition.await();
            }
            //干活
            number--;
            System.out.println(Thread.currentThread().getName() + "\t number:" + number);
            //通知唤醒
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
