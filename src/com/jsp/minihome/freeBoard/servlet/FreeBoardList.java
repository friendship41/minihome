package com.jsp.minihome.freeBoard.servlet;

import com.jsp.minihome.freeBoard.service.FreeBoardService;
import com.jsp.minihome.freeBoard.vo.FreeBoardVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/FreeBoardList")
public class FreeBoardList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String nowPage = request.getParameter("nowPage");

        int startNum=0;
        int endNum=0;

        if(nowPage == null || nowPage.equals(""))
        {
            startNum = 1;
            endNum = 10;
        }


        FreeBoardService freeBoardService = new FreeBoardService();

        List<FreeBoardVO> list = freeBoardService.getFreeBoardList(startNum, endNum);

        request.setAttribute("freeBoardLists", list);
        RequestDispatcher disp = request.getRequestDispatcher("/board/freeBoard/freeBoardListPage.jsp");
        disp.forward(request, response);
    }
}
