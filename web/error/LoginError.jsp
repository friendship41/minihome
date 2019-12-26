<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 3:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String mssg = (String)request.getAttribute("msg");%>
<html>
<head>
    <title>로그인 에러</title>
    <script>
        function alertMessage(msg) {
            alert(msg);
            history.back();
        }
        alertMessage("<%= mssg%>");
    </script>
</head>

</body>
</html>
