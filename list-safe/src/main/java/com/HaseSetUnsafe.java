package com;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author llp
 * @date 2020/7/24 14:06
 */
public class HaseSetUnsafe {
    public static void main(String[] args) {
        Set<String> sets = new HashSet<>();
        Set<String> sets1 = Collections.synchronizedSet(new HashSet<>());
        Set<String> sets2 = new CopyOnWriteArraySet<>();

        for(int i=0;i<30;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    sets2.add(UUID.randomUUID().toString().substring(0,8));
                    System.out.println(sets2);
                }
            }).start();
        }
    }
}
