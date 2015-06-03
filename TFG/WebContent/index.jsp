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
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">
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

		<div class='jumbotron'>

			<c:forEach items="${requestScope.obras}" var="ob">
				<div class="row custom">
					<div class="col-sm-2"></div>
					<div class="col-sm-4">
						<a href="VerHistoria?op=0&hi=<c:out value="${ob.id}"></c:out>"><c:out
								value="${ob.titulo}"></c:out></a>
					</div>
					<div class="col-sm-2">
						<c:out value="${ob.fecha_in}"></c:out>
					</div>
					<div class="col-sm-2">
						<c:out value="${ob.fecha_mod}"></c:out>
					</div>
					<div class="col-sm-2">
						<a href="VerAutor?autor=<c:out value="${ob.autor}"></c:out>"><c:out
								value="${autor[ob.autor]}"></c:out></a>
					</div>
					<div class="col-sm-2">
						<img alt='Imagen de perfil'
							src='<c:out value="${ob.portada}"></c:out>'>
					</div>
					<div class="col-sm-10">
						<p class="text-justify">
							<c:out value="${ob.resumen}"></c:out>
						</p>
					</div>
				</div>
				<br>
			</c:forEach>
	</div>
				<ul class="pagination center">
					<c:forEach begin="0" end="${requestScope.max}" step="1"
						varStatus="loop">
						<c:choose>
							<c:when test="${loop.index eq requestScope.pos}">
								<li class="active"><a
									href='Index?pos=<c:out value="${loop.index}"></c:out>&show=1'><c:out
											value="${loop.index}"></c:out></a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href='Index?pos=<c:out value="${loop.index}"></c:out>&show=1'><c:out
											value="${loop.index}"></c:out></a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
	
	</div>
	</div>
</body>
</html>
