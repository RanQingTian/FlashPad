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
public class Test {
    public boolean turn = true;

    public static void show(int i, String... words) {
        System.out.println(words.length);
    }

    public boolean revert() {
        return  turn = !turn;
    }

    public static void main(String[] args) {
//        Box panel = Box.createVerticalBox();
//
//        Integer[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
//        String[] cols = {"A", "B", "C"};
//        JComboBox comboBox = new JComboBox(cols);
//        panel.add(comboBox, BorderLayout.SOUTH);
//
//        System.out.println(comboBox.getSelectedIndex());
//        /* display the panel in a frame */
//        JFrame frame = new JFrame();
//        frame.getContentPane().add(panel);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 400);
//        frame.setVisible(true);
        LinkedList<Integer> test = new LinkedList<>();
        test.add(0);
        Test mytest = new Test();
        ReentrantLock lock = new ReentrantLock();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    System.out.println("main");
                    lock.lock();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    a.signal();
                    lock.unlock();
                }
                while (true) {
                    System.out.println(test.getLast());
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String as = "a";
                Thread.currentThread().setName(as);
                while (true) {
                    try {
                        if (lock.tryLock(2000,TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println(as + " sleep");
                                a.await();
                                System.out.println(as + " wake up");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(as);

                        } else {
                            System.out.println("A Cannot acquire lockA");
                            Thread.sleep(1000);
                            continue;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock.isHeldByCurrentThread()) {
                            System.out.println("a finished");
                            b.signal();
                            lock.unlock();
                            test.add(3);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String bs = "b";
                Thread.currentThread().setName(bs);
                while (true) {
//                    System.out.println("b in");
                    try {
                        if (lock.tryLock(2000,TimeUnit.MILLISECONDS)) {
                            try {
                                System.out.println(bs + " sleep");
                                b.await();
                                System.out.println(bs + " wake up");
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
//                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(bs);
                        } else {
                            System.out.println("B Cannot acquire lockA");
                            Thread.sleep(1000);
                            continue;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        if (lock.isHeldByCurrentThread()) {
                            System.out.println("b finished");
                            a.signal();
                            lock.unlock();
                            test.add(3);
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            }
        }).start();
        Date start = new Date();

        Date end = new Date();
//        System.out.println((end.getTime() - start.getTime()));


    }

}
