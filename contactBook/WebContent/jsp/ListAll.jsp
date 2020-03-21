<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="br.com.contactBook.dao.ContactDao,br.com.contactBook.model.User,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<table>
<c:forEach var="user" items="${users}">
<tr>
	<td>${user.id}</td>
	<td>${user.name}</td>
	<td>${user.email}</td>
	<td>${user.telephone.ddd}</td>
	<td>${user.telephone.telephoneNumber}</td>
	<td>${user.telephone.sort}</td>
	<td>${user.telephone.userId}</td>
</tr>
</c:forEach>
</table>
</body>
</html>