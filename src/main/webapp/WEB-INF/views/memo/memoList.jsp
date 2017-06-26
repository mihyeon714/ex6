<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>memoList</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>


<script type="text/javascript" src="../resources/js/memo.js"></script> <!-- 만들어둔 함수 -->
<script type="text/javascript">

	/* js 파일을 분리했을때 분리된 파일에서 DOM 요소를 읽어오지 못하는 경우가 있으나 원인을 알지 못하였다 */
	/* 따라서 매개변수들을 잘 활용하는 것이 좋겠다 */

	$(function(){
		
		getList(1,'%','%'); //초반에 list 로딩
		
		$("#btn").click(function(){
			//form 의 value 들로 ajax 
			var writer = $("#writer").val();
			var contents = $("#contents").val();
			//alert("writer= "+ writer + " contents= "+contents);
			memoWrite(writer,contents);
			//비우기
			$("#writer").val("");
			$("#contents").val("");
		});
		
		
		$("#btnView").click(function(){
			 memoView(13);
		});
		
		
		
	});

</script>


</head>
<body>
	
	<div>
		<form action="">
			<p><input type="text" id="writer"></p>
			<p><textarea rows="" cols="" id="contents"></textarea></p>
			<input type="button" value="WRITE" id="btn">
		</form>
	</div>


	<!-- 처음부터 ajax 이용해서 가져오기 -->
	<div id="result"></div>
	
	<button id="btnView">VIEW</button>
	
	
	
</body>
</html>