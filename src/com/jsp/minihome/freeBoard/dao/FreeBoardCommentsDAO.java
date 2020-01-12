package com.jsp.minihome.freeBoard.dao;

import com.jsp.minihome.freeBoard.vo.FreeBoardCommentsVO;
import com.jsp.minihome.freeBoard.vo.FreeBoardVO;

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

public class FreeBoardCommentsDAO
{
    private FreeBoardCommentsDAO()
    {}

    private static FreeBoardCommentsDAO freeBoardCommentsDAO;

    public static FreeBoardCommentsDAO getInstance()
    {
        if(freeBoardCommentsDAO == null)
        {
            synchronized (FreeBoardCommentsDAO.class)
            {
                freeBoardCommentsDAO = new FreeBoardCommentsDAO();
            }
        }
        return freeBoardCommentsDAO;
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


    public List<FreeBoardCommentsVO> selectAllFreeBoardCommentsList(int no)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FreeBoardCommentsVO> list = new ArrayList<>();

        try
        {
            con = this.getConnection();

            String sql = "SELECT M.PROFILE_IMG_LOC, FC.COMMENTS_TABLE_NUM, FC.FREEBOARD_NUM, FC.WRITER_ID, FC.WRITER_NAME, FC.COMMENT_CONTENT, TO_CHAR(FC.COMMENT_DATE,'MON DD, YYYY') COMMENT_DATE FROM MINIHOME_FREEBOARD_COMMENTS FC JOIN MINIHOME_MEMBER M ON FC.WRITER_ID = M.USER_ID WHERE FREEBOARD_NUM=? ORDER BY FC.COMMENT_DATE DESC";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                FreeBoardCommentsVO freeBoardCommentsVO = new FreeBoardCommentsVO();
                freeBoardCommentsVO.setWriterProfileImgLoc(rs.getString("PROFILE_IMG_LOC"));
                freeBoardCommentsVO.setCommentsTableNum(rs.getInt("COMMENTS_TABLE_NUM"));
                freeBoardCommentsVO.setFreeBoardNum(rs.getInt("FREEBOARD_NUM"));
                freeBoardCommentsVO.setWriterId(rs.getString("WRITER_ID"));
                freeBoardCommentsVO.setWriterName(rs.getString("WRITER_NAME"));
                freeBoardCommentsVO.setCommentContent(rs.getString("COMMENT_CONTENT"));
                freeBoardCommentsVO.setCommentDate(rs.getString("COMMENT_DATE"));

                list.add(freeBoardCommentsVO);
            }

            return list;

        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardCommentsDAO/selectAllFreeBoardCommentsList: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public boolean insertComment(FreeBoardCommentsVO freeBoardCommentsVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "INSERT INTO MINIHOME_FREEBOARD_COMMENTS VALUES(MINIHOME_FREEBOARD_COMMENTS__NO_SEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE)";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, freeBoardCommentsVO.getFreeBoardNum());
            pstmt.setString(2, freeBoardCommentsVO.getWriterId());
            pstmt.setString(3, freeBoardCommentsVO.getWriterName());
            pstmt.setString(4, freeBoardCommentsVO.getCommentContent());
            int result = pstmt.executeUpdate();

            if(result > 0)
            {
                return true;
            }
            else
            {
                return false;
            }


        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardCommentsDAO/insertComment: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public boolean deleteComment(int commentsTableNum)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "DELETE FROM MINIHOME_FREEBOARD_COMMENTS WHERE COMMENTS_TABLE_NUM=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, commentsTableNum);
            int result = pstmt.executeUpdate();

            if(result > 0)
            {
                return true;
            }
            else
            {
                return false;
            }


        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardCommentsDAO/deleteComment: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


}
