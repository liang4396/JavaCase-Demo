package com;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author llp
 * @date 2020/7/24 16:09
 */
public class SpinLockDemo {
    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    public void lock(){
        Thread thread = Thread.currentThread();
        while (!threadAtomicReference.compareAndSet(null,thread)){

        }
        System.out.println(thread.getName()+"\t come in (#^.^#)");
    }

    public void myUnlock(){
        Thread thread = Thread.currentThread();
        threadAtomicReference.compareAndSet(thread,null);
        System.out.println(thread.getName()+"\t myUnlock (#^.^#)");
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLockDemo.lock();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                spinLockDemo.myUnlock();
            }
        },"a").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                spinLockDemo.lock();
                spinLockDemo.myUnlock();

            }
        },"b").start();
    }
}
