package examples;

import java.util.*;

/**
 * Created by Eligi.Ran on 2017/3/20.
 */

/**
 * JtextPane use html type
 */
public class Test {
    public boolean turn = true;
    volatile boolean re = false;
    boolean syn = false;
    public final int i;
    {
        i = 1;
    }

    public synchronized static void show(int i, String... words) {
        System.out.println(words.length);
    }

    public boolean revert() {
        return  turn = !turn;
    }

    public static void main(String[] args) {
        int length = 100000;
        List<Integer> integers = new ArrayList<>(length);
        List<Integer> integers2 = new ArrayList<>(length);
        Date start1 = new Date();
        integers.forEach(ele -> ele = new Double(Math.random()*length).intValue());
        Date start2 = new Date();
        System.out.println("for(:) is " + (start2.getTime() - start1.getTime()));
        for (int i = 0; i<length; i++) {
            integers2.add( new Double(Math.random()*length).intValue());
        }
        Date start3 = new Date();
        System.out.println("for( ; ;) is " + (start3.getTime() - start2.getTime()));
        Collections.sort(integers);
        Date start4 = new Date();
        System.out.println("sort is " + (start4.getTime() - start3.getTime()));
        integers2.parallelStream().sorted();
        Date start5 = new Date();
        System.out.println("parallelSort is " + (start5.getTime() - start4.getTime()));

        Date end = new Date();
//        System.out.println((end.getTime() - start.getTime()));


    }

}
