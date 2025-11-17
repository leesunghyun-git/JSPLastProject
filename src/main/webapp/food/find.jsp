<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
let page=1;
let fd='';
let column='';
let types=[];
function ajaxdata(){
	$.ajax({
		type:'post',
		url:'../food/find_ajax.do',
		data:{"fd":fd,"column":column,"type":types,"page":page},
		traditional:true,
		dataType:'json',
		success:function(result)
		{
			$('#count').text(result.count)
			console.log(result)
			listappend(result.list)
			renderPage(result.startPage,result.endPage,result.totalPage)
		},
		error:function(error)
		{
			console.log(error)
		}
	})
}
$(function(){
	$('#findBtn').click(function(){
		let count=$('input[name=type]:checked').length
		page=1;
		curPage=1;
		if(count==0)
		{
			alert("체크박스에 체크하세요!!")
			return
		}
		$('input[name=type]:checked').each(function(){
			types.push($(this).val())
		
		})
		console.log(types)
		fd=$('#fd').val()
		if(fd.trim()==="")
		{
			$('#fd').focus()
			return
		}
		column=$('#column').val()
		console.log(types)
		console.log("검색어:"+fd)
		console.log("컬럼명:"+column)
		// 배열 => traditional
		ajaxdata()
	
	})
	$('#fd').keydown(function(e){
		if(e.keyCode==13)
		{
			let count=$('input[name=type]:checked').length
			page=1;
			curPage=1;
			if(count==0)
			{
				alert("체크박스에 체크하세요!!")
				return
			}
			$('input[name=type]:checked').each(function(){
				types.push($(this).val())
			
			})
			console.log(types)
			fd=$('#fd').val()
			if(fd.trim()==="")
			{
				$('#fd').focus()
				return
			}
			column=$('#column').val()
			console.log(types)
			console.log("검색어:"+fd)
			console.log("컬럼명:"+column)
			ajaxdata()
		}
	})
	$(document).on('click', '.page-link', function(e){
        e.preventDefault();
        let p = $(this).data('page');
        if(p === undefined) return;
        page = parseInt(p);
        ajaxdata();
        $('html, body').animate({
            scrollTop: $("#fdresult").offset().top - 100
        }, 500);
    });
})
function listappend(list){
	let html='';
	$.each(list,function(index,vo){
		html+=''
			+'	 <div class="col-12 col-md-6 col-lg-4">'
			+' <div class="single-post wow fadeInUp" data-wow-delay="0.1s">'
			+'<div class="post-thumb">'
			+'	<a href="../food/detail.do?fno='+vo.fno+'">'
			+'    <img src="'+vo.poster+'" alt="">'
			+'    </a>'
			+' </div>'
			+'<div class="post-content">'
			+'    <div class="post-meta d-flex">'
			+'        <div class="post-author-date-area d-flex">'
			+'            <div class="post-author">'
			+'                <a href="../food/detail.do?fno'+vo.fno+'">'+vo.type+'</a>'
			+'            </div>'
			+'           <div class="post-date">'
			+'               <a href="../food/detail.do?fno'+vo.fno+'">'+vo.address+'</a>'
			+'            </div>'
			+'        </div>'
			+'        <div class="post-comment-share-area d-flex">'
			+'            <div class="post-favourite">'
			+'                <a href="../food/detail.do?fno'+vo.fno+'"><i class="fa fa-heart-o" aria-hidden="true"></i>'+vo.likecount+'</a>'
			+'            </div>'
			+'           <div class="post-comments">'
			+'                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i>'+vo.replycount+'</a>'
			+'            </div>'
			+'            <div class="post-share">'
			+'               <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>'
			+'            </div>'
			+'        </div>'
			+'     </div>'
			+'    <a href="../food/detail.do?fno'+vo.fno+'">'
			+'        <h4 class="post-headline">'+vo.name+'</h4>'
			+'    </a>'
			+'</div>'
			+' </div>'
			+'</div>'
	
	})
	$('#fdresult').html(html)


}
function renderPage(startPage,endPage,totalPage){
	let pagehtml='';
	if(startPage>1)
	{
		pagehtml+=''
		+'<li class="page-item">'
        +'<a href="#" class="page-link" data-page='+(startPage-1)+'"><i class="fa fa-angle-double-left" aria-hidden="true"> Before</i></a>'
        +'</li>'
	}
	for(let i=startPage;i<=endPage;i++)
	{
		pagehtml+=''
		+'<li class="page-item"><a class="page-link '+(i==page?"active":"")+'" href="#" data-page='+i+'>'+i;
		if(i==page)
			{
				pagehtml+='<span class="sr-only">(current)</span>'
			}
		pagehtml+='</a></li>'
	
	}
    if(endPage<totalPage)
    {
    	pagehtml+=''
		+'<li class="page-item">'
		+'<a class="page-link" href="#" data-page='+(endPage+1)+'>Next <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>'
		+'</li>'
    
    }
        
	$('.pagination').html(pagehtml);
	$('.page-status p').text('page '+page+' / '+totalPage+' pages');

}
</script>
<style type="text/css">
.recipe_row {
	margin: 0px auto;
	width: 850px;
}
</style>
</head>
<body>
	<!-- ****** Breadcumb Area Start ****** -->
	<div class="breadcumb-area"
		style="background-image: url(../img/bg-img/breadcumb.jpg);">
		<div class="container h-100">
			<div class="row h-100 align-items-center">
				<div class="col-12">
					<div class="bradcumb-title text-center">
						<h2>맛집 검색</h2>
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
						<input type="checkbox" name="type" value="A">한식
						<input type="checkbox" name="type" value="B">일식
						<input type="checkbox" name="type" value="C">중식
						<input type="checkbox" name="type" value="D">양식
						<input type="checkbox" name="type" value="E">분식
						<select name="column" id="column"class="input-sm">
							<option value="address">주소</option>
							<option value="name">업체명</option>
							<option value="theme">테마</option>
						</select>
						<input type="text" name=fd id="fd" class="input-sm">
						<input type="button" id="findBtn" value="검색" class="btn-sm btn-danger">	
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
				<h3>총 검색 결과 <span id="count"></span>건</h3>
			</div>
			<div class="row" id="fdresult">
			
			</div>
			<div class="col-12">
                    <div class="pagination-area d-sm-flex mt-15">
                        <nav aria-label="#">
                            <ul class="pagination">
                            	
                            </ul>
                        </nav>
                        <div class="page-status">
                            <p></p>
                        </div>
                    </div>
                </div>
		</div>
	</section>
</body>
</html>