package com.jsp.minihome.freeBoard.servlet;

import com.jsp.minihome.freeBoard.service.FreeBoardService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteComment")
public class DeleteComment extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        String commentsTableNum = request.getParameter("commentNum");
        String no = request.getParameter("no");

        FreeBoardService freeBoardService = new FreeBoardService();
        freeBoardService.deleteComment(Integer.parseInt(commentsTableNum));

        RequestDispatcher disp = request.getRequestDispatcher("GetComments?no="+no);
        disp.forward(request, response);

    }
}
