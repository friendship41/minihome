package com.jsp.minihome.friend.dao;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class FriendDAO
{
    private FriendDAO()
    {}

    private static FriendDAO friendDAO;

    public static FriendDAO getInstance()
    {
        if(friendDAO == null)
        {
            synchronized (FriendDAO.class)
            {
                friendDAO = new FriendDAO();
            }
        }
        return friendDAO;
    }

    private Connection getConnection()
    {
        Connection con = null;
        try
        {
            Context init = new InitialContext();
            DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/miniHome");
            con = ds.getConnection();
        }
        catch (NamingException | SQLException e)
        {
            e.printStackTrace();
            System.out.println("커넥션 받아오는 곳에서 오류");
        }
        return con;
    }

    private void disConnect(Connection con, PreparedStatement pstmt, ResultSet rs){
        try {if(rs!=null && !rs.isClosed()){rs.close();}} catch (SQLException e1) {}
        try {if(pstmt!=null && !pstmt.isClosed()){pstmt.close();}}catch (SQLException e) {}
        try {if(con!=null && !con.isClosed()){con.close();}}catch (SQLException e){}
    }


}
