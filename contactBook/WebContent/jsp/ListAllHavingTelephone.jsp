<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="br.com.contactBook.dao.ContactDao,br.com.contactBook.model.User,java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Lista de Usuários</title>
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