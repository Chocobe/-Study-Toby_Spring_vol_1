<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="<%= application.getContextPath() %>"/>

<!DOCTYPE html>
<html lang="ko">
	<head>
		<meta charset="UTF-8">
		<title>index 페이지</title>
	</head>

	<body>
		<h1>index 페이지 입니다.</h1>
		
		<hr/>
		
		<h1>contextPath : ${contextPath}</h1>
		
		<label for="myBoard">테스트 게시판</label>
		<input type="button" value="테스트 게시판" id="myBoard" onclick="location.href='${contextPath}/myBoardView.do';">
	</body>
</html>