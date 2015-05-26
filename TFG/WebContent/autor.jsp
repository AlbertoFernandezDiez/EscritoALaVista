<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset='utf8'>
<c:set var="autor" value="${requestScope.autor}" scope="request"></c:set>
<title><c:out value="${autor.nombre}"></c:out></title>
</head>
<body>

<h1>Autor: <c:out value="${autor.nombre}"></c:out></h1>
<h2>Pais: <c:out value="${autor.pais}"></c:out></h2>
<h2>Nacimiento: <c:out value="${autor.nacimiento}"></c:out></h2>
<img alt="Foto de perfil" src="<c:out value="${autor.imagen}"></c:out>">
<p>
<c:set var="cap" value="emp" scope="request"></c:set>
</p>

</body>
</html>