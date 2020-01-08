package com.jsp.minihome.member.dao;

import com.jsp.minihome.member.vo.MemberVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO
{
//    DBConnectionPool pool = DBConnectionPool.getInstance();

    private static MemberDAO memberDAO;

    private MemberDAO()
    {}

    public static MemberDAO getInstance()
    {
        if(memberDAO == null)
        {
            synchronized (MemberDAO.class){
                memberDAO = new MemberDAO();
            }
        }
        return memberDAO;
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

    public String selectUserPassword(String userId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
//                    pool.getConnection();
            String sql = "SELECT USER_PASSWORD FROM MINIHOME_MEMBER WHERE USER_ID =?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            rs.next();
            return rs.getString("USER_PASSWORD");
        }
        catch (SQLException e)
        {
            System.out.println("MemberDAO/selectUserPassword: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }

    public boolean insertMember(MemberVO memberVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String sql = "INSERT INTO MINIHOME_MEMBER VALUES(?,?,?,?,?,?,'N', null, 1, 1)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberVO.getUserId());
            pstmt.setString(2, memberVO.getUserPassword());
            pstmt.setString(3, memberVO.getUserName());
            pstmt.setString(4, memberVO.getUserGender());
            pstmt.setString(5, memberVO.getUserEmail());
            pstmt.setString(6, memberVO.getUserPhone());
            int go = pstmt.executeUpdate();

            if(go > 0)
                return true;
            else
                return false;
        }
        catch (SQLException e)
        {
            System.out.println("MemberDAO/insertMember: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public List<MemberVO> selectAllMember(String idOrName)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberVO> memberVOList = new ArrayList<>();

        try
        {
            con = this.getConnection();
            String sql = "SELECT USER_ID, USER_NAME, USER_GENDER FROM MINIHOME_MEMBER WHERE USER_ID=? OR USER_NAME=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, idOrName);
            pstmt.setString(2, idOrName);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                MemberVO memberVO = new MemberVO();
                memberVO.setUserId(rs.getString("USER_ID"));
                memberVO.setUserName(rs.getString("USER_NAME"));
                memberVO.setUserGender(rs.getString("USER_GENDER"));
                memberVOList.add(memberVO);
            }
            return memberVOList;
        }
        catch (SQLException e)
        {
            System.out.println("MemberDAO/insertMember: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }

}
