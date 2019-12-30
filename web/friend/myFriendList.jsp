<%@ page import="java.util.List" %>
<%@ page import="com.jsp.minihome.member.vo.MemberVO" %>
<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-27
  Time: 오후 2:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<MemberVO> requestFriendList = (List<MemberVO>) request.getAttribute("requestFriendList"); %>
<html>
<head>
    <title>친구 목록</title>
    <script>
        function goHome() {
            location.href="/minihome";
        }
        function goFindFriend(){
            location.href="findFriendForm.jsp";
        }
        function responseAddFriend(how) {
            location.href=how;
        }
    </script>
</head>
<body>
    <div>
        <div>
            <button onclick="goHome()">홈으로</button>
            <button onclick="goFindFriend()">친구추가</button>
        </div>

        <div>

        </div>

        <% if(requestFriendList != null){ %>
        <div>
            <div>친구 요청 목록</div>
            <% for(MemberVO requestFriend : requestFriendList){
                String accept = "/minihome/ResponseAddFriend?friendId="+requestFriend.getUserId()+"&res=t";
                String dis = "/minihome/ResponseAddFriend?friendId="+requestFriend.getUserId()+"&res=f";%>
                <div>아이디: <%=requestFriend.getUserId()%>, 이름: <%=requestFriend.getUserName()%>, 성별: <%=requestFriend.getUserGender()%>&nbsp&nbsp<button onclick="responseAddFriend('<%=accept%>')">수락</button><button onclick="responseAddFriend('<%=dis%>')">거절</button></div>
            <% } %>
        </div>
        <% } %>
    </div>
</body>
</html>
