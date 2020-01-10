package com.jsp.minihome.member.service;

import com.jsp.minihome.member.dao.MemberDAO;
import com.jsp.minihome.member.vo.MemberVO;

import javax.servlet.http.HttpSession;

public class MemberService
{
    private MemberDAO memberDAO;

    public int loginCheck(String id, String password)
    {
        memberDAO = MemberDAO.getInstance();
        String dbPw = memberDAO.selectUserPassword(id);

//        비밀번호가 없다면
        if(dbPw == null)
        {
            return -1;
        }
//        비밀번호가 존재
        else
        {
//            비밀번호가 같다면
            if(password.equals(dbPw))
            {
                return 1;
            }
//            비밀번호가 다르다면
            else
            {
                return 0;
            }
        }
    }

    public boolean signUp(MemberVO memberVO)
    {
        memberDAO = MemberDAO.getInstance();
        return memberDAO.insertMember(memberVO);
    }

    public MemberVO getSingleMember(String id)
    {
        memberDAO = MemberDAO.getInstance();
        return memberDAO.selectSingleMember(id);
    }

}
