<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h2>index.jsp</h2>
	
	<a href="${pageContext.request.contextPath}/loginForm.jsp">1. 로그인(페이지 직접 호출)</a>
<!-- 	1.번의 사용 방법은 지양함. -->
	<br>
	<a href="${pageContext.request.contextPath}/login">2. 로그인(서블릿 호출)</a>

</body>
</html>