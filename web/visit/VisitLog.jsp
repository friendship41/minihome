<%@ page import="com.jsp.minihome.visit.vo.VisitVO" %><%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, com.jsp.minihome.visit.vo.VisitVO" %>
<% List<VisitVO> list = (List<VisitVO>)request.getAttribute("visitList");
String nowLocId = (String) session.getAttribute("nowLocId");
String id = (String) session.getAttribute("id"); %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>방명록</title>
    <%@ include file="../include/headLink.jsp" %>
    <script>
        function goHome() {
            location.href="/minihome";
        }
        function deleteVisit(no) {
            var loc = "/minihome/DeleteVisit?visitNo="+no;
            location.href=loc;
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
                            <h2><%=nowLocId%>님의 방명록</h2>
                        </div>
                    </div>
                    <div class="span8">
                        <ul class="breadcrumb">
                            <li><a href="/minihome"><i class="icon-home"></i></a><i class="icon-angle-right"></i></li>
                            <li class="active">방명록</li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>


        <section id="content">
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d22864.11283411948!2d-73.96468908098944!3d40.630720240038435!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew+York%2C+NY%2C+USA!5e0!3m2!1sen!2sbg!4v1540447494452" width="100%" height="380" frameborder="0" style="border:0" allowfullscreen></iframe>


            <div class="container">
                <div class="row">
                    <div class="span12">
                        <h4>친구에게 방명록을 적어주세요 <strong>답장은 기대하지 마시구요</strong></h4>

                        <form method="post" action="/minihome/Visit" role="form" class="contactForm">
                            <div id="sendmessage">ㅎㅎㅎㅎㅎ</div>
                            <div id="errormessage">ㅡㅡ...</div>

                            <div class="row">
<%--                                <div class="span4 form-group">--%>
<%--                                    <input type="text" name="name" class="form-control" id="name" placeholder="Your Name" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />--%>
<%--                                    <div class="validation"></div>--%>
<%--                                </div>--%>
<%--                                <div class="span4 form-group">--%>
<%--                                    <input type="email" class="form-control" name="email" id="email" placeholder="Your Email" data-rule="email" data-msg="Please enter a valid email" />--%>
<%--                                    <div class="validation"></div>--%>
<%--                                </div>--%>
<%--                                <div class="span4 form-group">--%>
<%--                                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Subject" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />--%>
<%--                                    <div class="validation"></div>--%>
<%--                                </div>--%>
                                <div class="span12 margintop10 form-group">
                                    <textarea class="form-control" name="contents" rows="12" data-rule="required" data-msg="Please write something" placeholder="내용" required></textarea>
                                    <div class="validation"></div>
                                    <p class="text-center">
                                        <button class="btn btn-large btn-theme margintop10" type="submit">작 성</button>
                                    </p>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </section>

    </div>


    <div>
        <div>
        </div>

        <% for(VisitVO visitVO : list)
        {%>
        <div>
            <div>이름: <%= visitVO.getUserName()%></div>
            <div>작성일자: <%= visitVO.getWriteDate()%></div>
            <div>내용: <%= visitVO.getContents()%></div>
            <% if(id.equals(visitVO.getWriterId())) {%>
                <div><button onclick="deleteVisit('<%=visitVO.getVisitNo()%>')">삭제</button></div>
            <%}%>
        </div><br><br>
        <% }%>
    </div>



    <%@ include file="../include/loadJS.jsp" %>
</body>
</html>
