<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
    <div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>${tag } 목록</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="breadcumb-nav">
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                           	<%-- 검색 --%>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>
    </div>
    <!-- ****** Breadcumb Area End ****** -->

    <!-- ****** Archive Area Start ****** -->
    <section class="archive-area section_padding_80">
        <div class="container">
            <div class="row">

				
                <!-- Single Post -->
                <c:forEach var="vo" items="${list }" > 
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single-post wow fadeInUp" data-wow-delay="0.1s">
                        <!-- Post Thumb -->
                        <div class="post-thumb">
                        	<a href="../goods/detail_before.do?no=${vo.no }&page=${curPage}&cno=${cno}">
                            <img src="${vo.goods_poster }" alt="">
                            </a>
                        </div>
                        <!-- Post Content -->
                        <div class="post-content">
                            <div class="post-meta d-flex">
                                <div class="post-author-date-area d-flex">
                                    <!-- Post Author -->
                                    <div class="post-author">
                                        <a href="../goods/detail_before.do?no=${vo.no }&page=${curPage}&cno=${cno}">${vo.goods_price }</a>
                                    </div>
                                    <div class="post-author">
                                        <a href="../goods/detail_before.do?no=${vo.no }&page=${curPage}&cno=${cno}">할인율 : ${vo.goods_discount }%</a>
                                    </div>
                                </div>
                                <!-- Post Comment & Share Area -->
               
                            </div>
                            <a href="../goods/detail_before.do?no=${vo.no }&page=${curPage}&cno=${cno}">
                                <h4 class="post-headline">${vo.goods_name }</h4>
                            </a>
                        </div>
                    </div>
                </div>
                </c:forEach>
				
				 <div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            	<c:if test="${startPage>1}">
                            	<li class="page-item">
                                    <a class="page-link" href="../goods/list.do?page=${startPage-1 }&cno=${cno}"><i class="fa fa-angle-double-left" aria-hidden="true"> Before</i></a>
                                </li>
                                </c:if>
                                <c:forEach var="i" begin="${startPage }" end="${endPage }">
                                <li class="page-item ${i==curPage?'active':'' }"><a class="page-link" href="../goods/list.do?page=${i }&cno=${cno}">${i }
                                <c:if test="${i==curPage}">
                                <span class="sr-only">(current)</span>
                                </c:if>
                                </a></li>
                                </c:forEach>
                                <c:if test="${endPage<totalPage }">
                                <li class="page-item">
                                    <a class="page-link" href="../goods/list.do?page=${endPage+1 }&cno=${cno}">Next <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
                                </li>
                                </c:if>
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p>Page ${curPage } of ${totalPage } results</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Archive Area End ****** -->
</body>
</html>