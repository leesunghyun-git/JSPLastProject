<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.page-link{
  cursor: pointer;
}
</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#findBtn').click(function(){
		commons(1)
	})
	
})
function commons(page)
{
	let column=$('#column').val()
	let fd=$('#fd').val()
	$.ajax({
		type:'post',
		url:'../recipe/recipe_result.do',
		data:{"fd":fd,"column":column,"page":page},
		// responseText => HTML을 문자열로 읽기 responseXML => Object
		success:function(result)
		{
			$('.find_print').html(result)
		}
	})
}
function change(page)
{
	commons(page)
}
function prev(page)
{
	commons(page)
}
function next(page)
{
	commons(page)
}
</script>
</head>
<body>
<div class="breadcumb-area" style="background-image: url(../img/bg-img/breadcumb.jpg);">
        <div class="container h-100">
            <div class="row h-100 align-items-center">
                <div class="col-12">
                    <div class="bradcumb-title text-center">
                        <h2>레시피 검색</h2>
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
                          <!-- <form method="post" action="../food/find.do"> -->
                            <select name="column" id="column" class="input-sm">
                              <option value="title">제목</option>
                              <option value="chef">쉐프</option>
                    
                            </select>
                            <input type=text name=fd id="fd" class="input-sm">
                            <input type=button value="검색"
                             class="btn-sm btn-danger" id="findBtn">
                            <!-- </form> -->
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
            <div class="row find_print">
              
            </div>
            
        </div>
    </section>

</body>
</html>x