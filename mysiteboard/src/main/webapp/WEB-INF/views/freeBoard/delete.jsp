<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제하기</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/freeBoard/delete/${seq}" method="post">
비밀번호:<input type="password" name="password">
<input type="submit" value="삭제">
</form>
</body>
</html>