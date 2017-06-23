<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>boardList</title>

<script type="text/javascript">
	$(function(){
		$(".go").click(function(){
			/* 
			//location사용하기
			var cur = $(this).attr("id");
			location.href="${board}List?curPage="+cur+"&search=${listInfo.search}&find=${listInfo.find}";
			 */
			//form 사용하기
			document.frm.curPage.value=$(this).attr("id");
			document.frm.search.value="${listInfo.search}";
			document.frm.find.value="${listInfo.find}";
			document.frm.submit();
		});
	});
</script>


<script type="text/javascript">
	var m = '${message}'; //redirect로 받은 message 임 
	if( m != ''){
		alert(m); // 들어있으면 알려주세요
	}
</script>

<style type="text/css">
span{
	cursor: pointer;
}
</style>
</head>
<body>
	<!-- LIST 제목 -->
	<h1>${board}</h1>
	
	<div>
		<form action="${board}List" name="frm">
				<input type="hidden" name="curPage">
				<select name="search">
				<option value="title">TITLE</option>
				<option value="writer">WRITER</option>
				<option value="contents">CONTENTS</option>
			</select>
			<input type="text" name="find" value="${listInfo.find}">
			<input type="submit" value="SEARCH">
		</form>
	</div>
	

	<table class="table table-bordered">
		<thead>
			<tr>
				<th>NUM</th>
				<th>TITLE</th>
				<th>WRITER</th>
				<th>REG_DATE</th>
				<th>HIT</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.num}</td>
					<td>
						<c:catch><c:forEach begin="1" end="${dto.depth}">ㄴ</c:forEach></c:catch>
						<a href="../${board}/${board}View?num=${dto.num}">${dto.title}</a>
					</td>
					<td>${dto.writer}</td>
					<td>${dto.reg_date}</td>
					<td>${dto.hit}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	<c:if test="${listInfo.curBlock > 1}">
		<span class="go" id="${listInfo.startNum-listInfo.perBlock}">[이전]</span>
	</c:if>
	
	<c:forEach begin="${listInfo.startNum}" end="${listInfo.lastNum}" var="i">
		<span class="go" id="${i}">${i}</span>
	</c:forEach>
	
	<c:if test="${listInfo.curBlock < listInfo.totalBlock}">
		<span class="go" id="${listInfo.lastNum+1}">[다음]</span>
	</c:if>
	

	<a href="./${board}Write">글쓰기</a>
	<a href="../">뒤로가기</a>
	
	<div>
		<p>curPage : ${listInfo.curPage}</p>
		<p>search : ${listInfo.search}</p>
		<p>find : ${listInfo.find}</p>
	</div>


</body>
</html>