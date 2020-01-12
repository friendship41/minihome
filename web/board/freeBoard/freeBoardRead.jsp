<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2020-01-12
  Time: 오전 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>${requestScope.freeBoardVO.subject}</title>
    <%@ include file="../../include/headLink.jsp" %>
    <script>

        function getToServer() {
            $.ajax({
                url: "/minihome/GetComments",
                type: "GET",
                data: {no:${requestScope.freeBoardVO.no}},
                dataType: "json"
            })
                .done(function(json) {
                    console.log(json);
                    setCommentList(json);
                })
                .fail(function (xhr, status, errorThrown) {
                    alert(errorThrown);
                })
        }

        function writeComment() {
            $.ajax({
                url: "/minihome/WriteFreeBoardComment",
                type: "GET",
                data: {
                    no:"${requestScope.freeBoardVO.no}",
                    writerId:"${requestScope.myInfo.userId}",
                    writerName:"${requestScope.myInfo.userName}",
                    commentCon:$("textarea#taComment").val()
                },
                dataType: "json"
            })
                .done(function(json) {
                    $("textarea#taComment").val('');
                    setCommentList(json);
                })
                .fail(function (xhr, status, errorThrown) {
                    alert(errorThrown);
                })
        }

        function deleteComment(commentNum) {
            $.ajax({
                url: "/minihome/DeleteComment",
                type: "GET",
                data: {
                    commentNum:commentNum,
                    no:"${requestScope.freeBoardVO.no}"
                },
                dataType: "json"
            })
                .done(function(json) {
                    console.log(json);
                    setCommentList(json);
                })
                .fail(function (xhr, status, errorThrown) {
                    alert(errorThrown);
                })
        }

        function setCommentList(json) {
            resetComments(json.length);

            for(var i=0; i<json.length; i++)
            {
                var tempComments = json[i];
                var tempId = "commentInside"+tempComments.commentsTableNum;

                var singleComment = '<div class="media">\n' +
                    '                            <a href="#" class="thumbnail pull-left"><img src="img/avatar.png" alt="" /></a>\n' +
                    '                            <div class="media-body">\n' +
                    '                                <div class="media-content" id="'+tempId+'">\n' +
                    '                                    <h6><span>'+tempComments.commentDate+'</span>  '+tempComments.writerName+'</h6>\n' +
                    '                                    <p>\n' +
                                                            tempComments.commentContent+
                    '                                    </p>\n' +
                    '                                </div>\n' +
                    '                            </div>\n' +
                    '                        </div>';

                $("#commentsListTop").append(singleComment);
                if(tempComments.writerId == '${requestScope.myInfo.userId}')
                {
                    $(document.getElementById(tempId)).append("<button class=\"btn btn-mini btn-theme align-right\" onclick=\"deleteComment("+tempComments.commentsTableNum+")\">삭 제</button>");
                    // $("#"+tempId).append("<button class=\"btn btn-mini btn-theme align-right\" onclick=\"deleteComment("+tempComments.commentsTableNum+")\">삭 제</button>");
                }

            }
            console.log($("#commentsListTop").html());

        }
        function resetComments(cnt) {
            $("#commentsListTop").empty();
            $("#commentsListTop").append("<h4>"+cnt+" Comments</h4>");
        }
    </script>
</head>
<body onload="getToServer()">

<div id="wrapper">

    <%@ include file="../../include/wrapperToHeader.jsp" %>

    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="span4">
                    <div class="inner-heading">
                        <h2>자유 게시판</h2>
                    </div>
                </div>
                <div class="span8">
                    <ul class="breadcrumb">
                        <li><a href="/minihome"><i class="icon-home"></i></a><i class="icon-angle-right"></i></li>
                        <li><a href="/minihome/FreeBoardList">Board</a><i class="icon-angle-right"></i></li>
                        <li class="active">자유게시판</li>
                    </ul>
                </div>
            </div>
        </div>
    </section>


    <section id="content">
        <div class="container">
            <div class="row">
                <div class="span4">
                    <aside class="left-sidebar">
                        <div class="widget">
                            <h5 class="widgetheading">Categories</h5>
                            <ul class="cat">
                                <li><i class="icon-angle-right"></i><a href="#">공지</a><span> (20)</span></li>
                                <li><i class="icon-angle-right"></i><a href="#">잡담</a><span> (11)</span></li>
                                <li><i class="icon-angle-right"></i><a href="#">토론</a><span> (9)</span></li>
                                <li><i class="icon-angle-right"></i><a href="#">질문</a><span> (12)</span></li>
                                <li><i class="icon-angle-right"></i><a href="#">짤</a><span> (18)</span></li>
                            </ul>
                        </div>
                        <div class="widget">
                            <h5 class="widgetheading">Latest posts</h5>
                            <ul class="recent">
                                <li>
                                    <img src="img/dummies/blog/65x65/thumb1.jpg" class="pull-left" alt="" />
                                    <h6><a href="#">Lorem ipsum dolor sit</a></h6>
                                    <p>
                                        Mazim alienum appellantur eu cu ullum officiis pro pri
                                    </p>
                                </li>
                                <li>
                                    <a href="#"><img src="img/dummies/blog/65x65/thumb2.jpg" class="pull-left" alt="" /></a>
                                    <h6><a href="#">Maiorum ponderum eum</a></h6>
                                    <p>
                                        Mazim alienum appellantur eu cu ullum officiis pro pri
                                    </p>
                                </li>
                                <li>
                                    <a href="#"><img src="img/dummies/blog/65x65/thumb3.jpg" class="pull-left" alt="" /></a>
                                    <h6><a href="#">Et mei iusto dolorum</a></h6>
                                    <p>
                                        Mazim alienum appellantur eu cu ullum officiis pro pri
                                    </p>
                                </li>
                            </ul>
                        </div>
                    </aside>
                </div>


                <div class="span8">
                    <article>
                        <div class="row">
                            <div class="span8">
                                <div class="post-image">
                                    <div class="post-heading">
                                        <h3><a>${requestScope.freeBoardVO.subject}</a></h3>
                                    </div>
<%--                                    <img src="img/dummies/blog/img1.jpg" alt="" />--%>
                                </div>

                                <div class="row">
                                    <div class="span8">
                                        <div class="solidline">
                                        </div>
                                    </div>
                                </div>

                                <p>
                                    ${requestScope.freeBoardVO.content}
                                </p>
<%--                                <blockquote>--%>
<%--                                    <i class="icon-quote-left"></i> Lorem ipsum dolor sit amet, ei quod constituto qui. Summo labores expetendis ad quo, lorem luptatum et vis. No qui vidisse signiferumque...--%>
<%--                                </blockquote>--%>
<%--                                <p>--%>
<%--                                    Fierent adipisci iracundia est ei, usu timeam persius ea. Usu ea justo malis, pri quando everti electram ei, ex homero omittam salutatus sed. Dicam appetere ne qui, no has scripta appellantur. Mazim alienum appellantur eu cum, cu ullum officiis pro, pri--%>
<%--                                    at eius erat accusamus.--%>
<%--                                </p>--%>
                                <div class="bottom-article">
                                    <ul class="meta-post">
                                        <li><i class="icon-user"></i><a href="#"> Admin</a></li>
                                        <c:if test="${requestScope.freeBoardVO.writerId eq requestScope.myInfo.userId}">
                                            <li><button class="btn btn-mini btn-theme">수정</button></li>
                                            <li><button class="btn btn-mini btn-theme" onclick="location.href='/minihome/DeleteFreeBoard?no=${requestScope.freeBoardVO.no}'">삭제</button></li>
                                        </c:if>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </article>
                    <!-- author info -->
                    <div class="about-author">
                        <a href="#" class="thumbnail align-left"><img src="img/avatar.png" alt="" /></a>
                        <h5><strong><a href="#">${requestScope.writerVO.userName}</a></strong></h5>
                        <p>
                            ${requestScope.writerVO.userStateMessage}
                        </p>
                    </div>
                    <div class="comment-area" id="commentsListTop">

                    </div>


                    <c:if test="${requestScope.myInfo ne null}">
                        <form id="commentform" name="comment-form">

                            <div class="row">
                                <div class="span8">
                                    <h4>댓글을 남겨주세요~</h4>
                                </div>
                                <div class="span4">
                                    <input type="text" placeholder="* Enter your full name" value="${requestScope.myInfo.userName}" readonly/>
                                </div>
                                <div class="span4">
                                    <input type="text" placeholder="* Enter your email address" value="${requestScope.myInfo.userEmail}" readonly/>
                                </div>
                                <div class="span8 margintop10">
                                    <p>
                                        <textarea id="taComment" rows="12" class="input-block-level" placeholder="*Your comment here"></textarea>
                                    </p>
                                </div>
                            </div>
                        </form>
                        <button class="btn btn-theme" onclick="writeComment()">댓글 남기기</button>
                    </c:if>

                </div>


            </div>
        </div>
    </section>



</div>

<%@ include file="../../include/footer.jsp" %>
<%@ include file="../../include/loadJS.jsp" %>
</body>
</html>
