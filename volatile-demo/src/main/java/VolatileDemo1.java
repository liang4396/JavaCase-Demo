import java.util.concurrent.TimeUnit;

/**
 * @author llp
 * @date 2020/7/22 11:06
 * Volatile可见性证明
 */
public class VolatileDemo1 {
    public static void main(String[] args) {
        MyData myData = new MyData();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "come in !");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                myData.addTo60();
                System.out.println(Thread.currentThread().getName() + "updated number value:"+myData.number);
            }
        });
        thread.start();
        while (myData.number == 0) {
            //等待 mydata中的number中的值修改 不加volatile的情况下 thread值修改之后，但main线程根本不知道
            //mian线程中的number值根本没有修改
            //如果增加volatile以后 为 thread线程启动等待3秒，此时main线程已经获取到number的值，
            // 但在等待，thread3秒后修改了number的值 mian线程的number得到通知 也改变为60
            //TODO 总结就是mian线程先获取了number的值为0，thread3秒后将其修改，mian线程中number也改变了！
        }
        System.out.println(Thread.currentThread().getName() + "mian is over:");
    }
}

class MyData {
    volatile  int  number = 0;

    public void addTo60() {
        this.number = 60;
    }
}