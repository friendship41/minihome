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
<% String success = (String) request.getAttribute("success");
    String id = (String) session.getAttribute("id");
    String nowLocId = (String) session.getAttribute("nowLocId"); %>
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

                <% if (friendList != null)
                {%>
                <div class="span6">
                    <h4>검색 결과</h4>
                    <% if (success.equals("f"))
                    {%>
                    <h3><strong>일치하는 정보가 없습니다.</strong></h3>
                    <% }
                    else
                    {%>
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
                            <% for (MemberVO friend : friendList)
                            {
                                String addFriendLink = "/minihome/AddFriend?friendId=" + friend.getUserId();%>
                        <tr>
                            <td>
                                <%=friend.getUserId()%>
                            </td>
                            <td>
                                <%=friend.getUserName()%>
                            </td>
                            <td>
                                <%=friend.getUserGender()%>
                            </td>
                            <td>
                                <button onclick="location.href='<%=addFriendLink%>'" class="btn btn-mini btn-theme">친구요청</button>
                            </td>
                        </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <% } %>

                </div>
                <% } %>


            </div>

        </div>
    </section>


</div>


<%--<div>--%>

<%--    <div>--%>

<%--        <div>검색 결과</div>--%>

<%--            %>--%>
<%--        <div><a href="<%=addFriendLink%>">아이디: , 이름: ,--%>
<%--            성별:--%>
<%--        </a></div>--%>
<%--    </div>--%>
<%--</div>--%>

<%@ include file="../include/footer.jsp" %>
<%@ include file="../include/loadJS.jsp" %>
</body>
</html>
