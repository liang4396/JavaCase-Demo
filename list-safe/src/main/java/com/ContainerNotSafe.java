package com;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author llp
 * @date 2020/7/24 13:23
 */
public class ContainerNotSafe {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        List<String> list1 = Collections.synchronizedList(new ArrayList<>());
        List<String> list2 = new CopyOnWriteArrayList<>();
/*
        List<String> list = new Vector<>();
        list.add("a");
        list.add("b");
        list.add("c");
        for (String element : list){
            System.out.println(element);
        }*/
        for(int i=0;i<30;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    list2.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(list2);
                }
            }).start();
        }

    }
}
