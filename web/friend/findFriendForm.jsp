<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-27
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jsp.minihome.member.vo.MemberVO, java.util.List" %>
<% List<MemberVO> friendList = (List<MemberVO>) request.getAttribute("friendList"); %>
<% String success = (String) request.getAttribute("success");%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>친구 찾기</title>
    <%@ include file="../include/headLink.jsp" %>
</head>
<body>
<div id="wrapper">
    <%@ include file="../include/wrapperToHeader.jsp" %>


    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="span4">
                    <div class="inner-heading">
                        <h2>친구추가</h2>
                    </div>
                </div>
                <div class="span8">
                    <ul class="breadcrumb">
                        <li><a href="/minihome"><i class="icon-home"></i></a><i class="icon-angle-right"></i></li>
                        <li><a href="/minihome/FriendList">Friend</a><i class="icon-angle-right"></i></li>
                        <li class="active">친구추가</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>




</div>



    <div>
        <div>
            <form method="get" action="/minihome/FindFriend">
                <div>아이디 또는 이름: <input type="text" name="friendId" placeholder="아이디나 이름을 입력해 주세요"></div>
                <div><input type="submit" value="찾기"></div>
            </form>
        </div>
        <% if(friendList != null) {%>
            <div>
                <% if(success.equals("f")){%>
                    <div>일치하는 정보가 없습니다.</div>
                <% }
                else{%>
                    <div>검색 결과</div>
                    <% for(MemberVO friend : friendList){
                        String addFriendLink = "/minihome/AddFriend?friendId="+friend.getUserId();%>
                        <div><a href="<%=addFriendLink%>">아이디: <%=friend.getUserId()%>, 이름: <%=friend.getUserName()%>, 성별: <%=friend.getUserGender()%></a></div>
                    <% }
                } %>
            </div>
        <% } %>
    </div>
</body>
</html>
