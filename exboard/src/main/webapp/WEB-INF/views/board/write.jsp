<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성하기</title>
</head>
<body>
<form:form commandName="BoardVo" action="${pageContext.request.contextPath}/board/write" method="post">
	<table border="1">
	<tr>
	<td>작성자:</td>
	<td><form:input path="writer"/><form:errors path="writer"/></td>
	</tr>
	<tr><td>제목:</td>
	<td><form:input path="title"/><form:errors path="title"/></td>
	</tr>
	<tr>
	<td>내용:</td><td><form:input path="content"/><form:errors path="content"/></td>
	</tr>
	<tr><td>비밀번호:</td><td><input type="password" name="password"><form:errors path="password"/></td>
	</tr> 
	</table>
	<input type="submit" value="등록">
	</form:form>
</body>
</html>