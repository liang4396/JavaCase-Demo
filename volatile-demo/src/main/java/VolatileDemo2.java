import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author llp
 * @date 2020/7/22 11:29
 * 不可保证原子性！证明
 */
public class VolatileDemo2 {
    public static void main(String[] args) {
        MyData1 myData1 = new MyData1();
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 20; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        myData1.addplus();
                        myData1.addatomicIntegerplus();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("final myData1 number value :" + myData1.number);
        System.out.println("final atomicInteger number value :" + myData1.atomicInteger);
    }
}

class MyData1 {
    volatile int number = 0;

    volatile AtomicInteger atomicInteger = new AtomicInteger(0);

    public void addplus() {
        number++;
    }

    public void addatomicIntegerplus() {
        atomicInteger.getAndIncrement();
    }
}