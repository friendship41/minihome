package com.jsp.minihome.member.servlet;

import com.jsp.minihome.dao.MemberDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Member;

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
            response.sendRedirect("/error/LoginError.jsp");
        }
//        비밀번호가 존재
        else
        {
//            비밀번호가 같다면
            if(formPw.equals(dbPw))
            {
                
            }
//            비밀번호가 다르다면
            else
            {
                response.sendRedirect("/error/LoginError.jsp");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }
}
