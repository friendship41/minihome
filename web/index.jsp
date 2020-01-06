<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오전 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String id = (String) session.getAttribute("id");
String nowLocId = (String) session.getAttribute("nowLocId"); %>
<html>
  <head>
    <title>메인 페이지</title>
  </head>
  <body>
  <% if(nowLocId != null && !nowLocId.equals("")) {%>
  <h1>현재 위치는 <b><%=nowLocId%></b>님의 미니홈피입니다.</h1>
  <%}
    if(id == null || id.equals(""))
  {%>
  <a href="member/Login.jsp">로그인</a>
  <% }
  else{ %>
  <a href="/minihome/Logout">로그아웃</a>
  <a href="/minihome/FriendList">친구목록</a>
  <a href="/minihome/friend/findFriendForm.jsp">친구추가</a>
  <a href="/minihome/Visit">방명록</a>
  <% } %>
  </body>
</html>
