<%@page import="packBD.GestorBD"%>
<%@page import="packClases.ListaObras"%>
<%@page import="packClases.Obra"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset='utf8'>
<title>Proyecto</title>
<link rel="stylesheet" href="css/style.css" />

</head>
<body>
	<div id="contenedor">
		<header>
			<h1>Nombre de la web</h1>
		</header>
		<c:set var="autor" value="${requestScope.autores}" scope="request"></c:set>

		<nav>
			<ul>
				<li>INICIO</li>
				<li>CATEGORIA</li>
				

				<c:choose>
					<c:when test="${not empty requestScope.userId}">
					<li><a href="UploadBook">CREAR HISTORIA</a></li>
						<li><a
							href='VerAutor?autor=<c:out value="${requestScope.userId}"></c:out>'><c:out
									value="${requestScope.userName}"></c:out></a></li>
						<li><a href="LogOut">Logout</a></li>
					</c:when>
					<c:otherwise>
						<li><a href='login.html'>IDENTIFICARSE</a></li>
						<li><a href="registrarse.html">REGISTRARSE</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>
		<section>
			<c:forEach items="${requestScope.obras}" var="ob">
				<article>
					<img alt='Imagen de perfil'
						src='<c:out value="${ob.portada}"></c:out>'>
					<ul>
						<li><a
							href="VerHistoria?op=0&hi=<c:out value="${ob.id}"></c:out>"><c:out
									value="${ob.titulo}"></c:out></a></li>
						<li><c:out value="${ob.fecha_in}"></c:out></li>
						<li><c:out value="${ob.fecha_mod}"></c:out></li>
						<li><a
							href="VerAutor?autor=<c:out value="${ob.autor}"></c:out>"><c:out
									value="${autor[ob.autor]}"></c:out></a></li>
					</ul>
					<div id='resumen'>
						<p>
							<c:out value="${ob.resumen}"></c:out>
						</p>
					</div>
				</article>
			</c:forEach>
		</section>
</body>
</html>
