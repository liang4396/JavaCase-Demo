package com;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author llp
 * @date 2020/7/28 14:25
 */
public class BlockingQueueDemo {
    public static void main(String[] args) {
        List list = null;
/*        BlockingQueue<String> arrayListBock = new ArrayBlockingQueue(3);
        System.out.println(arrayListBock.add("a"));
        System.out.println(arrayListBock.add("b"));
        System.out.println(arrayListBock.add("c"));
        arrayListBock.remove();
        arrayListBock.remove();
        arrayListBock.remove();*/
        BlockingQueue<String> arrayListBock1 = new ArrayBlockingQueue(3);
        System.out.println(arrayListBock1.offer("a"));
        System.out.println(arrayListBock1.offer("b"));
        System.out.println(arrayListBock1.offer("c"));
        System.out.println(arrayListBock1.peek());
        System.out.println(arrayListBock1.poll());
        System.out.println(arrayListBock1.poll());
        System.out.println(arrayListBock1.poll());
        System.out.println(arrayListBock1.poll());



    }
}
