<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String id = (String)session.getAttribute("id"); %>
<html>
<head>
    <title>로그인 페이지</title>
    <script>
        <% if(id != null && !id.equals(""))
        {%>
        alert("잘못된 접근입니다.");
        history.back();
        <% }%>
    </script>
</head>
<body>

    <div>
        <div>
            <form method="post" action="/minihome/Login">
                <div>
                    <div>아이디</div>
                    <div><input type="text" name="id"></div>
                </div>
                <div>
                    <div>비밀번호</div>
                    <div><input type="password" name="password"></div>
                </div>
                <div>
                    <div><input type="submit" value="로그인"></div>
                    <div><a href="SignUpForm.jsp">회원가입</a></div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
