<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-27
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jsp.minihome.member.vo.MemberVO, java.util.List" %>
<% List<MemberVO> friendList = (List<MemberVO>) request.getAttribute("friendList"); %>
<% String success = (String) request.getAttribute("success");%>
<html>
<head>
    <title>친구 찾기</title>
</head>
<body>
    <div>
        <div>
            <form method="get" action="/minihome/FindFriend">
                <div>아이디 또는 이름: <input type="text" name="friendId" placeholder="아이디나 이름을 입력해 주세요"></div>
                <div><input type="submit" value="찾기"></div>
            </form>
        </div>
        <% if(friendList != null) {%>
        <div>
            <% if(success.equals("f")){%>
            <div>일치하는 정보가 없습니다.</div>
            <% }
            else{%>
                <div>검색 결과</div>
                <% for(MemberVO friend : friendList){
                    String addFriendLink = "/minihome/AddFriend?friendId="+friend.getUserId();%>
                    <div><a href="<%=addFriendLink%>">아이디: <%=friend.getUserId()%>, 이름: <%=friend.getUserName()%>, 성별: <%=friend.getUserGender()%></a></div>
                <% }
            } %>
        </div>
        <% } %>
    </div>
</body>
</html>
