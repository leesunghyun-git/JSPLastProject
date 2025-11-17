<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%--
			아키텍처 : 요구사항 분석
			   |
		--------------------
		|		 |		   |
	   PM		PM		  PM
	    |
	 --------
	 |		|
	PL	    PL => 데이터베이스 설계
	 => 서버단  View  DB단
	 				  | DAO
	 			| Front
	 	  | => Model
	 |
   -----
   |   |
  2년  1년
   |
  신입


 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
$(function(){
	$('.timeBtn').click(function(){
		let time=$(this).text();
		$('#rtime').val(time);
		$('#food_reserve_time').text(time);
		$.ajax({
			type:'post',
			url:'../reserve/reserve_inwon.do',
			success:function(result){
				$('#reserve_inwon').html(result);
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
    <div class="d-flex justify-content-center gap-2 flex-wrap">
    	<c:forEach var="time" items="${tList }">
    		  <button class="btn btn-outline-primary btn-sm timeBtn">${time }</button>
    	</c:forEach>
</div>
</body>
</html>