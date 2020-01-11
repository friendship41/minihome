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

        int listSize = 10;
        int blockSize = 5;

        if(nowPage == null || nowPage.equals(""))
        {
            nowPage = "1";
        }
        int nowPageInt = Integer.parseInt(nowPage);
        int startNum = listSize*(nowPageInt-1)+1;
        int endNum =nowPageInt*listSize;

        FreeBoardService freeBoardService = new FreeBoardService();

        int cnt = freeBoardService.getFreeBoardCount();
        cnt--;
        List<FreeBoardVO> list = freeBoardService.getFreeBoardList(startNum, endNum);


        int blockCnt = (int)(cnt/listSize)+(cnt%listSize==0 ? 0:1);
        int startBlockNum = ((int)((nowPageInt-1)/blockSize))*blockSize + 1;
        int endBlockNum = startBlockNum+blockSize - 1;

        if(blockCnt < endBlockNum)
        {
            endBlockNum = blockCnt;
        }

        request.setAttribute("startBlockNum",startBlockNum);
        request.setAttribute("endBlockNum", endBlockNum);
        request.setAttribute("blockCnt", blockCnt);
        request.setAttribute("nowPage", nowPage);
        request.setAttribute("freeBoardCnt", cnt);
        request.setAttribute("freeBoardLists", list);

        RequestDispatcher disp = request.getRequestDispatcher("/board/freeBoard/freeBoardListPage.jsp");
        disp.forward(request, response);
    }
}
