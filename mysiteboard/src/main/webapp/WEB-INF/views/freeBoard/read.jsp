<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글내용</title>
</head>
<body>
<table border="1">
	<tr>
	<th>제목</th>
	<th>작성자</th>
	<th>등록일</th>
	<th>조회수</th>
	</tr>
	
	<tr>
	<td>${dto.title}</td>
	<td>${dto.writer}</td>
	<td>${dto.regDate}</td>
	<td>${dto.readCnt}</td>
	</tr>
	
	<tr><th colspan="4">내용</th></tr>
	<tr><td colspan="4">${dto.content}</td></tr>
</table>
<a href="${pageContext.request.contextPath}/freeBoard/list"><button>목록</button></a>
<a href="${pageContext.request.contextPath}/freeBoard/update/${dto.seq}"><button>수정</button></a>
<a href="${pageContext.request.contextPath}/freeBoard/delete/${dto.seq}"><button>삭제</button></a>
</body>
</html>