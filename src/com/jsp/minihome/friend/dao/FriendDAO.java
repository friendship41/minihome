package com.jsp.minihome.friend.dao;

import com.jsp.minihome.member.vo.MemberVO;

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

    public int insertFriend(String userId, String friendId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();
            con.setAutoCommit(false);

            String sql1 = "SELECT * FROM MINIHOME_FRIEND WHERE (USER_ID=? AND FRIEND_ID=?) OR (FRIEND_ID=? AND USER_ID=?)";
            String sql2 = "INSERT INTO MINIHOME_FRIEND VALUES(MINIHOME_FRIEND__TABLE_NUM_SEQ.NEXTVAL, ?, ?, 'D')";

            pstmt = con.prepareStatement(sql1);
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);
            pstmt.setString(3, userId);
            pstmt.setString(4, friendId);
            rs = pstmt.executeQuery();

            if(!rs.next())
            {
                pstmt = con.prepareStatement(sql2);
                pstmt.setString(1, userId);
                pstmt.setString(2, friendId);
                int result = pstmt.executeUpdate();

                if(result != 0)
                {
                    con.commit();
                    return 1;   // 요청 성공
                }
                else
                {
                    return -1;
                }
            }
            con.rollback();
            return 2;   // 이미 요청이 존재하거나 친구임
        }
        catch (SQLException e)
        {
            System.out.println("FriendDAO/insertFriend: "+e.getMessage());
            return -1;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public List<MemberVO> selectFriendRequestList(String userId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<MemberVO> list = new ArrayList<>();

        try
        {
            con = this.getConnection();

            String sql = "SELECT M.USER_ID, M.USER_NAME, M.USER_GENDER FROM MINIHOME_FRIEND F JOIN MINIHOME_MEMBER M ON F.FRIEND_ID = M.USER_ID WHERE F.USER_ID=? AND F.AGREE_STATE='D'";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            while(rs.next())
            {
                MemberVO memberVO = new MemberVO();
                memberVO.setUserId(rs.getString("USER_ID"));
                memberVO.setUserName(rs.getString("USER_NAME"));
                memberVO.setUserGender(rs.getString("USER_GENDER"));
                list.add(memberVO);
            }
            return list;

        }
        catch (SQLException e)
        {
            System.out.println("FriendDAO/getFriendRequestList: "+e.getMessage());
            return null;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


    public boolean updateFriendAgree(String userId, String friendId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "UPDATE MINIHOME_FRIEND SET AGREE_STATE='A' WHERE USER_ID=? AND FRIEND_ID=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);

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
            System.out.println("FriendDAO/updateFriendAgree: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }

    public boolean deleteFriendRequest(String userId, String friendId)
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            con = this.getConnection();

            String sql = "DELETE MINIHOME_FRIEND WHERE USER_ID=? AND FRIEND_ID=?";

            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, friendId);

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
            System.out.println("FriendDAO/deleteFriendRequest: "+e.getMessage());
            return false;
        }
        finally
        {
            this.disConnect(con,pstmt,rs);
        }
    }


}

