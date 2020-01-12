package com.jsp.minihome.freeBoard.dao;

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

public class FreeBoardDAO
{
    private FreeBoardDAO()
    {}

    private static FreeBoardDAO freeBoardDAO;

    public static FreeBoardDAO getInstance()
    {
        if(freeBoardDAO == null)
        {
            synchronized (FreeBoardDAO.class)
            {
                freeBoardDAO = new FreeBoardDAO();
            }
        }
        return freeBoardDAO;
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

    public void insertNewBoard(FreeBoardVO freeBoardVO)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "INSERT INTO MINIHOME_FREEBOARD VALUES (MINIHOME_FREEBOARD__NO_SEQ.NEXTVAL, ?,?,?,?,?,?,?,0,(SELECT MAX(REF)+1 FROM MINIHOME_FREEBOARD),0,0,SYSDATE,?)";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, freeBoardVO.getWriterId());
            pstmt.setString(2, freeBoardVO.getWriterName());
            pstmt.setString(3, freeBoardVO.getEmail());
            pstmt.setString(4, freeBoardVO.getSubject());
            pstmt.setString(5, freeBoardVO.getCategories());
            pstmt.setString(6, freeBoardVO.getContent());
            if(freeBoardVO.getImgLoc() != null && !freeBoardVO.getImgLoc().equals(""))
            {
                pstmt.setString(7, freeBoardVO.getImgLoc());
            }
            else
            {
                pstmt.setString(7, null);
            }
            pstmt.setString(8, freeBoardVO.getIp());

            pstmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardDAO/insertNewBoard: "+e.getMessage());
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public int selectBoardCount()
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "SELECT COUNT(*) cnt FROM MINIHOME_FREEBOARD";

            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            rs.next();
            return rs.getInt("CNT");
        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardDAO/selectBoardCount: "+e.getMessage());
            return -1;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


//    public int selectSearchBoardCount(String searchType, String searchWord)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql1 = "SELECT COUNT(*) CNT FROM JAVALINE_BOARD1 WHERE WRITER LIKE ?";
//            String sql2 = "SELECT COUNT(*) CNT FROM JAVALINE_BOARD1 WHERE SUBJECT LIKE ?";
//            String sql3 = "SELECT COUNT(*) CNT FROM JAVALINE_BOARD1 WHERE CONTENT LIKE ?";
//
//            if(searchType.equalsIgnoreCase("writer"))
//            {
//                pstmt = con.prepareStatement(sql1);
//            }
//            else if(searchType.equalsIgnoreCase("subject"))
//            {
//                pstmt = con.prepareStatement(sql2);
//            }
//            else
//            {
//                pstmt = con.prepareStatement(sql3);
//            }
//
//            pstmt.setString(1, ("%"+searchWord+"%"));
//            rs = pstmt.executeQuery();
//
//            rs.next();
//            return rs.getInt("CNT");
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/selectSearchBoardCount: "+e.getMessage());
//            return -1;
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//

    public List<FreeBoardVO> selectAllBoardList(int startNum, int endNum)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<FreeBoardVO> list = new ArrayList<>();

        try
        {
            con = this.getConnection();

            String sql = "SELECT * FROM (SELECT ROWNUM RN, B.* FROM (SELECT NO, WRITER_ID, WRITER_NAME, EMAIL, SUBJECT, CATEGORIES, CONTENT, IMG_LOC, READCNT, REF, STEP, DEPTH, TO_CHAR(REGDATE,'YYYY/MM/DD') REGDATE, IP FROM MINIHOME_FREEBOARD A ORDER BY REF DESC, STEP ASC) B) WHERE RN>=? AND RN<=? AND NO!=-1";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, startNum);
            pstmt.setInt(2, endNum);
            rs = pstmt.executeQuery();

            while (rs.next())
            {
                FreeBoardVO freeBoardVO = new FreeBoardVO();

                freeBoardVO.setNo(rs.getInt("NO"));
                freeBoardVO.setWriterId(rs.getString("WRITER_ID"));
                freeBoardVO.setWriterName(rs.getString("WRITER_NAME"));
//                freeBoardVO.setEmail(rs.getString("EMAIL"));
                freeBoardVO.setSubject(rs.getString("SUBJECT"));
                freeBoardVO.setCategories(rs.getString("CATEGORIES"));
//                freeBoardVO.setContent(rs.getString("CONTENT"));
//                freeBoardVO.setImgLoc(rs.getString("IMG_LOC"));
                freeBoardVO.setReadcnt(rs.getInt("READCNT"));
                freeBoardVO.setRef(rs.getInt("REF"));
                freeBoardVO.setStep(rs.getInt("STEP"));
                freeBoardVO.setDepth(rs.getInt("DEPTH"));
                freeBoardVO.setRegdate(rs.getString("REGDATE"));
//                freeBoardVO.setIp(rs.getString("IP"));
                list.add(freeBoardVO);
            }

            return list;

        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardDAO/selectAllBoardList: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }

//
//    public List<Board1VO> selectSearchedBoardList(int startNum, int endNum, String searchType, String searchWord)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        List<Board1VO> list = new ArrayList<>();
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql1 = "SELECT * FROM (SELECT ROWNUM RN, NO, WRITER, EMAIL, SUBJECT, READCNT, REF, STEP, DEPTH, REGDATE, CONTENT, IP FROM (SELECT NO, WRITER, EMAIL, SUBJECT, READCNT, REF, STEP, DEPTH, TO_CHAR(REGDATE,'YYYY/MM/DD') REGDATE, CONTENT, IP FROM JAVALINE_BOARD1 WHERE WRITER LIKE ? ORDER BY REF DESC, STEP ASC)) WHERE RN>=? AND RN<=?";
//            String sql2 = "SELECT * FROM (SELECT ROWNUM RN, NO, WRITER, EMAIL, SUBJECT, READCNT, REF, STEP, DEPTH, REGDATE, CONTENT, IP FROM (SELECT NO, WRITER, EMAIL, SUBJECT, READCNT, REF, STEP, DEPTH, TO_CHAR(REGDATE,'YYYY/MM/DD') REGDATE, CONTENT, IP FROM JAVALINE_BOARD1 WHERE SUBJECT LIKE ? ORDER BY REF DESC, STEP ASC)) WHERE RN>=? AND RN<=?";
//            String sql3 = "SELECT * FROM (SELECT ROWNUM RN, NO, WRITER, EMAIL, SUBJECT, READCNT, REF, STEP, DEPTH, REGDATE, CONTENT, IP FROM (SELECT NO, WRITER, EMAIL, SUBJECT, READCNT, REF, STEP, DEPTH, TO_CHAR(REGDATE,'YYYY/MM/DD') REGDATE, CONTENT, IP FROM JAVALINE_BOARD1 WHERE CONTENT LIKE ? ORDER BY REF DESC, STEP ASC)) WHERE RN>=? AND RN<=?";
//
//            if(searchType.equalsIgnoreCase("writer"))
//            {
//                pstmt = con.prepareStatement(sql1);
//            }
//            else if(searchType.equalsIgnoreCase("subject"))
//            {
//                pstmt = con.prepareStatement(sql2);
//            }
//            else
//            {
//                pstmt = con.prepareStatement(sql3);
//            }
//
//            pstmt.setString(1, ("%"+searchWord+"%"));
//            pstmt.setInt(2, startNum);
//            pstmt.setInt(3, endNum);
//            rs = pstmt.executeQuery();
//
//            while (rs.next())
//            {
//                Board1VO board1VO = new Board1VO();
//                board1VO.setNo(rs.getInt("NO"));
//                board1VO.setWriter(rs.getString("WRITER"));
////                board1VO.setEmail(rs.getString("EMAIL"));
//                board1VO.setSubject(rs.getString("SUBJECT"));
//                board1VO.setReadcnt(rs.getInt("READCNT"));
//                board1VO.setRef(rs.getInt("REF"));
//                board1VO.setStep(rs.getInt("STEP"));
//                board1VO.setDepth(rs.getInt("DEPTH"));
//                board1VO.setRegdate(rs.getString("REGDATE"));
////                board1VO.setContent(rs.getString("CONTENT"));
////                board1VO.setIp(rs.getString("IP"));
//                list.add(board1VO);
//            }
//
//            return list;
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/selectSearchedBoardList: "+e.getMessage());
//            return null;
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//
//


    public void updateReadCnt(int no)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "UPDATE MINIHOME_FREEBOARD SET READCNT=READCNT+1 WHERE NO=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            pstmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardDAO/updateReadCnt: "+e.getMessage());
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }




    public FreeBoardVO selectSingleFreeBoard(int no)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FreeBoardVO freeBoardVO = new FreeBoardVO();

        try
        {
            con = this.getConnection();

            String sql = "SELECT NO, WRITER_ID, WRITER_NAME, EMAIL, SUBJECT, CATEGORIES, CONTENT, IMG_LOC, READCNT, REF, STEP, DEPTH, TO_CHAR(REGDATE,'YYYY/MM/DD') REGDATE, IP FROM MINIHOME_FREEBOARD WHERE NO=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            if (rs.next())
            {
                freeBoardVO.setNo(no);
                freeBoardVO.setWriterId(rs.getString("WRITER_ID"));
                freeBoardVO.setWriterName(rs.getString("WRITER_NAME"));
                freeBoardVO.setEmail(rs.getString("EMAIL"));
                freeBoardVO.setSubject(rs.getString("SUBJECT"));
                freeBoardVO.setCategories(rs.getString("CATEGORIES"));
                freeBoardVO.setContent(rs.getString("CONTENT"));
                freeBoardVO.setImgLoc(rs.getString("IMG_LOC"));
                freeBoardVO.setReadcnt(rs.getInt("READCNT"));
                freeBoardVO.setRef(rs.getInt("REF"));
                freeBoardVO.setStep(rs.getInt("STEP"));
                freeBoardVO.setDepth(rs.getInt("DEPTH"));
                freeBoardVO.setRegdate(rs.getString("REGDATE"));
                freeBoardVO.setIp(rs.getString("IP"));

                return freeBoardVO;
            }
            else
            {
                return null;
            }

        }
        catch (SQLException e)
        {
            System.out.println("FreeBoardDAO/selectSingleFreeBoard: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


//    public void updateSingleBoard(Board1VO board1VO)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql = "UPDATE JAVALINE_BOARD1 SET SUBJECT=?, CONTENT=?, REGDATE=SYSDATE, IP=? WHERE NO=?";
//
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, board1VO.getSubject());
//            pstmt.setString(2, board1VO.getContent());
//            pstmt.setString(3, board1VO.getIp());
//            pstmt.setInt(4, board1VO.getNo());
//            pstmt.executeUpdate();
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/updateSingleBoard: "+e.getMessage());
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//
//    public void deleteSingleBoard(int no)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql = "DELETE FROM JAVALINE_BOARD1 WHERE NO=?";
//
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, no);
//            pstmt.executeUpdate();
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/deleteSingleBoard: "+e.getMessage());
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//
//
//    public void updateRefStepDepth(Board1VO board1VO)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql = "UPDATE JAVALINE_BOARD1 SET STEP=STEP+1 WHERE REF=? AND STEP>?";
//
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, board1VO.getRef());
//            pstmt.setInt(2, board1VO.getStep());
//            pstmt.executeUpdate();
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/updateRefStepDepth: "+e.getMessage());
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//
//
//    public void insertReply(Board1VO board1VO)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql = "INSERT INTO JAVALINE_BOARD1 VALUES(JAVALINE_BOARD1__NO_SEQ.NEXTVAL, ?, ?, ?, 0, ?, ?, ?, SYSDATE, ?, ?)";
//
//            pstmt = con.prepareStatement(sql);
//            pstmt.setString(1, board1VO.getWriter());
//            pstmt.setString(2, board1VO.getEmail());
//            pstmt.setString(3, board1VO.getSubject());
//            pstmt.setInt(4, board1VO.getRef());
//            pstmt.setInt(5, board1VO.getStep()+1);
//            pstmt.setInt(6, board1VO.getDepth()+1);
//            pstmt.setString(7, board1VO.getContent());
//            pstmt.setString(8, board1VO.getIp());
//            pstmt.executeUpdate();
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/insertReply: "+e.getMessage());
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//
//    public int selectReplyCount(int no)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql = "SELECT COUNT(NO) CNT FROM JAVALINE_BOARD1 WHERE REF=(SELECT REF FROM JAVALINE_BOARD1 WHERE NO = ?) AND STEP>(SELECT STEP FROM JAVALINE_BOARD1 WHERE NO = ?)";
//
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, no);
//            pstmt.setInt(2, no);
//            rs = pstmt.executeQuery();
//
//            rs.next();
//            return rs.getInt("CNT");
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/selectReplyCount: "+e.getMessage());
//            return -1;
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
//
//
//
//    public void updateBoardToNull(int no)
//    {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        try
//        {
//            con = this.getConnection();
//
//            String sql = "UPDATE JAVALINE_BOARD1 SET WRITER='[삭제됨]', EMAIL='[삭제됨]', SUBJECT='[삭제됨]', CONTENT='[삭제됨]' WHERE NO=?";
//
//            pstmt = con.prepareStatement(sql);
//            pstmt.setInt(1, no);
//            pstmt.executeUpdate();
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println("Board1DAO/updateBoardToNull: "+e.getMessage());
//        }
//        finally
//        {
//            this.disConnect(con,pstmt,rs);
//        }
//    }
}
