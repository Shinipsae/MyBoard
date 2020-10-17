<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>삭제하기</title>
</head>
<body>
<%@ include file="head.jsp" %>
<form action="${pageContext.request.contextPath}/memberBoard/delete/${seq}" method="post">
비밀번호:<input type="password" name="password">
<input type="submit" value="삭제">
</form>
</body>
</html>