<%@ page import="com.jsp.minihome.visit.vo.VisitVO" %><%--
  Created by IntelliJ IDEA.
  User: stage41
  Date: 2019-12-26
  Time: 오후 5:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>방명록</title>
    <%@ include file="../include/headLink.jsp" %>
    <script type="text/javascript">

        function getToServer() {
            $.ajax({
                url: "/minihome/GetVisitList",
                data: {},
                method: "GET",
                dataType: "json"
            })
                .done(function(json) {
                    console.log(json);
                    getList(json);
                })
                .fail(function (xhr, status, errorThrown) {
                    $("#visitList").append(errorThrown);
                    $("#visitList").append(status);
                })
        }

        function deleteVisitAjaxRequest(url) {
            $.ajax({
                url: url,
                data: {},
                method: "GET",
                dataType: "json"
            })
                .done(function(json) {
                    console.log(json);
                    getList(json);
                })
                .fail(function (xhr, status, errorThrown) {
                    alert(errorThrown);
                })
        }

        function writeVisit() {
            var temp = $("textarea#inputContents").val();
            $.ajax({
                url: "/minihome/Visit",
                type: "POST",
                data: { contents:temp},
                dataType: "json"
            })
                .done(function(json) {
                    console.log(json);
                    $("textarea#inputContents").val('');
                    getList(json);
                })
                .fail(function (xhr, status, errorThrown) {
                    alert(errorThrown);
                })
        }


        function deleteVisit(no) {
            var loc = "/minihome/DeleteVisit?visitNo=" + no;
            deleteVisitAjaxRequest(loc);
        }

        function getList(json){
            // console.log(json.length);

            resetVisitList();

            for(var i=0; i<json.length; i++)
            {

                var tempVisit = json[i];
                // console.log("i: "+i);
                i *= 1;


                if(i%2 === 0 && i===json.length-1)
                {
                    var htmlv = '<div class="row">' +
                        '                        <div class="span6">\n' +
                        '                            <div class="wrapper">\n' +
                        '                                <div class="testimonial">\n' +
                        '                                    <p class="text">\n' +
                        '                                        <i class="icon-quote-left icon-48"></i>'+ tempVisit.contents +
                        '                                    </p>\n' +
                        '                                    <div class="author">\n' +
                        '                                        <img src="../img/dummies/testimonial-author1.png" class="img-circle bordered"\n' +
                        '                                             alt=""/>\n' +
                        '                                        <p class="name">\n' +
                        tempVisit.userName +
                        '                                        </p>\n' +
                        '                                        <span class="info" id="'+tempVisit.visitNo+'_visitBtnLoc">'+tempVisit.writeDate+'&nbsp;&nbsp;&nbsp;\n' +
                        '                                        </span>\n' +
                        '                                    </div>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </div>\n';
                    appendVisitBody(htmlv);
                    if(tempVisit.writerId == '${sessionScope.id}')
                    {
                        addDeleteBtn(tempVisit.visitNo);
                    }
                }
                else if((i%2) === 0 && i!=json.length-1)
                {
                    var htmlv = '<div class="row">' +
                        '                        <div class="span6">\n' +
                        '                            <div class="wrapper">\n' +
                        '                                <div class="testimonial">\n' +
                        '                                    <p class="text">\n' +
                        '                                        <i class="icon-quote-left icon-48"></i>'+ tempVisit.contents +
                        '                                    </p>\n' +
                        '                                    <div class="author">\n' +
                        '                                        <img src="../img/dummies/testimonial-author1.png" class="img-circle bordered"\n' +
                        '                                             alt=""/>\n' +
                        '                                        <p class="name">\n' +
                        tempVisit.userName +
                        '                                        </p>\n' +
                        '                                        <span class="info" id="'+tempVisit.visitNo+'_visitBtnLoc">'+tempVisit.writeDate+'&nbsp;&nbsp;&nbsp;\n' +
                        '                                        </span>\n' +
                        '                                    </div>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                        <div class="span6">\n' +
                        '                            <div class="wrapper">\n' +
                        '                                <div class="testimonial">\n' +
                        '                                    <p class="text">\n' +
                        '                                        <i class="icon-quote-left icon-48"></i>'+ json[(i+1)].contents +
                        '                                    </p>\n' +
                        '                                    <div class="author">\n' +
                        '                                        <img src="../img/dummies/testimonial-author1.png" class="img-circle bordered"\n' +
                        '                                             alt=""/>\n' +
                        '                                        <p class="name">\n' +
                        json[(i+1)].userName +
                        '                                        </p>\n' +
                        '                                        <span class="info" id="'+json[(i+1)].visitNo+'_visitBtnLoc">'+json[(i+1)].writeDate+'&nbsp;&nbsp;&nbsp;\n' +
                        '                                        </span>\n' +
                        '                                    </div>\n' +
                        '                                </div>\n' +
                        '                            </div>\n' +
                        '                        </div>\n' +
                        '                    </div>\n';
                    appendVisitBody(htmlv);
                    if(tempVisit.writerId == '${sessionScope.id}')
                    {
                        addDeleteBtn(tempVisit.visitNo);
                    }
                    if(json[(i+1)].writerId == '${sessionScope.id}')
                    {
                        addDeleteBtn(json[(i+1)].visitNo);
                    }
                }

            }

            // console.log($("#visitListt").html());
        }

        //<button class="btn btn-mini btn-theme" onclick="deleteVisit('visitNo')">삭 제</button>

        function appendVisitBody(text) {
            $("#visitListt").append(text);
            // console.log(text);
        }

        function addDeleteBtn(no) {
            var temp = "#"+no+"_visitBtnLoc";
            $(temp).append("<button class=\"btn btn-mini btn-theme\" onclick=\"deleteVisit("+no+")\">삭 제</button>");
            // console.log($(temp).html());
        }

        function resetVisitList() {
            $("#visitListt").empty();
            $("#visitListt").append("<h4 class=\"heading\" id=\"visitListTitle\"><strong>${sessionScope.nowLocId}</strong><span>님의 방명록</span></h4>");
        }


    </script>
</head>
<body onload="getToServer()">
<div id="wrapper">
    <%@ include file="../include/wrapperToHeader.jsp" %>


    <section id="inner-headline">
        <div class="container">
            <div class="row">
                <div class="span4">
                    <div class="inner-heading">
                        <h2>${sessionScope.nowLocId}님의 방명록</h2>
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
        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d22864.11283411948!2d-73.96468908098944!3d40.630720240038435!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c24fa5d33f083b%3A0xc80b8f06e177fe62!2sNew+York%2C+NY%2C+USA!5e0!3m2!1sen!2sbg!4v1540447494452"
                width="100%" height="380" frameborder="0" style="border:0" allowfullscreen></iframe>


        <div class="container">
            <div class="row">
                <div class="span12">
                    <h4>친구에게 방명록을 적어주세요 <strong>답장은 기대하지 마시구요</strong></h4>

                    <div role="form" class="contactForm">
                        <div id="sendmessage">ㅎㅎㅎㅎㅎ</div>
                        <div id="errormessage">ㅡㅡ...</div>

                        <div class="row">
                            <div class="span12 margintop10 form-group">
                                <textarea class="form-control" id="inputContents" name="contents" rows="12" data-rule="required"
                                          data-msg="Please write something" placeholder="내용" required></textarea>
                                <p class="text-center">
                                    <button class="btn btn-large btn-theme margintop10" onclick="writeVisit()">작 성</button>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </section>


    <section id="content">
        <div class="container">
            <div class="row marginbot30">
                <div class="span12" id="visitListt">
                    <h4 class="heading" id="visitListTitle"><strong>${sessionScope.nowLocId}</strong><span>님의 방명록</span></h4>

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
