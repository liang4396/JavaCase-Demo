package com;

import java.sql.Time;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author llp
 * @date 2020/7/29 15:01
 */
public class prodConsumer_blockingqueue {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(10));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myResource.myProd();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"生产").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    myResource.myConsumer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },"消费").start();

        try {
            TimeUnit.SECONDS.sleep(5);
            myResource.Stop();
            System.out.println("大老板叫停，5秒结束");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyResource {
    private volatile boolean flag = true;//默认开启 进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean offer;
        while (flag) {
            data = atomicInteger.incrementAndGet() + "";
            offer = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (offer) {
                System.out.println(Thread.currentThread().getName() + "\t 插入数据" + data + "成功！");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入数据" + data + "失败！");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 生产动作结束 ！");
    }


    public void myConsumer() throws Exception {
        String poll = null;
        while (flag) {
            poll = blockingQueue.poll(2, TimeUnit.SECONDS);
            if(null == poll || poll.equalsIgnoreCase("")){
                flag = false;
                System.out.println(Thread.currentThread().getName() + "\t 超过2秒没有取到蛋糕,消费退出！");
                System.out.println();
                return ;
            }
            System.out.println(Thread.currentThread().getName() + "\t 取出数据" + poll + "成功！");
        }
    }

    public void Stop(){
        this.flag = false;
    }
}
