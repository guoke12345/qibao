package com.qibao.common.utils;

import java.sql.*;
import java.util.function.Consumer;

/**
 * 链接数据库工具类
 *
 * @author qibao
 * @version v0.1 2021/8/25
 */
public class ConnectMysqlDBUtil {
    private final String userName;
    private final String password;
    private final String url;

    public ConnectMysqlDBUtil(String userName, String password, String url) {
        this.userName = userName;
        this.password = password;
        this.url = url;
    }

    public void exeSelect(String sql, Consumer<ResultSet> consumer) throws SQLException, ClassNotFoundException {
        // mysql-connector-java 5.x及之前版本中的
//        Class.forName("driverClassName: com.mysql.jdbc.Driver");
        // mysql-connector-java 5.x及之后版本中的
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        consumer.accept(resultSet);
        statement.close();
        connection.close();
    }

    public void exeUpdate(String sql) throws ClassNotFoundException, SQLException {
        // mysql-connector-java 5.x及之前版本中的
//        Class.forName("com.mysql.jdbc.Driver");
        // mysql-connector-java 5.x及之前版本中的
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, userName, password);
        Statement statement = connection.createStatement();
        int lines = statement.executeUpdate(sql);
        System.out.println("影响行：" + lines);
        statement.close();
        connection.close();
    }
}
