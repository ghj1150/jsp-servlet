<%@page import="vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <title>더조은 아카데미 UI 구현 게시판 레이아웃</title>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
    <link rel="icon" href="images/favicon.ico" type="image/x-icon">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/css/bootstrap.min.css" integrity="sha512-jnSuA4Ss2PkkikSOLtYs8BlYIeeIK1h99ty4YfvRPAlzr377vr3CXDb7sb7eEEBYjDtcYj+AjBH3FLv5uSJuXg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.3/js/bootstrap.bundle.min.js" integrity="sha512-7Pi/otdlbbCR+LnW+F7PwFcSDJOuUJB3OxtEHbg4vSMvzvJjde4Po1v4BR9Gdc9aXNUNFVUY+SK51wWT8WF0Gg==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.min.js" integrity="sha512-v2CJ7UaYy4JwqLDIrZUI/4hqeoQieOmAZNXBeQyjo21dadnwR+8ZaIJVT8EE2iyI61OV8e6M8PP2/4hpQINQ/g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="https://cdn.jsdelivr.net/npm/bxslider@4.2.17/dist/jquery.bxslider.min.js"></script>
</head>
<body>
    <div class="wrap">
        <header class="container-fluid">
            <div class="container clearfix p-2">
                <a href="index.html" class="float-start"><img src="images/logo.png" alt="로고" class="img-fluid" width="250px"></a>
                <h1 class="text-center fw-bold p-3">더조은 아카데미 UI 구현 게시판 레이아웃</h1>
            </div>
        </header>
        <nav class="navbar bg-dark navbar-expand-sm">
            <ul class="navbar-nav container justify-content-start">
                <li class="mx-3 nav-item"><a class="nav-link text-white" href="index.html">메인페이지</a></li>
                <li class="mx-3 nav-item"><a class="nav-link text-white" href="mypage.html">마이페이지</a></li>
                <li class="mx-3 nav-item"><a class="nav-link text-white" href="post.html?category=1">공지사항</a></li>
                <li class="mx-3 nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-white" href="#" role="button" data-bs-toggle="dropdown">게시판</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="post.html?category=2">자유게시판</a></li>
                        <li><a class="dropdown-item" href="post.html?category=3">자료실</a></li>
                        <li><a class="dropdown-item" href="gallery.html">갤러리</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <main class="container">
            <div class="row">
                <div class="col-md-9">
                    <div class="p-3">
                        <h1>index</h1>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="p-3 d-grid gap-2">
                    <%
                    	Object o = session.getAttribute("member");
                    	if(o == null) {
                    %>
                        <a href="signin" class="btn btn-primary btn-block">로그인</a>
                        <div class="small clearfix">    
                            <a href="signup" class="small float-start text-decoration-none">회원가입</a>
                            <a href="signin.html" class="small float-end text-decoration-none">아이디/비밀번호 찾기</a>
                        </div>
					<%
                    	}
                    	else {
                    		Member m = (Member) o;
					%>
						<p><a href="mypage.html" class="text-decoration-none text-dark"><strong><%=m.getName()%></strong></a>님 환영합니다</p>
                        <div class="small clearfix">
                            <a href="logout.html" class="small float-start text-decoration-none">로그아웃</a>
                            <a href="mypage.html" class="small float-end text-decoration-none">마이페이지</a>
                        </div>
					<%
                    	}
					%>                            
                    </div>
                </div>
            </div>
        </main>
        <footer class="bg-warning text-center p-4">
            <address>서울특별시 구로구 디지털로 306 대륭포스트 2차 2층 더조은 아카데미 204호</address>
            <p>All rights reserved &copy; copyright.</p>
        </footer>
    </div>
</body>
</html>