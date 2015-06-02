<%@page import="packBD.GestorBD"%>
<%@page import="packClases.ListaObras"%>
<%@page import="packClases.Obra"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta charset="UTF-8">
<title>Proyecto</title>
<link rel="stylesheet" href="css/style.css" /> 

<!-- Imports para BootStrap -->
<script src="js/jquery-1.10.2.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="js/bootstrap.min.js"></script>

</head>
<body>
<div class="container theme-showcase" role="main">

		

		<c:set var="autor" value="${requestScope.autores}" scope="request"></c:set>
	<jsp:include page="menu.jsp" />
		<!-- <nav>
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
		</nav>-->
		<div class='jumbotron'>
	<!-- 	<section>
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
		</section>-->
	<c:forEach items="${requestScope.obras}" var="ob">
						<div class="row">
							<div class="col-sm-4" ></div>
							<div class="col-sm-2" >
								<a href="VerHistoria?op=0&hi=<c:out value="${ob.id}"></c:out>"><c:out
										value="${ob.titulo}"></c:out></a>
							</div>
							<div class="col-sm-2" >
								<c:out value="${ob.fecha_in}"></c:out>
							</div>
							<div class="col-sm-2" >
								<c:out value="${ob.fecha_mod}"></c:out>
							</div>
							<div class="col-sm-2" >
								<a href="VerAutor?autor=<c:out value="${ob.autor}"></c:out>"><c:out
									value="${autor[ob.autor]}"></c:out></a>
							</div>
							<div class="col-sm-4" >
								<img alt='Imagen de perfil'
									src='<c:out value="${ob.portada}"></c:out>'>
							</div>
							<div class="col-sm-8" >
								<p class="text-justify"><c:out value="${ob.resumen}"></c:out></p>
							</div>
						</div>
						<br>
						</c:forEach></div></div></div>
</body>
</html>
