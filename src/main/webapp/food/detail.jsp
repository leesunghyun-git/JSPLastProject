<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/comment.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.detail_row {
	margin: 0px auto;
	width: 850px;
}
.img-link{
	cursor: pointer;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
// ?rno=1 &page=5 => ${param.rno}
let likeCheck =false
let rno=${param.fno}
let page=${param.page}
let id='${sessionScope.id}'

$(function(){
		if(id.length>0)
		{
			$.ajax({
				type:'post',
				url:'../like/likeCheck.do',
				data:{
					"rno":rno,
					"type":1
				},
				success:function(result){
					if(result==='OK')
					{
						likeCheck=true
						$('#likeBtn').attr("src","../img/images/likeon.png")
					}
					else
					{
						likeCheck=false
						$('#likeBtn').attr("src","../img/images/likeoff.png")
					}
				},
				error:function(err)
				{
					console.log(err)
				}
			
			})
		
		}
		$('#likeBtn').click(function(){
			if(likeCheck===true)
			{
				$.ajax({
					type:'post',
					url:'../like/likeOff.do',
					data:{"rno":rno,"type":1},
					success:function(result){
						if(result>=0)
						{
							likeCheck=false
							$('#likeBtn img').attr('src','../img/images/likeoff.png')
						}
					},
					error:function(error){
						console.log(error)
					}
				
				})

			
			}
			else
			{
				$.ajax({
					type:'post',
					url:'../like/likeOn.do',
					data:{"rno":rno,"type":1},
					success:function(result){
						if(result>=0)
						{
							likeCheck=true
							$('#likeBtn img').attr('src','../img/images/likeon.png')
						}
					},
					error:function(error){
						console.log(error)
					}
				
				})
			}
			
		
		
		})
		let bcheck=false
		$('.updateBtn').click(function(){
			let no=$(this).attr("data-no");
			$('.forms').hide();
			$('.updateBtn').val("수정")
			if(bcheck===false)
			{
				bcheck=true
				$('#form'+no).show()
				$(this).val("취소")
			}
			else
			{
				bcheck=false
				$('#form'+no).hide()
				$(this).val("수정")
			}
			
		})



})

</script>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area"
		style="background-image: url(../img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>맛집 상세보기</h2>
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
							<%-- 검색기 --%>
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
			<div class="row justify-content-center">
				<div class="col-12 col-lg-8">
					<div class="row no-gutters">
						<!-- Related Post Area -->
						<div class="col-12 col-sm-12">
							<div class="related-post-area section_padding_50">

								<div class="related-post-slider owl-carousel">
									<c:forTokens items="${vo.images }" delims="," var="img">
										<!-- Single Related Post-->
										<div class="single-post">
											<!-- Post Thumb -->
											<div class="post-thumb">
												<img src="${img }" alt="">
											</div>
										</div>
									</c:forTokens>
								</div>
							</div>
						</div>
					</div>

					<table class="table">
						<tr>
							<td width=40% rowspan="8" class="text-center"><img
								src="${vo.poster }" style="width: 340px; height: 350px"
								class="img-rounded"></td>
							<td colspan="2">
								<h3>${vo.name }&nbsp;<span style="color: orange">${vo.score }</span>
								</h3>
							</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">주소</td>
							<td width=45%>${vo.address }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">전화</td>
							<td width=45%>${vo.phone }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">음식종류</td>
							<td width=45%>${vo.type }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">가격대</td>
							<td width=45%>${vo.price }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">주차</td>
							<td width=45%>${vo.parking }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">영업시간</td>
							<td width=45%>${vo.time }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">테마</td>
							<td width=45%>${vo.theme }</td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<td>${vo.content }</td>
						</tr>
						<tr>
							<td class="text-right">
							<c:if  test="${sessionScope.admin=='n' && sessionScope.id!=null }">
							<a href="#"
								class="btn btn-xs" id="likeBtn"><img src="../img/images/likeoff.png"
								style="width:25px;height:25px"
								></a> 
								<c:if test="${jCount==null || jCount==0 }">
								<a href="../jjim/jjim_insert.do?rno=${vo.fno}&type=1&page=${page}"class="btn btn-xs btn-success">찜하기</a> 
								</c:if>
								<c:if test="${jCount!=null && jCount!=0 }">
								<span class="btn btn-xs btn-outline-success">찜하기</span> 
								</c:if>		
								
								<a href="#"class="btn btn-xs btn-info">예약하기</a>
							</c:if>
								<c:if test="${page==0}">
								<a href="javascript:history.back()" class="btn btn-xs btn-warning">목록</a>
								</c:if>
								<c:if test="${page!=0}">
								<a href="../food/list.do?page=${page}" class="btn btn-xs btn-warning">목록</a>
								</c:if>
								
								</td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<td class="text-center">
								<div id="map" style="width: 100%; height: 350px;"></div> <script
									type="text/javascript"
									src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8ba7c1bf5703be19d075b1df1555ef2f&libraries=services"></script>
								<script>
									var mapContainer = document
											.getElementById('map'), // 지도를 표시할 div 
									mapOption = {
										center : new kakao.maps.LatLng(
												33.450701, 126.570667), // 지도의 중심좌표
										level : 3
									// 지도의 확대 레벨
									};

									// 지도를 생성합니다    
									var map = new kakao.maps.Map(mapContainer,
											mapOption);

									// 주소-좌표 변환 객체를 생성합니다
									var geocoder = new kakao.maps.services.Geocoder();

									// 주소로 좌표를 검색합니다
									geocoder
											.addressSearch(
													'${vo.address}',
													function(result, status) {

														// 정상적으로 검색이 완료됐으면 
														if (status === kakao.maps.services.Status.OK) {

															var coords = new kakao.maps.LatLng(
																	result[0].y,
																	result[0].x);

															// 결과값으로 받은 위치를 마커로 표시합니다
															var marker = new kakao.maps.Marker(
																	{
																		map : map,
																		position : coords
																	});

															// 인포윈도우로 장소에 대한 설명을 표시합니다
															var infowindow = new kakao.maps.InfoWindow(
																	{
																		content : '<div style="width:150px;text-align:center;padding:6px 0;">${vo.name}</div>'
																	});
															infowindow
																	.open(map,
																			marker);

															// 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
															map
																	.setCenter(coords);
														}
													});
								</script>
							</td>
						</tr>

					</table>
				</div>
			</div>
		</div>
		<!-- 댓글 -->
		<h2>맛집 리뷰</h2>
		<c:if test="${rCount==0 }">
				리뷰가 없습니다.
			</c:if>
		<c:if test="${rCount>0 }">
		<ul class="review-list">
			<c:forEach var="rvo" items="${reList }">
			<li class="review-card">
				<div class="review-header">
					<div class="review-avatar">${fn:substring(rvo.name,0,1) }</div>
					<div class="review-nick">${rvo.id }</div>
					<div class="review-date">${rvo.dbday }</div>
				</div>
				<div class="review-text">${rvo.msg }</div>
				<c:if test="${sessionScope.id==rvo.id }">
				<div class="review-meta">
					<div><input type="button" class="btn-xs btn-primary updateBtn" value="수정" data-no="${rvo.no }"></div>
					<div><a href="../review/review_delete.do?no=${rvo.no }&type=1&cno=${rvo.cno}&page=${page}" class="btn-xs btn-danger">삭제</a>
				</div>
				</c:if>
				<div>
				<form class="review-form forms" id="form${rvo.no}"method="post" action="../review/review_update.do" style="display:none">
				<input type="hidden" name="cno" value="${vo.fno }">
				<input type="hidden" name="page" value="${page }">
				<input type="hidden" name="no" value="${rvo.no }">
				<input type="hidden" name="type" value="1">
				<input type="text" name="msg" placeholder="리뷰 입력" required="required">
				<button type="submit">등록</button>
				</form>
				</div>
			</li>
			</c:forEach>
		</ul>
		</c:if>
		<c:if test="${sessionScope.id!=null }">
		<form class="review-form" method="post" action="../review/review_insert.do">
			<input type="hidden" name="cno" value="${vo.fno }">
			<input type="hidden" name="page" value="${page }">
			<input type="hidden" name="type" value="1">
			<input type="text" name="msg" placeholder="리뷰 입력" required="required">
			<button type="submit">등록</button>
		</form>
		</c:if>
	</section>
</body>
</html>