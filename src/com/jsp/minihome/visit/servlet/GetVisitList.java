package com.jsp.minihome.visit.servlet;

import com.google.gson.Gson;
import com.jsp.minihome.visit.service.VisitService;
import com.jsp.minihome.visit.vo.VisitVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/GetVisitList")
public class GetVisitList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String nowLocId = (String) session.getAttribute("nowLocId");


        if(nowLocId != null)
        {
            VisitService visitService = new VisitService();
            List<VisitVO> list = visitService.getVisitList(nowLocId);

//            여기서 널값 나오면 에러페이지 보내는거 작업해야함
            if(list != null)
            {
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
        else
        {
            request.setAttribute("msg", "로그인을 해주세요");
            RequestDispatcher disp = request.getRequestDispatcher("/commonPage/MsgGoHome.jsp");
            disp.forward(request, response);
            return;
        }
    }
}
