package com.jsp.minihome.visit.servlet;

import com.jsp.minihome.member.dao.MemberDAO;
import com.jsp.minihome.visit.dao.VisitDAO;
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
import java.util.List;

@WebServlet("/Visit")
public class Visit extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String nowLocId = (String) session.getAttribute("nowLocId");
        if(id != null)
        {
            String contents = request.getParameter("contents");

        }
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
                request.setAttribute("visitList", list);
            }

        }
        else
        {
            request.setAttribute("msg", "로그인을 해주세요");
            RequestDispatcher disp = request.getRequestDispatcher("/commonPage/MsgGoHome.jsp");
            disp.forward(request, response);
            return;
        }
        RequestDispatcher disp = request.getRequestDispatcher("/visit/VisitLog.jsp");
        disp.forward(request, response);
        return;
    }
}
