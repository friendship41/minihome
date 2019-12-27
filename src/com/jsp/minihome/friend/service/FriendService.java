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

}
