package com.hspedu.jdbc.preparedstatement;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Zhang Yu
 * @version 1.0
 */
@SuppressWarnings({"all"})
public class PreparedStatement_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {

        Scanner scanner = new Scanner(System.in);

        //让用户输入管理员名和密码
        System.out.print("请输入管理员的名字："); //next(): 当接收到 空格或者 ' 就表示结束
        String admin_name = scanner.nextLine();// 老师说明，如果希望看到SQL注入，这里需要用nextLine
        System.out.print("请输入管理员的密码：");
        String admin_pwd = scanner.nextLine();


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

        //3. 得到PreparedStatement
        //3.1 组织sql， sql语句的 ? 就相当于
        String sql = "select name, pwd from admin where name = ? and pwd = ?";

        //3.2 preparedStatement 对象实现了 PreparedStatement 接口的实现类的对象
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        //3.3 给 ？ 赋值
        prepareStatement.setString(1, admin_name);
        prepareStatement.setString(2, admin_pwd);

        //4. 执行select 语句使用 executeQuery
        // 这里执行 executeQuery 就不需要再输入 sql 了
        // 这里执行 executeQuery， 不要在写 sql
        ResultSet resultSet = prepareStatement.executeQuery();
        if(resultSet.next()) { //如果查询到一条记录，则说明该管理存在
            System.out.println("恭喜， 登录成功");
        } else {
            System.out.println("对不起，登录失败");
        }

        //关闭连接
        resultSet.close();
        prepareStatement.close();
        connection.close();
    }
}
