package com;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author llp
 * @date 2020/7/24 14:42
 */
public class LockDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
    }
}

class T1{
    volatile int n = 0;
    public void add(){
        n++;
    }
}
