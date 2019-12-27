package com.jsp.minihome.member.servlet;

import com.jsp.minihome.member.service.MemberService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Login")
public class Login extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String formId = request.getParameter("id");
        String formPw = request.getParameter("password");

        MemberService memberService = new MemberService();
        int result = memberService.loginCheck(formId, formPw);

        switch (result)
        {
//        비밀번호가 없다면
            case -1:
                request.setAttribute("msg","존재하지 않는 아이디 입니다.");
                break;
//        비밀번호가 같다면
            case 1:
                HttpSession session = request.getSession();
                session.setAttribute("id",formId);
                response.sendRedirect("/minihome");
                return;
//        비밀번호가 다르다면
            case 0:
                request.setAttribute("msg","잘못된 비밀번호 입니다.");
                break;
        }
        RequestDispatcher disp = request.getRequestDispatcher("/error/LoginError.jsp");
        disp.forward(request,response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
