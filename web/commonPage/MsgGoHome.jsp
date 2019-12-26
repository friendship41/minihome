<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 4:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String mssg = (String)request.getAttribute("msg");%>
<html>
<head>
    <title><%= mssg%></title>
    <script>
        function alertMessage(msg) {
            alert(msg);
            location.href="/minihome";
        }
        alertMessage("<%= mssg%>");
    </script>
</head>
<body>

</body>
</html>
