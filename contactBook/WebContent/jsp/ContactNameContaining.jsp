<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<c:forEach var="contact" items="${users}">
			<tr>
				<td>${contact.name}</td>
				<td>${contact.email}</td>
				<td>${contact.telephone.ddd}</td>
				<td>${contact.telephone.telephoneNumber}</td>
				<td>${contact.telephone.sort}</td>
				<td>${contact.telephone.userId}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>