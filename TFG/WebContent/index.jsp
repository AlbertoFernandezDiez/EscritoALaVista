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
<!--    <link rel=”stylesheet” href=”estilos.css”>-->
<link rel="stylesheet" href="css/style.css" />

</head>
<body>
	<div id="contenedor">
		<header>
			<h1>Nombre de la web</h1>
		</header>
		<nav>
			<ul>
				<li>INICIO</li>
				<li>CATEGORIA</li>
				<li>IDENTIFICARSE</li>
			</ul>
		</nav>
		<section>
			<c:forEach items="${requestScope.obras}" var="ob">
				<article>
					<ul>
						<li><a href="VerHistoria?op=0&hi=<c:out value="${ob.id}"></c:out>"><c:out value="${ob.titulo}"></c:out></a></li>
						<li><c:out value="${ob.fecha_in}"></c:out></li>
						<li><c:out value="${ob.fecha_mod}"></c:out></li>
						<li><a href="#">Yo</a></li>
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
