package com.jsp.minihome.friend.servlet;

import com.jsp.minihome.friend.service.FriendService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteFriend")
public class DeleteFriend extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String myId = request.getParameter("userId");
        String friendId = request.getParameter("friendId");

        FriendService friendService = new FriendService();
        friendService.deleteFriend(myId, friendId);
        response.sendRedirect("/minihome/FriendList");
    }
}
