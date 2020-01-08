<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2020-01-07
  Time: 오후 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String id = (String) session.getAttribute("id");
    String nowLocId = (String) session.getAttribute("nowLocId"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Title</title>
    <%@ include file="../include/headLink.jsp" %>
</head>
<body>
<div id="wrapper">
    <%@ include file="../include/wrapperToHeader.jsp" %>


<%@ include file="../include/footer.jsp" %>
<%@ include file="../include/loadJS.jsp" %>
</body>
</html>
