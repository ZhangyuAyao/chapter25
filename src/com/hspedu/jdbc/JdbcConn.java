package com.hspedu.jdbc;
import com.mysql.cj.jdbc.Driver;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Zhang Yu
 * @version 1.0
 * 分析java 连接 mysql 的5种方式
 */
public class JdbcConn {

    @Test
    public void connect01() throws SQLException {
        Driver driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        // 将 用户名和密码放入到 Properties 对象
        Properties properties = new Properties();
        properties.setProperty("user", "root");// 用户
        properties.setProperty("password", "hsp");// 密码
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    //方式2
    @Test
    public void connect02() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver类，动态加载，更加的灵活，减少依赖性
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");;
        Driver driver = (Driver)aClass.newInstance();

        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        // 将 用户名和密码放入到 Properties 对象
        Properties properties = new Properties();
        properties.setProperty("user", "root");// 用户
        properties.setProperty("password", "hsp");// 密码
        Connection connect = driver.connect(url, properties);
        System.out.println("方式2："+connect);
    }

    //方式3 使用DriverManager 替代 Driver 进行统一管理
    @Test
    public void connect03() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        //使用反射加载Driver
        Class<?> aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) aClass.newInstance();

        //创建url 和 user 和password
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        String user = "root";
        String password = "hsp";

        DriverManager.registerDriver(driver);//注册Driver驱动
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第三种方式="+connection);
    }

    //方式4：使用Class.forName 自动完成注册驱动，简化代码
    @Test
    public void connect04() throws ClassNotFoundException, SQLException {
        //使用反射加载了 Driver 类
        //在加载 Driver类时，完成注册
        /*
            源码：1. 静态代码块，在类加载时，完成一次
            2. DriverManager.registerDriver(new Driver());
            3. 因此注册 driver 的工作已经完成
        * static {
                try {
                    DriverManager.registerDriver(new Driver());
                } catch (SQLException var1) {
                    throw new RuntimeException("Can't register driver!");
                }
            }
        * */
        //下面这句话不需要写也可以，因为jdk1.5之后能够自动调用jar包下的
        // META-INF\services\java.sql.Driver ，里面指定了 Driver 的地址
        // 不过还是写上比较好，这样子会比较明确
        Class.forName("com.mysql.cj.jdbc.Driver");

        //创建url 和 user 和password
        String url = "jdbc:mysql://localhost:3306/hsp_db02";
        String user = "root";
        String password = "hsp";
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("第4种方式 " + connection);
    }

    //方式5
    //在方式4的基础上改进，增加配置文件，连接数据库更加灵活
    @Test
    public void connect05() throws IOException, ClassNotFoundException, SQLException {

        //通过Properties对象获取配置文件的信息
        Properties properties = new Properties();
        properties.load(new FileInputStream("src\\mysql.properties"));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");

        Class.forName(driver);

        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println("方式五：" + connection);
    }




}
