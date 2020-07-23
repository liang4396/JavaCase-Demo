package com;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author llp
 * @date 2020/7/23 16:09
 */
public class ABADemo {
    static AtomicReference<Integer> integerAtomicReference = new AtomicReference<>(100);
    static AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(100,1);

    public static void main(String[] args) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                integerAtomicReference.compareAndSet(100, 101);
                integerAtomicReference.compareAndSet(101, 100);
            }
        }, "t1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integerAtomicReference.compareAndSet(100, 2019);
               // System.out.println(integerAtomicReference.get());
            }
        }, "t2").start();


        System.out.println("ABA问题解决==================================================================");





        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = integerAtomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"\t 当前版本号："+stamp);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integerAtomicStampedReference.compareAndSet(100,101,
                        integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1);

                System.out.println(Thread.currentThread().getName()+"\t 当前版本号："+integerAtomicStampedReference.getStamp());
                integerAtomicStampedReference.compareAndSet(101,100,
                        integerAtomicStampedReference.getStamp(),integerAtomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t 当前版本号："+integerAtomicStampedReference.getStamp());
            }
        }, "t3").start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int stamp = integerAtomicStampedReference.getStamp();
                System.out.println(Thread.currentThread().getName()+"\t 当前版本号："+stamp);
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                integerAtomicStampedReference.compareAndSet(100,2019,
                        stamp,integerAtomicStampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName()+"\t 当前版本号："+integerAtomicStampedReference.getStamp()+"当前值："+integerAtomicStampedReference.getReference());
            }
        }, "t4").start();
    }
}

