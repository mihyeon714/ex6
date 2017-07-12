<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="../../resources/SE2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
    //전역변수선언
    var editor_object = [];
     
    nhn.husky.EZCreator.createInIFrame({
        oAppRef: editor_object,
        elPlaceHolder: "smarteditor",
        sSkinURI: "../../resources/SE2/SmartEditor2Skin.html", 
        htParams : {
            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseToolbar : true,             
            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
            bUseVerticalResizer : true,     
            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
            bUseModeChanger : true, 
        }
    });
     
    //전송버튼 클릭이벤트
    $("#savebutton").click(function(){
        //id가 smarteditor인 textarea에 에디터에서 대입
        editor_object.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);
         
        // 이부분에 에디터 validation 검증
         
        //폼 submit
        $("#frm").submit();
    });
})
</script>

<style type="text/css">
table, th, td{
	text-align: center;
	font-size: 15px;	
}

input{
	border-style: none;
	width:50%;
	height: 100%;
}

textarea{
	width:100%;
}
</style>
<title>boardWrite</title>
</head>
<body>
	<h1>${board} Write Form</h1>
	<form action="${board}${path}" method="POST" id="frm">
		<c:catch>
			<input type="hidden" name="num" value="${dto.num}">
		</c:catch>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>TITLE</th>
					<th>WRITER</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td><input type="text" name="title" value="${dto.title}"></td>
					<td><input type="text" name="writer" value="${dto.writer}"></td>
				</tr>
			</tbody>
			<thead>
				<tr>
					<th colspan="2">CONTENTS</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td colspan="2"><textarea id="smarteditor" cols="300px" rows="100%" name="contents">${dto.contents}</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" id="savebutton" value="글쓰기"></td>
				</tr>
			</tbody>
		</table>
	</form>
</body>
</html>