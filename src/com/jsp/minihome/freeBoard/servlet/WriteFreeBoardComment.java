package com.jsp.minihome.freeBoard.servlet;

import com.jsp.minihome.freeBoard.service.FreeBoardService;
import com.jsp.minihome.freeBoard.vo.FreeBoardCommentsVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/WriteFreeBoardComment")
public class WriteFreeBoardComment extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String freeBoardNum = request.getParameter("no");
        String writerId = request.getParameter("writerId");
        String writerName = request.getParameter("writerName");
        String comment = request.getParameter("commentCon");

        FreeBoardCommentsVO freeBoardCommentsVO = new FreeBoardCommentsVO();
        freeBoardCommentsVO.setFreeBoardNum(Integer.parseInt(freeBoardNum));
        freeBoardCommentsVO.setWriterId(writerId);
        freeBoardCommentsVO.setWriterName(writerName);
        freeBoardCommentsVO.setCommentContent(comment);

        FreeBoardService freeBoardService = new FreeBoardService();
        freeBoardService.writeComment(freeBoardCommentsVO);

        RequestDispatcher disp = request.getRequestDispatcher("GetComments?no="+freeBoardNum);
        disp.forward(request, response);
    }
}
