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
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/Visit")
public class Visit extends HttpServlet
{

    private void responseWriteVisitAjax(VisitVO visitVO, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter out = response.getWriter();
        out.print("이런게 AJAX");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String nowLocId = (String) session.getAttribute("nowLocId");

        System.out.println(id);
        System.out.println(nowLocId);

        if(id != null)
        {
            String contents = (String) request.getParameter("contents");
            System.out.println(contents);

            VisitVO visitVO = new VisitVO();
            visitVO.setUserId(nowLocId);
            visitVO.setWriterId(id);
            visitVO.setContents(contents);

            VisitService visitService = new VisitService();
            visitService.writeVisit(visitVO);

            VisitVO lastVisit = visitService.getLastVisit(nowLocId);


            responseWriteVisitAjax(lastVisit, request, response);

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
