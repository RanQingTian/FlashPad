package data;

import data.csvparser.CsvScanner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eligi.Ran on 2017/1/19.
 */
public class MysqlConnector {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        String sql = null;
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost:3306/testdb?"
                + "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

        try {
            // 之所以要使用下面这条语句，是因为要使用MySQL的驱动，所以我们要把它驱动起来，
            // 可以通过Class.forName把它加载进去，也可以通过初始化来驱动起来，下面三种形式都可以
            Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
            // or:
            // com.mysql.jdbc.Driver driver = new com.mysql.jdbc.Driver();
            // or：
            // new com.mysql.jdbc.Driver();

            System.out.println("成功加载MySQL驱动程序");
            // 一个Connection代表一个数据库连接
            conn = DriverManager.getConnection(url);
            // Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
//            Statement stmt = conn.createStatement();
//            sql = "SELECT * FROM data";
//            ResultSet rs = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
//           while (rs.next()){
//               System.out.println(rs.getString(1)+rs.getString(2)+rs.getString(3));
//           }
            PreparedStatement ps = null;
            conn.setAutoCommit(false);
//                Statement stmt = conn.createStatement();
            long startTime = new java.util.Date().getTime();
            System.out.println("Before reading data:" + startTime / 1000);
            List<List<String>> data = CsvScanner.getTestData();
            System.out.println("Finished reading data:" + (new java.util.Date().getTime() - startTime) / 1000);
            sql = "INSERT INTO data2 (value) VALUES ";
            int size = data.get(0).size(), commitSize = 500000;
            for (int i = 0; i < size; i++) {
                sql += "(?),";
            }
            sql = sql.substring(0, sql.length() - 1) + ";";
            ps = conn.prepareStatement(sql);

            int i = 0;

            int k = 0;
            int length = data.size();
            int commit = 0;
            for (int j = 0; j < length; j++) {
                System.out.println("Row" + (++i) + ":" + (new java.util.Date().getTime() - startTime) / (1000));
                List<String> list = data.get(0);
                for (String str : list) {
                    ps.setString(++k, str);
                    if (k % size == 0) {
                        ps.addBatch();
                        ps.executeBatch();
                        commit++;
                        System.out.println("Row" + i+" committed");
                        if ((commit*size) >= commitSize ) {
                            commit = 0;
                            System.out.println("insert start:" + (new java.util.Date().getTime() - startTime) / (1000));
                            conn.commit();
                            System.out.println("insert done:" + (new java.util.Date().getTime() - startTime) / (1000));
                        }
                        k = 0;
//                        ps.clearParameters();
                    }

                }
                data.remove(0);
            }
//            try {

            conn.commit();
//            }catch (BatchUpdateException e){
//
//            }
            if (ps != null) {
                ps.close();
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