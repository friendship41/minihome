package com.jsp.minihome.freeBoard.servlet;

import com.google.gson.Gson;
import com.jsp.minihome.freeBoard.service.FreeBoardService;
import com.jsp.minihome.freeBoard.vo.FreeBoardCommentsVO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetComments")
public class GetComments extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String no = request.getParameter("no");

        FreeBoardService freeBoardService = new FreeBoardService();
        List<FreeBoardCommentsVO> list = freeBoardService.getComments(Integer.parseInt(no));

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        Gson gson = new Gson();
        String jsonData = gson.toJson(list);
        System.out.println(jsonData);

        PrintWriter out = response.getWriter();
        try
        {
            out.println(jsonData);
        }
        finally
        {
            out.close();
        }
        return;

    }
}
