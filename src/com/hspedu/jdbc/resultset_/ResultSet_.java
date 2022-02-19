package com.hspedu.jdbc.resultset_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class ResultSet_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        //通过Properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        //1. 注册驱动
        Class.forName(driver);

        //2. 得到连接
        Connection connection = DriverManager.getConnection(url, user, password);

        //3. 得到Statement
        Statement statement = connection.createStatement();

        /*
        +----+-----------+-----+---------------------+-------+
        | id | name      | sex | borndate            | phone |
        +----+-----------+-----+---------------------+-------+
        |  2 | jack      | 男  | 1990-11-11 00:00:00 | 112   |
        |  3 | 刘德华    | 男  | 1990-11-11 00:00:00 | 112   |
        +----+-----------+-----+---------------------+-------+
        */
        //4. 组织sql
        String sql = "select id, name, sex, borndate from actor";

        //底层是怎么样的？

        ResultSet resultSet = statement.executeQuery(sql);
        //5. 使用while取出数据
        while (resultSet.next()) { //让光标向后移动，如果没有更多行，则返回false
            int id = resultSet.getInt(1);//获取改行的第1列数据
            String name = resultSet.getString(2);
            String sex = resultSet.getString(3);
            Date date = resultSet.getDate(4);
            System.out.println(id + "\t" + name + "\t" + sex + "\t" + date);
        }
        //6. 关闭连接
        resultSet.close();
        statement.close();
        connection.close();
    }
}
