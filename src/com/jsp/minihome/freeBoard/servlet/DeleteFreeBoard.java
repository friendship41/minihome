package com.jsp.minihome.freeBoard.servlet;

import com.jsp.minihome.freeBoard.service.FreeBoardService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteFreeBoard")
public class DeleteFreeBoard extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        int no = Integer.parseInt(request.getParameter("no"));

        FreeBoardService freeBoardService = new FreeBoardService();
        freeBoardService.deleteFreeBoard(no);

        response.sendRedirect("/minihome/FreeBoardList");
    }
}
