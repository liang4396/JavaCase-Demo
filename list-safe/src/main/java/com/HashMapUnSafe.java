package com;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author llp
 * @date 2020/7/24 14:21
 */
public class HashMapUnSafe {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        Map<String,String> map1 = new ConcurrentHashMap<>();
        Map<String,String> map3 = Collections.synchronizedMap(new HashMap<>());

        for(int i=0;i<30;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    map1.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                    System.out.println(map1);
                }
            }).start();
        }
    }
}
