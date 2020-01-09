package com.jsp.minihome.visit.servlet;

import com.google.gson.Gson;
import com.jsp.minihome.member.dao.MemberDAO;
import com.jsp.minihome.visit.dao.VisitDAO;
import com.jsp.minihome.visit.service.VisitService;
import com.jsp.minihome.visit.vo.VisitVO;
import org.apache.commons.collections4.Get;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet("/Visit")
public class Visit extends HttpServlet
{

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String nowLocId = (String) session.getAttribute("nowLocId");

        if(id != null)
        {
            String contents = request.getParameter("contents");
//            System.out.println("visit서블릿-contents: "+contents);

            VisitVO visitVO = new VisitVO();
            visitVO.setUserId(nowLocId);
            visitVO.setWriterId(id);
            visitVO.setContents(contents);

            VisitService visitService = new VisitService();
            visitService.writeVisit(visitVO);

            response.sendRedirect("/minihome/GetVisitList");

//            RequestDispatcher disp = request.getRequestDispatcher("GetVisitList");
//            disp.forward(request, response);
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");

        if(id == null || id.equals(""))
        {
            request.setAttribute("msg", "로그인을 해주세요");
            RequestDispatcher disp = request.getRequestDispatcher("commonPage/MsgGoHome.jsp");
            disp.forward(request, response);
        }
        else
        {
            response.sendRedirect("/minihome/visit/VisitLog.jsp");
        }
//        RequestDispatcher disp = request.getRequestDispatcher("/visit/VisitLog.jsp");
//        disp.forward(request, response);
//        return;
    }
}
