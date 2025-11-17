<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('.reserves').click(function(){
		$('.detail_row').show()
		let no=$(this).attr("data-no");
		/* alert("fno:"+fno) */
		$.ajax({
				type:'post',
				url:'../mypage/reserve_detail.do',
				data:{"no":no},
				success:function(result){
					let json=JSON.parse(result)
					$('#poster').attr("src",json.poster)
					$('#no').text(json.no)
					$('#rday').text(json.rday)
					$('#time').text(json.time)
					$('#inwon').text(json.inwon)
					$('#dbday').text(json.dbday)
					$('#name').text(json.name)
					$('#address').text(json.address)
					$('#phone').text(json.phone)
					$('#parking').text(json.parking)
					$('#score').text(json.score)
					$('#type').text(json.type)
					
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
    <h2>예약 내역</h2>
    <div class="info-card">
      <table>
        <tr>
          <th width="10%">번호</th>
          <th width="10%"></th>
          <th width="20%">맛집명</th>
          <th width="20%">예약일</th>
          <th width="10%">시간</th>
          <th width="10%">인원</th>
          <th width="20%"></th>
        </tr>
        <c:forEach var="vo" items="${list }">
          <tr class="dataTr" data-no="${vo.fvo.fno}">
            <td width="10%">${vo.no}</td>
            <td width="10%">
              <img src="${vo.fvo.poster}" style="width: 35px;height: 35px">
            </td>
            <td width="20%">${vo.fvo.name}</td>
            <td width="20%">${vo.rday }</td>
            <td width="10%">${vo.time }</td>
            <td width="10%">${vo.inwon }</td>
            <td width="20%">
             <c:if test="${vo.ok==0 }">
              <span class="btn btn-xs btn-defualt">예약대기</span>
             </c:if>
             <c:if test="${vo.ok==1 }">
              <span class="btn btn-xs btn-danger reserves" data-no="${vo.no }">예약완료</span>
             </c:if>
              <a href="../mypage/reserve_cancel.do?no=${vo.no }" class="btn btn-xs btn-warning">취소</a>
            </td>
          </tr>
        </c:forEach>
      </table>
      <div class="row detail_row" style="display:none">
    				<table class="table">
						<tr>
							<td width=40% rowspan="11" class="text-center"><img
								src="" style="width: 340px; height: 350px"
								class="img-rounded" id="poster"></td>
							<td colspan="2">
								<h3 id="name">${name }&nbsp;<span style="color: orange" id="score">${score }</span>
								</h3>
							</td>
						</tr>
						<tr>
							<td width=15% style="color: gray">예약번호</td>
							<td width=45% id="no"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">예약일</td>
							<td width=45% id="rday"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">예약시간</td>
							<td width=45% id="time"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">예약인원</td>
							<td width=45% id="inwon"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">예약등록일</td>
							<td width=45% id="dbday"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">주소</td>
							<td width=45% id="address"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">전화</td>
							<td width=45% id="phone"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">음식정보</td>
							<td width=45% id="type"></td>
						</tr>
						<tr>
							<td width=15% style="color: gray">주차</td>
							<td width=45% id="parking"></td>
						</tr>
					</table>
					<table class="table">
						<tr>
							<td id="table">${content }</td>
						</tr>
					</table>
		</div>
    </div>
  </main>
</body>
</html>