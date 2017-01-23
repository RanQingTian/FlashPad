package data;

import java.util.Date;

/**
 * Created by Eligi.Ran on 2017/1/20.
 */
public class Test {
    public static void main(String[] args){
        int size = 100000;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("12345");
        stringBuilder.replace(4,5,"0");
        System.out.println(stringBuilder.toString());



    }
}
