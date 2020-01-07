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
<% List<MemberVO> requestFriendList = (List<MemberVO>) request.getAttribute("requestFriendList");
    List<MemberVO> myFriendList = (List<MemberVO>) request.getAttribute("myFriendList");
    String nowLocId = (String) session.getAttribute("nowLocId");
    String id = (String) session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>친구 목록</title>
    <%@ include file="../include/headLink.jsp" %>
    <script>
        function goFindFriend() {
            location.href = "friend/findFriendForm.jsp";
        }

        function responseAddFriend(how) {
            location.href = how;
        }

        function deleteFriend(myId, friendId) {
            var a = confirm("정말로 삭제하겠습니까?");
            if (a) {
                var dloc1 = "/minihome/DeleteFriend?userId=" + myId;
                var dloc2 = dloc1 + "&friendId=" + friendId;
                location.href = dloc2;
            }
        }
    </script>
</head>
<body>
<div id="wrapper">
    <%@ include file="../include/wrapperToHeader.jsp" %>


    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="span4">
                    <div class="inner-heading">
                        <h2>친구목록</h2>
                    </div>
                </div>
                <div class="span8">
                    <ul class="breadcrumb">
                        <li><a href="/minihome"><i class="icon-home"></i></a><i class="icon-angle-right"></i></li>
                        <li><a href="/minihome/FriendList">Friend</a><i class="icon-angle-right"></i></li>
                        <li class="active">친구목록</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>

    <section id="content">
        <div class="container">

            <div class="row">
                <div class="span6">
                    <h4>친구 목록       <button onclick="goFindFriend()" class="btn btn-mini btn-theme">친구추가</button></h4>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                아이디
                            </th>
                            <th>
                                이름
                            </th>
                            <th>
                                성별
                            </th>
                            <th>
                                -
                            </th>
                        </tr>
                        </thead>
                        <% if (myFriendList != null)
                        {%>
                        <tbody>
                        <% for (MemberVO myFriend : myFriendList)
                        {
                            String loc = "/minihome/ChangeLoc?userId=" + myFriend.getUserId();%>
                        <tr>
                            <td>
                                <a href="<%=loc%>"><%=myFriend.getUserId()%>
                            </td>
                            <td>
                                <%=myFriend.getUserName()%>
                            </td>
                            <td>
                                <%=myFriend.getUserGender()%>
                            </td>
                            <td>
                                <button onclick="deleteFriend('<%=id%>','<%=myFriend.getUserId()%>')" class="btn btn-mini btn-theme">친구삭제</button>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                        <% } %>
                    </table>
                </div>
                <div class="span6">
                    <h4>친구요청 목록</h4>
                    <table class="table table-hover">
                        <thead>
                        <tr>
                            <th>
                                아이디
                            </th>
                            <th>
                                이름
                            </th>
                            <th>
                                성별
                            </th>
                            <th>
                                -
                            </th>
                        </tr>
                        </thead>
                        <% if (requestFriendList != null)
                        { %>
                        <tbody>
                        <% for (MemberVO requestFriend : requestFriendList)
                        {
                            String accept = "/minihome/ResponseAddFriend?friendId=" + requestFriend.getUserId() + "&res=t";
                            String dis = "/minihome/ResponseAddFriend?friendId=" + requestFriend.getUserId() + "&res=f";%>
                        <tr>
                            <td>
                                <%=requestFriend.getUserId()%>
                            </td>
                            <td>
                                <%=requestFriend.getUserName()%>
                            </td>
                            <td>
                                <%=requestFriend.getUserGender()%>
                            </td>
                            <td>
                                <button onclick="responseAddFriend('<%=accept%>')" class="btn btn-mini btn-theme">수락</button>
                                <button onclick="responseAddFriend('<%=dis%>')" class="btn btn-mini btn-theme">거절</button>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                        <% } %>
                    </table>
                </div>


            </div>

        </div>
    </section>


</div>

<%@ include file="../include/footer.jsp" %>
<%@ include file="../include/loadJS.jsp" %>
</body>
</html>
