<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>
</head>
<body>
<%@ include file="head.jsp" %>
<table border="1">
<tr>
	<th>번호</th>
	<th>제목</th>
	<th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
	<th>타입</th>
</tr>
<c:forEach var="board" items="${result}" varStatus="i">
<tr>
	<td>${i.count}</td>
	<td><a href="${pageContext.request.contextPath}/freeBoard/read/${board.seq}">${board.title}</a></td>
	<td>${board.writer}</td>
	<td>${board.regDate}</td>
	<td>${board.readCnt}</td>
	<td>${board.boardType}</td>
</tr>
</c:forEach>
</table>
<a href="${pageContext.request.contextPath}/freeBoard/write"><button>새글</button></a>
</body>
</html>