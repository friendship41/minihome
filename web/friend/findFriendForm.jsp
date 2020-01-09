<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-27
  Time: 오전 10:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.jsp.minihome.member.vo.MemberVO, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<% List<MemberVO> friendList = (List<MemberVO>) request.getAttribute("friendList"); %>--%>
<%--<% String success = (String) request.getAttribute("success");%>--%>
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


    <section id="content">
        <div class="container">

            <div class="row">
                <div class="span6">
                    <h4>친구 검색</h4>

                    <form class="form-search" method="get" action="/minihome/FindFriend">
                        <input placeholder="아이디 또는 이름을 입력해주세요" type="text" class="input-medium search-query"
                               name="friendId">
                        <button type="submit" class="btn btn-square btn-theme">검색</button>
                    </form>

                </div>

                <c:if test="${requestScope.friendList ne null}">
                    <div class="span6">
                        <h4>검색 결과</h4>
                        <c:choose>
                            <c:when test="${requestScope.success eq 'f'}">
                                <h3><strong>일치하는 정보가 없습니다.</strong></h3>
                            </c:when>
                            <c:otherwise>
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
                                    <tbody>
                                    <c:forEach var="friend" items="${requestScope.friendList}">
                                        <tr>
                                            <td>
                                                ${friend.userId}
                                            </td>
                                            <td>
                                                ${friend.userName}
                                            </td>
                                            <td>
                                                ${friend.userGender}
                                            </td>
                                            <td>
                                                <button onclick="location.href='/minihome/AddFriend?friendId=${friend.userId}'" class="btn btn-mini btn-theme">친구요청</button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </c:if>
            </div>

        </div>
    </section>


</div>

<%@ include file="../include/footer.jsp" %>
<%@ include file="../include/loadJS.jsp" %>
</body>
</html>
