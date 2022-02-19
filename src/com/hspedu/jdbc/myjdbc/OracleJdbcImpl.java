package com.hspedu.jdbc.myjdbc;

/**
 * @author Zhang Yu
 * @version 1.0
 */
public class OracleJdbcImpl implements JdbcInterface {

    @Override
    public Object getConnection() {
        System.out.println("得到 Oracle 的连接");
        return null;
    }

    @Override
    public void crud() {
        System.out.println("完成对 Oracle 的增删改查");
    }

    @Override
    public void close() {
        System.out.println("关闭 Oracle 的连接");
    }
}
