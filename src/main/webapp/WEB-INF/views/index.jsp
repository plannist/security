<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>  
  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JS Park securityTest Page</title>
</head>
<body>
	<h2>Spring Security 4 - Hello World Example</h2>
	<hr />
	<h3>
		Welcome !!
		<!-- 익명의 사용자를 의미한다. -->
		<security:authorize access="isAnonymous()">
			Guest
		</security:authorize>
		<!-- 인증된 사용자를 의미한다. -->
		<security:authorize access="isAuthenticated()">
			<security:authentication property="principal.username" />
		</security:authorize>
	</h3>
	<security:authorize access="isAnonymous()">
		Login as <a href="user">User</a> or <a href="/admin">Admin</a>
	</security:authorize>
	<security:authorize access="isAuthenticated()">
		<security:authorize access="hasRole('USER')">
			<a href="user">My Profile</a>
		</security:authorize>
		<security:authorize access="hasRole('ADMIN')">
			<a href="admin">My Admin Profile </a>
		</security:authorize>
		<a href="logout">Logout</a>
	</security:authorize>
</body>
</html>