<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.dataTr:hover{
	cursor:pointer;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.dataTr').click(function(){
		$('.detail_row').show()
		let fno=$(this).attr("data-no");
		/* alert("fno:"+fno) */
		$.ajax({
				type:'post',
				url:'../jjim/food_detail.do',
				data:{"fno":fno},
				success:function(result){
					let json=JSON.parse(result)
					$('#poster').attr("src",json.poster)
					$('#name').text(json.name)
					$('#score').text(json.score)
					$('#address').text(json.address)
					$('#phone').text(json.phone)
					$('#price').text(json.price)
					$('#parking').text(json.parking)
					$('#type').text(json.type)
					$('#theme').text(json.theme)
					$('#content').text(json.content)
					$('#time').text(json.time)
					
					setTimeout(()=>{
						$('.detail_row').hide("slow")
					
					},10000);
					
				},
				error:function(error){
					console.log(error)
				}
		
		})
	})

})

</script>
</head>
<body>
	<main class="mypage-main">
    <h2>찜 내역</h2>
    <div class="info-card">
      <table>
        <tr>
          <th width="10%">번호</th>
          <th width="15%"></th>
          <th width="25%">맛집명</th>
          <th width="35%">주소</th>
          <th width="15%"></th>
        </tr>
        <c:forEach var="vo" items="${list }">
        <tr class="dataTr" data-no=${vo.fvo.fno }>
        	<td width="10%">${vo.jno }</td>
        	<td width="15%">
        		<img src="${vo.fvo.poster }" style="width:35px;height:35px;">
        	</td >
        	<td width="25%">${vo.fvo.name }</td>
        	<td width="35%">${vo.fvo.address }</td>
        	<td width="15%">
        		<a href="../jjim/jjim_cancel.do?jno=${vo.jno }" class="btn btn-danger btn-xs">취소</a>
        	</td>
        </tr>
        </c:forEach>
      </table>
    </div>
    <div class="row detail_row" style="display:none">
    				<table class="table">
						<tr>
							<td width=40% rowspan="8" class="text-center"><img
								src="${poster }" style="width: 340px; height: 350px"
								class="img-rounded" id="poster"></td>
							<td colspan="2">
								<h3 id="name">${name }&nbsp;<span style="color: orange" id="score">${score }</span>
								</h3>
							</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">주소</td>
							<td width=45% id="address">${address }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">전화</td>
							<td width=45% id="phone">${phone }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">음식종류</td>
							<td width=45% id="type">${type }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">가격대</td>
							<td width=45% id="price">${price }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">주차</td>
							<td width=45% id="parking">${parking }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">영업시간</td>
							<td width=45% id="time">${time }</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">테마</td>
							<td width=45% id="theme">${theme }</td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<td id="table">${content }</td>
						</tr>
					</table>
		</div>
  </main>
</body>
</html>