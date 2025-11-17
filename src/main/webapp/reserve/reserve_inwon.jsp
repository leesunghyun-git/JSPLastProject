<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(function(){
		$('.inwon').click(function(){
			let inwon=$(this).text()
			$('#food_reserve_inwon').text(inwon)
			$('#rinwon').val(inwon)
			$('#reserveBtn').show()
		})
	})
</script>
</head>
<body>
	 <div class="d-flex justify-content-center gap-2 flex-wrap">
	 		<c:forEach var="i" begin="2" end="5">
              <button class="btn btn-outline-success btn-sm inwon">${i }명</button>
     		</c:forEach>
     		  <button class="btn btn-outline-success btn-sm inwon">단체</button>	
     </div>
</body>
</html>