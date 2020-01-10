package com.jsp.minihome.freeBoard.servlet;

import com.jsp.minihome.freeBoard.service.FreeBoardService;
import com.jsp.minihome.freeBoard.vo.FreeBoardVO;
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

@WebServlet("/NewFreeBoard")
public class NewFreeBoard extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        String id = request.getParameter("userId");
        String name = request.getParameter("userName");
        String email = request.getParameter("userEmail");
        String subject = request.getParameter("subject");
        String category = request.getParameter("cateVal");
        String content = request.getParameter("content");
        String imgLoc = request.getParameter("imgLoc");
        String ip = request.getParameter("ip");

        FreeBoardVO freeBoardVO = new FreeBoardVO();
        freeBoardVO.setWriterId(id);
        freeBoardVO.setWriterName(name);
        freeBoardVO.setEmail(email);
        freeBoardVO.setSubject(subject);
        freeBoardVO.setCategories(category);
        freeBoardVO.setContent(content);
        freeBoardVO.setImgLoc(imgLoc);
        freeBoardVO.setIp(ip);

        FreeBoardService freeBoardService = new FreeBoardService();
        freeBoardService.writeFreeBoard(freeBoardVO);

        response.sendRedirect("/minihome/FreeBoardList");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");

        MemberService memberService = new MemberService();
        MemberVO memberVO = memberService.getSingleMember(id);

        if(memberVO != null)
        {
            request.setAttribute("myInfo", memberVO);
            RequestDispatcher disp = request.getRequestDispatcher("board/freeBoard/freeBoardForm.jsp");
            disp.forward(request, response);
        }
    }
}
