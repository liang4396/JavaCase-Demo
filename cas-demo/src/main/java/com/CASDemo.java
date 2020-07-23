package com;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author llp
 * @date 2020/7/23 14:37
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        atomicInteger.getAndIncrement();
        System.out.println(atomicInteger.compareAndSet(5, 2019)+"\t current data :" + atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+"\t current data :" + atomicInteger.get());
    }
}
