<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오전 11:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String id = (String)session.getAttribute("id"); %>
<html>
  <head>
    <title>메인 페이지</title>
  </head>
  <body>
  <% if(id == null || id.equals(""))
  {%>
  <a href="member/Login.jsp">로그인</a>
  <% }
  else{ %>
  <a href="/minihome/Logout">로그아웃</a>
  <a href="/minihome/Visit">방명록</a>
  <% } %>
  </body>
</html>
