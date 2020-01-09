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
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>친구 찾기</title>
    <%@ include file="../include/headLink.jsp" %>

    <script>

        function findFriendAjax() {
            var findId = $("#tfFindId").val();
            $.ajax({
                url: "/minihome/FindFriend",
                type: "GET",
                data: {friendId:findId},
                dataType: "json"
            })
                .done(function(json) {

                    resetFindFriendTable();
                    // console.log("jsonLength: "+json.length);

                    if(json.length === 0)
                    {
                        $("#searchResultDiv").append("<h3><strong>일치하는 정보가 없습니다.</strong></h3>");
                    }
                    else
                    {
                        $("#searchResultDiv").append('<h4>검색 결과</h4>\n' +
                            '                                <table class="table table-hover">\n' +
                            '                                    <thead>\n' +
                            '                                    <tr>\n' +
                            '                                        <th>\n' +
                            '                                            아이디\n' +
                            '                                        </th>\n' +
                            '                                        <th>\n' +
                            '                                            이름\n' +
                            '                                        </th>\n' +
                            '                                        <th>\n' +
                            '                                            성별\n' +
                            '                                        </th>\n' +
                            '                                        <th>\n' +
                            '                                            -\n' +
                            '                                        </th>\n' +
                            '                                    </tr>\n' +
                            '                                    </thead>\n' +
                            '                                    <tbody id="findTableBody">');
                        json.forEach(function(user) {
                            // console.log(user);
                            $("#findTableBody").append('<tr>\n' +
                                '                                            <td>\n' +
                                user.userId +
                                '                                            </td>\n' +
                                '                                            <td>\n' +
                                user.userName +
                                '                                            </td>\n' +
                                '                                            <td>\n' +
                                user.userGender +
                                '                                            </td>\n' +
                                '                                            <td>\n' +
                                '                                                <button onclick="location.href=\'/minihome/AddFriend?friendId='+user.userId+'\'" class="btn btn-mini btn-theme">친구요청</button>\n' +
                                '                                            </td>\n' +
                                '                                        </tr>');
                        })

                        console.log($("#searchResultDiv").html());
                    }

                })
                .fail(function (xhr, status, errorThrown) {
                    alert(errorThrown);
                })
        }

        function resetFindFriendTable() {
            $("#searchResultDiv").empty();
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

                    <div class="form-search" method="get" action="/minihome/FindFriend">
                        <input placeholder="아이디 또는 이름을 입력해주세요" type="text" class="input-medium search-query"
                               name="friendId" id="tfFindId">
                        <button class="btn btn-square btn-theme" onclick="findFriendAjax()">검색</button>
                    </div>

                </div>

                    <div class="span6" id="searchResultDiv">

                    </div>
            </div>

        </div>
    </section>


</div>

<%@ include file="../include/footer.jsp" %>
<%@ include file="../include/loadJS.jsp" %>

<script src="//cdnjs.cloudflare.com/ajax/libs/json3/3.3.2/json3.min.js"></script>

</body>
</html>
