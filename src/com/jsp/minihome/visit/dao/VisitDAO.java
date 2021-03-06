package com.jsp.minihome.visit.dao;

import com.jsp.minihome.visit.servlet.Visit;
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
    private VisitDAO()
    {
    }

    private static VisitDAO visitDAO;

    public static VisitDAO getInstance(){
        if(visitDAO == null)
        {
            synchronized (VisitDAO.class){
                visitDAO = new VisitDAO();
            }
        }
        return visitDAO;
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

    public List<VisitVO> selectAllVisit(String userId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<VisitVO> list = new ArrayList<>();

        try
        {
            con = this.getConnection();
            String sql = "SELECT V.VISIT_TABLE_NUM, V.WRITER_ID, M.USER_NAME, V.CONTENTS, TO_CHAR(V.WRITE_DATE,'YY/MM/DD HH24:MI') WRITE_DATE FROM MINIHOME_VISIT V JOIN MINIHOME_MEMBER M ON V.WRITER_ID = M.USER_ID WHERE V.USER_ID=? ORDER BY V.WRITE_DATE DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                VisitVO visitVO = new VisitVO();
                visitVO.setVisitNo(rs.getInt("VISIT_TABLE_NUM"));
                visitVO.setWriterId(rs.getString("WRITER_ID"));
                visitVO.setUserName(rs.getString("USER_NAME"));
                visitVO.setContents(rs.getString("CONTENTS"));
                visitVO.setWriteDate(rs.getString("WRITE_DATE"));
                list.add(visitVO);
            }
            return list;
        }
        catch (SQLException e)
        {
            System.out.println("VisitDAO/selectAllVisit: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public boolean insertSingleVisit(VisitVO visitVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String sql = "INSERT INTO MINIHOME_VISIT VALUES(MINIHOME_VISIT__TABLE_NUM_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, visitVO.getUserId());
            pstmt.setString(2, visitVO.getWriterId());
            pstmt.setString(3, visitVO.getContents());

            int result = pstmt.executeUpdate();

            if(result == 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("VisitDAO/insertSingleVisit: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public boolean deleteSingleVisit(int no)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String sql = "DELETE MINIHOME_VISIT WHERE VISIT_TABLE_NUM=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);

            int result = pstmt.executeUpdate();

            if(result == 0)
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        catch (SQLException e)
        {
            System.out.println("VisitDAO/insertSingleVisit: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public VisitVO selectLastVisit(String userId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            String sql = "SELECT V.VISIT_TABLE_NUM, V.WRITER_ID, M.USER_NAME, V.CONTENTS, TO_CHAR(V.WRITE_DATE,'YY/MM/DD HH24:MI') WRITE_DATE FROM MINIHOME_VISIT V JOIN MINIHOME_MEMBER M ON V.WRITER_ID = M.USER_ID WHERE V.USER_ID=? ORDER BY V.WRITE_DATE DESC";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            rs = pstmt.executeQuery();

            VisitVO visitVO = null;
            if(rs.next())
            {
                visitVO = new VisitVO();
                visitVO.setVisitNo(rs.getInt("VISIT_TABLE_NUM"));
                visitVO.setWriterId(rs.getString("WRITER_ID"));
                visitVO.setUserName(rs.getString("USER_NAME"));
                visitVO.setContents(rs.getString("CONTENTS"));
                visitVO.setWriteDate(rs.getString("WRITE_DATE"));
            }
            return visitVO;
        }
        catch (SQLException e)
        {
            System.out.println("VisitDAO/selectAllVisit: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }



}
