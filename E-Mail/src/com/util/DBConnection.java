package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	public Connection getConnection()
    {
        String url = "jdbc:mysql://localhost:3306/email";
        String username = "root";
        String password = "2123";
        Connection conn = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, password);
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("驱动加载出错");
        }
        catch(SQLException e)
        {
            System.out.println("数据库连接出错");
        }
        return conn;

    }
    public void close(ResultSet rs,PreparedStatement p,Connection conn)
    {
        try
        {
            rs.close();
            p.close();
            conn.close();
        }
        catch(SQLException e)
        {
            System.out.println("数据库关闭出错");
        }
    }

}
