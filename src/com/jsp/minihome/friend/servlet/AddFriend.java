package com.jsp.minihome.friend.servlet;

import com.jsp.minihome.friend.service.FriendService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/AddFriend")
public class AddFriend extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String friendId = request.getParameter("friendId");
        HttpSession session = request.getSession();
        String myId = (String) session.getAttribute("id");

        FriendService friendService = new FriendService();
        int result = friendService.requestAddFriend(myId, friendId);

        String msg = null;
        String url = null;
//        친구 요청 성공
        if(result == 1)
        {
            msg = "친구추가 요청을 보냈습니다.";
            url = "/commonPage/MsgGoHome.jsp";
        }
//        이미 존재하는 요청이거나 친구임
        else if(result == 2)
        {
            msg = "요청이 있거나 친구인 상태입니다.";
            url = "/error/LoginError.jsp";
        }
//        오류나서 실패
        else
        {
            msg = "오류가 발생했습니다.";
            url = "/error/LoginError.jsp";
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp = request.getRequestDispatcher(url);
        disp.forward(request, response);
        return;
    }
}
