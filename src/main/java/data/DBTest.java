package data;

import data.csvparser.CsvScanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eligi.Ran on 2017/1/20.
 */
public class DBTest {

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql = null;
        final String initSql = "INSERT INTO data3 (value,fieldName,rowIndex,fieldIndex) VALUES ";
        String url = "jdbc:mysql://localhost:3306/testdb?"
                + "user=root&password=db123456&useUnicode=true&characterEncoding=UTF8";

        try {
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // new com.mysql.jdbc.Driver();

            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);


            int size = 8;

            StringBuilder stringBuilder = new StringBuilder(initSql);
            PreparedStatement ps = null;
            System.out.println("load start");
            List<List<String>> data = CsvScanner.getTestData();
            System.out.println("load end");

            int i = 0, k = 0, commitSize = 5;
            long startTime = new java.util.Date().getTime();
            int commit = 0;
            System.out.println("Record filed Name");
            List<String> fieldName = data.get(0);
            int fieldSize = fieldName.size();
            StringBuilder strb = new StringBuilder("insert into field_name (id,fieldName,fileName) values ");
            final String builder = new String("('id','fieldName','fileName'),");
            StringBuilder temp = null;
            int idIndexBegin = builder.indexOf("'id'"), idIndexEnd = idIndexBegin + 4,
                    fieldNameIndexBegin = 0, fieldNameIndexEnd = 0, fileIndexBegin = 0, fileIndexEnd = 0;
            int firstPart = fieldSize - 1;
            int fieldCommit = 0;
            for (int field = 0; field < firstPart; field++) {
                temp = new StringBuilder(builder);
                temp.replace(idIndexBegin, idIndexEnd, "" + field);
                fieldNameIndexBegin = temp.indexOf("'fieldName'");
                fieldNameIndexEnd = fieldNameIndexBegin + 11;
                temp.replace(fieldNameIndexBegin, fieldNameIndexEnd, "'" + fieldName.get(field) + "'");
                fileIndexBegin = temp.indexOf("'fileName'");
                fileIndexEnd = fileIndexBegin + 10;
                temp.replace(fileIndexBegin, fileIndexEnd, "'" + "csvName" + "'");
                strb.append(temp);
                fieldCommit++;

            }
            strb.replace(strb.length() - 1, strb.length(), ";");
            ps = conn.prepareStatement(strb.toString());
            ps.addBatch();
            ps.executeBatch();
            conn.commit();
            ps.close();
            ps = null;

            System.out.println("Record field Name finished : " + (new java.util.Date().getTime() - startTime) / (1000));
            int length = data.size();

            for (int row = 0; row < length; row++) {
                System.out.println("Row" + (++i) + ":" + (new java.util.Date().getTime() - startTime) / (1000));
                List<String> list = data.get(0);
                int colSize = list.size();
                int column = 0;
                for (String str : list) {
                    stringBuilder.append("('" + str + "','" + fieldName.get(column) + "','" + row + "','" + column + "'),");
                    column++;
                }
                if ((row + 1) % size == 0) {
                    sql = stringBuilder.replace(stringBuilder.length() - 1, stringBuilder.length(), "").toString();
                    stringBuilder = null;
                    ps = conn.prepareStatement(sql);
                    ps.addBatch();
                    ps.executeBatch();
                    sql = null;
                    commit++;
                    stringBuilder = new StringBuilder(initSql);
                    k = 0;
                    if (commit % commitSize == 0) {
                        commit = 0;
                        System.out.println(row + "  insert start:" + (new java.util.Date().getTime() - startTime) / (1000));
                        conn.commit();
                        System.out.println("insert done:" + (new java.util.Date().getTime() - startTime) / (1000));
                    }
                    ps.close();
                    ps = null;
                }
                list = null;
                data.remove(0);
            }
            if (commit > 0) {
                sql = new String(stringBuilder.substring(0, stringBuilder.length() - 1) + ";");
                ps = conn.prepareStatement(sql);
                ps.addBatch();
                ps.executeBatch();
                conn.commit();
                System.out.println("final insert done:" + (new java.util.Date().getTime() - startTime) / (1000));
            }

        } catch (SQLException e) {
            System.out.println("MySQL操作错误");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }
}
