package data.csvparser;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Eligi.Ran on 2017/1/18.
 */
public class CsvScanner {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        long start = new Date().getTime();
        FileInputStream inputStream = null;
        Scanner sc = null;
        String path = "d:/5000X5000.csv";

        StringBuilder sb = new StringBuilder();
        NumberFormat format = NumberFormat.getInstance();
        File file = new File(path);

        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            long maxUsed = 0;
            System.out.println(runtime.totalMemory() / (1024 * 1024));
            String crunchifyLine = null;
            while (sc.hasNextLine()) {
                crunchifyLine = sc.nextLine();
                crunchifyCSVtoArrayList(crunchifyLine);
                // System.out.println(line);
//                long maxMemory = runtime.maxMemory();
//                long allocatedMemory = runtime.totalMemory();
//                long freeMemory = runtime.freeMemory();
//                long used = (allocatedMemory - freeMemory) / (1024*1024);
//                maxUsed = maxUsed >= used ? maxUsed : used;
            }
            System.out.println("max used:" + maxUsed + "\t" + crunchifyLine);

            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }

        long end = new Date().getTime();
        System.out.println(end - start);
    }

    public static ArrayList<String> crunchifyCSVtoArrayList(String crunchifyCSV) {
        ArrayList<String> crunchifyResult = new ArrayList<String>();

        if (crunchifyCSV != null) {
            String[] splitData = crunchifyCSV.split("\\s*,\\s*");
            for (int i = 0; i < splitData.length; i++) {
                if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
                    crunchifyResult.add(splitData[i].trim());
                }
            }
        }
        return crunchifyResult;
    }

    public static List getTestData(){
        List<List<String>> result = new ArrayList<List<String>>();
        FileInputStream inputStream = null;
        Scanner sc = null;
        String path = "d:/5000X5000.csv";
        File file = new File(path);
        try {
            inputStream = new FileInputStream(file);
            sc = new Scanner(inputStream, "UTF-8");
            long maxUsed = 0;
            String crunchifyLine = null;
            while (sc.hasNextLine()) {
                crunchifyLine = sc.nextLine();
                result.add(crunchifyCSVtoArrayList(crunchifyLine));
            }
            // note that Scanner suppresses exceptions
            if (sc.ioException() != null) {
                throw sc.ioException();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (sc != null) {
                sc.close();
            }
        }
        return result;
    }

    private void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
