<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>

<c:out value="${requestScope.tit}" ></c:out>
</title>
<style>
table,th,td
{
border:1px solid black;
}
</style>
</head>
<body>

<table>
<tbody>
<tr><th>ID</th><th>Name</th><th>Role</th></tr>
<c:forEach items="${requestScope.chapterList}" var="emp">
<tr><td><c:out value="${emp.id}"></c:out></td>
<td><c:out value="${emp.nombre}"></c:out></td>
<td><c:out value="${emp.obra}"></c:out></td></tr>
</c:forEach>
</tbody>
</table>

</body>
</html>