<%@ page import="com.jsp.minihome.visit.vo.VisitVO" %><%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.jsp.minihome.visit.vo.VisitVO" %>
<% List<VisitVO> list = (List<VisitVO>)request.getAttribute("visitList");%>
<html>
<head>
    <title>방명록</title>
</head>
<body>
    <div>
        <form method="post" action="/minihome/Visit">
            <div>
                <div>내용</div>
                <div><textarea name="contents"></textarea></div>
                <div><input type="submit" value="작성"></div>
            </div>
        </form>
        <br><br>
        <% for(VisitVO visitVO : list)
        {%>
        <div>
            <div>이름: <%= visitVO.getUserName()%></div>
            <div>작성일자: <%= visitVO.getWriteDate()%></div>
            <div>내용: <%= visitVO.getContents()%></div>
        </div><br><br>
        <% }%>
    </div>
</body>
</html>
