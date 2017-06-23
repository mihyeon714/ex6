<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>fileView</title>
</head>
<body>

	<h2><a href="../resources/upload/${fileName}">${oriName}</a></h2>
	<a href="./fileDelete?fileName=${fileName}">DELETE</a>
	<a href="./fileDown?fileName=${fileName}&oriName=${oriName}">DOWNLOAD</a>
</body>
</html>