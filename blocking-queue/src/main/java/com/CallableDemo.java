package com;

import com.sun.org.apache.xml.internal.security.Init;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @author llp
 * @date 2020/7/29 16:58
 */
public class CallableDemo {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(10);// 创建一个线程池，数量和开启线程的数量一样
        List<Future<String>> futureList = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {

/*          TODO 不使用线程池创建线程：
            FutureTask<String> future = new FutureTask<>(new MyCallable(i));
            new Thread(future).start();
            futureList.add(future);*/
            //TODO 使用线程池创建线程：
            Future<String> future = executor.submit(new MyCallable(i));
            futureList.add(future);
/*          TODO 堵塞式的体现：
            try {
                String s = executor.submit(new MyCallable(i)).get();
                System.out.println(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/
        }

        for (Future<String> future : futureList) {
            try {
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        System.out.println("Future:" + future
                                + ",Result:" + future.get());
                        break;
                    } else {
                        Thread.sleep(1000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        executor.shutdown();
    }
}

class MyCallable implements Callable<String> {
    int number;

    @Override
    public String call() throws Exception {
        /*int time = 10 - number;*/
        int time =  number;
        Thread.sleep(1000 * time);
        return "休息了" + time + "秒 number:" + number;
    }

    public MyCallable(int number) {
        this.number = number;
    }


}