package com.jsp.minihome.member.servlet;

import com.jsp.minihome.member.dao.MemberDAO;

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

        MemberDAO memberDAO = new MemberDAO();
        String dbPw = memberDAO.getPassword(formId);
//        비밀번호가 없다면
        if(dbPw == null)
        {
            request.setAttribute("msg","존재하지 않는 아이디 입니다.");
        }
//        비밀번호가 존재
        else
        {
//            비밀번호가 같다면
            if(formPw.equals(dbPw))
            {
                HttpSession session = request.getSession();
                session.setAttribute("id",formId);
                response.sendRedirect("/minihome");
                return;
            }
//            비밀번호가 다르다면
            else
            {
                request.setAttribute("msg","잘못된 비밀번호 입니다.");
            }
        }
        RequestDispatcher disp = request.getRequestDispatcher("/error/LoginError.jsp");
        disp.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
