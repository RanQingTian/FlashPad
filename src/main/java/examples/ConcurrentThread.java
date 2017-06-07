package examples;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Eligi.Ran on 2017/3/20.
 */

/**
 * JtextPane use html type
 */
public class ConcurrentThread {
    public boolean turn = true;

    public static void show(int i, String... words) {
        System.out.println(words.length);
    }

    public boolean revert() {
        return  turn = !turn;
    }

    public static void main(String[] args) {
        LinkedList<Integer> test = new LinkedList<>();
        test.add(0);
        ConcurrentThread mytest = new ConcurrentThread();
        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        long start = new Date().getTime();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    System.out.println("main thread wake up ");
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    a.signal();
                    lock.unlock();
                    System.out.println("main thread unlocked ");

                    while (true) {
                        System.out.println("now length :" + test.size() + " last inserted " + test.getLast());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        Thread.sleep(1000);
                        System.out.println((new Date().getTime() - start) + " a work done");
                        b.signal();
                        a.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println((new Date().getTime() - start) + " a thread unlocked");
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        lock.lock();
                        Thread.sleep(1000);
                        System.out.println((new Date().getTime() - start) + " b work done");
                        a.signal();
                        b.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                        System.out.println((new Date().getTime() - start) + " b thread unlocked");
                    }
                }
            }
        }).start();

        Date end = new Date();


    }

}
