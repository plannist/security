<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
  prefix="security"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BORAJI.COM</title>
</head>
<body>
  <h2>Spring Security 4 - Hello World Example</h2>
  <hr />
  <h3>Admin dashboard</h3>
  <security:authorize access="isAuthenticated()">
    <b>Welcome! <security:authentication property="principal.username" /></b>
  </security:authorize>
  <br />
  <security:authorize access="isAuthenticated()">
    <a href="/">Home</a> | <a href="logout">Logout</a>
  </security:authorize>
</body>
</html>