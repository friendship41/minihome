package com.jsp.minihome.visit.dao;

import com.jsp.minihome.visit.vo.VisitVO;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VisitDAO
{
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

    public List<VisitVO> getVisitList(String userId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<VisitVO> list = new ArrayList<>();

        try
        {
            con = this.getConnection();
            String sql = "SELECT M.USER_NAME, V.CONTENTS, TO_CHAR(V.WRITE_DATE,'YY/MM/DD HH24:MI') WRITE_DATE FROM MINIHOME_VISIT V JOIN MINIHOME_MEMBER M ON V.WRITER_ID = M.USER_ID WHERE V.USER_ID=? ORDER BY V.WRITE_DATE DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                VisitVO visitVO = new VisitVO();
                visitVO.setUserName(rs.getString("USER_NAME"));
                visitVO.setContents(rs.getString("CONTENTS"));
                visitVO.setWriteDate(rs.getString("WRITE_DATE"));
                list.add(visitVO);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("VisitDAO/getVisitList: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }
}
