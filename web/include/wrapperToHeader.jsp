<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="hidden-top">
    <div class="hidden-top-inner container">
        <div class="row">
            <div class="span12">
                <ul>
                    <li>어서오세여 <strong>${sessionScope.id}</strong>님!!</li>
                    <c:if test="${sessionScope.nowLocId ne null}">
                        <li>현재 위치는 <strong>${sessionScope.nowLocId}</strong> 님의 미니홈피입니다.</li>
                    </c:if>
                </ul>
            </div>
        </div>
    </div>
</div>

<header>
    <div class="container">
        <div id="header-hidden-link">
            <a href="#" class="toggle-link" title="환영인사" data-target=".hidden-top"><i></i>welcome</a>
        </div>

        <div class="row nomargin">
            <div class="span12">
                <div class="headnav">
                    <ul>
                        <c:choose>
                            <c:when test="${sessionScope.id eq null}">
                                <li><a href="#mySignin" data-toggle="modal"><i class="icon-user"></i> 로그인</a></li>
                                <li><a href="#mySignup" data-toggle="modal">회원가입</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="/minihome/Logout" data-toggle="modal"><i class="icon-user"></i> 로그아웃</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>

                <!-- Sign in Modal -->
                <div id="mySignin" class="modal styled hide fade" tabindex="-1" role="dialog"
                     aria-labelledby="mySigninModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 id="mySigninModalLabel"><strong>로그인</strong></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" method="post" action="/minihome/Login">
                            <div class="control-group">
                                <label class="control-label" for="inputText">Username</label>
                                <div class="controls">
                                    <input type="text" id="inputText" placeholder="Username" name="id">
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="inputSigninPassword">Password</label>
                                <div class="controls">
                                    <input type="password" id="inputSigninPassword" placeholder="Password"
                                           name="password">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <button type="submit" class="btn">로그인</button>
                                </div>
                                <%--                  <p class="aligncenter margintop20">--%>
                                <%--                    Forgot password? <a href="#myReset" data-dismiss="modal" aria-hidden="true" data-toggle="modal">Reset</a>--%>
                                <%--                  </p>--%>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- end signin modal -->
                <!-- Signup Modal -->
                <div id="mySignup" class="modal styled hide fade" tabindex="-1" role="dialog"
                     aria-labelledby="mySignupModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 id="mySignupModalLabel"><strong>회원가입</strong></h4>
                    </div>
                    <div class="modal-body">
                        <form class="form-horizontal" action="/minihome/SignUp" method="post">
                            <div class="control-group">
                                <label class="control-label" for="inputId">ID</label>
                                <div class="controls">
                                    <input type="text" id="inputId" placeholder="ID" name="userId" required>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="inputSignupPassword">Password</label>
                                <div class="controls">
                                    <input type="password" id="inputSignupPassword" placeholder="Password"
                                           name="userPassword" required>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="inputSignupPassword2">Confirm Password</label>
                                <div class="controls">
                                    <input type="password" id="inputSignupPassword2" placeholder="Password"
                                           name="userPasswordConfirm" required>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="inputSignupName">Name</label>
                                <div class="controls">
                                    <input type="text" id="inputSignupName" placeholder="Name" name="userName" required>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="inputSignupEmail">Email</label>
                                <div class="controls">
                                    <input type="text" id="inputSignupEmail" placeholder="Email" name="userEmail" required>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Gender</label>
                                <div class="controls">
                                    <input type="radio" name="userGender" value="M">남 <input type="radio"
                                                                                             name="userGender"
                                                                                             value="F">여
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="inputSignupPhone">Phone Number</label>
                                <div class="controls">
                                    <input type="text" id="inputSignupPhone" placeholder="Phone Number"
                                           name="userPhone">
                                </div>
                            </div>
                            <div class="control-group">
                                <div class="controls">
                                    <button type="submit" class="btn">회원가입</button>
                                </div>
                                <p class="aligncenter margintop20">
                                    이미 가입된 회원이신가요? <a href="#mySignin" data-dismiss="modal" aria-hidden="true"
                                                      data-toggle="modal">로그인</a>
                                </p>
                            </div>
                        </form>
                    </div>
                </div>
                <!-- end signup modal -->

            </div>
        </div>

        <div class="row">
            <div class="span4">
                <div class="logo">
                    <a href="/minihome"><img src="/minihome/img/logo.png" alt="Home" class="logo"/></a>
                    <h1>Personal MiniHomepage</h1>
                </div>
            </div>
            <div class="span8">
                <div class="navbar navbar-static-top">
                    <div class="navigation">
                        <nav>
                            <ul class="nav topnav">
                                <li>
                                    <a href="/minihome">Home&nbsp;&nbsp;&nbsp;</a>
                                </li>
                                <c:if test="${sessionScope.id ne null}">
                                    <li class="dropdown">
                                        <a href="/minihome/FriendList">Friend <i class="icon-angle-down"></i></a>
                                        <ul class="dropdown-menu">
                                            <li><a href="/minihome/FriendList">친구목록</a></li>
                                            <li><a href="/minihome/friend/findFriendForm.jsp">친구추가</a></li>
                                        </ul>
                                    </li>
                                </c:if>
                                <li class="dropdown">
                                    <a href="#">Pages <i class="icon-angle-down"></i></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="about.html">About us</a></li>
                                        <li><a href="pricingbox.html">Pricing boxes</a></li>
                                        <li><a href="testimonials.html">Testimonials</a></li>
                                        <li><a href="404.html">404</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="#">Portfolio <i class="icon-angle-down"></i></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="portfolio-2cols.html">Portfolio 2 columns</a></li>
                                        <li><a href="portfolio-3cols.html">Portfolio 3 columns</a></li>
                                        <li><a href="portfolio-4cols.html">Portfolio 4 columns</a></li>
                                        <li><a href="portfolio-detail.html">Portfolio detail</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown">
                                    <a href="/minihome/FreeBoardList">Board <i class="icon-angle-down"></i></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="/minihome/FreeBoardList">자유게시판</a></li>
                                        <li><a href="blog-right-sidebar.html">Blog right sidebar</a></li>
                                        <li><a href="post-left-sidebar.html">Post left sidebar</a></li>
                                        <li><a href="post-right-sidebar.html">Post right sidebar</a></li>
                                    </ul>
                                </li>
                                <li>
                                    <a href="/minihome/Visit">방명록 </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <!-- end navigation -->
                </div>
            </div>
        </div>

    </div>
</header>
