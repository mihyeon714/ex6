<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 방법1 -->
<%-- ${message} --%>

<!-- 방법2 -->
<tr>
	<td>${dto.num}</td>
	<td>${dto.contents}</td>
	<td>${dto.writer}</td>
	<td>${dto.reg_date}</td>
</tr>


<!-- 방법3 -->
<!-- 리스트자체를 받음 -->
