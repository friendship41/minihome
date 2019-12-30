package com.jsp.minihome.friend.servlet;

import com.jsp.minihome.friend.service.FriendService;
import com.jsp.minihome.member.dao.MemberDAO;
import com.jsp.minihome.member.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/FriendList")
public class FriendList extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
         request.setCharacterEncoding("utf-8");

        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");

        if(id != null)
        {
            FriendService friendService = new FriendService();
            List<MemberVO> list = friendService.getFriendRequestList(id);

            request.setAttribute("requestFriendList", list);

            RequestDispatcher disp = request.getRequestDispatcher("/friend/myFriendList.jsp");
            disp.forward(request, response);
        }
    }
}
