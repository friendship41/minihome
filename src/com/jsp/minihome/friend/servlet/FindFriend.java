package com.jsp.minihome.friend.servlet;

import com.jsp.minihome.friend.service.FriendService;
import com.jsp.minihome.member.vo.MemberVO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/FindFriend")
public class FindFriend extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        String idOrName = request.getParameter("friendId");

        FriendService friendService = new FriendService();
        List<MemberVO> list = friendService.searchFriend(idOrName);
//        정상적으로 받아왔을때
        if(list != null)
        {
            request.setAttribute("friendList", list);
//            결과값이 없으면
            if(list.size() == 0)
            {
                request.setAttribute("success", "f");
            }
//            결과값이 있으면
            else
            {
                request.setAttribute("success", "s");
            }
        }
//        디비 에러 났을때
        else
        {
            request.setAttribute("success", "f");
        }


        RequestDispatcher disp = request.getRequestDispatcher("/friend/findFriendForm.jsp");
        disp.forward(request, response);
        return;
    }
}
