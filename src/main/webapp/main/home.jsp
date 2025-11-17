<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
header #navbar {
  position: relative !important;
  z-index: 9000 !important;
}

header #navbar ul.menu-list li {
  position: relative !important;
  z-index: 9001 !important;
}
</style>
</head>
<body>
	<!-- ****** Categories Area Start ****** -->
    <section class="categories_area clearfix" id="about">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".3s">
                        <img src="../img/catagory-img/1.jpg" alt="">
                        <div class="catagory-title">
                            <a href="#newsSection">
                                <h5>오늘의 날씨</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".6s">
                        <img src="../img/catagory-img/2.jpg" alt="">
                        <div class="catagory-title">
                            <a href="#">
                                <h5>오늘의 뉴스</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".9s">
                        <img src="../img/catagory-img/3.jpg" alt="">
                        <div class="catagory-title">
                            <a href="#">
                                <h5>오늘의 신상품</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Categories Area End ****** -->

    <!-- ****** Blog Area Start ****** -->
    <section class="blog_area section_padding_0_80">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="row">

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="single-post wow fadeInUp" data-wow-delay=".2s">
                                <!-- Post Thumb -->
                                <div class="post-thumb text-center">
                                	<a href="../food/detail_before.do?fno=${mainVO.fno}">
                                    <img src="${mainVO.poster }" style="width:580px;height:400px">
                                    </a>
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="../food/detail_before.do?fno=${mainVO.fno}">${mainVO.type }</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="../food/detail_before.do?fno=${mainVO.fno}">${mainVO.address }</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="../food/detail_before.do?fno=${mainVO.fno}"><i class="fa fa-heart-o" aria-hidden="true"></i>${mainVO.likecount }</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="../food/detail_before.do?fno=${mainVO.fno}"><i class="fa fa-comment-o" aria-hidden="true"></i>${mainVO.replycount }</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="../food/detail_before.do?fno=${mainVO.fno}">
                                        <h2 class="post-headline">${mainVO.name }</h2>
                                    </a>
                                    <p>${mainVO.content }</p>
                                    <a href="../food/detail_before.do?fno=${mainVO.fno}" class="read-more">상세 보기</a>
                                </div>
                            </div>
                        </div>

						<c:forEach var="hvo" items="${hitList }">
                        <!-- Single Post -->
                        <div class="col-12 col-md-6">
                            <div class="single-post wow fadeInUp" data-wow-delay=".4s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="${hvo.poster }" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">${hvo.type }</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">${hvo.address }</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> ${hvo.likecount }</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> ${hvo.replycount }</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">${vo.name }</h4>
                                    </a>
                                </div>
                            </div>
                        </div>
						</c:forEach>

                        <!-- ******* List Blog Area Start ******* -->

                        <!-- Single Post -->
                        <c:forEach var="lvo" items="${likeList }">
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".2s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="${lvo.poster }" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">${lvo.type }</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">${lvo.address }</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> ${lvo.likecount }</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> ${lvo.replycount  }</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">${lvo.name }</h4>
                                    </a>
                                    <p>${lvo.content }</p>
                                    <a href="#" class="read-more">상세 보기</a>
                                </div>
                            </div>
                        </div>
						</c:forEach>
                </div>
                </div>

                <!-- ****** Blog Sidebar ****** -->
                <div class="col-12 col-sm-8 col-md-6 col-lg-4">
                    <div class="blog-sidebar mt-5 mt-lg-0">
                        <!-- Single Widget Area -->
                        <div class="single-widget-area about-me-widget text-center">
                            <div class="widget-title">
                                <h6>오늘의 쉐프</h6>
                            </div>
                            <div class="about-me-widget-thumb">
                                <img src="${cvo.poster }" alt="">
                            </div>
                            <h4 class="font-shadow-into-light">${cvo.chef }</h4>
                        </div>

                        <!-- Single Widget Area -->

                        <!-- Single Widget Area -->
                        <div class="single-widget-area popular-post-widget">
                            <div class="widget-title text-center">
                                <h6>인기 레시피</h6>
                            </div>
                            <!-- Single Popular Post -->
                            <c:forEach var="rvo" items="${rList }">
                            <div class="single-populer-post d-flex">
                                <img src="${rvo.poster }" alt="">
                                <div class="post-content">
                                    <a href="#">
                                        <h6>${rvo.title }</h6>
                                    </a>
                                    <p>${rvo.year }. ${rvo.month }. ${rvo.day }</p>
                                </div>
                            </div>
                            </c:forEach>
                            <!-- Single Popular Post --> 
                        </div>

                        <!-- Single Widget Area -->
                        <div class="single-widget-area add-widget text-center" id="">
                            
                        </div>

                        <!-- Single Widget Area -->
                        <div class="single-widget-area newsletter-widget" id="newsSection">
                             <jsp:include page="../news/news.jsp"></jsp:include>
                        </div>	
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Blog Area End ****** -->
</body>
</html>