<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>JSPLastProject</title>
    <link rel="icon" href="../img/core-img/favicon.ico">
    <link href="../css/style.css" rel="stylesheet">
    <link href="../css/responsive/responsive.css" rel="stylesheet">
</head>
<body>
    <div id="preloader">
        <div class="yummy-load"></div>
    </div>
    <div id="pattern-switcher">
        실시간 상담
    </div>
    <div id="patter-close">
        <i class="fa fa-times" aria-hidden="true"></i>
    </div>
	<!-- ***** header ******** -->
    <jsp:include page="header.jsp"></jsp:include>
	<!-- ******* home ********* -->
    <jsp:include page="${main_jsp }"></jsp:include>
	<!-- ******** cookies ******* -->
	<jsp:include page="cookies.jsp"></jsp:include>
	<!-- ******** Footer ******** -->
    <jsp:include page="footer.jsp"></jsp:include>
	
	
    <script src="../js/jquery/jquery-2.2.4.min.js"></script>
    <script src="../js/bootstrap/popper.min.js"></script>
    <script src="../js/bootstrap/bootstrap.min.js"></script>
    <script src="../js/others/plugins.js"></script>
    <script src="../js/active.js"></script>
</body>
