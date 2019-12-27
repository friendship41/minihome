<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-27
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jsp.minihome.member.vo.MemberVO" %>
<% MemberVO friend = (MemberVO) request.getAttribute("friend"); %>
<html>
<head>
    <title>친구 찾기</title>
</head>
<body>
    <div>
        <div>
            <form method="get" action="#">
                <div>아이디: <input type="text" name="friendId"></div>
                <div><input type="submit" value="찾기"></div>
            </form>
        </div>
        <% if(friend != null) {%>
        <div>
            <div>검색 결과</div>
        </div>
        <% } %>
    </div>
</body>
</html>
