package data.csvparser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eligi.Ran on 2017/1/18.
 */
public class CsvParser {
    public static void main(String[] args) {
        long start  = new Date().getTime();
        BufferedReader crunchifyBuffer = null;

        try {
            String crunchifyLine;
//            crunchifyBuffer = new BufferedReader(new FileReader("d:/Crunchify-CSV-to-ArrayList.txt"));
            crunchifyBuffer = new BufferedReader(new FileReader("d:/5000X5000.csv"));
            Runtime runtime = Runtime.getRuntime();
            StringBuilder sb = new StringBuilder();
            NumberFormat format = NumberFormat.getInstance();
            // How to read file in java line by line?
            while ((crunchifyLine = crunchifyBuffer.readLine()) != null) {
                crunchifyCSVtoArrayList(crunchifyLine);
//                System.out.println("Raw CSV data: " + crunchifyLine);
//                System.out.println("Converted ArrayList data: " + crunchifyCSVtoArrayList(crunchifyLine) + "\n");
                long maxMemory = runtime.maxMemory();
                long allocatedMemory = runtime.totalMemory();
                long freeMemory = runtime.freeMemory();
//                System.out.println("max memory: " + format.format((allocatedMemory-freeMemory) / (1024)) + "<br/>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (crunchifyBuffer != null) crunchifyBuffer.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
        long end  = new Date().getTime();
        System.out.println(end - start);
    }

    // Utility which converts CSV to ArrayList using Split Operation
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
}
