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

@WebServlet("/ReadFreeBoard")
public class ReadFreeBoard extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String writerId = request.getParameter("writerId");
        String no = request.getParameter("no");
        HttpSession session = request.getSession();
        String myId = (String) session.getAttribute("id");

        FreeBoardService freeBoardService = new FreeBoardService();
        FreeBoardVO freeBoardVO = freeBoardService.getSingleBoard(Integer.parseInt(no));
        request.setAttribute("freeBoardVO", freeBoardVO);

        MemberService memberService = new MemberService();
        MemberVO writerVO = memberService.getSingleMember(writerId);
        request.setAttribute("writerVO", writerVO);

        if(myId != null && !myId.equals(""))
        {
            MemberVO myInfo = memberService.getSingleMember(myId);
            request.setAttribute("myInfo", myInfo);
        }

        RequestDispatcher disp = request.getRequestDispatcher("board/freeBoard/freeBoardRead.jsp");
        disp.forward(request, response);
    }
}
