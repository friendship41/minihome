package com.jsp.minihome.member.servlet;

import com.jsp.minihome.member.dao.MemberDAO;
import com.jsp.minihome.member.service.MemberService;
import com.jsp.minihome.member.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/SignUp")
public class SignUp extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        MemberVO user = new MemberVO();
        user.setUserId(request.getParameter("userId"));
        user.setUserPassword(request.getParameter("userPassword"));
        user.setUserName(request.getParameter("userName"));
        user.setUserGender(request.getParameter("userGender"));
        String phone = request.getParameter("userPhone");
        if(!phone.equals(""))
            user.setUserPhone(phone);


        MemberService memberService = new MemberService();
        boolean result = memberService.signUp(user);
        if(result)
        {
            request.setAttribute("msg", "회원가입 성공!");
            HttpSession session = request.getSession();
            session.setAttribute("id", user.getUserId());
        }
        else
        {
            request.setAttribute("msg", "회원가입 실패!");
        }
        RequestDispatcher disp = request.getRequestDispatcher("commonPage/MsgGoHome.jsp");
        disp.forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
