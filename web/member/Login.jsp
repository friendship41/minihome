<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>로그인 페이지</title>
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
                    <div><a href="#">회원가입</a></div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
