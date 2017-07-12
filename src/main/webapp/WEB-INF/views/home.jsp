<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<a href="notice/noticeList">Notice</a>

<h1>
	Hello world!  
</h1>

<h1>
	Test 하이
</h1>

<a href="memo/memoList">MEMO</a> <br/>

<a href="file/fileUpload">FILE</a>

<P>  The time on the server is ${serverTime}. </P>



<form action=""> 
	<fieldset style="width:300px;">
		<legend>납품자정보</legend>
		<ol>
			<li> 납품자명: <input type="text" placeholder="name"></li>
			<li> email: <input type="email" placeholder="answs@naver.com"></li>
			<li> 홈페이지: <input type="url" placeholder="http://" ></li>
		</ol>
	</fieldset>

	<fieldset style="width:300px;">
		<legend>납품정보</legend>
		<ul>
			<li>상품명: 
			<input list="productName" name="productName">
        	<datalist id="productName">
	       		<option value="dog1004" label="도그1004">
	       		<option value="catchicken" label="고양이치킨">
	       		<option value="milk" label="우유">
        	</datalist>
			</li>
			<li>납품수량: <input type="number" placeholder="최소 10이상" min="10" max="100" step="10"></li>
			<li>납품등급: <input type="range" min="0" max="10" step="2"></li>
			<li>기타사항: <textarea></textarea></li>
		</ul>
	</fieldset>

	<button>send message</button>

</form>




</body>
</html>
