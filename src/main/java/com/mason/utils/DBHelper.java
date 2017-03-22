package com.mason.utils;

import java.sql.*;

/**
 * Created by huangsiqian on 2017/3/22 0022.
 */
public class DBHelper {

    /*String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    String url = "jdbc:sqlserver://127.0.0.1;DatabaseName=javenforexcel";*/

    String driver = "com.mysql.jdbc.Driver";
//    String url = "jdbc:mysql://127.0.0.1:3306/javenforexcel";
    String url = "jdbc:mysql://120.27.78.58:3306/wangrun-test";


    Connection con = null;
    ResultSet res = null;

    public void DataBase() {
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, "wangrun", "Wangrun321");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.err.println("装载 JDBC/ODBC 驱动程序失败。" );
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.err.println("无法连接数据库" );
            e.printStackTrace();
        }
    }

    // 查询
    public ResultSet  Search(String sql, String str[]) {
        DataBase();
        try {
            PreparedStatement pst =con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            res = pst.executeQuery();

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    // 增删修改
    public int AddU(String sql, String str[]) {
        int a = 0;
        DataBase();
        try {
            PreparedStatement pst = con.prepareStatement(sql);
            if (str != null) {
                for (int i = 0; i < str.length; i++) {
                    pst.setString(i + 1, str[i]);
                }
            }
            a = pst.executeUpdate();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return a;
    }
}
