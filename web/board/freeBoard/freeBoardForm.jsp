<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2020-01-09
  Time: 오후 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>글쓰기</title>
    <%@ include file="../../include/headLink.jsp" %>
    <script type="text/javascript">
        function clickCategory(cateNum) {
            if('${requestScope.myInfo.userClass}' === 'A')
            {
                if(cateNum === 1)
                {
                    clearAllCategory();
                    $("#cate1").attr('class','active');
                    $("#cateVal").attr('value','공지');
                }
                else if(cateNum === 2)
                {
                    clearAllCategory();
                    $("#cate2").attr('class','active');
                    $("#cateVal").attr('value','잡담');
                }
                else if(cateNum === 3)
                {
                    clearAllCategory();
                    $("#cate3").attr('class','active');
                    $("#cateVal").attr('value','토론');
                }
                else if(cateNum === 4)
                {
                    clearAllCategory();
                    $("#cate4").attr('class','active');
                    $("#cateVal").attr('value','질문');
                }
                else if(cateNum === 5)
                {
                    clearAllCategory();
                    $("#cate5").attr('class','active');
                    $("#cateVal").attr('value','짤');
                }
            }
            else
            {
                if(cateNum === 2)
                {
                    clearAllCategory();
                    $("#cate2").attr('class','active');
                    $("#cateVal").attr('value','잡담');
                }
                else if(cateNum === 3)
                {
                    clearAllCategory();
                    $("#cate3").attr('class','active');
                    $("#cateVal").attr('value','토론');
                }
                else if(cateNum === 4)
                {
                    clearAllCategory();
                    $("#cate4").attr('class','active');
                    $("#cateVal").attr('value','질문');
                }
                else if(cateNum === 5)
                {
                    clearAllCategory();
                    $("#cate5").attr('class','active');
                    $("#cateVal").attr('value','짤');
                }
            }



        }
        function clearAllCategory() {
            if('${requestScope.myInfo.userClass}' === 'A')
            {
                $("#cate1").removeAttr('class');
                $("#cate6").removeAttr('class');
            }
            $("#cate2").removeAttr('class');
            $("#cate3").removeAttr('class');
            $("#cate4").removeAttr('class');
            $("#cate5").removeAttr('class');

        }
        function setIp() {
            console.log(ip());
            var myip = ip();
            $("#ip").attr('value',myip);
        }
    </script>
</head>
<body onload="setIp()">
<div id="wrapper">
    <%@ include file="../../include/wrapperToHeader.jsp" %>

    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="span4">
                    <div class="inner-heading">
                        <h2>글쓰기</h2>
                    </div>
                </div>
                <div class="span8">
                    <ul class="breadcrumb">
                        <li><a href="/minihome"><i class="icon-home"></i></a><i class="icon-angle-right"></i></li>
                        <li><a href="/minihome/FreeBoardList">Board</a><i class="icon-angle-right"></i></li>
                        <li><a href="/minihome/FreeBoardList">자유게시판</a><i class="icon-angle-right"></i></li>
                        <li class="active">글쓰기</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>


    <section id="content">
        <div class="container">
            <div class="row">
                <div class="span12">
                    <h4><strong>자유 게시판</strong></h4>
                    <form action="/minihome/NewFreeBoard" method="post" role="form" class="contactForm">
                        <div id="sendmessage">Your message has been sent. Thank you!</div>
                        <div id="errormessage"></div>

                        <div class="row">

                            <input type="hidden" value="freeBoard" name="whereBoard" id="whereBoard">
                            <input type="hidden" value="${sessionScope.nowLocId}" name="whoseBoard" id="whoseBoard">
                            <input type="hidden" name="ip" id="ip">
                            <c:if test="${requestScope.myInfo.userClass eq 'A'}">
                                <input type="hidden" name="cateVal" id="cateVal" value="공지">
                            </c:if>
                            <c:if test="${requestScope.myInfo.userClass ne 'A'}">
                                <input type="hidden" name="cateVal" id="cateVal" value="잡담">
                            </c:if>
                            <div class="span12 form-group pagination">
                                    <ul>
                                        <c:if test="${requestScope.myInfo.userClass eq 'A'}">
                                            <li class="active" id="cate1"><span onclick="clickCategory(1)">공지</span></li>
                                            <li id="cate2"><span onclick="clickCategory(2)">잡담</span></li>
                                        </c:if>
                                        <c:if test="${requestScope.myInfo.userClass ne 'A'}">
                                            <li class="disabled" id="cate1"><span onclick="clickCategory(1)">공지</span></li>
                                            <li class="active" id="cate2"><span onclick="clickCategory(2)">잡담</span></li>
                                        </c:if>
                                        <li id="cate3"><span onclick="clickCategory(3)">토론</span></li>
                                        <li id="cate4"><span onclick="clickCategory(4)">질문</span></li>
                                        <li id="cate5"><span onclick="clickCategory(5)">짤</span></li>
                                        <li class="disabled" id="cate6"><span onclick="clickCategory(6)">답변</span></li>
                                    </ul>
                            </div>

                            <div class="span4 form-group margintop10">
                                <input type="text" name="userId" class="form-control" id="userId" data-rule="minlen:4" readonly value="${sessionScope.id}"/>
                                <div class="validation"></div>
                            </div>
                            <div class="span4 form-group margintop10">
                                <input type="text" name="userName" class="form-control" id="userName" data-rule="minlen:4" readonly value="${requestScope.myInfo.userName}"/>
                                <div class="validation"></div>
                            </div>
                            <div class="span4 form-group margintop10">
                                <input type="text" name="userEmail" class="form-control" id="userEmail" data-rule="minlen:4" readonly value="${requestScope.myInfo.userEmail}"/>
                                <div class="validation"></div>
                            </div>
                            <div class="span12 form-group margintop10">
                                <input type="text" name="subject" class="form-control" id="subject" data-rule="minlen:4" placeholder="제목" required="true"/>
                                <div class="validation"></div>
                            </div>
                            <div class="span12 margintop10 form-group">
                                <textarea class="form-control" name="content" id="contentText" rows="12" data-rule="required" data-msg="Please write something" placeholder="내용" required="true"></textarea>
                                <div class="validation"></div>
                                <p class="text-center">
                                    <button class="btn btn-large btn-theme margintop10" type="submit">작성</button>
                                </p>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>

</div>

<%@ include file="../../include/footer.jsp" %>
<%@ include file="../../include/loadJS.jsp" %>
<script type="text/javascript" src="https://jsgetip.appspot.com"></script>

</body>
</html>
