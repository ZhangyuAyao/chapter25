package com.hspedu.jdbc;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Zhang Yu
 * @version 1.0
 * 课堂练习
 */
public class Classwork {

    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table news(id int, content varchar(32))");
        statement.executeUpdate("insert into news values(1, '今天早起学习')");
        statement.executeUpdate("insert into news values(2, '中午睡个觉')");
        statement.executeUpdate("insert into news values(3, '下午也起来学习')");
        statement.executeUpdate("insert into news values(4, '工作和学习真不太一样')");
        statement.executeUpdate("insert into news values(5, '工作之余也可以学习')");
        statement.executeUpdate("update news set content = '今天确实早起学习了' where id = 1");
        statement.executeUpdate("delete from news where id = 3 ");
        statement.close();
        connection.close();


    }


}
