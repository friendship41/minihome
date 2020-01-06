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
String myId = (String) session.getAttribute("id"); %>
<html>
<head>
    <title>방명록</title>
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
    <div>
        <div>
            <button onclick="goHome()">홈으로</button>
        </div>

        <form method="post" action="/minihome/Visit">
            <div>
                <div>내용</div>
                <div><textarea name="contents"></textarea></div>
                <div><input type="submit" value="작성"></div>
            </div>
        </form>
        <br><br>
        <% for(VisitVO visitVO : list)
        {%>
        <div>
            <div>이름: <%= visitVO.getUserName()%></div>
            <div>작성일자: <%= visitVO.getWriteDate()%></div>
            <div>내용: <%= visitVO.getContents()%></div>
            <% if(myId.equals(visitVO.getWriterId())) {%>
                <div><button onclick="deleteVisit('<%=visitVO.getVisitNo()%>')">삭제</button></div>
            <%}%>
        </div><br><br>
        <% }%>
    </div>
</body>
</html>
