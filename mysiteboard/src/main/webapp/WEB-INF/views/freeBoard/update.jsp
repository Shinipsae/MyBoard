<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/freeBoard/update/${dto.seq}" method="post">
<table border="1">
	<tr><th>제목</th><th>작성자</th></tr>
	<tr><td><input type="text" name="title" value="${dto.title}"></td><td>${dto.writer}</td></tr>
	<tr><th colspan="2">내용</th></tr>
	<tr><td colspan="2"><textarea name="content">${dto.content}</textarea></td></tr>
	<tr><td>비밀번호:</td><td><input type="password" name="password"></td></tr>
</table>
<input type="submit" value="수정">
</form>
</body>
</html>