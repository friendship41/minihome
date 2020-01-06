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

@WebServlet("/ResponseAddFriend")
public class ResponseAddFriend extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        String userId = (String) session.getAttribute("id");
//        로그인 상태 체크
        if(userId != null)
        {
            String friendId = request.getParameter("friendId");
            String tf = request.getParameter("res");
//            친구아이디 값 넘어온거 체크
            if(friendId != null && !friendId.equals(""))
            {
//                수락 거절 파라미터 체크
                if(tf != null && !tf.equals("") && (tf.equalsIgnoreCase("t") || tf.equalsIgnoreCase("f")))
                {
                    FriendService friendService = new FriendService();
                    boolean result = friendService.replyFriendRequest(userId, friendId, tf);
//                    정상 처리되었는지 체크
                    if(result)
                    {
//                        친구 수락이라면
                        if(tf.equalsIgnoreCase("t"))
                        {
                            request.setAttribute("msg", "친구수락이 정상처리 되었습니다.");
                        }
//                        거절이라면
                        else
                        {
                            request.setAttribute("msg", "거절이 정상처리 되었습니다.");
                        }
                    }
//                    서버에서 뭔가 문제가 생기면
                    else
                    {
                        request.setAttribute("msg", "처리도중 문제가 생겼습니다 (친구수락/거절)");
                    }
                    request.setAttribute("href", "/minihome/FriendList.jsp");
                    RequestDispatcher disp = request.getRequestDispatcher("/commonPage/MsgGoHref.jsp");
                    disp.forward(request, response);
                }
            }
        }

    }
}
