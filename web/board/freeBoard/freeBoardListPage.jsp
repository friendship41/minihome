<%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2020-01-07
  Time: 오후 12:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ page import="com.jsp.minihome.freeBoard.vo.FreeBoardVO, java.util.List" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>자유게시판</title>
    <%@ include file="../../include/headLink.jsp" %>
</head>
<body>
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
                            <form class="form-search">
                                <input placeholder="Type something" type="text" class="input-medium search-query">
                                <button type="submit" class="btn btn-square btn-theme">Search</button>
                            </form>
                        </div>
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
                                        <h3>자유 게시판</h3>
                                    </div>
                                </div>
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>
                                            #
                                        </th>
                                        <th>
                                            카테고리
                                        </th>
                                        <th>
                                            작성자
                                        </th>
                                        <th>
                                            제목
                                        </th>
                                        <th>
                                            작성일
                                        </th>
                                        <th>
                                            조회수
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="arti" items="${requestScope.freeBoardLists}">
                                        <c:choose>
                                            <c:when test="${arti.categories eq '공지'}">
                                                <tr class="error">
                                            </c:when>
                                            <c:when test="${arti.categories eq '답변'}">
                                                <tr class="success">
                                            </c:when>

                                            <c:when test="${arti.categories eq '짤'}">
                                                <tr class="info">
                                            </c:when>
                                            <c:otherwise>
                                                <tr class="warning">
                                            </c:otherwise>
                                        </c:choose>
                                            <td>
                                                    ${arti.no}
                                            </td>
                                            <td>
                                                    ${arti.categories}
                                            </td>
                                            <td>
                                                    ${arti.writerName}
                                            </td>
                                            <td>
                                                    ${arti.subject}
                                            </td>
                                            <td>
                                                    ${arti.regdate}
                                            </td>
                                            <td>
                                                    ${arti.readcnt}
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>

                                <div class="bottom-article">
                                    <button class="btn btn-large btn-theme margintop10 pull-right" onclick="location.href='board/freeBoard/freeBoardForm.jsp'">글쓰기</button>
                                </div>

                            </div>
                        </div>
                    </article>
                    <div id="pagination">
                        <span class="all">Page 1 of 3</span>
                        <span class="current">1</span>
                        <a href="#" class="inactive">2</a>
                        <a href="#" class="inactive">3</a>
                    </div>
                </div>
            </div>
        </div>
    </section>


</div>

<%@ include file="../../include/footer.jsp" %>
<%@ include file="../../include/loadJS.jsp" %>
</body>
</html>
