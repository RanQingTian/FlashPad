package data;

import java.util.Date;

/**
 * Created by Eligi.Ran on 2017/1/20.
 */
public class Test {
    public static void main(String[] args){
        int size = 100000;

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("INSERT INTO data2 (value) VALUES ");
        long time2 = new Date().getTime();
        for (int i = 0; i < size; i++) {
            stringBuilder.append( "(?),");
        }
        System.out.println((new Date().getTime()-time2)/1000);

        String sql = "INSERT INTO data2 (value) VALUES ";
        long time1 = new Date().getTime();
        for (int i = 0; i < size; i++) {
            sql += "(?),";
        }
        System.out.println((new Date().getTime()-time1)/1000);



    }
}
