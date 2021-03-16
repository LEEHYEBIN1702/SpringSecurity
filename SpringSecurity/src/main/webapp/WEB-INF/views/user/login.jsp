<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
<font color="red">
${SPRING_SECURITY_LAST_EXCEPTION.message}
<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</font>
</c:if>
<h3>로그인</h3>
<form action="login" method="post">
ID : <input name="id">
PW : <input name="password">
<button>로그인</button>
</form>
<a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=3b5349c3a549df7c66e189320c742da8&redirect_uri=http://localhost:88/bank/callback">카카오 로그인</a>
</body>

</html>