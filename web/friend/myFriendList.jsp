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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                        <c:if test="${requestScope.myFriendList ne null}">
                            <tbody>
                            <c:forEach var="myFriend" items="${requestScope.myFriendList}">
                                <tr>
                                    <td>
                                        <a href="/minihome/ChangeLoc?userId=${myFriend.userId}">${myFriend.userId}
                                    </td>
                                    <td>
                                        ${myFriend.userName}
                                    </td>
                                    <td>
                                        ${myFriend.userGender}
                                    </td>
                                    <td>
                                        <button onclick="deleteFriend('${sessionScope.id}','${myFriend.userId}')" class="btn btn-mini btn-theme">친구삭제</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </c:if>
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
                        <c:if test="${requestScope.requestFriendList ne null}">
                            <tbody>
                            <c:forEach var="requestFriend" items="${requestScope.requestFriendList}">
                                <tr>
                                    <td>
                                        ${requestFriend.userId}
                                    </td>
                                    <td>
                                        ${requestFriend.userName}
                                    </td>
                                    <td>
                                        ${requestFriend.userGender}
                                    </td>
                                    <td>
                                        <button onclick="responseAddFriend('/minihome/ResponseAddFriend?friendId=${requestFriend.userId}&res=t')" class="btn btn-mini btn-theme">수락</button>
                                        <button onclick="responseAddFriend('/minihome/ResponseAddFriend?friendId=${requestFriend.userId}&res=f')" class="btn btn-mini btn-theme">거절</button>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </c:if>
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
