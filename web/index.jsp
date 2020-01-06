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
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>메인 페이지</title>
    <%@ include file="include/headLink.jsp" %>
</head>
<body>
<div id="wrapper">
    <%@ include file="include/wrapperToHeader.jsp" %>

</div>




<%@ include file="include/loadJS.jsp" %>
</body>
</html>
