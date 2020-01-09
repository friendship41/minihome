package com.jsp.minihome.visit.servlet;

import com.jsp.minihome.visit.service.VisitService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteVisit")
public class DeleteVisit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String no = request.getParameter("visitNo");

        VisitService visitService = new VisitService();
        try
        {
            visitService.deleteVisit(Integer.parseInt(no));
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();
        }
        RequestDispatcher disp = request.getRequestDispatcher("GetVisitList");
        disp.forward(request, response);
    }
}
