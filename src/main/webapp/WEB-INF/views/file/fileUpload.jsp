<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fileUpload</title>
</head>
<body>

	<form action="fileUpload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="f1">
		<button>UPLOAD</button>
	</form>
	
	<form action="multiFileUpload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="f1">
		<input type="file" name="f2">
		<button>UPLOAD</button>
	</form>
	
	<!-- name 이 같고 여러개 파일 넘어감 -->
	<form action="sameMultiFileUpload" method="POST" enctype="multipart/form-data">
		<input type="text" name="name">
		<input type="file" name="f1">
		<input type="file" name="f1">
		<input type="file" name="f1">
		<button>UPLOAD</button>
	</form>

</body>
</html>