<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 4:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>회원가입</title>
</head>
<body>
    <div>
        <div>
            <form method="post" action="/minihome/SignUp">
                <div>
                    <div>아이디</div>
                    <div><input type="text" name="userId"></div>
                </div>
                <div>
                    <div>비밀번호</div>
                    <div><input type="password" name="userPassword"></div>
                </div>
                <div>
                    <div>비밀번호 확인</div>
                    <div><input type="password" name="userPasswordConfirm"></div>
                </div>
                <div>
                    <div>이름</div>
                    <div><input type="text" name="userName"></div>
                </div>
                <div>
                    <div>성별</div>
                    <div><input type="radio" name="userGender" value="M">남 <input type="radio" name="userGender" value="F">여</div>
                </div>
                <div>
                    <div>폰번호</div>
                    <div><input type="text" name="userPhone" placeholder="-는 빼고 적어주세요"></div>
                </div>
                <div>
                    <div><input type="submit" value="회원가입"></div>
                    <div><input type="reset" value="초기화"></div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
