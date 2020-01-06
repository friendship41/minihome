package com.jsp.minihome.friend.service;

import com.jsp.minihome.friend.dao.FriendDAO;
import com.jsp.minihome.member.dao.MemberDAO;
import com.jsp.minihome.member.vo.MemberVO;

import java.util.List;

public class FriendService
{
    private FriendDAO friendDAO;
    private MemberDAO memberDAO;

    public List<MemberVO> searchFriend(String idOrName)
    {
        memberDAO = MemberDAO.getInstance();
        return memberDAO.selectAllMember(idOrName);
    }

    public int requestAddFriend(String userId, String friendId)
    {
        friendDAO = FriendDAO.getInstance();
        return friendDAO.insertFriend(userId, friendId);
    }

    public List<MemberVO> getFriendRequestList(String userId)
    {
        friendDAO = FriendDAO.getInstance();
        return friendDAO.selectFriendRequestList(userId);
    }

    public boolean replyFriendRequest(String userId, String friendId, String tf)
    {
        friendDAO = FriendDAO.getInstance();
        if(tf.equalsIgnoreCase("t"))
        {
            return friendDAO.updateFriendAgree(friendId, userId);
        }
        else if(tf.equalsIgnoreCase("f"))
        {
            return friendDAO.deleteFriendRequest(friendId, userId);
        }
        else
        {
            return false;
        }
    }

    public List<MemberVO> getFriendList(String userId)
    {
        friendDAO = FriendDAO.getInstance();
        return friendDAO.selectFriendList(userId);
    }

    public void deleteFriend(String myId, String friendId)
    {
        friendDAO = FriendDAO.getInstance();
        friendDAO.deleteFriend(myId, friendId);
    }

}
